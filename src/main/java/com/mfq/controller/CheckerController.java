package com.mfq.controller;

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

import com.mfq.bean.user.User;
import com.mfq.constants.ErrorCodes;
import com.mfq.helper.SignHelper;
import com.mfq.service.user.UserService;
import com.mfq.utils.JsonUtil;
import com.mfq.utils.VerifyUtils;

@Controller
public class CheckerController {

    private static final Logger logger = LoggerFactory
            .getLogger(CheckerController.class);

    @Resource
    UserService userService;

    @RequestMapping(value = {"/mobile/check", "/mobile/check/"}, method = { RequestMethod.GET,
            RequestMethod.POST }, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String checkMobileUsage(HttpServletRequest request,
            HttpServletResponse response) {
        try {
            Map<String, Object> params = JsonUtil.readMapFromReq(request);
            String mobile = (String) params.get("mobile");
            if (!SignHelper.validateSign(params)) { // 签名验证失败
                return JsonUtil.toJson(ErrorCodes.SIGN_VALIDATE_ERROR, "签名验证失败",
                        null);
            } else if (!VerifyUtils.verifyMobile(mobile)) {
                return JsonUtil.toJson(ErrorCodes.USER_ERR_MOBILE, "手机号码格式错误",
                        null);
            } else {
                User user = userService.queryUserByMobile(mobile);
                if (user != null && user.getUid() > 0) {
                    return JsonUtil.toJson(ErrorCodes.USER_ERR_MOBILE,
                            "手机号码已被使用", null);
                } else {
                    return JsonUtil.toJson(ErrorCodes.SUCCESS, "手机号码未使用", null);
                }
            }
        } catch (Exception e) {
            logger.error("Exception Check Mobile Usage!", e);
            return JsonUtil.toJson(ErrorCodes.CORE_ERROR, "系统异常", null);
        }
    }

    @RequestMapping(value = {"/email/check", "/email/check/"}, method = { RequestMethod.GET,
            RequestMethod.POST }, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String checkEmailUsage(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        Map<String, Object> params = JsonUtil.readMapFromReq(request);
        String email = (String) params.get("email");
        if (!SignHelper.validateSign(params)) { // 签名验证失败
            return JsonUtil.toJson(ErrorCodes.SIGN_VALIDATE_ERROR, "签名验证失败",
                    null);
        } else if (!VerifyUtils.verifyEmail(email)) {
            return JsonUtil.toJson(ErrorCodes.USER_ERR_EMAIL, "邮箱格式错误", null);
        } else {
            User user = userService.queryUserByEmail(email);
            if (user.getUid() > 0) {
                return JsonUtil.toJson(ErrorCodes.USER_ERR_EMAIL, "邮箱已被使用",
                        null);
            } else {
                return JsonUtil.toJson(ErrorCodes.SUCCESS, "邮箱未使用", null);
            }
        }
    }
}