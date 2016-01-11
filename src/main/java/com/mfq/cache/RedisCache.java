package com.mfq.cache;

import com.mfq.dataservice.cache.IRedis;
import com.mfq.dataservice.cache.impl.RedisCacheManipulater;

public class RedisCache {

    private IRedis getMsRedis() {
        return new RedisCacheManipulater();
    }

    public boolean setWithExpiration(String code, String authenticate, int exp) {
        return getMsRedis().setString(code, authenticate, exp);
    }

    public String getString(String code) {
        return getMsRedis().getString(code);
    }

    public boolean exist(String key) {
        return getMsRedis().exists(key);
    }

    public boolean delete(String key) {
        return getMsRedis().delete(key);
    }
    
    public long lpush(String key, String value){
    	return getMsRedis().lpush(key, value);
    }
    
    public String rpop(String key){
    	return getMsRedis().rpop(key);
    }
    
    public String brpop(String key){
    	return getMsRedis().brpop(0, key);
    }
    
}
