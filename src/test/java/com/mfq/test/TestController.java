package com.mfq.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.collect.Maps;
import com.mfq.helper.SignHelper;
import com.mfq.utils.HttpUtil;
import com.mfq.utils.JsonUtil;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;

public class TestController {
	
	private final static String purl="http://t.5imfq.com:8080/";


    public void testOrderCreate() throws IOException {
        String url = purl+"/finance/list/";
        Map<String, Object> params = Maps.newHashMap();
        params.put("uid", "2936");
        params.put("orderNo", "mn2016011116070133780097");

        String sign = SignHelper.makeSign(params);
        params.put("sign", sign);
        String body = JsonUtil.writeToJson(params);
        String resp = HttpUtil.postJson(url, body, true);
        System.out.println(resp);
    }
    /*
    amount=400order_no=bl20160225171509454100a5
     */
    public static void testCallback(){
        String url = purl+"/pay/mobile_callback/innerpay.do";
        Map<String, Object> params = Maps.newHashMap();
        params.put("amount", 400);
        params.put("order_no", "bl20160225171509454100a5");

        String body = JsonUtil.writeToJson(params);
        String resp = HttpUtil.postJson(url, body, true);
        System.out.println(resp);
    }

    public static void main(String[] args) {
        testCallback();
    }
}


