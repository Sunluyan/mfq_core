package com.mfq.handler;

import com.mfq.constants.Grade;

public class GradeEnumHandler extends EnumOrdinalTypeHandler<Grade> {

    public GradeEnumHandler(){
        super(Grade.class);
    }
    
    @Override
    protected Object getEnumValue(Grade parameter) {
        return parameter.getId();
    }

    @Override
    protected Grade getEnumFromValue(Object value) {
        return Grade.fromId((Integer) value);
    }
}