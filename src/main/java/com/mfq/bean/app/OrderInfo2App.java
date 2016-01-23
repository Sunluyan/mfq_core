package com.mfq.bean.app;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mfq.bean.Hospital;
import com.mfq.bean.OrderFreedom;
import com.mfq.bean.coupon.Coupon;
import com.mfq.service.CouponService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mfq.bean.FinanceBill;
import com.mfq.bean.OrderInfo;
import com.mfq.constants.Constants;
import com.mfq.constants.OrderStatus;
import com.mfq.constants.PolicyStatus;
import com.mfq.utils.JsonUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class OrderInfo2App implements Serializable{
	@Resource
	CouponService couponService;

	// 返回值json中data内容：
	Long uid; // 用户
	String order_no; // 订单号
	String billNo;//分期编号
	BigDecimal price; // 总价
	Integer pay_type; // 订单类型；1分期付款，0全额付款
	Integer status; // 订单状态
	BigDecimal hospital_pay; //到院应付
	BigDecimal online_pay; //线上支付 
	BigDecimal use_balance; //使用余额部分
	BigDecimal pay_price;  //已支付金额
	Integer product_type; //产品类型 
	Integer period; // 期数
	Integer cur_period;//总期数
	Long product_id; // 产品ID
	String product_name; // 产品名称
	String couponNum; //优惠券
	String product_img; // 产品图片
	String product_url; //产品详情url
	String hospital_name; // 医院名称
	int policy_status;  //是否有保单
	Date order_time; //下单时间
	Date update_time; //更新时间
	String security_code; //验证码（到院出示）
	Date serviceStartTime;//预约就医时间
	Date everyMonthPayTime;//每月还款时间
	BigDecimal all_period;//分期总额
	BigDecimal month_period;//分期总额
	Integer financeState;//分期状态
	BigDecimal need_pay;//需要在线支付的金额
	BigDecimal refund_pay;//退款金额
	Date refund_time;  //退款时间
	
	

	public OrderInfo2App() {
		super();
	}
	
	//普通订单
	public OrderInfo2App(com.mfq.bean.Product product, OrderInfo orderInfo, FinanceBill financeBill , String hospital_name, PolicyStatus policy_status) {
		if(financeBill == null){
			financeBill = new FinanceBill();
		}
		this.uid = orderInfo.getUid();
		this.order_no = orderInfo.getOrderNo();
		this.price = (policy_status.getId() == 1)?orderInfo.getPrice().add(BigDecimal.valueOf(19)):orderInfo.getPrice();
		this.pay_type = orderInfo.getPayType();
		this.status = orderInfo.getStatus();
		this.period = orderInfo.getPeriod();
		this.product_id = orderInfo.getPid();
		this.order_time = orderInfo.getCreatedAt();
		this.update_time = orderInfo.getUpdatedAt();
		this.hospital_pay = orderInfo.getHospitalPay();
		this.online_pay = orderInfo.getOnlinePay();
		this.policy_status = policy_status.getId();
		if (orderInfo.getStatus() == OrderStatus.ORDER_OK.getValue()
				|| orderInfo.getStatus() == OrderStatus.PAY_OK.getValue()
				|| orderInfo.getStatus() == OrderStatus.APPLY_REFUND.getValue()
				|| orderInfo.getStatus() == OrderStatus.WAIT_REFUND.getValue()) {
			this.pay_price = orderInfo.getOnlinePay();
		} else {
			this.pay_price = BigDecimal.valueOf(0);
		}
		this.use_balance = orderInfo.getUseBalance();
		this.couponNum = orderInfo.getCouponNum();
		this.product_type = product.getType().getId();
		this.security_code = orderInfo.getSecurityCode();
		this.product_name = product.getName();
		this.product_img = product.getImg();
		this.product_url = "http://" + Constants.SITE_DOMAIN + "/product/app/detail?pid="+product.getId();
		this.hospital_name = hospital_name;
		this.serviceStartTime = orderInfo.getServiceStartTime();
		this.cur_period = financeBill.getCurPeriod();
		this.everyMonthPayTime = financeBill.getDueAt();
		this.billNo = financeBill.getBillNo();
		this.financeState = financeBill.getStatus();

		this.need_pay = orderInfo.getPrice().subtract(orderInfo.getPeriodPay());
		this.month_period = financeBill.getNewBalance();

		// TODO: 16/1/22    更改退款 
		this.refund_pay = BigDecimal.valueOf(0);
		this.refund_time = new Date();
	}

	//任意单
	public OrderInfo2App(OrderFreedom orderFreedom , Hospital hospital){
		/**String billNo;//分期编号
		 BigDecimal price; // 总价
		 Integer pay_type; // 订单类型；1分期付款，0全额付款
		 Integer status; // 订单状态
		 BigDecimal hospital_pay; //到院应付
		 BigDecimal online_pay; //线上支付
		 BigDecimal use_balance; //使用余额部分
		 BigDecimal pay_price;  //已支付金额
		 Integer product_type; //产品类型
		 Integer period; // 期数
		 Integer cur_period;//总期数
		 Long product_id; // 产品ID
		 String product_name; // 产品名称
		 String couponNum; //优惠券
		 String product_img; // 产品图片
		 String product_url; //产品详情url
		 String hospital_name; // 医院名称
		 int policy_status;  //是否有保单
		 Date order_time; //下单时间
		 Date update_time; //更新时间
		 String security_code; //验证码（到院出示）
		 Date serviceStartTime;//预约就医时间
		 Date everyMonthPayTime;//每月还款时间
		 BigDecimal all_period;//分期总额
		 BigDecimal month_period;//分期总额
		 Integer financeState;//分期状态
		 BigDecimal need_pay;//需要在线支付的金额
		 */
		this.uid = orderFreedom.getUid();
		this.order_no = orderFreedom.getOrderNo();
		this.pay_type = 1;
		this.status = orderFreedom.getStatus();
		this.online_pay = orderFreedom.getOnlinePay();
		this.product_name = orderFreedom.getProname();
		this.couponNum = orderFreedom.getCouponNum();
		this.hospital_name = hospital.getName();
		this.policy_status = orderFreedom.getPolicyStatus();
		this.order_time = orderFreedom.getCreateTime();
		this.update_time = orderFreedom.getUpdateTime();
		this.security_code = orderFreedom.getSecurityCode();
		this.serviceStartTime = orderFreedom.getServiceTime();
		if(PolicyStatus.AUDITING.getId() == orderFreedom.getPolicyStatus()){
			this.need_pay = orderFreedom.getPrice().add(BigDecimal.valueOf(19));
		}else{
			this.need_pay = orderFreedom.getPrice();
		}

		// TODO: 16/1/22  更改退款
		this.refund_pay = BigDecimal.valueOf(0);
		this.refund_time = new Date();
	}


	public Long getUid() {
		if(this.uid == null){
			return 0l;
		}
		return uid;
	}
	

	public void setUid(Long uid) {
		this.uid = uid;
	}


	public String getOrder_no() {
		if(this.order_no == null){
			return "";
		}
		return order_no;
	}


	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}


	public String getBillNo() {
		if(this.billNo==null){
			return "";
		}
		return billNo;
	}


	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}


	public BigDecimal getPrice() {
		if(this.price==null){
			return BigDecimal.valueOf(0);
		}
		return price;
	}


	public void setPrice(BigDecimal price) {
		this.price = price;
	}


	public Integer getPay_type() {
		if(this.pay_type==null){
			return 0;
		}
		return pay_type;
	}


	public void setPay_type(Integer pay_type) {
		this.pay_type = pay_type;
	}


	public Integer getStatus() {
		if(this.status==null){
			return 0;
		}
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	public BigDecimal getHospital_pay() {
		if(this.hospital_pay==null){
			return BigDecimal.valueOf(0);
		}
		return hospital_pay;
	}


	public void setHospital_pay(BigDecimal hospital_pay) {
		this.hospital_pay = hospital_pay;
	}


	public BigDecimal getOnline_pay() {
		if(this.online_pay==null){
			return BigDecimal.valueOf(0);
		}
		return online_pay;
	}


	public void setOnline_pay(BigDecimal online_pay) {
		this.online_pay = online_pay;
	}


	public BigDecimal getUse_balance() {
		if(this.use_balance==null){
			return BigDecimal.valueOf(0);
		}
		return use_balance;
	}


	public void setUse_balance(BigDecimal use_balance) {
		this.use_balance = use_balance;
	}


	public BigDecimal getPay_price() {
		if(this.pay_price==null){
			return BigDecimal.valueOf(0);
		}
		return pay_price;
	}


	public void setPay_price(BigDecimal pay_price) {
		this.pay_price = pay_price;
	}


	public Integer getProduct_type() {
		if(this.product_type==null){
			return 0;
		}
		return product_type;
	}


	public void setProduct_type(Integer product_type) {
		this.product_type = product_type;
	}


	public Integer getPeriod() {
		if(this.period==null){
			return 0;
		}
		return period;
	}


	public void setPeriod(Integer period) {
		
		this.period = period;
	}


	public Integer getCur_period() {
		if(this.cur_period == null){
			return 0;
		}
		return cur_period;
	}


	public void setCur_period(Integer cur_period) {
		this.cur_period = cur_period;
	}


	public Long getProduct_id() {
		if(this.product_id == null){
			return 0l;
		}
		return product_id;
	}


	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
	}


	public String getProduct_name() {
		if(this.product_name == null){
			return "";
		}
		return product_name;
	}


	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}


	public String getCouponNum() {
		if(this.couponNum == null){
			return "";
		}
		return couponNum;
	}


	public void setCouponNum(String couponNum) {
		this.couponNum = couponNum;
	}


	public String getProduct_img() {
		if(this.product_img == null){
			return "";
		}
		return product_img;
	}


	public void setProduct_img(String product_img) {
		this.product_img = product_img;
	}


	public String getProduct_url() {
		if(this.product_url == null){
			return "";
		}
		return product_url;
	}


	public void setProduct_url(String product_url) {
		this.product_url = product_url;
	}


	public String getHospital_name() {
		if(this.hospital_name == null){
			return "";
		}
		return hospital_name;
	}


	public void setHospital_name(String hospital_name) {
		this.hospital_name = hospital_name;
	}


	public int getPolicy_status() {
		
		return policy_status;
	}


	public void setPolicy_status(int policy_status) {
		this.policy_status = policy_status;
	}


	public Date getOrder_time() {
		if(this.order_time == null){
			return null;
		}
		return order_time;
	}


	public void setOrder_time(Date order_time) {
		this.order_time = order_time;
	}


	public Date getUpdate_time() {
		if(this.update_time == null){
			return null;
		}
		return update_time;
	}


	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}


	public String getSecurity_code() {
		if(this.security_code == null){
			return "";
		}
		return security_code;
	}


	public void setSecurity_code(String security_code) {
		this.security_code = security_code;
	}


	public Date getServiceStartTime() {
		if(this.serviceStartTime == null){
			return null;
		}
		return serviceStartTime;
	}


	public void setServiceStartTime(Date serviceStartTime) {
		this.serviceStartTime = serviceStartTime;
	}


	public Date getEveryMonthPayTime() {
		if(this.everyMonthPayTime == null){
			return null;
		}
		return everyMonthPayTime;
	}


	public void setEveryMonthPayTime(Date everyMonthPayTime) {
		this.everyMonthPayTime = everyMonthPayTime;
	}


	public BigDecimal getAll_period() {
		if(all_period == null){
			return BigDecimal.valueOf(0);
		}
		return all_period;
	}


	public void setAll_period(BigDecimal all_period) {
		this.all_period = all_period;
	}


	public Integer getFinanceState() {
		if(financeState == null){
			return 0;
		}
		return financeState;
	}


	public void setFinanceState(Integer financeState) {
		this.financeState = financeState;
	}


	public BigDecimal getNeed_pay() {
		if(this.need_pay == null){
			return BigDecimal.valueOf(0);
		}
		return need_pay;
	}


	public void setNeed_pay(BigDecimal need_pay) {
		
		this.need_pay = need_pay;
	}


	@Override
	public String toString() {
		return "OrderInfo2App [uid=" + uid + ", order_no=" + order_no
				+ ", billNo=" + billNo + ", price=" + price + ", pay_type="
				+ pay_type + ", status=" + status + ", hospital_pay="
				+ hospital_pay + ", online_pay=" + online_pay
				+ ", use_balance=" + use_balance + ", pay_price=" + pay_price
				+ ", product_type=" + product_type + ", period=" + period
				+ ", cur_period=" + cur_period + ", product_id=" + product_id
				+ ", product_name=" + product_name + ", couponNum=" + couponNum
				+ ", product_img=" + product_img + ", product_url="
				+ product_url + ", hospital_name=" + hospital_name
				+ ", policy_status=" + policy_status + ", order_time="
				+ order_time + ", update_time=" + update_time
				+ ", security_code=" + security_code + ", serviceStartTime="
				+ serviceStartTime + ", everyMonthPayTime=" + everyMonthPayTime
				+ ", all_period=" + all_period + ", month_period="
				+ month_period + ", financeState=" + financeState
				+ ", need_pay=" + need_pay + "]";
	}


	public BigDecimal getMonth_period() {
		return month_period;
	}


	public void setMonth_period(BigDecimal month_period) {
		this.month_period = month_period;
	}
	
	
	
	
	
	
	
	
}