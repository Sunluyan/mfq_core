package com.mfq.bean.user;

public enum Status {

    NORMAL(0),//正常
    INACTIVE(1), //
    BLOCKED(2), 
    DELETED(15);

    private final int value;

    private Status(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Status fromValue(int value) {
        for (Status s : Status.values()) {
            if (value == s.getValue()) {
                return s;
            }
        }
        return null;
    }
}
