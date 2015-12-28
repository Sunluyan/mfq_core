package com.mfq.utils;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.mfq.constants.Constants;

public class MFQAdminUtil {

    private static final Logger logger = LoggerFactory
            .getLogger(MFQAdminUtil.class);

    public static boolean isAdminUser(HttpServletRequest request) {
        Map<String, Object> params = JsonUtil.readMapFromReq(request);
        if(!CollectionUtils.isEmpty(params)){
            String makeKey = makeKey(params);
            String getKey = (String)params.get("key");
            if(StringUtils.equalsIgnoreCase(makeKey, getKey)){
                return true;
            }
        }
        logger.warn("User非系统管理系统用户！");
        return false;
    }

    public static String makeKey(Map<String, Object> map) {
        if (CollectionUtils.isEmpty(map)) {
            logger.warn("Map is null or size is little than 1");
            return null;
        }
        SortedMap<String, Object> sort = new TreeMap<String, Object>(map);
        StringBuffer signBuffer = new StringBuffer("");
        for (String key : sort.keySet()) {
            if (StringUtils.equalsIgnoreCase(key, "sign")
                    || StringUtils.equalsIgnoreCase(key, "key")) {
                continue;
            }
            signBuffer.append(key).append("=").append(sort.get(key));
        }
        String sign = MD5Util.md5Digest(
                MD5Util.md5Digest(signBuffer.toString()) + Constants.SEC_KEY);
        logger.info("Make sign:{}, org string is:{}", sign,
                signBuffer.toString());
        return sign;
    }
}