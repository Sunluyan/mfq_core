package com.mfq.bean.app;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by hui on 16/3/15.
 */
public class ProductDetail2App {

    String product_name = "";
    String product_detail = "";
    String cheap_reason = "";  //优惠政策
    BigDecimal product_price = BigDecimal.valueOf(0);
    BigDecimal original_price = BigDecimal.valueOf(0); //原价
    int is_fq;  //是否分期 0 不可分期 1 可分期
    int is_collect; //收藏状态 0 没 1 有
    Map<Integer, BigDecimal> fqs = Maps.newHashMap();  //分期
    Map<String, String> diary = Maps.newHashMap();  //美丽日记
    Map<String, String> documentary = Maps.newHashMap(); //手术纪实

    List<String> product_imgs = Lists.newArrayList();  //产品图片

    Map<String, String> notice = Maps.newHashMap();  //购买须知
    List<String> details = Lists.newArrayList(); //产品详情

    String hospital_icon = "";
    String hospital_name = "";
    String hospital_url = "";

    public ProductDetail2App(){
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_detail() {
        return product_detail;
    }

    public void setProduct_detail(String product_detail) {
        this.product_detail = product_detail;
    }

    public String getCheap_reason() {
        return cheap_reason;
    }

    public void setCheap_reason(String cheap_reason) {
        this.cheap_reason = cheap_reason;
    }

    public BigDecimal getProduct_price() {
        return product_price;
    }

    public void setProduct_price(BigDecimal product_price) {
        this.product_price = product_price;
    }

    public BigDecimal getOriginal_price() {
        return original_price;
    }

    public void setOriginal_price(BigDecimal original_price) {
        this.original_price = original_price;
    }

    public int getIs_fq() {
        return is_fq;
    }

    public void setIs_fq(int is_fq) {
        this.is_fq = is_fq;
    }

    public int getIs_collect() {
        return is_collect;
    }

    public void setIs_collect(int is_collect) {
        this.is_collect = is_collect;
    }

    public Map<Integer, BigDecimal> getFqs() {
        return fqs;
    }

    public void setFqs(Map<Integer, BigDecimal> fqs) {
        this.fqs = fqs;
    }

    public Map<String, String> getDiary() {
        return diary;
    }

    public void setDiary(Map<String, String> diary) {
        this.diary = diary;
    }

    public Map<String, String> getDocumentary() {
        return documentary;
    }

    public void setDocumentary(Map<String, String> documentary) {
        this.documentary = documentary;
    }

    public List<String> getProduct_imgs() {
        return product_imgs;
    }

    public void setProduct_imgs(List<String> product_imgs) {
        this.product_imgs = product_imgs;
    }

    public Map<String, String> getNotice() {
        return notice;
    }

    public void setNotice(Map<String, String> notice) {
        this.notice = notice;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }

    public String getHospital_icon() {
        return hospital_icon;
    }

    public void setHospital_icon(String hospital_icon) {
        this.hospital_icon = hospital_icon;
    }

    public String getHospital_name() {
        return hospital_name;
    }

    public void setHospital_name(String hospital_name) {
        this.hospital_name = hospital_name;
    }

    public String getHospital_url() {
        return hospital_url;
    }

    public void setHospital_url(String hospital_url) {
        this.hospital_url = hospital_url;
    }
}
