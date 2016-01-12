package com.mfq.constants;

/**
 * 支付类型
 * @author HUI
 *
 */
public enum OrderType {

	ONLINE(1, "订单"),
    RECHARGE(2, "充值"),
    REFUND(3, "还款"),
    FREEDOM(4,"随意单");
    
	
    int id;
    String desc;
    
    OrderType(int id, String desc){
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
    
    public static OrderType fromId(int id){
        for(OrderType status : OrderType.values()){
            if(status.getId() == id){
                return status;
            }
        }
        return null;
    }
}
