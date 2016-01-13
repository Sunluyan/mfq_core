package com.mfq.bean.app;

import java.math.BigDecimal;
import java.util.Date;

public class CouponInfo2App {

	// 返回值json中data内容：
    long id;
    long uid; // 关联的用户，默认值未关联的为0
    long batchId; // 批次ID
    String couponNum; // 优惠券编码
    BigDecimal money; // 优惠券金额
    int status; // 状态 0初始状态 1冻结 2已使用
    Date updatedAt; // 最后更新时间
    String batch; // 批次说明
    Date periodBeg; // 有效开始日期
    Date periodEnd; // 有效结束日期
    BigDecimal condition; // 折扣条件


	public CouponInfo2App() {
		super();
	}

	public CouponInfo2App(com.mfq.bean.coupon.Coupon coupon, com.mfq.bean.coupon.CouponBatchInfo couponBatchInfo) {
		if(coupon==null||couponBatchInfo==null)
			return;
		this.id = coupon.getId();
		this.uid = coupon.getUid();
		this.batchId = coupon.getBatchId();
		this.couponNum = coupon.getCouponNum();
		this.money = coupon.getMoney();
		this.status = coupon.getStatus().getValue();
		this.updatedAt = coupon.getUpdatedAt();
		this.batch = couponBatchInfo.getBatch();
		this.periodBeg = couponBatchInfo.getPeriodBeg();
		this.periodEnd = couponBatchInfo.getPeriodEnd();
		this.condition = couponBatchInfo.getCondition();
		
	}

	@Override
	public String toString() {
		return "CouponInfo2App{" +
				"id=" + id +
				", uid=" + uid +
				", batchId=" + batchId +
				", couponNum='" + couponNum + '\'' +
				", money=" + money +
				", status=" + status +
				", updatedAt=" + updatedAt +
				", batch='" + batch + '\'' +
				", periodBeg=" + periodBeg +
				", periodEnd=" + periodEnd +
				", condition=" + condition +
				'}';
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public long getBatchId() {
		return batchId;
	}

	public void setBatchId(long batchId) {
		this.batchId = batchId;
	}

	public String getCouponNum() {
		return couponNum;
	}

	public void setCouponNum(String couponNum) {
		this.couponNum = couponNum;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public Date getPeriodBeg() {
		return periodBeg;
	}

	public void setPeriodBeg(Date periodBeg) {
		this.periodBeg = periodBeg;
	}

	public Date getPeriodEnd() {
		return periodEnd;
	}

	public void setPeriodEnd(Date periodEnd) {
		this.periodEnd = periodEnd;
	}

	public BigDecimal getCondition() {
		return condition;
	}

	public void setCondition(BigDecimal condition) {
		this.condition = condition;
	}
	
	
}