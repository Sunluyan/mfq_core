package com.mfq.service.sms;

import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mfq.bean.Vcode;
import com.mfq.bean.user.User;
import com.mfq.cache.MsgTmplContext;
import com.mfq.constants.Constants;
import com.mfq.service.VcodeService;
import com.mfq.service.mail.MailService;
import com.mfq.service.user.UserService;
import com.mfq.utils.JsonUtil;
import com.mfq.utils.SecretDesUtils;

@Service
public class MailAndSmsService {

    private static Logger logger = LoggerFactory
            .getLogger(MailAndSmsService.class);

    private static long MAIL_EXPIRE_DURATION = TimeUnit.DAYS.toMillis(2);// 激活链接两天有效
    private static long SEND_DURATION = TimeUnit.MINUTES.toMillis(5);// 重发间隔5分钟
    private long MOBILE_EXPIRE_DURATION = 1000 * 60 * 30; // 30分钟有效
    private long Msm_SEND_DURATION = 1000 * 60 * 2; // 重发时间2分钟

    @Resource
    UserService userService;
    @Resource
    VcodeService vcodeService;
    @Resource
    SMSInnerService smsService;
    @Resource
    MailService mailService;

    public void sendRegMail(long uid, String email, String refer)
            throws Exception {
        User user = userService.queryUser(uid);
        Vcode vcode = vcodeService.applyVcode(email, new Date(System.currentTimeMillis() + MAIL_EXPIRE_DURATION),
                new Date(System.currentTimeMillis() + SEND_DURATION));

        String url = String.format(
                "http://%s/mail/bind/?vcode=%s&uid=%s&email=%s&referer=%s",
                Constants.SITE_DOMAIN, SecretDesUtils.encrypt(vcode.getVcode()),
                SecretDesUtils.encrypt("" + user.getUid()),
                SecretDesUtils.encrypt(email), SecretDesUtils.encrypt(refer));
        Object[] params = new Object[] {
                url,
                user.getNick(),
                Constants.SITE_NAME,
                Constants.SITE_DOMAIN
        };
        logger.info("send reg mail to {}", email);
        mailService.sendMail(
                buildFragment("/mail/model/mail_reg_title.ftl", params, false),
                buildFragment("/mail/model/mail_reg_content.ftl", params, false), email);
    }

    public void sendResetMail(String nick, String email) throws Exception {
        Vcode vcode = vcodeService.applyVcode(email,
                new Date(System.currentTimeMillis() + MAIL_EXPIRE_DURATION),
                new Date(System.currentTimeMillis() + SEND_DURATION));

        String url = "http://" + Constants.SITE_DOMAIN + "/password/reset/"
                + SecretDesUtils.encrypt(vcode.getVcode()) + "/"
                + SecretDesUtils.encrypt(email) + "/";
        Object[] params = new Object[] { 
                url, 
                nick, 
                Constants.SITE_NAME,
                Constants.SITE_DOMAIN
                };
        logger.info("send reset mail to {}", email);
        mailService.sendMail(buildFragment("mail_reset_title", params, false),
                buildFragment("mail_reset_content", params, false), email);
    }

    public String sendResetSms(String nick, String mobile) throws Exception {
        Vcode applyvcode = vcodeService.applyVcode(mobile,
                new Date(System.currentTimeMillis() + MOBILE_EXPIRE_DURATION),
                new Date(System.currentTimeMillis() + Msm_SEND_DURATION));
//        Object[] params = new Object[] {
//        		nick,
//                applyvcode.getVcode()
//                };

//        smsService.sendVcodeMessage(
//                buildFragment("mobile_reset_verification", params, true), mobile);

        smsService.sendVcodeMessage(applyvcode.getVcode(), mobile, true);

        Map<String, Object> reData = new HashMap<String, Object>();
        reData.put("reSendSecond",
                (applyvcode.getResendAt().getTime() - System.currentTimeMillis()) / 1000);
        return JsonUtil.successResultJson(reData);
    }

    protected String buildFragment(String key, Object[] params, boolean isSms)
            throws Exception {
        String tmplText = "";
        if (isSms) {
            tmplText = MsgTmplContext.getSmsTmpl(key);
        } else {
            tmplText = MsgTmplContext.getMailTmpl(key);
        }
        String msgContent = null;
        if (StringUtils.isBlank(tmplText)) {
            logger.warn("{} 短信模板未找到!", new Object[] { key });
            return msgContent;
        }
        // 短信或邮件一起处理
        MessageFormat messageFormat = new MessageFormat(tmplText);
        if (params == null) {
            logger.warn("{} 短信参数未找到", new Object[] { key });
            return messageFormat.toString();
        }
        msgContent = messageFormat.format(params);
        if (StringUtils.isBlank(msgContent)) {
            logger.warn("SMS content is null!");
        } else {
            logger.info(
                    "\n-------------------------------------- smsContent: {}",
                    msgContent);
        }
        return msgContent;
    }
}