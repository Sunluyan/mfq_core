package com.mfq.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.mfq.bean.PayCallbackResult;
import com.mfq.bean.PayRecord;
import com.mfq.bean.user.UserQuota;
import com.mfq.constants.Constants;
import com.mfq.constants.OrderType;
import com.mfq.constants.PayStatus;
import com.mfq.dao.PayRecordMapper;
import com.mfq.service.user.UserQuotaService;
import com.mfq.utils.DateUtil;
import com.mfq.utils.ListSortUtil;

@Service
public class PayRecordService {

    private static final Logger logger = LoggerFactory
            .getLogger(PayRecordService.class);

    @Resource
    UserQuotaService quotaService;
    @Resource
    PayRecordMapper mapper;

    /**
     * 需要插入充值记录表
     * 
     * @param isRecharge是否充值
     * @param uid
     *            用户id
     * @param orderNo，若充值则为充值单号
     * @return <=0插入失败，>0插入成功
     */
    public long saveRecord(OrderType orderType, long uid, String orderNo,
            BigDecimal amount, BigDecimal balancePay) {
        if (uid <= 0) {
            logger.warn("UserId is unvalid for recharge process! uid={}", uid);
            return 0;
        }
        if (orderType == OrderType.RECHARGE && StringUtils.isBlank(orderNo)) {
            orderNo = makeChargeNo(uid);
        }
        UserQuota quota = quotaService.queryUserQuota(uid);
        BigDecimal balance = balancePay; // 纯粹的余额部分
        BigDecimal present = new BigDecimal(0);
        // 如果存在赠送金额，则判断赠送金额使用部分，优先使用余额（非赠送部分）
        if (quota.getPresent().compareTo(new BigDecimal(0)) > 0) {
        	// 假如余额需要支付金额 大于 账户中的纯余额部分
        	if(balancePay.compareTo(quota.getBalance()) > 0){
        		balance = quota.getBalance(); // 纯粹的余额部分应该取一个较小值--取实际剩余的纯剩余额度，否则（账户余额足够大）按上面的逻辑获取-balancePay
        	}
            present = balancePay.subtract(balance);
            logger.info("balance={}, balancePay={}", balance, balancePay);
        }
		if (amount.compareTo(BigDecimal.valueOf(0)) < 0 || balance.compareTo(BigDecimal.valueOf(0)) < 0
				|| present.compareTo(BigDecimal.valueOf(0)) < 0) {
			return 0;
		}
        PayRecord record = buildDefaultRecord(orderType, uid, orderNo, amount,
                balance, present);
        logger.info("create record {}", record);
        return mapper.insertOne(record);
    }

    /**
     * 支付成功回调update
     * 
     * @param result
     * @param status
     * @return
     */
    public PayRecord updateOne(PayCallbackResult result, PayStatus status) {
        PayRecord record = findByOrderNo(result.getOrderNo());
        if (record == null || status == null) {
            logger.error("不存在与{}匹配的充值记录", result.getOrderNo());
            return null;
        }
        logger.info("orderNo , record {} | {}", result.getOrderNo(), record.getId());
        logger.info("record status {} | {}",record.getStatus(), status);
        if (record.getStatus() == status) {
            logger.warn("充值记录状态与要更新到的状态相同！");
            // do nothing
            return null;
        }
        // 更新充值记录
        record.setAmount(result.getAmount());
        record.setBankCode(
                StringUtils.defaultIfBlank(result.getBankCode(), ""));
        record.setCallbackAt(new Date());
        record.setCardType(result.getCardType());
        record.setCardNo(StringUtils.defaultIfBlank(result.getLastno(), ""));
        record.setPayAt(new Date());
        record.setStatus(status);
        record.setTpp(result.getApiType().getCode());
        record.setTradeNo(StringUtils.defaultIfBlank(result.getTradeNo(), ""));
        record.setUpdatedAt(new Date());
        mapper.updateOne(record);
        logger.error("用户充值构造的内容：{}", record);
        return record;
    }

    /**
     * 只能清除未实际付款的充值记录update－status
     * 
     * @param uid
     *            用户的ID
     * @param cid
     *            pay_record的ID
     * @return
     */
    public boolean cancelRecharge(long uid, long cid) {
        PayRecord record = findById(cid);
        if (record.getUid() != uid) {
            logger.error("取消不属于自己的充值记录！");
            return false;
        }
        record.setStatus(PayStatus.CANCEL);
        return mapper.updateOne(record) > 0;
    }

    /**
     * 获取用户充值/支付历史记录
     * 
     * @param uid
     * @return
     */
    public List<PayRecord> findUserHistory(OrderType orderType, long uid) {
        // 状态为取消的不展示
    	List<PayRecord> list = mapper.findByUId(orderType, uid, PayStatus.PAID);
		//排序
        if(!CollectionUtils.isEmpty(list)){
        	ListSortUtil<PayRecord> sortList = new ListSortUtil<PayRecord>();
        	sortList.sort(list, "updatedAt", "desc"); 
        }
    	return list;
    }

    /**
     * 构造默认记录
     * 
     * @return
     */
    private PayRecord buildDefaultRecord(OrderType orderType, long uid, String orderNo,
            BigDecimal amount, BigDecimal balance, BigDecimal present) {
        PayRecord record = new PayRecord();
        record.setOrderType(orderType);
        record.setUid(uid);
        record.setCardNo("");
        record.setOrderNo(orderNo);
        record.setStatus(PayStatus.GO_PAY);
        record.setTradeNo("");
        record.setTpp("");
        record.setBankCode("");
        record.setAmount(amount);
        if (balance == null) {
            balance = new BigDecimal(0);
        }
        record.setBalance(balance);
        if (present == null) {
            present = new BigDecimal(0);
        }
        record.setPresent(present);
        return record;
    }

    /**
     * 生成支付单号，仅支付与对账使用
     * 
     * @param pId：产品ID
     * @return
     */
    public String makeChargeNo(long uid) {
        String pHex = Long.toHexString(uid);
        Integer randomInt = new Random().nextInt(9999);
        String randomStr = new DecimalFormat("0000").format(randomInt);
        StringBuilder sb = new StringBuilder(Constants.RECHARGE_ORDER_PREFIX);
        sb.append(DateUtil.formatCurTimeLong());
        sb.append(randomStr);
        sb.append(StringUtils.leftPad(pHex, 4, "0"));
        logger.info("After Make Charge No is : {}", sb.toString());
        return sb.toString();
    }

    public PayRecord findById(long id) {
        return mapper.findById(id);
    }

    public PayRecord findByOrderNo(String orderNo) {
        return mapper.findByOrderNo(orderNo);
    }

    public long insertOne(PayRecord record) {
        return mapper.insertOne(record);
    }

    public long updateOne(PayRecord record) {
        return mapper.updateOne(record);
    }
}
