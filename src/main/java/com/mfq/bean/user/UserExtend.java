package com.mfq.bean.user;

import java.util.Date;

public class UserExtend {

    long uid; // 帐户ID
    String inviteCode; //邀请码
    String promotionCode; //推广码
    boolean isBind;  //是否绑定邀请码
	String openId;
    String remark; // 备注
    String mobileType;
    String channelId;
    Date createdAt; // 创建额度时间
    Date updatedAt; // 更新额度时间
    
    public UserExtend(long uid, String promotionCode){
    	this.uid = uid;
    	this.inviteCode = "";
    	this.promotionCode = "";
    	this.mobileType = "";
    	this.channelId = "";
    	this.isBind = false;
    	this.openId = "";
    	this.remark = "";
    	this.createdAt = new Date();
    	this.updatedAt = new Date();
    }
    
    public UserExtend(){
    	this.uid = 0;
    	this.inviteCode = "";
    	this.promotionCode = "";
    	this.mobileType = "";
    	this.channelId = "";
    	this.isBind = false;
    	this.openId = "";
    	this.remark = "";
    	this.createdAt = new Date();
    	this.updatedAt = new Date();
    }
    
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public String getInviteCode() {
		return inviteCode;
	}
	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getPromotionCode() {
		return promotionCode;
	}
	public void setPromotionCode(String promotionCode) {
		this.promotionCode = promotionCode;
	}
	
	public String getMobileType() {
		return mobileType;
	}

	public void setMobileType(String mobileType) {
		this.mobileType = mobileType;
	}

	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
    public boolean isBind() {
		return isBind;
	}
	public void setBind(boolean isBind) {
		this.isBind = isBind;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
    
}