package com.mfq.test;

import java.util.Map;

import com.google.common.collect.Maps;
import com.mfq.helper.SignHelper;
import com.mfq.utils.HttpUtil;
import com.mfq.utils.JsonUtil;

public class TestController {
	
	private final static String purl="http://i.5imfq.com/";

    public void testOrderCreate(){
        String url = purl+"/noti/msg/";
        Map<String, Object> params = Maps.newHashMap();
        params.put("uid", 2798);
        params.put("type", '0');
        params.put("page", 1);

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
