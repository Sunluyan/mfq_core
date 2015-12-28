package com.mfq.constants;

/**
 * 单一帐单状态
 * @author xingyongshan
 *
 */
public enum BillStatus {

    PAY_OFF(-1, "已还款"),
    WAIT_PAY(1, "待还款"),
    OVER_TIME(2, "过期未还款");
    
	/**
	 * 同时包含待还款、过期未还款的状态码
	 */
	public static int NOT_PAY = 100;
	
    int id;
    String desc;
    
    BillStatus(int id, String desc){
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
    
    public static BillStatus fromId(int id){
        for(BillStatus status : BillStatus.values()){
            if(status.getId() == id){
                return status;
            }
        }
        return null;
    }
}
