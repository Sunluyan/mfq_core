package com.mfq.controller;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mfq.service.sms.SMSService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.google.common.collect.Maps;
import com.mfq.bean.CodeMsg;
import com.mfq.bean.Vcode;
import com.mfq.bean.user.SignIndex;
import com.mfq.bean.user.User;
import com.mfq.constants.ErrorCodes;
import com.mfq.dataservice.context.UserIdHolder;
import com.mfq.helper.UserHelper;
import com.mfq.service.VcodeService;
import com.mfq.service.passport.PassportService;
import com.mfq.service.sms.MailAndSmsService;
import com.mfq.service.user.UserService;
import com.mfq.utils.DateUtil;
import com.mfq.utils.JsonUtil;
import com.mfq.utils.MailHomeUtils;
import com.mfq.utils.RequestUtils;
import com.mfq.utils.SecretDesUtils;

/**
 * 密码相关controller，包括重置密码等
 * 
 * @author xingyongshan
 *
 */
@Controller
public class PasswordController {

    private static final Logger logger = LoggerFactory
            .getLogger(PasswordController.class);

    @Resource
    UserService userService;
    @Resource
    PassportService passportService;
    @Resource
    VcodeService vcodeService;
    @Resource
    MailAndSmsService mailAndSmsService;
    @Resource
    SMSService smsService;

    // 密码reset 邮件
    @RequestMapping(value = "/password/reset/{vcode}/{email}/", method = RequestMethod.GET)
    public String resetPasswordByEmail(HttpServletRequest request,
            HttpServletResponse response, @PathVariable("vcode") String vcode,
            @PathVariable("email") String email, Model model) throws Exception {

        String decryptVcode = SecretDesUtils.decrypt(vcode);
        String decryptEmail = SecretDesUtils.decrypt(email);
        CodeMsg codeMsg = vcodeService.validate(decryptEmail, decryptVcode);
        if (codeMsg.getCode() == 0) {
            return "redirect:/password/reset/form/?vcode=" + vcode + "&key="
                    + email;
        } else {
            logger.warn("email:" + email + ",vcode:" + vcode + ",msg:"
                    + codeMsg.getMsg());
            return "/password/password_find_form";
        }
    }

    // 真正的重置form 页 //这2个参数都是加密后的
    @RequestMapping(value = "/password/reset/form/", method = RequestMethod.GET)
    public String passwordResetForm(String vcode, String key, Model model) {
        model.addAttribute("vcode", vcode);
        model.addAttribute("key", key);
        return "/password/password_reset_form";
    }

    // 进入到 发送邮件页
    @RequestMapping(value = "/password/mail/form/", method = RequestMethod.GET)
    public String passwordMailForm(String email, Model model) {
        model.addAttribute("email", email);
        model.addAttribute("mailHome", MailHomeUtils.getMailHome(email));
        return "/password/password_mail_form";
    }

    // 进入找回密码form 页
    @RequestMapping(value = {"/password/find/form", "/password/find/form/"}, method = RequestMethod.GET)
    public String passwordFindForm() {
        return "/password/password_find_form";
    }

    // 发送 mobile 验证码
    @RequestMapping(value = "/a/password/reset/mobile/{mobile:\\d+}/", method = RequestMethod.POST)
    @ResponseBody
    public String resetPasswordByMobile(HttpServletRequest request,
            @PathVariable(value = "mobile") String mobile) throws Exception {
        User user = userService.queryUserByMobile(mobile);
        if (user.getUid() == 0) {
            logger.error("mobile:" + mobile
                    + ",want to reset password,but not exist");
            Map<Integer, String> map = Maps.newHashMap();
            map.put(ErrorCodes.USER_NOT_FIND, "用户不存在");
            return JsonUtil.toJson(ErrorCodes.USER_NOT_FIND, "用户不存在", null);
        }
        Vcode vcode = vcodeService.queryVcode(mobile);
        if (vcode.getId() == 0) {
            return mailAndSmsService.sendResetSms(user.getNick(), mobile);
        } else
            if (DateUtil.getDayBetweenD(new Date(), vcode.getResendAt()) > 0) {
            logger.warn(
                    "user want to apply mobile code,but resendat > currentTime,user:"
                            + UserIdHolder.getUserId());
            return JsonUtil.toJson(ErrorCodes.USER_VCODE_TOOFAST, "请过会再发",
                    null);
        }
        return mailAndSmsService.sendResetSms(user.getNick(), mobile);
    }
    

    // 发送邮件
    @RequestMapping(value = "/a/password/reset/email/{email}/", method = RequestMethod.POST)
    @ResponseBody
    public String resetEmail(HttpServletRequest request,
            @PathVariable(value = "email") String email, Model model)
                    throws Exception {
        User user = userService.queryUserByEmail(email);

        if (user.getUid() == 0) {
            logger.error(
                    "email:" + email + ",want to reset password,but not exist");
            return JsonUtil.toJson(ErrorCodes.USER_NOT_FIND, "用户不存在", null);
        }

        Vcode vcode = vcodeService.queryVcode(user.getEmail());
        if (vcode.getId() > 0 && DateUtil.getDayBetweenD(new Date(),
                vcode.getResendAt()) > 0) {
            logger.warn(
                    "user want to send  mail,but resendat > currentTime,user:"
                            + user.getUid());
            return JsonUtil.toJson(ErrorCodes.USER_VCODE_TOOFAST, "请过会再发",
                    null);
        } else {
            mailAndSmsService.sendResetMail(user.getNick(), email);
            Map<String, Object> result = Maps.newHashMap();
            result.put("forward_url", "/password/mail/form/?email=" + email);
            return JsonUtil.successResultJson();
        }
    }

    // 验证手机接口回填
    @ResponseBody
    @RequestMapping(value = "/a/password/reset/mobile/vcode/", method = RequestMethod.POST)
    public String checkVcode(@RequestParam(value = "key") String key,
            String code) {
        CodeMsg codeMsg = vcodeService.validate(key, code);

        Map<String, Object> result = Maps.newHashMap();
        result.put("forward_url",
                "/password/reset/form/?vcode=" + SecretDesUtils.encrypt(code)
                        + "&key=" + SecretDesUtils.encrypt(key));
        return JsonUtil.toJson(
                codeMsg.getCode() == 0 ? 0 : codeMsg.getCode() + 9,
                codeMsg.getMsg(), result);
    }

    // 重置密码
    @RequestMapping(value = "/a/password/reset/", method = RequestMethod.POST)
    @ResponseBody
    public String resetPassword(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String vcode = RequestUtils.getString(request, "vcode", "");
        String key = RequestUtils.getString(request, "key", "");
        String password = RequestUtils.getString(request, "password", "");
        
        Map<String, Object> params = JsonUtil.readMapFromReq(request);
        if(StringUtils.isBlank(vcode)){
        	vcode = params.get("vcode").toString();
        }
        if(StringUtils.isBlank(key)){
        	key = params.get("key").toString();
        }
        if(StringUtils.isBlank(password)){
        	password = params.get("password").toString();
        }
        	
//        String decryptVcode = SecretDesUtils.decrypt(vcode);
//        String decryptKey = SecretDesUtils.decrypt(key);
        String decryptVcode = vcode;
        String decryptKey = key;
        User user;
        if (decryptKey.contains("@")) {
            user = userService.queryUserByEmail(decryptKey);
        } else {
            user = userService.queryUserByMobile(decryptKey);
        }
        if (user.getUid() == 0) {
            logger.error("reset passowrd,but not exist,key:" + decryptKey);
            return JsonUtil.toJson(ErrorCodes.USER_NOT_FIND, "用户不存在", null);
        }
        CodeMsg codeMsg = vcodeService.validate(decryptKey, decryptVcode);
        boolean firstPassword = UserHelper.isSetSigns(user.getSign(),
                SignIndex.FirstPassword);
        if (codeMsg.getCode() == 0) {
            boolean success = passportService
                    .updatePasswordForAdmin(user.getUid(), password);
            if (firstPassword && success) {
                userService.updateSigns(user.getUid(), false,
                        SignIndex.FirstPassword);
            }
            return JsonUtil.successResultJson();
        } else {
            return JsonUtil.toJson(codeMsg.getCode(), codeMsg.getMsg(), null);
        }
    }
}
