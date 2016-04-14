package com.mfq.bean.app;

import com.mfq.bean.UsersDetail;
import com.mfq.bean.user.Gender;
import com.mfq.bean.user.User;
import com.mfq.constants.BloodType;
import com.mfq.constants.Constellation;
import com.mfq.constants.QiniuBucketEnum;
import org.apache.commons.lang.StringUtils;

/**
 * Created by liuzhiguo1 on 16/3/31.
 */
public class UsersDetail2App {
    Long uid = 0l;
    String img = "";
    String nick = "";
    String sex = "";
    String blood = "";
    String constellation = "";
    String age = "";
    String job = "";
    String school = "";
    String area = "";
    String description = "";
    String interesting = "";
    String percent = "";

    public UsersDetail2App() {
        super();
    }
    public UsersDetail2App(String fuck){
        if(fuck.equals("fuck")){
            this.uid = 3156l;
            this.img = "http://7xlcaq.com2.z0.glb.qiniucdn.com/liupei.jpg";
            this.nick = "小飞侠";
            this.sex = "女";
            this.blood = "AB";
            this.constellation = "双子座";
            this.age = "80";
            this.job = "长得漂亮的设计师";
            this.school = "湖南长沙女子大学";
            this.area = "北京市通州区九棵树站";
            this.description = "一个与世无争的小女孩~";
            this.interesting = "刘志国,刘志国,刘志国";


            int number = 0;
            if(StringUtils.isNotBlank(img)){
                number ++;
            }
            if(StringUtils.isNotBlank(nick)){
                number ++;
            }
            if(StringUtils.isNotBlank(sex)){
                number ++;
            }
            if(StringUtils.isNotBlank(blood)){
                number ++;
            }
            if(StringUtils.isNotBlank(constellation)){
                number ++;
            }
            if(StringUtils.isNotBlank(age)){
                number ++;
            }
            if(StringUtils.isNotBlank(job)){
                number ++;
            }
            if(StringUtils.isNotBlank(school)){
                number ++;
            }
            if(StringUtils.isNotBlank(area)){
                number ++;
            }
            if(StringUtils.isNotBlank(description)){
                number ++;
            }
            if(StringUtils.isNotBlank(interesting)){
                number ++;
            }
            Double percent = (double)number/11;
            if(percent == 1){
                this.percent = "100";
            }else{
                String[] s = percent.toString().split("\\.");
                this.percent = s[1].substring(0,2);
            }

        }
    }


    public UsersDetail2App(Long uid, String img, String nick, String sex, String blood, String constellation, String age, String job, String school, String area, String description, String interesting, String percent) {
        this.uid = uid;
        this.img = img;
        this.nick = nick;
        this.sex = sex;
        this.blood = blood;
        this.constellation = constellation;
        this.age = age;
        this.job = job;
        this.school = school;
        this.area = area;
        this.description = description;
        this.interesting = interesting;
        this.percent = percent;
    }

    public UsersDetail2App(User user, UsersDetail detail) {
        this.uid  = user.getUid();
        this.img = StringUtils.isNotBlank(user.getImg())?user.getImg():QiniuBucketEnum.AVATAR.getDomain()+user.getPic();
        this.nick = user.getNick();
        this.sex = user.getGender() == Gender.Male?"男":user.getGender() == Gender.Female?"女":"";
        this.blood = detail.getBloodType() !=null?BloodType.fromId(detail.getBloodType()).getDesc():"";
        this.constellation = detail.getConstellation() != null?Constellation.fromId(detail.getConstellation()).getDesc():"";
        this.age = detail.getAge();
        this.job = detail.getJob();
        this.school = detail.getSchool();
        this.area = detail.getArea();
        this.description = detail.getDescription();
        this.interesting = detail.getInteresting();

        int number = 0;
        if(StringUtils.isNotBlank(img)){
            number ++;
        }
        if(StringUtils.isNotBlank(nick)){
            number ++;
        }
        if(StringUtils.isNotBlank(sex)){
            number ++;
        }
        if(StringUtils.isNotBlank(blood)){
            number ++;
        }
        if(StringUtils.isNotBlank(constellation)){
            number ++;
        }
        if(StringUtils.isNotBlank(age)){
            number ++;
        }
        if(StringUtils.isNotBlank(job)){
            number ++;
        }
        if(StringUtils.isNotBlank(school)){
            number ++;
        }
        if(StringUtils.isNotBlank(area)){
            number ++;
        }
        if(StringUtils.isNotBlank(description)){
            number ++;
        }
        if(StringUtils.isNotBlank(interesting)){
            number ++;
        }
        Double percent = (double)number/11;
        if(percent == 1){
            this.percent = "100";
        }else{
            String[] s = percent.toString().split("\\.");
            this.percent = s[1].substring(0,2);
        }
    }


    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public String getConstellation() {
        return constellation;
    }

    public void setConstellation(String constellation) {
        this.constellation = constellation;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInteresting() {
        return interesting;
    }

    public void setInteresting(String interesting) {
        this.interesting = interesting;
    }

    @Override
    public String toString() {
        return "UsersDetail2App{" +
                "uid=" + uid +
                ", img='" + img + '\'' +
                ", nick='" + nick + '\'' +
                ", sex='" + sex + '\'' +
                ", blood='" + blood + '\'' +
                ", constellation='" + constellation + '\'' +
                ", age='" + age + '\'' +
                ", job='" + job + '\'' +
                ", school='" + school + '\'' +
                ", area='" + area + '\'' +
                ", description='" + description + '\'' +
                ", interesting='" + interesting + '\'' +
                ", percent='" + percent + '\'' +
                '}';
    }

    public String getpercent() {
        return percent;
    }

    public void setpercent(String percent) {
        this.percent = percent;
    }
}
