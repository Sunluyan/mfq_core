package com.mfq.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.collect.Maps;
import com.mfq.bean.*;
import com.mfq.bean.app.CouponInfo2App;
import com.mfq.bean.coupon.Coupon;
import com.mfq.bean.user.User;
import com.mfq.payment.impl.UnionpayServiceImpl;
import com.mfq.service.*;
import com.mfq.service.sms.SMSService;
import com.mfq.service.user.UserService;
import org.apache.commons.lang.StringUtils;
import org.jboss.netty.util.internal.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mfq.annotation.LoginRequired;
import com.mfq.bean.user.UserQuota;
import com.mfq.constants.ErrorCodes;
import com.mfq.constants.OrderType;
import com.mfq.constants.PayStatus;
import com.mfq.dataservice.context.UserIdHolder;
import com.mfq.helper.SignHelper;
import com.mfq.payment.BasePaymentService;
import com.mfq.payment.PayAPIType;
import com.mfq.payment.PayFactory;
import com.mfq.service.user.UserQuotaService;
import com.mfq.utils.JsonUtil;
import com.mfq.utils.RequestUtils;

@Controller
public class PaymentController {

	private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

	@Resource
	PayService payService;
	@Resource
	OrderService orderService;
	@Resource
	PayRecordService payRecordService;
	@Resource
	UserQuotaService userQuotaService;
	@Resource
	FinanceBillService financeBillService;
    @Resource
    UnionpayServiceImpl unionpayService;
	@Resource
	CouponService couponService;
    @Resource
    InviteService inviteService;
	@Resource
	BilltopayService billtopayService;
	
	
	/**
	 * @param tpp
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/pay/mobile_pay/{tpp}.do", method = RequestMethod.POST)
	@ResponseBody
	@LoginRequired
	public String mobileGoPay(@PathVariable String tpp, HttpServletRequest request, HttpServletResponse response)
	{
		String ret = "";
		long start = System.currentTimeMillis();
		BasePaymentService service2 = PayFactory.getInstance(tpp);
		logger.info("Mobiles goPay start");
		try {
			Map<String, Object> params = JsonUtil.readMapFromReq(request);
			if (!SignHelper.validateSign(params)) { // 签名验证失败
				ret = JsonUtil.toJson(ErrorCodes.SIGN_VALIDATE_ERROR, "签名验证失败", null);
				logger.error("签名验证失败！ret={}", ret);
				return ret;
			}
			String orderNo = (String) params.get("order_no");

			if(orderNo.contains("bl")){
				//生成支付订单号,并把orderNo变成PAYNO
				orderNo = billtopayService.makePayNo(orderNo);
				params.put("order_no",orderNo);
			}
			OrderType orderType = payService.getOrderType(orderNo);

			BigDecimal amount = new BigDecimal(params.get("amount").toString()); // 充值额度或实际需支付金额
																				 // 或 还款金额


			PayAPIType apiType = PayAPIType.fromCode(tpp);
	        if (apiType == null) {
	            return null;
	        }


			ret = payService.beforeGoPayCheck(orderType, amount, orderNo, apiType);
			if (StringUtils.isNotBlank(ret)) {
				logger.warn("Gopay前订单校验失败！");
				return ret;
			}


			BasePaymentService service = PayFactory.getInstance(tpp);
			logger.info("tpp:{}",tpp);
			if (service == null) {
				ret = JsonUtil.toJson(ErrorCodes.SIGN_VALIDATE_ERROR, "不支持的付款类型", null);
				logger.warn("Unsupported tpp: {}", tpp);
				return ret;
			}


			// 注意会有重复goPay状况，save的db操作中实际是带有ignore的
			long s = payRecordService.saveRecord(orderType, UserIdHolder.getUserId(), orderNo, amount);
			logger.info("save2PayRecord! orderNo={}, count={}", orderNo, s);

            System.out.println("before go pay  params:   "+params);
            ret = service.goPay(request, response, params, orderType);
			
		} catch (Exception e) {
			logger.error("Exception", e);
			ret = JsonUtil.toJson(ErrorCodes.CORE_ERROR, "系统错误", null);
		} finally {

			long end = System.currentTimeMillis();
			logger.info("goPay spend:{}s", end - start);
			logger.info("ret in goPay: {}",ret);

		}
		return ret;
	}

	
	
	/**
	 * 支付回调函数
	 * @param tpp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/pay/mobile_callback/{tpp}.do", method = { RequestMethod.GET, RequestMethod.POST })
	public void callback(@PathVariable String tpp, HttpServletRequest request, HttpServletResponse response)
			 {
		// String lockFlag = "";
		String ret = "";
		try {

			logger.info("{} pay callback received tpp: {}", tpp, RequestUtils.formatRequestParameters(request));
			//获取支付方式服务
			BasePaymentService service = PayFactory.getInstance(tpp);
			if (service == null) {
				logger.warn("Unsupported tpp: {}", tpp);
				throw new Exception("Unsupported tpp:"+tpp);
			}

			//传入 request、response 获取支付结果的实体类
			PayCallbackResult result = service.payCallback(request, response, ret);
			if(result.getOrderNo().contains("pa")){
				result.setOrderNo(billtopayService.payNoToBillsNo(result.getOrderNo()));
			}
			if(result.getOrderNo()==null){
				ret = JsonUtil.toJson(ErrorCodes.CORE_PARAM_NULL, "参数不对", null);
				throw new Exception("参数不对");
			}

			logger.info("{} get callback result:{}", tpp, result);
			
			// 根据订单号判断，是充值，支付还是还款
			if (payService.getOrderType(result.getOrderNo()) == OrderType.RECHARGE) {

				if (result.getStatus() != PayStatus.GO_PAY.getValue()) {
					logger.error("Pay callback status is:{}", result.getStatus());
					throw new Exception("回调充值状态异常");
				}
				logger.info("mobile_callback result = {}  , status = {}", result);
				//充值成功后修改各种记录
				payService.updateRechargePayOk(result, PayStatus.PAID);

			}
			else if(payService.getOrderType(result.getOrderNo()) == OrderType.REFUND){//还款（还分期账单）
				//还款，修改financeBill状态
				payService.updateOrderRefundOk(result);
			}

			else if(payService.getOrderType(result.getOrderNo()) == OrderType.ONLINE){
				//订单支付
				payService.updateOrderPayOk(result);

			}

			logger.info("result.getApiType().getCode():{}",result.getApiType().getCode());



		}catch (Exception e) {
			logger.error("MOBILE_CALLBACK_EXCEPTION", e);
			e.printStackTrace();   
			ret = JsonUtil.toJson(9999,e.getMessage(),null);
		}finally {
            // 释放分布式锁
			/*
			 * lock.unlock(lockFlag);
			 */
        }


        try {
	    	 //如果ret还是""的话，就说明是正确的，设置为正确的样子
	    	if(ret.equals("")){
	    		ret = JsonUtil.successResultJson();
	    	}
	        response.setCharacterEncoding("utf-8");
	        response.setContentType("text/plain");
	        logger.info("response write..");
	        response.getWriter().write(ret);
	        logger.info("response write end.");
	    } catch (IOException ex) {
	        logger.error("WECHAT response error", ex);
	    }
		
		logger.info("{} pay callback receive success", tpp);
	}


    /**
     * channel_type : WX/ALI/UN/KUAIQIAN/JD/BD/YEE/PAYPAL 分别代表微信/支付宝/银联/快钱/京东/百度/易宝/PAYPAL
     *
     * request中有
     sign String 32位小写
     timestamp	Long	1426817510111
     channel_type	String	'WX' or 'ALI' or 'UN' or 'KUAIQIAN' or 'JD' or 'BD' or 'YEE' or 'PAYPAL'
     sub_channel_type	String	'WX_APP' or 'WX_NATIVE' or 'WX_JSAPI' or 'WX_SCAN' or 'ALI_APP' or 'ALI_SCAN' or 'ALI_WEB' or 'ALI_QRCODE' or 'ALI_OFFLINE_QRCODE' or 'ALI_WAP' or 'UN_APP' or 'UN_WEB' or 'PAYPAL_SANDBOX' or 'PAYPAL_LIVE' or 'JD_WAP' or 'JD_WEB' or 'YEE_WAP' or 'YEE_WEB' or 'YEE_NOBANKCARD' or 'KUAIQIAN_WAP' or 'KUAIQIAN_WEB' or 'BD_APP' or 'BD_WEB' or 'BD_WAP'
     transaction_type	String	'PAY' or 'REFUND'
     transaction_id	String
     transaction_fee 订单总金额 单位为分
     trade_success
     message_detail
     optional
     * @param request
     * @param response
     */
    @RequestMapping(value = "/pay/mobile_callback/beecloud.do",method={RequestMethod.POST,RequestMethod.GET})
    public void BCCallback(HttpServletRequest request,HttpServletResponse response){
        String ret = "";
		logger.info("mobile_callback beecoud start"+request);
        try {
			BeeCloudResult result = new BeeCloudResult(request);
            //不论结果怎么样,只要签名正确,都应该先返回success.success代表接收正确
            if(result.getTrade_success()){
                response.getWriter().append("success");
                response.getWriter().flush();
                response.getWriter().close();
            }

            if(! UnionpayServiceImpl.checkSign(result. getTimestamp(),result.getSign())){
                throw new Exception("签名不正确!");
            }

            if(! result.getTrade_success()){
                throw new Exception("用户付款失败");
            }

            //在创建BeeCloudResult对象时,已经做了各种错误判断.出错了就把sign设为null,所以参数缺失错误可以用sign==null来判断
            if(result.getSign()==null){
                logger.error("参数错误!");
                throw new Exception("参数错误!");
            }
            //验证付款金额是否和订单需要付款的金额相等
			String totalFee = result.getTransaction_fee().toString();
			//把0.01变成1
			String amount = String.valueOf(Double.parseDouble(result.getOptional().get("amount").toString())*100);
            amount = amount.substring(0,amount.indexOf("."));
			if(!totalFee.equals(amount)){
				logger.error("应支付金额与实际支付不相等!totalFee:{},amount{}",totalFee,amount);
				throw new Exception("应支付金额与实际支付不相等!");
			}


            //如果是银联付款的话
            if(result.getChannel_type().equals("UN")){
                ret = unionpayService.beeCloudCallback(result,request,response);
            }

            inviteService.inviteMoneyRecordOperation(result);

        } catch (Exception e) {
           logger.error("BeeCloud error",e);
        }


    }

}























