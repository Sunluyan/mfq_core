package com.mfq.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mfq.annotation.MFQDao;
import com.mfq.bean.notification.Notification;

@MFQDao
public interface NotificationMapper {
    
    public List<Notification> queryNotificationByUid(@Param("start")int start, @Param("size")int size, @Param("uid") long uid, @Param("type") int type);
    
    public List<Notification> queryNotificationByType(@Param("start")int start, @Param("size")int size,@Param("type") int type);
    
    public int updateNotificationStatus(@Param("msgId") long msg_id, @Param("status") int status);

	public long queryNotificationCountByType(@Param("uid") long uid, @Param("type")int type);
    
}
