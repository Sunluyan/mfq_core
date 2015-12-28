package com.mfq.constants;

public enum CardType {

    UNDEFINED(0, "未指定"),
    
    DEBIG_CARD(1, "借记卡"),
    
    CREDIT_CARD(2, "信用卡"); 
    
    int id;
    String card;
    
    CardType(int id, String card){
        this.id = id;
        this.card = card;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }
    
    public static CardType fromId(int id){
        for(CardType card : CardType.values()){
            if(card.getId() == id){
                return card;
            }
        }
        return null;
    }
}
