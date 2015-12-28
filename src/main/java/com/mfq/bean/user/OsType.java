package com.mfq.bean.user;

public enum OsType {
    
    Ios(1),
    Android(2),
    Other(10);

    private final int value;

    private OsType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static OsType findByValue(int value) {
        for(OsType os : OsType.values()){
            return os;
        }
        return null;
    }
}
