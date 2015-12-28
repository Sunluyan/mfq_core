package com.mfq.bean;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.mfq.constants.BannerType;

public class HomeBanner {
	long id; // ID
	String img;
	String name;
	String url;
	BannerType pType;
	long pId;
	int index;
	int type;
	int flag;
	Date createdAt;
	
	public HomeBanner(long id, String img, String name, BannerType pType, long pId, String url){
		this.id = id;
		this.img = img;
		this.name = name;
		this.pType = pType;
		this.pId = pId;
		this.url = url;
		this.index = 0;
		this.type = 0;
		this.flag = 0;
		this.createdAt = new Date();
	}
	
	public HomeBanner(){
		
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

	public BannerType getpType() {
		return pType;
	}

	public void setpType(BannerType pType) {
		this.pType = pType;
	}

	public long getpId() {
		return pId;
	}

	public void setpId(long pId) {
		this.pId = pId;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}