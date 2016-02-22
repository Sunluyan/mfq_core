package com.mfq.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.google.common.collect.Maps;
import com.mfq.bean.app.OrderInfo2App;
import com.mfq.constants.PayType;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.google.common.collect.Lists;
import com.mfq.bean.FinanceBill;
import com.mfq.bean.OrderInfo;
import com.mfq.bean.Product;
import com.mfq.bean.app.FinanceBill2App;
import com.mfq.constants.BillStatus;
import com.mfq.constants.ErrorCodes;
import com.mfq.dao.FinanceBillMapper;
import com.mfq.service.user.UserQuotaService;
import com.mfq.utils.JsonUtil;

/**
 * 分期实现
 * 
 * @author xingyongshan
 *
 */
@Service
public class FinanceBillService {

	private static final Logger logger = LoggerFactory
			.getLogger(FinanceBillService.class);

	@Resource
	ProductService productService;
	@Resource
	OrderService orderService;
	@Resource
	FinanceBillMapper mapper;
	@Resource
	UserQuotaService service;

	/**
	 * bill info for app
	 * 
	 * @param uid
	 * @param s
	 * @return
	 * @throws Exception
	 */
	public String fetchAppBillInfo(long uid, Integer s) throws Exception {
		List<FinanceBill> data = findBillsByStatus(uid, s);
		if (CollectionUtils.isEmpty(data)) {
			return JsonUtil.toJson(ErrorCodes.CORE_ERROR, "构建待还款账单信息异常", null);
		} else {
			List<FinanceBill2App> appBills = makeAppBillByBill(data);
			return JsonUtil.successResultJson(appBills);
		}
	}

	/**
	 * findBillByBillNo
	 * 
	 * @return
	 * @throws Exception
	 */
	public FinanceBill findBillByBillNo(String billNo) {
		return mapper.queryBillByBillNo(billNo);
	}

	/**
	 * 构造APP账单bean
	 * 
	 * @param bills
	 * @return
	 */
	private List<FinanceBill2App> makeAppBillByBill(List<FinanceBill> bills) {
		if (CollectionUtils.isEmpty(bills)) {
			return null;
		}
		List<FinanceBill2App> list = Lists.newArrayList();
		for (FinanceBill bill : bills) {
			OrderInfo order = orderService.findByOrderNo(bill.getOrderNo());
			if (order != null) {
				Product product = productService.findById(order.getPid());
				FinanceBill2App bean = new FinanceBill2App(bill, order, product);
				list.add(bean);
			}
		}
		return list;
	}

	public List<FinanceBill> findBillsByStatus(long uid, Integer s)
			throws Exception {
		BillStatus bs = BillStatus.fromId(s);
		List<BillStatus> status = Lists.newArrayList();
		if (bs == null) {
			if (BillStatus.NOT_PAY == s) {
				status.add(BillStatus.OVER_TIME);
				status.add(BillStatus.WAIT_PAY);
			} else {
				logger.error("参数不合法！uid={}, s={}", uid, s);
				throw new Exception("传入请求类型参数不合法！");
			}
		} else {
			status.add(bs);
		}
		List<FinanceBill> data = mapper.findBillsByUidAndStatus(uid, status);
		if (CollectionUtils.isEmpty(data)) {
			return Lists.newArrayList();
		}
		return data;
	}

	/**
	 * 按用户查询所有账单记录，包含已还清，未还清，超期限未还等各种状态
	 * 
	 * @param uid
	 * @return
	 */
	public List<FinanceBill> findAllBills(long uid) {
		List<FinanceBill> bills = mapper.queryBillsByUid(uid);
		if (CollectionUtils.isEmpty(bills)) {
			return Lists.newArrayList();
		}
		return bills;
	}

	public long updateFinanceBillStatusAndPayAt(FinanceBill financeBill) {
		if (financeBill == null || financeBill.getId() <= 0) {
			return 0;
		}
		return mapper.updateFinanceBillStatusAndPayAt(financeBill);
	}

	// 传入uid 获取分期列表
	public List<Map<String, Object>> findFinanceListByUid(long uid) {
		if (uid == 0)
			return null;
		return mapper.findFinanceListByUid(uid);
	}

	// 传入finance.id 来获取分期详情
	public FinanceBill findFinanceDetailById(String orderNo) {
		return mapper.findFinanceDetailById(orderNo);
	}

	@Transactional
	public boolean createFinance(OrderInfo orderInfo) throws Exception {

		Integer period = orderInfo.getPeriod();
		BigDecimal everyMonthPay = BigDecimal.valueOf(0);
		everyMonthPay = orderInfo.getPeriodPay().divide(new BigDecimal(period),2,BigDecimal.ROUND_HALF_EVEN);
		long pid = orderInfo.getPid();
		Date now = new Date(System.currentTimeMillis());
		
		for (int i = 0; i < period; i++) {
			String billNo = orderService.makeOrderNo(pid).replace("mn", "bl");
			FinanceBill finance = new FinanceBill();
			finance.setUid(orderInfo.getUid());
			finance.setBillNo(billNo);
			finance.setOrderNo(orderInfo.getOrderNo());
			finance.setNewBalance(everyMonthPay);
			finance.setLateFee(new BigDecimal(0));
			finance.setCurPeriod(i + 1);
			finance.setAllPeriod(orderInfo.getPeriod());
			finance.setStatus(1);
			finance.setTradeAt(orderInfo.getCreatedAt());
			finance.setChargeAt(now);
			finance.setBillAt(now);
			// 获得每次订单的应还款时间，并把「天」后的时间单位清零。
			int month = now.getMonth();
			Date thisMonth = new Date(now.getTime());
			thisMonth.setMonth(month + i + 1);
			thisMonth.setHours(0);
			thisMonth.setMinutes(0);
			thisMonth.setSeconds(0);

			finance.setDueAt(thisMonth);
			finance.setPayAt(null);
			finance.setUpdatedAt(now);

			long id = mapper.insertOne(finance);
			if (id <= 0) {
				throw new Exception("创建账单错误！");
			}

		}
		return true;
	}
	
	@Transactional
	public void updateFinanceStateTask(){
		List<FinanceBill> financeList = mapper.findFinanceListByStatus(1);
		long now = System.currentTimeMillis();
		for (FinanceBill financeBill : financeList) {
			long duetime = financeBill.getDueAt().getTime();
			if(duetime<now){
				//如果时间大于现在，则改成逾期未还款的状态，并更新。
				//本月本金的5%
				FinanceBill record = new FinanceBill();
				record.setId(financeBill.getId());
				record.setStatus(2);
				OrderInfo orderinfo = orderService.findByOrderNo(financeBill.getOrderNo());
				//x = 11500 / 1.15  第一步获得总分期金额
				
				BigDecimal lateFee = orderinfo.getPeriodPay().multiply(new BigDecimal(orderinfo.getPeriod()));
				lateFee = lateFee.divide(new BigDecimal("0.0125").multiply(new BigDecimal(orderinfo.getPeriod())).add(new BigDecimal(1)),2,BigDecimal.ROUND_HALF_EVEN);
				lateFee = lateFee.divide(new BigDecimal(orderinfo.getPeriod()), 2, BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal("0.05")).setScale(2, BigDecimal.ROUND_HALF_EVEN);
				record.setLateFee(lateFee);
				if(mapper.updateByPrimaryKeySelective(record)!=1){
					throw new RuntimeException("更新账单状态出现问题，已回退");
				};
			}
		}
	}
	
	@Transactional
	public void updateFinanceLateFeeTask() throws Exception{
		List<FinanceBill> financeList = mapper.findFinanceListByStatus(2);
		long now = System.currentTimeMillis();
		for (FinanceBill financeBill : financeList) {
			long duetime = financeBill.getDueAt().getTime();
			if(duetime<now){
				//如果时间减去现在的时间 / 1000*60*60*24  = x 则减去x天数的滞纳金
				FinanceBill record = new FinanceBill();
				record.setId(financeBill.getId());
				
				long days = (now - duetime) / 1000 * 60 * 60 * 24 +1;
				
				
				OrderInfo orderinfo = orderService.findByOrderNo(financeBill.getOrderNo());
				BigDecimal lateFee = orderinfo.getPeriodPay().multiply(new BigDecimal(orderinfo.getPeriod()));
				lateFee = lateFee.divide(new BigDecimal("0.0125").multiply(new BigDecimal(orderinfo.getPeriod())).add(new BigDecimal(1)),2,BigDecimal.ROUND_HALF_EVEN);
				lateFee = lateFee.divide(new BigDecimal(orderinfo.getPeriod()), 2, BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal("0.05")).setScale(2, BigDecimal.ROUND_HALF_EVEN);
				lateFee = lateFee.multiply(new BigDecimal(days));
				
				record.setLateFee(lateFee);
				if (mapper.updateByPrimaryKeySelective(record)!=1){
					throw new RuntimeException("更新滞纳金出现问题，已回退");
				}
			}
		}
	}
	
	public void start(){
		updateFinanceStateTask();
	}

	public static void main(String[] args) throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring/spring.xml");
		FinanceBillService financeBillService = ac.getBean(FinanceBillService.class);

		String finance = financeBillService.queryAppFinancesByOrder(2936,"mn2016011116070133780097");
		System.out.println(finance);
	}
//13599996598

	public String queryAppFinancesByOrder(long uid, String orderNo) throws Exception {

		OrderInfo orderInfo = orderService.findByOrderNo(orderNo);

		if (orderInfo==null || orderInfo.getUid()!=uid || orderInfo.getPayType()!= PayType.FINANCING.getId()){
			return "订单不存在...";
		}

		List<FinanceBill> finances = mapper.queryBillByOrderNo(uid, orderNo);
		if (finances.size() < 3){
			throw new Exception("获取分期账单错误...");
		}

		//构建返回结果
		OrderInfo2App orderApp = orderService.makeAppOrderByOrder(orderInfo);
		List<FinanceBill2App> financesApp = makeAppBillByBill(finances);
		Map<String, Object> ret = Maps.newHashMap();
		ret.put("order_info", orderApp);
		ret.put("finances", financesApp);

		return JsonUtil.successResultJson(ret);
	}



}
