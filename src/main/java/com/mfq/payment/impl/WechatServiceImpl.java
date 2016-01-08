package com.mfq.payment.impl;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.mfq.annotation.PayAPIImpl;
import com.mfq.bean.OrderInfo;
import com.mfq.bean.PayCallbackResult;
import com.mfq.bean.Product;
import com.mfq.constants.CardType;
import com.mfq.constants.ErrorCodes;
import com.mfq.constants.OrderType;
import com.mfq.dataservice.context.AppContext;
import com.mfq.helper.SignHelper;
import com.mfq.payment.BasePaymentService;
import com.mfq.payment.PayAPIType;
import com.mfq.payment.util.wechat.Configure;
import com.mfq.payment.util.wechat.HttpsUtil;
import com.mfq.payment.util.wechat.PayReqData;
import com.mfq.service.OrderService;
import com.mfq.service.PayRecordService;
import com.mfq.service.ProductService;
import com.mfq.utils.DateUtil;
import com.mfq.utils.JsonUtil;
import com.mfq.utils.MD5;
import com.mfq.utils.XMLConverUtil;

@PayAPIImpl(payAPIType = PayAPIType.WECHAT)
@Service
public class WechatServiceImpl extends BasePaymentService {

    private static final Logger logger = LoggerFactory
            .getLogger(WechatServiceImpl.class);

    @Resource
    OrderService orderService;
    @Resource
    ProductService productService;
    @Resource
    PayRecordService payRecordService;

    private String genNonceStr() {
		Random random = new Random();
		return MD5.getMessageDigest(String.valueOf(random.nextInt(10000)).getBytes());
	}
    
    @Override
    public String goPay(HttpServletRequest request,
            HttpServletResponse response, Map<String, Object> params,
            OrderType orderType) {
        String ret = null;
        try {
            String orderNo = (String) params.get("order_no");
            String pname = "";
            if (orderType ==  OrderType.RECHARGE) {
                pname = "美分期个人余额充值－" + String.valueOf(params.get("amount"));
            } else if(orderType ==  OrderType.REFUND){
            	pname = "美分期个人余额充值－" + String.valueOf(params.get("amount"));
            } else {
                OrderInfo order = orderService.findByOrderNo(orderNo);
                Product product = productService.findById(order.getPid());
                pname = product.getName();
            }
            String nonce_str = genNonceStr();
            int amount = (int)(Float.parseFloat(params.get("amount").toString())*100);//按分计算
            PayReqData reqBean = new PayReqData(AppContext.getUuid(), nonce_str,
                    pname, orderNo, amount,
                    AppContext.getIp(), buildPayCallbackURL());
            String sign = SignHelper.makeWxSign(reqBean.toMap(), Configure.key);
            reqBean.setSign(sign);
            String xml = new HttpsUtil().sendPost(Configure.PAY_REQ, reqBean);
            Map<String, Object> soap = XMLConverUtil.convertSoapToMap(xml);
            logger.info("WECHAT goPay param info:{}", soap);
            
            if (!SignHelper.validateWxSign(soap, Configure.key)) {
                logger.error("WECHAT_RESPONSE_SIGN_ERROR");
                logger.info("wechat_response_info:{}", soap);
                return JsonUtil.toJson(ErrorCodes.CORE_ERROR,
                        "WECHAT_RESPONSE_SIGN_ERROR", null);
            }
            
            String msg = "";
            if (StringUtils.equalsIgnoreCase("SUCCESS",(String) soap.get("return_code"))) {
                if (StringUtils.equalsIgnoreCase("SUCCESS",(String) soap.get("result_code"))) {
                    Map<String, Object> map = Maps.newHashMap();
                    map.put("appid", soap.get("appid"));
                    map.put("partnerid", soap.get("mch_id"));
                    map.put("prepayid", soap.get("prepay_id"));
                    map.put("package", "Sign=WXPay");
                    map.put("noncestr", genNonceStr());
                    map.put("timestamp", (System.currentTimeMillis()/1000)+"");
                    map.put("sign", SignHelper.makeWxSign(map, Configure.key));
                    // 业务返回的具体数据（以下字段在return_code 和result_code 都为SUCCESS 的时候有返回）
                    map.put("code_url", soap.get("code_url"));
                    return JsonUtil.successResultJson(map);
                } else {
                    msg = (String) soap.get("err_code");
                }
            } else {
                msg = (String) soap.get("return_msg");
            }
            logger.warn("Gopay_ResponseError:{}", soap);
            ret = JsonUtil.toJson(ErrorCodes.CORE_ERROR, msg, null);
        } catch (Exception e) {
            logger.error("WECHAT_GOPAY_ERROR", e);
            ret = JsonUtil.toJson(ErrorCodes.CORE_ERROR, "WECHAT_GOPAY_ERROR",
                    null);
        }
        return ret;
    }

    @Override
    public PayCallbackResult payCallback(HttpServletRequest request,
            HttpServletResponse response, String ret) {
        PayCallbackResult result = new PayCallbackResult();
        result.setApiType(PayAPIType.WECHAT);
        ResponseData resp = new ResponseData("FAIL","");
        
        try {
            String xml = JsonUtil.readRequestString(request);
            Map<String, Object> soap = XMLConverUtil.convertSoapToMap(xml);
            logger.info("WECHAT payCallback param info:{}", soap);
            if (!SignHelper.validateWxSign(soap, Configure.key)) {
                logger.warn("WECHAT支付回调验签未通过或解析失败");
                return null;
            }
            if (	StringUtils.equalsIgnoreCase("SUCCESS",(String) soap.get("return_code"))
            	 && StringUtils.equalsIgnoreCase("SUCCESS",(String) soap.get("result_code"))) {
            	
                if (	StringUtils.equalsIgnoreCase((String) soap.get("appid"), Configure.appID)
                     || StringUtils.equalsIgnoreCase((String) soap.get("mch_id"), Configure.mchID)) {
                    // appId不一致
                	logger.warn("appId不一致！！");
                }
                result.setStatus(1);
                result.setMerchantaccount((String) soap.get("mch_id"));
                result.setOrderNo((String) soap.get("out_trade_no"));
                result.setTradeNo((String) soap.get("transaction_id"));
                result.setAmount(new BigDecimal(Integer.parseInt(soap.get("total_fee").toString()) ).divide(new BigDecimal(100)));
                result.setBankCode((String) soap.get("bank_type"));
                result.setBank((String) soap.get("bank_type"));
                result.setCardType(CardType.UNDEFINED);
                result.setLastno("");
                result.setPayAt(DateUtil.convertYYYYMMDDHHMMSS((String) soap.get("time_end")));
                // MD居然内部还有一个sign，也要进行校验！
                result.setSign((String) soap.get("sign"));
                resp.setReturn_code("SUCCESS");
                resp.setReturn_msg("OK");
                
            } else {
                result.setStatus(0);
                resp.setReturn_msg("Wechat支付回调状态判断异常");
                logger.error("WECHAT支付给商户结果为支付失败！非正常交易逻辑，请关注！");
            }
        } catch (Exception e) {
            resp.setReturn_msg("支付回调处理异常");
            logger.error("WECHAT callback process error", e);
        }
//        try {
//        	logger.info("cover xml  resp : {}", resp);
//            String str = XMLConverUtil.writeObj2Xml(resp);
//            response.setCharacterEncoding("utf-8");
//            response.setContentType("text/plain");
//            logger.info("response write..");
//            response.getWriter().write(str);
//            logger.info("response write end.");
//        } catch (IOException ex) {
//            logger.error("WECHAT response error", ex);
//        }
        try{
        	ret = XMLConverUtil.writeObj2Xml(resp);
        } catch (Exception ex){
        	logger.error("WECHAT response error", ex);
        }
        logger.info("WECHAT callback response:{}", resp);
        return result;
    }
    
    @Override
    public void refund() {

    }

    @Override
    public String queryPay(String orderNo) {
        return null;
    }

    @Override
    public String buildPayCallbackURL() {
        return StringUtils.replace(callbackTmpl, "{tpp}",
                PayAPIType.WECHAT.getCode());
    }

   public class ResponseData {
    	
        String return_code;
        String return_msg;

        ResponseData() {}
        
        ResponseData(String return_code, String return_msg) {
            this.return_code = return_code;
            this.return_msg = return_msg;
        }

        public String getReturn_code() {
            return return_code;
        }

        public void setReturn_code(String return_code) {
            this.return_code = return_code;
        }

        public String getReturn_msg() {
            return return_msg;
        }

        public void setReturn_msg(String return_msg) {
            this.return_msg = return_msg;
        }
        
        @Override
        public String toString(){
        	return "return_code ="+this.return_code+", return_msg="+this.return_msg;
        }
    }

}


