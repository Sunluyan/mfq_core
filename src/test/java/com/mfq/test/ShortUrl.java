package com.mfq.test;

import java.util.Map;

import com.google.common.collect.Maps;
import com.mfq.constants.QiniuBucketEnum;
import com.mfq.filemanipulate.image.ImageType;

public class ShortUrl {   
    public static void main(String[] args) {   
    	Map<String, Object> map = Maps.newHashMap();
    	map.put("amount", 0.1);
    	int amount = (int)(Float.parseFloat("0.1")*100);
    	System.out.println("");
    	
    }   
    
    private static String buildAvatarKey(ImageType type, long userId) {
        return QiniuBucketEnum.AVATAR.getBucket() + "/" + type.getFlag() 
                + "/u_"
                + userId
                + "_"
                + System.currentTimeMillis()
                + ".jpg";
        
    }
    

}   