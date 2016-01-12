package com.mfq.dao;

import org.apache.ibatis.annotations.Param;
import com.mfq.annotation.MFQDao;
import com.mfq.bean.coupon.CouponBatchInfo;
import org.springframework.stereotype.Component;

import java.util.List;

@MFQDao
@Component
public interface CouponBatchInfoMapper {

    public long insertOne(CouponBatchInfo couponBatchInfo);
    
    public CouponBatchInfo findById(@Param("id") long id);

    public CouponBatchInfo findByBatch(@Param("batch") String batch);

    public List<CouponBatchInfo> findByBatchs(@Param("batchs") String batchs);

}