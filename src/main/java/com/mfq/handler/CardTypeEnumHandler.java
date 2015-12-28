package com.mfq.handler;

import com.mfq.constants.CardType;

public class CardTypeEnumHandler extends EnumOrdinalTypeHandler<CardType> {

    public CardTypeEnumHandler(){
        super(CardType.class);
    }
    
    @Override
    protected Object getEnumValue(CardType parameter) {
        return parameter.getId();
    }

    @Override
    protected CardType getEnumFromValue(Object value) {
        return CardType.fromId((Integer)value);
    }
}