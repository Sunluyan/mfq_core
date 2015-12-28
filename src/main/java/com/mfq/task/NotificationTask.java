package com.mfq.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NotificationTask {
    
	private static Logger logger = LoggerFactory.getLogger(NotificationTask.class);
	
	public String getAccessToken() {
		logger.info("任务");
		return "MailTask";
	}
}
