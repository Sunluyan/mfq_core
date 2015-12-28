package com.mfq.handler;

import com.mfq.constants.BillStatus;

public class BillStatusEnumHandler extends EnumOrdinalTypeHandler<BillStatus>{
    
    public BillStatusEnumHandler(){
        super(BillStatus.class);
    }

    @Override
    protected Object getEnumValue(BillStatus parameter) {
        return parameter.getId();
    }

    @Override
    protected BillStatus getEnumFromValue(Object value) {
        return BillStatus.fromId((Integer) value);
    }
}