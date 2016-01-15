package com.mfq.bean;

import java.util.Date;

public class OperationRecord {
    private Long id;

    private Long uid;

    private Integer proId;

    private Integer typeId;

    private Date operationDate;

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

    public OperationRecord(Long id, Long uid, Integer proId, Integer typeId, Date operationDate) {
        this.id = id;
        this.uid = uid;
        this.proId = proId;
        this.typeId = typeId;
        this.operationDate = operationDate;
    }

    public OperationRecord() {
        super();
    }
}