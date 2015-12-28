package com.mfq.service.user;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.mfq.bean.user.PresentRecord;
import com.mfq.constants.PresentType;
import com.mfq.dao.PresentRecordMapper;
import com.mfq.utils.DateUtil;

@Service
public class PresentRecordService {

    private static final Logger logger = LoggerFactory
            .getLogger(PresentRecordService.class);

    @Resource
    PresentRecordMapper mapper;
    
    private static final Date GAME_OVER_TIME = DateUtil.convertLong("2015-10-31 23:59:59");
    
    //充值赠送金额限额
    private static final int wxMax = 500;
    //充值赠送金额限额
    private static final int czMax = 1000;
    //总赠送金额限额
    private static final int allMax = 1500;
    
    /**
     * 按照类别获取某用户一次操作的应赠送金额
     * @param uid
     * @param type
     * @param amount
     * @return
     */
    public BigDecimal getPresentByType(long uid, PresentType type, BigDecimal amount){
        if(!DateUtil.isBefore(new Date(), GAME_OVER_TIME)){
            logger.warn("充1000赠送1000活动已截至，请关注官方信息，欢迎下次使用！");
            return new BigDecimal(0);
        }
        
        BigDecimal pa = computeAllPresent(uid); // 历史总充值记录
        BigDecimal pt = computeAllPresent(uid, type); // 按类型看充值记录
        BigDecimal max = new BigDecimal(0);
        if(pa.compareTo(new BigDecimal(allMax)) > 0){ // 一个人的总赠送额度不能大于1500，充值赠送额度不大于1000
            logger.warn("用户总赠送金额超过最高限额，本次支付不再赠送");
        }else if(type == PresentType.RECHARGE){
            if(pt.compareTo(new BigDecimal(czMax)) > 0){
                logger.warn("用户充值赠送金额超过最高限额，本次支付不再赠送");
            }else{
                max = new BigDecimal(czMax);
            }
        }else if(type == PresentType.WECHAT){
            if(pt.compareTo(new BigDecimal(wxMax)) > 0){
                logger.warn("用户微信赠送金额超过最高限额，本次支付不再赠送");
            }else{
                max = new BigDecimal(wxMax);
            }
        }
        
        BigDecimal present;
        if(amount == null){ // 微信直接赠送，此时传入的amount是空值
            present = max;
        }else if(amount.compareTo(max) > 0){
            present = max;
        }else{
            present = amount;
        }
        
        return present;
    }
    
    /**
     * 插入一条新的赠送记录
     * @param presentRecord
     * @return
     */
    public long insertPresent(PresentRecord presentRecord) {
        logger.info("PresentRecord insert :{}", presentRecord);
        return mapper.insertOne(presentRecord);
    }
    
    /**
     * 查询某个人所有的赠送金额-部分类别－包含历史情况
     * @param uid
     * @return
     */
    private BigDecimal computeAllPresent(long uid){
        BigDecimal all = new BigDecimal(0);
        for(PresentType t : PresentType.values()){
            all.add(computeAllPresent(uid, t));
        }
        return all;
    }
    
    /**
     * 某个类别总的赠送金额－包含历史情况
     * @param uid
     * @param type
     * @return
     */
    private BigDecimal computeAllPresent(long uid, PresentType type){
        List<PresentRecord> list = queryByUser(uid, type);
        BigDecimal all = new BigDecimal(0);
        if(CollectionUtils.isEmpty(list)){
            return all;
        }
        for(PresentRecord r : list){
            all.add(r.getAmount());
        }
        return all;
    }

    public List<PresentRecord> queryByUser(long uid, PresentType type) {
        return mapper.queryByUser(uid, type);
    }

    public long insertOne(PresentRecord presentRecord){
        return mapper.insertOne(presentRecord);
    }
}
