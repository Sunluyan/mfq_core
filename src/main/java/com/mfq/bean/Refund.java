package com.mfq.bean;

import com.mfq.constants.PolicyStatus;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.math.BigDecimal;
import java.util.Date;

/**
 * refund
 * 
 * @author hui
 *
 */
public class Refund {

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

    public Refund(){
        this.orderNo = "";
        this.refundPay = BigDecimal.valueOf(0);
        this.checkUser = "";
        this.content = "";
        this.checkTime = new Date();
        this.refundTime = new Date();
        this.created = new Date();
        this.updated = new Date();
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
}
