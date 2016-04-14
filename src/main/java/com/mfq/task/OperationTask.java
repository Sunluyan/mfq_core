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
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by liuzhiguo1 on 16/1/15.
 */
@Component
public class OperationTask extends DefaultTask {

    private static final Logger logger = LoggerFactory.getLogger(OperationTask.class);

    @Override
    public String getTaskName() {
        return "OperationTask";
    }

    @Override
    public synchronized void doTask() throws Exception {
        logger.info("operation_task begin.....");
        OperationRecordMapper recordMapper = new ClassPathXmlApplicationContext("spring/spring.xml").getBean(OperationRecordMapper.class);
        try {
            while (UserOperationUtil.proOperation.size() != 0 && UserOperationUtil.proOperation.get(0) != null) {
                recordMapper.insertSelective(UserOperationUtil.proOperation.get(0));
                UserOperationUtil.proOperation.remove(0);
            }
            while (UserOperationUtil.typeOperation.size() != 0 && UserOperationUtil.typeOperation.get(0) != null) {
                recordMapper.insertSelective(UserOperationUtil.typeOperation.get(0));
                UserOperationUtil.typeOperation.remove(0);
            }
            while (UserOperationUtil.searchOperation.size() != 0 &&UserOperationUtil.searchOperation.get(0) != null) {
                recordMapper.insertSelective(UserOperationUtil.searchOperation.get(0));
                UserOperationUtil.searchOperation.remove(0);
            }
        } catch (Exception e) {
            logger.error("operation_task" + e + "\t" + e.getMessage() + "\t" + e.getCause());
            return;
        }

        logger.info("operation_task perfect end.....");

    }

    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring/spring.xml");
        OperationRecordMapper mapper = ac.getBean(OperationRecordMapper.class);
        OperationRecord record = new OperationRecord();
        record.setKeyword("fuck you all");
        record.setUid(2798l);

        mapper.insertSelective(record);

    }


}
















