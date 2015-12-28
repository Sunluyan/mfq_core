package com.mfq.constants;

/**
 * banner flag
 * @author hui
 *
 */
public enum BannerType {
	
	DEFAULT(0, "default","默认"),
    DELETE(1, "delete","已删除"),
    PRODUCT(2, "product","产品"),
    ACTIVITY(3, "activity", "活动");
	
    int id;
    String flag;
    String desc;
    
    BannerType(int id, String flag, String desc){
        this.id = id;
        this.flag = flag;
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
    
    public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public void setDesc(String desc) {
        this.desc = desc;
    }
    
    public static BannerType fromId(int id){
        for(BannerType status : BannerType.values()){
            if(status.getId() == id){
                return status;
            }
        }
        return null;
    }
}
