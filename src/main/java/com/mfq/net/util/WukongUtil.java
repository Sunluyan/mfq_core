package com.mfq.net.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.mfq.constants.Constants;
import com.mfq.net.wukongbao.pojo.WkbConstants;
import com.mfq.net.wukongbao.pojo.WukongRequest;
import com.mfq.utils.BeanUtil;
import com.mfq.utils.JsonUtil;
import com.mfq.utils.MD5Util;
import com.mfq.utils.RSAUtils;
import com.wkb.openapi.security.signature.SignatureUtils;

import net.sf.json.JSONObject;

public class WukongUtil {
	
    private static final Logger logger = LoggerFactory
            .getLogger(WukongUtil.class);
	
    
    /**
     *   1   签名 加密
     * @param mapTemp
     * @return
     */
    public static Map<String, Object> encryptAndSign(Map<String, Object> mapTemp){
    	Map<String,Object> map = null;
    	try {
    		map = SignatureUtils.encryptAndSign(mapTemp, WkbConstants.WKB_PUK_KEY.trim(), WkbConstants.DEV_PRI_KEY.trim(), "UTF-8", true, true);
    	}catch (InvalidKeyException e){
    		e.printStackTrace();
    		System.out.println("处错误啦。。。。");
    	}catch (Exception e){
    		e.printStackTrace();
    		
    		System.out.println("错误信息"+e.getMessage());
    	}
    	return map;
    }
    
    /**
     *  3 解密
     * @param response
     * @return
     * @throws Exception
     */
    public static String checkResponse(String response) throws Exception{
    	
    	JSONObject responseJson = JSONObject.fromObject(response);
        //如果bizContent为空 就不用解密了
        boolean isCheckSign = responseJson.has("sign");
        String bizContent = "";
        if (isCheckSign) {
        	bizContent = SignatureUtils.checkSignAndDecrypt(responseJson, WkbConstants.WKB_PUK_KEY.trim(),WkbConstants.DEV_PRI_KEY.trim(), isCheckSign, isCheckSign);
        } else {
        	bizContent = responseJson.toString();
        }
        return bizContent;
        
    }
    
    
    /**
     * 2 post  
     * @param url
     * @param map
     * @return
     * @throws Exception
     */
    public static String postRequest(String url, Map<String, Object> map) throws Exception{
    	HttpPost request = new HttpPost(url);
    	
    	@SuppressWarnings({ "deprecation", "resource" })
    	HttpClient client = new DefaultHttpClient();
    	
    	List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
    	for (String key : map.keySet()) {
    		nameValuePairs.add(new BasicNameValuePair(key, (String)map.get(key)));  
    	}
        
        request.addHeader("Content-type", "application/x-www-form-urlencoded");  
        request.setEntity(new UrlEncodedFormEntity(nameValuePairs,"UTF-8")); 
        //使用http调用请求
        HttpResponse response = client.execute(request);
        HttpEntity responseEntity = response.getEntity();
        //获取返回json字符串
        String  str= EntityUtils.toString(responseEntity);
        
        return str;
        
    }
    
    public static String postRequest(Map<String, Object> map) throws Exception{
    	return postRequest(WkbConstants.INSURE_URL, map);
    }
    
    
    
}
