package com.mfq.bean.app;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mfq.bean.*;
import com.mfq.constants.ProductImageType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hui on 16/3/15.
 */
public class ProductDetail2App {

    String name = "";
    String cheap_reason = "";  //优惠政策
    BigDecimal price = BigDecimal.valueOf(0);
    BigDecimal market_price = BigDecimal.valueOf(0); //原价
    int is_fq;  //是否分期 0 不可分期 1 可分期
    int is_collect; //收藏状态 0 没 1 有
    Map<Long, BigDecimal> fqs = Maps.newHashMap();  //分期
    String before = "";
    String after = "";
    String dairy = "";
    String surgery = "";
    List<String> pro_imgs = Lists.newArrayList();  //产品图片
    String ask = "[{\"answer\":\"\",\"question\":\"\"}]";  //购买须知
//    List<Map<String,String>> asks = Lists.newArrayList();

    List<String> details = Lists.newArrayList(); //产品详情
    String hos_img = "";
    String hos_name = "";
    String hos_desc = "";
    Integer hos_id = 0;

    Long pid = 0l;
    String share_url = "";



    public ProductDetail2App() {
        super();
    }

    public ProductDetail2App(String fuck) {
        this.name = "纯洁无痕水光针";
        this.cheap_reason = "凉风有性，秋月无边，亏我思娇的情绪好比度日如年。虽然我不是玉树临风，潇洒倜傥，但是我有广阔的胸襟和强健臂弯";
        this.price = BigDecimal.valueOf(3000.00);
        this.market_price = BigDecimal.valueOf(5000.00);
        this.is_fq = 1;
        this.is_collect = 0;
        Map<Long, BigDecimal> fqMap = new HashMap<>();
        fqMap.put(3l, BigDecimal.valueOf(336.25));
        fqMap.put(6l, BigDecimal.valueOf(234.87));
        fqMap.put(12l, BigDecimal.valueOf(155.32));
        this.fqs = fqMap;
        this.before = "http://img1.mfqzz.com/img1/p/20160316/145809645474897M.jpg";
        this.after = "http://img1.mfqzz.com/img1/p/20160316/145809645474897M.jpg";
        this.dairy = "http://img1.mfqzz.com/img1/p/20160316/14580964557719vP.jpg";
        this.surgery = "http://ww1.sinaimg.cn/large/5bc26f12jw1f1ji5tlh4fj214f1q8qt7.jpg";
        this.pro_imgs.add("http://img1.mfqzz.com/img1/p/20160308/1457409297043177.jpg");
        this.pro_imgs.add("http://img1.mfqzz.com/img1/p/20160308/1457409297810i49.jpg");
        this.pro_imgs.add("http://img1.mfqzz.com/img1/p/20151218/14504358495305Pn.jpg");
        this.ask = "[{\"answer\":\"问题0\",\"question\":\"问题0\"},{\"answer\":\"回答1\",\"question\":\"问题1\"},{\"answer\":\"回答2\",\"question\":\"问题2\"},{\"answer\":\"回答3\",\"question\":\"问题3\"},{\"answer\":\"回答4\",\"question\":\"问题4\"}]";
        this.details.add("http://img1.mfqzz.com/img1/p/20160316/145809645109915m.jpg");
        this.details.add("http://img1.mfqzz.com/img1/p/20160315/14580359951613Fi.jpg");
        this.details.add("http://ww2.sinaimg.cn/large/5bc26f12jw1f1ji58005kj223t23ukjl.jpg");
        this.details.add("http://ww1.sinaimg.cn/large/5bc26f12jw1f1ji5tlh4fj214f1q8qt7.jpg");
        this.hos_img = "http://img1.mfqzz.com/images/hospital/meilai.jpg";
        this.hos_name = "北京美莱医疗美容医院";
        this.hos_desc = "北京美莱医疗美容医院—隶属于美莱医学美容集团。医院下设整形、皮肤、无创、抗衰老、中医、口腔六大中心，涵盖胸部整形、鼻部整形、眼部整形、祛斑美白、祛痘嫩肤、紧肤除皱、无创塑形、功能医学抗衰老、中医美容、口腔美容等经典项目.";
        this.hos_id = 3;
        this.share_url = "/product/app/detail?pid="+this.pid;
    }


    public ProductDetail2App(Product product, ProductDetailNew detail) {
        this.name = product.getName();
        this.cheap_reason = detail.getPreferential();
        this.price = product.getPrice();
        this.market_price = product.getMarketPrice();
        this.is_fq = 1;
        this.pid = product.getId();
        this.share_url = "/product/app/detail?pid="+product.getId();

        this.ask = detail.getAsk();
        this.hos_id = product.getHospitalId();
    }


    public ProductDetail2App(Product product) {
        this.name = product.getName();
        this.price = product.getPrice();
        this.pid = product.getId();
        this.market_price = product.getMarketPrice();
        this.is_fq = 1;
        this.share_url = "/product/app/detail?pid="+product.getId();
        this.hos_id = product.getHospitalId();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCheap_reason() {
        return cheap_reason;
    }

    public void setCheap_reason(String cheap_reason) {
        this.cheap_reason = cheap_reason;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getMarket_price() {
        return market_price;
    }

    public void setMarket_price(BigDecimal market_price) {
        this.market_price = market_price;
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

    public Map<Long, BigDecimal> getFqs() {
        return fqs;
    }

    public void setFqs(Map<Long, BigDecimal> fqs) {
        this.fqs = fqs;
    }

    public String getBefore() {
        return before;
    }

    public void setBefore(String before) {
        this.before = before;
    }

    public String getAfter() {
        return after;
    }

    public void setAfter(String after) {
        this.after = after;
    }

    public String getDairy() {
        return dairy;
    }

    public void setDairy(String dairy) {
        this.dairy = dairy;
    }

    public String getSurgery() {
        return surgery;
    }

    public void setSurgery(String surgery) {
        this.surgery = surgery;
    }

    public List<String> getPro_imgs() {
        return pro_imgs;
    }

    public void setPro_imgs(List<String> pro_imgs) {
        this.pro_imgs = pro_imgs;
    }

    public String getAsk() {
        return ask;
    }

    public void setAsk(String ask) {
        this.ask = ask;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }

    public String getHos_img() {
        return hos_img;
    }

    public void setHos_img(String hos_img) {
        this.hos_img = hos_img;
    }

    public String getHos_name() {
        return hos_name;
    }

    public void setHos_name(String hos_name) {
        this.hos_name = hos_name;
    }

    public String getHos_desc() {
        return hos_desc;
    }

    public void setHos_desc(String hos_desc) {
        this.hos_desc = hos_desc;
    }

    public Integer getHos_id() {
        return hos_id;
    }

    public void setHos_id(Integer hos_id) {
        this.hos_id = hos_id;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public void setImages(List<ProductImg> imgs, List<ProductImage> images) {
        for (ProductImg img : imgs) {
            this.pro_imgs.add(img.getImg());
        }
        for (ProductImage image : images) {
            if(image.getType() == ProductImageType.BEFORE.getId()){
                this.before = image.getImg();
            }
            else if(image.getType() == ProductImageType.AFTER.getId()){
                this.after = image.getImg();
            }
            else if(image.getType() == ProductImageType.BEAUTIFUL.getId()){
                this.dairy = image.getImg();
            }
            else if(image.getType() == ProductImageType.DETAIL.getId()){
                this.details.add(image.getImg());
            }
            else if(image.getType() == ProductImageType.SURGERY.getId()){
                this.surgery = image.getImg();
            }
        }
    }

    public void setHospital(Hospital hospital){
        this.hos_img = hospital.getImg();
        this.hos_name = hospital.getName();
        this.hos_desc = hospital.getDescription();
    }


}
