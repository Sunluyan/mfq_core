package com.mfq.net.wukongbao.pojo;

public class WukongHospitalInfo {
	
	private String providerKey;       //医院唯一编码（商户提供）
	private String providerName;      //医院名称
	private String providerLevel;     //医院级别
	private String providerAddress;   //医院地址
	
	public String getProviderKey() {
		return providerKey;
	}
	public void setProviderKey(String providerKey) {
		this.providerKey = providerKey;
	}
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	public String getProviderLevel() {
		return providerLevel;
	}
	public void setProviderLevel(String providerLevel) {
		this.providerLevel = providerLevel;
	}
	public String getProviderAddress() {
		return providerAddress;
	}
	public void setProviderAddress(String providerAddress) {
		this.providerAddress = providerAddress;
	}
	
}
