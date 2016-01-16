package com.mfq.service;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.mfq.bean.*;
import com.mfq.constants.OrderType;
import com.mfq.dao.InviteMoneyMapper;
import com.mfq.dao.InviteRecordMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by liuzhiguo1 on 16/1/15.
 */
@Service
public class InviteService {

    Logger logger = LoggerFactory.getLogger(InviteService.class);

    @Resource
    InviteRecordMapper mapper;
    @Resource
    InviteMoneyMapper inviteMoneyMapper;
    @Resource
    OrderService orderService;
    @Resource
    OrderFreedomService orderFreedomService;
    @Resource
    FinanceBillService financeBillService;

    public int insertSelective(InviteRecord record) {
        return mapper.insertSelective(record);
    }

    /**
     * 插入奖励金额等操作
     *
     * @param result
     * @return
     */
    @Transactional
    public void inviteMoneyRecordOperation(PayCallbackResult result) throws Exception {
        //通过orderNo得到uid
        //首先判断此人是否是别人的下线
        //插入表

        String orderNo = result.getOrderNo();
        long uid = 0;

        if (orderNo.contains("mn")) {
            OrderInfo orderInfo = orderService.findByOrderNo(orderNo);
            uid = orderInfo.getUid();
        } else if (orderNo.contains("bl")) {
            FinanceBill financeBill = financeBillService.findBillByBillNo(orderNo);
            uid = financeBill.getUid();
        } else if (orderNo.contains("fk")) {
            OrderFreedom orderFreedom = orderFreedomService.selectByOrderNo(orderNo);
            uid = orderFreedom.getUid();
        }

        if (uid == 0) {
            logger.error("粗大事啦");//order中没有uid
            throw new Exception("粗大事啦!");
        }

        InviteRecordExample example = new InviteRecordExample();
        example.or().andInvitedUidEqualTo(uid);
        List<InviteRecord> inviteRecordList = mapper.selectByExample(example);
        if (CollectionUtils.isEmpty(inviteRecordList)) {
            return;
        }

        InviteMoney inviteMoney = new InviteMoney();
        inviteMoney.setUid(uid);
        inviteMoney.setPayMoney(result.getAmount());
        inviteMoney.setCreateDate(new Date());
        inviteMoney.setRewardMoney(getRewardMoney(result.getAmount()));

        int count = inviteMoneyMapper.insertSelective(inviteMoney);
        if(count != 1){
            logger.error("插入提成错误");
            throw new Exception("插入提成错误");
        }
    }

    /**
     * 插入奖励金额等操作(BeeCloud)
     *
     * @param result
     * @return
     */
    public void inviteMoneyRecordOperation(BeeCloudResult result) throws Exception{
        //通过orderNo得到uid
        //首先判断此人是否是别人的下线
        //插入表

        Long uid = Long.parseLong(result.getOptional().get("uid").toString());


        if (uid == 0 || uid == null) {
            logger.error("粗大事啦");//order中没有uid
            throw new Exception("粗大事啦!");
        }

        InviteRecordExample example = new InviteRecordExample();
        example.or().andInvitedUidEqualTo(uid);
        List<InviteRecord> inviteRecordList = mapper.selectByExample(example);
        if (CollectionUtils.isEmpty(inviteRecordList)) {
            return;
        }

        BigDecimal amount = BigDecimal.valueOf(result.getTransaction_fee()/100);
        InviteMoney inviteMoney = new InviteMoney();
        inviteMoney.setUid(uid);
        inviteMoney.setPayMoney(amount);
        inviteMoney.setCreateDate(new Date());
        inviteMoney.setRewardMoney(getRewardMoney(amount));

        int count = inviteMoneyMapper.insertSelective(inviteMoney);
        if(count != 1){
            logger.error("插入提成错误");
            throw new Exception("插入提成错误");
        }
    }

    public static void main(String[] args) {
        InviteService inviteService = new InviteService();
        BigDecimal result = inviteService.getRewardMoney(BigDecimal.valueOf(170000));
        System.out.println(result);
    }

    public BigDecimal getRewardMoney(BigDecimal amount) {
        //10000以下4%,
        //10000-30000 5%,
        //30000-50000 6%,
        //50000-80000 7%,
        //        >80000 8%
        BigDecimal persent = new BigDecimal(0);
        if (amount.compareTo(BigDecimal.valueOf(10000)) < 0) {
            persent = BigDecimal.valueOf(0.04);
        } else if (amount.compareTo(BigDecimal.valueOf(30000)) < 0) {
            persent = BigDecimal.valueOf(0.05);
        } else if (amount.compareTo(BigDecimal.valueOf(50000)) < 0) {
            persent = BigDecimal.valueOf(0.06);
        } else if (amount.compareTo(BigDecimal.valueOf(80000)) < 0) {
            persent = BigDecimal.valueOf(0.07);
        } else if(amount.compareTo(BigDecimal.valueOf(80000)) >= 0){
            persent = BigDecimal.valueOf(0.08);
        }
        BigDecimal result = amount.multiply(persent);

        return result;
    }
}
