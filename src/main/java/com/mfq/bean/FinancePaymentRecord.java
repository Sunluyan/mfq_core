package com.mfq.bean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 分期还款记录表
 * @author xingyongshan
 *
 */
public class FinancePaymentRecord {

    long id; // 还款记录ID
    long bid; // 帐单ID
    BigDecimal amount; // 还款金额
    Date dueAt; // 应还款日期
    Date payAt; // 实还款日期
    Date updatedAt; // 最后更新时间
    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public long getBid() {
        return bid;
    }
    public void setBid(long bid) {
        this.bid = bid;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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
    
}
