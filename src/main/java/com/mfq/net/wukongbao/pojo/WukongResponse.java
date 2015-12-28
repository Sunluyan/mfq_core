package com.mfq.net.wukongbao.pojo;

public class WukongResponse {
	
	private String charset;  //编码UTF-8 默认
	private String signType; //签名类型RSA
	private String sign;     //签名
	private String bizContent; //应用级返回参数json序列串，(详见各个接口文档)
	private String timestamp;  //时间戳
	private String format;     //格式json
	private String msgCode;    //结果编码
	private String msgInfo;    //结果信息 n
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
	public String getMsgCode() {
		return msgCode;
	}
	public void setMsgCode(String msgCode) {
		this.msgCode = msgCode;
	}
	public String getMsgInfo() {
		return msgInfo;
	}
	public void setMsgInfo(String msgInfo) {
		this.msgInfo = msgInfo;
	}
	
}
