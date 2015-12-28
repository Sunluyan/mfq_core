package com.mfq.constants;

public enum PayType {

    FULL(0, "团购全款"),
    PART(1, "团购在线＋到院支付"),
    FINANCING(2, "分期付款"); // 会有部分金额在线支付的情形
    
    int id;
    String name;
    
    PayType(int id, String name){
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
    
    public static PayType fromId(int id){
        for(PayType t : PayType.values()){
            if(t.getId() == id){
                return t;
            }
        }
        return null;
    }
}