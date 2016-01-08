package com.mfq.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mfq.annotation.LoginRequired;
import com.mfq.bean.app.UserProfile2App;
import com.mfq.bean.user.Gender;
import com.mfq.bean.user.UserQuota;
import com.mfq.constants.AuthStatus;
import com.mfq.constants.ErrorCodes;
import com.mfq.helper.SignHelper;
import com.mfq.service.user.UserQuotaService;
import com.mfq.service.user.UserService;
import com.mfq.utils.JsonUtil;

@Controller
@RequestMapping("/user")
public class UserController {

	private static final Logger logger = LoggerFactory
			.getLogger(UserController.class);
	
	@Resource
	UserService userService;
	@Resource
	UserQuotaService userQuotaService;
	
	
	@RequestMapping(value={"/", "/index.html"})
	public ModelAndView listPage(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("title", "用户信息");
		String userName = "";
		logger.info("user {} login meifenqi fronted system.....", userName);
		model.put("userName", userName);
		return new ModelAndView("/user/index", model);
	}
	
	
	 /**
     * 个人详细信息
     *
     * @throws Exception
     *             任何异常
     */
    @RequestMapping(value = {"/profile", "/profile/"}, method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    @LoginRequired
    public String userDetail(HttpServletRequest request,
            HttpServletResponse response) {
        String ret = "";
        try{
            Map<String, Object> params = JsonUtil.readMapFromReq(request);
            if (!SignHelper.validateSign(params)) { // 签名验证失败
                ret = JsonUtil.toJson(ErrorCodes.SIGN_VALIDATE_ERROR, "签名验证失败",
                        null);
                logger.error("签名验证失败！ret={}", ret);
                return ret;
            }
            if(params.get("uid") == null){
                ret = JsonUtil.toJson(ErrorCodes.CORE_PARAM_UNLAWFUL, "参数不合法", null);
                logger.error("参数不合法！ret={}", ret);
                return ret;
            }
            Long uid = Long.parseLong(params.get("uid").toString());
            UserProfile2App data = userService.queryUserProfile2App(uid);
            if(data != null && data.getUid() > 0){
                ret = JsonUtil.successResultJson(data);
            }else{
                ret = JsonUtil.toJson(ErrorCodes.CORE_ERROR, "构建用户信息异常", null);
            } 
        }catch(Exception e){
            logger.error("Exception Validate Vcode!", e);
            ret = JsonUtil.toJson(ErrorCodes.CORE_ERROR, "系统异常", null);
        }
        
//        ret = StringUtils.replaceOnce(ret, "\"graduatedAt\":null", "\"graduatedAt\":\"\"");
        logger.info("UserProfile_EndRet={}", ret);
        return ret;
    }
    
	 /**
     * 个人信息修改
     *
     * @throws Exception
     *             任何异常
     */
    @RequestMapping(value = {"/modify", "/modify/"}, method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    @LoginRequired
    public String Modify(HttpServletRequest request,
            HttpServletResponse response) {
    	String ret="";
    	try{
            Map<String, Object> params = JsonUtil.readMapFromReq(request); 
            if (!SignHelper.validateSign(params)) { // 签名验证失败
                ret = JsonUtil.toJson(ErrorCodes.SIGN_VALIDATE_ERROR, "签名验证失败",
                        null);
                logger.error("签名验证失败！ret={}", ret);
                return ret;
            }
            String nick = (String) params.get("nick");
            if(params.get("uid") == null || StringUtils.isBlank(nick)){
                ret = JsonUtil.toJson(ErrorCodes.CORE_PARAM_UNLAWFUL, "参数不合法", null);
                logger.error("参数不合法！ret={}", ret);
                return ret;
            }
            Long uid = Long.parseLong(params.get("uid").toString());
            int sex = (Integer) params.get("gender") == null? 0 : (Integer) params.get("gender");            
            Gender gender=Gender.fromValue(sex);
            
            ret=userService.modifyUser(uid,nick,gender);
    	}catch(Exception e){
    		logger.error("Exception set User!", e);
    	}
    	return ret;
    }
    
    /**
     * 查询用户分期状态
     * 传入uid，得到用户的认证状态
     * 
     * @param request
     * @param response
     * @return code,msg,data。如果没问题，code为0，result中为{state，desc}
     * 
     * 	0 未递交实名认证
 		1 已通过网络实名认证	-1 未通过网络实名认证
 		2 已通过人工实名认证	-2 未通过人工实名认证
 		3 已递交面签申请		-3 未通过面签
 		4 已通过面签
 		
 		
     */
    @RequestMapping(value = {"/state", "/state/"}, method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public @ResponseBody String userFinanceState(HttpServletRequest request,HttpServletResponse response){
 	   String ret = "";
 	   try {
 		   Map<String, Object> params = JsonUtil.readMapFromReq(request);
 		   if (!SignHelper.validateSign(params)) { // 签名验证失败
                ret = JsonUtil.toJson(ErrorCodes.SIGN_VALIDATE_ERROR, "签名验证失败",
                        null);
                logger.error("签名验证失败！ret={}", ret);
                return ret;
            }
 		   if(params.get("uid") == null){
                ret = JsonUtil.toJson(ErrorCodes.CORE_PARAM_UNLAWFUL, "参数不合法", null);
                logger.error("参数不合法！ret={}", ret);
                return ret;
            }
 		   
 		   long uid = Long.parseLong(params.get("uid").toString());
 		   UserQuota userQuota = userQuotaService.queryUserQuota(uid);
 		   
 		   if(userQuota==null){
               ret=JsonUtil.toJson(ErrorCodes.USER_NOT_FIND,"用户不存在",null);
               return null;
           }
 		   Map<String,Object> result = new HashMap<String, Object>();
 		   result.put("auth_status", userQuota.getAuthStatus());
 		   result.put("user_type", userQuota.getUserType());
 		   result.put("desc", AuthStatus.fromId(userQuota.getAuthStatus()).getDesc());
 		   return JsonUtil.toJson(0, null, result);
 	   } catch (Exception e) {
 		   logger.error("Exception in FinanceController.userFinanceState = {}",e);
            ret = JsonUtil.toJson(ErrorCodes.CORE_ERROR, "系统异常", null);
 	   }
 	   return ret;
    }
    
    @RequestMapping(value={"/auth/status","/auth/status/"},method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public @ResponseBody String updateUserAuthStatus(HttpServletRequest request,HttpServletResponse response){
  	   String ret = "";
  	 try {
		   Map<String, Object> params = JsonUtil.readMapFromReq(request);
		   if (!SignHelper.validateSign(params)) { // 签名验证失败
              ret = JsonUtil.toJson(ErrorCodes.SIGN_VALIDATE_ERROR, "签名验证失败",
                      null);
              logger.error("签名验证失败！ret={}", ret);
              return ret;
          }
		   if(params.get("uid") == null){
	              ret = JsonUtil.toJson(ErrorCodes.CORE_PARAM_UNLAWFUL, "参数不合法", null);
	              logger.error("参数不合法！ret={}", ret);
	              return ret;
	          }
		   if(params.get("auth_status") == null){
              ret = JsonUtil.toJson(ErrorCodes.CORE_PARAM_UNLAWFUL, "参数不合法", null);
              logger.error("参数不合法！ret={}", ret);
              return ret;
           }
 		   long uid = Long.parseLong(params.get("uid").toString());
 		   int authStatus = Integer.parseInt(params.get("auth_status").toString());
 		   int count = userQuotaService.updateAuthStatusByUid(uid,authStatus);
 		   if(count<=0){
               ret = JsonUtil.toJson(ErrorCodes.CORE_ERROR, "修改失败", null);   
 		   }
           ret = JsonUtil.toJson(0, null, "修改成功");  
 		   
	   } catch (Exception e) {
		   logger.error("Exception in FinanceController.userFinanceState = {}",e);
          ret = JsonUtil.toJson(ErrorCodes.CORE_ERROR, "系统异常", null);
	   }
  	   return ret;
    }
    
    
    
    
    
}