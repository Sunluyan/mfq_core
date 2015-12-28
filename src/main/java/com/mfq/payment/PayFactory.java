package com.mfq.payment;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;
import com.mfq.annotation.PayAPIImpl;

@Component
public class PayFactory {

    @Resource
    List<BasePaymentService> basePaymentServices;

    private static Map<PayAPIType, BasePaymentService> serviceMap = Maps.newHashMap();

    public static BasePaymentService getInstance(String code){
        PayAPIType apiType = PayAPIType.fromCode(code);
        if (apiType == null) {
            return null;
        }
        return serviceMap.get(apiType);
    }
    
    public static BasePaymentService getInstance(PayAPIType apiType) {
        if (apiType == null) {
            return null;
        }
        return serviceMap.get(apiType);
    }

    @PostConstruct
    public void find() {
        for (BasePaymentService abstractSdkService : basePaymentServices) {
            Class<?> aClass = abstractSdkService.getClass();
            PayAPIImpl annotation = aClass.getAnnotation(PayAPIImpl.class);
            if (annotation != null) {
                PayAPIType apiType = annotation.payAPIType();
                serviceMap.put(apiType, abstractSdkService);
            }
        }
    }
    
}
