package com.mfq.dao;

import org.apache.ibatis.annotations.Param;

import com.mfq.annotation.MFQDao;
import org.springframework.stereotype.Component;

@MFQDao
@Component
public interface UserLoginMapper {

    public boolean updateUsersLoginActived(@Param("uid") long uid);

    public long createUsersLogin(@Param("uid") long uid, @Param("regIp") String regIp,
            @Param("source") String source,
            @Param("regTrack") String regTrack);

    public boolean updateUsersLogin(@Param("uid") long uid, @Param("loginIp") String loginIp);
}