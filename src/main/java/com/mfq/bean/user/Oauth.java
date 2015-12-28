package com.mfq.bean.user;

import java.util.Map;

public class Oauth {

    public int uid; // required
    public short site_type; // required
    public String accessToken; // required
    public String uuid; // required
    public long createdAt; // required
    public long updatedAt; // required
    public Map<String, String> extra; // optional

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public short getSite_type() {
        return site_type;
    }

    public void setSite_type(short site_type) {
        this.site_type = site_type;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Map<String, String> getExtra() {
        return extra;
    }

    public void setExtra(Map<String, String> extra) {
        this.extra = extra;
    }


}

