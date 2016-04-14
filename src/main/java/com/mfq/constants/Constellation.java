package com.mfq.constants;

/**
 * Created by liuzhiguo1 on 16/3/30.
 */
public enum Constellation {
    UNSET(0, ""),
    Aries(1, "白羊座"),
    Taurus(2, "金牛座"),
    Gemini(3, "双子座"),
    Cancer(4, "巨蟹座"),
    Leo(5, "狮子座"),
    Virgo(6, "处女座"),
    Libra(7, "天秤座"),
    Scorpio(8, "天蝎座"),
    Sagittarius(9, "射手座"),
    Capricorn(10, "摩羯座"),
    Aquarius(11, "水瓶座"),
    Pisces(12, "双鱼座");

    /**
     * 同时包含待还款、过期未还款的状态码
     */
    public static int NOT_PAY = 100;

    int id;
    String desc;

    Constellation(int id, String desc){
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

    public static Constellation fromId(int id){
        for(Constellation status : Constellation.values()){
            if(status.getId() == id){
                return status;
            }
        }
        return null;
    }

    public static Constellation fromValue(String value){
        for(Constellation status : Constellation.values()){
            if(status.getDesc().equals(value)){
                return status;
            }
        }
        return null;
    }
}
