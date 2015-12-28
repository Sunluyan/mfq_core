package com.mfq.dataservice.cache.impl;

/**
 * Cache的Mbean操作，主要用于查看当前Cache状态
 *
 */
public interface RedisCacheMBean {

    Integer getMaxPoolCount();

    Integer getCurrentPoolCount();

    long getTotalCallTimes();

    String getId();
}