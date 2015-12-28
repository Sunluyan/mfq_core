package com.mfq.bean.user;

public class UserLogin {

    public long uid; // required
    public long regAt; // required
    public String regIp; // required
    public long lastLoginAt; // required
    public String lastLoginIp; // required
    public long activedAt; // optional
    public String source; // optional
    public String referer; // optional
    public String regTrack; // optional

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public long getRegAt() {
        return regAt;
    }

    public void setRegAt(long regAt) {
        this.regAt = regAt;
    }

    public String getRegIp() {
        return regIp;
    }

    public void setRegIp(String regIp) {
        this.regIp = regIp;
    }

    public long getLastLoginAt() {
        return lastLoginAt;
    }

    public void setLastLoginAt(long lastLoginAt) {
        this.lastLoginAt = lastLoginAt;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public long getActivedAt() {
        return activedAt;
    }

    public void setActivedAt(long activedAt) {
        this.activedAt = activedAt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }

    public String getRegTrack() {
        return regTrack;
    }

    public void setRegTrack(String regTrack) {
        this.regTrack = regTrack;
    }
}

