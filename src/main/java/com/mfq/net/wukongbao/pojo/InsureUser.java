package com.mfq.net.wukongbao.pojo;

/**
 * 投保用户 info
 * @author hui
 *
 */
public class InsureUser {
	private String userKey;
	private String userName;
	private String userPhone;
	private String userIDType;
	private String userIDInfo;
//	private String userEmail;
	public String getUserKey() {
		return userKey;
	}
	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserIDType() {
		return userIDType;
	}
	public void setUserIDType(String userIDType) {
		this.userIDType = userIDType;
	}
	public String getUserIDInfo() {
		return userIDInfo;
	}
	public void setUserIDInfo(String userIDInfo) {
		this.userIDInfo = userIDInfo;
	}
//	public String getUserEmail() {
//		return userEmail;
//	}
//	public void setUserEmail(String userEmail) {
//		this.userEmail = userEmail;
//	}
	
}
