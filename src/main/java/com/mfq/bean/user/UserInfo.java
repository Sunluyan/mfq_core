package com.mfq.bean.user;

import java.util.Date;

public class UserInfo {

    long uid; // 帐户ID
    String realname; // 真实姓名
    Gender gender;  //面签申请性别
    String origin;  //籍贯地
    String idcardFront;  //身份证正面照
    String idcardReverse; //身份证反面照 
    String address; // 现居地
    String homesite; // 主页
    String idCard; // 身份证号
    String remark; // 备注
    Date createdAt; // 创建额度时间
    Date updatedAt; // 更新额度时间
    
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getIdcardFront() {
		return idcardFront;
	}
	public void setIdcardFront(String idcardFront) {
		this.idcardFront = idcardFront;
	}
	public String getIdcardReverse() {
		return idcardReverse;
	}
	public void setIdcardReverse(String idcardReverse) {
		this.idcardReverse = idcardReverse;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getHomesite() {
		return homesite;
	}
	public void setHomesite(String homesite) {
		this.homesite = homesite;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
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