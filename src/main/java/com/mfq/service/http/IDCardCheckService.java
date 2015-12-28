package com.mfq.service.http;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.mfq.cache.RedisCache;
import com.mfq.constants.Constants;
import com.mfq.constants.Grade;
import com.mfq.service.user.UserQuotaService;
import com.mfq.utils.HttpUtil;

@Component("checkIDJob") 
public class IDCardCheckService extends Thread{

//	private static Logger logger = LoggerFactory.getLogger(IDCardCheckService.class);
//	
//	private static RedisCache cache = new RedisCache();
//	
//	private static IDCardCheckService single = new IDCardCheckService();
//	
//	@Resource
//	UserQuotaService userQuotaService;
//	
//	public IDCardCheckService(){}
//	
//	//静态工厂方法   
//    public static IDCardCheckService getInstance() {  
//        return single;  
//    } 
//    
//	public JSONObject CheckIDCard(String name, String idcard) {
//		String url = Constants.IDCARD_CHECK.replace("APPKEY", Constants.IDCARD_APPKEY).replace("NAME", name)
//				.replace("IDCARD", idcard);
//		String result = HttpUtil.get(url, false);
//		return JSONObject.parseObject(result);
//	}
//	
//	public JSONObject getIDInfo(){
//		String info = cache.brpop("tt");
//		if(info == null){
//			return null;
//		}
//		return JSONObject.parseObject(info);
//	}
//	
//	public long updateUserInfo(JSONObject info) throws Exception{
//		
//		long uid = info.getLong("uid");
//		String realname = info.getString("realname");
//		String idcard = info.getString("idcard");
//		String school = info.getString("school");
//		String contact = info.getString("idcard");
//		Grade grade = Grade.fromId(info.getInteger("grade"));
//		String classes = info.getString("classes");
//		String remark = info.getString("remark");
//		
//		userQuotaService.updateUserInfo(uid, realname, idcard, 
//				school, contact, grade, classes, remark);
//		return 1;
//	}
//	
//	public JSONObject doCheck(){
//		try{
//			
//			for(;;){
//				JSONObject data = getIDInfo();
//				if(data == null){
//					logger.info("this thread is null ....");
//					Thread.sleep(5000);
//				}
//				JSONObject info = CheckIDCard(data.getString("realname"), data.getString("idcard"));
//				if(info.getInteger("code") != 1){
//					continue;
//				}
//				long r = updateUserInfo(info);
//				if(r > 0){
//					System.out.println("success");
//				}else{
//					System.out.println("faild");
//				}
//			}
//			
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		
//		return null;
//		
//	}
//	
//	@Override
//	public void run() {
//		System.out.println("idcard check is start...");
//    	
//        Thread current = Thread.currentThread();  
//        System.out.println(current.getPriority());  
//        System.out.println(current.getName());  
//        System.out.println(current.getId());  
//        System.out.println(current.getThreadGroup());  
//        System.out.println(current.getStackTrace());  
//        System.out.println(current.hashCode());  
//        System.out.println(current.toString());  
//       
//    	doCheck();
//	}
	
}
