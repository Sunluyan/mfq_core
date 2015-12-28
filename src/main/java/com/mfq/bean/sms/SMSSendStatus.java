package com.mfq.bean.sms;

public enum SMSSendStatus {

    SUCCESS(1), FAILED(2), PENDING(3);

    int value;

    SMSSendStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public static SMSSendStatus fromValue(int value) {
        for(SMSSendStatus s : SMSSendStatus.values()){
            if(s.getValue() == value){
                return s;
            }
        }
        return null;
    }
}
