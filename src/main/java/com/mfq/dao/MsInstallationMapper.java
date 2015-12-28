package com.mfq.dao;

import org.apache.ibatis.annotations.Param;

import com.mfq.annotation.MFQDao;
import com.mfq.bean.Installation;

@MFQDao
public interface MsInstallationMapper {

    public Installation queryInstallationByDeviceTypeAndDeviceToken(
            @Param("deviceType") String deviceType,
            @Param("deviceToken") String deviceToken);

    public long createInstallation(Installation installation);

    public boolean updateInstallations(Installation installation);
}
