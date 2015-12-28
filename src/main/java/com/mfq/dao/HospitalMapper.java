package com.mfq.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.mfq.annotation.MFQDao;
import com.mfq.bean.Hospital;

@MFQDao
public interface HospitalMapper {

    public Hospital findById(@Param("id") long id);
    
    public List<Hospital> findAll();
    
    public long insertHospital(Hospital hospital);  
    
    public List<Map<String,Object>> findProCount(@Param("hosid")List<Long> hosid);
}