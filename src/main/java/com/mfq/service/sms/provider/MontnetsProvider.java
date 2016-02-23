package com.mfq.service.sms.provider;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mfq.constants.SmsConstants;
import com.mfq.utils.HttpUtil;

/**
 * provider:梦网科技
 * 
 * @author xingyongshan
 *
 */
public class MontnetsProvider extends MessageProvider {

    private static final Logger log = LoggerFactory
            .getLogger(MontnetsProvider.class);

    /*
     * 账号：JC2010 密码：322155 ip ：61.145.229.29 端口:9003
     */
    private String serverURL = "http://61.145.229.29:9002/MWGate/wmgw.asmx";
    private String corpID4Message = "JC2010";
    private String pwd4Message = "322155";

    private String suffix = ""; // 后缀

    int MAXRETRY = 3; // 最多重试次数

    @Override
    public void loadConfiguration(Map<String, Object> setting) {
        this.serverURL = (String) setting.get("serverURL");
        this.corpID4Message = (String) setting.get("corpID4Message");
        this.pwd4Message = (String) setting.get("pwd4Message");

        String asuffix = (String) setting.get("suffix");
        if (StringUtils.isNotBlank(asuffix)) {
            this.suffix = asuffix;
        }
    }

    @Override
    public String getBalance() {
        // url:/MWGate/wmgw.asmx/MongateQueryBalance?userId=string&password=string
        String function = "/MongateQueryBalance";

        Map<String, String> params = new LinkedHashMap<String, String>();
        String resultType = "int";

        return doSimpleRequest(false, function, params, resultType);
    }

    private String doSimpleRequest(boolean batch, String function,
            Map<String, String> sendparams, String resultType) {

        Map<String, String> params = new LinkedHashMap<>();
        params.put("userId", corpID4Message);
        params.put("password", pwd4Message);
        params.putAll(sendparams);
        try {
            String code = SmsConstants.ERROR_SERVER;
            int retry = 0;
            while (retry < MAXRETRY) {
                try {
                    String response = HttpUtil.postForm(serverURL + function,
                            params, true);
                    // 不是速度问题
                    if (StringUtils.isNotBlank(response)) {
                        code = pickupSimpleResult(response, resultType);
                        break;
                    } else {
                        log.error(
                                "Montnets mobile message Service need retry, return nothing ");
                        retry++;
                    }
                } catch (RuntimeException e) {
                    throw e;
                } catch (Exception e) {
                    log.error(
                            "exception occured when call Montnets mobile message Service, need retry",
                            e);
                    retry++;
                }
                Thread.sleep(500); // sleep
            } // end while
            if (retry > 1) {
                log.warn(
                        "retry occured when use Montnets mobile message Service before success, retry:  "
                                + retry);
            }
            // 可以忽略的错误
            if ("-12".equalsIgnoreCase(code)) {
                return SmsConstants.ERROR_RULE_IGNORE + code;
            }
            return code;
        } catch (Exception e) {
            log.error(this.getClass().toString()
                    + " mobile message service error", e);
        }
        return SmsConstants.ERROR_SERVER;
    }

    private String pickupSimpleResult(String content, String type) {

        // <string xmlns="http://tempuri.org/">string</string>
        String begin = "<" + type + " xmlns=\"http://tempuri.org/\">";
        String end = "</" + type + ">";
        int beginIndex = content.indexOf(begin) + begin.length();
        int endIndex = content.indexOf(end);
        if (beginIndex > 0 && endIndex > 0) {
            return content.substring(beginIndex, endIndex);
        }
        return null;
    }

    @Override
    public String sendBatchMessage(String content, String mobiles) {

        String function = "/MongateSendSubmit";

        String resultType = "string";
        Map<String, String> params = new LinkedHashMap<String, String>();

        params.put("pszMobis", mobiles);
        params.put("pszMsg", content + suffix);

        String[] mobileList = mobiles.split(",");

        params.put("iMobiCount", Integer.toString(mobileList.length));
        params.put("pszSubPort", "*");

        String time = Long.toString(System.currentTimeMillis());
        if (time.length() > 14) {
            time = time.substring(time.length() - 14);
        }
        params.put("MsgId", time);
        String result = doSimpleRequest(false, function, params, resultType);

        if (result != null && result.length() > 10) {
            result = "R" + result;
        }

        return result;
    }

    @Override
    public String sendVcodeMessage(String content, String mobile, boolean isReset) {
        return sendSingleMessage(content, mobile);
    }

    @Override
    public String sendSingleMessage(String content, String mobile) {
        // url:
        // /MWGate/wmgw.asmx/MongateSendSubmit?userId=string&password=string&pszMobis=string&pszMsg=string&iMobiCount=string&pszSubPort=string&MsgId=string
        String function = "/MongateSendSubmit";
        String resultType = "string";
        Map<String, String> params = new LinkedHashMap<String, String>();

        params.put("pszMobis", mobile);
        params.put("pszMsg", content + suffix);

        params.put("iMobiCount", "1");
        params.put("pszSubPort", "*");

        String time = Long.toString(System.currentTimeMillis());
        if (time.length() > 14) {
            time = time.substring(time.length() - 14);
        }

        params.put("MsgId", time);

        String result = doSimpleRequest(false, function, params, resultType);

        if (result != null && result.length() > 10) {
            result = "R" + result;
        }
        return result;
    }

    @Override
    public String sendSingleMessageBytime(String content, String mobile,
            Date sendtime) {
        throw new UnsupportedOperationException("not avaiable");
    }
}