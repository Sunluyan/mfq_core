package com.mfq.utils;

public class OpenIdUtil {

    public static String encode(long uid, Integer seed) {
        long openId = uid+seed*99;
        return Base62.encode(openId);
    }

    public static long decode(String openId, Integer seed) {
        long openIdValue = Base62.decode(openId);
        long uid = (long) (openIdValue-seed*99);
        return uid;
    }
}
