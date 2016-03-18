package com.mfq.service;

import com.mfq.bean.ProductImage;
import com.mfq.bean.ProductImageExample;
import com.mfq.dao.ProductImageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by liuzhiguo1 on 16/3/16.
 */
@Service
public class ProductImageService {

    private static final Logger logger = LoggerFactory.getLogger(ProductImageService.class);

    @Resource
    ProductImageMapper mapper;

    public List<ProductImage> getProductByPid(Long pid){
        ProductImageExample example = new ProductImageExample();
        example.or().andPidEqualTo(pid.intValue());
        return mapper.selectByExample(example);
    }
}
