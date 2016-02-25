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

    public static void main(String[] args) {
        TestController test = new TestController();
        try {
            test.testOrderCreate();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class User{
    int id;
    String name;
    String age;

    public User(int id, String name, String age){
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
