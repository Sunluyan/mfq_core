package com.mfq.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mfq.dataservice.context.SpringWrapper;
import com.mfq.service.OrderCloseService;
import com.mfq.task.base.DefaultTask;

public class OrderCloseTask extends DefaultTask {
    
	private static final Logger logger = LoggerFactory.getLogger(OrderCloseTask.class);
	
    @Override
    public String getTaskName() {
        return "Order_Close_Task";
    }

    @Override
    public void doTask() throws Exception {
    	logger.info("task  start....");
        OrderCloseService service = (OrderCloseService) SpringWrapper
                .getBean("OrderCloseService");
        service.start();
    }
}