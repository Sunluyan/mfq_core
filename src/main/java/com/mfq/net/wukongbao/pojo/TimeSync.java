package com.mfq.net.wukongbao.pojo;

public class TimeSync {
	public class TimeSyncRequest {
		String channelOrderNo;
		String timeType;
		String orderTime;
		String arriveLateReason;
		public String getChannelOrderNo() {
			return channelOrderNo;
		}
		public void setChannelOrderNo(String channelOrderNo) {
			this.channelOrderNo = channelOrderNo;
		}
		public String getTimeType() {
			return timeType;
		}
		public void setTimeType(String timeType) {
			this.timeType = timeType;
		}
		public String getOrderTime() {
			return orderTime;
		}
		public void setOrderTime(String orderTime) {
			this.orderTime = orderTime;
		}
		public String getArriveLateReason() {
			return arriveLateReason;
		}
		public void setArriveLateReason(String arriveLateReason) {
			this.arriveLateReason = arriveLateReason;
		}
		
		
	}
	
	public class TimeSyncResponse{
		String channelOrderNo;
		String timeType;
		
		public String getChannelOrderNo() {
			return channelOrderNo;
		}
		public void setChannelOrderNo(String channelOrderNo) {
			this.channelOrderNo = channelOrderNo;
		}
		public String getTimeType() {
			return timeType;
		}
		public void setTimeType(String timeType) {
			this.timeType = timeType;
		}
		
	}
}
