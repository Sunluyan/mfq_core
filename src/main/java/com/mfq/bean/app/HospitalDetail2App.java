package com.mfq.bean.app;

import com.mfq.bean.Hospital;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuzhiguo1 on 16/3/17.
 */
public class HospitalDetail2App {

    String name = "";
    String img_hos = "";
    String desc = "";
    List<String> img_details = new ArrayList<>();
    List<ProductListItem2App> pros = new ArrayList<>();

    public HospitalDetail2App() {
        super();
    }

    public HospitalDetail2App(String fuck){
        this.name = "北京美莱医疗美容医院";
        this.img_hos = "http://img1.mfqzz.com/images/hospital/meilai.jpg";
        this.desc = "医院介绍:北京美莱医疗美容医院—隶属于美莱医学美容集团。医院下设整形、皮肤、无创、抗衰老、中医、口腔六大中心，涵盖胸部整形、鼻部整形、眼部整形、祛斑美白、祛痘嫩肤、紧肤除皱、无创塑形、功能医学抗衰老、中医美容、口腔美容等经典项目。";
        this.img_details.add("http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-171.png");
        this.img_details.add("http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-172.png");
        this.img_details.add("http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-173.png");
    }

    public HospitalDetail2App(Hospital hospital) {
        this.name = hospital.getName();
        this.img_hos = hospital.getImg();
        this.desc = hospital.getDescription();
        String[] details = hospital.getDetails().split(",");
        for (String detail : details) {
            this.img_details.add(detail);
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg_hos() {
        return img_hos;
    }

    public void setImg_hos(String img_hos) {
        this.img_hos = img_hos;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<String> getImg_details() {
        return img_details;
    }

    public void setImg_details(List<String> img_details) {
        this.img_details = img_details;
    }

    public List<ProductListItem2App> getPros() {
        return pros;
    }

    public void setPros(List<ProductListItem2App> pros) {
        this.pros = pros;
    }
}
