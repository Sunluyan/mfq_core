package com.mfq.controller;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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

import com.google.common.cache.CacheStats;
import com.google.common.collect.Maps;
import com.mfq.bean.CodeMsg;
import com.mfq.bean.Vcode;
import com.mfq.bean.user.SignIndex;
import com.mfq.bean.user.User;
import com.mfq.cache.IPRateLimiter;
import com.mfq.constants.ErrorCodes;
import com.mfq.dataservice.context.UserIdHolder;
import com.mfq.helper.SignHelper;
import com.mfq.service.VcodeService;
import com.mfq.service.sms.MailAndSmsService;
import com.mfq.service.sms.SMSService;
import com.mfq.service.user.UserService;
import com.mfq.utils.DateUtil;
import com.mfq.utils.JsonUtil;
import com.mfq.utils.RequestUtils;

/**
 * 手机确认码服务
 */
@Controller
public class VcodeController {

    private static final Logger logger = LoggerFactory
            .getLogger(VcodeController.class);

    private final long MOBILE_EXPIRE_DURATION = 1000 * 60 * 30; // 30分钟有效
    private final long Msm_SEND_DURATION = 1000 * 60 * 2; // 重发时间2分钟
    private final IPRateLimiter ipRateLimiter = IPRateLimiter.create(1000,
            TimeUnit.HOURS.toMillis(24)); // ip 缓冲池 1k, 缓存时间 24h
    private final int MAX_TIMES_PRE_IP = 1000;

    @Resource
    VcodeService vcodeService;
    @Resource
    SMSService smsService;
    @Resource
    UserService userService;
    
    @Resource
    MailAndSmsService mailAndSmsService;

    @RequestMapping(value = "/vcode/clean-up-iplimit/")
    @ResponseBody
    public String cleanUpIPLimit(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        CacheStats stats = ipRateLimiter.status();
        String str = String.format(
                "hitCount:%s, missCount:%s, loadSuccessCount:%s, loadExceptionCount:%s, totalLoadTime:%s, evictionCount:%s",
                stats.hitCount(), stats.missCount(), stats.loadSuccessCount(),
                stats.loadExceptionCount(), stats.totalLoadTime(),
                stats.evictionCount());
        String ret = JsonUtil.toJson(ErrorCodes.SUCCESS, str, null);
        ipRateLimiter.cleanUp();
        return ret;
    }

    /**
     * 发送短信确认码
     *
     * @param request
     *            请求
     * @param response
     *            响应
     * @throws Exception
     *             任何异常
     */
    @RequestMapping(value = {"/vcode/send", "/vcode/send/"}, method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String apply(HttpServletRequest request,
            HttpServletResponse response) {
        try{
            Map<String, Object> params = JsonUtil.readMapFromReq(request);
            String mobile = (String) params.get("mobile");
            if (!SignHelper.validateSign(params)) { // 签名验证失败
                return JsonUtil.toJson(ErrorCodes.SIGN_VALIDATE_ERROR, "签名验证失败",
                        null);
            }
            String ip = RequestUtils.getString(request, "ip",
                    request.getRemoteAddr());
            if (!ipRateLimiter.tryAcquire(ip, MAX_TIMES_PRE_IP)) {
                logger.error("IP 请求超限, ip:{}", ip);
                return JsonUtil.toJson(1005, "IP 请求超限", null);
            }
            Vcode vcode = vcodeService.queryVcode(mobile);
            if (vcode.getId() > 0
                    && DateUtil.getDayBetweenD(new Date(), vcode.getResendAt())  > 0 ) {
                return JsonUtil.toJson(ErrorCodes.USER_VCODE_TOOFAST, "请求太频繁",
                        null);
            }
            Date expireAt = new Date(System.currentTimeMillis() + MOBILE_EXPIRE_DURATION);
            Date resendAt = new Date(System.currentTimeMillis() + Msm_SEND_DURATION);
            vcode = vcodeService.applyVcode(mobile, expireAt, resendAt);

            String msg = vcode.getVcode();

            logger.info("msg_full_log: {}|{}|{}|{}", ip,
                    request.getHeader("Referer"), mobile, msg);

            smsService.sendCodeSms(mobile, msg);
            Map<String, Object> data = Maps.newHashMap();
            long reSendSecond = (vcode.getResendAt().getTime() - System.currentTimeMillis())
                    / 1000;
            data.put("reSendSecond", reSendSecond); // 兼容web的响应字段名
            data.put("seqId", vcode.getSeqId());
            return JsonUtil.successResultJson(data);
        }catch(Exception e){
            logger.error("Exception Send Vcode!", e);
            return JsonUtil.toJson(ErrorCodes.CORE_ERROR, "系统异常", null);
        }
    }

    /**
     * 手机短信确认码验证
     *
     * @throws Exception
     *             任何异常
     */
    @RequestMapping(value = {"/vcode/validate", "/vcode/validate/"}, method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String validate(HttpServletRequest request,
            HttpServletResponse response) {
        try{
            Map<String, Object> params = JsonUtil.readMapFromReq(request);
            if (!SignHelper.validateSign(params)) { // 签名验证失败
                return JsonUtil.toJson(ErrorCodes.SIGN_VALIDATE_ERROR, "签名验证失败",
                        null);
            }
            String mobile = (String) params.get("mobile");
            String code = (String) params.get("vcode");
            if(StringUtils.isBlank(mobile) || StringUtils.isBlank(code)){
                return JsonUtil.toJson(ErrorCodes.CORE_PARAM_NULL, "参数异常", null);
            }
            CodeMsg cm = vcodeService.validate(mobile, code);
            return JsonUtil.toJson(cm.getCode(), cm.getMsg(), null);
        }catch(Exception e){
            logger.error("Exception Validate Vcode!", e);
            return JsonUtil.toJson(ErrorCodes.CORE_ERROR, "系统异常", null);
        }
    }

    /**
     * 绑定用户手机号码（用户登录后输入手机号码和验证码绑定手机号）
     * 
     * 任何异常
     */
    @RequestMapping(value = {"/vcode/binduser", "/vcode/binduser/"}, method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String vcodeBinduser(HttpServletRequest request,
            HttpServletResponse response) {
        try{
            Map<String, Object> params = JsonUtil.readMapFromReq(request);
            if (!SignHelper.validateSign(params)) { // 签名验证失败
                return JsonUtil.toJson(ErrorCodes.SIGN_VALIDATE_ERROR, "签名验证失败",
                        null);
            }
            String mobile = (String) params.get("mobile");
            String code = (String) params.get("vcode");
            if (!UserIdHolder.isLogin()) {
                return JsonUtil.toJson(ErrorCodes.CORE_USER_NOLOGIN,
                        "用户未登录或者用户票据无效", null);
            }
            logger.info("vcode_binduser {}: {} -> {}", UserIdHolder.getLongUid(),
                    mobile, code);
            User dbUser = userService.queryUserByMobile(mobile);
            if (dbUser.getUid() > 0
                    && dbUser.getUid() != UserIdHolder.getLongUid()) {
                return JsonUtil.toJson(ErrorCodes.USER_MOBILE_USED, "手机号被占用", null);
            }
            CodeMsg cm = vcodeService.validate(mobile, code);
            if (cm.getCode() == 0) {
                userService.updateMobile(UserIdHolder.getLongUid(), mobile);
                userService.updateSigns(UserIdHolder.getLongUid(), false,
                        SignIndex.NewMobile);
                // 无视结果，重复绑定相同手机
                return JsonUtil.successResultJson();
            }
            return JsonUtil.toJson(cm.getCode(), cm.getMsg(), null);
        }catch(Exception e){
            logger.error("Exception BindUser By Vcode!", e);
            return JsonUtil.toJson(ErrorCodes.CORE_ERROR, "系统异常", null);
        }

    }
}
