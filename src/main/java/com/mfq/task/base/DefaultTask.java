package com.mfq.task.base;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class DefaultTask implements Runnable {

	protected static Logger log = LoggerFactory.getLogger(DefaultTask.class);

	protected String taskName = null;

	@Override
	public final void run() {
		long startTime = System.currentTimeMillis();
		try {
			taskName = StringUtils.defaultString(getTaskName());
			log.warn("Start task {}", taskName);
			doTask();
		} catch (Exception e) {
			log.error("Execute Task error, taskname={}, error={}", taskName,
					e.getMessage());
		} finally {
			log.info("End task {}, spend {} ms", taskName,
					System.currentTimeMillis() - startTime);
		}
	}

	public abstract String getTaskName();

	public abstract void doTask() throws Exception;
}