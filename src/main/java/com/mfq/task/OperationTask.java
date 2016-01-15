package com.mfq.task;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.mfq.cache.UserOperationUtil;
import com.mfq.dataservice.cache.IRedis;
import com.mfq.dataservice.cache.impl.RedisCacheManipulater;
import com.mfq.task.base.DefaultTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by liuzhiguo1 on 16/1/15.
 */
public class OperationTask extends DefaultTask{

    private static final Logger logger = LoggerFactory.getLogger(FinanceTask.class);

    public static IRedis iRedis =  new RedisCacheManipulater();

    @Override
    public String getTaskName() {
        return "OpeationTask";
    }

    @Override
    public void doTask() throws Exception {
        List<Map<String,Object>> proOperation = UserOperationUtil.getProOperation();
        if(CollectionUtils.isNotEmpty(proOperation)){
            StringBuffer stringBuffer = new StringBuffer();
            Integer count = 0;

            for (Map<String, Object> stringObjectMap : proOperation) {
                //TODO 插入操作
            }
        }
    }


}
