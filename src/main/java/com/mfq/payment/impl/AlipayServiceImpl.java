package com.mfq.payment.impl;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.mfq.annotation.PayAPIImpl;
import com.mfq.bean.PayCallbackResult;
import com.mfq.constants.OrderType;
import com.mfq.payment.BasePaymentService;
import com.mfq.payment.PayAPIType;

@PayAPIImpl(payAPIType = PayAPIType.ALIPAY)
@Service
public class AlipayServiceImpl extends BasePaymentService {

    @Override
    public String goPay(HttpServletRequest request,
            HttpServletResponse response, Map<String, Object> params,
            OrderType orderType) {
        return null;
    }

    @Override
    public PayCallbackResult payCallback(HttpServletRequest request,
            HttpServletResponse response, String ret) {
        return null;
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
                PayAPIType.ALIPAY.getCode());
    }
}
