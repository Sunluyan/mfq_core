package com.mfq.bean.user;

import java.util.Date;

import org.springframework.beans.BeanUtils;

public class User implements java.io.Serializable {

    private static final long serialVersionUID = -5974700168528723202L;

    long uid; // required 用户id
    Status status; // required	
    String nick; // required
    String email; // required
    String mobile; // required
    Gender gender; // optional 0未设置 1男 2女
    String origin;	//原籍
    String icon; // optional
    String img; // optional
    String pic; // optional
    int locationId;
    String location;
    int birthday; // optional
    String intro; // optional
    String interest; // optional
    int career; // optional
    Date createdAt; // optional
    Date updatedAt; // optional
    int sign; // optional

    public User() {
    }

    public User(User other) {
        BeanUtils.copyProperties(other, this);
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public Status getStatus() {
        return status;
    }

    public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setStatus(Status status) {
        this.status = status;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getBirthday() {
        return birthday;
    }

    public void setBirthday(int birthday) {
        this.birthday = birthday;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public int getCareer() {
        return career;
    }

    public void setCareer(int career) {
        this.career = career;
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

    public int getSign() {
        return sign;
    }

    public void setSign(int sign) {
        this.sign = sign;
    }

	@Override
	public String toString() {
		return "User [uid=" + uid + ", status=" + status + ", nick=" + nick
				+ ", email=" + email + ", mobile=" + mobile + ", gender="
				+ gender + ", origin=" + origin + ", icon=" + icon + ", img="
				+ img + ", pic=" + pic + ", birthday=" + birthday + ", intro="
				+ intro + ", interest=" + interest + ", career=" + career
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
				+ ", sign=" + sign + "]";
	}
    
    
}
