package com.mfq.controller;

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

import com.mfq.constants.ErrorCodes;
import com.mfq.helper.SignHelper;
import com.mfq.service.LoginService;
import com.mfq.service.RegisterService;
import com.mfq.utils.JsonUtil;
import com.mfq.utils.RequestUtils;

@Controller
public class RegisterController {
    
    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);
    
    @Resource
    LoginService loginService;
    @Resource
    RegisterService registerService;
    
    @RequestMapping(value = {"/register2"}, method = RequestMethod.GET)
    @ResponseBody
    public String register2(HttpServletRequest request, HttpServletResponse response) {
    	long uid = Long.parseLong(request.getParameter("uid"));
    	long end = Long.parseLong(request.getParameter("end"));
    	return registerService.reg(uid, end);
    }
    
    @RequestMapping(value = {"/register", "/register/"}, method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String register(HttpServletRequest request, HttpServletResponse response) {
        try{
            String asid = request.getHeader("X-Mfq-Asid");
            asid = asid == null ? "" : asid;
            Map<String, Object> params = JsonUtil.readMapFromReq(request);
            if (!SignHelper.validateSign(params)) { // 签名验证失败
                return JsonUtil.toJson(ErrorCodes.SIGN_VALIDATE_ERROR, "签名验证失败",
                        null);
            }
            String mobile = (String) params.get("mobile");
            String email = (String) params.get("email");
            String nick = (String) params.get("nick");
            String password = (String) params.get("password");
            String vcode = (String) params.get("vcode");
            String promotion_code = StringUtils.stripToEmpty((String)params.get("p_code"));
            
            int career = (Integer) params.get("role") == null? 0 : (Integer) params.get("role");
            
            String result = registerService.reg(email, mobile, nick, password,
                    vcode, request.getRemoteAddr(), "", "", "asid:" + asid, career, promotion_code,
                    request, response,params);

            Map<String, Object> loginResultMap = JsonUtil
                    .getMapFromJsonStr(result);
            int code = Integer.parseInt(loginResultMap.get("code").toString());
            if (code != 0) {
                return result;
            } else {
                // 注册成功登陆用户
                String username = "";
                if (StringUtils.isNotEmpty(mobile)) {
                    username = mobile;
                } else if (StringUtils.isNotEmpty(email)) {
                    username = email;
                }
                String refer = RequestUtils.getString(request, "refer", request.getHeader("Referer"));
                refer = StringUtils.isEmpty(refer) ? "http://www.5imfq.com/profile/" : refer;
                result = loginService.login(request, response, username, password, refer, false,params);
                
                loginResultMap = JsonUtil.getMapFromJsonStr(result);
                code = Integer.parseInt(loginResultMap.get("code").toString());
                if (code == 0) {
                    try{
                        // 更新access token所对应的用户信息
                        @SuppressWarnings("unchecked")
                        Map<String, Object> data = (Map<String, Object>)loginResultMap.get("data");
                        if(data != null && data.get("uid") != null){
                            Long uid = Long.parseLong(data.get("uid").toString());
                            loginService.updateIntallations(request, uid);
                        }
                    }catch(Exception e){
                        logger.error("REGISTER_UPDATE_ACCESSTOKEN_ERROR", e);
                    }
                }
                return result;
            }
        }catch(Exception e){
            logger.error("Exception_Register_Progress!", e);
            return JsonUtil.toJson(ErrorCodes.CORE_ERROR, "系统异常", null);
        }
    }
}