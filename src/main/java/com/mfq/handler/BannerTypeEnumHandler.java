package com.mfq.handler;

import com.mfq.constants.BannerType;

public class BannerTypeEnumHandler extends EnumOrdinalTypeHandler<BannerType> {

    public BannerTypeEnumHandler(){
        super(BannerType.class);
    }
    
    @Override
    protected Object getEnumValue(BannerType parameter) {
        return parameter.getId();
    }

    @Override
    protected BannerType getEnumFromValue(Object value) {
        return BannerType.fromId((Integer) value);
    }
}
