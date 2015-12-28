package com.mfq.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.mfq.annotation.MFQDao;
import com.mfq.bean.Vcode;

@MFQDao
public interface VcodeMapper {

    public Vcode queryBykey(@Param("key") String key);

    public Long insertVcode(@Param("key") String key, @Param("code") String code,
            @Param("expireAt") Date expireAt, @Param("resendAt") Date resendAt);

    public Long updateVcode(@Param("key") String key, @Param("code") String code,
            @Param("expireAt") Date expireAt, @Param("resendAt") Date resendAt, @Param("nowAt") Date nowAt);

    public Long updateRetryTime(@Param("key") String key);
}
