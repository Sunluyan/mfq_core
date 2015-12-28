package com.mfq.cache;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.mfq.bean.passport.Passport;
import com.mfq.bean.user.User;
import com.mfq.dataservice.context.SpringWrapper;
import com.mfq.service.passport.PassportService;
import com.mfq.service.user.UserService;

/**
 * 为了加速WEB的访问请求，缓存最近3分钟的频繁访问的200个ticket，会有一定风险，后续根据实际线上情况注意！
 * 
 * @author xingyongshan
 *
 */
public class TicketCacheUtils {

    private static Cache<String, User> cache = CacheBuilder.newBuilder()//
            .initialCapacity(1000).maximumSize(1000)// 1000是包括无效的ticket，加入黑名单
            .expireAfterWrite(10, TimeUnit.MINUTES)// 缓存10分钟
            .build();
    
    private static final Logger logger = LoggerFactory.getLogger(TicketCacheUtils.class);

    private static final User DEAD_USER = new User();

    /**
     * 从cookie中验证票据并解析用户信息
     * 
     * @param cookiePassport
     *            cookie中票据(只有uid/ticket)
     * @param userService
     *            用户服务
     * @param passportService
     *            票据服务
     * @param cacheable
     *            是否缓存结果（如果验票成功，缓存用户信息）
     * @return 用户信息或者null
     * @throws Exception
     *             任何异常
     */
    public static User validateUser(Passport cookiePassport, boolean cacheable)
            throws Exception {
        if (cookiePassport == null || cookiePassport.getUid() <= 0) {
            return null;
        }
        String ticket = cookiePassport.getTicket();
        User user = cacheable ? getUser(ticket) : null;
        
        if (user == null) {
            UserService userService = (UserService) SpringWrapper.getBean("userService");
            PassportService passportService = (PassportService) SpringWrapper.getBean("passportService");
            Passport validPassport = passportService
                    .validateTicket(cookiePassport.getUid(), ticket);
            logger.info("----------------"+validPassport.getUid()+"-------------");
            if (validPassport == null || validPassport.getUid() <= 0) {
                // 无效的票据，加入黑名单
                DEAD_USER.setUid(0);
                cache.put(ticket, DEAD_USER);
                return null;
            }
            user = userService.queryUser(validPassport.getUid());
            if (cacheable) {
                cacheUser(ticket, user);
            }
        }
        return user != DEAD_USER ? user : null;
    }

    /**
     * 获取某个ticket的缓存用户
     * 
     * @param ticket
     *            票据信息
     * @return 用户信息或者null
     */
    public static User getUser(String ticket) {
        return ticket != null ? cache.getIfPresent(ticket) : null;
    }

    /**
     * 缓存某个用户
     * 
     * @param ticket
     *            票据信息
     * @param user
     *            用户信息
     */
    public static void cacheUser(String ticket, User user) {
        if (user != null && user.getUid() > 0) {
            cache.put(ticket, user);
        }
    }

    public static void invalidUser(String ticket) {
        if (ticket != null) {
            cache.invalidate(ticket);
        }
    }
}