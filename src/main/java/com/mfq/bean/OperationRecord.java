package com.mfq.bean;

import java.io.Serializable;
import java.util.Date;

public class OperationRecord implements Serializable{

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long uid;

    private Integer proId;

    private Integer typeId;

    private Date operationDate;

    private String keyword;

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

    public Integer getProId() {
        return proId;
    }

    public void setProId(Integer proId) {
        this.proId = proId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Date getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(Date operationDate) {
        this.operationDate = operationDate;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword)
    {
        this.keyword = keyword == null ? null : keyword.trim();
    }

    @Override
    public String toString() {
        return "OperationRecord{" +
                "id=" + id +
                ", uid=" + uid +
                ", proId=" + proId +
                ", typeId=" + typeId +
                ", operationDate=" + operationDate +
                ", keyword='" + keyword + '\'' +
                '}';
    }
}