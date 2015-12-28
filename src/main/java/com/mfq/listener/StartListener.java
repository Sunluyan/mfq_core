package com.mfq.listener;

import java.io.File;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mfq.net.wukongbao.pojo.WkbConstants;
import com.mfq.task.base.ExecuteTaskService;
import com.mfq.utils.Config;


public class StartListener implements ServletContextListener {

    private static Logger logger = LoggerFactory.getLogger(StartListener.class);

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            public void uncaughtException(Thread t, Throwable e) {
                logger.error("uncaughtException", e);
            }
        });
        
        // 定时程序
        String timerFilePath = Config.getItem("timerFilePath");
        if (new File(timerFilePath).exists()) {
            if (logger.isInfoEnabled()) {
                logger.info("Run as Timer！！");
            }
            try {
                ExecuteTaskService.execute();
            } catch (Exception e) {
                logger.error("---------------task didn't registerd!!!!!!!!!", e);
                // 定时任务没注册上
                throw new RuntimeException("task didn't registerd!");
            }
        }
        

//    		System.out.println("INSURE_URL="+WkbConstants.INSURE_URL+", APP_KEY="+WkbConstants.APP_KEY+",INSURE_PRODUCT="+WkbConstants.INSURE_PRODUCT);
        //启动监听
//        RunnerUtils.submit(IDCardCheckService.getInstance());
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		
	}
}
