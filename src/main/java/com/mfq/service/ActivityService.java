package com.mfq.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import ch.qos.logback.classic.jul.JULHelper;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.mfq.bean.*;
import com.mfq.bean.app.*;
import com.mfq.bean.coupon.Coupon;
import com.mfq.bean.coupon.CouponBatchInfo;
import com.mfq.bean.user.PresentRecord;
import com.mfq.constants.*;
import com.mfq.dao.*;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.TestOnly;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mfq.bean.user.User;
import com.mfq.bean.user.UserExtend;
import com.mfq.dataservice.context.UserIdHolder;
import com.mfq.payment.BasePaymentService;
import com.mfq.payment.PayAPIType;
import com.mfq.payment.PayFactory;
import com.mfq.service.user.UserExtendService;
import com.mfq.service.user.UserQuotaService;
import com.mfq.service.user.UserService;
import com.mfq.service.wechat.WeChatService;
import com.mfq.utils.JsonUtil;

@Service
public class ActivityService {

    private static final Logger logger = LoggerFactory
            .getLogger(ActivityService.class);

    @Resource
    UserExtendMapper userExtendMapper;
    @Resource
    UserService userService;
    @Resource
    UserExtendService extendService;
    @Resource
    ProductService productService;
    @Resource
    HospitalService hospitalService;
    @Resource
    WeChatService weChatService;
    @Resource
    OrderService orderService;
    @Resource
    UserQuotaService userQuotaService;
    @Resource
    PayService payService;
    @Resource
    PayRecordService payRecordService;
    @Resource
    PresentalMapper presentalMapper;
    @Resource
    CouponMapper couponMapper;
    @Resource
    CouponBatchInfoMapper couponBatchInfoMapper;
    @Resource
    ActivityRecordMapper activityRecordMapper;
    @Resource
    ActivityMapper activityMapper;

    public List<ProductInfoItem> getItemsByType(ProductType type) {
        List<Product> plist = productService.queryProductsByType(type);
        return convert2AppList(plist);
    }

    public List<ProductInfoItem> convert2AppList(List<Product> plist) {
        List<ProductInfoItem> list = Lists.newArrayList();
        for (Product p : plist) {
            Hospital hospital = hospitalService.findById(p.getHospitalId());
            ProductInfoItem item = new ProductInfoItem(p, hospital);
            list.add(item);
        }
        return list;
    }


    public String seckingProduct(String code, long uid, long pid) throws Exception {
        if (uid <= 0) {
            return JsonUtil.toJson(ErrorCodes.CORE_NEED_LOGIN, "用户未登录", null);
        }
        User user = userService.queryUser(uid);
        if (user == null || user.getUid() <= 0) {
            return JsonUtil.toJson(ErrorCodes.USER_NOT_FIND, "用户不存在", null);
        }
        UserExtend extend = extendService.getUserExtendByUid(uid);
        if (extend == null || extend.getUid() <= 0) {
            return JsonUtil.toJson(1001, "用户不是邀请用户", null);
        }
        code = code.toLowerCase();
        if (!code.equals(extend.getInviteCode().toLowerCase())) {
            return JsonUtil.toJson(1002, "邀请码错误", null);
        }
        if (orderService.getValidBalance(uid).compareTo(BigDecimal.valueOf(1)) < 0) {
            return JsonUtil.toJson(1003, "用户余额不足1元", null);
        }

        List<OrderInfo> orders = orderService.getSeckillingProduct(uid);
        logger.info("=======order size==={}={}==", orders.size(), uid);
        if (orders.size() > 0) {
            return JsonUtil.toJson(1005, "秒杀活动只限参与一次", null);
        }
        Product product = productService.findById(pid);
        if (product.getRemainNum() < 1) {
            return JsonUtil.toJson(1006, "产品已被强完！！！", null);
        }
        //秒杀产品
        OrderInfo2App order = orderService.createOrder(PayType.FULL, uid, pid, product.getPrice(), product.getPrice(), BigDecimal.valueOf(0), product.getPrice(), 0, BigDecimal.valueOf(0), "", 0, new Date());
        if (order == null || order.getOrder_no() == null) {
            return JsonUtil.toJson(1004, "订单生成失败！！！", null);
        }
        String ret = goPay(order);

        return ret;
    }

    @Transactional
    private String goPay(OrderInfo2App order) throws Exception {
        logger.info("秒杀进入gopay order is {}|{}|{}", order.getOrder_no(), order.getPrice(), order.getPay_type());
        OrderType orderType = payService.getOrderType(order.getOrder_no());
        BasePaymentService service = PayFactory.getInstance(PayAPIType.INNER);
        payService.getOrderType(order.getOrder_no());
        long s = payRecordService.saveRecord(orderType, UserIdHolder.getLongUid(), order.getOrder_no(),
                order.getPrice());
        if (s > 0) {

        }
        Map<String, Object> params = Maps.newHashMap();
        params.put("order_no", order.getOrder_no());
        params.put("amount", order.getPrice());
        String result = service.goPay(null, null, params, orderType);
        return result;
    }


    /**
     * 礼品券兑换活动,通过code验证,换取500元优惠券
     *
     * @param uid
     * @param code
     * @return
     */
    @Transactional
    public String presentCode(long uid, String code) throws Exception {
        Presental presental = presentalMapper.selectByPrimaryKey(code);
        if (presental == null) {
            return JsonUtil.toJson(9999, "没有该验证码", null);
        } else if (null != presental.getUid()) {
            return JsonUtil.toJson(8989, "验证码已被使用", null);
        }
        logger.info("presental.getUid:{}", presental.getUid());
        //验证码可用
        presental.setUid((int) uid);
        presental.setUpdatetime(new Date());
        int count = presentalMapper.updateByPrimaryKey(presental);
        if (count != 1) {
            logger.error("更新验证码出错!");
            throw new Exception("更新验证码出错");
        }
        Coupon coupon = new Coupon();
        coupon.setUid(uid);
        coupon.setMoney(BigDecimal.valueOf(500));
        coupon.setBatchId(2);
        coupon.setCouponNum(code);
        coupon.setStatus(CouponStatus.INIT);
        count = (int) couponMapper.insertOne(coupon);
        if (count != 1) {
            logger.error("插入优惠券出错!");
            throw new Exception("优惠券无效");
        }
        return JsonUtil.successResultJson("领取成功~");
    }

    //添加活动被打开次数
    public void addOpenCount(String activityName) {
        ActivityRecordExample example = new ActivityRecordExample();
        example.or().andNameEqualTo(activityName);
        List<ActivityRecord> list = activityRecordMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(list)) {
            ActivityRecord activityRecord = new ActivityRecord();
            activityRecord.setName(activityName);
            activityRecord.setOpenCount(1l);
            activityRecordMapper.insertSelective(activityRecord);
        } else {
            ActivityRecord activityRecord = list.get(0);
            activityRecord.setOpenCount(activityRecord.getOpenCount() + 1);
            activityRecordMapper.updateByPrimaryKey(activityRecord);
        }
    }

    //添加活动被分享次数
    public void addShareCount(String activityName) {
        ActivityRecordExample example = new ActivityRecordExample();
        example.or().andNameEqualTo(activityName);
        List<ActivityRecord> list = activityRecordMapper.selectByExample(example);
        ActivityRecord activityRecord = list.get(0);
        activityRecord.setShareCount(activityRecord.getShareCount() + 1);
        activityRecordMapper.updateByPrimaryKey(activityRecord);
    }

    //添加活动预期结果次数
    public void addResultCount(String activityName) {
        ActivityRecordExample example = new ActivityRecordExample();
        example.or().andNameEqualTo(activityName);
        List<ActivityRecord> list = activityRecordMapper.selectByExample(example);
        ActivityRecord activityRecord = list.get(0);
        activityRecord.setResultCount(activityRecord.getResultCount() + 1);
        activityRecordMapper.updateByPrimaryKey(activityRecord);
    }


    public List<ActivityOnline> onlineList() throws Exception{
        ActivityExample example = new ActivityExample();
        Date date = new Date();
        example.or().andEndAtGreaterThan(date).andBeginAtLessThan(date).andTypeEqualTo(1);
        List<Activity> activities = activityMapper.selectByExample(example);
        List<ActivityOnline> list = new ArrayList<>();
        for (Activity activity : activities) {
            ActivityOnline online = new ActivityOnline(activity);
            list.add(online);
        }
        return list;
    }

    public List<ActivityOffline> offlineList() throws Exception{
        ActivityExample example = new ActivityExample();
        Date date = new Date();
        example.or().andEndAtGreaterThan(date).andBeginAtLessThan(date).andTypeEqualTo(2);
        List<Activity> activities = activityMapper.selectByExample(example);
        List<ActivityOffline> list = new ArrayList<>();
        for (Activity activity : activities) {
            ActivityOffline offline = new ActivityOffline(activity);
            list.add(offline);
        }
        return list;
    }


    public ActivityOnlineDetail onlineDetail(Integer id) throws Exception{
        Activity activity = activityMapper.selectByPrimaryKey(id);
        ActivityOnlineDetail detail = new ActivityOnlineDetail(activity);
        List<Product> products = productService.selectByPids(activity.getPids());
        List<ProductListItem2App> list = productService.convert2AppList(products);
        for (ProductListItem2App productListItem2App : list) {
            productListItem2App.setUrl("product:"+productListItem2App.getId()+"-"+productListItem2App.getName());
        }
        detail.setPros(list);
        return detail;
    }




    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring/spring.xml");
    }


}
