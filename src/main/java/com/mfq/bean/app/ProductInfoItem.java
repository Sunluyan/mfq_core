package com.mfq.bean.app;

import java.math.BigDecimal;

import com.mfq.bean.Hospital;
import com.mfq.bean.Product;

public class ProductInfoItem {

	// 返回值json中data内容：
	private long id;
	private String name; //产品名称
	private String img;  //产品图片
	private BigDecimal price;  //价格
	private long totalNum;
	private long remainNum;
	private BigDecimal marketPrice; //市场价
	private String hospitalName; //医院
	
	public ProductInfoItem(Product product, Hospital hospital){
		this.id = product.getId();
		this.name = product.getName();
		this.price = product.getPrice();
		this.marketPrice = product.getMarketPrice();
		this.totalNum = product.getTotalNum();
		this.remainNum = product.getRemainNum();
		this.img = product.getImg();
		this.hospitalName = hospital.getName();
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
	
	public long getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(long totalNum) {
		this.totalNum = totalNum;
	}

	public long getRemainNum() {
		return remainNum;
	}

	public void setRemainNum(int remainNum) {
		this.remainNum = remainNum;
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

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	
}