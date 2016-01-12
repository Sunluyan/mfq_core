package com.mfq.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

import javax.annotation.Resource;

import com.mfq.bean.*;
import com.mfq.bean.app.CouponInfo2App;
import com.mfq.constants.*;
import com.mfq.dao.OrderFreedomMapper;
import org.apache.commons.collections.list.LazyList;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
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
import com.mfq.dataservice.context.UserIdHolder;
import com.mfq.net.wukongbao.pojo.WkbConstants;
import com.mfq.service.sms.SMSService;
import com.mfq.service.user.UserQuotaService;
import com.mfq.service.user.UserService;
import com.mfq.utils.DateUtil;
import com.mfq.utils.FQUtil;
import com.mfq.utils.JsonUtil;
import com.mfq.utils.ListSortUtil;
import com.mfq.utils.SecurityCodeUtil;

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
    OrderFreedomMapper orderFreedomMapper;
    @Resource
    SMSService smsService;
    @Resource
    CityMapper cityMapper;
    @Resource
    PolicyService policyService;
    @Resource
    FinanceBillService financeBillService;
    @Resource
    OrderFreedomService orderFreedomService;

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


//		if (!flag) {
//			throw new Exception("生单前订单校验失败！");
//		}

        long ls = mapper.findByUidAndPayTypeAndPid(uid, PayType.FINANCING.getId(), pid);
        long lsr = mapper.findByUidAndPayTypeAndPid(uid, PayType.FULL.getId(), pid);
        logger.info("ls = {}, lsr ={}", ls, lsr);
        if (ls + lsr > 2 || ls > 1 || lsr > 1) {
            throw new Exception("存在未支付订单。");
        }


        couponNum = StringUtils.defaultIfBlank(couponNum, "");
        /**
         * 仅分期订单：下单后，优惠券被冻结，订单取消后会被标注为释放；订单推开，同样须将优惠券释放为初始状态。 支付回调时，优惠券被标记为使用
         */

        UserQuota quota = userQuotaService.queryUserQuota(uid);

        if (t == PayType.FINANCING) {

            if (periodPay.divide(BigDecimal.valueOf(period), 2, BigDecimal.ROUND_HALF_EVEN).compareTo(BigDecimal.valueOf(100)) < 0) {
                throw new Exception("每月还款金额不能低于100元哦");
            }
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
            if (policy_status == -1) {
                if (amount.compareTo(periodPay) >= 0) {
                    toStatus = OrderStatus.PAY_OK;
                }
            }

        }
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

        //购买保险 暂时封锁，待悟空保完成后解锁 by 刘志国
        if (policy_status == 1) {
            boolean c = policyService.saveInsure(order, quota, user, p);
            if (c) {
                BigDecimal fremium = WkbConstants.PREMIUM;
                order.setPrice(order.getPrice().add(fremium));
            }
        }

        String SecurityCode = SecurityCodeUtil.getSecurityCode(orderNo);
        order.setSecurityCode(SecurityCode);
        long insertOrder = insertOrder(order);
        logger.info("创建订单的类型是:{},订单的详情为:{}", t.getName(), order.toString());
        if (insertOrder != 1) {
            throw new Exception("插入订单失败！请重试");
        }
        //优惠券标记为已使用
        if (StringUtils.isNotBlank(couponNum)) {
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
     * 生成*随意单*订单号
     *
     * @return
     */
    public String makeOrderFreedomNo() {
        Integer randomInt = new Random().nextInt(99999999);
        String randomStr = new DecimalFormat("00000000").format(randomInt);
        StringBuilder sb = new StringBuilder(Constants.FREEDOM_ORDER_PREFIX);
        sb.append(DateUtil.formatCurTimeLong());
        sb.append(randomStr);
        System.out.println(sb.toString());
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
                    && DateUtil.getDayBetweenD(product.getDateStart(),
                    new Date()) >= 0 && DateUtil.getDayBetweenD(
                    new Date(), product.getDateEnd()) >= 0)) {
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
        } else {
            logger.error("Exception_PayType_Param!");
            throw new Exception("Exception_PayType_Param!");
        }
        map.put("pid", product.getId());
        map.put(type.getId(), fm);
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
    public String queryOrdersByUid(long uid) throws Exception {
        List<OrderInfo> data = mapper.findByUid(uid);
        List<OrderInfo2App> appOrders = makeAppOrdersByOrderList(data);
        List<OrderFreedom> orderFreedoms = orderFreedomService.selectByUid(uid);
        //TODO 制作随意单的orderinfo2app
        List<OrderInfo2App> appOrdersFreedom = makeAppOrderByOrderFreedomList(orderFreedoms);
        for (OrderInfo2App orderInfo2App : appOrdersFreedom) {
            appOrders.add(orderInfo2App);
        }
        // 排序
        if (!CollectionUtils.isEmpty(appOrders)) {
            ListSortUtil<OrderInfo2App> sortList = new ListSortUtil<OrderInfo2App>();
            sortList.sort(appOrders, "update_time", "desc");
        }
        for (OrderInfo2App appOrder : appOrders) {
            System.out.println(appOrder);
        }
        return JsonUtil.successResultJson(appOrders);
    }

    public static void main(String[] args) throws Exception {
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring/spring.xml");
        OrderService service = ac.getBean(OrderService.class);
        service.queryOrdersByUid(2798);
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
        if (!orderNo.contains("fk")) {
            OrderInfo orderInfo = mapper.findByOrderNo(orderNo);
            if (orderInfo == null) {
                return JsonUtil.toJson(ErrorCodes.CORE_ERROR, "订单不存在", null);
            }
            OrderInfo2App bean = makeAppOrderByOrder(orderInfo);
            return JsonUtil.successResultJson(bean);
        } else {
            OrderFreedom orderFreedom = orderFreedomService.selectByOrderNo(orderNo);
            if (orderFreedom == null) {
                return JsonUtil.toJson(ErrorCodes.CORE_ERROR, "订单不存在", null);
            }
            OrderInfo2App bean = makeAppOrderByOrderFreedom(orderFreedom);
            return JsonUtil.successResultJson(bean);
        }
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
            Coupon coupon = couponService.findByCouponNum(bean.getCouponNum());
            List<Coupon> couponList = new ArrayList<>();
            couponList.add(coupon);
            CouponInfo2App CouponInfo2App = couponService.convert2AppList(couponList).get(0);
            bean.setNeed_pay(bean.getNeed_pay().subtract(CouponInfo2App.getMoney()));
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
            OrderInfo2App bean = makeAppOrderByOrder(orderInfo);
            return JsonUtil.successResultJson(bean);
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
        if (!orderNo.contains("fk")) {

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
            orderInfo.setStatus(OrderStatus.CANCEL_OK.getValue());
            long result = mapper.updateOrderInfo(orderInfo);
            if (result > 0) {
                OrderInfo2App bean = makeAppOrderByOrder(orderInfo);
                return JsonUtil.successResultJson(bean);
            } else {
                return JsonUtil.toJson(ErrorCodes.FAIL, "取消订单失败", null);
            }

        } else {

            OrderFreedom orderFreedom = orderFreedomService.selectByOrderNo(orderNo);
            if (orderFreedom == null) {
                return JsonUtil.toJson(ErrorCodes.FAIL, "订单不存在 ", null);
            }
            if (orderFreedom.getStatus() != OrderStatus.BOOK_OK.getValue()) {
                return JsonUtil.toJson(ErrorCodes.FAIL, "当前订单状态不允许取消", null);
            }
            Coupon coupon = couponService.findByCouponNum(orderFreedom.getCouponNum());
            if (coupon != null && coupon.getStatus() == CouponStatus.FREEZE) {
                long l = couponService.updateCouponStatus(orderFreedom.getCouponNum(),
                        CouponStatus.INIT);
                if (l <= 0) {
                    return JsonUtil.toJson(ErrorCodes.FAIL, "更新优惠券状态失败", null);
                }
            }
            orderFreedom.setStatus(OrderStatus.CANCEL_OK.getValue());
            long result = orderFreedomMapper.updateByPrimaryKeySelective(orderFreedom);
            if (result > 0) {
                OrderInfo2App bean = makeAppOrderByOrderFreedom(orderFreedom);
                return JsonUtil.successResultJson(bean);
            } else {
                return JsonUtil.toJson(ErrorCodes.FAIL, "取消订单失败", null);
            }
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
     * 计算分期
     *
     * @param uid
     * @return
     * @throws Exception
     */
    public String calculateFinancingByUidAndAmount(long uid,
                                                   BigDecimal amount) throws Exception {
        User user = userService.queryUser(uid);
        if (user == null || user.getUid() < 0 || UserIdHolder.getLongUid() < 0) {
            logger.warn("用户不存在！uid={}", uid);
            return JsonUtil.toJson(ErrorCodes.CORE_ERROR, "系统异常", null);
        }

        UserQuota quota = userQuotaService.queryUserQuota(uid);
        if (quota == null || quota.getUid() <= 0) {
            logger.warn("无法获取UserQuota！uid={}", uid);
            return JsonUtil.toJson(ErrorCodes.CORE_ERROR, "系统异常", null);
        }


        Map<Integer, Object> map = new HashMap<Integer, Object>();
        Integer[] periods = {6, 12, 24,};
        for (Integer integer : periods) {
            BigDecimal money = amount.multiply(new BigDecimal("0.0125"))
                    .add(amount.divide(new BigDecimal(integer), 2, BigDecimal.ROUND_HALF_EVEN)).setScale(2, BigDecimal.ROUND_HALF_EVEN);
            map.put(integer, money);
        }
        return JsonUtil.successResultJson(map);
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

    /**
     * 创建任意单的OrderInfo2App
     *
     * @param order
     * @return
     * @throws Exception
     */
    public OrderInfo2App makeAppOrderByOrderFreedom(OrderFreedom order) throws Exception {
        if (order == null) {
            return null;
        }
        logger.info("order = {}", order.toString());
        Hospital hospital = hospitalService.findById(order.getHospitalId());
        FinanceBill finance = financeBillService.findFinanceDetailById(order.getOrderNo());
        OrderInfo2App bean = new OrderInfo2App(order, hospital);

        //减去优惠券的价格
        if (StringUtils.isNotBlank(bean.getCouponNum())) {
            Coupon coupon = couponService.findByCouponNum(bean.getCouponNum());
            List<Coupon> couponList = new ArrayList<>();
            couponList.add(coupon);
            CouponInfo2App CouponInfo2App = couponService.convert2AppList(couponList).get(0);
            bean.setNeed_pay(bean.getNeed_pay().subtract(CouponInfo2App.getMoney()));
        }
        return bean;
    }

    public List<OrderInfo2App> makeAppOrderByOrderFreedomList(List<OrderFreedom> orderFreedoms) throws Exception {
        if (orderFreedoms == null || orderFreedoms.size() == 0) {
            return null;
        }
        List<OrderInfo2App> list = new ArrayList<>();
        for (OrderFreedom orderFreedom : orderFreedoms) {
            Hospital hospital = hospitalService.findById(orderFreedom.getHospitalId());
            FinanceBill finance = financeBillService.findFinanceDetailById(orderFreedom.getOrderNo());
            OrderInfo2App bean = new OrderInfo2App(orderFreedom, hospital);

            //减去优惠券的价格
            if (StringUtils.isNotBlank(bean.getCouponNum())) {
                Coupon coupon = couponService.findByCouponNum(bean.getCouponNum());
                List<Coupon> couponList = new ArrayList<>();
                couponList.add(coupon);
                CouponInfo2App CouponInfo2App = couponService.convert2AppList(couponList).get(0);
                bean.setNeed_pay(bean.getNeed_pay().subtract(CouponInfo2App.getMoney()));
            }
            list.add(bean);
        }

        return list;
    }

    /**
     * 创建任意单
     * 1.创建任意单订单
     * 2.加入orderInfo2app
     *
     * @param uid
     * @param amount
     * @param operationTime
     * @param couponNum
     * @param policyNum
     * @return
     */
    @Transactional
    public OrderInfo2App createOrderFreedom(long uid, BigDecimal amount, Date operationTime, String couponNum, int policyNum, int hosId, String proname) throws Exception {

        //public OrderFreedom(Long id, Long uid, String orderNo, Integer hospitalId, String proname,
        // BigDecimal price, Integer status, String couponNum, BigDecimal onlinePay, String securityCode,
        // Integer policyStatus, Date createTime, Date payTime, Date updateTime, Date serviceTime)
        
        String orderNo = makeOrderFreedomNo();
        String securityCode = SecurityCodeUtil.getSecurityCode(orderNo);
        OrderFreedom orderFreedom = new OrderFreedom(null, uid, orderNo, hosId, proname, amount, OrderStatus.BOOK_OK.getValue(), couponNum, BigDecimal.valueOf(0), securityCode,
                policyNum, new Date(), null, new Date(), operationTime);

        long count = orderFreedomMapper.insert(orderFreedom);
        if (count != 1) {
            logger.error("创建随意单失败!请重试");
            throw new Exception("创建随意单失败!请重试");
        }
        //开始创建orderInfo2App
        OrderInfo2App orderInfo2App = makeAppOrderByOrderFreedom(orderFreedom);
        return orderInfo2App;
    }

    /**
     * 进入随意单时需要的数据
     *
     * @param uid
     * @return 医院列表 优惠券列表
     */
    public Map<String,Object> bookingOrderFreedom(Long uid) throws Exception{
        //获取医院列表
        List<Hospital> hosList = hospitalService.queryHospitals();
        List<CouponInfo2App> couponList = couponService.findValidCoupon(uid);
        List<Map<String,Object>> hosMaps = new ArrayList<>();

        for (Hospital hospital : hosList) {
            Map<String,Object> map = new HashMap<>();
            map.put("hosname",hospital.getName());
            map.put("hosid",hospital.getId());
            hosMaps.add(map);
        }
        Map<String,Object> result = new HashMap<>();
        result.put("hospitals",hosMaps);
        result.put("coupons",couponList);

        return result;
    }
}


















