package com.mfq.service.wechat;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


import org.apache.commons.lang.StringUtils;
import org.apache.http.ParseException;
import org.jboss.netty.util.internal.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.mfq.bean.SysConfig;
import com.mfq.bean.user.SignIndex;
import com.mfq.bean.user.Status;
import com.mfq.bean.user.User;
import com.mfq.bean.wechat.WeChatMsg;
import com.mfq.bean.wechat.message.AccessToken;
import com.mfq.cache.WeChatJsApiTicketCacheUtils;
import com.mfq.constants.Career;
import com.mfq.constants.Constants;
import com.mfq.constants.ErrorCodes;
import com.mfq.dao.SysConfigMapper;
import com.mfq.dao.WeChatMsgMapper;
import com.mfq.helper.SignHelper;
import com.mfq.service.CouponService;
import com.mfq.service.LoginService;
import com.mfq.service.passport.PassportService;
import com.mfq.service.user.UserQuotaService;
import com.mfq.service.user.UserService;
import com.mfq.utils.DateUtil;
import com.mfq.utils.HttpUtil;
import com.mfq.utils.JsonUtil;
import com.mfq.utils.MD5;
import com.mfq.utils.MessageUtil;
import com.mfq.utils.VerifyUtils;

@Service
public class WeChatService {

    private static final Logger log = LoggerFactory
            .getLogger(LoginService.class);
	
    @Resource
    UserService userService;
    @Resource
    UserQuotaService userQuotaService;
    @Resource
    CouponService couponService;
    @Resource
    PassportService passportService;
    @Resource
    WeChatMsgMapper weChatMsgMapper;
    @Resource
    SysConfigMapper sysConfigMapper;
    
	public static final String ACCESS_TOKEN = "access_token";
	
	public static final String EXPIRES_IN = "expires_in";
	
	public static final String CREATE_AT = "expires_in";
	
	public static final int TOKEN_IN = 7000;
    
	/**
	 * 微信注册 名字 ，手机号
	 * @param name 名字
	 * @param mobile 手机号
	 */
    public String registerWeChatUser(String passwd,String mobile,String vcode) throws Exception{
  
    	log.info("Wechat REG|{}|{}|{}", passwd, mobile, vcode);
    	
        int code = ErrorCodes.SUCCESS;
        String msg = "注册成功";
        
        Map<String, Object> data = Maps.newHashMap();
        
        if (StringUtils.isBlank(passwd)) {
            code = 1001;
            msg = "密码格式错误";
        }  else if (StringUtils.isNotBlank(mobile)
                && !VerifyUtils.verifyMobile(mobile)) {
            code = 1004;
            msg = "手机号格式错误";
        } else if (StringUtils.isNotBlank(mobile)
                && userService.queryUserByMobile(mobile).getUid() != 0) {
            code = 1104;
            msg = "此手机号已注册";
        }
        if(code > 0){
        	return JsonUtil.toJson(code, msg,data);
        }
    	long userId=userService.createUser(Status.NORMAL, null, "", null, mobile, Career.ELSE.getId(), "", new SignIndex[0]);
    	if (userId <= 0) {
            code = 9999;
            msg = "创建用户失败";
        } else {
        	 passportService.createPassport(userId,
        			 passwd);
        }
    	return JsonUtil.toJson(code, msg,data);
    }
    
    
	/**
	 * 验证请求用户
	 * @param name 名字
	 * @param mobile 手机号
	 */
    public boolean checkRequestUser(HttpServletRequest request,String token){
    	
    	if(StringUtils.isBlank(token)){
    		return false;
    	}
    	String vsign=(String)request.getSession().getAttribute("uid");
    	if(token.equals(vsign)){
    		return true;
    	}
    	return false;
    	
    }
    
    
	/**
	 * 扫描二维码送代金卷 （如果用户不存在 则创建)
	 * @param vcode 验证码
	 * @param mobile 手机号
	 */
	public String regAndGiveMoney(String name, String mobile, String vcode, String type, BigDecimal amount, int wish) throws Exception {
		  
    	log.info("Wechat REG|{}|{}",  mobile, vcode);
    	
        int code = ErrorCodes.SUCCESS;
        String msg = "赠送成功";
        
        Map<String, Object> data = Maps.newHashMap();
        
        if (StringUtils.isNotBlank(name) && !VerifyUtils.verifyRealName(name)) {
            code = 1001;
            msg = "姓名格式错误";
        } else if (StringUtils.isNotBlank(mobile)
                && !VerifyUtils.verifyMobile(mobile)) {
            code = 1004;
            msg = "手机号格式错误";
        } 
        
        long uid = 0;
        User user = userService.queryUserByMobile(mobile);
        if (StringUtils.isNotBlank(mobile)
                && user.getUid() != 0) {

        	userQuotaService.updateUserWish(user.getUid(), wish);

        } else{
        	uid=userService.createUser(Status.NORMAL, null, name, null, mobile, Career.ELSE.getId(), "", new SignIndex[0]);
        	if (uid <= 0) {
                code = 9999;
                msg = "创建用户失败";
            } else {
            	passportService.createPassport(uid,
            			genRandomPass());
            	userQuotaService.updateUserWish(uid, wish);

            }
        }
        if(code == ErrorCodes.SUCCESS){
            couponService.makeAndSaveCouponByBatch(uid, 1);
        }
    	return JsonUtil.toJson(code, msg, data);
	}
	
	//生成随机数
	private static String genRandomPass(){
		int i = (int) (Math.random() * 1000000 + 100000);
		return String.valueOf(i);
	}

    /**
     * 保存微信消息
     * @param wechat
     * @return
     */
    public long saveWeChatMsg(WeChatMsg wechat){
    	return weChatMsgMapper.saveWeChatMsg(wechat);
    }
    
    public long saveWeChatMsg(Map<String, String> map){
    	WeChatMsg msg = new WeChatMsg();
    	msg.setToUserName(StringUtils.stripToEmpty(map.get("ToUserName")));
    	msg.setOpenId(StringUtils.stripToEmpty(map.get("FromUserName")));
    	msg.setCreateAt(DateUtil.getDateTimeBySeconds(map.get("CreateTime")));
    	msg.setMsgType(StringUtils.stripToEmpty(map.get("MsgType")));
    	msg.setPicUrl(StringUtils.stripToEmpty(map.get("PicUrl")));
    	msg.setContent(StringUtils.stripToEmpty(map.get("Content")));
    	msg.setMediaId(StringUtils.stripToEmpty(map.get("MediaId")));
    	msg.setMsgId(StringUtils.stripToEmpty(map.get("MsgId")));
    	return weChatMsgMapper.saveWeChatMsg(msg);
    }
    
	/**
	 * 获取accessToken
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	public AccessToken getAccessToken() throws ParseException, IOException{
		SysConfig config = sysConfigMapper.getConfig(Constants.WACHAT_TOKEN_KEY);
		AccessToken token = new AccessToken();
		if(config !=null && config.getValue() != null){
			token = JsonUtil.toBean(config.getValue(), AccessToken.class);
			if ((System.currentTimeMillis()- token.getCreateAt())/1000 < TOKEN_IN) {// 有效
				return token;
			}
		}

		token = httpAccessToken();
		sysConfigMapper.updateConfigByKey(new SysConfig(Constants.WACHAT_TOKEN_KEY, JsonUtil.writeToJson(token)));
		return token;
	}
	
	/**
	 * 获取微信js token
	 * @return
	 * @throws IOException
	 */
	public JSONObject getJSToken() throws IOException{
		SysConfig config = sysConfigMapper.getConfig("WX_JS");
		JSONObject token;
		if(config !=null && config.getValue() != null){
			token = JSONObject.parseObject(config.getValue());
			if ((System.currentTimeMillis()- token.getLong("create_at"))/1000 < TOKEN_IN) {// 有效
				return token;
			}
		}
		token = httpJSToken();
		token.put("create_at", System.currentTimeMillis()/1000);
		sysConfigMapper.updateConfigByKey(new SysConfig("WX_JS", JsonUtil.writeToJson(token)));
		return token;
	}
	
	private JSONObject httpJSToken() throws ParseException, IOException{
		String url=Constants.ACCESS_JSTOKEN_URL.replace("ACCESS_TOKEN", getAccessToken().getToken());
		String result = HttpUtil.get(url, false);
		return JSONObject.parseObject(result);
	}
	
	private AccessToken httpAccessToken() throws IOException{
		String url = Constants.ACCESS_TOKEN_URL.replace("APPID", Constants.APPID).replace("APPSECRET", Constants.APPSECRET);
		String result = HttpUtil.get(url, false);
		AccessToken token = parseAccessToken(JSONObject.parseObject(result));
		token.setCreateAt(System.currentTimeMillis());
		return token;
	}
	
	private AccessToken parseAccessToken(JSONObject token){
		AccessToken wtoken = new AccessToken();
		wtoken.setToken(token.getString(ACCESS_TOKEN));
		wtoken.setExpiresIn(token.getIntValue(EXPIRES_IN));
		return wtoken;
	}
	
	/**
	 * 获取客服
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	public JSONArray getKefu() throws IOException{
		String url = Constants.WECHAT_KF_URL.replace("ACCESS_TOKEN", getAccessToken().getToken());
		String result = HttpUtil.get(url, false);
		
		return JSONArray.parseArray(result);
	}
	
	/**
	 * 客服发送消息（异步消息）
	 * @return
	 * @throws IOException
	 */
	public String sendAsyncMessage(String toUser, String msgType, String content) throws IOException{
		String ret = "";
		JSONObject message = null;
		if(msgType.equals(MessageUtil.MESSAGE_TEXT)){
			message = MessageUtil.initKfTextMessage(toUser, content);
		}
		log.info("wechat sendMessgae is {}",message.toJSONString());
		System.out.println(message.toJSONString());
		String url = Constants.WECHAT_SEND_MESSAGE.replace("ACCESS_TOKEN", getAccessToken().getToken());
		String result = HttpUtil.post(url, message.toJSONString(), false);
		JSONObject re = JSONObject.parseObject(result);
		
		int code = re.getInteger("errcode");
		String msg = re.getString("errmsg");
		if( code == 0){
			ret = JsonUtil.successResultJson(msg);
		}else {
			ret = JsonUtil.toJson(code, msg, null);
			log.warn(" wechat sendMessage fild , return msg is {}", result);
		}
		return ret;
	}

	/**
	 * 获取关注用户信息
	 * @param openId
	 * @return
	 * @throws IOException 
	 * @throws ParseException 
	 */
	public String queryUser(String openId) throws ParseException, IOException {
		if(openId == null){
			log.error("参数不合法！OpenId is null");
			return JsonUtil.toJson(ErrorCodes.CORE_PARAM_UNLAWFUL, "参数不合法", null);
            
		}
		String url = Constants.WECHAT_QUERY_USER.replace("ACCESS_TOKEN", getAccessToken().getToken()).replace("OPENID", openId);
		String result = HttpUtil.get(url, false);
		JSONObject re = JSONObject.parseObject(result);
		return JsonUtil.successResultJson(re);
	}
	
	/**
	 * 获取图片信息
	 * @param openId
	 * @return
	 * @throws IOException 
	 * @throws ParseException 
	 */
	public InputStream queryMedia(String media) throws ParseException, IOException {
		if(media == null){
			log.error("参数不合法！media is null");
			return null;
            
		}
		JSONObject params = new JSONObject();
		params.put("media_id", media);
		
		String url = Constants.WECHAT_FOREVER_MEDIA.replace("ACCESS_TOKEN", getAccessToken().getToken()).replace("MEDIA_ID", media);
		InputStream result = HttpUtil.postMedia(url, params.toJSONString());
		return result;
	}
	
	
	/**
	 * 获取js api token
	 * @return
     */
	public String generateJsApiToken(String url){
		//先获取jsapi_ticket
		log.info("enter to generateJsApiToken");
		try{
			JSONObject ticket_js = getJSApiTicket();
			if(ticket_js == null){
				return JsonUtil.toJson(1001, "ticket 获取失败", null);
			}
			if("".equals(url)){
				return JsonUtil.toJson(1002, "url 参数错误", null);
			}
			Map<String, Object> params= Maps.newHashMap();
			String  ticket =ticket_js.getString("ticket");
			String nonceStr = genNonceStr();
			String timestamp = (System.currentTimeMillis()/1000)+"";
			params.put("noncestr", nonceStr);
			params.put("timestamp", timestamp);
			params.put("url", url);
			params.put("jsapi_ticket", ticket);
			String sign = SignHelper.makeWxSign(params);
			log.info("sign"+sign);
			Map<String,Object> data = Maps.newHashMap();
			data.put("appId", Constants.APPID);
			data.put("timestamp", timestamp);
			data.put("nonceStr", nonceStr);
			data.put("signature", sign);
			return JsonUtil.successResultJson(data);
		}catch(Exception e){
			log.error(""+e);
		}

		return null;
	}

	private String genNonceStr() {
		Random random = new Random();
		return MD5.getMessageDigest(String.valueOf(random.nextInt(10000)).getBytes());
	}

	private JSONObject getJSApiTicket(){
		try{
			String accessToken = HttpUtil.get(StringUtils.replace("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET","APPID",Constants.APPID).replace("APPSECRET", Constants.APPSECRET),false);
			String ticket = HttpUtil.get(StringUtils.replace("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi","ACCESS_TOKEN", JSONObject.parseObject(accessToken).getString("access_token")),false);
			log.info(JSONObject.parseObject(accessToken).getString("access_token"));

			//String ticket = WeChatJsApiTicketCacheUtils.getTicket();
			log.info("ticket:{}"+ticket);
			if(ticket != null){
				return JSON.parseObject(ticket);
			}
			//ticket为null的话,就重新获取一遍
			String url = Constants.WECHAT_JS_TICKET.replace("ACCESS_TOKEN", getAccessToken().getToken());
			String result = HttpUtil.get(url, false);
			if(JSONObject.parseObject(result).getInteger("errcode") == 0){
				WeChatJsApiTicketCacheUtils.cacheTicket(result);
				return JSON.parseObject(result);
			}else{
				log.info("get js ticket return result is error result = {}", result);
			}
			
		}catch (Exception e){
			log.info("get js ticket is error {}", e);
		}
		return null;
	}

}