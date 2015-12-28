package com.mfq.constants;

import org.apache.commons.lang.StringUtils;

public enum ProductFlag {

	RECOMMEND(1, "recommend", "首页推荐"),
    ELSE(0, "else", "其它");
    
    int id;
    String code;
    String desc;
    
    ProductFlag(int id, String code, String desc){
        this.id = id;
        this.code = code;
        this.desc = desc;
    }
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
    
    public static ProductFlag fromCode(String code){
        for(ProductFlag productFlag : ProductFlag.values()){
            if(StringUtils.equalsIgnoreCase(productFlag.getCode(), code)){
                return productFlag;
            }
        }
        return null;
    }
    
    public static ProductFlag fromId(int id){
        for(ProductFlag productFlag : ProductFlag.values()){
            if(productFlag.getId() == id){
                return productFlag;
            }
        }
        return null;
    }
}
