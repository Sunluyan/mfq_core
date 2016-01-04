package com.mfq.payment;

import org.apache.commons.lang.StringUtils;

public enum PayAPIType {
	
	INNER("innerpay", "平台内支付"),
    YEEPAY("yeepay", "易宝支付"),
    WECHAT("wechat", "微信支付"),
    ALIPAY("alipay", "支付宝"),
    UNIONPAY("bcUnionpay","银联支付");//银联支付基于BeeCloud的服务
    String code;
    String pay;
    
    PayAPIType(String code, String pay){
        this.code = code;
        this.pay = pay;
    }
    
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getPay() {
        return pay;
    }
    public void setPay(String pay) {
        this.pay = pay;
    }
    
    public static PayAPIType fromCode(String code){
        for(PayAPIType p : PayAPIType.values()){
            if(StringUtils.equalsIgnoreCase(p.getCode(), code)){
                return p;
            }
        }
        return null;
    }
}