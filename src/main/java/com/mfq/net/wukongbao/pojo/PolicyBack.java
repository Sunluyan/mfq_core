package com.mfq.net.wukongbao.pojo;

/**
 * 退单
 * @author hui
 *
 */
public class PolicyBack {
	public class PolicyBackRequest{
		String channelOrderNo;
		String policyBackReason;
		public String getChannelOrderNo() {
			return channelOrderNo;
		}
		public void setChannelOrderNo(String channelOrderNo) {
			
			this.channelOrderNo = channelOrderNo;
		}
		public String getPolicyBackReason() {
			return policyBackReason;
		}
		public void setPolicyBackReason(String policyBackReason) {
			this.policyBackReason = policyBackReason;
		}
		
		
	}
	
	public class PolicyBackResponse{
		String channelOrderNo;
		String policyNo;
		String policyBackStatus;
		String remark;
		public String getChannelOrderNo() {
			return channelOrderNo;
		}
		public void setChannelOrderNo(String channelOrderNo) {
			this.channelOrderNo = channelOrderNo;
		}
		public String getPolicyNo() {
			return policyNo;
		}
		public void setPolicyNo(String policyNo) {
			this.policyNo = policyNo;
		}
		public String getPolicyBackStatus() {
			return policyBackStatus;
		}
		public void setPolicyBackStatus(String policyBackStatus) {
			this.policyBackStatus = policyBackStatus;
		}
		public String getRemark() {
			return remark;
		}
		public void setRemark(String remark) {
			this.remark = remark;
		}
		
		
	}
}
