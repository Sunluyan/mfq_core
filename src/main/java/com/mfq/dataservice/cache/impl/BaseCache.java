package com.mfq.dataservice.cache.impl;

import java.util.Collection;
import java.util.Map;

import com.mfq.dataservice.cache.ICache;

/**
 * Cache的基础实现
 * 
 */
public abstract class BaseCache implements ICache {

    private String id;

    private long timeout = 3000L;//超时时间

    @Override
    public long decr(String key, long delta) {
        return decr(key, delta, 0);
    }

    @Override
    public long decr(String key, long delta, long initValue) {
        return decr(key, delta, initValue, getTimeout());
    }

    @Override
    public long decr(String key, long delta, long initValue, long timeout) {
        return decr(key, delta, initValue, timeout, 0);
    }

    @Override
    public boolean delete(String key) {
        return delete(key, getTimeout());
    }

    @Override
    public void destroy() {
        //ignore
    }

    @Override
    public <T> Map<String, T> get(Collection<String> keyCollections) {
        return get(keyCollections, getTimeout());
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T get(String key) {
        return (T) get(key, getTimeout());
    }

    /**
     * 全局的Cache id
     * 
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * get mbean name for Cache
     * 
     * @return mbean name
     */
    String getMbeanName() {
        return "com.mfq.app.cache:type=" + getClass().getSimpleName() + "-" + getId();
    }
    
    public long getTimeout() {
        return timeout;
    }

    @Override
    public long incr(String key, long delta) {
        return incr(key, delta, 0);
    }

    @Override
    public long incr(String key, long delta, long initValue) {
        return incr(key, delta, initValue, getTimeout());
    }

    @Override
    public long incr(String key, long delta, long initValue, long timeout) {
        return incr(key, delta, initValue, timeout, 0);
    }

    @Override
    public boolean set(String key, int exp, Object value) {
        return set(key, exp, value, getTimeout());
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }
}