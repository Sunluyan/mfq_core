package com.mfq.bean.app;

import com.mfq.bean.Activity;

/**
 * Created by liuzhiguo1 on 16/3/2.
 */
public class ActivityOnline {
    private Integer id ;
    private String img;
    private Integer end;

    public ActivityOnline(Activity activity) {
        this.id = activity.getId();
        this.img = activity.getImgSmall();
        this.end = activity.getEnd();
    }
    public ActivityOnline(){
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
