package com.mfq.constants;

/**
 * Created by liuzhiguo1 on 16/3/30.
 */
public enum BloodType {
    UNSET(0,"未设置"),
    A(1,"A"),
    B(2,"B"),
    AB(3,"AB"),
    O(4,"O");

    int type;
    String value;
    BloodType(int type ,String value) {
        this.type = type;
        this.value = value;
    }

    public int getId() {
        return type;
    }

    public void setId(int id) {
        this.type = id;
    }

    public String getDesc() {
        return value;
    }

    public void setDesc(String desc) {
        this.value = desc;
    }

    public static BloodType fromId(int id){
        for(BloodType status : BloodType.values()){
            if(status.getId() == id){
                return status;
            }
        }
        return null;
    }

    public static BloodType fromValue(String value){
        for(BloodType status : BloodType.values()){
            if(status.getDesc() == value){
                return status;
            }
        }
        return null;
    }

}
