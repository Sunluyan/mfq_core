package com.mfq.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mfq.annotation.LoginRequired;
import com.mfq.bean.PayCallbackResult;
import com.mfq.bean.user.UserQuota;
import com.mfq.constants.ErrorCodes;
import com.mfq.constants.OrderType;
import com.mfq.constants.PayStatus;
import com.mfq.dataservice.context.UserIdHolder;
import com.mfq.helper.SignHelper;
import com.mfq.payment.BasePaymentService;
import com.mfq.payment.PayAPIType;
import com.mfq.payment.PayFactory;
import com.mfq.service.FinanceBillService;
import com.mfq.service.OrderService;
import com.mfq.service.PayRecordService;
import com.mfq.service.PayService;
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
		logger.info("Mobiles goPay start");
		try {
			Map<String, Object> params = JsonUtil.readMapFromReq(request);
			if (!SignHelper.validateSign(params)) { // 签名验证失败
				ret = JsonUtil.toJson(ErrorCodes.SIGN_VALIDATE_ERROR, "签名验证失败", null);
				logger.error("签名验证失败！ret={}", ret);
				return ret;
			}
			String orderNo = (String) params.get("order_no");
			OrderType orderType = payService.getOrderType(orderNo);
			
			BigDecimal amount = new BigDecimal(params.get("amount").toString()); // 充值额度或实际需支付金额
																				 // 或 还款金额
			BigDecimal balancePay = new BigDecimal(0); // 余额部分，若为null应置为0
			if (params.get("balance") != null) {
				balancePay = new BigDecimal(params.get("balance").toString()); // 使用余额部分（目前包含优惠券的价值！！！）
			}
			String couponNum="";
			if(params.get("coupon_num")!=null){
				couponNum = params.get("coupon_num").toString();
			}
			
			PayAPIType apiType = PayAPIType.fromCode(tpp);
	        if (apiType == null) {
	            return null;
	        }
	        
			ret = payService.beforeGoPayCheck(orderType, amount, balancePay, couponNum, orderNo, apiType);
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
			long s = payRecordService.saveRecord(orderType, UserIdHolder.getLongUid(), orderNo, amount,
					balancePay);
			logger.info("save2PayRecord! orderNo={}, num={}", orderNo, s);
			
			
			ret = service.goPay(request, response, params, orderType);
			
		} catch (Exception e) {
			logger.error("Exception", e);
			ret = JsonUtil.toJson(ErrorCodes.CORE_ERROR, "系统错误", null);
		} finally {
			
			long end = System.currentTimeMillis();
			logger.info("goPay spend:{}s", end - start);
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
}