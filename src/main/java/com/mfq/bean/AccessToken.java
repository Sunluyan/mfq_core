package com.mfq.bean;

import java.util.Date;

public class AccessToken {

    long uid; // required
    String clientId; // required
    String openId; // required
    String accessToken; // required
    String refreshToken; // required
    String grantType; // required
    String tokenType; // required
    Date createdAt; // required
    Date expiredAt; // required
    Date refreshExpiredAt; // required
    String authenticate; // required
    String additional; // required
    
    public long getUid() {
        return uid;
    }
    public void setUid(long uid) {
        this.uid = uid;
    }
    public String getClientId() {
        return clientId;
    }
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
    public String getOpenId() {
        return openId;
    }
    public void setOpenId(String openId) {
        this.openId = openId;
    }
    public String getAccessToken() {
        return accessToken;
    }
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    public String getRefreshToken() {
        return refreshToken;
    }
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
    public String getGrantType() {
        return grantType;
    }
    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }
    public String getTokenType() {
        return tokenType;
    }
    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
    public Date getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    public Date getExpiredAt() {
        return expiredAt;
    }
    public void setExpiredAt(Date expiredAt) {
        this.expiredAt = expiredAt;
    }
    public Date getRefreshExpiredAt() {
        return refreshExpiredAt;
    }
    public void setRefreshExpiredAt(Date refreshExpiredAt) {
        this.refreshExpiredAt = refreshExpiredAt;
    }
    public String getAuthenticate() {
        return authenticate;
    }
    public void setAuthenticate(String authenticate) {
        this.authenticate = authenticate;
    }
    public String getAdditional() {
        return additional;
    }
    public void setAdditional(String additional) {
        this.additional = additional;
    }
    
}
