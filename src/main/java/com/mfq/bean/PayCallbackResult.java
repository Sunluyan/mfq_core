package com.mfq.bean;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.mfq.constants.CardType;
import com.mfq.payment.PayAPIType;

public class PayCallbackResult {

    public PayAPIType apiType;
    public String merchantaccount; // 商户账户
    public String tradeNo;     // 交易流水号
    public String orderNo;     // 交易订单，美分期自身的订单号
    public BigDecimal amount;  // 支付金额，精确到元，有些单位是分需要做转换为元
    public String bankCode;    // 支付卡所属银行的编码，如ICBC
    public String bank;        // 支付卡所属银行的名称
    public CardType cardType = CardType.UNDEFINED;       // 支付卡的类型，0未定义，1为借记卡，2为信用卡
    public String lastno;       // 支付卡卡号后4位
    public int status;          // 订单状态    int 1：成功
    public Date payAt;          // 支付完成时间
    public String sign;         // 签名
    
    public PayAPIType getApiType() {
        return apiType;
    }
    public void setApiType(PayAPIType apiType) {
        this.apiType = apiType;
    }
    public String getMerchantaccount() {
        return merchantaccount;
    }
    public void setMerchantaccount(String merchantaccount) {
        this.merchantaccount = merchantaccount;
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
    public String getBankCode() {
        return bankCode;
    }
    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }
    public String getBank() {
        return bank;
    }
    public void setBank(String bank) {
        this.bank = bank;
    }
    public CardType getCardType() {
        return cardType;
    }
    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }
    public String getLastno() {
        return lastno;
    }
    public void setLastno(String lastno) {
        this.lastno = lastno;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public Date getPayAt() {
        return payAt;
    }
    public void setPayAt(Date payAt) {
        this.payAt = payAt;
    }
    public String getSign() {
        return sign;
    }
    public void setSign(String sign) {
        this.sign = sign;
    }
    
    @Override
    public String toString(){
        return ToStringBuilder.reflectionToString(this);
    }
}