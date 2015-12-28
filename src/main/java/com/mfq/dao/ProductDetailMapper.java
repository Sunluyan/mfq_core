package com.mfq.dao;

import org.apache.ibatis.annotations.Param;

import com.mfq.annotation.MFQDao;
import com.mfq.bean.ProductDetail;

@MFQDao
public interface ProductDetailMapper {

    public ProductDetail findByPid(@Param("pid") long pid);
    
    public long insertDetail(ProductDetail detail);
}
