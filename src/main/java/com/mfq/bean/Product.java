package com.mfq.bean;

import java.math.BigDecimal;
import java.util.Date;

import com.mfq.constants.ProductType;

public class Product implements Comparable {
    long id;            // 产品ID
    String name;        // 产品名称
    int tid;            // 产品类别
    int hospitalId;     // 医院ID
    BigDecimal price;   // 团购总价格
    BigDecimal marketPrice; //市场价
    BigDecimal onlinePay;  // 定金-在线支付
    BigDecimal hospitalPay; //到院支付
    BigDecimal pPrice;  // 分期价格,只记录一个基准的？
    ProductType type;           //类别
    int pNum;           // 分期数，只记录一个最大的？
    Date dateStart;     // 有效期开始时间
    Date dateEnd;       // 有效期结束时间
    long totalNum;            //产品总量
    long remainNum;      //剩余数量
    long viewNum;       // 浏览量
    long saleNum;       // 销量
    int cityId;         //城市id
    int flag;           // 推荐
    String img;         // 产品图片
    long orderNo;         //排序码
    boolean online;      // 产品状态,default true
    Date createdAt;     // 创建日期
    Date updatedAt;     // 最后更新时间

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public int getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(int hospitalId) {
        this.hospitalId = hospitalId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getOnlinePay() {
        return onlinePay;
    }

    public void setOnlinePay(BigDecimal onlinePay) {
        this.onlinePay = onlinePay;
    }

    public BigDecimal getHospitalPay() {
        return hospitalPay;
    }

    public void setHospitalPay(BigDecimal hospitalPay) {
        this.hospitalPay = hospitalPay;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public BigDecimal getpPrice() {
        return pPrice;
    }

    public void setpPrice(BigDecimal pPrice) {
        this.pPrice = pPrice;
    }

    public int getpNum() {
        return pNum;
    }

    public void setpNum(int pNum) {
        this.pNum = pNum;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public long getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(long totalNum) {
        this.totalNum = totalNum;
    }

    public long getRemainNum() {
        return remainNum;
    }

    public void setRemainNum(long remainNum) {
        this.remainNum = remainNum;
    }

    public long getViewNum() {
        return viewNum;
    }

    public void setViewNum(long viewNum) {
        this.viewNum = viewNum;
    }

    public long getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(long saleNum) {
        this.saleNum = saleNum;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(long orderNo) {
        this.orderNo = orderNo;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;

    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tid=" + tid +
                ", hospitalId=" + hospitalId +
                ", price=" + price +
                ", marketPrice=" + marketPrice +
                ", onlinePay=" + onlinePay +
                ", hospitalPay=" + hospitalPay +
                ", pPrice=" + pPrice +
                ", type=" + type +
                ", pNum=" + pNum +
                ", dateStart=" + dateStart +
                ", dateEnd=" + dateEnd +
                ", totalNum=" + totalNum +
                ", remainNum=" + remainNum +
                ", viewNum=" + viewNum +
                ", saleNum=" + saleNum +
                ", cityId=" + cityId +
                ", flag=" + flag +
                ", img='" + img + '\'' +
                ", orderNo=" + orderNo +
                ", online=" + online +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        if (!(o instanceof Product))
            throw new RuntimeException("对象不正确！！");
        Product product = (Product) o;

        if (this.saleNum > product.saleNum) {
            return -1;
        } else if (this.saleNum < product.saleNum) {
            return 1;
        } else if (this.id == product.id) {
            return 0;
        }
        return 1;
    }
}
