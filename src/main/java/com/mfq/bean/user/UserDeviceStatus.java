package com.mfq.bean.user;

public enum UserDeviceStatus {

    Offline(0),
    Online(1);

    private final int value;

    private UserDeviceStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static UserDeviceStatus findByValue(int value) {
        for(UserDeviceStatus uds : UserDeviceStatus.values()){
            if(value == uds.getValue()){
                return uds;
            }
        }
        return null;
    }
}
