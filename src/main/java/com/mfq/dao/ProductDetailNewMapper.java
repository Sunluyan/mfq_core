package com.mfq.dao;

import com.mfq.annotation.MFQDao;
import com.mfq.bean.ProductDetailNew;
import com.mfq.bean.ProductDetailNewExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@MFQDao
public interface ProductDetailNewMapper {
    int countByExample(ProductDetailNewExample example);

    int deleteByExample(ProductDetailNewExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductDetailNew record);

    int insertSelective(ProductDetailNew record);

    List<ProductDetailNew> selectByExampleWithBLOBs(ProductDetailNewExample example);

    List<ProductDetailNew> selectByExample(ProductDetailNewExample example);

    ProductDetailNew selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProductDetailNew record, @Param("example") ProductDetailNewExample example);

    int updateByExampleWithBLOBs(@Param("record") ProductDetailNew record, @Param("example") ProductDetailNewExample example);

    int updateByExample(@Param("record") ProductDetailNew record, @Param("example") ProductDetailNewExample example);

    int updateByPrimaryKeySelective(ProductDetailNew record);

    int updateByPrimaryKeyWithBLOBs(ProductDetailNew record);

    int updateByPrimaryKey(ProductDetailNew record);
}