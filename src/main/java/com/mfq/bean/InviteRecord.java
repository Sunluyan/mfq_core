package com.mfq.bean;

import java.util.Date;

public class InviteRecord {
    private Long id;

    private Long uid;

    private Date invitedTime;

    private Long invitedUid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Date getInvitedTime() {
        return invitedTime;
    }

    public void setInvitedTime(Date invitedTime) {
        this.invitedTime = invitedTime;
    }

    public Long getInvitedUid() {
        return invitedUid;
    }

    public void setInvitedUid(Long invitedUid) {
        this.invitedUid = invitedUid;
    }
}