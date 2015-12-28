package com.mfq.bean.user;

public class UserDevice {

    public int id; // required
    public int uid; // required
    public UserDeviceStatus onlineStatus; // required
    public OsType osType; // required
    public String appId; // required
    public String appVersion; // required
    public String osVersion; // required
    public String deviceToken; // required

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public UserDeviceStatus getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(UserDeviceStatus onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public OsType getOsType() {
        return osType;
    }

    public void setOsType(OsType osType) {
        this.osType = osType;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }
}

