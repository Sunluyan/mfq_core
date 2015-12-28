package com.mfq.handler;

import com.mfq.bean.sms.SMSSendType;

public class SMSSendTypeEnumHandler extends EnumOrdinalTypeHandler<SMSSendType>{

    public SMSSendTypeEnumHandler(){
        super(SMSSendType.class);
    }

    @Override
    protected Object getEnumValue(SMSSendType parameter) {
        return parameter.getValue();
    }

    @Override
    protected SMSSendType getEnumFromValue(Object value) {
        return SMSSendType.fromValue((Integer) value);
    }
}