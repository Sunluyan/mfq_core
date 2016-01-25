package com.mfq.constants;

public enum RefundStatus {

    APPLY(1),
    PASS(2),
    REJECT(3),
    REFUNDED(4),
    FINISH(5);

    int value;

    RefundStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    

    public static RefundStatus fromValue(Integer value) {
        if (value == null) {
            return null;
        }

        for (RefundStatus s : RefundStatus.values())
            if (s.getValue() == value) return s;
        return null;
    }
}