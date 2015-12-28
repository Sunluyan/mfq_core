package com.mfq.net.wukongbao.pojo;

import java.math.BigInteger;

/**
 * 投保订单
 * @author hui
 *
 */
public class InsureOrder {
	private String productNo;
	private String channelOrderNo;
	private String orderTime;
	private String orderServiceStartTime;
	private String channelProductName;
	private String orderSum;
	private String orderPayType;
	private String insuredld;
	private String userInfoContent;
	private String waiterInfoContent;
	private String providerInfoContent;
	public String getProductNo() {
		return productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
	public String getChannelOrderNo() {
		return channelOrderNo;
	}
	public void setChannelOrderNo(String channelOrderNo) {
		this.channelOrderNo = channelOrderNo;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public String getOrderServiceStartTime() {
		return orderServiceStartTime;
	}
	public void setOrderServiceStartTime(String orderServiceStartTime) {
		this.orderServiceStartTime = orderServiceStartTime;
	}
	public String getChannelProductName() {
		return channelProductName;
	}
	public void setChannelProductName(String channelProductName) {
		this.channelProductName = channelProductName;
	}
	
	public String getOrderSum() {
		return orderSum;
	}
	public void setOrderSum(String orderSum) {
		this.orderSum = orderSum;
	}
	public String getOrderPayType() {
		return orderPayType;
	}
	public void setOrderPayType(String orderPayType) {
		this.orderPayType = orderPayType;
	}
	public String getInsuredld() {
		return insuredld;
	}
	public void setInsuredld(String insuredld) {
		this.insuredld = insuredld;
	}
	public String getUserInfoContent() {
		return userInfoContent;
	}
	public void setUserInfoContent(String userInfoContent) {
		this.userInfoContent = userInfoContent;
	}
	public String getWaiterInfoContent() {
		return waiterInfoContent;
	}
	public void setWaiterInfoContent(String waiterInfoContent) {
		this.waiterInfoContent = waiterInfoContent;
	}
	public String getProviderInfoContent() {
		return providerInfoContent;
	}
	public void setProviderInfoContent(String providerInfoContent) {
		this.providerInfoContent = providerInfoContent;
	}
	
	
}
