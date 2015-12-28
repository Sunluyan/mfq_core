package com.mfq.bean;

import java.math.BigDecimal;
import java.util.Date;

import com.mfq.constants.PolicyStatus;

public class PolicyInfo {
    private Long id;

    private String orderNo;

    private String productNo;

    private String insuranceCode;

    private String phone;

    private String policyHolder;

    private String policyNo;

    private BigDecimal premium;

    private BigDecimal sumInsured;

    private Date policyBdate;

    private Date policyEdate;

    private PolicyStatus policyStatus;

    public PolicyInfo(){
    	this.insuranceCode = "";
    	this.phone = "";
    	this.policyHolder = "";
    	this.policyNo = "";
    	this.premium = BigDecimal.valueOf(0);
    	this.sumInsured = BigDecimal.valueOf(0);
    	this.policyBdate = new Date();
    	this.policyEdate = new Date();
    	this.setPolicyStatus(PolicyStatus.AUDITING);
    }
    
    @Override
    public String toString(){
    	String s = this.orderNo +"|"+ this.policyHolder +"|"+ this.policyStatus ;
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

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo == null ? null : productNo.trim();
    }

    public String getInsuranceCode() {
        return insuranceCode;
    }

    public void setInsuranceCode(String insuranceCode) {
        this.insuranceCode = insuranceCode == null ? null : insuranceCode.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getPolicyHolder() {
        return policyHolder;
    }

    public void setPolicyHolder(String policyHolder) {
        this.policyHolder = policyHolder == null ? null : policyHolder.trim();
    }

    public String getPolicyNo() {
        return policyNo;
    }

    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo == null ? null : policyNo.trim();
    }

    public BigDecimal getPremium() {
        return premium;
    }

    public void setPremium(BigDecimal premium) {
        this.premium = premium;
    }

    public BigDecimal getSumInsured() {
        return sumInsured;
    }

    public void setSumInsured(BigDecimal sumInsured) {
        this.sumInsured = sumInsured;
    }

    public Date getPolicyBdate() {
        return policyBdate;
    }

    public void setPolicyBdate(Date policyBdate) {
        this.policyBdate = policyBdate;
    }

    public Date getPolicyEdate() {
        return policyEdate;
    }

    public void setPolicyEdate(Date policyEdate) {
        this.policyEdate = policyEdate;
    }

	public PolicyStatus getPolicyStatus() {
		return policyStatus;
	}

	public void setPolicyStatus(PolicyStatus policyStatus) {
		this.policyStatus = policyStatus;
	}
    
}