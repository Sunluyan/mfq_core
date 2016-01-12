package com.mfq.dao.area;

import org.apache.ibatis.annotations.Param;

import com.mfq.annotation.MFQDao;
import com.mfq.bean.area.City;
import org.springframework.stereotype.Component;

@MFQDao
@Component
public interface CityMapper {

    public City findById(@Param("id") long id);
  
}