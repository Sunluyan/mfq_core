package com.mfq.bean.app;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ProductListItem2App {

	long id;     // 产品ID
	String name; // 产品名称
	BigDecimal price; // 团购价格
	BigDecimal marketPrice; //市场价
	int type; //类别
	long viewNum; // 浏览量
	long saleNum; // 销量
	String img; // 产品图片
	String url; //产品详情url
	BigDecimal subsidy; //补贴
	String city;   //城市
	Map<Integer, BigDecimal> fq;  //分期
	String hospitalId;
	String hosName;

	public String getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String getHosName() {
		return hosName;
	}

	public void setHosName(String hosName) {
		this.hosName = hosName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(BigDecimal marketPrice) {
		this.marketPrice = marketPrice;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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

	public BigDecimal getSubsidy() {
		return subsidy;
	}

	public void setSubsidy(BigDecimal subsidy) {
		this.subsidy = subsidy;
	}

	public long getViewNum() {
		return viewNum;
	}

	public void setViewNum(long viewNum) {
		this.viewNum = viewNum;
	}

	public long getSaleNum() {
		return saleNum;
	}

	public void setSaleNum(long saleNum) {
		this.saleNum = saleNum;
	}

	public Map<Integer, BigDecimal> getFq() {
		return fq;
	}

	public void setFq(Map<Integer, BigDecimal> fq) {
		this.fq = fq;
	}
	

}


