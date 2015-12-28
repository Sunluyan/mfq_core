package com.mfq.constants;

public enum ProductType {

    NORMAL(0,"普通产品"),
	SPECIAL(1, "特价产品"),
    SECKILLING(2, "秒杀产品");
    
    int id;
    String name;
    
    ProductType(int id, String name){
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
    
    public static ProductType fromId(int id){
        for(ProductType t : ProductType.values()){
            if(t.getId() == id){
                return t;
            }
        }
        return null;
    }
}