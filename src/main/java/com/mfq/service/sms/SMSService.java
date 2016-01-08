package com.mfq.service.sms;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mfq.cache.MsgTmplContext;
import com.mfq.constants.ErrorCodes;
import com.mfq.service.mail.MailService;
import com.mfq.service.user.UserService;
import com.mfq.utils.Config;
import com.mfq.utils.DateUtil;
import com.mfq.utils.JsonUtil;
import com.mfq.utils.VerifyUtils;

/**
 * 对外提供短信接口服务－SMSService，对内请使用SMSInnerService服务
 * 
 * @author xingyongshan
 *
 */
@Service
public class SMSService {

    private static final Logger logger = LoggerFactory.getLogger("SMSLOG");

    @Resource
    SMSInnerService smsInnerService;
    @Resource
    UserService userService;
    @Resource
    MailService mailService;

    String lastBatchMessage = ""; // 最后一条短信

    /**
     * 发送验证码－Code类短信服务
     * 
     * @param mobile
     * @param message
     * @throws Exception
     */
    public void sendCodeSms(String mobile, String message) throws Exception {
    	
    	//套用验证码发送模板
    	String [] p= message.split(",");
    	String msgTmpl = MsgTmplContext.getSmsTmpl("mobile_reg_verification");
    	Object[] params = new Object[]{p[0]};
    	MessageFormat messageFormat = new MessageFormat(msgTmpl);
    	message = messageFormat.format(params);
        
        sendSMS(mobile, message, null, true);
    }

    /**
     * 发送普通文本内容短信
     * 
     * @param mobile
     * @param message
     * @throws Exception
     */
    public void sendSms(String mobile, String message) throws Exception {
        sendSMS(mobile, message, null, false);
    }
    
    public void sendSysSMS(String message) throws Exception{
        sendSms(Config.getItem("sms_alarm"), message);
    }
    
    /**
     * 发送订单提醒
     * @param message
     * @throws Exception
     */
    public void sendCreateOrderSMS(String [] params) throws Exception{
    	if(filterMobiles(params))
    		return;
    	String mobiles= StringUtils.stripToEmpty(Config.getItem("sms_create_order"));
    	String msgTmpl = StringUtils.stripToEmpty(MsgTmplContext.getSmsTmpl("mobile_create_order"));
    	MessageFormat messageFormat = new MessageFormat(msgTmpl);
    	String message = messageFormat.format(params);
    	sendBatchSms(mobiles, message);
    }
    
    /**
     * 发送订单完成提醒
     * @param message
     * @throws Exception
     */
    public void sendFinishOrderSMS(String [] params) throws Exception{
    		if(filterMobiles(params))
        		return;
        	String mobiles= StringUtils.stripToEmpty(Config.getItem("sms_finish_order"));
        	String msgTmpl = StringUtils.stripToEmpty(MsgTmplContext.getSmsTmpl("mobile_finish_order"));
        	MessageFormat messageFormat = new MessageFormat(msgTmpl);
        	String message = messageFormat.format(params);
        	sendBatchSms(mobiles, message);
		
    	
    }
    
    /**
     * 过滤测试手机号
     * @param mobiles
     * @return
     */
    private boolean filterMobiles(String[] params){
    	String [] testMobiles= StringUtils.stripToEmpty(Config.getItem("sms_filter_mobile")).split(",");
    	String mobile = "";
    	if(params.length>2){
    		mobile = params[1];
    	}
    	for(String s:testMobiles){
			if (mobile.equals(s))
				return true;
    	}
    	return false;
    }

    /**
     * 发送定时短信
     * 
     * @param mobile
     * @param message
     * @param datetime
     * @throws Exception
     */
    public void sendSms(String mobile, String message, String datetime)
            throws Exception {
        sendSMS(mobile, message, datetime, false);
    }

    /**
     * 批量发布短信
     * 
     * @param mobiles手机号码
     * @param message消息体
     * @throws Exception
     */
    public void sendBatchSms(String mobiles, String message) throws Exception {
        int code = 0;
        String msg = "发送成功";
        if (message.trim().isEmpty() || message.trim().length() < 10) {
            code = ErrorCodes.CORE_PARAM_NULL;
            msg = "短信内容不能为空, 不能少于10个字";
            writeCodeMsg(code, msg);
            return;
        }
        if (message.trim().length() > 65) {
            code = ErrorCodes.SMS_OVERFLOW;
            msg = "一条短信不要超过65个字符, 签名占5个字";
            writeCodeMsg(code, msg);
            return;
        }
        if (mobiles.trim().isEmpty()) {
            code = ErrorCodes.USER_ERR_MOBILE;
            msg = "手机号码格式错误";
            writeCodeMsg(code, msg);
            return;
        }
        mobiles = mobiles.replaceAll("\r\n", "\n");
        String[] mobilelist = mobiles.split(",|;|\n|\r");
        for (String mobile : mobilelist) {
            if (!VerifyUtils.verifyMobile(mobile)) {
                code = ErrorCodes.USER_ERR_MOBILE;
                msg = "有手机号码格式错误: " + mobile;
                writeCodeMsg(code, msg);
                return;
            }
        }
        if (message.trim().equals(lastBatchMessage)) {
            code = ErrorCodes.SMS_DUPLICATE;
            msg = "短信内容不能重复, 会被封号";
            writeCodeMsg(code, msg);
            return;
        } else {
            lastBatchMessage = message.trim();
        }
        logger.info(String.format("SEND_SMS %s => %s", message, mobiles));
        smsInnerService.sendBatchMessage(message.trim(),
                Arrays.asList(mobilelist));

        writeCodeMsg(code, msg);
    }

    /**
     * 
     * @param mobile
     *            手机号码
     * @param message
     *            消息体
     * @param datetime
     *            定时发送时间，格式 yyyy-MM-dd HH:mm:ss
     * @param isCode
     * @throws IOException
     */
    private void sendSMS(String mobile, String message, String datetime,
            boolean isCode) throws IOException {
        if ("null".equals(datetime)) {
            datetime = "";
        }
        int code = ErrorCodes.SUCCESS;
        String msg = "发送成功";
        if (VerifyUtils.verifyMobile(mobile)) {
            if (StringUtils.isBlank(message)) {
                code = ErrorCodes.CORE_PARAM_NULL;
                msg = "短信内容不能为空";
                writeCodeMsg(code, msg);
                return;
            }
            Date sendtime = null;
            if (StringUtils.isNotBlank(datetime)) { // 有发送时间
                sendtime = DateUtil.convertLong(datetime);
                if (!isCode && sendtime != null
                        && (sendtime.getTime() - new Date().getTime() > 0)) {
                    logger.info(String.format("SEND_SMS bytime %s => %s  at %s",
                            mobile, message, datetime));
                    smsInnerService.sendMessageByTime(message, mobile,
                            sendtime);
                } else {
                    code = ErrorCodes.CORE_DATEFORMAT;
                    msg = "发送日期格式错误或日期已过期";
                }
            } else {
                if (isCode) {
                    logger.info(String.format("SEND_SMS (CODE) %s => %s",
                            mobile, message));
                    smsInnerService.sendVcodeMessage(message, mobile);
                } else {
                    logger.info(String.format("SEND_SMS %s => %s", mobile,
                            message));
                    smsInnerService.sendMessage(message, mobile);
                }
            }
        } else {
            code = ErrorCodes.USER_ERR_MOBILE;
            msg = "手机号码格式错误";
        }
        writeCodeMsg(code, msg);
    }

    private void writeCodeMsg(int code, String msg) {
        String info = JsonUtil.toJson(code, msg, null);
        logger.info("SendSMS:{}", info);
        if (code != ErrorCodes.SUCCESS) {
            // email and mobile alarm
            mailService.sendMail("短信发送失败", info, Config.getItem("mail_alarm"));
        }
    }

	public void sendInterViewHint(String[] params) throws Exception {
		if(filterMobiles(params))
    		return;
    	String mobiles= StringUtils.stripToEmpty(Config.getItem("sms_interview_hint"));
    	String msgTmpl = StringUtils.stripToEmpty(MsgTmplContext.getSmsTmpl("mobile_interview_hint"));
    	MessageFormat messageFormat = new MessageFormat(msgTmpl);
    	String message = messageFormat.format(params);
    	sendBatchSms(mobiles, message);
	}
	
}
