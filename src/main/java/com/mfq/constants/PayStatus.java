package com.mfq.constants;

public enum PayStatus {

    GO_PAY(1), 
    PAID(2), 
    CANCEL(3);

    int value;

    PayStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    

    public static PayStatus fromValue(Integer value) {
        if (value == null) {
            return null;
        }

        for (PayStatus s : PayStatus.values())
            if (s.getValue() == value) return s;
        return null;
    }
}