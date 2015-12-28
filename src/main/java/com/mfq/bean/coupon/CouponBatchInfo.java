package com.mfq.bean.coupon;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class CouponBatchInfo {
    
    long id;
    String batch; // 批次说明
    Date periodBeg; // 有效开始日期
    Date periodEnd; // 有效结束日期
    BigDecimal money; // 抵扣金额
    BigDecimal condition; // 折扣条件
    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
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
    public BigDecimal getMoney() {
        return money;
    }
    public void setMoney(BigDecimal money) {
        this.money = money;
    }
    public BigDecimal getCondition() {
        return condition;
    }
    public void setCondition(BigDecimal condition) {
        this.condition = condition;
    }

    @Override
    public String toString(){
        return ToStringBuilder.reflectionToString(this);
    }
    
}