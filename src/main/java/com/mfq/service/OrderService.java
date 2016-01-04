package com.mfq.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

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
import com.mfq.bean.FinanceBill;
import com.mfq.bean.Hospital;
import com.mfq.bean.OrderInfo;
import com.mfq.bean.PolicyInfo;
import com.mfq.bean.Product;
import com.mfq.bean.app.OrderInfo2App;
import com.mfq.bean.area.City;
import com.mfq.bean.coupon.Coupon;
import com.mfq.bean.user.User;
import com.mfq.bean.user.UserQuota;
import com.mfq.constants.Constants;
import com.mfq.constants.CouponStatus;
import com.mfq.constants.ErrorCodes;
import com.mfq.constants.OrderStatus;
import com.mfq.constants.PayStatus;
import com.mfq.constants.PayType;
import com.mfq.constants.PolicyStatus;
import com.mfq.constants.ProductType;
import com.mfq.constants.RefundType;
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
	SMSService smsService;
	@Resource
	CityMapper cityMapper;
	@Resource
	PolicyService policyService;
	@Resource
	FinanceBillService financeBillService;

	/**
	 * 
	 * @param t
	 * @param uid
	 * @param pid
	 * @param amount  订单总额
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
		if(ls+lsr > 2 || ls > 1 || lsr > 1){
			throw new Exception("存在未支付订单。");
		}
		
		
		couponNum = StringUtils.defaultIfBlank(couponNum, "");
		
		/**
		 * 仅分期订单：下单后，优惠券被冻结，订单取消后会被标注为释放；订单推开，同样须将优惠券释放为初始状态。 支付回调时，优惠券被标记为使用
		 */

		UserQuota quota = userQuotaService.queryUserQuota(uid);

		if (t == PayType.FINANCING) {
			
			if(periodPay.divide(BigDecimal.valueOf(period),2, BigDecimal.ROUND_HALF_EVEN).compareTo(BigDecimal.valueOf(100))<0){
				throw new Exception("每月还款金额不能低于100元哦");
			}
			
			if (quota.getBalance().compareTo(balancePay) >= 0) {
				logger.info("分期 ——余额支付！！ balance ={} , balancePay={}",
						quota.getBalance(), balancePay);
				// 优先使用正常余额帐户
				userQuotaService.updateUserBalance(uid, balancePay.negate());
			} else if (quota.getBalance().add(quota.getPresent())
					.compareTo(balancePay) >= 0) {
				logger.info("分期 ——余额+赠送额度支付！！ balance ={} , balancePay={}",
						quota.getBalance(), balancePay);
				BigDecimal temp = balancePay.subtract(quota.getBalance());
				userQuotaService.updateUserBalance(uid, quota.getBalance()
						.negate());
				userQuotaService.updateUserPresent(uid, temp.negate()); // 其次使用赠送余额帐户
			} else { //
				throw new Exception("余额不够支付此订单");
			}
			
		}


//		if(ProductType.SECKILLING == p.getType()){
//			//减少产品数量 -1
//			logger.info("秒杀减轻 产品数量");
//			productService.updateProductRemainNum(uid, pid, -1);
//		}
		
		String orderNo = makeOrderNo(p.getId());

		OrderInfo order = new OrderInfo(orderNo, amount, uid, pid, t.getId(), period, periodPay,
				OrderStatus.PAY_OK.getValue(), onlinePay, hospitalPay, couponNum, balancePay, operation_time);
		
		
		//购买保险
		if(policy_status == 1){
			order.setPolicyStatus(PolicyStatus.AUDITING);
		}else{
			order.setPolicyStatus(PolicyStatus.WITHOUT);
		}

		//购买保险 暂时封锁，待悟空保完成后解锁 by 刘志国
		if(policy_status == 1){
			boolean c = policyService.saveInsure(order,quota, user, p);
			if(c){
				BigDecimal fremium = WkbConstants.PREMIUM;
				order.setPrice(order.getPrice().add(fremium));
			}
		}
		
		String SecurityCode = SecurityCodeUtil.getSecurityCode(orderNo);
		order.setSecurityCode(SecurityCode);
		long insertOrder = insertOrder(order);
		
		if (insertOrder != 1) {
			throw new Exception("插入订单失败！请重试");
		}
		// 生成账单
		if (t == PayType.FINANCING) {
			financeBillService.createFinance(order);
		}
		
		String[] params = {
				"".equals(quota.getRealname()) ? "未设置用户名" : quota.getRealname(),
				user.getMobile(), order.getOrderNo(), p.getType().getName() };
		smsService.sendCreateOrderSMS(params);
		return makeAppOrderByOrder(order);
	}

	private boolean validBeforeCreate(long uid, Product p, BigDecimal amount,
			BigDecimal onlinePay, BigDecimal hospitalPay,
			BigDecimal balancePay, int period, BigDecimal periodPay, PayType t,
			String couponNum, int policy_status) {
		// 生单前对产品状态进行判断
		if (!p.isOnline()) {
			logger.warn("产品已不在线！请重新检索下单！");
			return false;
		}

		if (ProductType.SECKILLING == p.getType()) {
			if (onlinePay.compareTo(p.getPrice()) < 0) {
				logger.warn("在线支付<产品要求的在线支付金额");
				return false;
			}
		} else if (PayType.FINANCING != t) {

			// 在线支付<产品要求的在线支付金额
			if (onlinePay.compareTo(p.getOnlinePay()) < 0) {
				logger.warn("在线支付<产品要求的在线支付金额");
				return false;
			}

			BigDecimal checkM = amount.subtract(onlinePay).subtract(hospitalPay);
			if(policy_status > 0){  //是否投保
				checkM = checkM.add(com.mfq.net.wukongbao.pojo.WkbConstants.PREMIUM);
			}
			if (checkM.compareTo(BigDecimal.valueOf(0)) < 0) {
				logger.warn("订单金额<错误 {}",amount.subtract(onlinePay).subtract(hospitalPay));
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
				logger.warn("订单金额<错误");
				return false;
			}

			// 还需要校验分期金额与分期数是否一致
			if (baseFQMoney.compareTo(new BigDecimal(0)) > 0) { // 如果有分期部分
				// 校验分期期数是否OK
				UserQuota quota = userQuotaService.queryUserQuota(uid);
				logger.info("quota_left baseFQMoney, period, periodPay {}|{}|{}|{}",quota.getQuotaLeft(), baseFQMoney, period, periodPay);
				boolean fqOK = FQUtil.fenqiIsOk(quota, baseFQMoney, period,
						periodPay);
//				if (!fqOK) {	不明白fuckqOK是什么意思，TODO by 刘志国
//					return false;
//				}
			}
		}
		return true;
	}

	public long updateOrder(long id, int oldStatus, int newStatus) {
		System.out.println(id+"   "+oldStatus+"   "+newStatus);
		return mapper.updateOrderStatusSafe(id, oldStatus, newStatus);
	}

	/**
	 * 生成订单号
	 * 
	 * @param pId
	 *            ：产品ID
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
				couponService.findValidCoupon(uid, product.getPrice()));
		return JsonUtil.successResultJson(map);
	}

	private void buildBookingMap(Map<Object, Object> map, PayType type,
			User user, UserQuota quota, Product product) throws Exception {
		
		Map<String, Object> fm = Maps.newHashMap();
		
		fm.put("product_name", product.getName());
		Hospital hospital = hospitalService.findById(product.getHospitalId());
		fm.put("hospital_name", hospital.getName());
		City city = cityMapper.findById(hospital.getCityId());
		fm.put("hospital_addr", city.getName());
		fm.put("amount", product.getPrice());
		
		
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

	public List<OrderInfo> findIdsByTypeAndStatus(ProductType type,
			OrderStatus status) {
		return mapper.findIdsByTypeAndStatus(type, status);
	}

	public OrderInfo findByOrderNo(String orderNo) {
		return mapper.findByOrderNo(orderNo);
	}

	public long insertOrder(OrderInfo order) {
		return mapper.insertOrder(order);
	}

	/**
	 * 我的订单
	 * 
	 * @param uid
	 */

	public String queryOrdersByUidAndStatus(long uid) {
		List<OrderInfo> data = Lists.newArrayList();
		data = mapper.findByUid(uid);
		List<OrderInfo2App> appOrders = makeAppOrdersByOrderList(data);
		// 排序
		if (!CollectionUtils.isEmpty(appOrders)) {
			ListSortUtil<OrderInfo2App> sortList = new ListSortUtil<OrderInfo2App>();
			sortList.sort(appOrders, "update_time", "desc");
		}
		return JsonUtil.successResultJson(appOrders);
	}

	public String queryOrderDetailByOid(Long uid, String orderNo)
			throws Exception {
		OrderInfo orderInfo = mapper.findByOrderNo(orderNo);
		if (orderInfo == null) {
			return JsonUtil.toJson(ErrorCodes.CORE_ERROR, "订单不存在", null);
		}
		OrderInfo2App bean = makeAppOrderByOrder(orderInfo);
		return JsonUtil.successResultJson(bean);
	}

	private List<OrderInfo2App> makeAppOrdersByOrderList(List<OrderInfo> orders) {
		if (CollectionUtils.isEmpty(orders)) {
			return null;
		}
		List<OrderInfo2App> list = Lists.newArrayList();
		for (OrderInfo order : orders) {
			Product product = productService.findById(order.getPid());
			if (product == null || product.getId() <= 0){
				logger.info("OrderService  ____product is null" + order.getPid());
				continue;
			}
			Hospital hospital = hospitalService.findById(product.getHospitalId());

			OrderInfo2App bean = new OrderInfo2App(product, order, new FinanceBill(),hospital.getName(), order.getPolicyStatus());

			list.add(bean);
		}
		return list;
	}

	public OrderInfo2App makeAppOrderByOrder(OrderInfo order) throws Exception {
		if (order == null) {
			return null;
		}
		logger.info("order = {}",order.toString());
		Product product = productService.findById(order.getPid());
		Hospital hospital = hospitalService.findById(product.getHospitalId());
		FinanceBill finance = financeBillService.findFinanceDetailById(order.getOrderNo());
		OrderInfo2App bean = new OrderInfo2App(product, order, finance, hospital.getName(), order.getPolicyStatus());
		System.out.println(bean.toString());
		return bean;
	}
	
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
	 * 根据订单计算可用余额 主要是为了排除赠送金额超支下单的状况
	 * 
	 * @return
	 */
	public BigDecimal getValidBalance(OrderInfo order) {
		if (order == null) {
			return null;
		}
		return getValidBalance(order.getUid());
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
	 * @return
	 */
	public BigDecimal getValidBalance(UserQuota quota) {
		if (quota == null || quota.getUid() <= 0) {
			return BigDecimal.valueOf(0);
		}
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
		Integer[] periods = {6,12,24,};
		for (Integer integer : periods) {
			BigDecimal money = amount.multiply(new BigDecimal("0.0125")) 
        			.add(amount.divide(new BigDecimal(integer),2,BigDecimal.ROUND_HALF_EVEN)).setScale(2, BigDecimal.ROUND_HALF_EVEN);
			map.put(integer, money);
		}
		return JsonUtil.successResultJson(map);
	}

	/**
	 * 计算分期基数
	 * 
	 * @param product
	 *            产品
	 * @param couponNum
	 *            优惠券编码
	 * @return
	 */
	public BigDecimal getFQPrincipalByProduct(Product product,
			String couponNum, BigDecimal balance) {	
		Coupon coupon = null;
		BigDecimal amount = new BigDecimal(0);
		if (StringUtils.isNotBlank(couponNum)) {
			coupon = couponService.findByCouponNum(couponNum);
			if (coupon != null && coupon.getUid() > 0) {
				amount = product.getPrice().subtract(balance)
						.subtract(coupon.getMoney());
			} else {
				amount = product.getPrice().subtract(balance);
			}
		} else {
			amount = product.getPrice().subtract(balance);
		}

		if (amount.compareTo(BigDecimal.valueOf(100)) == -1) {
			return BigDecimal.valueOf(0);
		}
		return amount;
	}

	public List<OrderInfo> getSeckillingProduct(long uid) {

		return mapper.findByUidAndProductType(uid, ProductType.SECKILLING);
	}

	public List<OrderInfo> findByUid(long uid) {
		return mapper.findByUid(uid);
	}
	
	public long updateOnlinePayByPrimaryKey(String orderNo,BigDecimal onlinePay){
		return mapper.updateOnlinepayByOrderNo(orderNo,onlinePay);
	}
	
	public int updatePolicyStatusByStatus(PolicyStatus insureEffect, PolicyStatus auditing, String orderNo) {
		return mapper.updatePolicyStatusByStatus(insureEffect, auditing, orderNo);
		
	}
	
	public static void main(String[] args) {
		BigDecimal periodPay = BigDecimal.valueOf(2300);
		int period = 24;
		boolean isBig= periodPay.divide(BigDecimal.valueOf(period),2, BigDecimal.ROUND_HALF_EVEN).compareTo(BigDecimal.valueOf(100))<0;
		System.out.println(periodPay.divide(BigDecimal.valueOf(period),2, BigDecimal.ROUND_HALF_EVEN));
		
	}
	
	

}