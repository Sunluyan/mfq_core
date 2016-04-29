package com.mfq.constants;

public enum CouponType {

    HOSPITALS(1,"医院类的优惠券"),
    PRODUCTS(2,"产品类的优惠券"),
    CLASSIFY(3,"类别型的优惠券");


    int id;
    String desc;

    CouponType(int id, String desc){
        this.id = id;
        this.desc = desc;
    }
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
    public static CouponType fromId(int id){
        for(CouponType status : CouponType.values()){
            if(status.getId() == id){
                return status;
            }
        }
        return null;
    }
    
}
