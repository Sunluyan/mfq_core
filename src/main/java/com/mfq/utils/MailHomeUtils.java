package com.mfq.utils;

import org.apache.commons.lang.StringUtils;

public class MailHomeUtils {

    public static String getMailHome(String email) {
        if (StringUtils.isBlank(email)) {
            return "";
        }
        String mailUrl = "";
        if (email.contains("gmail")) {
            mailUrl = "https://mail.google.com/mail/";
        } else if (email.contains("vip.sina.com")) {
            mailUrl = "http://vip.sina.com.cn/";
        } else {
            mailUrl = "http://mail." + StringUtils.substringAfterLast(email, "@");
        }
        return mailUrl;
    }
}
