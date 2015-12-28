package com.mfq.cache;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MsgTmplContext {

    private static Logger log = LoggerFactory.getLogger(MsgTmplContext.class);

    private static final Locale locale = new Locale("zh", "CN");

    private static Map<String, String> smsMap = new HashMap<String, String>();
    private static Map<String, String> emailMap = new HashMap<String, String>();
    
    private static ResourceBundle smsResource = null;
    private static ResourceBundle emailResource = null;

    public static String getSmsTmpl(String key) {
        return smsMap.get(key);
    }
    
    public static String getMailTmpl(String key){
        return emailMap.get(key);
    }

    static {
        try {
            log.info("MsgTmplContext init start......");
            smsResource = ResourceBundle.getBundle("sms_tmpl", locale);
            emailResource = ResourceBundle.getBundle("email_tmpl", locale);

            java.util.Enumeration<String> smsEnum = smsResource.getKeys();
            while (smsEnum.hasMoreElements()) {
                String key = smsEnum.nextElement();
                smsMap.put(key, convertString(smsResource.getString(key)));
            }

            java.util.Enumeration<String> emailEnum = emailResource.getKeys();
            while (emailEnum.hasMoreElements()) {
                String key = emailEnum.nextElement();
                emailMap.put(key, convertString(emailResource.getString(key)));
            }
            log.info("MsgTmplContext init end......");
        } catch (Exception mre) {
            log.error("MsgTmplContext_Error", mre);
        }
    }

    private static String convertString(String str) {
        try {
            return new String(str.getBytes("ISO8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error("error: str=" + str, e);
            return "";
        }
    }
}