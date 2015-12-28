package com.mfq.bean.user;

public class BasicUser {

    public int uid; // required
    public Status status; // required
    public String nick; // required
    public String icon; // optional
    public String img; // optional
    public String pic; // optional
    public boolean noicon; // optional

    public BasicUser() {
    }


    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public Status getStatus() {
        return status;
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

    public boolean isNoicon() {
        return noicon;
    }

    public void setNoicon(boolean noicon) {
        this.noicon = noicon;
    }
}

