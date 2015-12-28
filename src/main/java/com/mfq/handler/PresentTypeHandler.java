package com.mfq.handler;

import com.mfq.constants.PresentType;

public class PresentTypeHandler extends EnumOrdinalTypeHandler<PresentType>{
    
    public PresentTypeHandler(){
        super(PresentType.class);
    }

    @Override
    protected Object getEnumValue(PresentType parameter) {
        return parameter.getId();
    }

    @Override
    protected PresentType getEnumFromValue(Object value) {
        return PresentType.fromId((Integer) value);
    }
}