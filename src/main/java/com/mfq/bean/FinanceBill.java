package com.mfq.bean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 分期帐单
 * @author xingyongshan
 *
 */
public class FinanceBill {

    Long id; // bill ID
    Long uid; // 用户ID
	String billNo; //账单号
    String orderNo; // 订单号
    BigDecimal newBalance; // 本期应还金额
    BigDecimal lateFee; // 滞纳金
    Integer curPeriod; // 当前期数
    Integer allPeriod; //总期数
    /**
     * @See BillStatus
     */
    Integer status; // 帐单状态
    Date tradeAt; // 交易日期
    Date chargeAt; // 记账日期
    Date billAt; // 帐单生成时间
    Date dueAt; // 帐单应还款日期
    Date payAt; // 实际还款日期
    Date updatedAt; // 最后更新日期
    
    public FinanceBill(){
    	super();
    }
    
    
    public FinanceBill(Long id, Long uid, String billNo, String orderNo,
			BigDecimal newBalance, BigDecimal lateFee, Integer curPeriod,
			Integer allPeriod, Integer status, Date tradeAt, Date chargeAt,
			Date billAt, Date dueAt, Date payAt, Date updatedAt) {
		super();
		this.id = id;
		this.uid = uid;
		this.billNo = billNo;
		this.orderNo = orderNo;
		this.newBalance = newBalance;
		this.lateFee = lateFee;
		this.curPeriod = curPeriod;
		this.allPeriod = allPeriod;
		this.status = status;
		this.tradeAt = tradeAt;
		this.chargeAt = chargeAt;
		this.billAt = billAt;
		this.dueAt = dueAt;
		this.payAt = payAt;
		this.updatedAt = updatedAt;
	}


	public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getUid() {
        return uid;
    }
    public void setUid(Long uid) {
        this.uid = uid;
    }
    public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
    public String getOrderNo() {
        return orderNo;
    }
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
    public BigDecimal getNewBalance() {
        return newBalance;
    }
    public void setNewBalance(BigDecimal newBalance) {
        this.newBalance = newBalance;
    }
    public BigDecimal getLateFee() {
        return lateFee;
    }

    public void setLateFee(BigDecimal lateFee) {
        this.lateFee = lateFee;
    }

    public Integer getCurPeriod() {
        return curPeriod;
    }
    public void setCurPeriod(Integer curPeriod) {
        this.curPeriod = curPeriod;
    }
    public Integer getAllPeriod() {
        return allPeriod;
    }
    public void setAllPeriod(Integer allPeriod) {
        this.allPeriod = allPeriod;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public Date getTradeAt() {
        return tradeAt;
    }
    public void setTradeAt(Date tradeAt) {
        this.tradeAt = tradeAt;
    }
    public Date getChargeAt() {
        return chargeAt;
    }
    public void setChargeAt(Date chargeAt) {
        this.chargeAt = chargeAt;
    }
    public Date getBillAt() {
        return billAt;
    }
    public void setBillAt(Date billAt) {
        this.billAt = billAt;
    }
    public Date getDueAt() {
        return dueAt;
    }
    public void setDueAt(Date dueAt) {
        this.dueAt = dueAt;
    }
    public Date getPayAt() {
        return payAt;
    }
    public void setPayAt(Date payAt) {
        this.payAt = payAt;
    }
    public Date getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }


	@Override
	public String toString() {
		return "FinanceBill [id=" + id + ", uid=" + uid + ", billNo=" + billNo
				+ ", orderNo=" + orderNo + ", newBalance=" + newBalance
				+ ", lateFee=" + lateFee + ", curPeriod=" + curPeriod
				+ ", allPeriod=" + allPeriod + ", status=" + status
				+ ", tradeAt=" + tradeAt + ", chargeAt=" + chargeAt
				+ ", billAt=" + billAt + ", dueAt=" + dueAt + ", payAt="
				+ payAt + ", updatedAt=" + updatedAt + "]";
	}
    
    
    
}
