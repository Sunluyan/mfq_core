package com.mfq.dao;

import org.apache.ibatis.annotations.Param;

import com.mfq.annotation.MFQDao;
import com.mfq.bean.notification.MsgRead;
import org.springframework.stereotype.Component;

@MFQDao
@Component
public interface MsgReadMapper {
    
    public long insertMsgRead(MsgRead read);

	public MsgRead queryMsgRead(@Param("uid")long uid,@Param("msgId") long id);

    public long CountNewMsg(@Param("uid") long uid);
}
