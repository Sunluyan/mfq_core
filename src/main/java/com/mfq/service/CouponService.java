package com.mfq.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import com.mfq.constants.ErrorCodes;
import com.mfq.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
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
 * @author xingyongshan
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


    /**
     * 该方法用来查询用户的优惠券,并封装成 CouponInfo2App 对象返回
     * @param uid
     * @return List<CouponINfo>
     */
    public List<CouponInfo2App> findValidCoupon(long uid) {
		List<Coupon> coupons = couponMapper.findCouponsByUidAndStatus(uid,CouponStatus.INIT);
        if (CollectionUtils.isEmpty(coupons)) {
            return null;
        }
        //封装查询条件,弄成 ('1','2','3') 的样子
		String batchs = "(";
		for (Coupon coupon : coupons) {
			batchs += "'"+coupon.getBatchId()+"',";
		}
		batchs = batchs.substring(0,batchs.length()-1);
		batchs += ")";

        //查询优惠券详情
		List<CouponBatchInfo> couponBatchInfos = batchMapper.findByBatchs(batchs);
        //创建一个要发送给客户端的 CouponInfo2App 集合,等会用
        List<CouponInfo2App> couponInfo2Apps = new ArrayList<>();

        for (Coupon coupon : coupons) {

            CouponBatchInfo  couponBatchInfo = new CouponBatchInfo();
            for (CouponBatchInfo batchInfo : couponBatchInfos) {
                if(batchInfo.getId() == coupon.getBatchId()){
                    couponBatchInfo = batchInfo;
                    break;
                }
            }

            CouponInfo2App couponInfo2App = new CouponInfo2App(coupon,couponBatchInfo);
            couponInfo2Apps.add(couponInfo2App);
        }

        return couponInfo2Apps;
    }

	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring/spring.xml");
		CouponService service = ac.getBean(CouponService.class);
		List<CouponInfo2App> list = service.findValidCoupon(2798);
        for (CouponInfo2App couponInfo2App : list) {
			System.out.println(couponInfo2App);
		}
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
	
	public List<CouponInfo2App> convert2AppList(List<Coupon> clist) {
		if (CollectionUtils.isEmpty(clist)) {
			return null;
		}
		List<CouponInfo2App> cinfolist= Lists.newArrayList();
		
		for(Coupon coupon:clist){
            logger.info("coupon:{}",coupon.toString());
			CouponBatchInfo couponBatchInfo = batchMapper.findById(coupon.getBatchId());
			cinfolist.add(new CouponInfo2App(coupon, couponBatchInfo));
		}
		return cinfolist;
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

    /***
     * 删除coupon
     * @param couponNum
     * @param uid
     * @return
     */
    public long delCoupon(String couponNum, Long uid) {
        long updateNum = 0;
        Coupon coupon = couponMapper.findByUserAndNum(uid, couponNum);
        if(coupon !=null) {
            updateNum = couponMapper.delCoupon(couponNum);
        }
        return updateNum;
    }

    public boolean checkCoupon(String coupon_num,BigDecimal amount) throws Exception{
        String ret = "";
        Coupon coupon = findByCouponNum(coupon_num);
        List<Coupon> couponList = new ArrayList<>();
        couponList.add(coupon);
        CouponInfo2App CouponInfo2App = convert2AppList(couponList).get(0);//这里有可能出错,出错了的话很可能就是没有该优惠券
        if(CouponInfo2App.getMoney().compareTo(amount) >= 0){//如果优惠价格比总价还低
            ret = JsonUtil.toJson(ErrorCodes.CORE_ERROR, "减免金额低于总价", null);
            throw new Exception("优惠券 减免金额低于总价");
        }
        if(CouponInfo2App.getCondition().compareTo(amount) >= 0) {//如果优惠价格比总价还低
            ret = JsonUtil.toJson(ErrorCodes.CORE_ERROR, "不满足优惠条件", null);
            throw new Exception("优惠券 不满足优惠条件");
        }
        return true;
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