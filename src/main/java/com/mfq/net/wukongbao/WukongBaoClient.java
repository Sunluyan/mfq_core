package com.mfq.net.wukongbao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Maps;
import com.mfq.bean.Hospital;
import com.mfq.bean.OrderInfo;
import com.mfq.bean.PolicyInsure;
import com.mfq.bean.Product;
import com.mfq.bean.user.User;
import com.mfq.bean.user.UserQuota;
import com.mfq.net.util.WukongUtil;
import com.mfq.net.wukongbao.pojo.InsureOrder;
import com.mfq.net.wukongbao.pojo.InsureUser;
import com.mfq.net.wukongbao.pojo.PolicyBack.PolicyBackRequest;
import com.mfq.net.wukongbao.pojo.TimeSync.TimeSyncRequest;
import com.mfq.net.wukongbao.pojo.WkbConstants;
import com.mfq.service.HospitalService;
import com.mfq.service.OrderService;
import com.mfq.service.PolicyService;
import com.mfq.service.ProductService;
import com.mfq.service.user.UserQuotaService;
import com.mfq.service.user.UserService;
import com.mfq.utils.DateUtil;
import com.mfq.utils.JsonUtil;
import com.mfq.utils.RunnerUtils;

import net.sf.json.JSONObject;

@Component
public class WukongBaoClient {
	
	private static Logger logger = LoggerFactory
            .getLogger(WukongBaoClient.class);
	
	@Resource
	UserQuotaService userQuotaService;
	@Resource
	UserService userService;
	
	
	java.text.DecimalFormat bgformat=new java.text.DecimalFormat("000");
	
	/**
	 * 悟空保接口
	 * @param request
	 * @return
	 */
	public String WukongRequest(Map<String, Object> request, PolicyInsure insure){
		try{
			insure.setRequestStr(JsonUtil.writeToJson(request));
			insure.setRequestTime(new Date());
			
			logger.info("请求参数:"+JsonUtil.writeToJson(request));
			System.out.println("qingi:"+JsonUtil.writeToJson(request));
			Map<String, Object> map = WukongUtil.encryptAndSign(request);
			String response = WukongUtil.postRequest(map);
			
			String ret = WukongUtil.checkResponse(response);
			insure.setReponseStr(ret);
			insure.setReponseTime(new Date());			
			logger.info("返回值：{}", ret);
			return ret;
		}catch(Exception e){
			logger.info("insure is error {}", e);
		}
		return null;
	}

	
	/**
	 * 查询保单 请求
	 * @return
	 */
	public Map<String, Object> getQueryPolicyRequest(String policyOrderNo){
		return getWuKongBaoRequest(JSONObject.fromObject(policyOrderNo));
	}
	
	/**
	 * 退保 请求
	 * @param request
	 * @return
	 */
	public Map<String, Object> getPolicyBackRequest(PolicyBackRequest request){
		
		return getWuKongBaoRequest(JSONObject.fromObject(request))	;
	}
	
	/**
	 * 时间同步 请求
	 * @param request
	 * @return
	 */
	public Map<String, Object> getSyncTimeRequest(TimeSyncRequest request){
		return getWuKongBaoRequest(JSONObject.fromObject(request));
	}
	
	
	public Map<String, Object> getInsureRequest(OrderInfo order, Hospital hospital, String productName,long uid, long waiterId){
		InsureOrder orderInsure = getInsureOrder(order, hospital, productName, uid, waiterId);
		return getWuKongBaoRequest(JSONObject.fromObject(orderInsure));
	}
	
	/**
	 * 保单 请求  (封装保单数据)
	 * @param order
	 * @param productName
	 * @param waiterId
	 * @return
	 */
	public InsureOrder getInsureOrder(OrderInfo order, Hospital hospital, String productName,long uid, long waiterId){
		InsureOrder insure = new InsureOrder();
		insure.setProductNo(WkbConstants.INSURE_PRODUCT);
		insure.setChannelOrderNo(order.getOrderNo());
		insure.setOrderTime(DateUtil.formatYYYYMMDDHHMMSS(order.getCreatedAt()));
		insure.setOrderServiceStartTime(DateUtil.formatYYYYMMDDHHMMSS(order.getServiceStartTime()));
		insure.setChannelProductName(productName);
		insure.setOrderSum(bgformat.format(order.getPrice().multiply(BigDecimal.valueOf(100))));   //已分为单位
		
		insure.setOrderPayType("2");
		insure.setInsuredld(""); //投保标的
			
		JSONObject waiter = new JSONObject();
		waiter.put("waiterKey", "1");
		waiter.put("waiterName", "陪护");
		waiter.put("waiterIDType", "身份证");
		waiter.put("waiterIDInfo", "45623652365985652");
		waiter.put("waiterPhone", "15698656356");
			
		JSONArray jaInsure = new JSONArray();
		jaInsure.add(waiter);
			
		insure.setWaiterInfoContent(jaInsure.toJSONString());

			
		JSONObject hospitalInfo = new JSONObject();
		hospitalInfo.put("providerKey", hospital.getId());
		hospitalInfo.put("providerName", hospital.getName());
		hospitalInfo.put("providerType", WkbConstants.HOSPITAL_LEVEL);
		hospitalInfo.put("providerAddress", hospital.getAddress());
		
		JSONArray jaHospital = new JSONArray();
		jaHospital.add(hospitalInfo);
		insure.setProviderInfoContent(jaHospital.toString());
		
		//添加用户信息
		Map<String, Object> user = getInsureUser(uid);
		
		//校验user
		if("".equals(user.get("userName").toString())){
			return null;
		}
		
		
		JSONArray ja = new JSONArray();
		ja.add(JSONObject.fromObject(user));
		
		insure.setUserInfoContent(ja.toJSONString());
		
		//校验数据
		
		return insure;
	}
	
	public Map<String, Object> getInsureUser(long uid){
		
		User user = userService.queryUser(uid);
		if(user == null || user.getUid() <1)
			return null;
		
		UserQuota quota = userQuotaService.queryUserQuota(uid);
		
		Map<String, Object> map = Maps.newHashMap();
		map.put("userKey", String.valueOf(user.getUid()));
		map.put("userName", quota.getRealname());
		map.put("userPhone", user.getMobile());
		map.put("userAddress", user.getLocation());
		map.put("userEmail", "326191813@123.com");
		map.put("userIDInfo", "2343545234234");
		map.put("userIDType", "1");

		
		return map;
	}
	

	/**
	 * 保单 请求
	 * @param order
	 * @param productName
	 * @param waiterId
	 * @return
	 */
	public Map<String, Object> getInsureRequest2(OrderInfo order, PolicyInsure pinsure, long waiterId){
		InsureOrder insure = new InsureOrder();
		insure.setProductNo(WkbConstants.INSURE_PRODUCT);
		insure.setChannelOrderNo(order.getOrderNo());
		insure.setOrderTime(DateUtil.formatYYYYMMDDHHMMSS(order.getCreatedAt()));
		insure.setOrderServiceStartTime(DateUtil.formatYYYYMMDDHHMMSS(order.getServiceStartTime()));
		insure.setChannelProductName(pinsure.getProductName());
		insure.setOrderSum(String.valueOf(order.getOnlinePay().multiply(BigDecimal.valueOf(100))));
		if(order.getPayType() == 0){
			insure.setOrderPayType("1");
		}else if(order.getPayType() == 2){
			insure.setOrderPayType("2");
		}
		insure.setInsuredld(""); //投保标的

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
		
		//添加用户信息
		
		insure.setUserInfoContent("");
		
		return getWuKongBaoRequest(JSONObject.fromObject(insure));
	}
	


	/**
	 * 悟空保接口 (异步)
	 * @param request
	 * @return
	 */
	public  String WukongSysncRequest(Map<String, Object> request){
		try{
			Map<String, Object> map = WukongUtil.encryptAndSign(request);
			RequestCallable r = new RequestCallable(map);
			Future<Object> f = RunnerUtils.submit(r);
			//while(f.get()==null)
				//Thread.sleep(1000);
			return WukongUtil.checkResponse(f.get().toString());
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
				
				String response = WukongUtil.postRequest(request);
				System.out.println(response);
				return response;
			}catch (Exception e){
				e.printStackTrace();
			}
			return null;
		}
	}
	
	private static Map<String,Object> getWuKongBaoRequest(JSONObject order){
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("appKey", WkbConstants.APP_KEY);
		map.put("serviceName", WkbConstants.SERVICE_NAME);
		map.put("charset", WkbConstants.CHARSET);
		map.put("signType", WkbConstants.SIGN_TYPE);
		map.put("notifyUrl", WkbConstants.NOTIFY_URL);
		map.put("bizContent", order.toString());
		map.put("format", WkbConstants.FORMAT);
		map.put("timestamp", DateUtil.formatCurTimeLong());
		map.put("version", WkbConstants.VERSION);

		return map;
		
	}
	
	
	public static void main(String[] args) throws Exception {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/spring.xml"); 
		WukongBaoClient client = (WukongBaoClient)context.getBean("wukongBaoClient");
		OrderService orderService = (OrderService)context.getBean("orderService");
		
		PolicyService client2 = (PolicyService)context.getBean("policyService");
//		client2.insure("mn2015122020315966400004");
		
		OrderInfo info = new OrderInfo();
		info.setOrderNo("mn2015122020315966400004");
		info.setServiceStartTime(new Date(1450674000000l));  //1450674000000
		info.setPayType(0);  // 2
		info.setCreatedAt(new Date());
		info.setOnlinePay(BigDecimal.valueOf(50500));
		info.setPrice(BigDecimal.valueOf(1500));
		
//		info = orderService.findByOrderNo("mn2015122020315966400004");
		
		
		HospitalService hospitalService = (HospitalService)context.getBean("hospitalService");
		ProductService productService = (ProductService)context.getBean("productService");
		Product product = productService.findById(info.getPid());
		Hospital hospital = hospitalService.findById(1);
		
//		Hospital hospital = hos.findById(1);
		
//		InsureOrder order = client.getInsureOrder(info, hospital,"双眼皮手术",2798,  0l);
		
		Map<String, Object> map = client.getInsureRequest(info, hospital, "双眼皮手术", 2798, 0l);
		
		System.out.println("业务数据："+JSONObject.fromObject(map).toString());
		
//		Map<String, Object> map = getWuKongBaoRequest(JSONObject.fromObject(order));
				
		String re= client.WukongRequest(map, new PolicyInsure());
		System.out.println(re);
			
	}

	

}
