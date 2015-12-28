package com.mfq.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mfq.annotation.MFQDao;
import com.mfq.bean.coupon.CouponBatchInfo;

@MFQDao
public interface CouponBatchInfoMapper {

    public long insertOne(CouponBatchInfo couponBatchInfo);
    
    public CouponBatchInfo findById(@Param("id") long id);
    
    public CouponBatchInfo findByBatch(@Param("batch") String batch);

    public List<CouponBatchInfo> findValidByCondition(@Param("condition") BigDecimal condition);
}