package com.mfq.handler;

import com.mfq.constants.PayStatus;

public class PayStatusHandler extends EnumOrdinalTypeHandler<PayStatus> {

    public PayStatusHandler(){
        super(PayStatus.class);
    }
    
    @Override
    protected Object getEnumValue(PayStatus parameter) {
        return parameter.getValue();
    }

    @Override
    protected PayStatus getEnumFromValue(Object value) {
        return PayStatus.fromValue((Integer) value);
    }
}
