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

import com.mfq.constants.Constants;
import com.mfq.constants.ErrorCodes;
import com.mfq.dataservice.context.UserIdHolder;
import com.mfq.helper.SignHelper;
import com.mfq.service.LoginService;
import com.mfq.service.passport.PassportService;
import com.mfq.utils.JsonUtil;

/**
 * 用户管理&登陆退出相关-无线
 */
@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory
            .getLogger(LoginController.class);

    @Resource
    LoginService loginService;
    @Resource
    PassportService passportService;
 
    @RequestMapping(value = {"/login", "/login/"}, method = {RequestMethod.POST,RequestMethod.GET}, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String login(HttpServletRequest request, HttpServletResponse response) {
        try{
            Map<String, Object> params = JsonUtil.readMapFromReq(request);
            if (!SignHelper.validateSign(params)) { // 签名验证失败
                return JsonUtil.toJson(ErrorCodes.SIGN_VALIDATE_ERROR, "签名验证失败",
                        null);
            }
            String username = (String) params.get("username");
            String password = (String) params.get("password");
            String refer = (String) params.get("refer");
            if(StringUtils.isBlank(refer)){
                refer = "http://" + Constants.SITE_DOMAIN + "/profile/";
            }
            Boolean redirect = (Boolean) params.get("r");
            if(redirect == null){
                redirect = false;
            }
            
            String ret = loginService.login(request, response, username, password, refer, false,params);
            if(redirect){
                //提取refer
                Map<String, ?> map = JsonUtil.getMapFromJsonStr(ret);
                if(map.get("data") != null && map.get("forward_url") != null){
                    refer = map.get("forward_url").toString();
                }
                response.sendRedirect(refer);
            }
            logger.info("LoginRet is:{}", ret);
            return ret;
        } catch (Exception e) {
            logger.error("Exception Login Process!", e);
            return JsonUtil.toJson(ErrorCodes.CORE_ERROR, "系统异常", null);
        }
    }

    @RequestMapping(value = {"/logout", "/logout/"}, method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String logout(HttpServletRequest request) throws Exception {
        //注销用户
        try{
            //更新推送列表的信息
            loginService.updateIntallations(request, 0);
            if (UserIdHolder.isLogin()) {
                //过期m票
                passportService.destroyPassport(UserIdHolder.getLongUid());
                return JsonUtil.successResultJson();
            } else {
                logger.error("logout failed, empty access token");
                return JsonUtil.toJson(ErrorCodes.FAIL, "退出登录失败，access token为空", null);
            }
        } catch (Exception e) {
            logger.error("Exception Logout Process!", e);
            return JsonUtil.toJson(ErrorCodes.CORE_ERROR, "系统异常", null);
        }
    }
}
