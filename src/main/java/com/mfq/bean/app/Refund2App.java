package com.mfq.bean.app;

import com.mfq.bean.Refund;

import java.math.BigDecimal;
import java.util.Date;

public class Refund2App {

	// 返回值json中data内容：
	long id; // id
	String orderNo; // 订单号
	BigDecimal refundPay;
	int refundType;
	int status;
	int checkFlag;
	String checkUser;
	String content;
	Date checkTime;
	Date refundTime;
	Date created;
	Date updated;


	//订单
	String productName;
	String productImg;
	String hospitalName;
	BigDecimal price;
	BigDecimal payPrice;

	public Refund2App(Refund refund, String productName, String productImg, String hospitalName, BigDecimal price, BigDecimal payPrice){
		this.id = refund.getId();
		this.orderNo = refund.getOrderNo();
		this.refundPay = refund.getRefundPay();
		this.status = refund.getStatus();
		this.checkFlag = refund.getCheckFlag();
		this.checkUser = refund.getCheckUser();
		this.content = refund.getContent();
		this.checkTime = refund.getCheckTime();
		this.refundTime = refund.getRefundTime();
		this.created = refund.getCreated();
		this.updated = refund.getUpdated();

		this.productName = productName;
		this.productImg = productImg;
		this.hospitalName = hospitalName;
		this.price = price;
		this.payPrice = payPrice;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public BigDecimal getRefundPay() {
		return refundPay;
	}

	public void setRefundPay(BigDecimal refundPay) {
		this.refundPay = refundPay;
	}

	public int getRefundType() {
		return refundType;
	}

	public void setRefundType(int refundType) {
		this.refundType = refundType;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getCheckFlag() {
		return checkFlag;
	}

	public void setCheckFlag(int checkFlag) {
		this.checkFlag = checkFlag;
	}

	public String getCheckUser() {
		return checkUser;
	}

	public void setCheckUser(String checkUser) {
		this.checkUser = checkUser;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public Date getRefundTime() {
		return refundTime;
	}

	public void setRefundTime(Date refundTime) {
		this.refundTime = refundTime;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductImg() {
		return productImg;
	}

	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getPayPrice() {
		return payPrice;
	}

	public void setPayPrice(BigDecimal payPrice) {
		this.payPrice = payPrice;
	}
}