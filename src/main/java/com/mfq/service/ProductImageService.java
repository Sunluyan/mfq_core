package com.mfq.service;

import com.mfq.bean.ProductImage;
import com.mfq.bean.ProductImageExample;
import com.mfq.bean.app.ProductListItem2App;
import com.mfq.constants.ProductImageType;
import com.mfq.dao.ProductImageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    public List<ProductImage> getProductByPidAndType(Long pid, ProductImageType type){
        ProductImageExample example = new ProductImageExample();
        example.or().andPidEqualTo(pid.intValue()).andTypeEqualTo(type.getId());
        return mapper.selectByExample(example);
    }
    public List<ProductImage> getProductByPidsAndType(String pids, ProductImageType type){
        ProductImageExample example = new ProductImageExample();
        List<Integer> list = new ArrayList<>();
        for(int i = 0;i<pids.split(",").length;i++){
            list.add(Integer.parseInt(pids.split(",")[i]));
        }
        example.or().andPidIn(list).andTypeEqualTo(type.getId());
        return mapper.selectByExample(example);
    }

    public void changeProduct2AppItemToSquareImg(List<ProductListItem2App> app){
        String pids = "";
        for (ProductListItem2App productListItem2App : app) {
            pids += productListItem2App.getId()+",";
        }
        List<ProductImage> squares = getProductByPidsAndType(pids, ProductImageType.SQUARE);

        for (ProductListItem2App productListItem2App : app) {
            for (ProductImage square : squares) {
                if(square.getPid() == productListItem2App.getId()){
                    productListItem2App.setImg(square.getImg());
                    continue;
                }
            }
        }
    }
}
