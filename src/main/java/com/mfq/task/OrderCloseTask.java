package com.mfq.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mfq.dataservice.context.SpringWrapper;
import com.mfq.service.OrderCloseService;
import com.mfq.task.base.DefaultTask;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class OrderCloseTask extends DefaultTask {
    
	private static final Logger logger = LoggerFactory.getLogger(OrderCloseTask.class);
	
    @Override
    public String getTaskName() {
        return "Order_Close_Task";
    }

    @Override
    public void doTask() throws Exception {
    	logger.info("task  start....");

        ApplicationContext context = new ClassPathXmlApplicationContext("spring/spring.xml");
        OrderCloseService service = context.getBean(OrderCloseService.class);
//        service.start();
    }
}