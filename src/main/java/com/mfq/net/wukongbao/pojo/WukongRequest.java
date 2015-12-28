package com.mfq.net.wukongbao.pojo;

public class WukongRequest {
	
	private String appKey;      //悟空保平台分配的唯一商户识别码
	private String serviceName; //接口名称  （美心安）
	private String charset;     //编码  （UTF-8)
	private String signType;    //签名类型   RSA
	private String sign;        //签名
	private String notifyUrl;   //悟空保主动通知商户结果的http路径
	private String version;     //接口版本
	private String bizContent;  //业务参数  
	private String timestamp;   //时间戳  格式“yyyyMMddHHmmss”
	private String format;      //格式化  JSON
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getCharset() {
		return charset;
	}
	public void setCharset(String charset) {
		this.charset = charset;
	}
	public String getSignType() {
		return signType;
	}
	public void setSignType(String signType) {
		this.signType = signType;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getNotifyUrl() {
		return notifyUrl;
	}
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getBizContent() {
		return bizContent;
	}
	public void setBizContent(String bizContent) {
		this.bizContent = bizContent;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	
}
