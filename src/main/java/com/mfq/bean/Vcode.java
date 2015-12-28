package com.mfq.bean;

import java.util.Date;

public class Vcode {

    public int id; // required
    public String key; // required
    public String vcode; // required
    public Date expireAt; // required
    public Date resendAt; // required
    public int retryTime; // required
    public Date createdAt; // optional
    public Date updatedAt; // optional
    public int seqId; // optional
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getVcode() {
        return vcode;
    }
    public void setVcode(String vcode) {
        this.vcode = vcode;
    }
    public Date getExpireAt() {
        return expireAt;
    }
    public void setExpireAt(Date expireAt) {
        this.expireAt = expireAt;
    }
    public Date getResendAt() {
        return resendAt;
    }
    public void setResendAt(Date resendAt) {
        this.resendAt = resendAt;
    }
    public int getRetryTime() {
        return retryTime;
    }
    public void setRetryTime(int retryTime) {
        this.retryTime = retryTime;
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
    public int getSeqId() {
        return seqId;
    }
    public void setSeqId(int seqId) {
        this.seqId = seqId;
    }
}
