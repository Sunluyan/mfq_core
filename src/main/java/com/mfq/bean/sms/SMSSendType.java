package com.mfq.bean.sms;

public enum SMSSendType {

    NORMAL(1), 
    TIMED(2), 
    BATCH(3), 
    RESEND(4), 
    VCODE(5);

    int value;

    SMSSendType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static SMSSendType fromValue(int value) {
        for(SMSSendType s : SMSSendType.values()){
            if(value == s.getValue()){
                return s;
            }
        }
        return null;
    }
}
