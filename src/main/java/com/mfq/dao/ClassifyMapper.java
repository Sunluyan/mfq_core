package com.mfq.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mfq.annotation.MFQDao;
import com.mfq.bean.ProductClassify;

@MFQDao
public interface ClassifyMapper {

    public long insertOne(ProductClassify productClassify);

    public ProductClassify findById(@Param("id") int id);

    public List<ProductClassify> findByRootId(@Param("rootId") int rootId);

	public List<Integer> findClassIdsByRootId(@Param("rootId") int rootId);

}