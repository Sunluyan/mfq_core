package com.mfq.bean;

import java.math.BigDecimal;
import java.util.Date;

public class OrderFreedom {
    private Long id;

    private Long uid;

    private String orderNo;

    private Integer hospitalId;

    private String proname;

    private BigDecimal price;

    private Integer status;

    private String couponNum;

    private BigDecimal onlinePay;

    private String securityCode;

    private Integer policyStatus;

    private Date createTime;

    private Date payTime;

    private Date updateTime;

    private Date serviceTime;

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

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public Integer getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Integer hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getProname() {
        return proname;
    }

    public void setProname(String proname) {
        this.proname = proname == null ? null : proname.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCouponNum() {
        return couponNum;
    }

    public void setCouponNum(String couponNum) {
        this.couponNum = couponNum == null ? null : couponNum.trim();
    }

    public BigDecimal getOnlinePay() {
        return onlinePay;
    }

    public void setOnlinePay(BigDecimal onlinePay) {
        this.onlinePay = onlinePay;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode == null ? null : securityCode.trim();
    }

    public Integer getPolicyStatus() {
        return policyStatus;
    }

    public void setPolicyStatus(Integer policyStatus) {
        this.policyStatus = policyStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(Date serviceTime) {
        this.serviceTime = serviceTime;
    }

    @Override
    public String toString() {
        return "OrderFreedom{" +
                "id=" + id +
                ", uid=" + uid +
                ", orderNo='" + orderNo + '\'' +
                ", hospitalId=" + hospitalId +
                ", proname='" + proname + '\'' +
                ", price=" + price +
                ", status=" + status +
                ", couponNum='" + couponNum + '\'' +
                ", onlinePay=" + onlinePay +
                ", securityCode='" + securityCode + '\'' +
                ", policyStatus=" + policyStatus +
                ", createTime=" + createTime +
                ", payTime=" + payTime +
                ", updateTime=" + updateTime +
                ", serviceTime=" + serviceTime +
                '}';
    }

    public OrderFreedom(Long id, Long uid, String orderNo, Integer hospitalId, String proname, BigDecimal price, Integer status, String couponNum, BigDecimal onlinePay, String securityCode, Integer policyStatus, Date createTime, Date payTime, Date updateTime, Date serviceTime) {
        this.id = id;
        this.uid = uid;
        this.orderNo = orderNo;
        this.hospitalId = hospitalId;
        this.proname = proname;
        this.price = price;
        this.status = status;
        this.couponNum = couponNum;
        this.onlinePay = onlinePay;
        this.securityCode = securityCode;
        this.policyStatus = policyStatus;
        this.createTime = createTime;
        this.payTime = payTime;
        this.updateTime = updateTime;
        this.serviceTime = serviceTime;
    }
    public OrderFreedom(){
        super();
    }
}