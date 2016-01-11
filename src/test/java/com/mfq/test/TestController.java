package com.mfq.test;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.mfq.utils.MD5Util;
import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Maps;
import com.mfq.helper.SignHelper;
import com.mfq.utils.HttpUtil;
import com.mfq.utils.JsonUtil;

public class TestController {
	
	private final static String purl="http://i.5imfq.com/";

    public void testOrderCreate(){
        String url = purl+"/coupon/del/";
        Map<String, Object> params = Maps.newHashMap();
        params.put("uid", 2798);
        params.put("coupon_num", '3');

        String sign = SignHelper.makeSign(params);
        params.put("sign", sign);
        String body = JsonUtil.writeToJson(params);
        String resp = HttpUtil.postJson(url, body, true);
        System.out.println(resp);
    }

    public static void main(String[] args) {
        TestController test = new TestController();
        test.testOrderCreate();
    }
}
