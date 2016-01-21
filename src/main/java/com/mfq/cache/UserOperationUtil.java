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

    public static IRedis iRedis =  new RedisCacheManipulater();

    public static List<OperationRecord> typeOperation = new ArrayList<>();

    public static List<OperationRecord> proOperation = new ArrayList<>();

    public static List<OperationRecord> searchOperation = new ArrayList<>();


    static{
        if(iRedis.get("typeOperation") != null){
            typeOperation = iRedis.get("typeOperation");
        }else{
            iRedis.set("typeOperation",Integer.MAX_VALUE,typeOperation);
        }

        if(iRedis.get("proOperation") != null){
            typeOperation = iRedis.get("proOperation");
        }else{
            iRedis.set("proOperation",Integer.MAX_VALUE,proOperation);
        }

        if(iRedis.get("searchOperation") != null){
            typeOperation = iRedis.get("searchOperation");
        }else{
            iRedis.set("searchOperation",Integer.MAX_VALUE,searchOperation);
        }
    }

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
        iRedis.set("typeOperation",Integer.MAX_VALUE,typeOperation);
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
        iRedis.set("proOperation",Integer.MAX_VALUE,proOperation);
    }

    public static void setKeyword(long uid ,String keyword){
        OperationRecord operationRecord = new OperationRecord();
        operationRecord.setUid(uid);
        operationRecord.setKeyword(keyword);
        operationRecord.setOperationDate(new Date());
        searchOperation.add(operationRecord);
        iRedis.set("searchOperation",Integer.MAX_VALUE,searchOperation);
    }

    public static List<OperationRecord> getTypeOperation(){
        iRedis.delete("typeOperation");
        return typeOperation;
    }
    public static List<OperationRecord> getProOperation(){
        iRedis.delete("proOperation");
        return proOperation;
    }
    public static List<OperationRecord> getSearchOperation(){
        iRedis.delete("searchOperation");
        return searchOperation;
    }

}
