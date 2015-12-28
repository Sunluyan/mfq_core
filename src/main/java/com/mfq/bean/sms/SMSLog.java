package com.mfq.bean.sms;

import java.util.Date;

public class SMSLog {

    long sid; // required
    String mobile; // required
    String content; // required
    SMSSendStatus sendStatus; // required
    String provider; // required
    SMSSendType sendType; // required
    String extra; // required
    Date createdAt; // required
    Date timedAt; // required
    Date sentAt; // required
    long resendSid; // required

    public SMSLog() {
    }

    public SMSLog(
            long sid,
            String mobile,
            String content,
            SMSSendStatus sendStatus,
            String provider,
            SMSSendType sendType,
            String extra,
            Date createdAt,
            Date timedAt,
            Date sentAt,
            long resendSid) {
        
        this();
        this.sid = sid;

        this.mobile = mobile;
        this.content = content;
        this.sendStatus = sendStatus;
        this.provider = provider;
        this.sendType = sendType;
        this.extra = extra;
        this.createdAt = createdAt;

        this.timedAt = timedAt;
        this.sentAt = sentAt;
        this.resendSid = resendSid;

    }


    public long getSid() {
        return sid;
    }

    public void setSid(long sid) {
        this.sid = sid;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public SMSSendStatus getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(SMSSendStatus sendStatus) {
        this.sendStatus = sendStatus;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public SMSSendType getSendType() {
        return sendType;
    }

    public void setSendType(SMSSendType sendType) {
        this.sendType = sendType;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getTimedAt() {
        return timedAt;
    }

    public void setTimedAt(Date timedAt) {
        this.timedAt = timedAt;
    }

    public Date getSentAt() {
        return sentAt;
    }

    public void setSentAt(Date sentAt) {
        this.sentAt = sentAt;
    }

    public long getResendSid() {
        return resendSid;
    }

    public void setResendSid(long resendSid) {
        this.resendSid = resendSid;
    }
}

