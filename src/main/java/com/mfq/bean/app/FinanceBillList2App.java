package com.mfq.bean.app;

import com.google.common.collect.Lists;
import com.mfq.bean.FinanceBill;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class FinanceBillList2App implements Serializable{
	
	private String product_name; //产品名称
	private Date order_time; //订单时间
	private BigDecimal order_price; //订单金额
	private int finance_status;  //分期状态   还款中  1   已还清 2
	private int installments_num; //分期数
	private int now_installment;  //当前分期数
	private String order_no; //订单号
	private BigDecimal finance_principal; //分期本金
	private BigDecimal finance_service; //分期服务费
	private BigDecimal finance_total; //合计应还
	private BigDecimal has_pay;  //已还
	private BigDecimal wait_pay; //待还
	private List<Bill2App> bills;


	public FinanceBillList2App(String productName, Date orderTime, BigDecimal orderPrice, int financeStatus, int installmentsNum, int nowInstallment,
							   String orderNo, BigDecimal financePrincipal, BigDecimal financeService, BigDecimal financeTotal, BigDecimal hasPay, BigDecimal waitPay,
							   List<FinanceBill> bills){
		this.product_name = productName;
		if(orderTime != null){
			this.order_time = orderTime;
		}
		this.order_price = orderPrice;
		this.finance_status = financeStatus;
		this.installments_num = installmentsNum;
		this.now_installment = nowInstallment;
		this.order_no = orderNo;
		this.finance_principal = financePrincipal;
		this.finance_service = financeService;
		this.finance_total = financeTotal;
		this.has_pay = hasPay;
		this.wait_pay = waitPay;

		if(bills!=null){
			List<Bill2App> billapps = Lists.newArrayList();
			for(FinanceBill bill :bills){

				boolean isNow = false;
				if(now_installment == bill.getCurPeriod()){
					isNow = true;
				}
				int bs = bill.getStatus();
				Bill2App app = new Bill2App(bill.getBillNo(), bill.getNewBalance(), bill.getCurPeriod(), isNow, bs, bill.getDueAt());
				billapps.add(app);
			}
			this.bills = billapps;
		}else {
			this.bills = null;
		}

	}

	class Bill2App{
		String bill_no;  //账单号
		BigDecimal bill_price;  //账单金额
		int bill_num; //账单期
		boolean is_now;  //是否是当前账单
		int bill_status;  //账单状态  已付 1  待付 2
		Date repay_time; //还款时间


		public Bill2App(String billNo, BigDecimal billPrice, int billNum, boolean isNow, int billStatus, Date repayTime){
			this.bill_no = billNo;
			this.bill_price = billPrice;
			this.bill_num = billNum;
			this.is_now = isNow;
			this.bill_status = billStatus;
			if(repayTime!=null){
				this.repay_time = repayTime;
			}
		}

		public List<Bill2App> Bill2App(List<FinanceBill> bills){

			List<Bill2App> billapps = Lists.newArrayList();
			for(FinanceBill bill :bills){

				boolean isNow = true;
				int billStatus = bill.getStatus();
				Bill2App app = new Bill2App(bill.getBillNo(), bill.getNewBalance(), bill.getCurPeriod(), isNow, billStatus, bill.getDueAt());
				billapps.add(app);
			}

			return billapps;
		}

		public String getBill_no() {
			return bill_no;
		}

		public void setBill_no(String bill_no) {
			this.bill_no = bill_no;
		}

		public BigDecimal getBill_price() {
			return bill_price;
		}

		public void setBill_price(BigDecimal bill_price) {
			this.bill_price = bill_price;
		}

		public int getBill_num() {
			return bill_num;
		}

		public void setBill_num(int bill_num) {
			this.bill_num = bill_num;
		}

		public boolean is_now() {
			return is_now;
		}

		public void setIs_now(boolean is_now) {
			this.is_now = is_now;
		}

		public int getBill_status() {
			return bill_status;
		}

		public void setBill_status(int bill_status) {
			this.bill_status = bill_status;
		}

		public Date getRepay_time() {
			return repay_time;
		}

		public void setRepay_time(Date repay_time) {
			this.repay_time = repay_time;
		}
	}


	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public Date getOrder_time() {
		return order_time;
	}

	public void setOrder_time(Date order_time) {
		this.order_time = order_time;
	}

	public List<Bill2App> getBills() {
		return bills;
	}

	public void setBills(List<Bill2App> bills) {
		this.bills = bills;
	}

	public String getOrder_no() {
		return order_no;
	}

	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}

	public BigDecimal getFinance_principal() {
		return finance_principal;
	}

	public void setFinance_principal(BigDecimal finance_principal) {
		this.finance_principal = finance_principal;
	}

	public BigDecimal getFinance_service() {
		return finance_service;
	}

	public void setFinance_service(BigDecimal finance_service) {
		this.finance_service = finance_service;
	}

	public BigDecimal getFinance_total() {
		return finance_total;
	}

	public void setFinance_total(BigDecimal finance_total) {
		this.finance_total = finance_total;
	}

	public BigDecimal getHas_pay() {
		return has_pay;
	}

	public void setHas_pay(BigDecimal has_pay) {
		this.has_pay = has_pay;
	}

	public BigDecimal getWait_pay() {
		return wait_pay;
	}

	public void setWait_pay(BigDecimal wait_pay) {
		this.wait_pay = wait_pay;
	}

	public BigDecimal getOrder_price() {
		return order_price;
	}

	public void setOrder_price(BigDecimal order_price) {
		this.order_price = order_price;
	}

	public int getFinance_status() {
		return finance_status;
	}

	public void setFinance_status(int finance_status) {
		this.finance_status = finance_status;
	}

	public int getInstallments_num() {
		return installments_num;
	}

	public void setInstallments_num(int installments_num) {
		this.installments_num = installments_num;
	}

	public int getNow_installment() {
		return now_installment;
	}

	public void setNow_installment(int now_installment) {
		this.now_installment = now_installment;
	}
}