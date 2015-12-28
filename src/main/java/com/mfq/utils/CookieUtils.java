package com.mfq.utils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mfq.bean.passport.Passport;
import com.mfq.dataservice.context.AppContext;


public class CookieUtils {

    private static final Logger log = LoggerFactory.getLogger(CookieUtils.class);

    // 记录用户token的cookie
    public static final String COOKIE_MM = "m";
    // nginx cookie
    public static final String COOKIE_NXID = "nxid";

    // 记录最近记录的三个用户id
    final static String LAST_LOGIN_IDS = "ls";

    public static final String DEFAULT_DOMAIN = ".5imfq.com";

    public static final int COOKIE_OVER_TIME_MAX = (int) TimeUnit.DAYS.toSeconds(365);

    public static final int COOKIE_OVER_TIME_MIN = (int) TimeUnit.DAYS.toSeconds(30);

    public static final int COOKIE_AGE_SR = 30 * 24 * 3600; //30 days


    /**
     * 写入登录cookie
     *
     * @param req       HTTP请求
     * @param resp      HTTP响应
     * @param passport  passport信息
     * @param autoLogin 是否自动登录（自动登录持久化cookie，非自动登录会话cookie）
     * @param domain    cookie域名，默认是'.5imfq.com'
     */
    public static void setLoginCookie(HttpServletRequest req, HttpServletResponse resp, 
            Passport passport, boolean autoLogin) {
        String xid = Base62.encode(passport.getUid());
        String sessionValue = passport.getTicket() + "-" + xid;
        int maxAge = !autoLogin ? -1 : (int) ((passport.getExpiredAt().getTime() - System.currentTimeMillis()) / 1000);
        log.info("Login _sessionValue:{}",sessionValue);
        //无线APP需要set到header返回
        if(AppContext.isApp()){
            resp.setHeader(COOKIE_MM, sessionValue); // 用于返回给无线的token
        }else{
            setCookie(req, resp, COOKIE_MM, sessionValue, maxAge, true);
            logActiveIds(req, resp, xid);
        }
    }
    
    public static String getGuestToken(HttpServletRequest req, HttpServletResponse resp, boolean isMobile){
        String xid = Base62.encode(0);
        String sessionValue = CommonUtil.createTicket() + "-" + xid;;
        return sessionValue;
    }

    private static void logActiveIds(HttpServletRequest req, HttpServletResponse resp, String xid) {
        String value = getCookieValue(req, LAST_LOGIN_IDS);
        if (value == null || !value.equals(xid)) {
            setCookie(req, resp, LAST_LOGIN_IDS, xid, COOKIE_OVER_TIME_MAX, false);
        }
    }

    /**
     * 从cookie中读取nxid
     *
     * @param request HTTP 请求
     * @return nxid值或者null
     * @see #parseNxid(String)
     */
    public static String readNxid(HttpServletRequest request) {
        String nxid = getCookieValue(request, COOKIE_NXID);
        return parseNxid(nxid);
    }

    public static String readTrackIdByNxid(HttpServletRequest request) {
        String nxid = readNxid(request);
        if (StringUtils.isNotBlank(nxid)) {
            return "nxid:" + nxid;
        }
        return "";
    }

    /**
     * 解析nxid的值
     *
     * @param cookieNxid
     * @return 32位字符串的nxid值，与nginx保持一致
     * @see #readNxid(HttpServletRequest)
     */
    public static String parseNxid(String cookieNxid) {
        if (!StringUtils.isEmpty(cookieNxid)) {
            byte[] bytes = null;
            try{
                bytes = Base64.decodeToByteArray(cookieNxid);
            }catch (Exception e){
                log.error("decodeNxid is null");
                return null;
            }
            // String nxid = "wKgGF1JmHCs7gUgMAwMEAg==";
            StringBuilder s = new StringBuilder(32);
            ByteBuffer buf = ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN);
            while (buf.hasRemaining()) {
                s.append(String.format("%08X", buf.getInt()));
            }
            return s.toString();
        }
        return null;
    }

    /**
     * 编码NXID的cookie值
     *
     * @param nxid nxid解码后的32位值，例如：C801060A143B6F529C5F808502230603
     * @return cookie中编码的nxid值
     */
    public static String encodeNxid(String nxid) {
        if (StringUtils.isBlank(nxid)) {
            return null;
        }
        ByteBuffer bb = ByteBuffer.allocate(nxid.length() / 2).order(ByteOrder.LITTLE_ENDIAN);
        for (int i = 0; i < nxid.length() - 1; i += 8) {
            int x = Integer.parseInt(nxid.substring(i, i + 8), 16);
            bb.putInt(x);
        }
        return Base64.encode(bb.array());
    }

    /**
     * 解析cookie中的m票据
     *
     * @param cookie_mm cookie中的mm值
     * @return 登录票据（仅包含未验证有效性uid/ticket两个字段,无cookie或错误cookie返回null)
     * @see #readPassport(HttpServletRequest)
     */
    public static Passport parsePassport(String cookie_mm) {
        if (!StringUtils.isEmpty(cookie_mm)) {
            String[] fs = cookie_mm.split("-");
            if (fs.length == 2) {
                try {
                    int uid = (int) Base62.decode(fs[1]);
                    String ticket = fs[0];
                    Passport pt = new Passport();
                    pt.setUid(uid);
                    pt.setTicket(ticket);
                    return pt;
                } catch (Exception ex) {
                    // ignore
                }
            }
        }
        return null;
    }

    /**
     * 读取cookie中的m票据
     *
     * @param request HTTP 请求
     * @return 登录票据（仅包含uid/ticket两个字段)
     */
    public static Passport readPassport(HttpServletRequest request) {
        return parsePassport(getCookieValue(request, COOKIE_MM));
    }


    /**
     * 删除HttpServletResponse中的mm票据（如果有的话)
     *
     */
    public static void deletePassport(HttpServletRequest request, HttpServletResponse response) {
        deleteCookie(request, response, getCookie(request, COOKIE_MM), DEFAULT_DOMAIN);
    }

    /**
     * Convenience method for deleting a cookie by name
     *
     * @papram request the current web request
     * @param response the current web response
     * @param cookie   the cookie to delete
     */
    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, Cookie cookie, String domain) {
        if (cookie != null) {
            // Delete the cookie by setting its maximum age to zero
            cookie.setMaxAge(0);

            if (log.isDebugEnabled()) {
                log.debug("deleteCookie(HttpServletResponse, Cookie) -  : cookie=" + cookie); //$NON-NLS-1$
            }

            if (domain == null) {
                domain = DEFAULT_DOMAIN;
            }
            cookie.setDomain(domain);
            cookie.setValue("");

            String ctx = request.getContextPath();
            cookie.setPath(StringUtils.isBlank(ctx) ? "/" : ctx);

            response.addCookie(cookie);
        }
    }

    /**
     * Convenience method to get a cookie by name
     *
     * @param request the current request
     * @param name    the name of the cookie to find
     * @return the cookie (if found), null if not found
     */
    public static Cookie getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        Cookie returnCookie = null;
        if (cookies == null) {
            return returnCookie;
        }
        for (int i = 0; i < cookies.length; i++) {
            Cookie thisCookie = cookies[i];
            if (thisCookie.getName().equals(name)) {
                // cookies with no value do me no good!
                if (!thisCookie.getValue().equals("")) {
                    returnCookie = thisCookie;
                    break;
                }
            }
        }
        return returnCookie;
    }

    /**
     * 获取cookie值
     *
     * @param request HTTP请求
     * @param name    cookie名称
     * @return null为不存在或者cookie值
     */
    public static String getCookieValue(HttpServletRequest request, String name) {
        Cookie cookie = getCookie(request, name);
        if (cookie == null)
            return null;
        return cookie.getValue();
    }


    public static void setCookie(HttpServletRequest request, HttpServletResponse response,
                                 String name, String value, Integer expiry, Boolean isHttpOnly) {
        setCookie(request, response, name, value, expiry, isHttpOnly, DEFAULT_DOMAIN);
    }

    public static Cookie setCookie(HttpServletRequest request,
                                   HttpServletResponse response, String name, String value,
                                   Integer expiry, Boolean isHttpOnly, String domain) {
        Cookie cookie = new Cookie(name, value);
        if (expiry != null) {
            cookie.setMaxAge(expiry.intValue());
        }
        if (StringUtils.isNotBlank(domain)) {
            cookie.setDomain(domain);
        }
        if (isHttpOnly) {
            cookie.setHttpOnly(isHttpOnly);
        }
        String ctx = request.getContextPath();
        cookie.setPath(StringUtils.isBlank(ctx) ? "/" : ctx);
        response.addCookie(cookie);
        return cookie;
    }
}