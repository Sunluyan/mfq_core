package com.mfq.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mfq.bean.notification.MsgRead;
import com.mfq.bean.notification.Notification;
import com.mfq.bean.user.UserExtend;
import com.mfq.constants.ErrorCodes;
import com.mfq.dao.MsgReadMapper;
import com.mfq.dao.NotificationMapper;
import com.mfq.service.user.UserExtendService;
import com.mfq.service.user.UserService;
import com.mfq.utils.JsonUtil;
import com.mfq.utils.ListSortUtil;

@Service
public class NotificationService {

	private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

	@Resource
	UserExtendService userExtendService;
	@Resource
	UserService userService;
	@Resource
	NotificationMapper mapper;
	@Resource
	MsgReadMapper msgReadMapper;
	
	private int pageSize = 10;
	
	public String queryMsgs(long uid, int page, Date time, int type) {

		String ret ="";
		
		if(page > 0)
			page = page -1;
		
		int start = page*pageSize;
		
		Map<String,Object> data = Maps.newHashMap();
		
		List<Notification> noti = Lists.newArrayList();
		
		if(type ==1){
			
			List<Notification> notis = mapper.queryNotificationByType(start, pageSize, 1); 
			long noticount = mapper.queryNotificationCountByType(0, 1);
			for(Notification n: notis){
				MsgRead read = msgReadMapper.queryMsgRead(uid, n.getId());
				if(read != null){
					n.setStatus(1);
					noti.add(n);
				}else{
					noti.add(n);
				}
				
			}
			if(!CollectionUtils.isEmpty(noti)){
	        	ListSortUtil<Notification> sortList = new ListSortUtil<Notification>();
	        	sortList.sort(noti, "created", "desc"); 
	        }
			
			data.put("msg", noti);
			data.put("count", noticount);
			
		}else if(type == 2){
			
			List<Notification> uMsg=mapper.queryNotificationByUid(start , pageSize, uid, 2);
			long msgcount = mapper.queryNotificationCountByType(uid, 2);
			
			if(!CollectionUtils.isEmpty(uMsg)){
	        	ListSortUtil<Notification> sortList = new ListSortUtil<Notification>();
	        	sortList.sort(uMsg, "created", "desc"); 
	        }
			
			data.put("msg", uMsg);
			data.put("count", msgcount);
			
		}else{
			List<Notification> notis = mapper.queryNotificationByType(start, pageSize, 1); 
			long noticount = mapper.queryNotificationCountByType(0, 1);
			for(Notification n: notis){
				MsgRead read = msgReadMapper.queryMsgRead(uid, n.getId());
				if(read != null){
					n.setStatus(1);
					noti.add(n);
				}else{
					noti.add(n);
				}
				
			}
			if(!CollectionUtils.isEmpty(noti)){
	        	ListSortUtil<Notification> sortList = new ListSortUtil<Notification>();
	        	sortList.sort(noti, "created", "desc"); 
	        }
			
			List<Notification> uMsg=mapper.queryNotificationByUid(start , pageSize, uid, 2);
			long msgcount = mapper.queryNotificationCountByType(uid, 2);
			
			if(!CollectionUtils.isEmpty(uMsg)){
	        	ListSortUtil<Notification> sortList = new ListSortUtil<Notification>();
	        	sortList.sort(uMsg, "created", "desc"); 
	        }
			Map<String,Object> count = Maps.newHashMap();
			count.put("notification_count", noticount);
			count.put("msg_count", msgcount);
			data.put("notification", notis);
			data.put("msg", uMsg);
		}
		
		ret = JsonUtil.successResultJson(data);
		
		logger.info("Notification log is {}", ret);
		return ret;
	}
	
	
	public int updateChannelId(long uid, String channelId, String mobileType){
		try{
			if(uid < 1 || StringUtils.isBlank(channelId)){
				return 0;
			}
			UserExtend extend = userExtendService.getUserExtendByUid(uid);
			if(extend == null || extend.getUid() < 0){
				extend = new UserExtend();
				extend.setUid(uid);
				extend.setChannelId(channelId);
				extend.setMobileType(mobileType);
				return userExtendService.insertUserExtend(extend);
			}
			return userExtendService.updateUserChannelId(channelId, mobileType, uid);
		}catch(Exception e){
			logger.error(" updareChannelId error {}",e);
		}
		return 0;
	}


	public String insertMsgRead(long uid, long msg_id, int type) {
		if(uid < 1 || msg_id <1){
			return JsonUtil.toJson(ErrorCodes.CORE_PARAM_UNLAWFUL, "参数异常",
                    null);
		}
		MsgRead read = msgReadMapper.queryMsgRead(uid, msg_id);
		if(read != null){
			return JsonUtil.toJson(0, "已读", null);
		}
		read = new MsgRead();
		read.setUid(uid);
		read.setMsgId(msg_id);
		read.setType(type);
		read.setFlag(0);
		read.setCreated(new Date());
		long result = msgReadMapper.insertMsgRead(read);
		if(result > 0){
			mapper.updateNotificationStatus(msg_id, 1);
			return JsonUtil.toJson(0, "success", null);
		}else{
			return JsonUtil.toJson(1001, "失败", null);
		}
	}

}