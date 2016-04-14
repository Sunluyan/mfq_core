package com.mfq.bean.app;

import java.math.BigDecimal;
import java.util.Date;

import com.mfq.bean.UsersDetail;
import com.mfq.bean.user.Gender;
import com.mfq.bean.user.User;
import com.mfq.bean.user.UserQuota;
import com.mfq.helper.UserHelper;
import org.apache.commons.lang.StringUtils;

public class UserProfile2App {
    long uid;
    String mobile;
    String nick;
    int gender; // user gender(1 for boy,2 for girl,0 for unset)
    String avatar;
    int location_id; //（ID需前后端一致,或吐location给app端）
    String realname; //（真实姓名）
    String id_card; //（身份证号）
    String contact; //联系方式
    String school; //学校
    int auth_status;  // 认证状态 0-未认证 1-已认证 2-认证中
    int user_type;   //用户类型 上班族或者学生 0 未认证
    Date startschoolAt; // 毕业时间
    int career; //（1是学生，0是其它)
    BigDecimal quota_all; //（总额度）
    BigDecimal quota_left; //（剩余额度）
    BigDecimal balance; //（帐户余额）
    Date graduatedAt;//入学时间
    String desc;
    String percent;


    public UserProfile2App() {

    }

    public UserProfile2App(User user, UserQuota userQuota) {
        this.uid = user.getUid();
        this.mobile = user.getMobile();
        this.nick = user.getNick();
        this.gender = user.getGender() == null ? Gender.Unset.getValue() : user.getGender().getValue();
        if (user != null && StringUtils.isNotBlank(user.getImg())) {
            this.avatar = user.getImg();
        } else if (user != null && StringUtils.isNotBlank(user.getPic())) {
            this.avatar = user.getPic();
        }
        if(this.avatar!=null && !this.avatar.contains("http"))
            this.avatar = UserHelper.getAvatarUrl(user);
        else if(this.avatar == null)
            this.avatar = "";

        this.location_id = userQuota.getSchoolLocationId();
        this.realname = userQuota.getRealname();
        this.id_card = userQuota.getIdCard();
        this.contact = userQuota.getContact();
        this.school = userQuota.getSchool();
        this.auth_status = userQuota.getAuthStatus();
        this.startschoolAt = userQuota.getStartschoolAt();
        this.graduatedAt = userQuota.getStartschoolAt();
        this.career = user.getCareer();
        this.user_type = userQuota.getUserType();
        this.quota_all = userQuota.getQuotaAll();
        this.quota_left = userQuota.getQuotaLeft();
        this.balance = userQuota.getBalance().add(userQuota.getPresent());
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getLocation_id() {
        return location_id;
    }

    public void setLocation_id(int location_id) {
        this.location_id = location_id;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getId_card() {
        return id_card;
    }

    public void setId_card(String id_card) {
        this.id_card = id_card;
    }

    public String getContact() {
        return contact;
    }

    public int getAuth_status() {
        return auth_status;
    }

    public int getUser_type() {
        return user_type;
    }

    public void setUser_type(int user_type) {
        this.user_type = user_type;
    }

    public void setAuth_status(int auth_status) {
        this.auth_status = auth_status;
    }

    public Date getStartschoolAt() {
        return startschoolAt;
    }

    public void setStartschoolAt(Date startschoolAt) {
        this.startschoolAt = startschoolAt;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public int getCareer() {
        return career;
    }

    public void setCareer(int career) {
        this.career = career;
    }

    public BigDecimal getQuota_all() {
        return quota_all;
    }

    public void setQuota_all(BigDecimal quota_all) {
        this.quota_all = quota_all;
    }

    public BigDecimal getQuota_left() {
        return quota_left;
    }

    public void setQuota_left(BigDecimal quota_left) {
        this.quota_left = quota_left;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

	public Date getGraduatedAt() {
		return graduatedAt;
	}

	public void setGraduatedAt(Date graduatedAt) {
		this.graduatedAt = graduatedAt;
	}
    
}