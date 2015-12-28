package com.mfq.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mfq.annotation.MFQDao;
import com.mfq.bean.passport.ConnectSite;
import com.mfq.bean.passport.PassportOauth;

@MFQDao
public interface PassportOauthMapper {

    public boolean createPassportOauth(PassportOauth oauth);

    public boolean unbindPassportOauth(@Param("uid") long uid,
            @Param("uuid") String uuid);

    public boolean delPassportOauth(@Param("uid") long uid,
            @Param("uuid") String uuid);

    public List<PassportOauth> queryPassportOauthByUid(@Param("uid") long uid);

    public PassportOauth queryPassportOauth(@Param("connectSite") ConnectSite connectSite,
            @Param("uuid") String uuid);

    public PassportOauth queryPassportOauthByUidAndSiteType(
            @Param("uid") long uid,
            @Param("connectSite") ConnectSite connectSite);

    public List<PassportOauth> queryPassportOauthByUidsAndSiteType(
            @Param("list") List<Integer> uids,
            @Param("connectSite") ConnectSite siteType);

    public boolean updatePassportOauth(PassportOauth oauth);

    public boolean updatePassportOauthExtra(@Param("uid") long uid,
            @Param("siteType") int siteType, @Param("extra") String extra);

    public boolean updateSync(@Param("uid") long uid,
            @Param("connectSite") ConnectSite connectSite,
            @Param("sync") boolean sync);
}
