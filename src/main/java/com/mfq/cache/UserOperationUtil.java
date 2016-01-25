package com.mfq.cache;

import com.mfq.bean.OperationRecord;
import com.mfq.dataservice.cache.IRedis;
import com.mfq.dataservice.cache.impl.RedisCacheManipulater;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.util.*;

/**
 * Created by liuzhiguo1 on 16/1/14.
 */

public class UserOperationUtil {

    public static final Logger logger = LoggerFactory.getLogger(UserOperationUtil.class);

    public static Vector<OperationRecord> typeOperation = new Vector<>();

    public static Vector<OperationRecord> proOperation = new Vector<>();

    public static Vector<OperationRecord> searchOperation = new Vector<>();


    /**
     * 记录用户打开过的分类
     * @param uid
     */
    public static void setType(long uid , int typeId){
        OperationRecord operationRecord = new OperationRecord();
        operationRecord.setUid(uid);
        operationRecord.setTypeId(typeId);
        operationRecord.setOperationDate(new Date());
        typeOperation.add(operationRecord);
    }
    /**
     * 记录用户打开过的产品
     * @param uid
     * @param pid
     */
    public static void setProduct(long uid , int pid){
        OperationRecord operationRecord = new OperationRecord();
        operationRecord.setUid(uid);
        operationRecord.setProId(pid);
        operationRecord.setOperationDate(new Date());
        proOperation.add(operationRecord);
    }

    public static void setKeyword(long uid ,String keyword){
        OperationRecord operationRecord = new OperationRecord();
        operationRecord.setUid(uid);
        operationRecord.setKeyword(keyword);
        operationRecord.setOperationDate(new Date());
        searchOperation.add(operationRecord);
    }

}
