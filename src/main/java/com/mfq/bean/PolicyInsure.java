package com.mfq.bean;

import java.util.Date;

public class PolicyInsure {
    private Long id;

    private String orderNo;

    private Date orderTime;  //订单成交时间

    private Date serviceStime;  //服务开始时间

    private String productName; //产品名称

    private String hospitalName;  //医院名称

    private Long orderSum;  //订单消费金额

    private String orderPayType;   //订单支付方式

    private Long nurseId;   //陪护人员ID

    private Long uid;   //用户id

    private String policyPid; //保单产品id

    private Long policyId;  //产品ID

    private String requestStr;

    private String reponseStr;

    private Date requestTime;

    private Date reponseTime;

    private Integer status;  //保单状态

    private Date createdAt;

    private Date updatedAt;

    public PolicyInsure(){
    	this.createdAt = new Date();
    	this.updatedAt = new Date();
    }
    
    @Override
    public String toString(){
    	String s = this.orderNo +"|"+ this.orderTime +"|"+ this.requestStr +"|"+ this.reponseStr +"|"+ this.status;
    	return s;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Date getServiceStime() {
        return serviceStime;
    }

    public void setServiceStime(Date serviceStime) {
        this.serviceStime = serviceStime;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName == null ? null : hospitalName.trim();
    }

    public Long getOrderSum() {
        return orderSum;
    }

    public void setOrderSum(Long orderSum) {
        this.orderSum = orderSum;
    }

    public String getOrderPayType() {
        return orderPayType;
    }

    public void setOrderPayType(String orderPayType) {
        this.orderPayType = orderPayType == null ? null : orderPayType.trim();
    }

    public Long getNurseId() {
        return nurseId;
    }

    public void setNurseId(Long nurseId) {
        this.nurseId = nurseId;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getPolicyPid() {
        return policyPid;
    }

    public void setPolicyPid(String policyPid) {
        this.policyPid = policyPid == null ? null : policyPid.trim();
    }

    public Long getPolicyId() {
        return policyId;
    }

    public void setPolicyId(Long policyId) {
        this.policyId = policyId;
    }

    public String getRequestStr() {
        return requestStr;
    }

    public void setRequestStr(String requestStr) {
        this.requestStr = requestStr == null ? null : requestStr.trim();
    }

    public String getReponseStr() {
        return reponseStr;
    }

    public void setReponseStr(String reponseStr) {
        this.reponseStr = reponseStr == null ? null : reponseStr.trim();
    }

    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    public Date getReponseTime() {
        return reponseTime;
    }

    public void setReponseTime(Date reponseTime) {
        this.reponseTime = reponseTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}