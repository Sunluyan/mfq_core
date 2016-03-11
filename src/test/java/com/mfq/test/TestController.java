package com.mfq.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.collect.Maps;
import com.mfq.helper.SignHelper;
import com.mfq.utils.HttpUtil;
import com.mfq.utils.JsonUtil;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;

public class TestController {

    //	private final static String purl="http://i.5imfq.com";
    private final static String purl = "http://m.5imfq.com";
//	private final static String purl="http://localhost:8080";


    public static void testOrderCreate() throws IOException {
        String url = purl + "/finance/list/";
        Map<String, Object> params = Maps.newHashMap();
        params.put("uid", "2847");
        params.put("orderNo", "mn2016011116070133780097");

        String sign = SignHelper.makeSign(params);
        params.put("sign", sign);
        String body = JsonUtil.writeToJson(params);
        String resp = HttpUtil.postJson(url, body, true);
        System.out.println(resp);
    }

    /*
    "retryCounter":0,"transaction_id":"pa2016022717091540560001","retry_counter":0,"transaction_fee":10000,"channelType":"UN","sub_channel_type":"UN_APP","optional":{"uid":3686,"amount":100,"orderNo":"pa2016022717091540560001"},"transaction_type":"PAY","notify_url":"http://m.5imfq.com/pay/mobile_callback/beecloud.do","transactionId":"pa2016022717091540560001","transactionType":"PAY","transactionFee":10000,"tradeSuccess":true,"notifyUrl":"http://m.5imfq.com/pay/mobile_callback/beecloud.do","id":"49cc22c0-6ef2-40ad-9813-2eec0c8482ce","channel_type":"UN","messageDetail":{"bizType":"000201","orderId":"pa2016022717091540560001","txnSubType":"01","signature":"qLw/jtjlvQ+97/xzkpK9sV/PQRpuf4Yd8e8NC7Q9kbKCUv4pJJyqZ5/Gqy3+8zoZyMJY1stVaOK3KRJP45aiDkYtRzFDmuRH2x0IISHI7ze2QVBNmamyUChr9hT7FtmRPEaatnujRSIfwu6ok/8C4v/k4AXLBxCic9gVhyZw4wp0H/Vbf7ghlDE+aD7U172/tTvvbG0BfZDQkEXd/4WH0TLnT/VXRR69QnmdGAzw4ldV4tDuc4KDK6rGTsK9YU4RqJlm9mZg9fxyGqt3VSWAyYHPfHTVrSLGP8pMFMMSBPV9jdkWzC8JT+qASAaiRjdMqm52PLXgT3Dq+ts7OteNFA==","traceNo":"634423","settleAmt":"10000","settleCurrencyCode":"156","settleDate":"0227","txnType":"01","certId":"69597475696","encoding":"UTF-8","version":"5.0.0","queryId":"201602271709166344238","accessType":"0","tradeSuccess":true,"respMsg":"Success!","traceTime":"0227170916","txnTime":"20160227170916","merId":"898111448161528","currencyCode":"156","respCode":"00","signMethod":"01","txnAmt":"10000"},"message_detail":{"bizType":"000201","orderId":"pa2016022717091540560001","txnSubType":"01","signature":"qLw/jtjlvQ+97/xzkpK9sV/PQRpuf4Yd8e8NC7Q9kbKCUv4pJJyqZ5/Gqy3+8zoZyMJY1stVaOK3KRJP45aiDkYtRzFDmuRH2x0IISHI7ze2QVBNmamyUChr9hT7FtmRPEaatnujRSIfwu6ok/8C4v/k4AXLBxCic9gVhyZw4wp0H/Vbf7ghlDE+aD7U172/tTvvbG0BfZDQkEXd/4WH0TLnT/VXRR69QnmdGAzw4ldV4tDuc4KDK6rGTsK9YU4RqJlm9mZg9fxyGqt3VSWAyYHPfHTVrSLGP8pMFMMSBPV9jdkWzC8JT+qASAaiRjdMqm52PLXgT3Dq+ts7OteNFA==","traceNo":"634423","settleAmt":"10000","settleCurrencyCode":"156","settleDate":"0227","txnType":"01","certId":"69597475696","encoding":"UTF-8","version":"5.0.0","queryId":"201602271709166344238","accessType":"0","tradeSuccess":true,"respMsg":"Success!","traceTime":"0227170916","txnTime":"20160227170916","merId":"898111448161528","currencyCode":"156","respCode":"00","signMethod":"01","txnAmt":"10000"},"trade_success":true,"sign":"648fca295acf2e16f8008670750e6ce2","signAll":"dafe5f734edad8e85a5fca0838dde697","timestamp":1456564140000}

     */
    public static void testCallback() {

        String url = purl + "/pay/mobile_callback/bcUnionpay.do";
        Map<String, Object> params = Maps.newHashMap();
        params.put("amount", 400);
        params.put("order_no", "bl20160225171509454100a5");

        String body = JsonUtil.writeToJson(params);
        String resp = HttpUtil.postJson(url, body, true);
        System.out.println(resp);
    }

    //{  "order_no" : "bl2016022620405411150097,bl2016022620405522140097,bl2016022620405568630097",
    // "amount" : 8749.74,  "sign" : "9CCDCC60C483C914BED16292EE605C2E",  "uid" : 2847}
    public static void testGopay() {
        String url = purl + "/pay/mobile_pay/bcUnionpay.do";
        Map<String, Object> params = Maps.newHashMap();

        params.put("order_no", "bl2016022620405514960097,bl2016022620405566380097");
        params.put("amount", 5833.16);
        params.put("sign", "2B3282886AF74AC5B0C464981A70CE0C");
        params.put("uid", 2847);

        String body = JsonUtil.writeToJson(params);
        String resp = HttpUtil.postJson(url, body, true);
        System.out.println(resp);
    }

    public static void testFinance() {
        String url = purl + "/order/finance_info";
        Map<String, Object> params = Maps.newHashMap();
        params.put("pid", 223);

        String sign = SignHelper.makeSign(params);
        params.put("sign", sign);
        String body = JsonUtil.writeToJson(params);
        String resp = HttpUtil.postJson(url, body, true);
        System.out.println(resp);
    }

    public static void testOnline() {
        String url = purl + "/activity/online/detail";
        Map<String, Object> params = Maps.newHashMap();
        params.put("id", 7);
        String sign = SignHelper.makeSign(params);
        params.put("sign", sign);
        String body = JsonUtil.writeToJson(params);
        String resp = HttpUtil.postJson(url, body, true);
        System.out.println(resp);
    }

    public static void testSearch() {
        String url = purl + "/product/search";
        Map<String, Object> params = Maps.newHashMap();
        params.put("keyword", "美莱");
        params.put("page", 1);
        String sign = SignHelper.makeSign(params);
        params.put("sign", sign);
        String body = JsonUtil.writeToJson(params);
        String resp = HttpUtil.postJson(url, body, true);
        JSONObject json = JSON.parseObject(resp);
        JSONArray array = json.getJSONArray("data");
        System.out.println(array.size());
        System.out.println(resp);
    }


    public static void main(String[] args) throws IOException {

//        testOrderCreate();
//        testCallback();
//        testGopay();
//        testFinance();
        testOnline();
//        testSearch();
    }

}


