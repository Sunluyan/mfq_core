package com.mfq.bean.app;

import com.mfq.bean.Activity;

import java.util.Date;

/**
 * Created by liuzhiguo1 on 16/3/2.
 */
public class ActivityOffline {

    private Integer id;

    private String imgSmall;

    private String time;

    private String place;

    private String link;



    public ActivityOffline(Activity activity) {
        this.id = activity.getId();
        this.imgSmall = activity.getImgSmall();
        this.time = activity.getActivityTime();
        this.place = activity.getActivityPlace();
        this.link = activity.getLink();
    }

    public ActivityOffline() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImgSmall() {
        return imgSmall;
    }

    public void setImgSmall(String imgSmall) {
        this.imgSmall = imgSmall;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
