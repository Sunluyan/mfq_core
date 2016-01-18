package com.mfq.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mfq.annotation.MFQDao;
import com.mfq.bean.sms.SMSConfig;
import org.springframework.stereotype.Component;

@MFQDao
@Component
public interface SMSConfigMapper {

    public boolean insertSMSConfig(SMSConfig config);
    
    public SMSConfig findSMSConfigById(@Param("id") int id);
    
    public SMSConfig findSMSConfigBySn(@Param("sn") String sn);
    
    public SMSConfig findSMSConfigByName(@Param("name") String name);
    
    public List<SMSConfig> findAllSMSConfig();
    
    public boolean updateSMSConfig(SMSConfig sms);
}