package com.mfq.dao;

import org.apache.ibatis.annotations.Param;

import com.mfq.annotation.MFQDao;
import com.mfq.bean.SysConfig;

@MFQDao
public interface SysConfigMapper {

	public Long setConfig(SysConfig config);
	
	public SysConfig getConfig(@Param("key") String key);

	public long updateConfigByKey(SysConfig config);
}
