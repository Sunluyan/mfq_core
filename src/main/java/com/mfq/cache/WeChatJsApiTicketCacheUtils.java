package com.mfq.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 缓存js ticket  2小时过期  redis缓存
 * 
 * @author hui
 *
 */
public class WeChatJsApiTicketCacheUtils {

    private static RedisCache cache = new RedisCache();
    
    private static final Logger logger = LoggerFactory.getLogger(WeChatJsApiTicketCacheUtils.class);

    private static final String TICKET_KEY = "wechat_jsapi_tickets";
    
    private static final int exp = 7000;


    /**
     * 获取某个ticket的缓存用户
     * 
     * @param ticket
     *            票据信息
     * @return 用户信息或者null
     */
    public static String getTicket() {
        String ticket = TICKET_KEY != null ? cache.getString(TICKET_KEY) : null;
        if(ticket == null || "".equals(ticket) ){
        	return null;
        }
        return ticket;
    }

    /**
     * 缓存某个用户
     * 
     * @param ticket
     *            票据信息
     * @param user
     *            用户信息
     */
    public static void cacheTicket(String value) {
        if (value != null || "".equals(value)) {
            cache.setWithExpiration(TICKET_KEY, value, exp);
        }
    }

}