package com.mfq.bean.app;

import java.util.Date;

import com.mfq.constants.PolicyStatus;

public class PolicyListForApp {
	
	private Long id;

	private String product_name;
	
    private String order_no;
    
    private String hospital_name;
    
    private Date buy_time;
    
    private Date begin_time;
    
    private Date end_time;
    
    private String policy_status;
    
    private String content; //保障类容
    
    
	public PolicyListForApp(Long id, String product_name, String order_no, String hospital_name, Date buy_time,
			Date begin_time, Date end_time, PolicyStatus policy_status, String content) {
		super();
		this.id = id;
		this.product_name = product_name;
		this.order_no = order_no;
		this.hospital_name = hospital_name;
		this.buy_time = buy_time;
		this.begin_time = begin_time;
		this.end_time = end_time;
		this.policy_status = String.valueOf(policy_status.getId());
		this.content = "当您与医院发生医疗纠纷，在保障日期内美心安可以为您提供医疗诉讼服务，并为您提供相应的律师。";
		
	}
	
	public PolicyListForApp() {
		super();
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getOrder_no() {
		return order_no;
	}

	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}

	public String getHospital_name() {
		return hospital_name;
	}

	public void setHospital_name(String hospital_name) {
		this.hospital_name = hospital_name;
	}

	public Date getBuy_time() {
		return buy_time;
	}

	public void setBuy_time(Date buy_time) {
		this.buy_time = buy_time;
	}

	public Date getBegin_time() {
		return begin_time;
	}

	public void setBegin_time(Date begin_time) {
		this.begin_time = begin_time;
	}

	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPolicy_status() {
		return policy_status;
	}

	public void setPolicy_status(String policy_status) {
		this.policy_status = policy_status;
	}
	
}
