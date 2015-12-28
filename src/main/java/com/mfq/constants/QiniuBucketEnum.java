package com.mfq.constants;

import org.apache.commons.lang.StringUtils;

public enum QiniuBucketEnum {

    // 注意这里域名的结束，有个左斜线
    AVATAR("avatar", "http://avatar.mfqzz.com/", "头像相册空间"),
    IMG0("img0", "http://img0.mfqzz.com/", "静态css－js存储空间"),
    IMG1("img1", "http://img1.mfqzz.com/", "产品空间"),
    IMG2("img2", "http://img2.mfqzz.com/", "Banner－广告图片等空间"),
    AUTH("auth", "http://img1.mfqzz.com/", "用户认证图片空间"),
    STATIC("static","http://7xlcaq.com2.z0.glb.qiniucdn.com/","静态图片存储空间");
    
    String bucket;
    String domain;
    String desc;
    
    QiniuBucketEnum(String bucket, String domain, String desc){
        this.bucket = bucket;
        this.domain = domain;
        this.desc = desc;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    
    public static QiniuBucketEnum fromBucket(String bucket){
        for(QiniuBucketEnum bk : QiniuBucketEnum.values()){
            if(StringUtils.equalsIgnoreCase(bk.getBucket(), bucket)){
                return bk;
            }
        }
        return null;
    }
}