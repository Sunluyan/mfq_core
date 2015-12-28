package com.mfq.bean;

import java.util.Date;

public class Installation {

    long id; // required
    String deviceType; // required
    String deviceToken; // required
    String appId; // required
    String appVersion; // required
    String channels; // required
    int badge; // required
    long uid; // required
    Date createdAt; // required
    Date updatedAt; // required
    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getDeviceType() {
        return deviceType;
    }
    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }
    public String getDeviceToken() {
        return deviceToken;
    }
    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
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
    public String getChannels() {
        return channels;
    }
    public void setChannels(String channels) {
        this.channels = channels;
    }
    public int getBadge() {
        return badge;
    }
    public void setBadge(int badge) {
        this.badge = badge;
    }
    public long getUid() {
        return uid;
    }
    public void setUid(long uid) {
        this.uid = uid;
    }
    public Date getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    public Date getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
    
}
