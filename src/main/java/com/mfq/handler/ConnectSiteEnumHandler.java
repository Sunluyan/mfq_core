package com.mfq.handler;

import com.mfq.bean.passport.ConnectSite;

public class ConnectSiteEnumHandler extends EnumOrdinalTypeHandler<ConnectSite> {

    public ConnectSiteEnumHandler(){
        super(ConnectSite.class);
    }
    
    @Override
    protected Object getEnumValue(ConnectSite parameter) {
        return parameter.getValue();
    }

    @Override
    protected ConnectSite getEnumFromValue(Object value) {
        return ConnectSite.fromValue((Integer)value);
    }
}