package com.mfq.dao;

import com.mfq.annotation.MFQDao;
import com.mfq.bean.ProductImage;
import com.mfq.bean.ProductImageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@MFQDao
public interface ProductImageMapper {
    int countByExample(ProductImageExample example);

    int deleteByExample(ProductImageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductImage record);

    int insertSelective(ProductImage record);

    List<ProductImage> selectByExample(ProductImageExample example);

    ProductImage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProductImage record, @Param("example") ProductImageExample example);

    int updateByExample(@Param("record") ProductImage record, @Param("example") ProductImageExample example);

    int updateByPrimaryKeySelective(ProductImage record);

    int updateByPrimaryKey(ProductImage record);
}