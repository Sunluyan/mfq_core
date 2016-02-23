package com.mfq.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.collect.Maps;
import com.mfq.helper.SignHelper;
import com.mfq.utils.JsonUtil;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;

public class TestController {
	
	private final static String purl="http://i.5imfq.com/";


    public void testOrderCreate() throws IOException {
//        String url = purl+"/finance/order/";
//        Map<String, Object> params = Maps.newHashMap();
//        params.put("uid", "2936");
//        params.put("orderNo", "mn2016011116070133780097");
//
//        String sign = SignHelper.makeSign(params);
//        params.put("sign", sign);
//        String body = JsonUtil.writeToJson(params);
//        String resp = HttpUtil.postJson(url, body, true);
//        System.out.println(resp);

//
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "hzucmj");
        map.put("age", 22);
        map.put("yyy",null);
//
//
//        String t = JsonUtil.toJson(map, false,false);
//        System.out.print(t);


        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
        User user = new User(1,"jack",null);
        map.put("su",user);
        String outJson = mapper.writeValueAsString(map);
        System.out.println(outJson);


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
