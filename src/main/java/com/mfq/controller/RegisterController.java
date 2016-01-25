package com.mfq.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mfq.helper.MobileHelper;
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

    /**
     *
     * @param request
     * @param response
     * @return
     */
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
            String invite_code = StringUtils.stripToEmpty((String)params.get("p_code"));
            String blackbox = params.get("blackbox") == null ? null :params.get("blackbox").toString();
            int career = params.get("role") == null? 0 : (Integer) params.get("role");
            String mobileType = MobileHelper.getMobileType(request);
            logger.info("mobileType-------------------------"+mobileType);
            if(mobileType == null || ( !mobileType.toLowerCase().equals("android") &&
                    !mobileType.toLowerCase().equals("ios"))){
                logger.error("非法请求设备");
                throw new Exception("非法请求设备");
            }

            //如果出问题,会抛出错误
            long uid = registerService.reg(email, mobile, nick, password,
                    vcode, request.getRemoteAddr(), "", "", "asid:" + asid,
                    career, invite_code,blackbox,mobileType,request,response);

            Map<String,Object> map = new HashMap<>();
            map.put("uid",uid);
            return JsonUtil.successResultJson(map);
        }catch(Exception e){
            logger.error("Exception_Register_Progress!", e);
            return JsonUtil.toJson(ErrorCodes.CORE_ERROR, e.toString(), null);
        }
    }
}