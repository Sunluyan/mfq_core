package com.mfq.handler;

import com.mfq.bean.sms.SMSSendStatus;

public class SMSSendStatusEnumHandler extends EnumOrdinalTypeHandler<SMSSendStatus>{

    public SMSSendStatusEnumHandler(){
        super(SMSSendStatus.class);
    }

    @Override
    protected Object getEnumValue(SMSSendStatus parameter) {
        return parameter.getValue();
    }

    @Override
    protected SMSSendStatus getEnumFromValue(Object value) {
        return SMSSendStatus.fromValue((Integer) value);
    }
}