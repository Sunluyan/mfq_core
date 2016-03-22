package com.mfq.test;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    private final static String purl = "http://i.5imfq.com";
//	private final static String purl="http://localhost:8080";
//private final static String purl = "http://t.5imfq.com:8080";


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



    public static void testJson() {
        String ask = "[{\"answer\":\"问题0\",\"question\":\"问题0\"},{\"answer\":\"回答1\",\"question\":\"问题1\"},{\"answer\":\"回答2\",\"question\":\"问题2\"},{\"answer\":\"回答3\",\"question\":\"问题3\"},{\"answer\":\"回答4\",\"question\":\"问题4\"}]";
        JSONArray json = JSON.parseArray(ask);
        System.out.println(json);
    }
    public static List<BigInteger> getFibonacci(){
        List<BigInteger> fibonaccis = new ArrayList<>();
        fibonaccis.add(BigInteger.ONE);
        BigInteger one = BigInteger.ZERO;
        BigInteger two = BigInteger.ONE;
        for(int i = 0;i<99;i++){
            BigInteger result = one.add(two);
            fibonaccis.add(result);
            one = two;
            two = result;
        }
        BigInteger number = BigInteger.ONE;
        for (BigInteger fibonacci : fibonaccis) {
            for(BigInteger i = BigInteger.valueOf(2);i.compareTo(fibonacci) < 0;i = i.add(BigInteger.ONE)){
                if(fibonacci.divide(i) == i.multiply(fibonacci)){
                    continue;
                }
            }
            number = fibonacci;
        }
        System.out.println(number.toString());


        return fibonaccis;
    }
    public static boolean isEqual(String str1,String str2){
        String a = "";
        String b = "";
        Integer size = 0;
        for(int i = 1;i<(str1.length()/2);i++){
            a = str1.substring(0,i);
            str1 = str1.substring(i);
            if(str1.contains(a)){
                size = i;
                break;
            }
            str1 = a+str1;
        }
        if(size == 0){
            return false;
        }
        b = str1.split(a)[0];
        if(!str1.equals(a+b+a)){
            return false;
        }
        return true;
    }
    public static void insertNumToList(List<Integer> list ,Integer num){
        int index = -1;
        boolean isBig2Small = false;
        if(list.get(0)>list.get(1)){
            isBig2Small = true;
        }
        for (int i = 0;i<list.size();i++) {
            if(isBig2Small){
                if(num>list.get(i)){
                    index = i;
                    break;
                }
            }else{
                if(num<list.get(i)){
                    index = i;
                    break;
                }
            }
        }

        list.add(list.get(list.size()-1));
        for(int i = list.size()-2;i>=0;i--){
            if(i == index){
                list.set(i,num);
                break;
            }
            list.set(i,list.get(i-1));
        }
        for (Integer integer : list) {
            System.out.println(integer);
        }
    }

    public static void findBiggest(String str){
        List<String> list = new ArrayList<>();
        Map<String,Integer> map = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            boolean isFind = false;
            String s = str.split("")[i];
            for (String word : list) {
                if(word.equals(s)){
                    int count = map.get(s);
                    map.put(s,count +1);
                    isFind = true;
                    break;
                }
            }
            if(!isFind){
                map.put(s,1);
                list.add(s);
            }
        }
        int biggest = 0;
        String biggestWord = "";
        for (String key : map.keySet()){
            Integer count = map.get(key);
            if(biggest<count){
                biggest = count;
                biggestWord = key;
            }
        }
        System.out.println(biggestWord);
    }

    public static void testProductDetail() {
        String url = purl + "/product/detail/";
        Map<String, Object> params = Maps.newHashMap();
        params.put("pid", "231");
        params.put("uid", 2847);
        String sign = SignHelper.makeSign(params);
        params.put("sign", sign);
        String body = JsonUtil.writeToJson(params);
        String resp = HttpUtil.postJson(url, body, true);
        System.out.println(resp);
    }

    public static void main(String[] args) throws IOException {

//        testOrderCreate();
//        testCallback();
//        testGopay();
//        testFinance();
//        testProductDetail();
//        testJson();
//        getFibonacci();
//        findBiggest("daaaabbbbcccccccc");
        testProductDetail();
    }

}


