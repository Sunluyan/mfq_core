package com.mfq.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mfq.annotation.MFQDao;
import com.mfq.bean.HomeClassify;

@MFQDao
public interface HomeClassifyMapper {

    public long insertHomeClassify(HomeClassify classify);
    
    public HomeClassify findById(@Param("id") long id);
    
    public List<HomeClassify> queryAll();
    
    public long updateHomeClassify(HomeClassify model);
    
    public long deleteHomeClassify(@Param("id") long id);
    
}