package com.mfq.bean.app;

import com.mfq.bean.Activity;

import java.util.Date;

/**
 * Created by liuzhiguo1 on 16/3/2.
 */
public class ActivityNotOnline {

    private Integer id;

    private String imgSmall;

    private Date time;

    private String place;

    public ActivityNotOnline(Activity activity) {
        this.id = activity.getId();
        this.imgSmall = activity.getImgSmall();
        this.time = activity.getActivityTime();
        this.place = activity.getActivityPlace();
    }

    public ActivityNotOnline() {
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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
