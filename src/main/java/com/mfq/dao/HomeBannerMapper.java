package com.mfq.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mfq.annotation.MFQDao;
import com.mfq.bean.HomeBanner;

@MFQDao
public interface HomeBannerMapper {

    public long insertHomeBanner(HomeBanner banner);
    
    public HomeBanner findById(@Param("id") long id);
    
    public List<HomeBanner> queryAll();
    
    public long updateHomeBanner(HomeBanner model);
    
    public long deleteHomeBanner(@Param("id") long id);
    
}