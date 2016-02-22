package com.mfq.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.mfq.annotation.MFQDao;
import com.mfq.bean.FinanceBill;
import com.mfq.constants.BillStatus;
import org.springframework.stereotype.Component;

@Component
@MFQDao
public interface FinanceBillMapper {

	public long insertOne(FinanceBill bill);

	public FinanceBill queryOne(@Param("id") long id);
	
	public List<FinanceBill> queryBillsByUid(@Param("uid") long uid);
	
	public List<FinanceBill> findBillsByUidAndStatus(@Param("uid") long uid, @Param("list") List<BillStatus> list);

	public FinanceBill queryBillByBillNo(@Param("billNo")String billNo);

	public long updateFinanceBillStatusAndPayAt(FinanceBill bill);
	
	//分期记录页面所需信息
	public List<Map<String,Object>> findFinanceListByUid(@Param("uid") long uid);
	
	public FinanceBill findFinanceDetailById(@Param("orderNo") String orderNo);
	
	public List<FinanceBill> findFinanceListByStatus(@Param("status") long status);
	
	public long insertSelective(FinanceBill record);
	
    public long updateByPrimaryKeySelective(FinanceBill record);


	public List<FinanceBill> queryBillByOrderNo(@Param("uid") long uid,@Param("orderNo") String orderNo);
}