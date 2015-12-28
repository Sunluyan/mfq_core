package com.mfq.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WeChatTask {
    
	private static Logger logger = LoggerFactory.getLogger(WeChatTask.class);
	
	public String getAccessToken() {
		logger.info("任务");
		return "MailTask";
	}
}
