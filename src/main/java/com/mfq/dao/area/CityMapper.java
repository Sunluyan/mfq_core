package com.mfq.dao.area;

import org.apache.ibatis.annotations.Param;

import com.mfq.annotation.MFQDao;
import com.mfq.bean.area.City;

@MFQDao
public interface CityMapper {

    public City findById(@Param("id") long id);
  
}