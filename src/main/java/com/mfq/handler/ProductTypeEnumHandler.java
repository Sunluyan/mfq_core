package com.mfq.handler;

import com.mfq.constants.ProductType;

public class ProductTypeEnumHandler extends EnumOrdinalTypeHandler<ProductType> {

    public ProductTypeEnumHandler(){
        super(ProductType.class);
    }
    
    @Override
    protected Object getEnumValue(ProductType parameter) {
        return parameter.getId();
    }

    @Override
    protected ProductType getEnumFromValue(Object value) {
        return ProductType.fromId((Integer) value);
    }
}
