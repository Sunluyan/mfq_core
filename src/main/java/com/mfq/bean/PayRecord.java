package com.mfq.bean;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.mfq.constants.CardType;
import com.mfq.constants.OrderType;
import com.mfq.constants.PayStatus;

/**
 * 支付记录
 * 
 * @author xingyongshan
 *
 */
public class PayRecord {

    long id; // 支付ID
    OrderType orderType; // 支付类型
    String tradeNo; // 交易流水号
    String orderNo; // 订单号
    BigDecimal amount; // 交易金额
    BigDecimal balance; // 使用余额
    BigDecimal present; // 使用赠送
    long uid; // user id
    String tpp; // 支付平台
    String bankCode; // 支付银行
    CardType cardType; // 卡类型，1借记卡，2信用卡
    String cardNo; // 银行卡号－可能不是全部－只有部分展示
    PayStatus status; // 充值状态
    Date payAt; // 支付时间
    Date callbackAt; // 回调时间
    Date updatedAt; // 最后更新时间

    public PayRecord() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public OrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}

	public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getPresent() {
        return present;
    }

    public void setPresent(BigDecimal present) {
        this.present = present;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getTpp() {
        return tpp;
    }

    public void setTpp(String tpp) {
        this.tpp = tpp;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public PayStatus getStatus() {
        return status;
    }

    public void setStatus(PayStatus status) {
        this.status = status;
    }

    public Date getPayAt() {
        return payAt;
    }

    public void setPayAt(Date payAt) {
        this.payAt = payAt;
    }

    public Date getCallbackAt() {
        return callbackAt;
    }

    public void setCallbackAt(Date callbackAt) {
        this.callbackAt = callbackAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
