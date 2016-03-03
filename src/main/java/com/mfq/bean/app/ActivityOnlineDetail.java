package com.mfq.bean.app;

import com.mfq.bean.Activity;

import java.util.List;

/**
 * Created by liuzhiguo1 on 16/3/2.
 */
public class ActivityOnlineDetail {

    private Integer id;
    private String title;
    private String desc;
    private String imgSmall;
    private String imgBig;
    private List<ProductListItem2App> pros ;

    public ActivityOnlineDetail(Activity activity) {
        this.id = activity.getId();
        this.title = activity.getTitle();
        this.desc = activity.getActivityDesc();
        this.imgSmall = activity.getImgSmall();
        this.imgBig = activity.getImgBig();
    }

    public ActivityOnlineDetail() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImgSmall() {
        return imgSmall;
    }

    public void setImgSmall(String imgSmall) {
        this.imgSmall = imgSmall;
    }

    public String getImgBig() {
        return imgBig;
    }

    public void setImgBig(String imgBig) {
        this.imgBig = imgBig;
    }

    public List<ProductListItem2App> getPros() {
        return pros;
    }

    public void setPros(List<ProductListItem2App> pros) {
        this.pros = pros;
    }
}
