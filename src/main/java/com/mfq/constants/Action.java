package com.mfq.constants;

public enum Action {

    REG(0), 
    LOGIN(1), 
    MOBILE(2), 
    EMAIL(3), 
    AVATAR(4), 
    BasicSetting(5);

    private int value;

    private Action(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Action findByValue(int value) {
        for (Action a : Action.values()) {
            if (value == a.getValue()) {
                return a;
            }
        }
        return null;
    }

}
