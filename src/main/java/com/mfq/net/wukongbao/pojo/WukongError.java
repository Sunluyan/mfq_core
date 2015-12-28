package com.mfq.net.wukongbao.pojo;

public enum WukongError {

	SUCCESS(0, "SUCCESS","成功",""),
	SIGN_FAILED(1, "SIGN_FAILED","签名失败","检查密钥和签名串是否正确"),
	INVALID_APPKEY(1,"INVALID_APPKEY","无效appKey","检查APPKEY是否正确"),
	INVALID_METHOD(1, "INVALID_METHOD","无效服务方法名","检查serviceName是否正确"),
	TIME_OUT(1, "TIME_OUT","超时","检查timestamp是否和系统时间相差很大"),
	INVALID_PARAM(1, "INVALID_PARAM","参数无效","检查密钥和签名串是否正确"),
	INVALID_SERVICE_ERROR(1, "INVALID_SERVICE_ERROR","调用服务错误","");

	int id;
	String name;
	String desc;
	String solve;  //解决方案 
	
	WukongError(int id, String name, String desc, String solve){
		this.id = id;
		this.name = name;
		this.desc = desc;
		this.solve = solve;
	}
}
