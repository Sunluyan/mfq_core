package com.mfq.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

import javax.annotation.Resource;

import com.mfq.bean.*;
import com.mfq.bean.app.CouponInfo2App;
import com.mfq.constants.*;
import com.mfq.dao.ProFqRecordMapper;
import com.mfq.service.activity.DidiService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mfq.bean.app.OrderInfo2App;
import com.mfq.bean.area.City;
import com.mfq.bean.coupon.Coupon;
import com.mfq.bean.user.User;
import com.mfq.bean.user.UserQuota;
import com.mfq.dao.OrderInfoMapper;
import com.mfq.dao.area.CityMapper;
import com.mfq.net.wukongbao.pojo.WkbConstants;
import com.mfq.service.sms.SMSService;
import com.mfq.service.user.UserQuotaService;
import com.mfq.service.user.UserService;
import com.mfq.utils.DateUtil;
import com.mfq.utils.FQUtil;
import com.mfq.utils.JsonUtil;
import com.mfq.utils.ListSortUtil;

@Service
public class OrderService {

    private static final Logger logger = LoggerFactory
            .getLogger(OrderService.class);

    @Resource
    UserService userService;
    @Resource
    UserQuotaService userQuotaService;
    @Resource
    CouponService couponService;
    @Resource
    ProductService productService;
    @Resource
    HospitalService hospitalService;
    @Resource
    OrderInfoMapper mapper;
    @Resource
    SMSService smsService;
    @Resource
    CityMapper cityMapper;
    @Resource
    PolicyService policyService;
    @Resource
    FinanceBillService financeBillService;
    @Resource
    ProFqRecordMapper proFqRecordMapper;
    @Resource
    DidiService didiService;


    static final int max = 100000, min = 10000;

    /**
     * 生成订单(不包括随意单)
     *
     * @param t
     * @param uid
     * @param pid
     * @param amount         订单总额
     * @param onlinePay
     * @param hospitalPay
     * @param balancePay
     * @param period
     * @param periodPay
     * @param couponNum
     * @param policy_status
     * @param operation_time
     * @return
     * @throws Exception
     */
    @Transactional
    public OrderInfo2App createOrder(PayType t, long uid, long pid,
                                     BigDecimal amount, BigDecimal onlinePay, BigDecimal hospitalPay,
                                     BigDecimal balancePay, int period, BigDecimal periodPay,
                                     String couponNum, int policy_status, Date operation_time)
            throws Exception {

        Product p = productService.findById(pid);
        User user = userService.queryUser(uid);
        if (p == null || user == null) {
            throw new Exception("无法找到此用户或产品。");
        }

        boolean flag = validBeforeCreate(uid, p, amount, onlinePay,
                hospitalPay, balancePay, period, periodPay, t, couponNum, policy_status);
        // TODO: 16/2/25  下分期订单的时候,应该把订单状态改成已支付

        long ls = mapper.findByUidAndPayTypeAndPid(uid, PayType.FINANCING.getId(), pid);
        long lsr = mapper.findByUidAndPayTypeAndPid(uid, PayType.FULL.getId(), pid);
        logger.info("ls = {}, lsr ={}", ls, lsr);
        if (ls > 1 || lsr > 1) {
            throw new Exception("存在未支付订单。");
        }


        couponNum = StringUtils.defaultIfBlank(couponNum, "");
        /**
         * 仅分期订单：下单后，优惠券被冻结，订单取消后会被标注为释放；订单推开，同样须将优惠券释放为初始状态。 支付回调时，优惠券被标记为使用
         */

        UserQuota quota = userQuotaService.queryUserQuota(uid);

        if (t == PayType.FINANCING) {

            if (quota.getBalance().compareTo(balancePay) >= 0) {
                logger.info("分期 ——余额支付！！ balance ={} , balancePay={}",
                        quota.getBalance(), balancePay);
                // 使用正常余额帐户
                userQuotaService.updateUserBalance(uid, balancePay.negate());
            } else { //
                throw new Exception("余额不够支付此订单");
            }

        }

        String orderNo = makeOrderNo(p.getId());
        OrderStatus toStatus = OrderStatus.BOOK_OK;

        if (t == PayType.FINANCING) {
            if (policy_status == 0) {
                if (amount.compareTo(periodPay) <= 0) {
                    toStatus = OrderStatus.PAY_OK;
                }
            }
        }
        //如果优惠券是didi
        if (couponNum.equals("didi")) {
            didiService.updateByMobile(user.getMobile(),(int)pid);
            onlinePay = amount;
            toStatus = OrderStatus.PAY_OK;
        }
        //didi完毕

        logger.info("t in orderService createOrder:{} , toStatus:{}", t, toStatus);
        //之前在创建分期订单的时候,把amount减少了优惠券的优惠金额(比如3000的amount,优惠券是满1000-500,刚才把amount减到了2500)
        //现在需要把amount改回来
        if (t == PayType.FINANCING && StringUtils.isNotBlank(couponNum)) {
            Coupon coupon = couponService.findByCouponNum(couponNum);
            List<Coupon> couponList = new ArrayList<>();
            couponList.add(coupon);
            CouponInfo2App couponInfo2App = couponService.convert2AppList(couponList).get(0);//这里有可能出错,出错了的话很可能就是没有该优惠券
            amount = amount.add(couponInfo2App.getMoney());
        }
        OrderInfo order = new OrderInfo(orderNo, amount, uid, pid, t.getId(), period, periodPay,
                toStatus.getValue(), onlinePay, hospitalPay, couponNum, balancePay, operation_time);


        //购买保险
        if (policy_status == 1) {
            order.setPolicyStatus(PolicyStatus.AUDITING);
        } else {
            order.setPolicyStatus(PolicyStatus.WITHOUT);
        }

        //购买保险 暂时封锁，待悟空保完成后解锁
        if (policy_status == 1) {
            boolean c = policyService.saveInsure(order, quota, user, p);
            if (c) {
                BigDecimal fremium = WkbConstants.PREMIUM;
                order.setPrice(order.getPrice().add(fremium));
            }
        }

        order.setSecurityCode("");
        long insertOrder = insertOrder(order);

        if (insertOrder < 10) {
            logger.info("创建订单号为{}", insertOrder);
            order = mapper.findByOrderNo(orderNo);
        }

        String SecurityCode = createSecurityCode(order.getId());

        long re = mapper.updateSecurityCode(orderNo, SecurityCode);

        logger.info("创建订单的类型是:{},订单的详情为:{}", t.getName(), order.toString());
        if (insertOrder != 1) {
            throw new Exception("插入订单失败！请重试");
        }
        //优惠券标记为已使用
        if (StringUtils.isNotBlank(couponNum) && !couponNum.equals("didi")) {
            long l = couponService.updateCouponStatus(couponNum,
                    CouponStatus.USED);
            if (l != 1) {
                logger.error("更新优惠券失败!");
                throw new Exception("更新优惠券失败!");
            }
        }


        // 生成账单
        if (t == PayType.FINANCING) {
            financeBillService.createFinance(order);
        }

        String[] params = {
                "".equals(quota.getRealname()) ? "未设置用户名" : quota.getRealname(),
                user.getMobile(), order.getOrderNo(), p.getType().getName()};
        smsService.sendCreateOrderSMS(params);
        return makeAppOrderByOrder(order);
    }


    private static String createSecurityCode(long orderId) {
        if (orderId < 10) {
            logger.info("创建订单号为{}", orderId);
            orderId = new Random().nextInt(9999);
        }
        StringBuilder code = new StringBuilder("m");
        code.append(DateUtil.formatShort());
        Integer randomInt = new Random().nextInt(999);
        String randomStr = new DecimalFormat("000").format(randomInt);
        code.append(orderId);
        code.append(randomStr);

        return code.toString();
    }


    /**
     * 下单前的校验,没有完善,应设计为抛出错误,统一在createOrder的函数中处理,因为可以回滚. TODO
     *
     * @param uid
     * @param p
     * @param amount
     * @param onlinePay
     * @param hospitalPay
     * @param balancePay
     * @param period
     * @param periodPay
     * @param t
     * @param couponNum
     * @param policy_status
     * @return
     */
    private boolean validBeforeCreate(long uid, Product p, BigDecimal amount,
                                      BigDecimal onlinePay, BigDecimal hospitalPay,
                                      BigDecimal balancePay, int period, BigDecimal periodPay, PayType t,
                                      String couponNum, int policy_status) {
        // 生单前对产品状态进行判断
        if (!p.isOnline()) {
            logger.warn("产品已不在线！请重新检索下单！");
            return false;
        }


        if (ProductType.SECKILLING == p.getType() && onlinePay.compareTo(p.getPrice()) < 0) {
            logger.warn("在线支付<产品要求的在线支付金额");
            return false;
        }


        if (PayType.FINANCING != t) { // 分期订单的话

            BigDecimal checkM = amount.subtract(onlinePay).subtract(hospitalPay);
            if (policy_status > 0) {  //是否投保
                checkM = checkM.add(com.mfq.net.wukongbao.pojo.WkbConstants.PREMIUM);
            }
            if (checkM.compareTo(BigDecimal.valueOf(0)) < 0) {
                logger.warn("订单金额<错误 {}", amount.subtract(onlinePay).subtract(hospitalPay));
                return false;
            }

            //暂时加一个保险的金额
//			if (amount.subtract(onlinePay).subtract(hospitalPay).add(BigDecimal.valueOf(19))
//					.compareTo(BigDecimal.valueOf(0)) < 0) {
//				logger.warn("订单金额<错误 {}",
//						amount.subtract(onlinePay).subtract(hospitalPay));
//				return false;
//			}

        } else {
            Coupon coupon = couponService.findByCouponNum(couponNum);
            BigDecimal couponMoney = new BigDecimal(0);
            if (coupon != null) {
                couponMoney = coupon.getMoney();
            }
            // 判断订单分期总额
            BigDecimal baseFQMoney = amount.subtract(onlinePay)
                    .subtract(hospitalPay).subtract(balancePay)
                    .subtract(couponMoney);

            if (amount.subtract(baseFQMoney).compareTo(BigDecimal.valueOf(0)) < 0) {
                logger.warn("订单金额 < 0 ");
                return false;
            }

            // 还需要校验分期金额与分期数是否一致
            if (baseFQMoney.compareTo(new BigDecimal(0)) > 0) { // 如果有分期部分
                // 校验分期期数是否OK
                UserQuota quota = userQuotaService.queryUserQuota(uid);
                logger.info("quota_left baseFQMoney, period, periodPay {}|{}|{}|{}", quota.getQuotaLeft(), baseFQMoney, period, periodPay);
                boolean fqOK = FQUtil.fenqiIsOk(quota, baseFQMoney, period,
                        periodPay);

            }
        }
        return true;
    }

    /**
     * 更新订单状态
     *
     * @param id
     * @param oldStatus
     * @param newStatus
     * @return
     */
    public long updateOrder(long id, int oldStatus, int newStatus) {
        System.out.println(id + "   " + oldStatus + "   " + newStatus);
        return mapper.updateOrderStatusSafe(id, oldStatus, newStatus);
    }

    /**
     * 生成订单号
     *
     * @param pId ：产品ID
     * @return
     */
    public String makeOrderNo(long pId) {
        String pHex = Long.toHexString(pId);
        Integer randomInt = new Random().nextInt(9999);
        String randomStr = new DecimalFormat("0000").format(randomInt);
        StringBuilder sb = new StringBuilder(Constants.ONLINE_ORDER_PREFIX);
        sb.append(DateUtil.formatCurTimeLong());
        sb.append(randomStr);
        sb.append(StringUtils.leftPad(pHex, 4, "0"));
        logger.info("After Make Order No is : {}", sb.toString());
        return sb.toString();
    }


    /**
     * 预订时返回的数据
     *
     * @param uid
     * @param pid
     * @return
     * @throws Exception
     */
    public String bookingOrder(long uid, long pid) throws Exception {
        User user = userService.queryUser(uid);
        if (user == null || user.getUid() < 0) {
            logger.warn("用户不存在！uid={}", uid);
            return JsonUtil.toJson(ErrorCodes.CORE_ERROR, "系统异常", null);
        }

        Product product = productService.findById(pid);
        if (product.getType() == ProductType.SECKILLING) { // 校验秒杀
            if (product.getRemainNum() < 1) {
                logger.warn("该产品剩余量为 0 ！pid={}", pid);
                return JsonUtil.toJson(ErrorCodes.SECKILL_NOT_PRODUCT,
                        "该产品剩余量为 0", null);
            }
            if (!(product != null
                    && DateUtil.getDayBetweenD(product.getDateStart(),
                    new Date()) >= 0 && DateUtil.getDayBetweenD(
                    new Date(), product.getDateEnd()) >= 0)) {
                logger.warn("该产品不可下单！pid={}", pid);
                return JsonUtil.toJson(ErrorCodes.SECKILL_ACTIVITY_END, "活动结束",
                        null);
            }
            long result = productService.updateProductRemainNum(uid, pid, -1);
            if (result < 1) {
                logger.warn("更新产品失败！！ ！pid={}", pid);
                return JsonUtil.toJson(ErrorCodes.SECKILL_ERROR_PRODUCT,
                        "产品下单失败", null);
            }
        } else {
            if (!(product != null
                    && DateUtil.getDayBetweenD(product.getDateStart(), new Date()) >= 0
                    && DateUtil.getDayBetweenD(new Date(), product.getDateEnd()) >= 0)) {
                logger.warn("该产品不可下单！pid={}", pid);
                return JsonUtil.toJson(ErrorCodes.CORE_ERROR, "该产品不可下单 ", null);
            }

        }

        UserQuota quota = userQuotaService.queryUserQuota(uid);
        Map<Object, Object> map = Maps.newHashMap();

        if (product.getType() == ProductType.SECKILLING) {
            buildBookingMap(map, PayType.FULL, user, quota, product);
        } else {
            for (PayType t : PayType.values()) {
                buildBookingMap(map, t, user, quota, product);
            }
        }
        return JsonUtil.successResultJson(map);
    }

    /**
     * 下单前对余额的校验
     *
     * @param uid
     * @param pid
     * @return
     * @throws Exception
     */
    public String orderValidBalance(long uid, long pid) throws Exception {
        Product product = productService.findValidProduct(pid);
        if (product == null) {
            logger.warn("该产品不可下单！pid={}", pid);
            return JsonUtil.toJson(ErrorCodes.CORE_ERROR, "系统异常", null);
        }
        Map<String, Object> map = Maps.newHashMap();
        // 可用余额－－会包含部分赠送金额，可用余额是小于总金额的（总金额＝帐户余额＋赠送代金券）
        map.put("balance", getValidBalance(uid));

        map.put("coupon",
                couponService.findValidCoupon(uid));
        return JsonUtil.successResultJson(map);
    }

    /**
     * 预订时的返回数据
     *
     * @param map
     * @param type
     * @param user
     * @param quota
     * @param product
     * @throws Exception
     */
    private void buildBookingMap(Map<Object, Object> map, PayType type,
                                 User user, UserQuota quota, Product product) throws Exception {

        Map<String, Object> fm = Maps.newHashMap();

        fm.put("product_name", product.getName());
        Hospital hospital = hospitalService.findById(product.getHospitalId());
        fm.put("hospital_name", hospital.getName());
        City city = cityMapper.findById(hospital.getCityId());
        fm.put("hospital_addr", city.getName());
        fm.put("amount", product.getPrice());
        List<CouponInfo2App> coupons = couponService.findValidCoupon(quota.getUid());

        ///滴滴活动 如果有的话就假造一个优惠券
        if (didiService.selectByMobile(user.getMobile(), (int) product.getId())) {
            CouponInfo2App couponInfo2App = new CouponInfo2App();
            couponInfo2App.setBatchId(666);
            couponInfo2App.setBatch("didi");
            couponInfo2App.setCondition(product.getPrice());
            couponInfo2App.setCouponNum("didi");
            couponInfo2App.setId(666);
            couponInfo2App.setMoney(product.getPrice());
            couponInfo2App.setUpdatedAt(new Date());
            couponInfo2App.setPeriodBeg(DateUtil.convertShort("20150101"));
            couponInfo2App.setPeriodEnd(DateUtil.convertShort("20170101"));
            couponInfo2App.setUid(user.getUid());
            if(org.apache.commons.collections.CollectionUtils.isEmpty(coupons)){
                coupons = new ArrayList<>();
            }
            coupons.add(couponInfo2App);
        }
        ///完成
        if (coupons != null && coupons.size() != 0) {
            fm.put("coupons", coupons);
        }


        if (product.getType() == ProductType.SECKILLING) { // 秒杀
            fm.put("online_pay", product.getPrice());
            fm.put("hospital_pay", BigDecimal.valueOf(0));
        } else if (PayType.FULL == type || PayType.PART == type) { // 团购
            fm.put("online_pay", product.getOnlinePay());
            fm.put("hospital_pay", product.getHospitalPay());
        } else if (PayType.FINANCING == type) { // 分期
            fm.put("quota_left", quota.getQuotaLeft());
            BigDecimal fqPrice = productService.selectFqPriceByPid(product.getId());
            if (fqPrice != null) fm.put("amount", fqPrice);

            BigDecimal fqAmount = quota.getQuotaLeft().compareTo((BigDecimal) fm.get("amount")) < 0 ? quota.getQuotaLeft() : (BigDecimal) fm.get("amount");

            fm.put("fqs", caculationFqs(fqAmount));


        } else {
            logger.error("Exception_PayType_Param!");
            throw new Exception("Exception_PayType_Param!");
        }
        map.put("pid", product.getId());
        map.put(type.getId(), fm);
    }


    public Map<Long, BigDecimal> caculationFqs(BigDecimal price) {
        Map<Long, BigDecimal> fq = new HashMap<>();

        BigDecimal three = price.divide(new BigDecimal(3), 2, BigDecimal.ROUND_HALF_EVEN);
        BigDecimal six = price.divide(new BigDecimal(6), 2, BigDecimal.ROUND_HALF_EVEN);
        BigDecimal twenty = price.divide(new BigDecimal(12), 2, BigDecimal.ROUND_HALF_EVEN);

        fq.put(3l, three);
        fq.put(6l, six);
        fq.put(12l, twenty);
        return fq;
    }

    /**
     * 通过订单状态和产品类型查找相应的订单
     *
     * @param type
     * @param status
     * @return
     */
    public List<OrderInfo> findByTypeAndStatus(ProductType type,
                                               OrderStatus status) {
        return mapper.findByTypeAndStatus(type, status);
    }

    /**
     * 通过订单号查询订单
     *
     * @param orderNo
     * @return
     */
    public OrderInfo findByOrderNo(String orderNo) {
        return mapper.findByOrderNo(orderNo);
    }

    /**
     * 插入订单
     *
     * @param order
     * @return
     */
    public long insertOrder(OrderInfo order) {
        return mapper.insertOrder(order);
    }


    /**
     * 我的订单
     * 查询用户全款订单和随意单
     *
     * @param uid
     */
    public String queryOrdersByUid(long uid, Integer status) throws Exception {
        List<OrderInfo> data = mapper.findByUidAndStatus(uid, status);
        List<OrderInfo2App> appOrders = makeAppOrdersByOrderList(data);
        // 排序
        if (!CollectionUtils.isEmpty(appOrders)) {
            ListSortUtil<OrderInfo2App> sortList = new ListSortUtil<OrderInfo2App>();
            sortList.sort(appOrders, "update_time", "desc");
        }
        return JsonUtil.successResultJson(appOrders);
    }


    /**
     * 通过orderNo 和uid 查询订单
     *
     * @param uid
     * @param orderNo
     * @return
     * @throws Exception
     */
    public String queryOrderDetailByOid(Long uid, String orderNo)
            throws Exception {
        OrderInfo orderInfo = mapper.findByOrderNo(orderNo);
        if (orderInfo == null) {
            return JsonUtil.toJson(ErrorCodes.CORE_ERROR, "订单不存在", null);
        }
        OrderInfo2App bean = makeAppOrderByOrder(orderInfo);
        return JsonUtil.successResultJson(bean);

    }


    /**
     * 批量制作orderInfo2App
     *
     * @param orders(List<OrderInfo>)
     * @return
     */
    private List<OrderInfo2App> makeAppOrdersByOrderList(List<OrderInfo> orders) {
        if (CollectionUtils.isEmpty(orders)) {
            return null;
        }
        List<OrderInfo2App> list = Lists.newArrayList();
        for (OrderInfo order : orders) {
            Product product = productService.findById(order.getPid());
            if (product == null || product.getId() <= 0) {
                logger.info("OrderService  ____product is null" + order.getPid());
                continue;
            }
            Hospital hospital = hospitalService.findById(product.getHospitalId());

            OrderInfo2App bean = new OrderInfo2App(product, order, new FinanceBill(), hospital.getName(), order.getPolicyStatus());
            //减去优惠券的价格
            if (StringUtils.isNotBlank(bean.getCouponNum().trim())) {
                Coupon coupon = couponService.findByCouponNum(bean.getCouponNum());
                if (coupon != null && StringUtils.isNotBlank(bean.getCouponNum())) {
                    List<Coupon> couponList = new ArrayList<>();
                    couponList.add(coupon);
                    CouponInfo2App CouponInfo2App = couponService.convert2AppList(couponList).get(0);
                    bean.setNeed_pay(bean.getNeed_pay().subtract(CouponInfo2App.getMoney()));
                }
            }
            list.add(bean);
        }
        return list;
    }

    /**
     * 制作orderInfo2App
     *
     * @param order
     * @return
     * @throws Exception
     */
    public OrderInfo2App makeAppOrderByOrder(OrderInfo order) throws Exception {
        if (order == null) {
            return null;
        }
        logger.info("order = {}", order.toString());
        Product product = productService.findById(order.getPid());
        Hospital hospital = hospitalService.findById(product.getHospitalId());
        FinanceBill finance = financeBillService.findFinanceDetailById(order.getOrderNo());
        OrderInfo2App bean = new OrderInfo2App(product, order, finance, hospital.getName(), order.getPolicyStatus());
        //减去优惠券的价格
        if (StringUtils.isNotBlank(bean.getCouponNum())) {
            if(bean.getCouponNum().equals("didi")){
                if(order.getPolicyStatus() == PolicyStatus.WITHOUT){
                    bean.setNeed_pay(BigDecimal.ZERO);
                }else{
                    bean.setNeed_pay(BigDecimal.valueOf(19));
                }
            }else{
                Coupon coupon = couponService.findByCouponNum(bean.getCouponNum());
                List<Coupon> couponList = new ArrayList<>();
                couponList.add(coupon);
                CouponInfo2App CouponInfo2App = couponService.convert2AppList(couponList).get(0);
                bean.setNeed_pay(bean.getNeed_pay().subtract(CouponInfo2App.getMoney()));

            }
        }

        System.out.println(bean.toString());
        return bean;
    }

    /**
     * 退款申请
     *
     * @param uid
     * @param orderNo
     * @param refund_type
     * @return
     * @throws Exception
     */
    public String refundOrder(Long uid, String orderNo, Integer refund_type)
            throws Exception {
        RefundType refundType = RefundType.fromId(refund_type);
        if (refundType == null) {
            return JsonUtil.toJson(ErrorCodes.CORE_ERROR, "参数错误 ", null);
        }
        OrderInfo orderInfo = mapper.findByOrderNo(orderNo);
        if (orderInfo == null) {
            return JsonUtil.toJson(ErrorCodes.CORE_ERROR, "订单不存在 ", null);
        }
        if (orderInfo.getStatus() != OrderStatus.PAY_OK.getValue()) {
            return JsonUtil.toJson(ErrorCodes.FAIL, "当前订单不允许退款 ", null);
        }
        orderInfo.setStatus(OrderStatus.APPLY_REFUND.getValue());
        orderInfo.setRefundType(refundType.getId());
        long result = mapper.updateOrderInfo(orderInfo);

        if (result > 0) {
//            OrderInfo2App bean = makeAppOrderByOrder(orderInfo);
//            return JsonUtil.successResultJson(bean);

            Refund refund = new Refund();
            refund.setId(1);
            refund.setOrderNo(orderInfo.getOrderNo());
            refund.setRefundPay(orderInfo.getPrice());
            refund.setCheckFlag(1);
            refund.setCheckTime(new Date());
            refund.getCheckUser();
            refund.setStatus(1);
            refund.setRefundTime(new Date());
            refund.setContent("想退款...");
            refund.setCreated(new Date());
            refund.setUpdated(new Date());
            return JsonUtil.successResultJson(refund);
        } else {
            return JsonUtil.toJson(ErrorCodes.FAIL, "退款申请失败", null);
        }
    }

    /**
     * 取消订单
     *
     * @param uid
     * @param orderNo
     * @return
     * @throws Exception
     */
    public String cancelOrder(Long uid, String orderNo) throws Exception {

        OrderInfo orderInfo = mapper.findByOrderNo(orderNo);
        if (orderInfo == null) {
            return JsonUtil.toJson(ErrorCodes.FAIL, "订单不存在 ", null);
        }
        if (orderInfo.getStatus() != OrderStatus.BOOK_OK.getValue()) {
            return JsonUtil.toJson(ErrorCodes.FAIL, "当前订单状态不允许取消", null);
        }
        Coupon coupon = couponService.findByCouponNum(orderInfo.getCouponNum());
        if (coupon != null && coupon.getStatus() == CouponStatus.FREEZE) {
            long l = couponService.updateCouponStatus(orderInfo.getCouponNum(),
                    CouponStatus.INIT);
            if (l <= 0) {
                return JsonUtil.toJson(ErrorCodes.FAIL, "更新优惠券状态失败", null);
            }
        }
        BigDecimal quota = orderInfo.getPeriodPay();
        long quotaResult = userQuotaService.updateUserQuota(uid, quota.negate());
        if (quotaResult != 1) {
            return JsonUtil.toJson(ErrorCodes.FAIL, "返还额度失败", null);
        }

        orderInfo.setStatus(OrderStatus.CANCEL_OK.getValue());
        long result = mapper.updateOrderInfo(orderInfo);
        if (result > 0) {
            OrderInfo2App bean = makeAppOrderByOrder(orderInfo);
            return JsonUtil.successResultJson(bean);
        } else {
            return JsonUtil.toJson(ErrorCodes.FAIL, "取消订单失败", null);
        }


    }


    /**
     * 获取订单可用金额 包括赠送金额
     *
     * @param uid
     * @return
     */
    public BigDecimal getValidBalance(long uid) {
        if (uid <= 0) {
            return null;
        }
        UserQuota quota = userQuotaService.queryUserQuota(uid); // 余额
        return quota.getBalance().add(quota.getPresent());
    }

    /**
     * 返回pid的分期价
     *
     * @param pid
     * @return
     * @throws Exception
     */
    public List<ProFqRecord> calculateFinancing(Integer pid) throws Exception {
        ProFqRecordExample example = new ProFqRecordExample();
        example.or().andPidEqualTo(pid);
        List<ProFqRecord> list = proFqRecordMapper.selectByExample(example);
        return list;
    }

    public List<OrderInfo> getSeckillingProduct(long uid) {

        return mapper.findByUidAndProductType(uid, ProductType.SECKILLING);
    }

    public List<OrderInfo> findByUid(long uid) {
        return mapper.findByUid(uid);
    }

    public long updateOnlinePayByPrimaryKey(String orderNo, BigDecimal onlinePay) {
        return mapper.updateOnlinepayByOrderNo(orderNo, onlinePay);
    }

    public int updatePolicyStatusByStatus(PolicyStatus insureEffect, PolicyStatus auditing, String orderNo) {
        return mapper.updatePolicyStatusByStatus(insureEffect, auditing, orderNo);

    }


    public static void main(String[] args) throws Exception {
//        ApplicationContext ac = new ClassPathXmlApplicationContext("spring/spring.xml");
//        OrderService service = ac.getBean(OrderService.class);
//        service.calculateFinancing(223);

        System.out.print(createSecurityCode(6666l));

    }


}


















