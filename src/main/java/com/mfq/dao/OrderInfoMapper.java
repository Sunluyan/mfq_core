package com.mfq.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mfq.annotation.MFQDao;
import com.mfq.bean.OrderInfo;
import com.mfq.constants.OrderStatus;
import com.mfq.constants.PayType;
import com.mfq.constants.PolicyStatus;
import com.mfq.constants.ProductType;

@MFQDao
public interface OrderInfoMapper {

    public List<OrderInfo> findIdsByTypeAndStatus(@Param("type") ProductType type,
            @Param("status") OrderStatus status);

    public OrderInfo findByOrderNo(@Param("orderNo") String orderNo);

    public long insertOrder(OrderInfo order);

    public long updateOrderInfo(OrderInfo orderInfo);

    public List<OrderInfo> findByUid(@Param("uid") long uid);

    public long updateOrderStatusSafe(@Param("id") long id,
            @Param("oldStatus") int oldStatus,
            @Param("newStatus") int newStatus);
    
	public List<OrderInfo> findByUidAndProductType(@Param("uid") long uid, @Param("type") ProductType type);
	
	public long findByUidAndPayTypeAndPid(@Param("uid") long uid, @Param("type") int type, @Param("pid") long pid);
	

	public long updateByPrimaryKeySelective(OrderInfo record);

	public int updateOnlinepayByOrderNo(@Param("orderNo")String orderNo,@Param("onlinePay")BigDecimal onlinePay);

	public int updatePolicyStatusByStatus(@Param("newStatus")PolicyStatus insureEffect, @Param("oldStatus")PolicyStatus auditing, @Param("orderNo") String orderNo);
}

