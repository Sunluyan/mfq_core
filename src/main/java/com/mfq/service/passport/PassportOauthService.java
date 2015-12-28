package com.mfq.service.passport;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mfq.bean.passport.ConnectSite;
import com.mfq.bean.passport.PassportOauth;
import com.mfq.dao.PassportOauthMapper;

@Service
public class PassportOauthService {
    
    @Resource
    PassportOauthMapper mapper;

    public PassportOauth queryPassportOauth(ConnectSite siteType, String uuid){
        return mapper.queryPassportOauth(siteType, uuid);
    }

    public PassportOauth queryPassportOauthByUidAndSiteType(long uid, ConnectSite siteType){
        return mapper.queryPassportOauthByUidAndSiteType(uid, siteType);
    }

    public List<PassportOauth> queryPassportOauthByUidsAndSiteType(List<Integer> uids, ConnectSite siteType){
        return mapper.queryPassportOauthByUidsAndSiteType(uids, siteType);
    }

    public List<PassportOauth> queryPassportOauthByUid(long uid){
        return mapper.queryPassportOauthByUid(uid);
    }

    public boolean createPassportOauth(PassportOauth oauth){
        return mapper.createPassportOauth(oauth);
    }

    public boolean updatePassportOauth(PassportOauth oauth){
        return mapper.updatePassportOauth(oauth);
    }

    public boolean updatePassportOauthExtra(long uid, int siteType, String extra){
        return mapper.updatePassportOauthExtra(uid, siteType, extra);
    }

    public boolean unbindPassportOauth(long uid, String uuid){
        return mapper.unbindPassportOauth(uid, uuid);
    }

    public boolean delPassportOauth(long uid, String uuid){
        return mapper.delPassportOauth(uid, uuid);
    }

    public boolean updateSync(long uid, ConnectSite siteType, boolean sync){
        return mapper.updateSync(uid, siteType, sync);
    }
}
