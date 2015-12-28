package com.mfq.handler;

import com.mfq.constants.PolicyStatus;

public class PolicyStatusEnumHandler extends EnumOrdinalTypeHandler<PolicyStatus> {

    public PolicyStatusEnumHandler(){
        super(PolicyStatus.class);
    }
    
    @Override
    protected Object getEnumValue(PolicyStatus parameter) {
        return parameter.getId();
    }

    @Override
    protected PolicyStatus getEnumFromValue(Object value) {
        return PolicyStatus.fromId((Integer) value);
    }
}
