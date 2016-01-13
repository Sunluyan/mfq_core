package com.mfq.helper;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import com.mfq.utils.MessageDigestUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.mfq.constants.Constants;
import com.mfq.utils.JsonUtil;
import com.mfq.utils.MD5Util;
import com.mfq.utils.RSAUtils;

public class SignHelper {

    private static final Logger logger = LoggerFactory
            .getLogger(SignHelper.class);

    // 规则：map内的key自然排序，之后进行MD5，末尾加入appKeyValue
    public static boolean validateSign(Map<String, Object> map) {
        String sign = makeSign(map);
        String outSign = fetchSign(map);
        if(StringUtils.equalsIgnoreCase(sign, outSign)){
            logger.info("sign ok!");
            return true;
        }else{
            logger.warn("sign bad! local:{}, from req:{}", sign, outSign);
            return false;
        }
    }
    
    public static String makeSign(Map<String, Object> map){
        if (CollectionUtils.isEmpty(map)) {
            logger.warn("Map is null or size is little than 1");
            return null;
        }
        SortedMap<String, Object> sort = new TreeMap<String, Object>(map);
        StringBuffer signBuffer = new StringBuffer("");
        for (String key : sort.keySet()) {
            if (StringUtils.equalsIgnoreCase(key, "sign")) {
                continue;
            }
            signBuffer.append(key).append("=").append(sort.get(key));
        }
        String sign = MD5Util.md5Digest(
                MD5Util.md5Digest(signBuffer.toString()) + Constants.SEC_KEY);
        logger.info("Make sign:{}, org string is:{}", sign, signBuffer.toString());
        return sign;
    }
    

    
    // 规则：map内的key自然排序，之后进行MD5，末尾加入appKeyValue
    public static boolean validateWxSign(Map<String, Object> map, String secKey) {
        String sign = makeWxSign(map, secKey);
        String outSign = fetchSign(map);
        if(StringUtils.equalsIgnoreCase(sign, outSign)){
            logger.info("sign ok!");
            return true;
        }else{
            logger.warn("sign bad! local:{}, from req:{}", sign, outSign);
            return false;
        }
    }
    
    public static String makeWxSign(Map<String, Object> map, String secKey){
        if (CollectionUtils.isEmpty(map)) {
            logger.warn("Map is null or size is little than 1");
            return null;
        }
        SortedMap<String, Object> sort = new TreeMap<String, Object>(map);
        StringBuffer signBuffer = new StringBuffer("");
        for (String key : sort.keySet()) {
            if (StringUtils.equalsIgnoreCase(key, "sign")) {
                continue;
            }
            signBuffer.append(key).append("=").append(sort.get(key)).append("&");
        }
        signBuffer.append("key=").append(secKey);
        String sign = MD5Util.md5Digest(signBuffer.toString()).toUpperCase();
        logger.info("Make sign:{}, org string is:{}", sign, signBuffer.toString());
        return sign;
    }

    /**
     * 生成signature , 传入jsapi_ticket , noncestr,timestamp , url ,通过sh1加密得到.
     * @param map
     * @return signature
     */
    public static String makeWxSign(Map<String, Object> map){
        if (CollectionUtils.isEmpty(map)) {
            logger.warn("Map is null or size is little than 1");
            return null;
        }
        StringBuffer signBuffer = new StringBuffer();
//        params.put("noncestr", nonceStr);
//        params.put("timestamp", timestamp);
//        params.put("url", url);
//        params.put("jsapi_ticket", ticket);
        signBuffer.append("jsapi_ticket="+map.get("jsapi_ticket").toString()+"&");
        signBuffer.append("noncestr="+map.get("noncestr").toString()+"&");
        signBuffer.append("timestamp="+map.get("timestamp").toString()+"&");
        signBuffer.append("url="+map.get("url").toString());

        String sign = MessageDigestUtils.sha1(signBuffer.toString());
        logger.info("Make signature:{}, signBuffer:{}", sign,signBuffer.toString() );
        return sign;
    }

    public static void main(String[] args) {
        Map<String,Object> map = new HashMap<>();
        map.put("amount",9000);
        map.put("hos_id",1);
        map.put("operation_time",1452700800000l);
        map.put("policy_num",0);
        map.put("proname","双眼皮");
        map.put("uid",2936);
        map.put("sign","264C6C546E06F34A712A289F18E2A16F");
        String sign = makeSign(map);
        System.out.println(sign);
    }

    public static String fetchSign(Map<String, Object> map){
        return (String) map.get("sign");
    }
}