package com.mfq.helper;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.mfq.constants.Constants;
import com.mfq.dataservice.context.AppContext;
import com.mfq.utils.CookieUtils;
import com.mfq.utils.RequestUtils;

public class MobileHelper {

    public static boolean isApp(HttpServletRequest req) {
        if (Constants.APP_TYPE.contains(getMobileType(req))
                && StringUtils.isNotBlank(getMobileUid(req))) {
            AppContext.setApp(true);
            return true;
        }else{
            AppContext.setApp(false);
            return false;
        }
    }

    public static String getMobileMM(HttpServletRequest req) {
        return RequestUtils.getHeaderString(req, CookieUtils.COOKIE_MM);
    }

    public static String getMobileVer(HttpServletRequest req) {
        return RequestUtils.getHeaderString(req, "ver");
    }

    public static String getMobileUid(HttpServletRequest req) {
        if(StringUtils.isNotBlank(AppContext.getUuid())){
            return AppContext.getUuid();
        }
        String uuid = RequestUtils.getHeaderString(req, "dtn");
        if(StringUtils.isNotBlank(uuid)){
            AppContext.setUuid(uuid);
        }
        return uuid;
    }

    public static String getMobileType(HttpServletRequest req) {
        return RequestUtils.getHeaderString(req, "dtp");
    } 
    
    public static String getMobileChannelId(HttpServletRequest req) {
    	return RequestUtils.getHeaderString(req, "channel_id");
    }
}