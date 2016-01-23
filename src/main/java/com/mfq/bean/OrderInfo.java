package com.mfq.bean;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.mfq.constants.PolicyStatus;

import javax.xml.crypto.Data;

/**
 * OrderInfo
 * 
 * @author yongshan.xing
 *
 */
public class OrderInfo {

    long id; // 订单ID
    String orderNo; // 订单号
    BigDecimal price; // 总价
    long uid; // user id
    long pid; // product id
    /**
     * @See payType
     */
    int payType; // 订单类型；2分期付款，1在线＋到院，0全额付款
    BigDecimal periodPay; // 每期还款金额，默认不分期为0
    int period; // 期数，默认不分期为0
    BigDecimal onlinePay; // 在线支付－－暂时可以使用优惠券
    BigDecimal hospitalPay; // 到院支付
    BigDecimal useBalance;  //使用余额部分
    String couponNum; // 优惠券号码
    String securityCode; // 医院使用的安全码
    Date serviceStartTime;  //预约就医时间
    PolicyStatus policyStatus;  //保单状态 
    /**
     * @See OrderStatus
     */
    int status; // 订单状态
    int refundType; // 退款方式
    Date createdAt; // 订单创建时间
    Date updatedAt; // 最后更新时间

    public OrderInfo() {

    }

    public OrderInfo(String orderNo, BigDecimal price, long uid, long pid,
            int payType, int period, BigDecimal periodPay, int status,
            BigDecimal onlinePay, BigDecimal hospitalPay, String couponNum, BigDecimal useBalance, Date serviceStartTime) {
        this.orderNo = orderNo;
        this.price = price;
        this.uid = uid;
        this.pid = pid;
        this.payType = payType;
        this.period = period;
        this.periodPay = periodPay;
        this.status = status;
        this.onlinePay = onlinePay;
        this.hospitalPay = hospitalPay;
        this.couponNum = couponNum;
        this.useBalance = useBalance;
        this.policyStatus = PolicyStatus.WITHOUT;
        this.serviceStartTime = serviceStartTime;
        this.createdAt = new Date();
        this.updatedAt = new Date();
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public BigDecimal getPeriodPay() {
        return periodPay;
    }

    public void setPeriodPay(BigDecimal periodPay) {
        this.periodPay = periodPay;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public BigDecimal getHospitalPay() {
        return hospitalPay;
    }

    public void setHospitalPay(BigDecimal hospitalPay) {
        this.hospitalPay = hospitalPay;
    }

    public BigDecimal getUseBalance() {
		return useBalance;
	}

	public void setUseBalance(BigDecimal useBalance) {
		this.useBalance = useBalance;
	}

	public BigDecimal getOnlinePay() {
        return onlinePay;
    }

    public void setOnlinePay(BigDecimal onlinePay) {
        this.onlinePay = onlinePay;
    }

    public String getCouponNum() {
        return couponNum;
    }

	public PolicyStatus getPolicyStatus() {
		return policyStatus;
	}

	public void setPolicyStatus(PolicyStatus policyStatus) {
		this.policyStatus = policyStatus;
	}

	public void setCouponNum(String couponNum) {
        this.couponNum = couponNum;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getRefundType() {
        return refundType;
    }

    public void setRefundType(int refundType) {
        this.refundType = refundType;
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
    
    public Date getServiceStartTime() {
		return serviceStartTime;
	}

	public void setServiceStartTime(Date serviceStartTime) {
		this.serviceStartTime = serviceStartTime;
	}
	
	

	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
