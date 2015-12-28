package com.mfq.service.sms.provider;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mfq.constants.SmsConstants;
import com.mfq.utils.DateUtil;
import com.mfq.utils.HttpUtil;

/**
 * Message Sender for TianHan.
 *
 */
public class TianHanMessageProvider extends MessageProvider {

    private static final Logger log = LoggerFactory
            .getLogger(TianHanMessageProvider.class);

    private String serverURL = "http://59.173.12.127:8081/interface/";
    private String corpID4Message = "FG000049";
    private String pwd4Message = "123456";
    private String cropID4AD = "FG000050";
    private String pwd4AD = "123456";
    private String suffix = "【美分期】"; // 后缀

    int MAXRETRY = 3; // 最多重试次数
    String RETRY_CODE = "-101";

    public void loadConfiguration(Map<String, Object> setting) {
        this.serverURL = (String) setting.get("serverURL");
        this.cropID4AD = (String) setting.get("cropID4AD");
        this.pwd4AD = (String) setting.get("pwd4AD");
        this.corpID4Message = (String) setting.get("corpID4Message");
        this.pwd4Message = (String) setting.get("pwd4Message");

        String asuffix = (String) setting.get("suffix");
        if (StringUtils.isNotBlank(asuffix)) {
            this.suffix = asuffix;
        }
    }

    private String doGeneralRequest(String function,
            Map<String, String> params) {
        return doGeneralRequest(function, corpID4Message, params);
    }

    private String doGeneralRequest(String function, String corpId,
            Map<String, String> params) {
        params.put("CorpID", corpId);
        params.put("Pwd", pwd4Message);
        try {
            String code = SmsConstants.ERROR_SERVER;
            int retry = 1;
            while (retry <= MAXRETRY) {
                try {
                    code = HttpUtil.postForm(serverURL + function + ".aspx",
                            params, true);
                    // 不是速度问题
                    if (StringUtils.isNotBlank(code)
                            && !code.equals(RETRY_CODE)) {
                        break;
                    } else {
                        log.error(
                                "TianHan mobile message Service need retry, the code: "
                                        + code);
                        retry++;
                    }
                } catch (RuntimeException e) {
                    throw e;
                } catch (Exception e) {
                    log.error(
                            "exception occured when call TianHan mobile message Service, need retry",
                            e);
                    retry++;
                }
                Thread.sleep(500); // sleep
            } // end while
            if (retry > 1) {
                log.warn(
                        "retry occured when use TianHan mobile message Service before success ");
            }
            return code;
        } catch (Exception e) {
            log.error(this.getClass().toString()
                    + " mobile message service error", e);
            return SmsConstants.ERROR_SERVER;
        }
    }

    private String doADRequest(String function, Map<String, String> params) {
        params.put("CorpID", cropID4AD);
        params.put("Pwd", pwd4AD);

        try {
            String response = HttpUtil.postForm(serverURL + function + ".aspx",
                    params, true);
            return response;
        } catch (Exception e) {
            log.error(this.getClass().toString() + " service error", e);
            return SmsConstants.ERROR_SERVER;
        }
    }

    @Override
    public String getBalance() {
        String function = "SelSum";

        Map<String, String> params = new LinkedHashMap<String, String>();

        return doGeneralRequest(function, params);
    }

    public String getBalance(String corpId) {
        String function = "SelSum";

        Map<String, String> params = new LinkedHashMap<String, String>();

        return doGeneralRequest(function, corpId, params);
    }

    @Override
    public String sendBatchMessage(String content, String mobiles) {
        String function = "BatchSend";

        Map<String, String> params = new LinkedHashMap<String, String>();
        params.put("Mobile", mobiles);
        params.put("Content", fillContent(content));

        return doADRequest(function, params);
    }

    @Override
    public String sendVcodeMessage(String content, String mobile) {
        String function = "Send";

        Map<String, String> params = new LinkedHashMap<String, String>();
        params.put("Mobile", mobile);
        params.put("Content", fillContent(content));

        return doGeneralRequest(function, params);
    }

    @Override
    public String sendSingleMessage(String content, String mobile) {
        String function = "Send";

        Map<String, String> params = new LinkedHashMap<String, String>();
        params.put("Mobile", mobile);
        params.put("Content", fillContent(content));

        return doGeneralRequest(function, params);
    }

    @Override
    public String sendSingleMessageBytime(String content, String mobile,
            Date datetime) {
        String sendtime = "";
        if (datetime != null) {
            sendtime = DateUtil.formatLong(datetime);
        }
        String function = "Send";
        Map<String, String> params = new LinkedHashMap<String, String>();
        params.put("Mobile", mobile);
        params.put("Content", fillContent(content));

        // 格式处理
        sendtime = sendtime.replaceAll("-", "").replaceAll(":", "")
                .replaceAll(" ", "");
        params.put("SendTime", sendtime);

        return doGeneralRequest(function, params);
    }

    private String fillContent(String content) {
        return content + suffix;
    }
}