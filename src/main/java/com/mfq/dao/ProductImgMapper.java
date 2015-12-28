package com.mfq.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mfq.annotation.MFQDao;
import com.mfq.bean.ProductImg;

@MFQDao
public interface ProductImgMapper {

    public List<ProductImg> findByPid(@Param("pid") long pid);
    
    public long insertImg(ProductImg model);
    
    public void updateOne(ProductImg model);
}