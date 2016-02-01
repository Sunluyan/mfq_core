package com.mfq.controller;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mfq.constants.ErrorCodes;
import com.mfq.helper.SignHelper;
import com.mfq.service.NotificationService;
import com.mfq.utils.JsonUtil;

@Controller
@RequestMapping("/noti")
public class NotificationController {

	private static final Logger logger = LoggerFactory
			.getLogger(NotificationController.class);
	
	@Resource
	NotificationService notificationService;
	
	@RequestMapping(value={"/msg/", "/msg"}, method = RequestMethod.POST)
	@ResponseBody
	public String msgs(HttpServletRequest request, HttpServletResponse response){
		String ret="";
		try{
			Map<String, Object> params = JsonUtil.readMapFromReq(request);
	        if (!SignHelper.validateSign(params)) { // 签名验证失败
	            ret = JsonUtil.toJson(ErrorCodes.SIGN_VALIDATE_ERROR, "签名验证失败",null);
	            logger.error("签名验证失败！ret={}", ret);
	            return ret;
	        }
	        if(params.get("page") == null|| params.get("type") == null){
                ret = JsonUtil.toJson(ErrorCodes.CORE_PARAM_UNLAWFUL, "参数不合法", null);
                logger.error("参数不合法！ret={}", ret);
                return ret;
            }
	        
	        long uid= 0;
	        if(params.get("uid") != null){
	        	uid = Long.parseLong(params.get("uid").toString());
	        }
			int page = Integer.parseInt(params.get("page").toString());
			int type = Integer.parseInt(params.get("type").toString());

			ret = notificationService.queryMsgs(uid, page, type);
		}catch(Exception e){
			logger.error("msg  is error", e);
		}
		logger.info("user ret = ",ret);
		return ret;
	}
	
	@RequestMapping(value={"/read/", "/read"}, method = RequestMethod.POST)
	@ResponseBody
	public String gen(HttpServletRequest request, HttpServletResponse response){
		String ret = "";
		try{
			Map<String, Object> params = JsonUtil.readMapFromReq(request);
	        if (!SignHelper.validateSign(params)) { // 签名验证失败
	            ret = JsonUtil.toJson(ErrorCodes.SIGN_VALIDATE_ERROR, "签名验证失败",null);
	            logger.error("签名验证失败！ret={}", ret);
	            return ret;
	        }
	        if(params.get("uid") == null || params.get("type") == null || params.get("msg_id") == null){
                ret = JsonUtil.toJson(ErrorCodes.CORE_PARAM_UNLAWFUL, "参数不合法", null);
                logger.error("参数不合法！ret={}", ret);
                return ret;
            }
			long uid = Long.parseLong(params.get("uid").toString());
			long msg_id = Long.parseLong(params.get("msg_id").toString());
			int type = Integer.parseInt(params.get("type").toString());
			ret = notificationService.insertMsgRead(uid, msg_id, type);
		}catch(Exception e){
			logger.info("this is error {}",e);
		}
		return ret;
		
		
	}


	@RequestMapping( value = {"/new/count/","/new/count"}, method = {RequestMethod.GET,RequestMethod.POST} )
	@ResponseBody
	public String haveNew(HttpServletRequest request){
		String ret = "";
		try{
			Map<String, Object> params = JsonUtil.readMapFromReq(request);
			if (!SignHelper.validateSign(params)) { // 签名验证失败
				ret = JsonUtil.toJson(ErrorCodes.SIGN_VALIDATE_ERROR, "签名验证失败",null);
				logger.error("签名验证失败！ret={}", ret);
				return ret;
			}
			if(params.get("uid") == null){
				ret = JsonUtil.toJson(ErrorCodes.CORE_PARAM_UNLAWFUL, "参数不合法", null);
				logger.error("参数不合法！ret={}", ret);
				return ret;
			}
			long uid = Long.parseLong(params.get("uid").toString());

			if(uid == 0){
				ret = JsonUtil.toJson(ErrorCodes.CORE_PARAM_UNLAWFUL, "参数不合法", null);
				logger.error("参数不合法！ret={}", ret);
				return ret;
			}

			ret = notificationService.isNewMessage(uid);
		}catch (Exception e){
			logger.error("is new msagges is  {}",e);
		}
		logger.info("is new massage ret = {}", ret);
		return ret;
	}
	
}