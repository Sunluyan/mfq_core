package com.mfq.constants;

import org.apache.commons.lang.StringUtils;

public enum Career {
    
    STUDENT(1, "student", "学生"),
    ELSE(0, "else", "其它");
    
    int id;
    String code;
    String desc;
    
    Career(int id, String code, String desc){
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
    
    public static Career fromCode(String code){
        for(Career career : Career.values()){
            if(StringUtils.equalsIgnoreCase(career.getCode(), code)){
                return career;
            }
        }
        return null;
    }
    
    public static Career fromId(int id){
        for(Career career : Career.values()){
            if(career.getId() == id){
                return career;
            }
        }
        return null;
    }
}
