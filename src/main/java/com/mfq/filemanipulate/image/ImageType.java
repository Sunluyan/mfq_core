package com.mfq.filemanipulate.image;

import org.apache.commons.lang.StringUtils;

public enum ImageType {

    AVATAR(1, "a", "头像"), 
    BANNER(2, "b", "Banner图片"), 
    LOGO(3, "l", "Logo图片"),
    PROD_PIC(4, "p", "产品图片"),
    HOSP_PIC(5, "h", "医院图片"),
    CONT_PIC(6, "c", "内容图片"),
    AUTH(7, "at", "认证图片");
    
    int id;
    String flag;
    String desc;
    
    ImageType(int id, String flag, String desc){
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

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    
    public static ImageType fromFlag(String type){
        for(ImageType img : ImageType.values()){
            if(StringUtils.equalsIgnoreCase(img.getFlag(), type)){
                return img;
            }
        }
        return null;
    }
    
    public static ImageType fromId(int id){
        for(ImageType img : ImageType.values()){
            if(img.getId() == id){
                return img;
            }
        }
        return null;
    }
}
