package com.mfq.service.sms.provider;

import com.google.common.collect.Maps;
import com.mfq.utils.DateUtil;
import com.mfq.utils.HttpUtil;
import com.mfq.utils.JsonUtil;
import com.mfq.utils.XMLConverUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Map;

public class AlidayuMessageProvider extends MessageProvider {

    private static final Logger logger = LoggerFactory
            .getLogger(AlidayuMessageProvider.class);

    /*
     * webservice服务器定义
     */
    private String serviceURL = "http://www.mxtong.net.cn/GateWay/Services.asmx";
    private String UserId = "968928";
    private String account = "admin";
    private String password = "YC8M5D"; // 原始密码
    private String suffix = "【美分期】"; // 后缀

    /**
     * 加载配置
     */
    public void loadConfiguration(Map<String, Object> setting) {
        try {
            this.serviceURL = (String)setting.get("serviceURL");
            this.UserId = (String)setting.get("UserId");
            this.account = (String)setting.get("account");
            this.password = (String)setting.get("password");

            String asuffix = (String)setting.get("suffix");
            if (StringUtils.isNotBlank(asuffix)) {
                this.suffix = asuffix;
            }
        } catch (Exception e) {
            logger.error("configurationException", e);
        }
    }

    /**
     * 接口功能：查询当前用户的短信总发送量、余量、当日发送量
     * 
     * @return xml格式 需解析
     */
    public Map<String, Object> directGetStockDetails() {
        // 连接 /DirectGetStockDetails?UserID=899332&Account=admin&Password=333222
        StringBuilder url = new StringBuilder(this.serviceURL)
                .append("/DirectGetStockDetails?UserID=").append(this.UserId)
                .append("&Account=").append(this.account).append("&Password=")
                .append(this.password);

        String resp = HttpUtil.getFromInputStream(url.toString(), true);
        if (StringUtils.containsIgnoreCase(resp, "GBK")) {
            resp = StringUtils.replace(resp, "GBK", "utf-8");
        }
        logger.info("send sms result :{}", resp);
        return XMLConverUtil.convertSoapToMap(resp);
    }

    /**
     * 接口功能：不登录到服务器，直接获取回复的短信内容
     * 
     * @return
     */
    public Map<String, Object> directFetchSMS() {
        StringBuilder url = new StringBuilder(serviceURL)
                .append("/DirectFetchSMS?UserID=").append(UserId)
                .append("&Account=").append(account).append("&Password=")
                .append(password);

        String resp = HttpUtil.getFromInputStream(url.toString(), true);
        if (StringUtils.containsIgnoreCase(resp, "GBK")) {
            resp = StringUtils.replace(resp, "GBK", "utf-8");
        }
        logger.info("send sms result :{}", resp);
        return XMLConverUtil.convertSoapToMap(resp);
    }

    /**
     * 发送信息
     * 
     * @param mobile
     *            发送手机号
     * @param content
     *            发送内容
     * @param sendType
     *            int发送类别（ 填1 ）
     * @param sendTime
     *            发送时间 （String类型 格式为 yyyy-mm-dd hh:mm:ss（为空则立即发送）
     * @param postFixNumber
     *            任务扩展号(1到9,或者为空)
     * @return
     * @throws  
     */
    public String directSend(String mobile, String content, int sendType,
            String sendTime, String postFixNumber) {
        String resp = null;
        try {
            logger.info("Maixun短信mobile:{},content:{}", mobile, content);
            String url = serviceURL + "/DirectSend";
            Map<String, String> params = Maps.newHashMap();
            params.put("UserID", this.UserId);
            params.put("Account", this.account);
            params.put("Password", this.password);
            params.put("Phones", mobile);
            params.put("SendType", String.valueOf(sendType));
            params.put("SendTime", StringUtils.defaultIfBlank(sendTime, ""));
            params.put("PostFixNumber", StringUtils.defaultIfBlank(postFixNumber, ""));
            params.put("Content",
                    new String(content.getBytes(), "UTF-8"));
            logger.info("Maixun短信参数:{}", params);
            resp = HttpUtil.postSmsMaixun(url, params, true);
        } catch (Exception e) {
            logger.error("directSend_Exception", e);
        }
        return resp;
    }

    // 继承方法
    @Override
    public String getBalance() {
        Map<String, Object> data = directGetStockDetails();
        logger.info("maixuntong balance is {}", data);
        return JsonUtil.writeToJson(data);
    }

    /**
     * 批量
     */
    @Override
    public String sendBatchMessage(String content, String mobiles) {
    	logger.info("=====进入Maixun MessageBatch。。。mobiles is {}",mobiles);
    	mobiles = mobiles.replace(",", ";");
        return directSend(mobiles, content + suffix, 1, null, null);
    }

    @Override
    public String sendVcodeMessage(String content, String mobile, boolean isReset) {
        return directSend(mobile, content + suffix, 1, null, null);
    }

    @Override
    public String sendSingleMessage(String content, String mobile) {
        return directSend(mobile, content + suffix, 1, null, null);
    }

    @Override
    public String sendSingleMessageBytime(String content, String mobile,
            Date sendtime) {
        String stime = "";
        if (sendtime != null) {
            stime = DateUtil.formatLong(sendtime);
        }
        return directSend(mobile, content + suffix, 1, stime, null);
    }

    public static void main(String[] args) {
        AlidayuMessageProvider m = new AlidayuMessageProvider();
        String content = "测试。。。";
        //System.out.println(content);
//        String result = m.sendBatchMessage(content, "15910812061;15910812061;15910812061");
//        m.getBalance();
//        System.out.println(result);
        // 测试群发
        // String result2=m.sendBatchMessage("测试",
        // "18612258336;15910812061");
//        System.out.println(result);
        // 测试获取剩余短信数量
        Map<String, Object> data = m.directGetStockDetails();
        System.out
                .println(data);

    }
}