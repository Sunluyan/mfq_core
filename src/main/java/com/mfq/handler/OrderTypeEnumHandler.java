package com.mfq.handler;

import com.mfq.constants.OrderType;

public class OrderTypeEnumHandler extends EnumOrdinalTypeHandler<OrderType> {

    public OrderTypeEnumHandler(){
        super(OrderType.class);
    }
    
    @Override
    protected Object getEnumValue(OrderType parameter) {
        return parameter.getId();
    }

    @Override
    protected OrderType getEnumFromValue(Object value) {
        return OrderType.fromId((Integer) value);
    }
}
