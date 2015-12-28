package com.mfq.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class BeanUtil {
	
	public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {    
        if (map == null)  
            return null;  
   
        Object obj = beanClass.newInstance();  
   
        org.apache.commons.beanutils.BeanUtils.populate(obj, map);  
   
        return obj;  
    }    
       
	public static Map<String, Object> objectToMap(Object obj) throws Exception {    
        if(obj == null){    
            return null;    
        }   
   
        Map<String, Object> map = new HashMap<String, Object>();    
   
        Field[] declaredFields = obj.getClass().getDeclaredFields();    
        for (Field field : declaredFields) {    
            field.setAccessible(true);  
            map.put(field.getName(), field.get(obj));  
        }    
   
        return map;  
    }   
}
