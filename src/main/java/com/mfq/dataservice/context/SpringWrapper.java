package com.mfq.dataservice.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 由于目前大部分的bean没有在spring中管理,导致无法注入，所以先使用一个wrapper适配一下
 */
public class SpringWrapper implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static ApplicationContext getContext() {
        return context;
    }

    public static <T> T getBean(Class<T> type) throws BeansException {
        return context.getBean(type);
    }

    public static Object getBean(String name) throws BeansException {
        return context.getBean(name);
    }
}