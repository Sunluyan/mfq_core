package com.mfq.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.Maps;
import com.mfq.helper.SignHelper;
import com.mfq.payment.impl.WechatServiceImpl;
import com.mfq.payment.util.wechat.Configure;
import com.mfq.payment.util.wechat.HttpsUtil;
import com.mfq.service.PayService;
import com.mfq.service.user.UserService;
import com.mfq.utils.HttpUtil;
import com.mfq.utils.JsonUtil;
import com.mfq.utils.MFQAdminUtil;
import com.mfq.utils.XMLConverUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestController {
    
	
    public static void testCheckMobile(){
        String url = purl+"/finance/list/";
        Map<String, Object> params = Maps.newHashMap();
        params.put("uid", "2936");
        String sign = SignHelper.makeSign(params);
        params.put("sign", sign);
        String body = JsonUtil.writeToJson(params);
        String resp = HttpUtil.postJson(url, body, true);
        System.out.println(resp);
    }
    
    public static void testVcodeSend(){
        String url = purl+"/vcode/send/";
        Map<String, Object> params = Maps.newHashMap();
        params.put("mobile", "18338751231");
        String sign = SignHelper.makeSign(params);
        params.put("sign", sign);
        String body = JsonUtil.writeToJson(params);
        String resp = HttpUtil.postJson(url, body, true);
        System.out.println(resp);
    }
    
    public static void testResetPwdMobile(){
        String url = purl+"/a/password/reset/mobile/18612258336/";
        Map<String, Object> params = Maps.newHashMap();
        params.put("op", "mobile");
        String sign = SignHelper.makeSign(params);
        params.put("sign", sign);
        String body = JsonUtil.writeToJson(params);
        String resp = HttpUtil.postJson(url, body, true);
        System.out.println(resp);
    }
    
    public static void testVcodeValidate(){
        String url = purl+"/vcode/validate/";
        Map<String, Object> params = Maps.newHashMap();
        params.put("mobile", "18612258336");
        params.put("vcode", "7407");
        String sign = SignHelper.makeSign(params);
        params.put("sign", sign);
        String body = JsonUtil.writeToJson(params);
        String resp = HttpUtil.postJson(url, body, true);
        System.out.println(resp);
    }



    public static void testLogin(){
        String url = purl+"/login/";
        Map<String, Object> params = Maps.newHashMap();
        params.put("username", "18338751231");
        params.put("password", "4114388");
        params.put("blackbox", "ALSDJFOU1384SLDJFIQ");
        String sign = SignHelper.makeSign(params);
        params.put("sign", sign);
        String body = JsonUtil.writeToJson(params);
        String resp = HttpUtil.postJson(url, body, true);
        System.out.println(body);
        System.out.println(resp);
    }
    
    
    public static void testSetUser(){
        String url = purl+"/user/modify";
        Map<String, Object> params = Maps.newHashMap();
        params.put("uid", "9");
        params.put("nicke", "test");
        params.put("gender", 2);
        String sign = SignHelper.makeSign(params);
        params.put("sign", sign);
        String body = JsonUtil.writeToJson(params);
        String resp = HttpUtil.postJson(url, body, true);
        System.out.println(resp);
    }
    
    public static void testFinance(){
        String url = purl+"/finance/bill";
        Map<String, Object> params = Maps.newHashMap();
        params.put("uid", "201");
        params.put("type", 1);
        String sign = SignHelper.makeSign(params);
        params.put("sign", sign);
        String body = JsonUtil.writeToJson(params);
        String resp = HttpUtil.postJson(url, body, true);
        System.out.println(resp);
    }
    
    public static void testProductClass(){
        String url = purl+"/product/classproduct";
        Map<String, Object> params = Maps.newHashMap();
        params.put("city", 1);
        params.put("category", 0);
        params.put("sort", 3);
        String sign = SignHelper.makeSign(params);
        params.put("sign", sign);
        String body = JsonUtil.writeToJson(params);
        String resp = HttpUtil.postJson(url, body, true);
        System.out.println(resp);
    }
    
    public static void testProductList(){
        String url = purl+"/favorites/list";
        Map<String, Object> params = Maps.newHashMap();
        params.put("uid", 1);
        String sign = SignHelper.makeSign(params);
        params.put("sign", sign);
        String body = JsonUtil.writeToJson(params);
        String resp = HttpUtil.postJson(url, body, true);
        System.out.println(resp);
    }
    
    public static void testOrderDetail(){
        String url = purl+"/order/detail";
        Map<String, Object> params = Maps.newHashMap();
        params.put("uid", 201);
        params.put("order_no", "123456");
        String sign = SignHelper.makeSign(params);
        params.put("sign", sign);
        String body = JsonUtil.writeToJson(params);
        String resp = HttpUtil.postJson(url, body, true);
        System.out.println(resp);
    }
    
    public static void testOrderBook(){
        String url = purl+"/order/book";
        Map<String, Object> params = Maps.newHashMap();
        params.put("uid", 3110);
        params.put("pid", 206);
        String sign = SignHelper.makeSign(params);
        params.put("sign", sign);
        String body = JsonUtil.writeToJson(params);
        String resp = HttpUtil.postJson(url, body, true);
        System.out.println(resp);
    }
    
    public static void testCouponList(){
        String url = purl+"/coupon/list";
        Map<String, Object> params = Maps.newHashMap();
        params.put("uid", 201);
        params.put("status", 0);
        String sign = SignHelper.makeSign(params);
        params.put("sign", sign);
        String body = JsonUtil.writeToJson(params);
        String resp = HttpUtil.postJson(url, body, true);
        System.out.println(resp);
    }
    
    
    public static void testFavDel(){
        String url = purl+"/favorites/delete";
        Map<String, Object> params = Maps.newHashMap();
        params.put("uid", 201);
        params.put("pids", "1,2");
        String sign = SignHelper.makeSign(params);
        params.put("sign", sign);
        String body = JsonUtil.writeToJson(params);
        String resp = HttpUtil.postJson(url, body, true);
        System.out.println(resp);
    }
    
    public static void testFavAdd(){
        String url = purl+"/favorites/add";
        Map<String, Object> params = Maps.newHashMap();
        params.put("uid", 201);
        params.put("pid", "1");
        String sign = SignHelper.makeSign(params);
        params.put("sign", sign);
        String body = JsonUtil.writeToJson(params);
        String resp = HttpUtil.postJson(url, body, true);
        System.out.println(resp);
    }
    
    public static void testUserDetail(){
        String url = purl+"/user/profile";
        Map<String, Object> params = Maps.newHashMap();
        params.put("uid", 202);
        String sign = SignHelper.makeSign(params);
        params.put("sign", sign);
        String body = JsonUtil.writeToJson(params);
        String resp = HttpUtil.postJson(url, body, true);
        System.out.println(resp);
    }
    
    public static void testFeedback(){
        String url = purl+"/feedback/sub";
        Map<String, Object> params = Maps.newHashMap();
        params.put("uid", 1);
        params.put("content", "拉速度开房间爱死了房间爱死了付款是否拉屎的减肥拉菲啊撒的发垃圾啊地方");
        String sign = SignHelper.makeSign(params);
        params.put("sign", sign);
        String body = JsonUtil.writeToJson(params);
        String resp = HttpUtil.postJson(url, body, true);
        System.out.println(resp);
    }
    
    public static void testOrderList(){
        String url = purl+"/order/list";
        Map<String, Object> params = Maps.newHashMap();
        params.put("uid", 2798);
        String sign = SignHelper.makeSign(params);
        params.put("sign", sign);
        String body = JsonUtil.writeToJson(params);
        String resp = HttpUtil.postJson(url, body, true);
        System.out.println(resp);
    }
    
    
    
    public static void testInterView(){
        String url = purl+"/interview/update";
        Map<String, Object> params = Maps.newHashMap();
        params.put("uid", 1);
        params.put("realname", "张辉");
        params.put("idcard", "421181199308122316");
        params.put("school", "北京大学");
        params.put("grade", 1);
        params.put("contact", "15910812056");
        params.put("remark", "");
        String sign = SignHelper.makeSign(params);
        params.put("sign", sign);
        String body = JsonUtil.writeToJson(params);
        String resp = HttpUtil.postJson(url, body, true);
        System.out.println(resp);
    }
    
    public static void testInterviewApply(){
        String url = purl+"/interview/apply";
        Map<String, Object> params = Maps.newHashMap();
        params.put("uid", 1);
        params.put("realname", "会");
        params.put("idcard", "464654451141654654646");
        params.put("school", "北京大学");
        params.put("contact", "15910812056");
        String sign = SignHelper.makeSign(params);
        params.put("sign", sign);
        String body = JsonUtil.writeToJson(params);
        String resp = HttpUtil.postJson(url, body, true);
        System.out.println(resp);
    }
    
    public static void testClassify(){
        long begin = System.currentTimeMillis();
        String resp = HttpUtil.get(purl+"/class/info?id=1", false);
        long end = System.currentTimeMillis();
        System.out.println(end - begin +"ms");
        System.out.println(resp);
    }
    
    public static void testInterviewAudit(){
        String url = purl+"/interview/audit";
        Map<String, Object> params = Maps.newHashMap();
        params.put("uid", 201);
        params.put("realname", "会");
        params.put("idcard", "464654451141654654646");
        params.put("school", "北京大学");
        params.put("contact", "15910812056");
        params.put("grade", "研二");
        params.put("classes", "4班");
        params.put("remark", "审核通过。。。。");
        params.put("status", 1);
        String sign = SignHelper.makeSign(params);
        params.put("sign", sign);
        String body = JsonUtil.writeToJson(params);
        String resp = HttpUtil.postJson(url, body, true);
        System.out.println(resp);
    }
    
    public static void testInterviewUpdate(){
        String url = purl+"/interview/update";
        Map<String, Object> params = Maps.newHashMap();
        params.put("uid", 201);
        params.put("realname", "张会");
        params.put("idcard", "464654451141654654646");
        params.put("school", "北京大学");
        params.put("contact", "15910812056");
        params.put("grade", "研二");
        params.put("classes", "4班");
        params.put("remark", "审核通过。。。。");
        params.put("amount", 55555.0);
        params.put("status", 1);
        params.put("pid", 1);
        String sign = SignHelper.makeSign(params);
        params.put("sign", sign);
        String body = JsonUtil.writeToJson(params);
        String resp = HttpUtil.postJson(url, body, true);
        System.out.println(resp);
    }
    
    //"amount":44443,"operation_time":1456416000000,"period":3,"pid":141,"policy":0,
    // "sign":"EE94F0356BFB4C7FD30CD0A9586862A7","uid":3156
    public static void testOrderCreate(){
        String url = purl+"/order/create";
        Map<String, Object> params = Maps.newHashMap();
        params.put("amount", 44443);
        params.put("operation_time", 1456416000000l);
        params.put("period", 3);
        params.put("pid", 141);
        params.put("policy", 0);
        params.put("uid", 3156);
        params.put("uid", 3156);

        String sign = SignHelper.makeSign(params);
        params.put("sign", sign);
        String body = JsonUtil.writeToJson(params);
        String resp = HttpUtil.postJson(url, body, true);
        System.out.println(resp);
    }
    
    public static void testOrderRefund(){
        String url = purl+"/order/refund";
        Map<String, Object> params = Maps.newHashMap();
        params.put("uid", 201);
        params.put("order_no", "564656163113");
        params.put("refund_type", 1);
        String sign = SignHelper.makeSign(params);
        params.put("sign", sign);
        String body = JsonUtil.writeToJson(params);
        String resp = HttpUtil.postJson(url, body, true);
        System.out.println(resp);
        
    }
    
    public static void testOrderDeposit(){
        String url = purl+"/recharge/deposit";
        Map<String, Object> params = Maps.newHashMap();
        params.put("uid", 201);
        String sign = SignHelper.makeSign(params);
        params.put("sign", sign);
        String body = JsonUtil.writeToJson(params);
        String resp = HttpUtil.postJson(url, body, true);
        System.out.println(resp);
    }
    
    //
    public static void testMobilePay(){
        String url = purl+"/pay/mobile_pay/innerpay.do";
        Map<String, Object> params = Maps.newHashMap();
        params.put("uid", 201);
        params.put("order_no", "mn20150915150356365700c9");
        params.put("amount", "0.1");
        params.put("balance", 55555.63);
        String sign = SignHelper.makeSign(params);
        params.put("sign", sign);
        String body = JsonUtil.writeToJson(params);
        String resp = HttpUtil.postJson(url, body, true);
        System.out.println(resp);
    }
    
    public static void testRechargeHistory(){
        String url = purl+"/recharge/history";
        Map<String, Object> params = Maps.newHashMap();
        params.put("uid", 201);
        String sign = SignHelper.makeSign(params);
        params.put("sign", sign);
        String body = JsonUtil.writeToJson(params);
        String resp = HttpUtil.postJson(url, body, true);
        System.out.println(resp);
    }
    
    public static void testOrderAvailable(){
        String url = purl+"/order/available_balance";
        Map<String, Object> params = Maps.newHashMap();
        params.put("uid", 201);
        params.put("pid", 2);
        String sign = SignHelper.makeSign(params);
        params.put("sign", sign);
        String body = JsonUtil.writeToJson(params);
        String resp = HttpUtil.postJson(url, body, true);
        System.out.println(resp);
    }
    
    public static void testRestPass(){
        String url = purl+"/a/password/reset/";
        Map<String, Object> params = Maps.newHashMap();
        params.put("vcode", "2563");
        params.put("key", "15910812061");
        params.put("password", "12343");
        String sign = SignHelper.makeSign(params);
        params.put("sign", sign);
        String body = JsonUtil.writeToJson(params);
        String resp = HttpUtil.postJson(url, body, true);
        System.out.println(resp);
    }
    
    public static void testOrderCancel(){
        String url = purl+"/order/cancel";
        Map<String, Object> params = Maps.newHashMap();
        params.put("uid", 201);
        params.put("order_no", "15910812061");
        String sign = SignHelper.makeSign(params);
        params.put("sign", sign);
        String body = JsonUtil.writeToJson(params);
        String resp = HttpUtil.postJson(url, body, true);
        System.out.println(resp);
    }
    
    public static void testOrderFinancing(){
        String url = purl+"/order/finance_info";
        Map<String, Object> params = Maps.newHashMap();
        params.put("uid", 201);
        params.put("balance", 58.6);
        params.put("pid", 7);
        params.put("coupon_num","200");
        String sign = SignHelper.makeSign(params);
        params.put("sign", sign);
        String body = JsonUtil.writeToJson(params);
        String resp = HttpUtil.postJson(url, body, true);
        System.out.println(resp);
    }
    
    
    public static void testSendMessage(){
        String url = purl+"/wechat/sendMessage";
        Map<String, Object> params = Maps.newHashMap();
        params.put("msgType", "text");
        params.put("msg", "oooooo");
        params.put("user", "oWF4uwVeS8zBb13njV17VFRED6fQ");
        String sign = SignHelper.makeSign(params);
        params.put("sign", sign);
        String body = JsonUtil.writeToJson(params);
        String resp = HttpUtil.postJson(url, body, true);
        System.out.println(resp);
    }
    
    public static void testgetMedia(){
        String url = purl+"/wechat/user";
        Map<String, Object> params = Maps.newHashMap();
        params.put("openid", "og_GVwNVO7NU74dZ6fvr-KGXdCAA");
        params.put("msg", "oooooo");
        params.put("user", "og_GVwNVO7NU74dZ6fvr-KGXdCAA");
        String sign = SignHelper.makeSign(params);
        params.put("sign", sign);
        String body = JsonUtil.writeToJson(params);
        String resp = HttpUtil.postJson(url, body, true);
        System.out.println(resp);
    }
    
    public static void testSendMes(){
		String url = "http://m.5imfq.com/sms/send";
        Map<String, Object> params = Maps.newHashMap();
        params.put("mobile", "15667701199,15910812061");
        params.put("message", "this is test...【测试】");
        String sign = SignHelper.makeSign(params);
        String key = MFQAdminUtil.makeKey(params);
        params.put("sign", sign);
        params.put("key", key);
        String body = JsonUtil.writeToJson(params);
        String resp = HttpUtil.postJson(url, body, true);
        
        System.out.println(resp);
    }
    
    public static void testNotiMsgs(){
        String url = purl+"/noti/msg/";
        Map<String, Object> params = Maps.newHashMap();
        params.put("uid", "201");
        params.put("page", 1);
        params.put("type", 1);
        params.put("t", 10236523651l);
        String sign = SignHelper.makeSign(params);
        params.put("sign", sign);
        String body = JsonUtil.writeToJson(params);
        String resp = HttpUtil.postJson(url, body, true);
        System.out.println(resp);
    }
    
    public static void testinsertMsgs(){
        String url = purl+"picture/callback/auth/9?key=/u/front_t/o.jpg";
        Map<String, Object> params = Maps.newHashMap();
        params.put("uid", "201");
        params.put("type", 1);
        params.put("msg_id", 2);
        String sign = SignHelper.makeSign(params);
        params.put("sign", sign);
        String body = JsonUtil.writeToJson(params);
        String resp = HttpUtil.postJson(url, body, true);
        System.out.println(resp);
    }
    
    
    
    /**
     * pid = 31
     * amount = 10000
     * quota_left = 6000
     * policy = 0
     * period = 12
     */
    public static void testCreateFinance(){
   	 	String url = purl+"/create/finance/";
        Map<String, Object> params = Maps.newHashMap();
        params.put("uid", 2798);
        params.put("pid", 31);
        params.put("amount", 10000);
        params.put("policy", 0);
        params.put("period", 12);
        String sign = SignHelper.makeSign(params);
        params.put("sign", sign);
        System.out.println("params ok");
        String body = JsonUtil.writeToJson(params);
        String resp = HttpUtil.postJson(url, body, true);
        System.out.println(resp);
    }
    
    public static void testFinanceList(){
    	String url = purl+"/finance/list/";
    	System.out.println("testFinanceList");
        Map<String, Object> params = Maps.newHashMap();
        params.put("uid", "2798");
        String sign = SignHelper.makeSign(params);
        params.put("sign", sign);
        String body = JsonUtil.writeToJson(params);
        String resp = HttpUtil.postJson(url, body, true);
        System.out.println("respOver");
        System.out.println(resp);
    }
    
    public static void testpay(){
    	String url = purl+"pay/mobile_pay/innerpay.do";
        Map<String, Object> params = Maps.newHashMap();
        params.put("uid", "2798");
        params.put("amount", "7939.00");
        params.put("order_no", "mn2015121721074709680010");
        
        String sign = SignHelper.makeSign(params);
        params.put("sign", sign);
        String body = JsonUtil.writeToJson(params);
        String resp = HttpUtil.postJson(url, body, true);
    }
    
    public static void testOrderDetaill(){
    	String url = purl+"order/detail";
        Map<String, Object> params = Maps.newHashMap();
        params.put("uid", "2798");
        params.put("order_no", "mn2015121723433675610004");
        String sign = SignHelper.makeSign(params);
        params.put("sign", sign);
        String body = JsonUtil.writeToJson(params);
        String resp = HttpUtil.postJson(url, body, true);
        System.out.println(resp);
    }
    public static void testAdultInfo(){
        String url = purl+"/auth/adult/info/";
        Map<String, Object> params = Maps.newHashMap();
        params.put("uid", 2798);
        params.put("company", "大公司");
        params.put("position", "技术总监");
        params.put("department", "软件开发部");
        params.put("salary", "8000-10000");
        params.put("social_insurance", "没有");
        params.put("work_years", "3-5年");
        String sign = SignHelper.makeSign(params);
        params.put("sign", sign);
        String body = JsonUtil.writeToJson(params);
        String resp = HttpUtil.postJson(url, body, true);
        System.out.println(resp);
    }
    public static void testPresent(){
        String url = purl+"/activity/present/code";
        Map<String, Object> params = Maps.newHashMap();
        params.put("uid", 2798);
        params.put("code", "7865");
        String sign = SignHelper.makeSign(params);
        params.put("sign", sign);
        String body = JsonUtil.writeToJson(params);
        String resp = HttpUtil.postJson(url, body, true);
        System.out.println(resp);
    }


    public static void testProductSearch(){
        String url = purl+"/product/search";
        Map<String,Object> params = Maps.newHashMap();
        //{"amount":9000,"hos_id":1,"operation_time":1452700800000,"policy_num":0,"proname":"哈哈哈","sign":"08AF04F51EF2BCF40FB1FEF3B058CC18","uid":2936}
        params.put("keyword","北京");
        params.put("page",1);
        params.put("sign",SignHelper.makeSign(params));
        String body = JsonUtil.writeToJson(params);
        String resp = HttpUtil.postJson(url,body,true);
        System.out.println(resp);
    }
    public static void testHotWords(){
        String url = purl+"hot/word";
        System.out.println(url);
        String resp = HttpUtil.postJson(url,"",true);
        System.out.println(resp);
    }

    public static void testRegister(){
        String url = purl+"/register/";
        Map<String, Object> params = Maps.newHashMap();
        params.put("mobile", "18338751231");
        params.put("password", "4114388");
        params.put("vcode", "3761");
        params.put("dtp", "android");
        params.put("blackbox","eyJkYXRhIjoiRURFU0dcL3F4MU9FYzFuZ2JHOW9ISmlocFBNUz0iLCJidW5kbGUiOiJjb20ubWVpZmVucWlfOCIsIm9zIjoiQW5kcm9pZCIsImRldmljZV9pZCI6IktTWmRaQzdiOW1SZzNQc3hXemd3TFBKbkp5WWsyOSthMk9CdUl5WWNIT3dhN0IyNVU3WTlWbzIyaGl1PSIsInNlc3Npb25faWQiOiJtZWlmZW5xaTFlMDJlMTE5ZmI2YWM0ZjRhOWEwMjNkZmEzYzkyNzgiLCJ2ZXJzaW9uIjoiMi4wLjYifQ==,");
        String sign = SignHelper.makeSign(params);
        params.put("sign", sign);
        String body = JsonUtil.writeToJson(params);
        String resp = HttpUtil.postJson(url, body, true);
        System.out.println(resp);
    }
    public static void testProfile(){
        String url = purl+"/user/profile/";
        Map<String, Object> params = Maps.newHashMap();
        params.put("uid",3151);
        String sign = SignHelper.makeSign(params);
        params.put("sign", sign);
        String body = JsonUtil.writeToJson(params);
        String resp = HttpUtil.postJson(url, body, true);
        System.out.println(resp);
    }
    public static void testWebRegister(){
        String url = purl+"/web/register/";
        Map<String, Object> params = Maps.newHashMap();
        params.put("mobile","18338751231");
        params.put("nick","");
        params.put("password","4114388");
        params.put("vcode","8024");
        params.put("token_id","motherfucker");
        String body = JsonUtil.writeToJson(params);
        String resp = HttpUtil.postJson(url, body, true);
        System.out.println(resp);
    }

    public static void testNotification(){
        String url = purl+"/noti/msg/";
        Map<String, Object> params = Maps.newHashMap();
        params.put("page",1);
        params.put("type",0);
        params.put("uid",2780);
        params.put("sign",SignHelper.makeSign(params));
        String body = JsonUtil.writeToJson(params);
        String resp = HttpUtil.postJson(url, body, true);
        System.out.println(resp);
    }

    public static void testBeecloud(){
        //{"data":{"app_sign":"20d567eabcafc5486cea8631ebc7bb1c",
        // "timestamp":1456125638426,
        // "title":"美分期个人余额充值－1.0","total_fee":100,
        // "optional":{"uid":3467,"amount":"1.0","orderNo":"cz2016022215203879940d8b"},
        // "app_id":"967f5a80-ae09-4c46-b29e-cb0843887eed",Î
        // "bill_no":"cz2016022215203879940d8b","channel":"UN_APP"},
        // "code":0,"msg":"success"}


        String url = purl+"pay/mobile_pay/beecloud.do";
        Map<String, Object> params = Maps.newHashMap();
        Map<String,Object> data = new HashMap<>();
        data.put("app_sign","20d567eabcafc5486cea8631ebc7bb1c");
        data.put("timestamp",1456125638426l);
        data.put("title","美分期个人余额充值－1.0");
        data.put("total_fee",100);
        Map<String,Object> optional = Maps.newHashMap();
        optional.put("uid",3467);
        optional.put("amount","1.0");
        optional.put("orderNo","cz2016022215203879940d8b");
        data.put("optional",optional);
        data.put("app_id","967f5a80-ae09-4c46-b29e-cb0843887eed");
        data.put("bill_no","cz2016022215203879940d8b");
        data.put("channel","UN_APP");
        params.put("data",data);
        params.put("code",0);
        params.put("msg","success");
        String body = JsonUtil.writeToJson(params);
        String resp = HttpUtil.postJson(url, body, true);
        System.out.println(resp);
    }

    //"amount":44443,"operation_time":1456416000000,"period":3,"pid":141,"policy":0,
    // "sign":"EE94F0356BFB4C7FD30CD0A9586862A7","uid":3156
    public static void testOrderFinance(){
        String url = purl+"/order/create/finance";
        Map<String, Object> params = Maps.newHashMap();
        params.put("amount", 44443);
        params.put("operation_time", 1456416000000l);
        params.put("period", 3);
        params.put("pid", 141);
        params.put("policy", 0);
        params.put("uid", 3156);
        params.put("sign", "EE94F0356BFB4C7FD30CD0A9586862A7");

        String body = JsonUtil.writeToJson(params);
        String resp = HttpUtil.postJson(url, body, true);
        System.out.println(resp);
    }

    public static void testCallback(){
        /**
         * "order_no":""}
         */
        String url = purl+"/pay/mobile_callback/innerpay.do";
        Map<String, Object> params = Maps.newHashMap();
        params.put("sign", "DC0FB01C3019B5EAC170E83C0E5D8A7B");
        params.put("amount", "34999.02");
        params.put("order_no", "bl2016022516130377600097,bl2016022516130371010097,bl2016022516130328250097,bl2016022516130349560097,bl2016022516130366280097,bl2016022516130326960097");
        String body = JsonUtil.writeToJson(params);
        String resp = HttpUtil.postJson(url, body, true);
        System.out.println(resp);
    }

    public static void testFinanceList2(){
        String url = purl+"/finance/list";
        Map<String, Object> params = Maps.newHashMap();
        params.put("uid", 2847);
        String sign = SignHelper.makeSign(params);
        params.put("sign", sign);
        String body = JsonUtil.writeToJson(params);
        String resp = HttpUtil.postJson(url, body, true);
        System.out.println(resp);
    }

    public static void testHospitalDetail(){
        String url = purl+"/hospital/detail";
        Map<String, Object> params = Maps.newHashMap();
        params.put("id", 2);
        String sign = SignHelper.makeSign(params);
        params.put("sign", sign);
        String body = JsonUtil.writeToJson(params);
        String resp = HttpUtil.postJson(url, body, true);
        System.out.println(resp);
    }

    public static void testPolicylList(){
        String url = purl+"/policy/list";
        Map<String, Object> params = Maps.newHashMap();
        params.put("uid", 2798);
        String sign = SignHelper.makeSign(params);
        params.put("sign", sign);
        String body = JsonUtil.writeToJson(params);
        long begin = System.currentTimeMillis();
        String resp = HttpUtil.postJson(url, body, true);
        long end = System.currentTimeMillis();
        System.out.println(end-begin+"ms");
        System.out.println(resp);
    }
    public static void testOnline(){
        String url = purl+"/activity/online";
        Map<String, Object> params = Maps.newHashMap();
        params.put("uid", 2798);
        String sign = SignHelper.makeSign(params);
        params.put("sign", sign);
        String body = JsonUtil.writeToJson(params);
        String resp = HttpUtil.postJson(url, body, true);
        System.out.println(resp);
    }

    public static void testOnlineDetail(){
        String url = purl+"/activity/online/detail";
        Map<String, Object> params = Maps.newHashMap();
        params.put("id", 9);
        String sign = SignHelper.makeSign(params);
        params.put("sign", sign);
        String body = JsonUtil.writeToJson(params);
        long begin = System.currentTimeMillis();
        String resp = HttpUtil.postJson(url, body, true);
        long end = System.currentTimeMillis();
        System.out.println(end - begin);
        System.out.println(resp);
    }


    public static void testWechatPay(){
        String url = purl+"/pay/mobile_callback/wechat.do";
        Map<String, Object> params = Maps.newHashMap();
//        is_subscribe=N, appid=wx1c8dcf726a7d540e, fee_type=CNY,
//                nonce_str=6822951732be44edf818dc5a97d32ca6,
//                out_trade_no=mn201604051552273155015b,
//                device_info=BEB762D3-9386-46EC-B871-4EB0716023FF,
//                transaction_id=4003252001201604054568143679, trade_type=APP,
//                sign=BAD27F028F023CD5687539D9C7C3EDA7, result_code=SUCCESS,
//                mch_id=1268884501, total_fee=10, time_end=20160405161246,
//                openid=oxgUBtwhgcDSyK4JZN8aJHvoVDlQ, bank_type=CFT, return_code=SUCCESS,
//                cash_fee=10}

        params.put("is_subscribe","N");
        params.put("appid","wx1c8dcf726a7d540e");
        params.put("fee_type","CNY");
        params.put("nonce_str","6822951732be44edf818dc5a97d32ca6");
        params.put("out_trade_no","mn201604051552273155015b");
        params.put("device_info","BEB762D3-9386-46EC-B871-4EB0716023FF");
        params.put("transaction_id","4003252001201604054568143679");
        params.put("trade_type","APP");
        params.put("sign","BAD27F028F023CD5687539D9C7C3EDA7");
        params.put("result_code","SUCCESS");
        params.put("mch_id","1268884501");
        params.put("total_fee","10");
        params.put("time_end","20160405161246");
        params.put("openid","oxgUBtwhgcDSyK4JZN8aJHvoVDlQ");
        params.put("bank_type","CFT");
        params.put("return_code","SUCCESS");
        params.put("cash_fee","10");

        try {
            String body = XMLConverUtil.writeObj2Xml(params);
            String resp = HttpUtil.postJson(url, body, true);
            System.out.println(resp);
        }catch(Exception e){

        }
    }

    
//    private final static String purl="http://i.5imfq.com/";
	//private final static String purl="http://t.5imfq.com/";
//	private final static String purl="http://m.5imfq.com/";
//	private final static String purl="http://localhost:8080/mfq-app";
  //  private final static String purl="http://localhost:7777/";
    private final static String purl = "http://localhost:8080/";
	
    public static void main(String[] args){

//        testClassify();
//        testOnline();
//        testOnlineDetail();
    }
    
    
}