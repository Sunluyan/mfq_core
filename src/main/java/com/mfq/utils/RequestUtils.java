package com.mfq.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.util.UrlPathHelper;

public class RequestUtils {

    public static final String ENCODING = "UTF-8";
    public static final long EXPIRED_TIME = 1350264600000L;

    private static final String callBackFormat = "%s(%s);";
    private static final String jsonpFormat = "var %s=%s;";

    /**
     * 获取request中，类型为Boolean的key的值，支持"true/1/on"。
     *
     * @param request
     *            需要解析的HttpServletRequest
     * @param key
     *            需要取出的属性名
     * @param defaultValue
     *            设置默认值
     * @return Boolean类型的属性值
     */
    public static boolean getBoolean(HttpServletRequest request, String key,
            boolean defaultValue) {
        return ParamUtils.getBoolean(request.getParameter(key), defaultValue);
    }

    /**
     * 获取request中，类型为Short的key的值
     *
     * @param request
     *            需要解析的HttpServletRequest
     * @param key
     *            需要取出的属性名
     * @param defaultValue
     *            设置默认值
     * @return Short类型的属性值
     */
    public static short getShort(HttpServletRequest request, String key,
            short defaultValue) {
        String str = request.getParameter(key);
        return ParamUtils.getShort(str, defaultValue);
    }

    /**
     * 获取request中，类型为Int的key的值
     *
     * @param request
     *            需要解析的HttpServletRequest
     * @param key
     *            需要取出的属性名
     * @param defaultValue
     *            设置默认值
     * @return Int类型的属性值
     */
    public static int getInt(HttpServletRequest request, String key,
            int defaultValue) {
        String str = request.getParameter(key);
        return ParamUtils.getInt(str, defaultValue);
    }

    /**
     * 获取request中，类型为Long的key的值
     *
     * @param request
     *            需要解析的HttpServletRequest
     * @param key
     *            需要取出的属性名
     * @param defaultValue
     *            设置默认值
     * @return Long类型的属性值
     */
    public static long getLong(HttpServletRequest request, String key,
            long defaultValue) {
        String str = request.getParameter(key);
        return ParamUtils.getLong(str, defaultValue);
    }

    public static double getDouble(HttpServletRequest request, String key,
            double defaultValue) {
        String str = request.getParameter(key);
        return ParamUtils.getDouble(str, defaultValue);
    }

    /**
     * 获取request中，类型为String的key的值
     *
     * @param request
     *            需要解析的HttpServletRequest
     * @param key
     *            需要取出的属性名
     * @param defaultValue
     *            设置默认值
     * @return String类型的属性值
     */
    public static String getString(HttpServletRequest request, String key,
            String defaultValue) {
        String str = request.getParameter(key);
        return ParamUtils.getString(str, defaultValue);
    }

    public static String getHeaderString(HttpServletRequest request,
            String key) {
        return request.getHeader(key);
    }

    /**
     * 获取request中，类型为String的key，如果传入的值不在合法值集合中，则返回默认值
     *
     * @param request
     *            需要解析的HttpServletRequest
     * @param key
     *            需要取出的属性名
     * @param validValues
     *            合法值
     * @param defaultValue
     *            设置默认值
     * @return String类型的属性值
     */
    public static String getString(HttpServletRequest request, String key,
            String[] validValues, String defaultValue) {
        boolean caseSensitive = false;
        return getString(request, key, validValues, defaultValue,
                caseSensitive);
    }

    /**
     * 获取request中，类型为String的key，如果传入的值不在合法值集合中，则返回默认值
     *
     * @param request
     *            需要解析的HttpServletRequest
     * @param key
     *            需要取出的属性名
     * @param validValues
     *            可以使用的值
     * @param defaultValue
     *            合法值
     * @param caseSensitive
     *            大小写是否敏感
     * @return String类型的属性值
     */
    public static String getString(HttpServletRequest request, String key,
            String[] validValues, String defaultValue, boolean caseSensitive) {
        String str = request.getParameter(key);
        return ParamUtils.getString(str, validValues, defaultValue,
                caseSensitive);
    }

    /**
     * 设置缓存过期时间
     *
     * @param response
     *            响应头
     * @param unit
     *            时间单元
     * @param time
     *            过期时间
     */
    public static void setCacheHeader(HttpServletResponse response,
            TimeUnit unit, long time) {
        response.setHeader("Pragma", "Public");
        // HTTP 1.1
        response.setHeader("Cache-Control", "max-age=" + unit.toSeconds(time));
        response.setDateHeader("Expires",
                System.currentTimeMillis() + unit.toMillis(time));
    }

    /**
     * 设置过期时间，增加CDN和浏览器缓存策略
     *
     * @param response
     *            响应头
     * @param time
     *            过期时间，单位秒
     */
    public static void setCacheHeader(HttpServletResponse response, long time) {
        setCacheHeader(response, TimeUnit.SECONDS, time);
    }

    /**
     * 设置Response头，加入no-cache属性
     * <p>
     * 对于HTTP头HEADER会作如下处理：
     * <ul>
     * <li>Expires: 0</li>
     * <li>RHOST: 后端IP地址信息 {@link DnsUtil#getAllLocalSiteAddress()}</li>
     * <li>Cache-Control: no-cache</li>
     * <li>Pragma: no-cache</li>
     * </ul>
     * </p>
     *
     * @param response
     *            响应头
     */
    public static void setNoCacheHeader(HttpServletResponse response) {
        if (!response.containsHeader("Expires")) {
            response.setDateHeader("Expires", EXPIRED_TIME);
        }
        if (!response.containsHeader("RHOST")) { // 如果没有写入RHOST，则写入一次
            response.setHeader("RHOST",
                    DnsUtil.getAllLocalSiteAddress() + "@" + PidUtil.getPid());
        }
        // HTTP 1.0
        if (!response.containsHeader("Pragma")) { // 如果没有写入RHOST，则写入一次
            response.setHeader("Pragma", "no-cache");
        }
        // HTTP 1.1
        if (!response.containsHeader("Cache-Control")) { // 如果没有写入RHOST，则写入一次
            response.setHeader("Cache-Control", "no-cache, no-store");
        }
    }

    /**
     * 向Response写入数据，通常适用于写入字符串、Json数据、XML数据等，写入成功后会刷新输出流。
     * <p>
     * 此操作会写入如下信息：
     * <ol>
     * <li>如果<code>request</code>中包含参数_cb=doit，则写入格式为: doit(obj.toString());
     * </li>
     * <li>如果<code>request</code>中包含参数_vn=doit，则写入格式为: var doit=obj.toString();
     * </li>
     * <li>否则，写入格式为:obj.toString()</li>
     * </ol>
     * </p>
     * 
     * @param request-HttpServletRequest
     * @param response-HttpServletResponse
     * @param obj-要写入的对象，调用{@link
     *            Object#toString()}写入输出流
     * @throws java.io.IOException-任何网络异常，IO异常
     */
    public static void writeResponse(HttpServletRequest request,
            HttpServletResponse response, Object obj) throws IOException {

        String callback = StringUtils.trimToNull(request.getParameter("_cb"));
        if (callback == null) {
            callback = StringUtils.trimToNull(request.getParameter("callback"));
        }
        String jsonp = StringUtils.trimToNull(request.getParameter("_vn"));
        boolean javascriptContentType = callback != null || jsonp != null;
        String encoding = response.getCharacterEncoding();
        if (StringUtils.isBlank(encoding)) {
            encoding = ENCODING;
        }
        String contentType = String.format("%s;charset=%s",
                javascriptContentType ? "application/json" : "text/plain",
                encoding);
        response.setContentType(contentType);

        if (callback != null) {
            response.getWriter().write(String.format(callBackFormat, callback,
                    String.valueOf(obj)));
        } else {
            if (jsonp != null) { // 如果是jsonp的跨域则强制写入变量var <jsonp> =
                response.getWriter().write(
                        String.format(jsonpFormat, jsonp, String.valueOf(obj)));
            } else {
                response.getWriter().write(String.valueOf(obj));
            }
        }
        response.getWriter().flush();// 强制Flush
    }

    public static boolean isAjaxRequest(HttpServletRequest request) {
        String xReq = request.getHeader("X-Requested-With");
        return (xReq != null);
    }

    public static String getReferer(HttpServletRequest request) {
        return request.getHeader("Referer");
    }

    /**
     * 获取当前请求的URL（包括schema/host/uri/querystring等)
     * 
     * @param request-Http请求
     * @return 完整的URL地址（包括schema/host/uri/querystring等)
     */
    public static String getRequestURL(HttpServletRequest request) {
        StringBuffer url = request.getRequestURL();
        String queryString = request.getQueryString();
        if (queryString != null) {
            url.append("?").append(request.getQueryString());
        }
        return url.toString();
    }

    public static String encodeURL(String url) {
        try {
            return java.net.URLEncoder.encode(url, ENCODING);
        } catch (UnsupportedEncodingException e) {
            return url;
        }
    }

    public static String decodeURL(String url) {
        try {
            return java.net.URLDecoder.decode(url, ENCODING);
        } catch (UnsupportedEncodingException e) {
            return url;
        }
    }

    public static String toCodeMsg(int code, String msg) {
        return String.format("{\"code\":%d,\"msg\":\"%s\"}", code, msg);
    }

    public static String getLocation(HttpServletRequest request) {
        UrlPathHelper helper = new UrlPathHelper();
        StringBuffer buff = request.getRequestURL();
        String uri = request.getRequestURI();
        String origUri = helper.getOriginatingRequestUri(request);
        buff.replace(buff.length() - uri.length(), buff.length(), origUri);
        String queryString = helper.getOriginatingQueryString(request);
        if (queryString != null) {
            buff.append("?").append(queryString);
        }
        return buff.toString();
    }

    public static String formatRequestParameters(HttpServletRequest request) {
        return formatRequestParameters(request, "=", ", ");
    }

    public static String formatRequestParameters(HttpServletRequest request,
            String equal, String separator) {
        StringBuilder params = new StringBuilder();
        boolean first = true;
        for (Object name : request.getParameterMap().keySet())
            for (String value : request.getParameterValues((String) name)) {
                if (first)
                    first = false;
                else
                    params.append(separator);

                params.append(name).append(equal).append(value);
            }

        return params.toString();
    }
}