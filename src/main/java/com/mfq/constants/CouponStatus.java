package com.mfq.constants;

/**
 * 优惠券状态
 * @author xingyongshan
 *
 */
public enum CouponStatus {
    INIT(0, "初始化"), 
    FREEZE(1, "冻结"), // 下单的为冻结
    USED(2, "已使用"), // 已使用的为已使用
    ABANDON(3, "已废弃");
    
    int value;
    String desc;

    CouponStatus(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }
    
    public int getValue() {
        return value;
    }
    
    public String getDesc() {
        return desc;
    }
    
    public static CouponStatus fromValue(int value) {
        for (CouponStatus action : CouponStatus.values()) {
            if (value == action.value) {
                return action;
            }
        }
        return null;
    }
}