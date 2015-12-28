package com.mfq.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.MDC;

import com.mfq.bean.passport.Passport;
import com.mfq.bean.user.User;
import com.mfq.cache.TicketCacheUtils;
import com.mfq.constants.Constants;
import com.mfq.dataservice.context.AppContext;
import com.mfq.dataservice.context.UserIdHolder;
import com.mfq.helper.MobileHelper;
import com.mfq.utils.Base62;
import com.mfq.utils.CookieUtils;
import com.mfq.utils.DnsUtil;
import com.mfq.utils.RequestUtils;
import com.mfq.utils.UserTraceLogger;

public class UserTraceFilter implements Filter {

    private static final UserTraceLogger userTrace = UserTraceLogger
            .getLogger(UserTraceFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
     
        long startTime = System.currentTimeMillis();

        AppContext.setIp(getIp(req));
        boolean needLog = isNeedLog(req);
        AppContext.setUserTrace(needLog);

        User user = null;
        Passport cookiePassport = null;
        String mm = null;
        String mobileType = MobileHelper.getMobileType(req);
        if (MobileHelper.isApp(req)) { // 无线用户
            mm = MobileHelper.getMobileMM(req);
            cookiePassport = CookieUtils.parsePassport(mm);
            try {
                user = TicketCacheUtils.validateUser(cookiePassport, true);
            } catch (Exception e) {
                userTrace.error("UserValidate PassportCookie Exception", e);
            }
        } else {
            // mobile不读取cookie
        }
        String traceId = null;

        if ((cookiePassport != null && cookiePassport.getUid() > 0)
                && (user == null || user.getUid() <= 0)) { // 票据过期，重置票据
            mm = CookieUtils.getGuestToken(req, resp, AppContext.isApp());
            userTrace.info("UserTraceFilter  Token Expired  mm = " + mm);
        }
        if (user != null && user.getUid() > 0) {
        	
        	
        	String channelId = MobileHelper.getMobileChannelId(req);
        	userTrace.info("登录用户为, user Id = "+user.getUid()+" channelId = "+channelId);
        	UserIdHolder.updateChannelId(user.getUid(), channelId, mobileType);
        	
            UserIdHolder.setUserId(user.getUid());
            request.setAttribute(Constants.CURRENT_XID_ATTRIBUTE,
                    Base62.encode(user.getUid()));
            traceId = cookiePassport.getTicket();
        } else if (cookiePassport != null
                && StringUtils.isNotBlank(cookiePassport.getTicket())) { // guest访问
        	UserIdHolder.setUserId(0);
            traceId = cookiePassport.getTicket();
        }
        if (StringUtils.isNotBlank(mm)) { // guest已访问过产生过m值！
            traceId = mm;
        } else { // guest首次访问
        	UserIdHolder.setUserId(0);
            traceId = CookieUtils.getGuestToken(req, resp, AppContext.isApp());
            userTrace.info("Guest_USER_First_Access, Generate Token:" + traceId);
        }

        resp.setHeader(CookieUtils.COOKIE_MM, traceId); // 用于返回给无线的token

        UserTraceLogger.setTraceId(traceId);
        MDC.put("device", AppContext.getUuid());
        MDC.put("traceid", traceId);

        // 处理cookie的逻辑 ,request 中放入userId
        String nxid = kickNXID(req, resp);
        String logParams = "";
        if (userTrace.isInfoEnabled() && needLog) {
            logParams = RequestUtils.formatRequestParameters(req);
            userTrace.info("mt=" + mobileType + ", device="
                    + AppContext.getUuid() + ", traceId=" + traceId
                    + ", userId=" + UserIdHolder.getUserId() + ", ip="
                    + AppContext.getIp() + ", nxid=" + nxid + ", request="
                    + req.getRequestURI() + ", params=" + logParams);
        }
        
        
        try {
            chain.doFilter(request, response);
        } catch (IOException e) {
            logError(e);
            throw e;
        } catch (ServletException e) {
            logError(e);
            throw e;
        } catch (RuntimeException e) {
            logError(e);
            throw e;
        } finally {
            if (userTrace.isInfoEnabled() && needLog) {
                userTrace.info("mt=" + mobileType + ", traceId=" + traceId
                        + ", userId=" + UserIdHolder.getUserId() + ", ip="
                        + AppContext.getIp() + ", nxid=" + nxid + ", request="
                        + req.getRequestURI() + ", params=" + logParams
                        + " Request End, Time="
                        + (System.currentTimeMillis() - startTime));
            }
            MDC.remove("traceid");
            MDC.remove("device");
        }
    }

    private String getIp(HttpServletRequest req) {
        String ip = req.getHeader("x-forwarded-for");
        if (StringUtils.isBlank(ip)
                || StringUtils.equalsIgnoreCase("unknown", ip)) {
            ip = req.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ip)
                || StringUtils.equalsIgnoreCase("unknown", ip)) {
            ip = req.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ip)
                || StringUtils.equalsIgnoreCase("unknown", ip)) {
            ip = req.getRemoteAddr();
        }
        if (StringUtils.equalsIgnoreCase("127.0.0.1", ip)) {
            ip = DnsUtil.getHostAddress();
        }
        if (StringUtils.length(ip) > 15 && StringUtils.indexOf(ip, ",") > 0) {
            ip = StringUtils.substring(ip, 0, StringUtils.indexOf(ip, ","));
        }
        return ip;
    }

    private boolean isNeedLog(HttpServletRequest req) {
        String uri = req.getRequestURI();

        if (uri.endsWith(".jsp") || uri.endsWith(".css") || uri.endsWith(".js")
                || uri.endsWith(".jpg") || uri.endsWith(".png")
                || uri.startsWith("/version") || uri.startsWith("/favicon.ico")
                || uri.startsWith("/healthcheck.html")) {
            return false;
        }

        return true;
    }

    private void logError(Exception e) {
        userTrace.error("Request Error", e);
    }

    private String kickNXID(HttpServletRequest request,
            HttpServletResponse response) {
        String nxid = (String) request.getAttribute(Constants.NXID_ATTRIBUTE);
        if (nxid == null) {
            nxid = CookieUtils.readNxid(request);
            if (nxid != null) {
                request.setAttribute(Constants.NXID_ATTRIBUTE, nxid);
            }
        }
        return nxid;
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}