package com.mfq.service;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.Date;

import javax.annotation.Resource;

import com.mfq.bean.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mfq.bean.app.OrderInfo2App;
import com.mfq.bean.coupon.Coupon;
import com.mfq.bean.user.User;
import com.mfq.bean.user.UserQuota;
import com.mfq.cache.MsgTmplContext;
import com.mfq.constants.AuthStatus;
import com.mfq.constants.BillStatus;
import com.mfq.constants.Constants;
import com.mfq.constants.CouponStatus;
import com.mfq.constants.ErrorCodes;
import com.mfq.constants.OrderStatus;
import com.mfq.constants.OrderType;
import com.mfq.constants.PayStatus;
import com.mfq.constants.PayType;
import com.mfq.constants.PolicyStatus;
import com.mfq.dao.PolicyInfoMapper;
import com.mfq.payment.PayAPIType;
import com.mfq.service.sms.SMSService;
import com.mfq.service.user.PresentRecordService;
import com.mfq.service.user.UserQuotaService;
import com.mfq.service.user.UserService;
import com.mfq.utils.DateUtil;
import com.mfq.utils.FQUtil;
import com.mfq.utils.JsonUtil;

import net.sf.json.JSONObject;

@Service
public class PayService {

	private static final Logger logger = LoggerFactory.getLogger(PayService.class);

	@Resource
	OrderService orderService;
	@Resource
	UserQuotaService quotaService;
	@Resource
	UserService userService;
	@Resource
	SMSService smsService;
	@Resource
	PayRecordService payRecordService;
	@Resource
	PresentRecordService presentRecordService;
	@Resource
	CouponService couponService;
	@Resource
	UserQuotaService userQuotaService;
	@Resource
	FinanceBillService financeBillService;
	@Resource
	PolicyService policyService;
	@Resource
	PolicyInfoMapper policyInfoMapper;


	/**
	 *
	 * @param orderType
	 * @param amount
	 * @param orderNo
     * @param tpp
     * @return
     */
	public String beforeGoPayCheck(OrderType orderType, BigDecimal amount,
			String orderNo, PayAPIType tpp) {
		String ret = "";

		if (orderType == OrderType.RECHARGE) { // 充值goPay
			if (amount.compareTo(new BigDecimal(0)) <= 0) {
				ret = JsonUtil.toJson(ErrorCodes.ORDER_CANT_GO_PAY, "充值金额不能<=0", null);
				logger.warn("充值金额不能<=0！");
			}
		} else if(orderType == OrderType.ONLINE) { // 普通下单goPay

			if(tpp.equals(PayAPIType.INNER) && orderType.getId()==1){
				//判断如果是余额付款，余额是否不足以支付金额
				UserQuota userQuota = userQuotaService.queryUserQuota(orderService.findByOrderNo(orderNo).getUid());
				if(userQuota.getBalance().compareTo(amount)<0){
					ret = JsonUtil.toJson(ErrorCodes.CORE_ERROR, "余额不足！", null);
					logger.warn("订单不满足支付条件！");
				}
			}
		} else if(orderType == OrderType.REFUND){ //还款

		}
		return ret;
	}

	/**
	 * 
	 * @param onlinePay
	 *            在线支付金额
	 * @param balancePay
	 *            余额支付金额
	 * @param orderNo
	 *            订单号
	 * @return
	 * @throws Exception
	 */
	private boolean canGoPay(BigDecimal onlinePay, BigDecimal balancePay, String couponNum, String orderNo) {
		logger.info("实际线上支付额度   {}， 余额支付额度 {}， 优惠券 {}， 订单号 {}", onlinePay, balancePay, couponNum, orderNo);
		try {
			OrderInfo order = orderService.findByOrderNo(orderNo);
			if (order == null) {
				logger.error("订单不存在！");
				return false;
			}
			BigDecimal couponMoney = new BigDecimal(0);
			if ((order.getPayType() == PayType.FINANCING.getId() && StringUtils.isNotBlank(order.getCouponNum()))) {
				couponNum = order.getCouponNum();
			}
			if (StringUtils.isNotBlank(couponNum)) {
				Coupon coupon = couponService.findByCouponNum(couponNum);
				// 只有分期的是被冻结状态
				if (order.getPayType() == PayType.FINANCING.getId() && coupon.getStatus() == CouponStatus.FREEZE) {
					couponMoney = coupon.getMoney();
				} else if (coupon.getStatus() == CouponStatus.INIT) {
					couponMoney = coupon.getMoney();
				}
			}

			// 非分期的情况:大条件是 付款价格＋帐户余额付款部分要等于订单在线支付金额（订单总金额－到院支付）
			if (order.getPayType() == PayType.FULL.getId() || order.getPayType() == PayType.PART.getId()) {
				
				// 余额支付的部分是否与之前判断
				// 假如用户可用的总剩余余额小于支付过程中传入的余额支付金额，则终止支付  包括赠送额度
				
				if (orderService.getValidBalance(order.getUid()).add(couponMoney).compareTo(balancePay) < 0) {
					logger.error("用户帐户余额不足以支付传入的约定余额！");
					return false;
				}
				
				if (onlinePay.add(balancePay).add(couponMoney)
						.compareTo(order.getPrice().subtract(order.getHospitalPay())) == 0) {
					return true;
				} else {
					logger.error("支付金额不匹配，在线支付={},余额付款={},订单金额={},到院支付={},订单号={}", onlinePay, balancePay,
							order.getPrice(), order.getHospitalPay(), orderNo);
					smsService.sendSysSMS("支付金额不匹配，在线支付=" + onlinePay + ",余额付款=" + balancePay + ",订单金额="
							+ order.getPrice() + ",到院支付=" + order.getHospitalPay() + ",订单号=" + orderNo);
					return false;
				}
			} else if (order.getPayType() == PayType.FINANCING.getId()) { // 分期校验
				UserQuota quota = quotaService.queryUserQuota(order.getUid());
				if (quota.getAuthStatus() != AuthStatus.ALREADYINTERVIEW.getId()) {
					logger.warn("用户尚未认证完成！不能分期支付！orderNo={}", orderNo);
					smsService.sendSysSMS("用户尚未认证完成！不能分期支付！orderNo=" + orderNo);
					return false;
				} else if (order.getPrice().subtract(onlinePay).subtract(balancePay).subtract(couponMoney)
						.compareTo(quota.getQuotaLeft()) > 0) {
					logger.warn("订单为分期类别，但实际分期额度＋在线支付额度<订单总额");
					smsService.sendSysSMS("非法支付：订单为分期类型，但实际可用额度＋在线支付额度<订单总额,orderNo=" + orderNo);
					return false;
				}
				// 判断订单分期额度
				// 八嘎，还没有个固定的规则判断，先用自己些的简单方式吧。
				// 这里仅仅比较简单的判断分期总金额，实际需要调整！！！！！！！==的情况为无息
				logger.info("计算分期  {}|{}|{}|{}", order.getPrice(), onlinePay, balancePay, couponMoney);
				BigDecimal baseFQMoney = order.getPrice().subtract(onlinePay).subtract(balancePay).subtract(couponMoney);
				logger.info(" fenqiIsOk params {}|{}|{}|{}|", quota.getUid(), baseFQMoney, order.getPeriod(), order.getPeriodPay());
				if (!FQUtil.fenqiIsOk(quota, baseFQMoney, order.getPeriod(), order.getPeriodPay())) {
					logger.warn("非法支付：分期总还款额小于分期总额！orderNo=" + orderNo);
					smsService.sendSysSMS("非法支付：分期总还款额小于分期总额！orderNo=" + orderNo);
					return false;
				}
				return true;
			}
		} catch (Exception e) {
			logger.error("cangopay_process_exception", e);
		}
		return false;
	}
	
	

	
	
	/**
	 * 如果是用于支付订单的，进入到这里。
	 * 1、需要判断支付的方式是支付平台，还是余额、优惠券等方式支付
	 * @param result
	 * @throws Exception
	 */
	@Transactional
	public void updateOrderPayOk(PayCallbackResult result)  {
		logger.info("PayCallBack result :{}", result);
		
		try {
			OrderInfo order = orderService.findByOrderNo(result.getOrderNo());
			OrderStatus toStatus = OrderStatus.PAY_OK;
			
			if (order.getStatus() == toStatus.getValue()) {
				logger.warn("数据库中本订单状态与目标状态相同，skip后续流程！");
				throw new Exception("数据库中本订单状态与目标状态相同，skip后续流程！");
			}
			
//			if(order.getPolicyStatus() == PolicyStatus.AUDITING){
//				//更新保单状态
//				PolicyInfo policyInfo = policyService.findPolicyInfoByOrder(order.getOrderNo());
//
//				policyInfo.setPolicyStatus(PolicyStatus.INSURE_EFFECT);
//				policyInfoMapper.updateByPrimaryKey(policyInfo);
//				
//			}
			
			//更新订单的支付状态
			long l = orderService.updateOrder(order.getId(), OrderStatus.BOOK_OK.getValue(), OrderStatus.PAY_OK.getValue());
			logger.info("updateOrder 方法 ，修改订单状态 ，orderId：{}，oldState：{}，newState：{}",order.getId(),OrderStatus.BOOK_OK.getValue(), OrderStatus.PAY_OK.getValue());
			if (l <= 0) {
				logger.warn("回调更新订单状态失败，需要报警！payCallbackResult={}", result);
				smsService.sendSysSMS("订单回调更新订单状态失败,order:" + result.getOrderNo());
			}
			
			//更新onlinePay。
			order.setOnlinePay(result.getAmount());
			long onlinePayCount = orderService.updateOnlinePayByPrimaryKey(order.getOrderNo(), result.getAmount());
			if(onlinePayCount <= 0){
				logger.warn("回调更新订单状态失败，需要报警！payCallbackResult={}", result);
				smsService.sendSysSMS("订单回调更新订单状态失败,order:" + result.getOrderNo());
			}
			
			
			PayRecord record = payRecordService.findByOrderNo(order.getOrderNo());
			if (record.getStatus() == PayStatus.PAID && PayAPIType.fromCode(record.getTpp()) != result.getApiType()) {
				logger.warn("发现疑似重复支付订单，请关注平台！orderNo={}, firstPay={}, nowPay={}", order.getOrderNo(), record.getTpp(),
						result.getApiType());
				smsService.sendSysSMS("发现疑似重复支付订单,order:" + result.getOrderNo() + ", firstPay=" + record.getTpp()
						+ ", nowPay=" + result.getApiType());
				logger.warn("终止本次回调处理！");
			}
			
			
			record.setStatus(PayStatus.PAID); // 是否应该写在这儿？
			record.setCallbackAt(result.getPayAt() == null ? new Date() : result.getPayAt());
			record.setTpp(result.getApiType().getCode());
			record.setTradeNo(result.getTradeNo());
			record.setBankCode(result.getBankCode());
			record.setPayAt(result.getPayAt());
			
			long m = payRecordService.updateOne(record);
			if (m <= 0) {
				logger.warn("回调更新充值记录失败，需要报警（请拨打报警电话110）！payCallbackResult={}", result);
				smsService.sendSysSMS("订单回调更新充值记录失败,order:" + result.getOrderNo());
			}

            //如果是用余额支付该订单,更新下用户余额
			if(result.getApiType() == PayAPIType.INNER){
                long count = quotaService.updateUserBalance(record.getUid(), result.getAmount());
                if(count!=1)
                    throw new Exception("用户余额更新失败");
            }


			
			//BigDecimal couponQuota = new BigDecimal(0);

						
			//User user = userService.queryUser(order.getUid());
			//UserQuota quota = quotaService.queryUserQuota(user.getUid());
			//String [] params = {"".equals(quota.getRealname())?"未设置用户名":quota.getRealname(), user.getMobile(), order.getOrderNo()};
			//smsService.sendFinishOrderSMS(params);
			
			
			//悟空保
			if(order.getPolicyStatus() == PolicyStatus.AUDITING){
				String ret = policyService.insure(order.getOrderNo());
				logger.info("wukong ret = {}", ret);
				JSONObject wk = JSONObject.fromObject(ret);
				if(wk.getInt("code") != 0){
					throw new Exception(wk.getString("msg"));
				}else{
					orderService.updatePolicyStatusByStatus(PolicyStatus.INSURE_EFFECT,PolicyStatus.AUDITING, order.getOrderNo());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("支付订单出现问题"+e);
		}
		
	}

    /**
     * 如果是用于支付订单的，进入到这里。
     * 1、需要判断支付的方式是支付平台，还是余额、优惠券等方式支付
     * @param result
     * @throws Exception
     */
    @Transactional
    public void updateOrderPayOk(BeeCloudResult result)  {
        logger.info("PayCallBack result :{}", result);

        try {
            OrderInfo order = orderService.findByOrderNo(result.getOptional().get("orderNo").toString());
            OrderStatus toStatus = OrderStatus.PAY_OK;

            if (order.getStatus() == toStatus.getValue()) {
                logger.warn("数据库中本订单状态与目标状态相同，skip后续流程！");
                throw new Exception("数据库中本订单状态与目标状态相同，skip后续流程！");
            }

//			if(order.getPolicyStatus() == PolicyStatus.AUDITING){
//				//更新保单状态
//				PolicyInfo policyInfo = policyService.findPolicyInfoByOrder(order.getOrderNo());
//
//				policyInfo.setPolicyStatus(PolicyStatus.INSURE_EFFECT);
//				policyInfoMapper.updateByPrimaryKey(policyInfo);
//
//			}
            PayAPIType payApiType  = null;
            if(result.getChannel_type().equals("UN")){
                payApiType = PayAPIType.UNIONPAY;
            }
            if(payApiType == null){
                logger.error("没有该付款方式(BeeCloud):{}",result.getChannel_type());
                throw new Exception("没有该付款方式(BeeCloud)");
            }
            //更新订单的支付状态
            long l = orderService.updateOrder(order.getId(), OrderStatus.BOOK_OK.getValue(), OrderStatus.PAY_OK.getValue());
            logger.info("updateOrder 方法 ，修改订单状态 ，orderId：{}，oldState：{}，newState：{}",order.getId(),OrderStatus.BOOK_OK.getValue(), OrderStatus.PAY_OK.getValue());
            if (l <= 0) {
                logger.warn("回调更新订单状态失败，需要报警！payCallbackResult={}", result);
                smsService.sendSysSMS("订单回调更新订单状态失败,order:" + result.getOptional().get("orderNo").toString());
            }

            //更新onlinePay。
            order.setOnlinePay(new BigDecimal(result.getOptional().get("amount").toString()));
            long onlinePayCount = orderService.updateOnlinePayByPrimaryKey(order.getOrderNo(), new BigDecimal(result.getOptional().get("amount").toString()));
            if(onlinePayCount <= 0){
                logger.warn("回调更新订单状态失败，需要报警！payCallbackResult={}", result);
                smsService.sendSysSMS("订单回调更新订单状态失败,order:" + result.getOptional().get("orderNo").toString());
            }


            PayRecord record = payRecordService.findByOrderNo(order.getOrderNo());
            if (record.getStatus() == PayStatus.PAID && PayAPIType.fromCode(record.getTpp()) != payApiType) {
                logger.warn("发现疑似重复支付订单，请关注平台！orderNo={}, firstPay={}, nowPay={}", order.getOrderNo(), record.getTpp(),
                        payApiType);
                smsService.sendSysSMS("发现疑似重复支付订单,order:" + result.getOptional().get("orderNo").toString() + ", firstPay=" + record.getTpp()
                        + ", nowPay=" + payApiType);
                logger.warn("终止本次回调处理！");
            }


            record.setStatus(PayStatus.PAID); // 是否应该写在这儿？
            record.setCallbackAt(result.getTimestamp() == null ? new Date() : new Date(result.getTimestamp()));
            record.setTpp(payApiType.getCode());

            long m = payRecordService.updateOne(record);
            if (m <= 0) {
                logger.warn("回调更新充值记录失败，需要报警（请拨打报警电话110）！payCallbackResult={}", result);
                smsService.sendSysSMS("订单回调更新充值记录失败,order:" + result.getOptional().get("orderNo").toString());
            }



            //BigDecimal couponQuota = new BigDecimal(0);


            //User user = userService.queryUser(order.getUid());
            //UserQuota quota = quotaService.queryUserQuota(user.getUid());
            //String [] params = {"".equals(quota.getRealname())?"未设置用户名":quota.getRealname(), user.getMobile(), order.getOrderNo()};
            //smsService.sendFinishOrderSMS(params);


            //悟空保
            if(order.getPolicyStatus() == PolicyStatus.AUDITING){
                String ret = policyService.insure(order.getOrderNo());
                logger.info("wukong ret = {}", ret);
                JSONObject wk = JSONObject.fromObject(ret);
                if(wk.getInt("code") != 0){
                    throw new Exception(wk.getString("msg"));
                }else{
                    orderService.updatePolicyStatusByStatus(PolicyStatus.INSURE_EFFECT,PolicyStatus.AUDITING, order.getOrderNo());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("支付订单出现问题"+e);
        }

    }

	/**
	 * 分为几种方式： 1.分期：
	 * 
	 * 2.团购单：
	 * 
	 * @param order
	 * @param result
	 * @throws Exception
	 */
	private void thread4UserQuota(OrderInfo order, PayCallbackResult result, PayRecord record, BigDecimal couponQuota)
			throws Exception {
		// 使用分期额度
		BigDecimal usedQuota = order.getPrice().subtract(record.getBalance()).subtract(record.getAmount())
				.subtract(couponQuota);
		if (order.getPayType() == PayType.FULL.getId()) {
			// 先判断总金额是否一致,团购订单总金额不考虑分期部分
			if (usedQuota.compareTo(new BigDecimal(0)) == 0 && record.getAmount().compareTo(result.getAmount()) == 0) {
				logger.info("订单采用全款支付方式，回调完成！");
			} else {
				logger.error("应支付金额与实际支付金额不符！");
				smsService.sendSysSMS("应支付金额与实际支付金额不符！orderNo=" + order.getOrderNo());
				throw new Exception("应支付金额与实际支付金额不符！");
			}
		}
		else if (order.getPayType() == PayType.PART.getId()) {
			// 先判断总金额是否一致,在线＋到院的情况
			// 在线
			if (order.getOnlinePay().subtract(record.getAmount()).subtract(record.getBalance())
					.subtract(record.getPresent()).compareTo(new BigDecimal(0)) == 0) {
				logger.info("订单采用在线＋到院结合的支付方式，回调完成！");
			} else {
				logger.error("订单在线支付金额与实际在线支付金额不符，回调金额处理异常！");
				smsService.sendSysSMS("订单在线支付金额与实际在线支付金额不符！orderNo=" + order.getOrderNo());
				throw new Exception("订单在线支付金额与实际在线支付金额不符！");
			}
		} else if (order.getPayType() == PayType.FINANCING.getId()) { // 分期付款的情况
			// 0支付，全部分期了
			if (result.getAmount().compareTo(record.getAmount()) == 0
					&& result.getAmount().compareTo(new BigDecimal(0)) == 0) {
				logger.info("用户为0支付！总价分期！orderNo:{}", order.getOrderNo());
			} else {
				logger.info("用户实际支付金额：{}", result.getAmount());
			}
			// 额度处理
			quotaService.updateUserQuota(order.getUid(), usedQuota);
		} else {
			logger.error("异常订单回调！orderNo={}", order.getOrderNo());
			smsService.sendSysSMS("异常订单回调！orderNo=" + order.getOrderNo());
		}
	}

	@Transactional
	public boolean updateRechargePayOk(PayCallbackResult result, PayStatus status) throws Exception {
		PayRecord r = payRecordService.updateOne(result, status);
		if (r == null) {
			throw new Exception("更新充值记录失败！");
		}
        quotaService.updateUserBalance(r.getUid(),result.getAmount().negate());

        // 成功发送短信
		sendRechargeSMS(r.getUid(), r.getAmount(), BigDecimal.valueOf(0));
		return true;
	}

    @Transactional
    public boolean updateRechargePayOk(BeeCloudResult result,PayStatus status){
        try{
            PayRecord r = payRecordService.updateOne(result,status);
            if (r == null) {
                throw new Exception("更新充值记录失败！");
            }
            quotaService.updateUserBalance(Long.valueOf(result.getOptional().get("uid").toString()),
                    new BigDecimal(result.getTransaction_fee().toString()).divide(BigDecimal.valueOf(100)).negate());
            // 成功发送短信
            sendRechargeSMS(r.getUid(), r.getAmount(), BigDecimal.valueOf(0));
            return true;

        }catch(Exception e){
            logger.error("updateRechargePayOk in PayService ERROR :{}",e);
            return false;
        }
    }

	// 发送短信!
	private void sendRechargeSMS(long uid, BigDecimal amount, BigDecimal present) {
		try {
			User user = userService.queryUser(uid);
			String msgTmpl = "";
			Object[] params;
			if (present.compareTo(new BigDecimal(0)) > 0) {
				msgTmpl = MsgTmplContext.getSmsTmpl("mobile_recharge_present_notice");
				params = new Object[] { user.getMobile(), DateUtil.formatLong(new Date()), amount, present };
			} else {
				msgTmpl = MsgTmplContext.getSmsTmpl("mobile_recharge_notice");
				params = new Object[] { user.getMobile(), DateUtil.formatLong(new Date()), amount };
			}
			MessageFormat messageFormat = new MessageFormat(msgTmpl);
			String message = messageFormat.format(params);
			smsService.sendSms(user.getMobile(), message);
		} catch (Exception e) {
			logger.error("RechargeSMS_SEND_ERROR", e);
		}
	}

	public OrderType getOrderType(String billNo) throws Exception {
		if (StringUtils.startsWithIgnoreCase(billNo, Constants.RECHARGE_ORDER_PREFIX)) {
			return OrderType.RECHARGE;
		} else if (StringUtils.startsWithIgnoreCase(billNo, Constants.ONLINE_ORDER_PREFIX)) {
			return OrderType.ONLINE;
		} else if(StringUtils.startsWithIgnoreCase(billNo, Constants.REFUND_ORDER_PREFIX)){
			return OrderType.REFUND;
		} else {
			throw new Exception("不支持的订单类型！billNo=" + billNo);
		}
	}

    @Transactional
    public boolean updateOrderRefundOk(PayCallbackResult result) throws Exception {

        PayRecord r = payRecordService.findByOrderNo(result.getOrderNo());
        //
        FinanceBill financeBill = financeBillService.findBillByBillNo(r.getOrderNo());
        if(financeBill == null || financeBill.getId() <= 0){
            return false;
        }
        financeBill.setPayAt(new Date());
        //不论是否过期，都要把状态修改成-1已支付状态。判断该账单是否是过期账单要在查询的时候通过看payat是否大于dueat，
        //而不是设置为状态2。状态2会在半夜启用Task来循环判定修改。状态2（BillStatus.OVER_TIME）表示的是逾期未付款，付了款之后怎能还是逾期未付款呢。
        //if(financeBill.getDueAt().getTime() > System.currentTimeMillis()){
        financeBill.setStatus(BillStatus.PAY_OFF.getId());
        //}else{
        //financeBill.setStatus(BillStatus.OVER_TIME.getId());
        //}
        financeBill.setUpdatedAt(new Date());

        long u = financeBillService.updateFinanceBillStatusAndPayAt(financeBill);
        long count = 0;
        if(result.getApiType() == PayAPIType.INNER){
           count  = quotaService.updateUserBalance(financeBill.getUid(), result.getAmount());
        }


        if(count != 1)throw new Exception("更新用户余额失败");

        logger.info("update orderRefund success! ret={}", u);
        return true;

    }

    @Transactional
    public boolean updateOrderRefundOk(BeeCloudResult result) throws Exception {

        PayRecord r = payRecordService.findByOrderNo(result.getOptional().get("orderNo").toString());
        //
        FinanceBill financeBill = financeBillService.findBillByBillNo(r.getOrderNo());
        if(financeBill == null || financeBill.getId() <= 0){
            return false;
        }
        financeBill.setPayAt(new Date());
        //不论是否过期，都要把状态修改成-1已支付状态。判断该账单是否是过期账单要在查询的时候通过看payat是否大于dueat，
        //而不是设置为状态2。状态2会在半夜启用Task来循环判定修改。状态2（BillStatus.OVER_TIME）表示的是逾期未付款，付了款之后怎能还是逾期未付款呢。
        //if(financeBill.getDueAt().getTime() > System.currentTimeMillis()){
        financeBill.setStatus(BillStatus.PAY_OFF.getId());
        //}else{
        //financeBill.setStatus(BillStatus.OVER_TIME.getId());
        //}
        financeBill.setUpdatedAt(new Date());

        long u = financeBillService.updateFinanceBillStatusAndPayAt(financeBill);

        logger.info("update orderRefund success! ret={}", u);
        return true;

    }

}