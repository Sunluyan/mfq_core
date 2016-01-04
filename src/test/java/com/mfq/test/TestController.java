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



    public void testBeeCloud(){
        String app_secret = "31b648a9-2837-461d-a129-e46a5bab5fca";

        Map<String, Object> params = Maps.newHashMap();
        //开始制作请求报文
        String app_id = "967f5a80-ae09-4c46-b29e-cb0843887eed";

        long timestamp = new Date().getTime();
        String sign = getBCSign(app_id,app_secret,timestamp);
        String channel_type = "UN";
        String sub_channel_type = "UN_APP";
        String transaction_type = "PAY";
        String transaction_id = "201506101035040000001";
        Integer transaction_fee = 1;
        String bill_no = "mn20151230162149038300a5";
        String title = "用户xx购买了xxx产品";
        boolean trade_success = true;
        Map<String,Object> optional =  new HashMap<>();
        optional.put("uid",3110);
        optional.put("orderNo","mn20151230162149038300a5");
        optional.put("amount",0.01);
        Map<String,Object> message_detail = new HashMap<>();
        message_detail.put("uid",3110);

        params.put("timestamp",timestamp);
        params.put("sign",sign);
        params.put("channel_type",channel_type);
        params.put("sub_channel_type",sub_channel_type);
        params.put("transaction_type",transaction_type);
        params.put("transaction_id",transaction_id);
        params.put("transaction_fee",transaction_fee);
        params.put("bill_no",bill_no);
        params.put("title",title);
        params.put("trade_success",trade_success);
        params.put("optional",optional);
        params.put("message_detail",message_detail);



        String url = "http://localhost:7777/pay/mobile_callback/beecloud.do";

        String body = JsonUtil.writeToJson(params);
        System.out.println(body);
        String resp = HttpUtil.postJson(url, body, true);
        System.out.println(resp);
    }

    /**
     *
     * {sign='805bed572972b72382f852c30abb6819',
     * timestamp=1451472540000,
     * channel_type='UN',
     * sub_channel_type='UN_APP', transaction_type='PAY', transaction_id='mn20151230184514638700a5', transaction_fee=100, trade_success=true, message_detail={bizType=000201, orderId=mn20151230184514638700a5, txnSubType=01, signature=Rr5YW6VNlkynWxrhJ/hqLEIOht47S1o/1Aadh7gvRlDOvJju7fM+Kifoeql2bCxX2IXFL25dfY6wNKNJVV1lkG2GN1K9boA1o3E4x6ys/OQjPaCQAEOduqeFxgsGqkKPAbw2OTpmVr++J5xfqcaAstPTHhOP1XVbB1upLlWdIn379iFoKKVuHvQDXnOzBBUg0PalP6To/m/it3anXf+qv13x+4503lDGFY0iARHMV9Ae3D+LOIY2omVFQ7G2fdcpQGygLrTM9g5aycw9ggCFit7GnHib+qWzAjuqHkOnciC9yxMZ9KCQfkpO2j6x2+VjAYE7uD8ASYbelFST8VKVIA==, traceNo=085620, settleAmt=100, settleCurrencyCode=156, settleDate=1230, txnType=01, certId=69597475696, encoding=UTF-8, version=5.0.0, queryId=201512301845180856208, accessType=0, tradeSuccess=true, respMsg=Success!, traceTime=1230184518, txnTime=20151230184518, merId=898111448161528, currencyCode=156, respCode=00, signMethod=01, txnAmt=100}, optional={}}
     * @param args
     */

    public static void main(String[] args) {
        TestController test = new TestController();
        System.out.println("begin");
        test.testBeeCloud();
    }
}
