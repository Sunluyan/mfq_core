package com.mfq.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mfq.annotation.LoginRequired;
import com.mfq.bean.FinanceBill;
import com.mfq.bean.OrderInfo;
import com.mfq.bean.app.OrderInfo2App;
import com.mfq.bean.user.UserQuota;
import com.mfq.constants.AuthStatus;
import com.mfq.constants.ErrorCodes;
import com.mfq.helper.SignHelper;
import com.mfq.service.FinanceBillService;
import com.mfq.service.OrderService;
import com.mfq.service.user.UserQuotaService;
import com.mfq.utils.JsonUtil;


@Controller
@RequestMapping("/finance")
public class FinanceController {

	private static final Logger logger = LoggerFactory.getLogger(FinanceController.class);

	@Resource
	FinanceBillService financeBillService;
	@Resource
	UserQuotaService userQuotaService;
	@Resource
	OrderService orderService;

	/**
	 * 账单
	 *
	 *3.获取个人近期待还款记录（近7日）
	    订单号
	    产品信息简介
	    当前待还金额
	    当前待还期数
	    还款总分期数
	    还款日期deadline
	 *
	 * @throws Exception
	 *             任何异常
	 */
	@RequestMapping(value = {"/bill", "/bill/"}, method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	@LoginRequired
	public String userDetail(HttpServletRequest request,
			HttpServletResponse response) {
		String ret = "";
		try{
			Map<String, Object> params = JsonUtil.readMapFromReq(request);
			if (!SignHelper.validateSign(params)) { // 签名验证失败
				ret = JsonUtil.toJson(ErrorCodes.SIGN_VALIDATE_ERROR, "签名验证失败",
						null);
				logger.error("签名验证失败！ret={}", ret);
				return ret;
			}
			if(params.get("uid") == null){
				ret = JsonUtil.toJson(ErrorCodes.CORE_PARAM_UNLAWFUL, "参数不合法", null);
				logger.error("参数不合法！ret={}", ret);
				return ret;
			}
			Long uid = Long.parseLong(params.get("uid").toString());
			if(uid == null || uid == 0){
				ret = JsonUtil.toJson(ErrorCodes.CORE_PARAM_UNLAWFUL, "参数不合法", null);
				logger.error("参数不合法！ret={}", ret);
				return ret;
			}

			Integer s = 100;
			if(params.get("type")!=null){
				s=Integer.parseInt(params.get("type").toString());
			}
			ret = financeBillService.fetchAppBillInfo(uid, s);
		}catch(Exception e){
			logger.error("Exception Validate Vcode!", e);
			ret = JsonUtil.toJson(ErrorCodes.CORE_ERROR, "系统异常", null);
		}
		logger.info("UserProfile_EndRet={}", ret);
		return ret;
	}




	/**
	 * 分期的首页，需要显示剩余额度和总额度。
	 * 传入 uid 
	 * @param request	
	 * @return 剩余额度和总额度 {authStatus:xx,quotaAll:xx,quotaLeft:xxx}
	 */
	@RequestMapping(value = {"/main","/main/"}, method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public @ResponseBody String financeMainPage(HttpServletRequest request){
		String ret = "";
		try {
			Map<String, Object> params = JsonUtil.readMapFromReq(request);
			if(!SignHelper.validateSign(params)){
				ret = JsonUtil.toJson(ErrorCodes.SIGN_VALIDATE_ERROR, "签名验证失败", null);
				logger.error("签名验证失败！ret={}", ret);
				return ret;
			}
			if(params.get("uid") == null){
				ret = JsonUtil.toJson(ErrorCodes.CORE_PARAM_UNLAWFUL, "参数不合法", null);
				logger.error("参数不合法！ret={}", ret);
				return ret;
			}

			long uid = Long.parseLong(params.get("uid").toString());
			UserQuota userQuota = userQuotaService.queryUserQuota(uid);

			if(userQuota==null)ret=JsonUtil.toJson(ErrorCodes.USER_NOT_FIND,"用户不存在",null);

			Map<String,Object> result = new HashMap<String, Object>();
			result.put("authStatus", userQuota.getAuthStatus());
			result.put("quotaAll", userQuota.getQuotaAll());
			result.put("quotaLeft", userQuota.getQuotaLeft());
			ret = JsonUtil.toJson(0, null, result);

		}catch (Exception e) {
			logger.error("Exception in FinanceController.userFinanceState = {}",e);
			ret = JsonUtil.toJson(ErrorCodes.CORE_ERROR, "系统异常", null);
		}
		return ret;
	}

	/**
	 * 根据用户id获取分期列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = {"/order","/order/"}, method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@LoginRequired
	public @ResponseBody String financeOrderList(HttpServletRequest request,HttpServletResponse response){
		String ret="";
		try {
			Map<String, Object> params = JsonUtil.readMapFromReq(request);
			if(!SignHelper.validateSign(params)){
				ret = JsonUtil.toJson(ErrorCodes.SIGN_VALIDATE_ERROR, "签名验证失败", null);
				logger.error("签名验证失败！ret={}", ret);
				return ret;
			}
			if(params.get("uid") == null && params.get("orderNo") == null){
				ret = JsonUtil.toJson(ErrorCodes.CORE_PARAM_UNLAWFUL, "参数不合法", null);
				logger.error("参数不合法！ret={}", ret);
				return ret;
			}

			long uid = Long.parseLong(params.get("uid").toString());
			String orderNo = params.get("orderNo").toString();

			ret = financeBillService.queryAppFinancesByOrder(uid, orderNo);

		}catch (Exception e){
			logger.error("finance order list is error {}", e);
		}
		logger.info("finance order list ret  is {}",ret);
		return ret;
	}

	/**
	 * 根据用户id获取分期列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = {"/list","/list/"}, method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@LoginRequired
	public @ResponseBody String financeList(HttpServletRequest request,HttpServletResponse response){
		String ret = "";
		try {
			Map<String, Object> params = JsonUtil.readMapFromReq(request);
			if(!SignHelper.validateSign(params)){
				ret = JsonUtil.toJson(ErrorCodes.SIGN_VALIDATE_ERROR, "签名验证失败", null);
				logger.error("签名验证失败！ret={}", ret);
				return ret;
			}
			if(params.get("uid") == null){
				ret = JsonUtil.toJson(ErrorCodes.CORE_PARAM_UNLAWFUL, "参数不合法", null);
				logger.error("参数不合法！ret={}", ret);
				return ret;
			}

			long uid = Long.parseLong(params.get("uid").toString());
			/**
			 * 1、获得用户所有分期订单
			 * 2、去重得到所有orderNo
			 * 3、通过orderNo获得应有的数据
			 * 查找用户所有订单
			 */
			List<FinanceBill> list = financeBillService.findAllBills(uid);
			if(list == null){
				ret = JsonUtil.toJson(0, null, null);
				logger.debug("user:{} 没有分期订单！ have not finance ",uid);
				return ret;
			}

			Set<String> orderNoSet = new HashSet<String>();
			for (FinanceBill finance : list) {
				orderNoSet.add(finance.getOrderNo());
			}

			List<OrderInfo2App> realresult = new ArrayList<OrderInfo2App>();


			for (String orderNo : orderNoSet) {	//循环订单号
				OrderInfo orderInfo = orderService.findByOrderNo(orderNo);
				OrderInfo2App data = orderService.makeAppOrderByOrder(orderInfo);
				logger.info("OrderInfo2App at FinanceController :{}",data);
				data.setFinanceState(-1);
				data.setBillNo("xiajibaBillNo");

				for (FinanceBill finance : list) {
					if(finance.getOrderNo().equals(orderNo)){//循环该用户所有和正在循环的订单号相同的 分期订单
						logger.info("finance in FinanceVontroller : ",finance.toString());
						data.setBillNo(finance.getBillNo());
						data.setEveryMonthPayTime(finance.getDueAt());
						data.setMonth_period(finance.getNewBalance());
						if(finance.getStatus() == 1){
							//分期状态设为1 , 当前期数为第一个为待付款的账单的current
							data.setFinanceState(1);
							data.setCur_period(finance.getCurPeriod());
							break;
						}
						if(finance.getStatus() == 2){
							//分期状态设为2 , 当前期数为第一个为待付款的账单的current
							data.setFinanceState(2);
							data.setCur_period(finance.getCurPeriod());
							break;
						}
					}
				}
				realresult.add(data);
			}

			logger.info("realresult:{}",realresult);
			ret = JsonUtil.toJson(0 , null, realresult);

		}catch (Exception e) {
			logger.error("Exception in FinanceController.userFinanceState = {}",e);
			ret = JsonUtil.toJson(ErrorCodes.CORE_ERROR, "系统异常", null);
		}
		return ret;
	}

	
	/**
	 * 计算利率，根据amount数字计算各个分期的还款额
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value={"/calculate","/calculate/"},method={RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody String calculate(HttpServletRequest request,HttpServletResponse response){
		String ret = "";
		try {
			Map<String, Object> params = JsonUtil.readMapFromReq(request);
			if(params.get("amount")==null){
				return JsonUtil.toJson(ErrorCodes.CORE_PARAM_NULL, "缺少amount参数", null);
			}
			BigDecimal amount = BigDecimal.valueOf(Double.parseDouble(String.valueOf(params.get("amount"))));
			amount = amount.divide(new BigDecimal("0.9"), 2,BigDecimal.ROUND_HALF_EVEN);
			
			ret =  JsonUtil.successResultJson(amount);
		} catch (Exception e) {
			return JsonUtil.toJson(7888, "系统错误", null);
		}
		
		return ret;
	}

	public static void main(String[] args) {
		
	}


}









