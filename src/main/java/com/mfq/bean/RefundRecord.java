package com.mfq.bean;

import java.math.BigDecimal;
import java.util.Date;

public class RefundRecord {
    
    public static final int STATUS_APPLY = 0; // 退款申请中
    public static final int STATUS_SUCCESS = 1; // 退款成功
    public static final int STATUS_FAIL = 2; // 退款失败
    
    long id;
    
    //退款方式 0 是线下 1是线上
    boolean isOnlineRefund;

    //订单号
    String orderNo;
    //批次号
    String batchNo;
    
    //退款请求时间
    Date applyTime;
    
    //交易额,精确到分，return Amount，应退给客户的钱
    BigDecimal amount;
    //退款返回时间
    Date notifyTime;
    //操作员
    String operator;
    
    //退款详情，returnDetail，提交的退款详情
    String detail;
    
    //结果处理详情，returnData，退款返回的明细
    String resultDetails;
    
    //错误代码
    String errCode;
    
    //是否已退款,取值见STATUS开头的常量
    int status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isOnlineRefund() {
        return isOnlineRefund;
    }

    public void setOnlineRefund(boolean isOnlineRefund) {
        this.isOnlineRefund = isOnlineRefund;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getNotifyTime() {
        return notifyTime;
    }

    public void setNotifyTime(Date notifyTime) {
        this.notifyTime = notifyTime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getResultDetails() {
        return resultDetails;
    }

    public void setResultDetails(String resultDetails) {
        this.resultDetails = resultDetails;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
