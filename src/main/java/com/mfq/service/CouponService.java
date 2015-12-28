package com.mfq.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.google.common.collect.Lists;
import com.mfq.bean.app.CouponInfo2App;
import com.mfq.bean.coupon.Coupon;
import com.mfq.bean.coupon.CouponBatchInfo;
import com.mfq.constants.CouponStatus;
import com.mfq.dao.CouponBatchInfoMapper;
import com.mfq.dao.CouponMapper;

/**
 * 优惠券 使用场景： 一、团购订单 1.全款支付，单价大于10000可用500 2.在线＋到院支付方式，优惠券仅用于冲抵到院支付部分（实际到院支付－优惠券）
 * 二、分期订单 可能会分为几种情况：0支付申请分期、余额支付申请分期、余额支付＋优惠券支付申请分期 1.订单金额大于5000
 * 
 * @author xingyongshan
 *
 */
@Service
public class CouponService {
    
    private static final Logger logger = LoggerFactory.getLogger(CouponService.class);

    @Resource
    CouponMapper couponMapper;

    @Resource
    CouponBatchInfoMapper batchMapper;

    /**
     * 构造用户优惠券，并入库
     */
    public void makeAndSaveCouponByBatch(long uid, long batchId) {
        logger.info("start coupon num generate process.....");
        CouponBatchInfo info = batchMapper.findById(batchId);
        Coupon coupon = new Coupon();
        coupon.setBatchId(batchId);
        coupon.setMoney(info.getMoney());
        coupon.setStatus(CouponStatus.INIT);
        coupon.setUid(uid);
        coupon.setUpdatedAt(new Date());
        for (int i = 0; i < 3; i++) {
            Random ran = new Random();
            StringBuilder builder = new StringBuilder();
            for (int j = 0; j < 10; j++) {
                builder.append(ran.nextInt(10));
            }
            coupon.setCouponNum(builder.toString());
            long l = couponMapper.insertOne(coupon);
            if(l > 0){
                break;
            }
        }
        logger.info("end coupon num generate process.....");
    }

    public Coupon findByCouponNum(String couponNum) {
        return couponMapper.findByCouponNum(couponNum);
    }

    public Coupon findValidCoupon(long uid, BigDecimal amount) {
        List<CouponBatchInfo> lbi = batchMapper.findValidByCondition(amount);
        if (CollectionUtils.isEmpty(lbi)) {
            return null;
        }
        List<Long> batchs = Lists.newArrayList();
        for (CouponBatchInfo batch : lbi) {
            batchs.add(batch.getId());
        }
        List<Coupon> list = couponMapper.findUserValid(uid, CouponStatus.INIT,
                batchs);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        // 取一个最大的优惠券? 这里取的是最接近amount的券
        Coupon ret = null;
        for (Coupon coupon : list) {
            if (ret == null) {
                ret = coupon;
            } else if (coupon.getMoney().compareTo(ret.getMoney()) > 0) {
                ret = coupon;
            }
        }
        return ret;
    }

    public long updateCouponStatus(String couponNum, CouponStatus status) {
        return couponMapper.updateStatus(couponNum, status);
    }

    public long insertOne(Coupon coupon) {
        return couponMapper.insertOne(coupon);
    }

	public List<CouponInfo2App> queryCoupons(Long uid, int status) {
		List<Coupon> list = Lists.newArrayList();
		if(status == -1){
			list = couponMapper.findByUid(uid);
		}else{
			CouponStatus couponStatus = CouponStatus.fromValue(status);
			if(couponStatus == null){
				return null;
			}
			list = couponMapper.findCouponsByUidAndStatus(uid, couponStatus);
		}
		return convert2AppList(list);
	}
	
	private List<CouponInfo2App> convert2AppList(List<Coupon> clist) {
		if (CollectionUtils.isEmpty(clist)) {
			return null;
		}
		List<CouponInfo2App> cinfolist= Lists.newArrayList();
		
		for(Coupon coupon:clist){
			CouponBatchInfo couponBatchInfo = batchMapper.findById(coupon.getBatchId());
			cinfolist.add(new CouponInfo2App(coupon, couponBatchInfo));
		}
		return cinfolist;
	}

	public List<Coupon> findValidCoupons(long uid, BigDecimal amount) {
        List<CouponBatchInfo> lbi = batchMapper.findValidByCondition(amount);
        if (CollectionUtils.isEmpty(lbi)) {
            return null;
        }
        List<Long> batchs = Lists.newArrayList();
        for (CouponBatchInfo batch : lbi) {
            batchs.add(batch.getId());
        }
        List<Coupon> list = couponMapper.findUserValid(uid, CouponStatus.INIT,
                batchs);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }

        return list;
		
	}
	
	public List<CouponForApp> coverToApp(List<Coupon> list){
		List<CouponForApp> data = Lists.newArrayList();
		for(Coupon c:list){
			data.add(coverToApp(c));
		}
		return data;
	}
	
	public CouponForApp coverToApp(Coupon c){
		CouponForApp app = new CouponForApp();
		app.money = c.getMoney();
		app.coupon_num = c.getCouponNum();
		return app;
	}
	
	class CouponForApp{
		BigDecimal money;
		String coupon_num;
		public BigDecimal getMoney() {
			return money;
		}
		public void setMoney(BigDecimal money) {
			this.money = money;
		}
		public String getCoupon_num() {
			return coupon_num;
		}
		public void setCoupon_num(String coupon_num) {
			this.coupon_num = coupon_num;
		}
	}
}