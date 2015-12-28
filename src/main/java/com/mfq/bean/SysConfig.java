package com.mfq.bean;

public class SysConfig {

	private long id;
	private String key;
	private String value;
	private String desc;
	private int index;
	
	public SysConfig(){
		
	}
	public SysConfig(String key, String value){
		this.key = key;
		this.value = value;
		this.desc = "";
		this.index = 0;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	
}
