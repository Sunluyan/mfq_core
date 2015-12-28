package com.mfq.constants;

import java.math.BigDecimal;

/**
 * 赠送类型
 * @author xingyongshan
 *
 */
public enum PresentType {

    RECHARGE(1, "充值", new BigDecimal(0)),
    WECHAT(2, "微信绑定", new BigDecimal(10000));
    
    int id;
    String desc;
    BigDecimal limit;
    
    PresentType(int id, String desc, BigDecimal limit){
        this.id = id;
        this.desc = desc;
        this.limit = limit;
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
    
    public BigDecimal getLimit() {
        return limit;
    }

    public void setLimit(BigDecimal limit) {
        this.limit = limit;
    }

    public static PresentType fromId(int id){
        for(PresentType t : PresentType.values()){
            if(t.getId() == id){
                return t;
            }
        }
        return null;
    }
    
}
