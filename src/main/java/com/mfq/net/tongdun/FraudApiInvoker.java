package com.mfq.net.tongdun;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.mfq.net.tongdun.pojo.FraudApiResponse;
import com.mfq.net.tongdun.pojo.TongdunPostData;

@Component
public class FraudApiInvoker {

    private final Logger logger  = LoggerFactory.getLogger(FraudApiInvoker.class);
    private static final String apiUrl = "https://api.fraudmetrix.cn/riskService";
    
    private HttpURLConnection conn;
    
    
    /**
     * 安卓登陆同盾安全检测
     * 
     * black_box 黑盒
     * account_login 登录账户名
     * ip_address 登陆IP地址
     * state 状态校验结果（密码校验结果：0表示密码正确，1表示密码错误）
     * type = android || ios
     * 	
     * @param data
     * @return 如果有问题，返回map，map中有msg(错误信息)和desicion(判断:0.null(参数不合法) 1.Reject 2.Review 3.Accept )；
     * 		   如果没有问题，返回null；
     */
    public Map<String,Object> fraudLogin(String ip_address,String account_login,Integer state,String black_box,String type){
    	try {
    		Map<String,Object> map = new HashMap<String,Object>();
    		//字段判空
    		if(state != 0 && state != 1){
    			logger.error("[FraudApiInvoker] at fraudLogin : state can not empty  ");
				map.put("msg","state只能为0（密码正确）或1（密码错误）");
    			return map;
    		}
    		if(StringUtils.isEmpty(black_box)){
    			logger.error("[FraudApiInvoker] at fraudLogin : black_box MUST can not  empty  ");
				map.put("msg","black_box 字段不能为空");
    			return map;
    		}
			if(StringUtils.isEmpty(account_login)){
				logger.error("[FraudApiInvoker] at fraudLogin : account_login can not empty  ");
				map.put("msg","account_login 字段不能为空");
				return map;
			}
			if(StringUtils.isEmpty(ip_address)){
				logger.error("[FraudApiInvoker] at fraudLogin : ip_address can not empty  ");
				map.put("msg","ip_address 字段不能为空");
				return map;
			}
			if(!type.trim().toLowerCase().equals("android") && !type.trim().toLowerCase().equals("ios")){
				logger.error("[FraudApiInvoker] at fraudLogin : type is not legal  ");
				map.put("msg","type 字段不合法");
				return map;
			}
			
			//打包参数
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("partner_code", TongdunPostData.partner_code);
			params.put("secret_key", type.trim().toLowerCase().equals("android")?TongdunPostData.secret_key_android:TongdunPostData.secret_key_ios);
			params.put("event_id", type.trim().toLowerCase().equals("android")?TongdunPostData.event_id_android_login:TongdunPostData.event_id_ios_login);
			System.out.println(params.get("event_id"));
	    	params.put("ip_address", ip_address);
	    	params.put("account_login", account_login);
	    	params.put("state", state);
	    	params.put("black_box", black_box);
			logger.error("[FraudApiInvoker] at fraudLogin : params= {}",params);
	    	return invoke(params);
	    	
		} catch (Exception e) {
			logger.error("[FraudApiInvoker] fraudLogin throw exception, details: " + e);
		}
    	return null;
    }
    
    /**
     * 
     * @param account_login 注册账户
     * @param account_mobile 注册手机
     * @param ip_address ip地址
     * @param black_box	黑盒	
     * @param type	设备类型 ios or android
     * @return
     */
    public Map<String,Object> fraudRegister(String account_login,String account_mobile,String ip_address,String blackbox,String type){
    	try {
			Map<String,Object> map = new HashMap<String, Object>();
			//字段判空
			if(!type.trim().toLowerCase().equals("android") && !type.trim().toLowerCase().equals("ios")){
				logger.error("[FraudApiInvoker] at fraudRegister : type is not legal  ");
				map.put("msg","type 字段不合法");
				return map;
			}
			if(StringUtils.isEmpty(blackbox)){
    			logger.error("[FraudApiInvoker] at fraudRegister : blackbox MUST can not be empty  ");
				map.put("msg","blackbox 字段不能为空");
    			return map;
    		}
			if(StringUtils.isEmpty(account_login)){
				logger.error("[FraudApiInvoker] at fraudRegister : account_login can not empty  ");
				map.put("msg","account_login 字段不能为空");
				return map;
			}
			if(StringUtils.isEmpty(account_mobile)){
				logger.error("[FraudApiInvoker] at fraudRegister : account_mobile can not empty  ");
				map.put("msg","account_mobile 字段不能为空");
				return map;
			}
			if(StringUtils.isEmpty(ip_address)){
				logger.error("[FraudApiInvoker] at fraudRegister : ip_address can not empty  ");
				map.put("msg","ip_address 字段不能为空");
				return map;
			}
			//打包参数
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("partner_code", TongdunPostData.partner_code);
			params.put("secret_key", type.trim().toLowerCase().equals("android")?TongdunPostData.secret_key_android:TongdunPostData.secret_key_ios);
			params.put("event_id", type.trim().toLowerCase().equals("android")?TongdunPostData.event_id_android_register:TongdunPostData.event_id_ios_register);
	    	params.put("ip_address", ip_address);
	    	params.put("account_login", account_login);
	    	params.put("black_box", blackbox);
			params.put("account_mobile", account_mobile);
			
			return invoke(params);
		} catch (Exception e) {
			logger.error("[FraudApiInvoker] fraudRegister throw exception, details: " + e);
		}
    	return null;
    }
    
    
    /**
     * 实名认证后交给同盾验证一下.
     * @param account_name
     * @param id_number
     * @param account_email
     * @param account_phone
     * @param ip_address
     * @param blackbox
     * @param type
     * @return
     */
    public Map<String,Object> fraudCertify(String account_name,String id_number,String account_email,String account_phone,String ip_address,String blackbox,String type){
    	try {
			Map<String,Object> map = new HashMap<String, Object>();
			//字段判空
			if(!type.trim().toLowerCase().equals("android") && !type.trim().toLowerCase().equals("ios")){
				logger.error("[FraudApiInvoker] at fraudCertify : type is not legal  ");
				map.put("msg","type 字段不合法");
				return map;
			}
			if(StringUtils.isEmpty(blackbox)){
    			logger.error("[FraudApiInvoker] at fraudCertify : blackbox MUST can not be empty  ");
				map.put("msg","blackbox 字段不能为空");
    			return map;
    		}
			if(StringUtils.isEmpty(account_name)){
				logger.error("[FraudApiInvoker] at fraudCertify : account_name can not empty  ");
				map.put("msg","account_name 字段不能为空");
				return map;
			}
			
			if(StringUtils.isEmpty(id_number)){
				logger.error("[FraudApiInvoker] at fraudCertify : id_number can not empty  ");
				map.put("msg","id_number 字段不能为空");
				return map;
			}
			if(StringUtils.isEmpty(account_email)){
				account_email = "";
			}
			if(StringUtils.isEmpty(account_phone)){
				logger.error("[FraudApiInvoker] at fraudCertify : account_phone can not empty  ");
				map.put("msg","account_phone 字段不能为空");
				return map;
			}
			if(StringUtils.isEmpty(ip_address)){
				logger.error("[FraudApiInvoker] at fraudCertify : ip_address can not empty  ");
				map.put("msg","ip_address 字段不能为空");
				return map;
			}
			
			//打包参数
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("partner_code", TongdunPostData.partner_code);
			params.put("secret_key", type.trim().toLowerCase().equals("android")?TongdunPostData.secret_key_android:TongdunPostData.secret_key_ios);
			params.put("event_id", type.trim().toLowerCase().equals("android")?TongdunPostData.event_id_android_loan:TongdunPostData.event_id_ios_loan);
	    	params.put("account_name", account_name);
	    	params.put("id_number", id_number);
	    	params.put("account_phone", account_phone);
	    	params.put("account_email", account_email);
	    	params.put("ip_address", ip_address);
	    	params.put("black_box", blackbox);
			
			return invoke(params);
		} catch (Exception e) {
			logger.error("[FraudApiInvoker] fraudRegister throw exception, details: " + e);
		}
    	return null;
    }
    
    
    /**
     * 传入参数列表Map
     * @param params{desicion:"判断结果",msg:"规则名"} 
     * 判断结果：Reject（拒绝）、Review（人工审核）、Accept（接受）
     * @return 如果没有命中规则，则返回null；如果命中规则，则返回最终判断结果(desicion)和命中规则名字(msg)
     */
    public Map<String,Object> invoke(Map<String, Object> params) {
        try {
            URL url = new URL(apiUrl);
            // 组织请求参数
            StringBuilder postBody = new StringBuilder();
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                if (entry.getValue() == null) continue;
                postBody.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue().toString(),
                                                                                     "utf-8")).append("&");
            }
            
            if (!params.isEmpty()) {
                postBody.deleteCharAt(postBody.length() - 1);
            }

            conn = (HttpURLConnection) url.openConnection();
            // 设置长链接
            conn.setRequestProperty("Connection", "Keep-Alive");
            // 设置连接超时
            conn.setConnectTimeout(1000);
            // 设置读取超时
            conn.setReadTimeout(500);
            // 提交参数
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.getOutputStream().write(postBody.toString().getBytes());
            conn.getOutputStream().flush();
            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                logger.warn("[FraudApiInvoker] invoke failed, response status:" + responseCode);
                return null;
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                result.append(line);
            }
            System.out.println(result);
            FraudApiResponse apiResp = JSON.parseObject(result.toString().trim(), FraudApiResponse.class);
            if(apiResp.getFinal_decision()!=null && apiResp.getHit_rules().get(0).getName()!=null){
            	Map<String,Object> resultMap = new HashMap<String,Object>();
            	resultMap.put("decision", apiResp.getFinal_decision());
            	logger.debug("apiResp.getHit_rules().get(0).getName() = "+apiResp.getHit_rules().get(0).getName());
            	resultMap.put("msg", apiResp.getHit_rules().get(0).getName());
            	return resultMap;
            }
        } catch (Exception e) {
            logger.error("[FraudApiInvoker] invoke throw exception, details: " + e);
        }
        return null;
    }

    /**
     * 命中地理位置策略并拒绝
     * FraudApiResponse [success=true, reason_code=null, final_score=0, final_decision=Reject, policy_name=美分期安卓登陆策略集, seq_id=1449738747732-20878047, spend_time=19, policy_set_name=美分期安卓登陆策略集, risk_type=suspiciousLogin_reject]
		
		
		
		{"final_decision":"Accept","final_score":0,"policy_name":"美分期iOS登陆策略集","policy_set":[{"policy_decision":"Accept","policy_mode":"FirstMatch","policy_name":"暴力破解","policy_score":0,"policy_uuid":"1f3f8bd58b7d4cc9b5a7c3bd4de6ebcd","risk_type":"bruteForce"},{"policy_decision":"Accept","policy_mode":"FirstMatch","policy_name":"账户盗用","policy_score":0,"policy_uuid":"ce9656df6f7a41a68099309f287b0fba","risk_type":"accountTakeover"}],"policy_set_name":"美分期iOS登陆策略集","risk_type":"","seq_id":"1450065729238-04908415","spend_time":17,"success":true}

     * @param args
     */
    public static void main(String[] args) {
        /* * 必需字段：
        * black_box 黑盒
        * account_login 登录账户名
        * ip_address 登陆IP地址
        * state 状态校验结果（密码校验结果：0表示密码正确，1表示密码错误）
        */
        String black_box = "black_box_value";
        Integer state = 1;
        String account_login = "18338751231";
        String ip_address = "119.253.47.110";
        	//String ip_address,String account_login,Integer state,String black_box,String type
        	
        
        	Map<String,Object> result = new FraudApiInvoker().fraudLogin(ip_address,account_login,state,black_box,"ios");  
        	if(result!=null){
        		System.out.println(result.get("msg"));
        	}else{
        		System.out.println(result);
        		
        	}
    }
}