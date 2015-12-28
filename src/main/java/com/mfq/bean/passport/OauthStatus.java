package com.mfq.bean.passport;

public enum OauthStatus {

    Normal(0),
    Unbind(99);

    int value;

    OauthStatus(int value){
        this.value = value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static OauthStatus findByValue(int value) {
        for (OauthStatus s : OauthStatus.values()) {
            if (value == s.getValue()) {
                return s;
            }
        }
        return null;
    }
}
