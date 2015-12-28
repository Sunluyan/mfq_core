package com.mfq.bean;

public class ProductImg {

    long id;
    long pid; // 产品id
    String img;     // 图片
    String desc; // 详情
    int index;        // index
    int flag;   // 标志
    
    public ProductImg(){
    	this.desc = "";
    	this.index = 0;
    	this.flag = 0;
    }
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getPid() {
		return pid;
	}
	public void setPid(long pid) {
		this.pid = pid;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
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
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
    
}