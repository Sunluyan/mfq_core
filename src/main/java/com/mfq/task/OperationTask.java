package com.mfq.task;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.mfq.bean.OperationRecord;
import com.mfq.bean.OperationRecordExample;
import com.mfq.bean.Product;
import com.mfq.cache.UserOperationUtil;
import com.mfq.dao.OperationRecordMapper;
import com.mfq.dao.ProductMapper;
import com.mfq.dataservice.cache.IRedis;
import com.mfq.dataservice.cache.impl.RedisCacheManipulater;
import com.mfq.task.base.DefaultTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by liuzhiguo1 on 16/1/15.
 */
public class OperationTask extends DefaultTask{

    private static final Logger logger = LoggerFactory.getLogger(FinanceTask.class);

    public static IRedis iRedis =  new RedisCacheManipulater();

    @Resource
    OperationRecordMapper recordMapper;

    @Override
    public String getTaskName() {
        return "OpeationTask";
    }

    @Override
    public void doTask() throws Exception {
        List<Map<String,Object>> proOperation = UserOperationUtil.getProOperation();
        if(CollectionUtils.isNotEmpty(proOperation)){

            for (Map<String, Object> map : proOperation) {
                // pid count date
                Integer pid = (int)map.get("pid");
                //    public OperationRecord(Long id, Long uid, Integer proId, Integer typeId, Date operationDate) {


            }
        }
    }


}
















