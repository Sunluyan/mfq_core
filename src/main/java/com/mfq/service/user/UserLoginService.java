package com.mfq.service.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mfq.dao.UserLoginMapper;

@Service
public class UserLoginService {
    
    @Resource
    UserLoginMapper mapper;
    
    public boolean updateUsersLoginActivedTime(long uid) {
        return mapper.updateUsersLoginActived(uid);
    }
    
    public long createUsersLogin(long uid, String regIp, String source,
            String regTrack) {
        return mapper.createUsersLogin(uid, regIp, source, regTrack);
    }
    
    public boolean updateUsersLogin(long uid, String loginIp) {
        return mapper.updateUsersLogin(uid, loginIp);
    }
}