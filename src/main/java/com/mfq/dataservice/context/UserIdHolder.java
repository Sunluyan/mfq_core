package com.mfq.dataservice.context;

import com.mfq.service.NotificationService;

/**
 * 用户id的线程上下文
 * @author xingyongshan
 * @since 2015-08-10
 */
public class UserIdHolder {

    private static ThreadLocal<Long> USER_ID = new ThreadLocal<Long>();

    /**
     * 当前用户的id，如果未登录则为null
     * 
     * @return 用户id or null
     */
    public static Long getUserId() {
        return USER_ID.get();
    }

    /**
     * 获取当前用户的数字uid
     * 
     * @return 如果用户不存在，则返回0
     */
    public static long getLongUid() {
        Long uid = getUserId();
        return uid == null ? 0 : uid.longValue();
    }

    public static void setUserId(long userId) {
        USER_ID.set(userId);
    }

    public static void clearUserId() {
        USER_ID.remove();
    }

    /**
     * 用户是否登录
     * 
     * @return 用户是否登录
     */
    public static boolean isLogin() {
        return getLongUid() > 0;
    }

	public static void updateChannelId(long uid, String mobileChannelId, String mobileType) {
		NotificationService service = (NotificationService)SpringWrapper.getBean("notificationService");
		service.updateChannelId(uid, mobileChannelId, mobileType);
	}
}
