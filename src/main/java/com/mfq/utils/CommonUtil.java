package com.mfq.utils;

import java.util.UUID;


/**
 * Created by xingyongshan on 15/8/9.
 */
public class CommonUtil {

    // private static Logger log = LoggerFactory.getLogger(CommonUtil.class);

    private CommonUtil() {

    }

    public static String fixSql(String sql) {
        return sql.replaceAll("@#%", "");
    }
    
    public static String createTicket() {
        return MD5Util.md5Digest(UUID.randomUUID().toString());
    }
}