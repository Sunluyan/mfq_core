package com.mfq.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringUtil {

	private static final Logger logger = LoggerFactory.getLogger(SpringUtil.class);
	/** Spring框架应用上下文对象 */
	private static ApplicationContext factory = getApplicationContext();

	static {
		getApplicationContext();
	}

	public static void setFactoryBean(ApplicationContext factory) {
		SpringUtil.factory = factory;
	}

	/**
	 * 获得Spring框架应用上下文对象 
	 * @return ApplicationContext
	 */
	public static ApplicationContext getApplicationContext()
	{
		//判断如果 ApplicationContext 的对象 ＝＝ NULL
		if ( factory == null )
		{
			if(logger.isDebugEnabled()) logger.debug("===================================Init Spring's ApplicationContext=========================================");
			try
			{
				
				factory = new ClassPathXmlApplicationContext(new String[]{"applicationContext-faengine.xml","applicationContext-common.xml"
						//,"applicationContext-daoSupport-test.xml"
						});
			}
			catch ( Exception e1 )
			{
				if(logger.isDebugEnabled()) logger.debug("err = " + e1.getMessage());
				e1.printStackTrace();
			}
		}
		//返回ApplicationContext
		return factory;
	}
}