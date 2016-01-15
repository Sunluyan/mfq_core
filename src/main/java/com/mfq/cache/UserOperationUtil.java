package com.mfq.cache;

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

    public static List<Map<String,Object>> typeOperation = new ArrayList<>();

    public static List<Map<String,Object>> proOperation = new ArrayList<>();

    //openCount 里的结构是 [{pid12:2},{typeId21:43}],键为产品或类别编号,值是点击次数
    public static Map<String,Integer> openCount = new HashMap<>();

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
    }

    /**
     * 记录用户打开过的分类
     * @param uid
     */
    public static void setType(long uid , int typeId){
        Map<String,Object> map = new HashMap<>();
        map.put("uid",uid);
        map.put("typeId",typeId);
        map.put("date",new Date());
        typeOperation.add(map);
        iRedis.set("typeOperation",Integer.MAX_VALUE,typeOperation);
    }
    /**
     * 记录用户打开过的产品
     * @param uid
     * @param pid
     */
    public static void setProduct(long uid , int pid){
        Map<String,Object> map = new HashMap<>();
        map.put("uid",uid);
        map.put("pid",pid);
        map.put("date",new Date());
        proOperation.add(map);
        iRedis.set("proOperation",Integer.MAX_VALUE,proOperation);
    }

    public static List<Map<String,Object>> getTypeOperation(){
        iRedis.delete("typeOperation");
        return typeOperation;
    }
    public static List<Map<String,Object>> getProOperation(){
        iRedis.delete("proOperation");
        return proOperation;
    }

    /**
     * 添加产品或分类的打开次数,
     * @param proOrType 类型
     * @param id    pid || typeId
     */
    public static void addOpenCount(String proOrType,Integer id){
        Map<String,Integer>  map = new HashMap<>();
        if(proOrType.equals("pro")){
            map.put("pid"+id,map.get("pid"+id) == null?0:map.get("pid"+id)+1);
        }else if(proOrType.equals("type")){
            map.put("typeId"+id,map.get("typeId"+id) == null?0:map.get("typeId"+id)+1);
        }else{
            //Do Nothing
        }
    }

    public static Map<String,Integer> getOpenCount(){
        //拿到之后记得赶紧把openCount清空
        Map<String,Integer> result = new HashMap<>();
        result.putAll(openCount);
        openCount.clear();
        return result;
    }



}
