package com.mfq.cache;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheStats;

/**
 * 基于 ip 的限制器, 应作为共享对象复用.
 */
public class IPRateLimiter {

    private final Cache<Long, AtomicInteger> cache;

    /**
     * @param maxRecordTrack 最大记录 ip 数
     * @param duration       记录缓存时间, 单位毫秒
     * @return
     */
    public static IPRateLimiter create(int maxRecordTrack, long duration) {
        return new IPRateLimiter(maxRecordTrack, duration);
    }

    private IPRateLimiter(int maxSize, long duration) {
        cache = CacheBuilder.newBuilder()
                .maximumSize(maxSize)
                .expireAfterWrite(duration, TimeUnit.MILLISECONDS)
                .build();
    }

    /**
     * @param ip
     * @param maxTimes
     * @return true - 未超过限额, false - 超过限额
     */
    public boolean tryAcquire(String ip, int maxTimes) {
        try {
            // -> 
            Long key = ip2long(ip);
            int time = 0;
            if(cache.getIfPresent(key) == null){
                cache.put(key, new AtomicInteger(time));
            }else{
                time = cache.getIfPresent(key).getAndIncrement();
            }
            return time < maxTimes;
        } catch (Exception e) {
            throw new RuntimeException("IPRateLimiter.tryAcquire exception", e);
        }
    }

    public CacheStats status() {
        return cache.stats();
    }

    public void cleanUp() {
        cache.cleanUp();
    }

    private Long ip2long(String ip) {
        long result = 0;
        String[] arr = ip.split("\\.");
        int len = arr.length ;
        for (int i = len; i > 0; i--) {
            long v = Long.parseLong(arr[len - i]);
            result |= v << (i * 8);
        }
        return result;
    }

}