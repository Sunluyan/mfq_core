package com.mfq.constants;

public enum RefundType {
	
    DEFAULT(-1, "无退款"),
    BEFORE(0, "原路返回"),
    BALANCE(1, "退款至余额");
    
    int id;
    String name;
    
    RefundType(int id, String name){
        this.id = id;
        this.name = name;
    }

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
    
    public static RefundType fromId(int id){
        for(RefundType t : RefundType.values()){
            if(t.getId() == id){
                return t;
            }
        }
        return null;
    }
}
