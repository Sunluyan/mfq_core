package com.mfq.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mfq.annotation.MFQDao;
import com.mfq.bean.coupon.Coupon;
import com.mfq.constants.CouponStatus;

@MFQDao
public interface CouponMapper {

    public long insertOne(Coupon coupon);

    public Coupon findByCouponNum(@Param("couponNum") String couponNum);

    public List<Coupon> findByUid(@Param("uid") long uid);

    public List<Coupon> findUserValid(@Param("uid") long uid,
            @Param("status") CouponStatus status,
            @Param("list") List<Long> list);

    public long updateStatus(@Param("couponNum") String couponNum, @Param("status") CouponStatus status);

    public Coupon findByUserAndNum(@Param("uid")long uid, @Param("couponNum")String couponNum);

    public long delCoupon(@Param("couponNum")String couponNum);

	public List<Coupon> findCouponsByUidAndStatus(@Param("uid") long uid, @Param("status") CouponStatus couponStatus);

}