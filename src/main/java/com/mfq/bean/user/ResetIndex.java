package com.mfq.bean.user;

public enum ResetIndex {
    
    NICK(2), 
    PIC(4), 
    INTRO(8), 
    REALNAME(16);

    private final int value;

    private ResetIndex(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ResetIndex findByValue(int value) {
        for (ResetIndex rt : ResetIndex.values()) {
            if (value == rt.getValue()) {
                return rt;
            }
        }
        return null;
    }
}
