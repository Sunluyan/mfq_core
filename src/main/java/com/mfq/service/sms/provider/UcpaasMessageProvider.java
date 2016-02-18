package com.mfq.service.sms.provider;

import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;
import com.mfq.utils.DateUtil;
import com.mfq.utils.HttpUtil;
import com.mfq.utils.JsonUtil;
import com.mfq.utils.MD5Util;

/**
 * 云之讯Message服务提供商
 * @author xingyongshan
 *
 */
public class UcpaasMessageProvider  extends MessageProvider {
    
    private static final Logger logger = LoggerFactory.getLogger(UcpaasMessageProvider.class);

    private String serverURL;
    private String softVersion;
    private String accountSid;
    private String authToken;
    
    private String appId = "c87b5cc35a9c4ce5a6ab91b247534cf3";
    private String vcodeTmpId = "20568";
    
    @Override
    public void loadConfiguration(Map<String, Object> setting) {
        this.softVersion = (String) setting.get("softVersion");
        this.accountSid = (String) setting.get("accountSid");
        StringBuilder serverBuilder = new StringBuilder((String) setting.get("baseURL")).append("/")
            .append(softVersion).append("/Accounts/")
            .append(accountSid).append("/Messages/templateSMS");
        this.serverURL = serverBuilder.toString();
        this.authToken = (String) setting.get("authToken");
    }

    @Override
    public String getBalance() {
        return null;
    }

    @Override
    public String sendBatchMessage(String content, String mobiles) {
        return null;
    }

    @Override
    public String sendVcodeMessage(String content, String mobile) {

        Map<String, Object> bodyMap = Maps.newHashMap();
        Map<String, String> inner = Maps.newHashMap();
        inner.put("appId", appId);
        inner.put("templateId", vcodeTmpId);
        inner.put("to", mobile); // mobile需逗号分割，该处仅针对单个对象发送
        inner.put("param", content + ",30");
        bodyMap.put("templateSMS", inner);
        String body = JsonUtil.writeToJson(bodyMap);
        String timestamp = DateUtil.formatCurTimeLong();
        String sign = buildSign(timestamp);
        logger.info("SMS_SEND,Yunzhixun Request Param:{}, {}, {}", body, timestamp, sign);
        String resp = HttpUtil.postSmsYzx(serverURL+"?sig="+sign, body, accountSid, timestamp, false);
        return resp;
    }

    @Override
    public String sendSingleMessage(String content, String mobile) {
        return null;
    }

    @Override
    public String sendSingleMessageBytime(String content, String mobile,
            Date sendtime) {
        return null;
    }
    
    private String buildSign(String timestamp){
        //sig= MD5（账户Id + 账户授权令牌 + 时间戳），共32位(注:转成大写)
        return MD5Util.md5Digest(accountSid+authToken+timestamp).toUpperCase();
    }
}