package com.mfq.bean.user;

public class UsersAuthPic {
    private Long uid;

    private String idCard;

    private String idCardF;

    private String idCardR;

    private String social;

    private String socialF;

    private String socialR;

    private String workPermit;

    private String workPermitF;

    private String workPermitR;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    public String getIdCardF() {
        return idCardF;
    }

    public void setIdCardF(String idCardF) {
        this.idCardF = idCardF == null ? null : idCardF.trim();
    }

    public String getIdCardR() {
        return idCardR;
    }

    public void setIdCardR(String idCardR) {
        this.idCardR = idCardR == null ? null : idCardR.trim();
    }

    public String getSocial() {
        return social;
    }

    public void setSocial(String social) {
        this.social = social == null ? null : social.trim();
    }

    public String getSocialF() {
        return socialF;
    }

    public void setSocialF(String socialF) {
        this.socialF = socialF == null ? null : socialF.trim();
    }

    public String getSocialR() {
        return socialR;
    }

    public void setSocialR(String socialR) {
        this.socialR = socialR == null ? null : socialR.trim();
    }

    public String getWorkPermit() {
        return workPermit;
    }

    public void setWorkPermit(String workPermit) {
        this.workPermit = workPermit == null ? null : workPermit.trim();
    }

    public String getWorkPermitF() {
        return workPermitF;
    }

    public void setWorkPermitF(String workPermitF) {
        this.workPermitF = workPermitF == null ? null : workPermitF.trim();
    }

    public String getWorkPermitR() {
        return workPermitR;
    }

    public void setWorkPermitR(String workPermitR) {
        this.workPermitR = workPermitR == null ? null : workPermitR.trim();
    }
}