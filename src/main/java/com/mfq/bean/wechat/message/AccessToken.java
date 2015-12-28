package com.mfq.bean.wechat.message;

import com.alibaba.fastjson.JSONObject;

public class AccessToken {
	private String token;
	private int expiresIn;
	private long createAt;

	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public int getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}
	public long getCreateAt() {
		return createAt;
	}
	public void setCreateAt(long createAt) {
		this.createAt = createAt;
	}
	
	public String toJSONString() {
		JSONObject json = new JSONObject();
		json.put("token", this.token);
		json.put("expiresIn", this.expiresIn);
		json.put("createAt", this.createAt);
		return json.toJSONString();
	}
}
