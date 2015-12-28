package com.mfq.net.wukongbao.pojo;

public class QueryPolicy {
	String channelOrderNo;
	String productNo;
	QueryPolicyData policyContent;
	
	public class QueryPolicyData{
		String insuranceCode;
		String phone;
		String policyNo;
		int premium;
		int sumInsured;
		String policyBeginDate;
		String policyEndDate;
		String policyStatus;
		
		public String getInsuranceCode() {
			return insuranceCode;
		}
		public void setInsuranceCode(String insuranceCode) {
			this.insuranceCode = insuranceCode;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public String getPolicyNo() {
			return policyNo;
		}
		public void setPolicyNo(String policyNo) {
			this.policyNo = policyNo;
		}
		public int getPremium() {
			return premium;
		}
		public void setPremium(int premium) {
			this.premium = premium;
		}
		public int getSumInsured() {
			return sumInsured;
		}
		public void setSumInsured(int sumInsured) {
			this.sumInsured = sumInsured;
		}
		public String getPolicyBeginDate() {
			return policyBeginDate;
		}
		public void setPolicyBeginDate(String policyBeginDate) {
			this.policyBeginDate = policyBeginDate;
		}
		public String getPolicyEndDate() {
			return policyEndDate;
		}
		public void setPolicyEndDate(String policyEndDate) {
			this.policyEndDate = policyEndDate;
		}
		public String getPolicyStatus() {
			return policyStatus;
		}
		public void setPolicyStatus(String policyStatus) {
			this.policyStatus = policyStatus;
		}
		
		
	}
	
	public String getChannelOrderNo() {
		return channelOrderNo;
	}


	public void setChannelOrderNo(String channelOrderNo) {
		this.channelOrderNo = channelOrderNo;
	}


	public String getProductNo() {
		return productNo;
	}


	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}


	public QueryPolicyData getPolicyContent() {
		return policyContent;
	}


	public void setPolicyContent(QueryPolicyData policyContent) {
		this.policyContent = policyContent;
	}

}
