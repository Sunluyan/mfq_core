package com.mfq.bean;

public class ProductClassify {

    int id;
    String name;
    String flag;
    String desp;
    int rootId; 
    String icon; //icon图片
    String hgImage; //分类背景图片
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getFlag() {
        return flag;
    }
    public void setFlag(String flag) {
        this.flag = flag;
    }
    public String getDesp() {
        return desp;
    }
    public void setDesp(String desp) {
        this.desp = desp;
    }
    public int getRootId() {
        return rootId;
    }
    public void setRootId(int rootId) {
        this.rootId = rootId;
    }
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getHgImage() {
		return hgImage;
	}
	public void setHgImage(String hgImage) {
		this.hgImage = hgImage;
	}
}
