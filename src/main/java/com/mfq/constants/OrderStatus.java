package com.mfq.constants;

public enum OrderStatus {
	
	BOOK_OK(0,"下单成功等待支付", "下单已完成，请尽快付款。"),
	PAY_OK(1,"支付成功", "您已下单完成，请合理安排时间前去医院。"), // 支付成功后
	ORDER_OK(2,"订单完成", "您的订单已完成，敬请留意，欢迎下次使用。"), // 属于最终状态
	CANCEL_OK(3,"订单取消", "订单已取消，如果您还需要预订，请返回美分期再次查询预订。"), // 属于最终状态
	APPLY_CHANGE(4,"变更申请中", "您已申请更改订单，客服会在30分钟内与您联系，请保持手机畅通。"),
	CHANGE_OK(5,"变更完成", "您的订单已完成变更，请放心前去医院。"),  // 支付成功后
	APPLY_REFUND(6,"退款待确认", "您已申请退款，客服会在1个工作日内与您联系，请保持手机畅通。"),
	WAIT_REFUND(7,"待退款", "您的退款申请已经受理，客服将在72小时内进行退款操作，结果会通过短信通知您，请保持手机畅通。"),
	REFUND_OK(8,"退款完成", "已完成退款操作。"), // 属于最终状态
	FAIL_REFUND(9,"退款失败","退款失败"),
	NONE(100, "", "");
	
	int value;
	String name;
	String desc;
	
	OrderStatus(int value, String name, String desc){
		this.value = value;
		this.name = name;
		this.desc = desc;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public static OrderStatus fromValue(int value){
		for(OrderStatus status : OrderStatus.values()){
			if(status.value == value){
				return status;
			}
		}
		return null;
	}
}
