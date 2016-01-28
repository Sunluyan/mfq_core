package com.mfq.bean.web;

import java.util.Date;

/**
 * Created by liuzhiguo1 on 16/1/26.
 */
public class UserVisitRecord {
    private Long fingerprint ;
    private Long uid;
    private Date visitiAt ;

    public UserVisitRecord(Long fingerprint, Long uid, Date visitiAt) {
        this.fingerprint = fingerprint;
        this.uid = uid;
        this.visitiAt = visitiAt;
    }

    public UserVisitRecord() {
        super();
    }

    public Long getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(Long fingerprint) {
        this.fingerprint = fingerprint;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Date getVisitiAt() {
        return visitiAt;
    }

    public void setVisitiAt(Date visitiAt) {
        this.visitiAt = visitiAt;
    }
}
