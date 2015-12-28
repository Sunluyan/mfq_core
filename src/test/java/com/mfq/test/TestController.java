package com.mfq.test;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Maps;
import com.mfq.helper.SignHelper;
import com.mfq.utils.HttpUtil;
import com.mfq.utils.JsonUtil;

public class TestController {
	
	
	@Test
	public void testAuthAudlt(){
        String url = purl+"auth/autonym";
        Map<String, Object> params = Maps.newHashMap();
        params.put("uid", 9);
        params.put("realname", "张辉");
        params.put("idcard", "421181199308122316");
        params.put("origin", "hubei");
        params.put("gender", 1);
        params.put("blackbox", "lasldfalsdfjklsadfj");
        params.put("user_type", 2);
        String sign = SignHelper.makeSign(params);
        params.put("sign", sign);
        String body = JsonUtil.writeToJson(params);
        String resp = HttpUtil.postJson(url, body, true);
        System.out.println(resp);
        Assert.assertEquals(0, 0);
        
	}
	
	private final static String purl="http://t.5imfq.com/";
	
//	@Test
    public void testOrderCreate(){
        String url = purl+"/order/create";
        Map<String, Object> params = Maps.newHashMap();
        params.put("uid", 2798);
        params.put("pid", 11);
        params.put("type", 2);
        params.put("amount", "1300");
        params.put("one_money", "300");
        params.put("online_pay", 200);
        params.put("period", 6);
        params.put("policy", 1);
        params.put("operation_time", "2015-12-21");

        String sign = SignHelper.makeSign(params);
        params.put("sign", sign);
        String body = JsonUtil.writeToJson(params);
        String resp = HttpUtil.postJson(url, body, true);
        System.out.println(resp);
    }
	
//	@Test
//    public void testAudltInfo(){
//        String url = purl+"/auth/adult/info";
//        Map<String, Object> params = Maps.newHashMap();
//        params.put("uid", 2798);
//        params.put("company", "美分期");
//        params.put("position", "开发");
//        params.put("department", "部门");
//        params.put("salary", 7888);
//        params.put("social_insurance", "8653365566");
//        params.put("work_years", 12);
//        
//
//        String sign = SignHelper.makeSign(params);
//        params.put("sign", sign);
//        String body = JsonUtil.writeToJson(params);
//        String resp = HttpUtil.postJson(url, body, true);
//        System.out.println(resp);
//    }
	
//	@Test
//    public void testStudentInfo(){
//        String url = purl+"/auth/student/info";
//        Map<String, Object> params = Maps.newHashMap();
//        params.put("uid", 2798);
//        params.put("student_id", "1231231");
//        params.put("school", "学校");
//        params.put("school_location", "北京");
//        params.put("grade", 3);
//        params.put("school_level", "level");
//        params.put("faculty", "系");
//        params.put("speciality", "专业");
//        params.put("scholastic_years", 3);
//
//
//        String sign = SignHelper.makeSign(params);
//        params.put("sign", sign);
//        String body = JsonUtil.writeToJson(params);
//        String resp = HttpUtil.postJson(url, body, true);
//        System.out.println(resp);
//    }
	
//	@Test
//    public void testPolicyList(){
//        String url = purl+"/order/list/";
//        Map<String, Object> params = Maps.newHashMap();
//        params.put("uid", 2798);
////        params.put("status", "1231231");
//        
//
//        String sign = SignHelper.makeSign(params);
//        params.put("sign", sign);
//        String body = JsonUtil.writeToJson(params);
//        String resp = HttpUtil.postJson(url, body, true);
//        System.out.println(resp);
//    }
//	
	
}
