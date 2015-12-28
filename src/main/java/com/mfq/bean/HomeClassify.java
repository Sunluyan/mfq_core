package com.mfq.bean;

import org.apache.commons.lang.builder.ToStringBuilder;

public class HomeClassify {
	long id; // ID
	String name;
	String url;
	int index;
	
	public HomeClassify(long id, String name, String url){
		this.id = id;
		this.name = name;
		this.url = url;
		this.index = 0;

	}
	
	public HomeClassify(){
		
	}
	
	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}