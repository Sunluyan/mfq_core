package com.mfq.test;

import java.util.Map;

import com.google.common.collect.Maps;
import com.mfq.helper.SignHelper;
import com.mfq.utils.HttpUtil;
import com.mfq.utils.JsonUtil;

public class TestController {
	
	private final static String purl="http://m.5imfq.com/";

    public void testOrderCreate(){
        String url = purl+"/auth/sms/";
        Map<String, Object> params = Maps.newHashMap();
        params.put("mobile", "15910812061");
        params.put("msg", "您好，美分期于2016年2月6日至17日放假，放假期间暂停后台业务内部升级，在此期间您可正常提交申请审核工作将于2月18号正式启动，给您带来不便请谅解，恭祝您新春快乐。");
//        params.put("page", 1);

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
