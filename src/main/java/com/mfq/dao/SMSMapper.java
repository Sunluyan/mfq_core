package com.mfq.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mfq.annotation.MFQDao;
import com.mfq.bean.sms.SMSLog;
import org.springframework.stereotype.Component;

@MFQDao
@Component
public interface SMSMapper {

    public long addSMSLog(SMSLog sms);

    public SMSLog findSMSLogById(@Param("sid") int sid);

    public List<SMSLog> findSMSLogByMobile(@Param("mobile") String mobile);

    public List<SMSLog> findPendingSMSByDateAndStatus(@Param("status") int status, 
            @Param("timedAt") Date timedAt, @Param("sendType") int sendType);

    public List<SMSLog> findSMSLogByStatusWithPage(@Param("status") int status,
            @Param("start") int start, @Param("limit") int limit);

    public boolean updateSMSLogStatus(SMSLog sms);
    
}