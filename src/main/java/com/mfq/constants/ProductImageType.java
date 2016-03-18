package com.mfq.constants;

/**
 * Created by liuzhiguo1 on 16/3/14.
 * 产品相关图片的枚举
 */
public enum ProductImageType {
    MAINIMAGE(1,"产品banner图片"),
    BEFORE(2,"手术前"),
    AFTER(3,"手术后"),
    BEAUTIFUL(4,"美丽日记其他内容"),
    SURGERY(5,"手术纪实"),
    DETAIL(6,"产品详情");

    int id;
    String name;

    ProductImageType(int id, String name){
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
