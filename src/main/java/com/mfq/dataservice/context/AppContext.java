package com.mfq.dataservice.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mfq.utils.Config;


public class AppContext {

    private static Logger log = LoggerFactory.getLogger(AppContext.class);

    private static ThreadLocal<String> ip = new ThreadLocal<String>();
    private static ThreadLocal<Boolean> userTrace = new ThreadLocal<Boolean>();
    
    //是否无线
    private static ThreadLocal<Boolean> app = new ThreadLocal<Boolean>();
    //UUID
    private static ThreadLocal<String> uuid = new ThreadLocal<String>();
    
    //这个订单号只能在写日志时候使用
    public final static ThreadLocal<String> orderNo = new ThreadLocal<String>();







    public static String getIp() {
        return ip.get();
    }

    public static void setIp(String ip) {
        AppContext.ip.set(ip);
    }

    public static String getOrderNoForLog() {
        return orderNo.get();
    }

    public static void setOrderNoForLog(String orderno) {
        orderNo.set(orderno);
    }

    public static void removeOrderNo4Order() {
        try {
            orderNo.remove();
        } catch (Exception e) {
            log.error("remvoeOrderNo4Order error", e);
        }
    }
    
    public static boolean isDebugMode() {
        return Config.isDev() && Config.getBooleanItem("debug", false);
    }

    public static void setUserTrace(boolean needLog) {
        userTrace.set(needLog);
    }

    public static boolean isUserTraceEnabled() {
        Boolean ret = userTrace.get();
        if (ret == null) {
            return false;
        }
        return ret;
    }

    public static void setApp(boolean isApp) {
        app.set(isApp);
    }
    
    public static boolean isApp() {
        Boolean ret = app.get();
        if (ret == null) {
            return false;
        }
        return ret;
    }
    
    public static String getUuid() {
        return uuid.get();
    }

    public static void setUuid(String uuid) {
        AppContext.uuid.set(uuid);
    }
}