package com.mfq.bean.app;

import java.math.BigDecimal;

import com.mfq.bean.FinanceBill;
import com.mfq.bean.OrderInfo;
import com.mfq.bean.Product;
import com.mfq.utils.DateUtil;

public class FinanceBill2App {

    long id; // bill ID
    long uid; // 用户ID
    String order_no; // 订单号
    String bill_no; //账单号
    long pid; //产品ID
    String pname; // 产品名称
    BigDecimal amount; // 本期应还金额
    BigDecimal late_fee; // 滞纳金
    int cp; // 当前期数
    int ap; //总期数
    int status; // 帐单状态
    String trade_at; // 交易日期
    String charge_at; // 记账日期
    String bill_at; // 帐单生成时间
    String due_at; // 帐单应还款日期
    String pay_at; // 实际还款日期
    
    public FinanceBill2App(String billNo, BigDecimal newBalance, Integer curPeriod, boolean isNow, int billStatus){
        
    }
    
    public FinanceBill2App(FinanceBill bill, OrderInfo order, Product product){
        this.id = bill.getId();
        this.uid = bill.getUid();
        this.bill_no = bill.getBillNo();
        this.order_no = bill.getOrderNo();
        this.pid = order.getPid();
        this.pname = product.getName();
        this.amount = bill.getNewBalance();
        this.late_fee = bill.getLateFee();
        this.cp = bill.getCurPeriod();
        this.ap = bill.getAllPeriod();
        this.status = bill.getStatus();
        this.trade_at = DateUtil.formatLong(bill.getTradeAt());
        this.charge_at = DateUtil.formatLong(bill.getChargeAt());
        this.bill_at = DateUtil.formatLong(bill.getBillAt());
        this.due_at = DateUtil.formatLong(bill.getDueAt());
        if(bill.getPayAt() != null) {
            this.pay_at = DateUtil.formatLong(bill.getPayAt());
        }else {
            this.pay_at = "";
        }
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public long getUid() {
        return uid;
    }
    public void setUid(long uid) {
        this.uid = uid;
    }
    public String getBill_no() {
		return bill_no;
	}

	public void setBill_no(String bill_no) {
		this.bill_no = bill_no;
	}

	public String getOrder_no() {
        return order_no;
    }
    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }
    public long getPid() {
        return pid;
    }
    public void setPid(long pid) {
        this.pid = pid;
    }
    public String getPname() {
        return pname;
    }
    public void setPname(String pname) {
        this.pname = pname;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public BigDecimal getLate_fee() {
        return late_fee;
    }

    public void setLate_fee(BigDecimal late_fee) {
        this.late_fee = late_fee;
    }

    public int getCp() {
        return cp;
    }
    public void setCp(int cp) {
        this.cp = cp;
    }
    public int getAp() {
        return ap;
    }
    public void setAp(int ap) {
        this.ap = ap;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }

    public String getTrade_at() {
        return trade_at;
    }

    public void setTrade_at(String trade_at) {
        this.trade_at = trade_at;
    }

    public String getCharge_at() {
        return charge_at;
    }

    public void setCharge_at(String charge_at) {
        this.charge_at = charge_at;
    }

    public String getBill_at() {
        return bill_at;
    }

    public void setBill_at(String bill_at) {
        this.bill_at = bill_at;
    }

    public String getDue_at() {
        return due_at;
    }

    public void setDue_at(String due_at) {
        this.due_at = due_at;
    }

    public String getPay_at() {
        return pay_at;
    }

    public void setPay_at(String pay_at) {
        this.pay_at = pay_at;
    }
    
}