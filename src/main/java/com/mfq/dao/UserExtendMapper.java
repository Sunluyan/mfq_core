package com.mfq.dao;

import org.apache.ibatis.annotations.Param;
import com.mfq.annotation.MFQDao;
import com.mfq.bean.user.UserExtend;
import org.springframework.stereotype.Component;


@MFQDao
@Component
public interface UserExtendMapper {

    public int insertUserExtend(UserExtend extend);

	public UserExtend queryUserExtendByUid(@Param("uid") long uid);

	public int updateUserExtend(UserExtend extend);

	public long bindInviteCode(@Param("uid") long uid);

	public int updateChannelId(@Param("channelId") String channelId, @Param("mobileType") String mobileType, @Param("uid") long uid);

	public int updateUserInviteCode(@Param("uid")long uid ,@Param("inviteCode")String inviteCode);

	public UserExtend queryUserExtendByInviteCode(@Param("inviteCode")String inviteCode);
}