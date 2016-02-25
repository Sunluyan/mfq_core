package com.mfq.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import com.mfq.bean.BeeCloudResult;
import com.mfq.bean.FinanceBill;
import com.mfq.constants.CardType;
import com.mfq.payment.PayAPIType;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
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
import org.springframework.transaction.annotation.Transactional;

@Service
public class PayRecordService {

    private static final Logger logger = LoggerFactory
            .getLogger(PayRecordService.class);

    @Resource
    UserQuotaService quotaService;
    @Resource
    PayRecordMapper mapper;
    @Resource
    FinanceBillService financeBillService;

    /**
     * 需要插入充值记录表
     *
     * @param uid               用户id
     * @param orderNo，若充值则为充值单号
     */
    @Transactional
    public long saveRecord(OrderType orderType, long uid, String orderNo,
                           BigDecimal amount) throws Exception {
        if (uid <= 0) {
            logger.warn("UserId is unvalid for recharge process! uid={}", uid);
            return 0;
        }
        if (orderType == OrderType.RECHARGE && StringUtils.isBlank(orderNo)) {
            orderNo = makeChargeNo(uid);
        }
        if (orderType == OrderType.REFUND) {
            FinanceBill financeBill = financeBillService.findBillByBillNo(orderNo.split(",")[0]);

            BigDecimal present = new BigDecimal(0);
            BigDecimal balance = new BigDecimal(0);
            PayRecord record = buildDefaultRecord(orderType, uid, financeBill.getOrderNo(), amount, balance, present);
            long count = mapper.insertOne(record);
            if(count != 1)throw new Exception("插入流水出错");
            return 1;
        }
        BigDecimal present = new BigDecimal(0);
        BigDecimal balance = new BigDecimal(0);
        PayRecord record = buildDefaultRecord(orderType, uid, orderNo, amount,
                balance, present);
        logger.info("create record {}", record);
        return mapper.insertOne(record);
    }




    /**
     * 充值成功回调update
     *
     * @param result
     * @param status
     * @return
     */
    @Transactional
    public PayRecord updateOne(PayCallbackResult result, PayStatus status) throws Exception {
        String billNo = result.getOrderNo().split(",")[0];
        String orderNo = financeBillService.findBillByBillNo(billNo).getOrderNo();
        PayRecord record = queryLastByOrderNo(orderNo);

        if (record == null || status == null) {
            if (record == null || status == null) {
                throw new Exception("更新流水出错");
            }
        }
        logger.info("orderNo , record {} | {}", result.getOrderNo(), record.getId());
        logger.info("record status {} | {}", record.getStatus(), status);
        if (record.getStatus() == status) {
            throw new Exception("充值记录状态与要更新到的状态相同");
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
     * BeeCloud 充值成功回调
     *
     * @param result
     * @param status
     * @return
     */
    @Transactional
    public PayRecord updateOne(BeeCloudResult result, PayStatus status) throws Exception {
        String billNo = result.getOptional().get("orderNo").toString().split(",")[0];
        String orderNo = financeBillService.findBillByBillNo(billNo).getOrderNo();
        PayRecord record = queryLastByOrderNo(orderNo);
        if (record == null || status == null) {
            throw new Exception("更新流水出错");
        }
        logger.info("orderNo , record {} | {}", result.getOptional().get("orderNo").toString(), record.getId());
        logger.info("record status {} | {}", record.getStatus(), status);
        if (record.getStatus() == status) {
            logger.warn("充值记录状态与要更新到的状态相同！");
            // do nothing
            return null;
        }

        // 更新充值记录
        record.setAmount(new BigDecimal(result.getOptional().get("amount").toString()));
        record.setBankCode("");
        record.setCallbackAt(new Date());
        record.setCardType(CardType.UNDEFINED);
        record.setCardNo("");
        record.setPayAt(new Date());
        record.setStatus(status);
        record.setTpp(result.getChannel_type());
        record.setTradeNo(StringUtils.defaultIfBlank(result.getTransaction_id(), ""));
        record.setUpdatedAt(new Date(result.getTimestamp()));
        mapper.updateOne(record);
        logger.error("用户充值构造的内容：{}", record);
        return record;
    }

    /**
     * 只能清除未实际付款的充值记录update－status
     *
     * @param uid 用户的ID
     * @param cid pay_record的ID
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
        if (!CollectionUtils.isEmpty(list)) {
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

    public PayRecord queryLastByOrderNo(String orderNo){
        List<PayRecord> list = mapper.queryByOrderNo(orderNo);
        return list.get(list.size()-1);
    }

    public static void main(String[] args) throws Exception {
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring/spring.xml");
        PayRecordService service = ac.getBean(PayRecordService.class);
    }

    public long insertOne(PayRecord record) {
        return mapper.insertOne(record);
    }

    public long updateOne(PayRecord record) {
        return mapper.updateOne(record);
    }
}
