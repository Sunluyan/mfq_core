package com.mfq.utils;

import com.alibaba.dubbo.common.utils.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by liuzhiguo1 on 16/1/26.
 */
public class UserVisitUtil {
    //web端用户的每次访问都会被记录下来
    //如果访问不符合规定,就锁定一定时间.
    /**
     * 结构为:
     * {
     * fingerprint:
     * [
     * {visitAt:时间戳,lock:false,locktime:0}
     * {visitAt:时间戳,lock:false,locktime:0}
     * {visitAt:时间戳,lock:true,locktime:10}
     * ],
     * fingerprint:
     * [
     * {visitAt:时间戳,lock:false,locktime:0},
     * {visitAt:时间戳,lock:false,locktime:0},
     * {visitAt:时间戳,lock:false,locktime:0},
     * ]
     * }
     */
    public static Map<Long, List> map = new Hashtable<>();

    public static void setMap(Long fingerprint, boolean lock, Integer locktime) {
        List<Map<Object, Object>> list = map.get(fingerprint);

        if (CollectionUtils.isEmpty(list)) {

            list = new ArrayList<>();
            Map<Object, Object> record = new HashMap<Object, Object>();
            record.put("visitAt", new Date());
            record.put("lock", lock);
            record.put("locktime", locktime);
            list.add(record);
            map.put(fingerprint, list);

        } else {
            Map<Object, Object> record = new HashMap<Object, Object>();
            record.put("visitAt", new Date());
            record.put("lock", lock);
            record.put("locktime", locktime);
            list.add(record);
        }
    }

    public static void valicdateVisit(Map<String, Object> params) throws Exception {
        //第一步根据fingerprint得到该用户的访问记录
        //第二步查看最后一次访问记录是否是锁定,以及锁定时间.如果锁定了没超过锁定时间,则拒绝访问;如果超出锁定时间,就插入一条新的访问记录
        //第三步如果没锁定,就插入一条访问记录.
        Long fingerprint = 0l;
        try {
            fingerprint = Long.parseLong(params.get("fingerprint").toString());
        } catch (Exception e) {
            throw new Exception("不合法的访问");
        }
        long now = new Date().getTime();
        List list = map.get(fingerprint);
        if(CollectionUtils.isEmpty(list)){
            Map map = new HashMap();
            map.put("visitAt",now);
            map.put("lock",false);
            map.put("locktime",0);
        }

        for (int i = list.size(); i >= 0; i--) {
            Map map = (Map) list.get(i);
            
        }

    }

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
    }


}
