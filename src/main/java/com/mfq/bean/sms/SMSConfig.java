package com.mfq.bean.sms;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.google.common.collect.Maps;
import com.mfq.utils.JsonUtil;

public class SMSConfig {
    
    int id; //sms provider id
    String sn;  //短信服务商－缩写
    String name; //短信服务商
    /**
     * 配置详情，其中detailMap为虚拟字段,
     * key为配置参数项，不同平台不同，数据库中json格式存储-单层
     */
    String detail;
    boolean normal;    //是否normal提供方
    boolean vcode;     //是否VCode提供方
    boolean batch;     //是否Batch提供方
    boolean backup;    //是否backup提供方
    Date updatedAt; //update时间
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getSn() {
        return sn;
    }
    public void setSn(String sn) {
        this.sn = sn;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDetail(String detail) {
        this.detail = detail;
    }
    public String getDetail() {
        return detail;
    }
    public Map<String, Object> getDetailMap() {
        if(StringUtils.isBlank(detail)){
            return Maps.newHashMap();
        }
        return JsonUtil.getMapFromJsonStr(detail);
    }
    public void setDetailMap(Map<String, Object> detailMap) {
        this.detail = JsonUtil.writeToJson(detailMap);
    }
    public boolean isNormal() {
        return normal;
    }
    public void setNormal(boolean normal) {
        this.normal = normal;
    }
    public boolean isVcode() {
        return vcode;
    }
    public void setVcode(boolean vcode) {
        this.vcode = vcode;
    }
    public boolean isBatch() {
        return batch;
    }
    public void setBatch(boolean batch) {
        this.batch = batch;
    }
    public boolean isBackup() {
        return backup;
    }
    public void setBackup(boolean backup) {
        this.backup = backup;
    }
    public Date getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
