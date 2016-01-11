package com.mfq.net.wukongbao;

import java.math.BigDecimal;
import java.security.InvalidKeyException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import javax.annotation.Resource;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.mfq.bean.OrderInfo;
import com.mfq.net.wukongbao.pojo.InsureOrder;
import com.mfq.net.wukongbao.pojo.InsureUser;
import com.mfq.net.wukongbao.pojo.PolicyBack.PolicyBackRequest;
import com.mfq.net.wukongbao.pojo.TimeSync.TimeSyncRequest;
import com.mfq.net.wukongbao.pojo.WkbConstants;
import com.mfq.net.wukongbao.pojo.WukongRequest;
import com.mfq.net.wukongbao.pojo.WukongResponse;
import com.mfq.service.user.UserQuotaService;
import com.mfq.utils.BeanUtil;
import com.mfq.utils.DateUtil;
import com.mfq.utils.HttpUtil;
import com.mfq.utils.JsonUtil;
import com.mfq.utils.RunnerUtils;
import com.wkb.openapi.security.signature.SignatureUtils;

import net.sf.json.JSONObject;

@Component
public class WukongBaoClient2 {
	
	private static Logger logger = LoggerFactory
            .getLogger(WukongBaoClient2.class);
	
	@Resource
	UserQuotaService userQuotaService;
	
	/**
	 * 悟空保接口
	 * @param request
	 * @return
	 */
	public String WukongRequest(Map<String, Object> request){
		try{
//			Map<String, Object> map= BeanUtil.objectToMap(request);
//			String sign = WukongUtil.makeWukongSign(map);
//			request.setSign(sign);
			String data = JsonUtil.writeToJson(request);
			String response = HttpUtil.post(WkbConstants.INSURE_URL, data, false);
			
			return response;
		}catch(Exception e){
			logger.info("insure is error {}", e);
		}
		return null;
	}

	public WukongRequest getWuKongBaoRequest(Object content){
		try{
			WukongRequest request = new WukongRequest();
			request.setAppKey(WkbConstants.APP_KEY);
			request.setServiceName(WkbConstants.SERVICE_NAME);
			request.setCharset(WkbConstants.CHARSET);
			request.setSignType(WkbConstants.SIGN_TYPE);
			request.setNotifyUrl(WkbConstants.NOTIFY_URL);
			
//			String bizStr = JsonUtil.writeToJson(content);
//			System.out.println("业务数据 is ："+bizStr);
//			String biz = WukongUtil.signBizContent(bizStr, bizStr);
//			System.out.println("业务加密："+ biz);
			
			request.setBizContent(JsonUtil.writeToJson(content));
			request.setTimestamp(DateUtil.formatCurTimeLong());
			request.setFormat(WkbConstants.FORMAT);
			request.setVersion(WkbConstants.VERSION);
		
//			request.setSign("");
			
			return request;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 查询保单 请求
	 * @return
	 */
	public WukongRequest getQueryPolicyRequest(String policyOrderNo){
		return getWuKongBaoRequest(policyOrderNo);
	}
	
	/**
	 * 退保 请求
	 * @param request
	 * @return
	 */
	public WukongRequest getPolicyBackRequest(PolicyBackRequest request){
		
		return getWuKongBaoRequest(request);
	}
	
	/**
	 * 时间同步 请求
	 * @param request
	 * @return
	 */
	public WukongRequest getSyncTimeRequest(TimeSyncRequest request){
		return getWuKongBaoRequest(request);
	}
	
	
	/**
	 * 保单 请求
	 * @param order
	 * @param productName
	 * @param waiterId
	 * @return
	 */
	public InsureOrder getInsureRequest2(OrderInfo order, String productName, long waiterId){
		InsureOrder insure = new InsureOrder();
		insure.setProductNo(WkbConstants.INSURE_PRODUCT);
		insure.setChannelOrderNo(order.getOrderNo());
		insure.setOrderTime(DateUtil.formatYYYYMMDDHHMMSS(order.getCreatedAt()));
		insure.setOrderServiceStartTime(DateUtil.formatYYYYMMDDHHMMSS(order.getServiceStartTime()));
		insure.setChannelProductName(productName);
		insure.setOrderSum(String.valueOf(order.getOnlinePay().multiply(BigDecimal.valueOf(100))));
		if(order.getPayType() == 0){
			insure.setOrderPayType("全款");
		}else if(order.getPayType() == 2){
			insure.setOrderPayType("分期");
		}
//		insure.setInsuredld(""); //投保标的

		if(waiterId != 0){
			InsureUser user = new InsureUser();
			user.setUserKey("");
			user.setUserName("");
			user.setUserIDType("");
			user.setUserIDInfo("");
			user.setUserPhone("");
			insure.setWaiterInfoContent(JsonUtil.writeToJson(user));

		}else{
			insure.setWaiterInfoContent("");
		}
//		insure.setProviderInfoContent("");
		
//		UserQuota quota = userQuotaService.queryUserQuota(order.getUid());
		//添加用户信息
		
		insure.setUserInfoContent("");
		
		return insure;
	}

	/**
	 * 保单 请求
	 * @param order
	 * @param productName
	 * @param waiterId
	 * @return
	 */
	public WukongRequest getInsureRequest(OrderInfo order, String productName, long waiterId){
		InsureOrder insure = new InsureOrder();
		insure.setProductNo(WkbConstants.INSURE_PRODUCT);
		insure.setChannelOrderNo(order.getOrderNo());
		insure.setOrderTime(DateUtil.formatYYYYMMDDHHMMSS(order.getCreatedAt()));
		insure.setOrderServiceStartTime(DateUtil.formatYYYYMMDDHHMMSS(order.getServiceStartTime()));
		insure.setChannelProductName(productName);
		insure.setOrderSum(String.valueOf(order.getOnlinePay().multiply(BigDecimal.valueOf(100))));
		if(order.getPayType() == 0){
			insure.setOrderPayType("全款");
		}else if(order.getPayType() == 2){
			insure.setOrderPayType("分期");
		}
//		insure.setInsuredld(""); //投保标的

		if(waiterId != 0){
			InsureUser user = new InsureUser();
			user.setUserKey("");
			user.setUserName("");
			user.setUserIDType("");
			user.setUserIDInfo("");
			user.setUserPhone("");
			insure.setWaiterInfoContent(JsonUtil.writeToJson(user));

		}else{
			insure.setWaiterInfoContent("");
		}
//		insure.setProviderInfoContent("");
		
//		UserQuota quota = userQuotaService.queryUserQuota(order.getUid());
		//添加用户信息
		
		insure.setUserInfoContent("");
		
		return getWuKongBaoRequest(insure);
	}
	


	/**
	 * 悟空保接口 (异步)
	 * @param request
	 * @return
	 */
	public WukongResponse WukongSysncRequest(Map<String, Object> request){
		try{
			RequestCallable r = new RequestCallable(request);
			Future f = RunnerUtils.submit(r);
			while(f.get()==null)
				Thread.sleep(1000);
			return JsonUtil.toBean(f.get().toString(), WukongResponse.class);
		}catch(Exception e){
			logger.info("insure is error {}", e);
		}
		return null;
	}

	class RequestCallable implements Callable<Object> {

		private Map<String, Object> request;
		RequestCallable(Map<String, Object> request) {
			this.request = request;
		}
		@Override
		public Object call() throws Exception {
			try {
				Map<String, Object> map = BeanUtil.objectToMap(request);
				String data = JsonUtil.writeToJson(map);
				String response = HttpUtil.post(WkbConstants.INSURE_URL, data, false);
				System.out.println(response);
				return response;
			}catch (Exception e){
				e.printStackTrace();
			}
			return null;
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		WukongBaoClient2 clientl = new WukongBaoClient2();
		OrderInfo info = new OrderInfo();
		info.setOrderNo("546325632562565");
		info.setServiceStartTime(new Date());
		info.setPayType(0);
		info.setCreatedAt(new Date());
		info.setOnlinePay(BigDecimal.valueOf(5000));
		InsureOrder requestr = clientl.getInsureRequest2(info, "双眼皮", 0l);
		
//		try{
//			
//		Map<String, Object> sortMap = BeanUtil.objectToMap(request);
//		
//		
//		System.out.println("sortMap:"+JsonUtil.writeToJson(sortMap));
////		Map<String,Object> sortMap = new HashMap<String,Object>();
//		
//		String biz =sortMap.get("bizContent").toString();
//		
//		System.out.println("bizContent:"+biz);
//		sortMap = getInfo();
//		Map<String,Object> encryptAndSign = SignatureUtils.encryptAndSign(sortMap, WkbConstants.WKB_PUK_KEY.trim(), WkbConstants.DEV_PRI_KEY.trim(), "UTF-8", true, true);
////		String bizContent = SignatureUtils.checkSignAndDecrypt(encryptAndSign, WkbConstants.DEV_PUB_KEY, WkbConstants.DEV_PRI_KEY, true, true);
////		sortMap.put("bizContent", SignatureUtils.rsaEncrypt(biz, WkbConstants.WKB_PUK_KEY, "UTF-8"));
////		sortMap.setSign(encryptAndSign.get("sign").toString());
////		sortMap.setBizContent(encryptAndSign.get("bizContent").toString());
//		
//		System.out.println(SignatureUtils.rsaEncrypt(biz, WkbConstants.WKB_PUK_KEY, "UTF-8"));
//		
//		
////		sortMap.put("sign", encryptAndSign.get("sign").toString());
////		sortMap.put("bizContent", encryptAndSign.get("bizContent").toString());
//		
//		System.out.println("请求 json:"+JsonUtil.writeToJson(sortMap));
//		String response = client.WukongRequest(sortMap);
//		
//		System.out.println("返回结果:"+response);
//		
//		}catch(Exception e){
//			e.printStackTrace();
//		}
		
		String url = "http://test.wkbins.com/openapiTest/insureInterface";
		
//		Map<String, Object> sortMap = BeanUtil.objectToMap(requestr);
//		
//		String response = clientl.WukongRequest(sortMap);
		
//		System.out.println(response);
		HttpPost request = new HttpPost(url);
		
		@SuppressWarnings({ "deprecation", "resource" })
		HttpClient client = new DefaultHttpClient();
		
		Map<String,Object> mapTemp = getInfo(requestr);
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
		System.out.println(str);
        JSONObject responseJson = JSONObject.fromObject(str);
        //如果bizContent为空 就不用解密了
        boolean isCheckSign = responseJson.has("sign");
        String bizContent = "";
        if (true) {
        	bizContent = SignatureUtils.checkSignAndDecrypt(responseJson, WkbConstants.WKB_PUK_KEY.trim(),WkbConstants.DEV_PRI_KEY.trim(), isCheckSign, isCheckSign);
        } else {
        	bizContent = responseJson.toString();
        }
        
        System.out.println(bizContent);
		
	}
	
	private static Map<String,Object> getInfo(InsureOrder order){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("appKey", WkbConstants.APP_KEY);
		map.put("serviceName", WkbConstants.SERVICE_NAME);
		map.put("charset", WkbConstants.SERVICE_NAME);
		map.put("signType", WkbConstants.SERVICE_NAME);
		map.put("notifyUrl", WkbConstants.NOTIFY_URL);
		map.put("bizContent", order.toString());
		map.put("format", WkbConstants.FORMAT);
		map.put("timestamp", DateUtil.formatCurTimeLong());
		map.put("version", WkbConstants.VERSION);
		map.put("sign", "");
		
	
		
		
//		JSONObject jSONObject = new JSONObject();
//		jSONObject.put("name", "张三");
//		jSONObject.put("age", 30);
//		JSONObject dog = new JSONObject();
//		dog.put("name", "小黑");
//		dog.put("age", 3);
//		jSONObject.put("dogs", dog);
//		String bizContent = jSONObject.toString();
//		//通过平台公钥进行加密
//		map.put("bizContent", bizContent);
//		map.put("timestamp", sdf.format(new Date()));
//		map.put("format", "JSON");
		
		
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//		Map<String,Object> map = new HashMap<String, Object>();
//		map.put("appKey", "144981662321715493");
//		map.put("serviceName", "meifenqi");
//		map.put("charset", "UTF-8");
//		map.put("signType", "RSA");
//		map.put("notifyUrl", "");
//		map.put("version", "1.0.0");
//		
//		JSONObject jSONObject = new JSONObject();
//		//jSONObject.put("channelOrderNo", "DD1234562");
//		jSONObject.put("channelOrderNo", "546325632562345");
//	//	jSONObject.put("policyBackReason", "撤单撤单撤单");
//		
////		jSONObject.put("policyNo", "1242352");
//		jSONObject.put("productNo", "144982224212308786");
//		jSONObject.put("orderTime", sdf.format(new Date()));
//		jSONObject.put("orderServiceStartTime","2016-03-06 00:00:00");
//		jSONObject.put("channelProductname", "双眼皮");
//		jSONObject.put("orderSum", "500000");
//		jSONObject.put("payType", "1");//这个不一样
//		jSONObject.put("insuredld", "");
//		
//		
//		jSONObject.put("userInfoContent", "");
//		jSONObject.put("waiterInfoContent", "");
//		jSONObject.put("providerInfoContent", "");
//		
//		String bizContent = jSONObject.toString();
//		//通过平台公钥进行加密
//		map.put("bizContent", bizContent);
//		map.put("timestamp", sdf.format(new Date()));
//		map.put("format", "JSON");
		return map;
		
	}
	
	
	
	
	
//	公钥
//	MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDLzNfn38T+MHFSB8lEp60xWTXe0v481fMh57er
//	9bfy4OXB4dwbKA7+HogAsVuzBgdYeS/F+pnu3gfy1djoXqOjXemkRL8zfA3kOkIcuYypcHekHhXq
//	3NJjc5JNaLJXJ7E8lT01Hjok1SVDQrS9ylzUuDrIPoz+6J+Oj3Csd5qEjwIDAQAB
//	
//	私钥
//	MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMvM1+ffxP4wcVIHyUSnrTFZNd7S
//	/jzV8yHnt6v1t/Lg5cHh3BsoDv4eiACxW7MGB1h5L8X6me7eB/LV2Oheo6Nd6aREvzN8DeQ6Qhy5
//	jKlwd6QeFerc0mNzkk1oslcnsTyVPTUeOiTVJUNCtL3KXNS4Osg+jP7on46PcKx3moSPAgMBAAEC
//	gYB6GvdZRCchLSEYRvX+HoCUSqEOKliNVprfu5e+ffCCouDF828PDkc9VkmiMKGCd6A7EBDhi65j
//	AcC9bAcT4nAjxbv+XjrflJ2N+XbNQmRp9aKRV2nzN28Gd2H/HS7HzbN7vq4ADZ7ERI///DlWPn5y
//	5UAHZvet3jfb4maW+AzhgQJBAOoex3W9fDCRKyEJ9xaB6AsY/n6DMfmo08w0u2zH5ThrXTTW8i3y
//	R+Tql1LZv9S4SdVvsRmGfteNz+yX3N/86KECQQDe2Kui1ahUQVaeinI4xiqmDdlqWfigmGVGAuRV
//	F5FKfL7UWKggNS2x6asJJ/JeZU5Ytua/2L1xdRcXxFfmjm8vAkEAxjJiVxmfnhn3MhQWmnZFM8fo
//	AsAcOaekDbcCiDe9pIK3uDjTcvQtyuySSLBQhsuwrcDNY7eJf//s6vUxJmoboQJAfl9m7f+15pnq
//	8htW7OVMGEDwU4RUH1Zs0AghzjOAPDqDyU3BggpGMIdva9DQfEF87YhDUPYrcGTTRLIemuuZFwJB
//	AJ4+CQJtLUIbGqjPRnk2AXlAzI1LMQPcUQbmWb4lT2c50ssF/twCinwA2gQzRTWIJo9/vxA4vAEc
//	KsW5HE9brQw=

}
