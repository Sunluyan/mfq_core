package com.mfq.helper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import com.mfq.bean.user.Gender;
import com.mfq.bean.user.SignIndex;
import com.mfq.bean.user.Status;
import com.mfq.bean.user.User;
import com.mfq.constants.Career;
import com.mfq.constants.Constants;
import com.mfq.constants.QiniuBucketEnum;
import com.mfq.utils.MD5Util;
import com.mfq.utils.VerifyUtils;

public class UserHelper {
    
    // 标记app头像
    // private static final String AVATAR_APP_PREFIX = "img/a/";
    public static String getAvatarUrl(User user) {
        String img;
        if (user != null && StringUtils.isNotBlank(user.getImg())) {
            img = user.getImg();
        } else if (user != null && StringUtils.isNotBlank(user.getPic())) {
            img = user.getPic();
        } else if (user != null && StringUtils.isNotBlank(user.getIcon())) {
            img = user.getIcon();
        } else {
            img = Constants.DEFAULT_IMG;
        }
        return QiniuBucketEnum.AVATAR.getDomain() + img;
    }

    public static User setSign(User user, SignIndex... indexs) {
        int sign = user.getSign();
        for (SignIndex index : indexs) {
            sign |= 1 << index.getValue();
        }
        user.setSign(sign);
        return user;
    }

    public static User unsetSign(User user, SignIndex... indexs) {
        int sign = user.getSign();
        for (SignIndex index : indexs) {
            sign &= ~(1 << index.getValue());
        }
        user.setSign(sign);
        return user;
    }

    public static boolean isSetSign(User user, SignIndex index) {
        return isSetSign(user.getSign(), index);
    }

    public static boolean isSetSign(int sign, SignIndex index) {
        return 0 != (sign & (1 << index.getValue()));
    }

    public static boolean isSetSigns(int sign, SignIndex... indexs) {
        for (SignIndex index : indexs) {
            if (0 == (sign & (1 << index.getValue()))) {
                return false;
            }
        }
        return true;
    }

    public static Map<String, Boolean> getUserSignStatus(User user) {
        Map<String, Boolean> signStatus = new HashMap<String, Boolean>();
        for (SignIndex si : SignIndex.values()) {
            signStatus.put(si.name(), isSetSign(user, si));
        }
        return signStatus;
    }

    public static void formatDefaultUser(User user) {
        if (user.getStatus() == null) {
            user.setStatus(Status.INACTIVE);
        }
        if (StringUtils.isBlank(user.getMobile())) {
            user.setMobile(MD5Util.md5Digest(UUID.randomUUID().toString())); // 随机手机号
        }
        if (StringUtils.isBlank(user.getEmail())) {
            user.setEmail(MD5Util.md5Digest(UUID.randomUUID().toString())); // 随机邮件
        }
        if(StringUtils.isBlank(user.getLocation())){
        	user.setLocation("");
        }
        if (StringUtils.isBlank(user.getNick())) {
            user.setNick(getAutoNick(user.getEmail()));
        }
        if (user.getGender() == null) {
            user.setGender(Gender.Unset);
        }
        if (StringUtils.isBlank(user.getIcon())) {
            user.setIcon("");
        }
        if (StringUtils.isBlank(user.getImg())) {
            user.setImg("");
        }
        if (StringUtils.isBlank(user.getPic())) {
            user.setPic("");
        }
        if (StringUtils.isBlank(user.getIntro())) {
            user.setIntro("");
        }
        if (StringUtils.isBlank(user.getInterest())) {
            user.setInterest("");
        }
        if (user.getCareer() == 0) {
            user.setCareer(Career.ELSE.getId());
        }
        if (user.getCreatedAt() == null) {
            user.setCreatedAt(new Date());
        }
        user.setUpdatedAt(new Date());
    }

    public static String getAutoNick(String email) {
        // 1,前缀固定为“M” 2,后面跟上随机的数字，8位 3,数字不含4和7
        String nick = "";
        if (StringUtils.isBlank(email) || !StringUtils.contains(email, "@")) {
            nick = getRuleName();
        } else {
            nick = email.substring(0, email.indexOf('@'));
            nick = StringUtils.substring(VerifyUtils.filterNick(nick), 0, 20)
                    .toLowerCase(); // 校验昵称，截取长度并且转化成小写
            if (NumberUtils.isDigits(nick)) {
                nick = StringUtils.substring(nick, 0, 16)
                        + RandomStringUtils.randomAlphabetic(4); // 不允许纯数字
            }
        }
        return nick;
    }

    private static String getRuleName() {
        // 1,前缀固定为“M” 2,后面跟上随机的数字，8位 3,数字不含4和7
        return "M" + RandomStringUtils.random(8, "01235689");
    }
}
