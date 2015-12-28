package com.mfq.service.sms.tongyi;

public class SendItem {
	private String smsID ;//短信ID
	private String recvNum ;//接收号码
	private String SmsContent ;//短信内容
	private String bid ;//用户登录名
	private String SendLevel;
	
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public String getSmsID() {
		return smsID;
	}
	public void setSmsID(String smsID) {
		this.smsID = smsID;
	}
	public String getRecvNum() {
		return recvNum;
	}
	public void setRecvNum(String recvNum) {
		this.recvNum = recvNum;
	}
	public String getSmsContent() {
		return SmsContent;
	}
	public void setSmsContent(String smsContent) {
		SmsContent = smsContent;
	}
	public String getSendLevel() {
		return SendLevel;
	}
	public void setSendLevel(String sendLevel) {
		SendLevel = sendLevel;
	}
	
	
}
