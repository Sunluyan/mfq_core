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
import jdk.nashorn.internal.runtime.regexp.joni.constants.OPCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by liuzhiguo1 on 16/1/15.
 */
public class OperationTask extends DefaultTask{

    private static final Logger logger = LoggerFactory.getLogger(OperationTask.class);

    public static IRedis iRedis =  new RedisCacheManipulater();

    @Resource
    OperationRecordMapper recordMapper;

    @Override
    public String getTaskName() {
        return "OperationTask";
    }

    //可能这一步需要做的比较多,所以需要防止并发出现.(其实并发也没事)
    @Override
    public synchronized void doTask() throws Exception {
        logger.info("operation_task begin.....");
        List<OperationRecord> proOperation = UserOperationUtil.getProOperation();
        logger.info("operation_task proOperation begin.....");
        if(CollectionUtils.isNotEmpty(proOperation)){
            for (OperationRecord record : proOperation) {
                recordMapper.insertSelective(record);
            }
        }
        logger.info("operation_task typeOperation begin.....");
        List<OperationRecord> typeOperation = UserOperationUtil.getTypeOperation();
        if(CollectionUtils.isNotEmpty(proOperation)){
            for (OperationRecord record : typeOperation) {
                recordMapper.insertSelective(record);
            }
        }
        logger.info("operation_task searchOperation begin.....");
        List<OperationRecord> searchOperation = UserOperationUtil.getSearchOperation();
        if(CollectionUtils.isNotEmpty(proOperation)){
            for (OperationRecord record : searchOperation) {
                recordMapper.insertSelective(record);
            }
        }
        logger.info("operation_task perfect end.....");

    }

    public static void main(String[] args) {
        ApplicationContext ac= new ClassPathXmlApplicationContext("spring/spring.xml");
        OperationRecordMapper mapper = ac.getBean(OperationRecordMapper.class);
        OperationRecord record = new OperationRecord();
        record.setKeyword("fuck you all");
        record.setUid(2798l);

        mapper.insertSelective(record);

    }


}
















