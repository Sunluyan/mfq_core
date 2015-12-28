package com.mfq.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

public class VerifyUtils {

    private static String emailReg = "^([a-z0-9A-Z]+[-|\\.|_]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    private static String mobileReg = "^1[3|4|5|7|8]\\d{9}$";
    private static String nickReg = "^[a-zA-Z0-9\\u4e00-\\u9fa5_]{2,20}$";
    private static String passwordReg = "^[\\d\\w\\.\\!\\#\\$\\%\\^\\*\\'\\+\\-\\/\\`\\@\\(\\)\\[\\]\\\\\\:\\;\\\"\\,\\<\\>\\?\\=\\_\\{\\|\\}\\~]{6,20}$";
    
    private static String realReg="^[\u4e00-\u9fa5]{2,4}$";
    
    public static boolean verifyEmail(String email) {
        return email != null && email.contains("@") && email.matches(emailReg);
    }

    public static boolean verifyMobile(String mobile) {
        return mobile != null && mobile.matches(mobileReg);
    }

    public static boolean maybeMobile(String mobile) {
        return mobile != null && StringUtils.length(mobile) < 32;
    }

    public static boolean verifyNick(String nick) {
        // 禁止纯数字
        return nick.matches(nickReg) && !NumberUtils.isDigits(nick);
    }

    public static boolean verifyPassword(String password) {
        return password != null && password.matches(passwordReg);
    }
    
    //用户真实姓名 汉字
    public static boolean verifyRealName(String realname) {
        return realname != null && realname.matches(realReg);
    }

    /**
     * 过滤昵称（用户名）中的特殊字符
     * 
     * @param nick:昵称（用户名）
     * @return 过滤后的昵称（仅保留a-zA-Z中文_)
     */
    public static String filterNick(String nick) {
        if (nick == null || nick.length() == 0) {
            return "";
        }
        StringBuilder buf = new StringBuilder(nick.length());
        for (char c : nick.toCharArray()) {

            if ((c >= 'a' && c <= 'z')//
                    || (c >= 'A' && c <= 'Z')//
                    || (c >= '0' && c <= '9')//
                    || c == '_'//
                    || (c >= '\u4e00' && c <= '\u9fa5')

            ) {
                buf.append(c);
            }
        }
        return buf.toString();
    }
}
