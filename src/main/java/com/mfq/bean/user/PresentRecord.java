package com.mfq.bean.user;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.mfq.constants.PresentType;

public class PresentRecord {

    long uid;
    BigDecimal amount; // 赠送金额
    PresentType type; // 赠送类型
    String remark; // 备注
    Date createdAt; // 赠送时间

    public PresentRecord() {

    }

    public PresentRecord(long uid, BigDecimal amount, PresentType type, String remark) {
        this.uid = uid;
        this.amount = amount;
        this.type = type;
        this.remark = remark;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public PresentType getType() {
        return type;
    }

    public void setType(PresentType type) {
        this.type = type;
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}