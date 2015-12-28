package com.mfq.payment.impl;

import java.math.BigDecimal;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.mfq.annotation.PayAPIImpl;
import com.mfq.bean.PayCallbackResult;
import com.mfq.constants.CardType;
import com.mfq.constants.ErrorCodes;
import com.mfq.constants.OrderType;
import com.mfq.helper.SignHelper;
import com.mfq.payment.BasePaymentService;
import com.mfq.payment.PayAPIType;
import com.mfq.service.OrderService;
import com.mfq.service.PayRecordService;
import com.mfq.service.ProductService;
import com.mfq.utils.HttpUtil;
import com.mfq.utils.JsonUtil;

@PayAPIImpl(payAPIType = PayAPIType.INNER)
@Service
public class InnerServiceImpl extends BasePaymentService {

	private static final Logger logger = LoggerFactory.getLogger(InnerServiceImpl.class);

	@Resource
	OrderService orderService;
	@Resource
	ProductService productService;
	@Resource
	PayRecordService payRecordService;

	/**
	 * 必须的参数：orderNo, amount, quota, sign -- json格式
	 */
	@Override
	public String goPay(HttpServletRequest request, HttpServletResponse response, Map<String, Object> params,
			OrderType orderType) {
		
		logger.info("平台内支付.....");
		String ret = "";
		try {
			String url = buildPayCallbackURL();
			logger.info("inner callback is {}", url);

			Map<String, Object> map = Maps.newHashMap();
			map.put("order_no", params.get("order_no").toString());
			map.put("amount", params.get("amount").toString());
			String sign = SignHelper.makeSign(map);
			map.put("sign", sign);
			
			
			String body = JsonUtil.writeToJson(map);
			String resp = HttpUtil.post(url, body, true);
			logger.info("INNER callback info:{}", resp);
			ret = JsonUtil.toJson(0, "全部平台内支付－不走第三方支付平台！", map);
			ret = resp;
			logger.info("INNER goPay param info:{}", ret);
		} catch (Exception e) {
			logger.error("INNER_GOPAY_ERROR", e);
			ret = JsonUtil.toJson(ErrorCodes.CORE_ERROR, "INNER_GOPAY_ERROR", null);
		}
		return ret;
	}

	@Override
	public PayCallbackResult payCallback(HttpServletRequest request, HttpServletResponse response, String ret) {
		PayCallbackResult result = new PayCallbackResult();
		Map<String, Object> map = JsonUtil.readMapFromReq(request);
		if(!SignHelper.validateSign(map)){
			logger.error("非法请求！");
			return result;
		}
		result.setApiType(PayAPIType.INNER);
		result.setStatus(1);
		result.setMerchantaccount("MFQ");
		result.setOrderNo(map.get("order_no").toString());
		result.setTradeNo(map.get("order_no").toString());
		result.setAmount(new BigDecimal(map.get("amount").toString()));
		result.setBankCode("MFQ");
		result.setBank("MFQ");
		result.setCardType(CardType.UNDEFINED);
		result.setLastno("");
		// MD居然内部还有一个sign，也要进行校验！
		result.setSign("");
		return result;
	}

	@Override
	public String queryPay(String orderNo) {
		return null;
	}

	@Override
	public void refund() {

	}

	@Override
	public String buildPayCallbackURL() {
		return StringUtils.replace(callbackTmpl, "{tpp}", PayAPIType.INNER.getCode());
	}
}