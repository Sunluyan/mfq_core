package com.mfq.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mfq.annotation.MFQDao;
import com.mfq.bean.PayRecord;
import com.mfq.constants.OrderType;
import com.mfq.constants.PayStatus;
import org.springframework.stereotype.Component;

@MFQDao
@Component
public interface PayRecordMapper {

    public PayRecord findById(@Param("id") long id);

    public PayRecord findByOrderNo(@Param("orderNo") String orderNo);

    public List<PayRecord> findByUId(@Param("orderType") OrderType orderType, @Param("uid") long uid,
            @Param("status") PayStatus status);
    
    public long insertOne(PayRecord record);

    public long updateOne(PayRecord record);
    
}
