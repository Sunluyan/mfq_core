package com.mfq.net.wukongbao.pojo;

public enum WukongInsureStatus {

	DEFAULT(0, "未申请保险","未申请保险"),
	INSURE_APPLY(1, "SIGN_FAILED","保单申请中"),
	INSURE_SUCCESS(2,"INVALID_APPKEY","保单创建成功"),
	INVALID_FAILD(3, "INVALID_METHOD","保单创建失败"),
	COMPLAINT(4,"COMPLAINT","申请投诉"),
	DUE(5,"DUE","保单过期");//申请投诉

	int id;
	String code;
	String desc;

	WukongInsureStatus(int id, String code, String desc){
		this.id = id;
		this.code = code;
		this.desc = desc;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
