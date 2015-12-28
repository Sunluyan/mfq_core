package com.mfq.bean.app;

import com.mfq.constants.Constants;
import com.mfq.utils.DateUtil;

public class ProductInfo2App {

	// 返回值json中data内容：
	Product product;
	Hospital hospital;

	public ProductInfo2App() {

	}

	public ProductInfo2App(com.mfq.bean.Product product, com.mfq.bean.ProductDetail pd,
			com.mfq.bean.Hospital hospital) {
		this.product = new ProductInfo2App.Product(product, pd);
		this.hospital = new ProductInfo2App.Hospital(hospital);
	}

	public class Product {
		long pid; // 产品ID
		String name; // 产品名称
		String date_start; // 有效期开始时间
		String date_end; // 有效期截止时间
		String consume_step; // 消费流程
		String reserve; // 如何预约
		String detail; // 产品详情
		String url;    //产品详情url 

		public Product() {
		}

		public Product(com.mfq.bean.Product product, com.mfq.bean.ProductDetail detail) {
			this.pid = product.getId();
			this.name = product.getName();
			this.date_start = DateUtil.formatLong(product.getDateStart());
			this.date_end = DateUtil.formatLong(product.getDateEnd());
			this.consume_step = detail.getConsumeStep();
			this.reserve = detail.getReserve();
			this.detail = detail.getBody();
			this.url = "http://" + Constants.SITE_DOMAIN + "/product/app/detail?pid="+product.getId();
		}
	}

	public class Hospital {
		long hospital_id; // 医院ID
		String hospital_name; // 医院名称
		String hospital_img; // 图片
		String hospital_address; // 医院地址

		public Hospital() {
		}

		public Hospital(com.mfq.bean.Hospital hospital) {
			this.hospital_id = hospital.getId();
			this.hospital_name = hospital.getName();
			this.hospital_img = hospital.getImg();
			this.hospital_address = hospital.getAddress();
		}
	}
}