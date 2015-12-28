package com.mfq.net.wukongbao;

import java.security.InvalidKeyException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.wkb.openapi.security.signature.SignatureUtils;

/**
 * @author Administrator
 *
 */
public class PolicyTestInterface {
	
	public static final String FORMAT_L6 = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMAT_L5 = "yyyy-MM-dd HH:mm";
	public static final String FORMAT_L4 = "yyyy-MM-dd HH";
	public static final String FORMAT_L3 = "yyyy-MM-dd";
	
	public static String DEV_PRI_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMvM1+ffxP4wcVIHyUSnrTFZNd7S"
			+"/jzV8yHnt6v1t/Lg5cHh3BsoDv4eiACxW7MGB1h5L8X6me7eB/LV2Oheo6Nd6aREvzN8DeQ6Qhy5"
			+"jKlwd6QeFerc0mNzkk1oslcnsTyVPTUeOiTVJUNCtL3KXNS4Osg+jP7on46PcKx3moSPAgMBAAEC"
			+"gYB6GvdZRCchLSEYRvX+HoCUSqEOKliNVprfu5e+ffCCouDF828PDkc9VkmiMKGCd6A7EBDhi65j"
			+"AcC9bAcT4nAjxbv+XjrflJ2N+XbNQmRp9aKRV2nzN28Gd2H/HS7HzbN7vq4ADZ7ERI///DlWPn5y"
			+"5UAHZvet3jfb4maW+AzhgQJBAOoex3W9fDCRKyEJ9xaB6AsY/n6DMfmo08w0u2zH5ThrXTTW8i3y"
			+"R+Tql1LZv9S4SdVvsRmGfteNz+yX3N/86KECQQDe2Kui1ahUQVaeinI4xiqmDdlqWfigmGVGAuRV"
			+"F5FKfL7UWKggNS2x6asJJ/JeZU5Ytua/2L1xdRcXxFfmjm8vAkEAxjJiVxmfnhn3MhQWmnZFM8fo"
			+"AsAcOaekDbcCiDe9pIK3uDjTcvQtyuySSLBQhsuwrcDNY7eJf//s6vUxJmoboQJAfl9m7f+15pnq"
			+"8htW7OVMGEDwU4RUH1Zs0AghzjOAPDqDyU3BggpGMIdva9DQfEF87YhDUPYrcGTTRLIemuuZFwJB"
			+"AJ4+CQJtLUIbGqjPRnk2AXlAzI1LMQPcUQbmWb4lT2c50ssF/twCinwA2gQzRTWIJo9/vxA4vAEc"
			+"KsW5HE9brQw=";
	
	
	private static final String mfqPrivateKey = "MIICdAIBADANBgkqhkiG9w0BAQEFAASCAl4wggJaAgEAAoGBAJ7koBDh4GCvpgxsPNz6Lbp6La3X"+
			"cdKwVs4NACQPo74a65WJeoHRJTbi6oaaLA1hslv5OYADMf/5GMrEnVDHiiQZfbSXHfdDLI8o7yH2"+
			"9DRLNDiBtoC71FCLLRUXhB0aGQBToKWnyPaUjnUcgDiH1UDGZHgLgCpwqFTYp0+w1Ry9AgMBAAEC"+
			"f2Fx1LiFzhV9o1f7CVh8FY9HXrw+ZI/RlWrIjk2PSdxMpi4PIhJ7MVd30ly6MEoEoF3HenwHeaKC"+
			"IbI44kGoHg8LzivC8Gt4cMps4w5pYCH0Jril2xwylcWzIMpffHb7Y7RKZo1YqD8LvkKLiVWvq8a+"+
			"zV+NeZn9XSIF6K0bFY0CQQDnvSgtqJBzcZQ/kHGxOxuj84VX6t9DlIpfRRE/5smN1ZlD/GqVdMqy"+
			"32hvF6abD1EniaKfz3WjnvKei+BHs/1jAkEAr4cf3q4tEs/AbfjOxDQh5mHXXQhbRJFaFt8QEd0u"+
			"6XGR+kXp9KMabe4kqnqzzchxYzXz0qnuvmsEp0mU49AnXwJBAMhhswVQFaANXTBdmUG0J6FsKCgu"+
			"iuHn5oKcuV61FCGVylKUSCF1/PQQ5D/zhnfcFcOHatyfSkyyW36m2seFppsCQAOzj8J8Xcr1wpMP"+
			"Gh0dFFHtYkM99968SfANiCM4TNLzaCak7sgP4HDNAszthXkjbvCupJOercAqNZDphlA/hXUCQHRB"+
			"zgqk9khEmgeHqrJ2EQ+RULH2PVP4l13z67lNEARbFUMgOEwjB06uf4HaduKy6uQCwOrBngGR1FhR"+
			"AOmw9p0=";
	
	
	private static final String mechantPrivateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJvQDw98p2ognGdn"+
			"LpYooZ37P20ZMCFs3CXZbd2si6rs1BriOUCHfeHNdrSfyg7cD0CTjAzZK753BZ+6"+
			"9AEWDDCLCySjAakCK/oEOlh6CElQ1A2mGDGc6iHWU37SH+s9M2cE6vQJmuLSw1tn"+
			"vFOJtxL0g748SmDMxvomqQe+iSx3AgMBAAECgYB1zdL1NJwnQ0y9aWfxo/q9xU5p"+
			"IBghzXqNK11DssdOjgy33r7GqSpl5MCRRBazMXJesq+6rx/Bu5+G6wS9JXxEsWvA"+
			"C/XmdJv9JAC69NNtO2oVSxhDAd2AGMtC2vquvl58nQjCir/13a46icgO9GrgJZnR"+
			"HT7BVfM4Z+Nr3Yi68QJBAMukQzqDwdtIR3Ds2R9Gohbi5b8cYJNRdyoHwz2j8JGP"+
			"V7lYASQd84GelwmvmHaaRB6gvSxgP8DcGHVol19HansCQQDD36+ylwp89gTt7yGw"+
			"LWfJqCjwmHwkE8ONqWyAFt9K/W9iXIw69cFg2Wrva+zDehyr52mjDp9KnYGcRtDQ"+
			"uxM1AkBY97LHZbjT7IgJtfBF0b4O8aIeFG+87OJ64llSHMWFUnKo7uHQvaUdBARI"+
			"nwalClCocOE0+ffgckfdQ4yee7zxAkAnYsE3jfuD6QxQs7b4cAjgmbmxt1evAXJe"+
			"9+RjVTYNJCzTgHyKFoSN7un7vp3bKKC/SEcLjJMnM8iuHec7MYLJAkAvlxa5PbmC"+
			"5rqmaYI5BAfi3PFXEy3XVvkyrDraTsk0W91DhxFsmSwOQ2zOHczfv7MjQh/sEGt6"+
			"SWSNaKw3uPCn";
	
	private static final String mypublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCj0SfLvqKDEYzLk+cN7K+8aVfH4dZ8RfQLgUhi"
+"suo5lsFDuOBhDNELKcmxMO5gDYd6Np6yZ91eww1V8zmYTkRqGpsCvj9L1vHqkYtKy6q9g7nlQpvl"
+"+c543ZFvp6a8XilR0O0xBDDQWhSJ8JCZpMXYuAgt/UGxAYTV/ncGS4VBtQIDAQAB";
	
	private static final String myprivateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAKPRJ8u+ooMRjMuT5w3sr7xpV8fh"+
			"1nxF9AuBSGKy6jmWwUO44GEM0QspybEw7mANh3o2nrJn3V7DDVXzOZhORGoamwK+P0vW8eqRi0rL"+
			"qr2DueVCm+X5znjdkW+nprxeKVHQ7TEEMNBaFInwkJmkxdi4CC39QbEBhNX+dwZLhUG1AgMBAAEC"+
			"gYAnSVC27xgAQOQ4Klc+IqYelFDl8cZDdW9Klw2qBehgOUROHndJLw6vE614Hhj81iHIcHLsrDYN"+
			"tM9s7nDAkvya5d+vIYUOH1XZwTEG6sRhU2+d7zVPLJDGSdx/IeeVqH9NveYpWm0+WK61EZO9b1di"+
			"xHEwgwekYFicCvP4qZM/AQJBAPPOWUD1HgkFeUllJA0h9yGYGKjCl2piGSCzf8/Z+lOocqrK0lwg"+
			"t3iGU6FbvCL46Yy0fBPIANA0/NUnor0IOvUCQQCsAqQHkpoEIX3KV+S3N1EQK9fLMqQO+et+3JXH"+
			"kLHRFqcyMGPC6YfaY0wMo+J9Z9k7Y0A3sDKpFBjjNLcbCjPBAkEAk2UsFpZaGzAg/FtWKU5gfwHZ"+
			"3qUOL3WxJVD/s2Rf75XGBTVraeEpqobjJok0O5xqbfNrk/X1b+qo9CDrRQclxQI/NRSbibqzzJKJ"+
			"PufgrKllWpgs/4ehGP4Grh9kGTGNrfhPoC1Om9d1FO+PiFcKjah+YNU/++9jssmA5Jxf5PhBAkA7"+
			"ybJ2cscnUgW/9KzhuMvALRk9NQ0I7604mTRgA5PBQ/Ix51vWp/lZPAKWbn8brM/rrzIlbQt86Rwf"+
			"kPRJzcxm";
	
	private static final String mechantPublicKey  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCb0A8PfKdqIJxnZy6WKKGd+z9tGTAhbNwl2W3drIuq7NQa4jlAh33hzXa0n8oO3A9Ak4wM2Su+dwWfuvQBFgwwiwskowGpAiv6BDpYeghJUNQNphgxnOoh1lN+0h/rPTNnBOr0CZri0sNbZ7xTibcS9IO+PEpgzMb6JqkHvoksdwIDAQAB";
	
	public static String getUUID(){    
        String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");    
        return uuid;    
    } 
	
	public static void main(String[] args) throws Exception{
		
		
		
		HttpPost request = new HttpPost("http://test.wkbins.com/openapiTest/insureInterface");
		
		@SuppressWarnings({ "deprecation", "resource" })
		HttpClient client = new DefaultHttpClient();
		
		Map<String,Object> mapTemp = getInfoMap();
		System.out.println("bizContent = "+mapTemp.get("bizContent").toString());
		Map<String,Object> map = null;
		try {
			map = SignatureUtils.encryptAndSign(mapTemp, mypublicKey, DEV_PRI_KEY.trim(), "UTF-8", true, true);
		}catch (InvalidKeyException e){
			e.printStackTrace();
			System.out.println("处错误啦。。。。");
		}catch (Exception e){
			e.printStackTrace();
			
			System.out.println("错误信息"+e.getMessage());
		}
		
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
        String  str=EntityUtils.toString(responseEntity);
		
        JSONObject responseJson = JSONObject.fromObject(str);
        //如果bizContent为空 就不用解密了
        boolean isCheckSign = responseJson.has("sign");
        String bizContent = "";
        if (isCheckSign) {
        	bizContent = SignatureUtils.checkSignAndDecrypt(responseJson, mypublicKey,DEV_PRI_KEY.trim(), isCheckSign, isCheckSign);
        } else {
        	bizContent = responseJson.toString();
        }
        
        System.out.println(bizContent);
	}
	
	private static Map<String,Object> getInfoMap () throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("appKey", "144981662321715493");//   144981662321715493
		map.put("serviceName", "meifenqi");
		map.put("charset", "UTF-8");
		map.put("signType", "RSA");
		map.put("notifyUrl", "");
		map.put("version", "1.0.0");
		
		JSONObject jSONObject = new JSONObject();
		//jSONObject.put("channelOrderNo", "DD1234562");
		jSONObject.put("channelOrderNo", "DP788072414");
	//	jSONObject.put("policyBackReason", "撤单撤单撤单");
		
//		jSONObject.put("policyNo", "1242352");
		jSONObject.put("productNo", "144982224212308786");     //144982224212308786   144947077732813751
		jSONObject.put("orderTime", sdf.format(new Date()));
		jSONObject.put("orderServiceStartTime","20160106151214");
		jSONObject.put("channelProductName", "双眼皮");
		jSONObject.put("orderSum", "500000");
		jSONObject.put("insuredld", "");
		jSONObject.put("orderPayType", "1");
		
		
		JSONObject orderUser = new JSONObject();
		orderUser.put("userKey", "xiumeijai234");
		orderUser.put("userName", "李四");
		orderUser.put("userEmail", "dddd@123.com");
		orderUser.put("userIDType", "1");
		orderUser.put("userIDInfo", "123123123123");
		
		
		JSONArray orderProviderInfo = new JSONArray();
		JSONObject orderProvider = new JSONObject();
		orderProvider.put("providerKey", "provider1213123");
		orderProvider.put("providerName", "提供商");
		orderProvider.put("providerType", "店铺");
		orderProviderInfo.add(orderProvider);
		
		JSONArray orderWaiterInfo = new JSONArray();
		JSONObject orderWaiter = new JSONObject();
		orderWaiter.put("waiterKey", "waiter112");
		orderWaiter.put("waiterName", "服务人员1");
		orderWaiter.put("waiterIDType", "省份证");
		orderWaiter.put("waiterIDInfo", "12313123");
		orderWaiter.put("waiterPhone", "15910823985");
		orderWaiterInfo.add(orderWaiter);
		
		
		jSONObject.put("userInfoContent", orderUser.toString());
		jSONObject.put("waiterInfoContent", orderWaiterInfo.toString());
		jSONObject.put("providerInfoContent", orderProviderInfo.toString());
		
		String bizContent = jSONObject.toString();
		//通过平台公钥进行加密
		map.put("bizContent", bizContent);
		map.put("timestamp", sdf.format(new Date()));
		map.put("format", "JSON");
		return map;
	}
}
