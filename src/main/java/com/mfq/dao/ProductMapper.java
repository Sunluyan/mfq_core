package com.mfq.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mfq.annotation.MFQDao;
import com.mfq.bean.Product;
import com.mfq.constants.ProductType;

@MFQDao
public interface ProductMapper {

    public Product findById(@Param("id") long id);
    
    public long insertProduct(Product p);
    
    public List<Product> findByClass(@Param("city_id") int city, @Param("list") List<Integer> list, @Param("type") ProductType type);

	public List<Product> findByFlag(@Param("city_id") int city, @Param("flag") int flag, @Param("type") ProductType type);

	public long updateViewNum(@Param("pid") long pidNo);

	public List<Product> queryProductsByType(@Param("type") ProductType type);

	public long updateProductRemain(@Param("pid") long pid, @Param("num") int num, @Param("remain") long remainNum);

}
