package com.mfq.payment.util.wechat;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.mfq.utils.DateUtil;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("xml")
public class PayReqData {

    // 每个字段具体的意思请查看API文档
    String appid = "";
    String mch_id = "";
    String device_info = "";
    String nonce_str = "";
    String body = "";
    String out_trade_no = "";
    int total_fee = 0;
    String spbill_create_ip = "";
    String time_start = "";
    String time_expire = "";

    String notify_url = "";
    String trade_type = "";
    
    String sign = ""; // sign最后生成之后再set

    public PayReqData() {
        appid = Configure.appID;
        mch_id = Configure.mchID;
        time_start = DateUtil.formatCurTimeLong();
        time_expire = DateUtil
                .formatYYYYMMDDHHMMSS(DateUtil.addMinute(new Date(), 60));
        trade_type = "APP";
    }

    public PayReqData(String device_info, String nonce_str, String body,
            String out_trade_no, int total_fee, String spbill_create_ip,
            String notify_url) {
        this();
        this.device_info = device_info;
        this.nonce_str = nonce_str;
        this.body = body;
        this.out_trade_no = out_trade_no;
        this.total_fee = total_fee;
        this.spbill_create_ip = spbill_create_ip;
        this.notify_url = notify_url;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public int getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(int total_fee) {
        this.total_fee = total_fee;
    }

    public String getSpbill_create_ip() {
        return spbill_create_ip;
    }

    public void setSpbill_create_ip(String spbill_create_ip) {
        this.spbill_create_ip = spbill_create_ip;
    }

    public String getTime_start() {
        return time_start;
    }

    public void setTime_start(String time_start) {
        this.time_start = time_start;
    }

    public String getTime_expire() {
        return time_expire;
    }

    public void setTime_expire(String time_expire) {
        this.time_expire = time_expire;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            Object obj;
            try {
                obj = field.get(this);
                if (obj != null) {
                    map.put(field.getName(), obj);
                }
            } catch (IllegalArgumentException e) {
//                logger.error("PayReqData_IllegalArgumentException", e);
            } catch (IllegalAccessException e) {
//                logger.error("PayReqData_IllegalAccessException", e);
            }
        }
        return map;
    }
}
