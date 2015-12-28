package com.mfq.bean.app;

import java.math.BigDecimal;
import java.util.Date;

public class ProductListItem2App2 {

	long id;     // 产品ID
	String name; // 产品名称
	BigDecimal price; // 团购价格
	BigDecimal marketPrice; //市场价
	BigDecimal pPrice; // 分期基准价格
	int type; //类别
	int pNum; // 分期基准数
	Date dateStart; // 有效期开始时间
	Date dateEnd; // 有效期结束时间
	long viewNum; // 浏览量
	long saleNum; // 销量
	String img; // 产品图片
	String url; //产品详情url

	String city;  //城市名
	String hospitalName; // 医院名字

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

	public BigDecimal getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(BigDecimal marketPrice) {
		this.marketPrice = marketPrice;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getpPrice() {
		return pPrice;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setpPrice(BigDecimal pPrice) {
		this.pPrice = pPrice;
	}

	public int getpNum() {
		return pNum;
	}

	public void setpNum(int pNum) {
		this.pNum = pNum;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
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

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
}
