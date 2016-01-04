package com.mfq.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.mfq.bean.Hospital;
import com.mfq.bean.OrderInfo;
import com.mfq.bean.PolicyInfo;
import com.mfq.bean.PolicyInfoExample;
import com.mfq.bean.PolicyInsure;
import com.mfq.bean.PolicyInsureExample;
import com.mfq.bean.Product;
import com.mfq.bean.app.PolicyListForApp;
import com.mfq.bean.user.User;
import com.mfq.bean.user.UserQuota;
import com.mfq.constants.PolicyStatus;
import com.mfq.dao.PolicyInfoMapper;
import com.mfq.dao.PolicyInsureMapper;
import com.mfq.net.wukongbao.WukongBaoClient;
import com.mfq.net.wukongbao.pojo.WkbConstants;
import com.mfq.net.wukongbao.pojo.WukongError;
import com.mfq.net.wukongbao.pojo.WukongInsureStatus;
import com.mfq.service.user.UserQuotaService;
import com.mfq.utils.DateUtil;
import com.mfq.utils.JsonUtil;

@Service
public class PolicyService {
    
	private static final Logger logger = LoggerFactory
            .getLogger(PolicyService.class);
	
    @Resource
    PolicyInfoMapper policyInfoMapper;   //保单信息
    
    @Resource
    PolicyInsureMapper policyInsureMapper;  //保单
    
    @Resource
    HospitalService hospitalService;
    
    @Resource
    OrderService orderService;
    
    @Resource
    ProductService productService;
    
    @Resource
    UserQuotaService quotaService;
    
    @Resource
    PolicyService policyService;
    

	@Resource
	WukongBaoClient wukongBaoClient;

	final PolicyInfoExample infoExample = new PolicyInfoExample();
	final PolicyInsureExample insureExample = new PolicyInsureExample();

	public List<PolicyListForApp> findByUid(long uid) {
		PolicyInsureExample example = new PolicyInsureExample();
		example.createCriteria().andUidEqualTo(uid);
		List<PolicyInsure> list = policyInsureMapper.selectByExample(example);
		
		return convert2AppList(list);
	}
	
	private List<PolicyListForApp> convert2AppList(List<PolicyInsure> data){
		List<PolicyListForApp> list = Lists.newArrayList();
		for(PolicyInsure insure:data){
			PolicyInfoExample example = new PolicyInfoExample();
			example.createCriteria().andOrderNoEqualTo(insure.getOrderNo());
			List<PolicyInfo> infos = policyInfoMapper.selectByExample(example);
			PolicyInfo info = new PolicyInfo();
			if(infos.size() > 0)
				info = infos.get(0);
			PolicyListForApp app = new PolicyListForApp(insure.getId(), insure.getProductName(), insure.getOrderNo(),
					insure.getHospitalName(), insure.getCreatedAt(), info.getPolicyBdate(), info.getPolicyEdate(), info.getPolicyStatus(),"");
			list.add(app);		
		}
		return list;
	}
	
	private PolicyListForApp convert2App(PolicyInsure insure){
			
			if(insure == null){
				return null;
			}
			PolicyInfoExample example = new PolicyInfoExample();
			example.createCriteria().andOrderNoEqualTo(insure.getOrderNo());
			List<PolicyInfo> infos = policyInfoMapper.selectByExample(example);
			PolicyInfo info = new PolicyInfo();
			if(infos.size() > 0)
				info = infos.get(0);
			PolicyListForApp app = new PolicyListForApp(insure.getId(), insure.getProductName(), insure.getOrderNo(),
					insure.getHospitalName(), insure.getCreatedAt(), info.getPolicyBdate(), info.getPolicyEdate(), info.getPolicyStatus(),"");
		return app;
	}

	/**
	 * 价格转换为int 分为单位
	 * @param p
	 * @return
	 */
	private long BigDecimalToInt(BigDecimal p){
		BigDecimal t =p.multiply(BigDecimal.valueOf(100));
		return t.intValue();
	}
	/**
	 * 投保
     * @return
     */
	@Transactional
	public String insure(String orderNo) throws Exception{
		String ret="";
		try{
			
			logger.info("wukongbao orderNo={}", orderNo);
			PolicyInsure insure = findPolicyInsureByOrder(orderNo);
			PolicyInfo info = findPolicyInfoByOrder(orderNo);
			
			logger.info("insure ={} /n  info = {} /n", insure, info);
			
			OrderInfo orderInfo = orderService.findByOrderNo(orderNo);
			Product product = productService.findById(orderInfo.getPid());
			Hospital hospital = hospitalService.findById(product.getHospitalId());
			
			UserQuota quota = quotaService.queryUserQuota(orderInfo.getUid());
			//投保
			Map<String, Object> map = wukongBaoClient.getInsureRequest(orderInfo, hospital, product.getName() , quota.getUid(), 0l);
			String response = wukongBaoClient.WukongRequest(map, insure);
			System.out.println("response ="+response);
			insureExample.createCriteria().andOrderNoEqualTo(orderNo);
			int is = policyInsureMapper.updateByExample(insure, insureExample);
			if(is < 1){
				throw new Exception("更新 policyInsure is error");
			}
			
			ret = doPolicyInsureResponse(response, info, insure);
			
		}catch(Exception e){
			logger.error("wukong bao  is error ", e);
			ret = JsonUtil.toJson(1004, "WU KONG BAO IS ERROR", null);
			throw e;
		}
		return ret;
	}
	
	/**
	 * 默认policyInfo
	 * @param orderNo
	 * @param phone
	 * @return
	 */
	private PolicyInfo defaultPolicyInfo(String orderNo, String phone){
		PolicyInfo info = new PolicyInfo();
		info.setProductNo(WkbConstants.INSURE_PRODUCT);
		info.setPhone(phone);
		info.setOrderNo(orderNo);
		info.setPremium(WkbConstants.PREMIUM);
		info.setPolicyStatus(PolicyStatus.AUDITING);
		
		return info;
	}

	/**
	 * 更新policyStatus
	 * @param request
	 * @return
	 */
	public  String updatePolicyStatus(HttpServletRequest request){
		String ret ="";
		try{
			String response = request.getParameter("js");
			
	
//			ret = doPolicyInsureResponse(response, defaultPolicyInfo());
		}catch (Exception e){
			logger.error("update policyFor success is error{}", e);
		}

		return ret;
	}
	
	
	//处理回调 保单
	public String doPolicyInsureResponse(String response, PolicyInfo policyInfo, PolicyInsure insure) throws Exception {
		
		JSONObject infos = JSONObject.parseObject(response);
		
		String msgCode= infos.getString("msgCode");
		if(!"SUCCESS".equals(msgCode)){
			throw new Exception("悟空保 投保错误。。 msg ： "+infos.toJSONString());
		}
		String msgInfo = infos.getString("bizContent");
		JSONObject info = JSONObject.parseObject(msgInfo);
		saveOrUpdatePolicyInfo(info, policyInfo);
		if(policyInfo == null){
			logger.info("policyInfo update is error ...");
			throw new Exception("悟空保 投保错误。。 msg ： "+infos.toJSONString());
		}

		//更新policyInsure
//		PolicyInsure insure = findPolicyInsureByOrder(policyInfo.getOrderNo());
		if(insure== null){
			//。。。。
			throw new Exception("悟空保 投保错误。。 msg ： "+infos.toJSONString());
		}
		insure.setReponseStr(msgInfo);
		insure.setReponseTime(new Date());

		if(WukongError.SUCCESS.name().equals(infos.getString("msgCode"))){

			insure.setStatus(WukongInsureStatus.INSURE_SUCCESS.getId());
		}else{
			insure.setStatus(WukongInsureStatus.INVALID_FAILD.getId());
		}
		insureExample.or().andOrderNoEqualTo(insure.getOrderNo());
		long t= policyInsureMapper.updateByExample(insure, insureExample);
		if(t>0){
			return JsonUtil.successResultJson();
		}
		return JsonUtil.toJson(1001, "insure is null", null);
	}

	private PolicyInsure findPolicyInsureByOrder(String orderNo){

		insureExample.createCriteria().andOrderNoEqualTo(orderNo);
		List<PolicyInsure> policyInsures = policyInsureMapper.selectByExample(insureExample);
		if(policyInsures.size() > 0){
			return policyInsures.get(0);
		}
		return null;
	}

	public PolicyInfo findPolicyInfoByOrder(String orderNo){

		infoExample.createCriteria().andOrderNoEqualTo(orderNo);
		List<PolicyInfo> policyInfos = policyInfoMapper.selectByExample(infoExample);
		if(policyInfos.size() > 0){
			return policyInfos.get(0);
		}
		return null;
	}

	
	//保存回调数据
	private PolicyInfo saveOrUpdatePolicyInfo(JSONObject info, PolicyInfo policyInfo) throws Exception{
		if(info==null){
			return null;
		}
		
		String orderNo = info.getString("channelOrderNo");
		if(StringUtils.isBlank(orderNo))
			return null;
		
		if(policyInfo == null)
			policyInfo = new PolicyInfo();

		String pinfo = info.getString("policyContent");
		JSONObject pInfo = (JSONObject)JSONArray.parseArray(pinfo).get(0);
		if(StringUtils.isNotEmpty(pInfo.getString("insuranceCode"))){
			policyInfo.setInsuranceCode(pInfo.getString("insuranceCode"));
		}
		if(StringUtils.isNotEmpty(pInfo.getString("policyHolder"))){
			policyInfo.setPolicyHolder(pInfo.getString("policyHolder"));
		}
		if(StringUtils.isNotEmpty(pInfo.getString("phone"))) {
			policyInfo.setPhone(pInfo.getString("phone"));
		}
		if(StringUtils.isNotEmpty(pInfo.getString("policyNo"))) {
			policyInfo.setPolicyNo(pInfo.getString("policyNo"));
		}
		if(pInfo.getInteger("premium")!=null) {
			policyInfo.setPremium(pInfo.getBigDecimal("premium").divide(BigDecimal.valueOf(100)));
		}
		if(pInfo.getInteger("sumInsured")!=null){
			policyInfo.setSumInsured(pInfo.getBigDecimal("sumInsured").divide(BigDecimal.valueOf(100)));  //已分为单位
		}
		if(StringUtils.isNotEmpty(pInfo.getString("policyBeginDate"))){
			Date btime = DateUtil.convertYYYYMMDDHHMMSS(pInfo.getString("policyBeginDate"));
			policyInfo.setPolicyBdate(btime);
		}
		if(StringUtils.isNotEmpty(pInfo.getString("policyEndDate"))){
			Date etime = DateUtil.convertYYYYMMDDHHMMSS(pInfo.getString("policyEndDate"));
			policyInfo.setPolicyEdate(etime);
		}
		if(StringUtils.isNotEmpty(pInfo.getString("policyStatus"))){
			int t = pInfo.getInteger("policyStatus");
			policyInfo.setPolicyStatus(PolicyStatus.fromId(t));
		}
		
		infoExample.createCriteria().andIdEqualTo(policyInfo.getId());
		int r = policyInfoMapper.updateByExample(policyInfo, infoExample);
		
		if(r>0)
			return policyInfo;
		return null;
	}

	//申请理赔
	public String complaintPolicy(String order_no) {
		PolicyInfo info =findPolicyInfoByOrder(order_no);
		if(info == null){
			return JsonUtil.toJson(1003, "保单不存在", null);
		}
		
		String status = WukongInsureStatus.COMPLAINT.getCode();
		
		info.setPolicyStatus(PolicyStatus.INSUREING);
		infoExample.or().andOrderNoEqualTo(info.getOrderNo());
		int result =policyInfoMapper.updateByExample(info, infoExample);
		
		if(result > 0 ){
			return JsonUtil.successResultJson();
		}else{
			return JsonUtil.toJson(1004, "错误", result);
		}
		
		
	}

	@Transactional
	public boolean saveInsure(OrderInfo orderInfo, UserQuota quota, User user, Product product) {
		//投保结果
		PolicyInfo info = new PolicyInfo();
		
		info.setProductNo(WkbConstants.INSURE_PRODUCT);
		info.setOrderNo(orderInfo.getOrderNo());
		info.setPremium(WkbConstants.PREMIUM);
		info.setSumInsured(BigDecimal.valueOf(0));  //保额
		info.setPhone(user.getMobile());
		
		
		
		//投保单
		PolicyInsure insure = new PolicyInsure();
		insure.setOrderNo(orderInfo.getOrderNo());
		insure.setNurseId(0l);
		insure.setPolicyPid(WkbConstants.INSURE_PRODUCT);
		insure.setProductName(product.getName());
		insure.setOrderSum(BigDecimalToInt(orderInfo.getPrice()));
		
		Hospital hospital = hospitalService.findById(product.getHospitalId());
		insure.setHospitalName(hospital.getName());
		insure.setUid(orderInfo.getUid());
		insure.setPolicyId(0l);
		insure.setReponseStr("");
		insure.setReponseStr("");
		insure.setStatus(1);
		insure.setCreatedAt(new Date());
		insure.setUpdatedAt(new Date());
		insure.setServiceStime(orderInfo.getServiceStartTime());
		
		if(orderInfo.getPayType() == 0){
			insure.setOrderPayType("全款");
		}else if(orderInfo.getPayType() == 2){
			insure.setOrderPayType("分期");
		}
		
		int l = policyInsureMapper.insert(insure);
		int r = policyInfoMapper.insert(info);
		return true;
	}

	public PolicyListForApp findByOrder(long uid, String orderNo) {
		PolicyInsure insure = policyService.findPolicyInsureByOrder(orderNo);
		return convert2App(insure);
	}
	
	
	public static void main(String[] args)  {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/spring.xml"); 
		PolicyService client = (PolicyService)context.getBean("policyService");
		
		try {
			client.insure("mn201512231114554417000d3");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
