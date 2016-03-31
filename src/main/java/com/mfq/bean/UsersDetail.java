package com.mfq.bean;

import com.mfq.constants.BloodType;

public class UsersDetail {
    private Long uid;

    private Integer bloodType;

    private Integer constellation;

    private String age;

    private String job;

    private String school;

    private String area;

    private String interesting;

    private String description;

    public UsersDetail() {
        super();
    }

    public UsersDetail(String fuck) {
        this.uid = 3157l;
        this.bloodType = 2;
        this.constellation = 10;
        this.age = "80后";
        this.job = "软件工程师";
        this.school = "上海复旦大学";
        this.area = "北京通州";
        this.interesting = "鼻部,皮肤,牙齿";



    }

    public UsersDetail(Long uid, Integer bloodType, Integer constellation, String age, String job, String school, String area, String interesting, String description) {
        this.uid = uid;
        this.bloodType = bloodType;
        this.constellation = constellation;
        this.age = age;
        this.job = job;
        this.school = school;
        this.area = area;
        this.interesting = interesting;
        this.description = description;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Integer getBloodType() {
        return bloodType;
    }

    public void setBloodType(Integer bloodType) {
        this.bloodType = bloodType;
    }

    public Integer getConstellation() {
        return constellation;
    }

    public void setConstellation(Integer constellation) {
        this.constellation = constellation;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age == null ? null : age.trim();
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job == null ? null : job.trim();
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school == null ? null : school.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getInteresting() {
        return interesting;
    }

    public void setInteresting(String interesting) {
        this.interesting = interesting == null ? null : interesting.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}