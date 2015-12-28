package com.mfq.handler;

import com.mfq.bean.user.Status;

public class UserStatusEnumHandler extends EnumOrdinalTypeHandler<Status>{

    public UserStatusEnumHandler(){
        super(Status.class);
    }

    @Override
    protected Object getEnumValue(Status parameter) {
        return parameter.getValue();
    }

    @Override
    protected Status getEnumFromValue(Object value) {
        return Status.fromValue((Integer) value);
    }
}