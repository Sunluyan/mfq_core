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

import com.mfq.annotation.LoginRequired;
import com.mfq.constants.ErrorCodes;
import com.mfq.service.sms.SMSService;
import com.mfq.utils.JsonUtil;
import com.mfq.utils.RequestUtils;

/**
 * MFQ-ADMIN得发短信入口
 * 
 * @author xingyongshan
 *
 */
@Controller
public class SMSController {

    private static final Logger logger = LoggerFactory
            .getLogger(SMSController.class);

    @Resource
    SMSService smsService;

    /**
     * 发送短信-mfq-admin使用
     *
     * @param request
     *            请求
     * @param response
     *            响应
     * @throws Exception
     *             任何异常
     */
    @RequestMapping(value = { "/sms/send",
            "/sms/send/" }, method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    @LoginRequired
    public String smsSend(HttpServletRequest request,
            HttpServletResponse response) {
        try {
            Map<String, Object> params = JsonUtil.readMapFromReq(request);
            String mobile = (String) params.get("mobile");
            String message = (String) params.get("message");
//            if (!SignHelper.validateSign(params) || !MFQAdminUtil.isAdminUser(request)) { // 签名验证失败
//                return JsonUtil.toJson(ErrorCodes.SIGN_VALIDATE_ERROR, "签名验证失败",
//                        null);
//            }
            String ip = RequestUtils.getString(request, "ip",
                    request.getRemoteAddr());
            smsService.sendBatchSms(mobile, message);
            logger.info("mm_msg_full_log: {}|{}|{}|{}", ip,
                    request.getHeader("Referer"), mobile, message);
            return JsonUtil.successResultJson();
        } catch (Exception e) {
            logger.error("Exception Send SMS!", e);
            return JsonUtil.toJson(ErrorCodes.CORE_ERROR, "系统异常", null);
        }
    }
}