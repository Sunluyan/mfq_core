package com.mfq.handler;

import com.mfq.constants.CouponStatus;

public class CouponStatusHandler extends EnumOrdinalTypeHandler<CouponStatus> {

    public CouponStatusHandler(){
        super(CouponStatus.class);
    }
    
    @Override
    protected Object getEnumValue(CouponStatus parameter) {
        return parameter.getValue();
    }

    @Override
    protected CouponStatus getEnumFromValue(Object value) {
        return CouponStatus.fromValue((Integer)value);
    }
}