package com.mfq.bean;

import com.mfq.utils.JsonUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * Created by liuzhiguo1 on 15/12/29.
 *
 * sign	String	32位小写
 timestamp	Long	1426817510111
 channel_type	String	'WX' or 'ALI' or 'UN' or 'KUAIQIAN' or 'JD' or 'BD' or 'YEE' or 'PAYPAL'
 sub_channel_type	String	'WX_APP' or 'WX_NATIVE' or 'WX_JSAPI' or 'WX_SCAN' or 'ALI_APP' or 'ALI_SCAN' or 'ALI_WEB' or 'ALI_QRCODE' or 'ALI_OFFLINE_QRCODE' or 'ALI_WAP' or 'UN_APP' or 'UN_WEB' or 'PAYPAL_SANDBOX' or 'PAYPAL_LIVE' or 'JD_WAP' or 'JD_WEB' or 'YEE_WAP' or 'YEE_WEB' or 'YEE_NOBANKCARD' or 'KUAIQIAN_WAP' or 'KUAIQIAN_WEB' or 'BD_APP' or 'BD_WEB' or 'BD_WAP'
 transaction_type	String	'PAY' or 'REFUND'
 transaction_id	String	'201506101035040000001'
 transaction_fee	Integer	1 表示0.01元
 trade_success	Bool	true
 message_detail	Map(JSON)	{orderId:xxxx}
 optional	Map(JSON)	{"agentId":"Alice"}
 */
public class BeeCloudResult {
    String sign;
    Long timestamp;
    String channel_type;
    String sub_channel_type;
    String transaction_type;
    String transaction_id;
    Integer transaction_fee;
    Boolean trade_success;
    Map<String,Object> message_detail;
    Map<String,Object> optional;



    //以sign为准,如果为空,将sign设为null;
    public BeeCloudResult(HttpServletRequest request) throws Exception{

        try{
            Map<String,Object> params = JsonUtil.readMapFromReq(request);
            this.sign = params.get("sign").toString();
            this.timestamp = Long.parseLong(params.get("timestamp").toString());
            this.channel_type = params.get("channel_type").toString();
            this.sub_channel_type = params.get("sub_channel_type").toString();
            this.transaction_type = params.get("transaction_type").toString();
            this.transaction_id = params.get("transaction_id").toString();
            this.transaction_fee = Integer.parseInt(params.get("transaction_fee").toString());
            this.trade_success = Boolean.parseBoolean(params.get("trade_success").toString());
            this.message_detail = (Map)params.get("message_detail");
            this.optional = (Map)params.get("optional");//orderNo 和 uid

        }catch (Exception e) {
            this.sign = null;
            throw new Exception("BeeCloudResult ERROR :"+e);
        }
        if(this.sign == null || this.channel_type == null || this.sub_channel_type == null || this.transaction_type == null
                || this.transaction_id == null || this.transaction_fee == null || this.trade_success == null
                || this.message_detail== null  || this.optional == null){

            this.sign = null;

        }

    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getChannel_type() {
        return channel_type;
    }

    public void setChannel_type(String channel_type) {
        this.channel_type = channel_type;
    }

    public String getSub_channel_type() {
        return sub_channel_type;
    }

    public void setSub_channel_type(String sub_channel_type) {
        this.sub_channel_type = sub_channel_type;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public Integer getTransaction_fee() {
        return transaction_fee;
    }

    public void setTransaction_fee(Integer transaction_fee)
    {
        this.transaction_fee = transaction_fee;
    }

    public Boolean getTrade_success() {
        return trade_success;
    }

    public void setTrade_success(Boolean trade_success) {
        this.trade_success = trade_success;
    }

    public Map<String, Object> getMessage_detail() {
        return message_detail;
    }

    public void setMessage_detail(Map<String, Object> message_detail) {
        this.message_detail = message_detail;
    }

    public Map<String, Object> getOptional() {
        return optional;
    }

    public void setOptional(Map<String, Object> optional)
    {
        this.optional = optional;
    }


    public BeeCloudResult() {
        super();
    }

    @Override
    public String toString() {
        return "BeeCloudResult{" +
                "sign='" + sign + '\'' +
                ", timestamp=" + timestamp +
                ", channel_type='" + channel_type + '\'' +
                ", sub_channel_type='" + sub_channel_type + '\'' +
                ", transaction_type='" + transaction_type + '\'' +
                ", transaction_id='" + transaction_id + '\'' +
                ", transaction_fee=" + transaction_fee +
                ", trade_success=" + trade_success +
                ", message_detail=" + message_detail +
                ", optional=" + optional +
                '}';
    }
}
