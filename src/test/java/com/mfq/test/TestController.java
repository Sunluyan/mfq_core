package com.mfq.test;

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
public String getBCSign(String app_id,String app_secret,long timestamp){
    String sign = MD5Util.md5Digest(app_id+timestamp+app_secret).toLowerCase();
    return sign;
}


    /**
     * {sign='da802f76badf55900fd9b6d6ae1956b0', timestamp=1451545260000, channel_type='UN',
     * sub_channel_type='UN_APP', transaction_type='PAY', transaction_id='cz2015123115001581820c26',
     * transaction_fee=100, trade_success=true,
     * message_detail={bizType=000201, orderId=cz2015123115001581820c26,
     * txnSubType=01,
     * signature=A9YU0G0U0/WtEZmhoRTBrWpP5wncplRR7WYJBge8jDVBtw3izW4Nk1AJdjds3QQIhOu1g9SW4zuHJiMezG32zdt/tO4OqvLzQZQz8A6yFzdb/CmrH9THlm0VGJr5rpK/83iOPS88v6GCLY+890D9xUgKlhBB40WlrGaMrszvCacfmtUUNIK4jaWyu7TY+YL1lLM64yb6Ct2HndwQE0zI/AwScaaBSuRkZRJY6FHZUIn+bFa79MCmTf+l3EnFLaVG+mqdoAYrsaphXs17CiWZZEB+uaNOe1I9uPJJbwVotn6jbCPOcxaOQ2q9+wY3YI3X1aEO1tSZuPP2vb3IMj7E1Q==,
     * traceNo=913940, settleAmt=100, settleCurrencyCode=156, settleDate=1231, txnType=01, certId=69597475696,
     * encoding=UTF-8, version=5.0.0, queryId=201512311500239139408, accessType=0, tradeSuccess=true, respMsg=Success!,
     * traceTime=1231150023, txnTime=20151231150023, merId=898111448161528, currencyCode=156, respCode=00, signMethod=01, txnAmt=100},
     * optional={uid=3110, amount=1, orderNo=cz2015123115001581820c26}}
     */
    public void testBeeCloud(){

        Map<String, Object> params = Maps.newHashMap();
        //开始制作请求报文

        long timestamp = 1451545260000l;
        String sign = "da802f76badf55900fd9b6d6ae1956b0";
        String channel_type = "UN";
        String sub_channel_type = "UN_APP";
        String transaction_type = "PAY";
        String transaction_id = "cz2015123115001581820c26";
        Integer transaction_fee = 100;
        boolean trade_success = true;

        Map<String,Object> optional =  new HashMap<>();
        optional.put("uid",3110);
        optional.put("orderNo","cz2015123115001581820c26");
        optional.put("amount",1);

        Map<String,Object> message_detail = new HashMap<>();
        message_detail.put("bizType",000201);
        message_detail.put("orderId","cz2015123115001581820c26");
        message_detail.put("txnSubType",01);
        message_detail.put("signature","A9YU0G0U0/WtEZmhoRTBrWpP5wncplRR7WYJBge8jDVBtw3izW4Nk1AJdjds3QQIhOu1g9SW4zuHJiMezG32zdt/tO4OqvLzQZQz8A6yFzdb/CmrH9THlm0VGJr5rpK/83iOPS88v6GCLY+890D9xUgKlhBB40WlrGaMrszvCacfmtUUNIK4jaWyu7TY+YL1lLM64yb6Ct2HndwQE0zI/AwScaaBSuRkZRJY6FHZUIn+bFa79MCmTf+l3EnFLaVG+mqdoAYrsaphXs17CiWZZEB+uaNOe1I9uPJJbwVotn6jbCPOcxaOQ2q9+wY3YI3X1aEO1tSZuPP2vb3IMj7E1Q==");
        message_detail.put("traceNo",913940);
        message_detail.put("settleAmt",100);

        params.put("timestamp",timestamp);
        params.put("sign",sign);
        params.put("channel_type",channel_type);
        params.put("sub_channel_type",sub_channel_type);
        params.put("transaction_type",transaction_type);
        params.put("transaction_id",transaction_id);
        params.put("transaction_fee",transaction_fee);
        params.put("trade_success",trade_success);
        params.put("optional",optional);
        params.put("message_detail",message_detail);



        String url = "http://i.5imfq.com/pay/mobile_callback/beecloud.do";
        String body = JsonUtil.writeToJson(params);
        System.out.println(body);
        String resp = HttpUtil.postJson(url, body, true);
        System.out.println("begen print response");
        System.out.println(resp);
    }



    public static void main(String[] args) {
        TestController test = new TestController();
        System.out.println("begin");
        test.testBeeCloud();
    }
}
