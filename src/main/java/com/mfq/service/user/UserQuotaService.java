package com.mfq.service.user;

import java.math.BigDecimal;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.mfq.bean.user.Gender;
import com.mfq.bean.user.User;
import com.mfq.bean.user.UserQuota;
import com.mfq.cache.RedisCache;
import com.mfq.constants.AuthStatus;
import com.mfq.constants.Constants;
import com.mfq.constants.ErrorCodes;
import com.mfq.constants.Grade;
import com.mfq.dao.UserQuotaMapper;
import com.mfq.dataservice.context.AppContext;
import com.mfq.net.tongdun.FraudApiInvoker;
import com.mfq.service.sms.SMSService;
import com.mfq.utils.HttpUtil;
import com.mfq.utils.IDCardUtil;
import com.mfq.utils.JsonUtil;
import com.mfq.utils.VerifyUtils;

@Service
public class UserQuotaService {

    private static final Logger logger = LoggerFactory
            .getLogger(UserQuotaService.class);

    @Resource
    UserQuotaMapper mapper;
    @Resource
    UserService userService;
    @Resource
    SMSService smsService;
    @Resource
    FraudApiInvoker fraudApiInvoker;
    
    private static RedisCache cache = new RedisCache();

    /**
     * 
     * @param uid
     * @param num
     * 可为正数或者负数 正数的话就是余额付款 负数的话就是充值
     * @return
     * @throws Exception
     */
    public long updateUserBalance(long uid, BigDecimal num) throws Exception {
        UserQuota quota = queryUserQuota(uid);
        if (quota == null || num == null) {
            logger.error("Notice: quota={}, operate num={}", quota, num);
            throw new Exception("用户余额配置信息不存在");
        }
        
        
        if(quota.getBalance().subtract(num).compareTo(BigDecimal.valueOf(0))<0){
        	throw new Exception("账户余额少于支付金额");
        }
        
        
        BigDecimal newBalance = quota.getBalance().subtract(num);
        
        
        long count = mapper.updateUserBalance(uid, newBalance);
        logger.info("更新用户{}余额度由{}到{}", uid, quota.getBalance(),
                newBalance);
        
        return count;
    }
    

    /**
     * 更新赠送余额
     * 
     * @param uid
     * @param num
     * @return
     * @throws Exception
     */
    public long updateUserPresent(long uid, BigDecimal num) throws Exception {
        UserQuota quota = queryUserQuota(uid);
        if (quota == null || num == null) {
            logger.error("Notice: quota={}, operate num={}", quota, num);
            throw new Exception("用户余额配置信息不存在");
        }
        // 假如是减掉金额，需要判断原始余额与减掉金额的大小
        if (quota.getPresent().add(num).compareTo(new BigDecimal(0)) < 0) {
            throw new Exception("用于余额不足以支付购物抵扣额度");
        }
        long l = mapper.updateUserPresent(uid, num, quota.getPresent());
        logger.info("更新用户{}赠送金额由{}到{}", uid, quota.getPresent(),
                quota.getPresent().add(num));
        return l;
    }
    
    @Transactional
    public long updateUserQuota(long uid, BigDecimal usedQuota)
            throws Exception {
        UserQuota quota = queryUserQuota(uid);
        if (quota == null || usedQuota == null) {
            logger.error("Notice: quota={}, operate num={}", quota, usedQuota);
            throw new Exception("用户额度配置信息不存在");
        }
        if (quota.getQuotaLeft().compareTo(usedQuota) < 0) {
            logger.error("Notice: Quota not enough, quotaLeft={}, willUse={}",
                    quota.getQuotaLeft(), usedQuota);
            throw new Exception("用户可用额度不足！");
        }
        logger.info("用户扣除前的剩余额度为：{}",quota.getQuotaLeft());
        quota.setQuotaLeft(usedQuota);
        logger.info("用户扣除后的剩余额度为：{}",quota.getQuotaLeft());
        return mapper.updateUserQuota(quota);
    }
    
    public static void main(String[] args) throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring/spring.xml");
		UserQuotaService service = ac.getBean(UserQuotaService.class);
		long count = service.updateUserQuota(2798, BigDecimal.valueOf(-10000));
		System.out.println(count);
        System.out.println("sadfasdfsadfasdfs");
    }

    /**
     * 申请面签
     * 
     * @param uid
     *            用户id
     * @param realname
     * @param idCard
     * @param school
     * @param contact
     *            联系方式
     * @return
     * @throws Exception 
     */
    public String applyInterView(Long uid, String realname, String idCard,
            String school, String contact) throws Exception {
        logger.info("user applyInterview |{}|{}|{}|{}|{}", uid, realname,
                idCard, school, contact);

        int code = ErrorCodes.SUCCESS;
        String msg = "申请面签成功";

        if (StringUtils.isNotBlank(realname)
                && !VerifyUtils.verifyRealName(realname)) {
            code = 1001;
            msg = "用户真实姓名格式错误";
        } else if (StringUtils.isBlank(idCard)) {
            code = 1001;
            msg = "身份证格式错误";
        } else if (StringUtils.isBlank(school)) {
            code = 1001;
            msg = "学校格式错误";
        } else if (StringUtils.isNotBlank(contact)
                && !VerifyUtils.verifyMobile(contact)) {
            code = 1001;
            msg = "联系方式格式错误";
        }
        if (code != ErrorCodes.SUCCESS) {
            return JsonUtil.toJson(code, msg, null);
        }
        
        int authStatus = AuthStatus.TOINTERVIEW.getId();
        JSONObject ret = CheckIDCard(realname, idCard);
        if(ret.getInteger("code") == 1){
        	authStatus = 1;
        }else{
        	return JsonUtil.toJson(1002, "身份证校验错误！！", null);
        }
        
        UserQuota quota = queryUserQuota(uid);
        quota.setRealname(realname);
        quota.setIdCard(idCard);
        quota.setSchool(school);
        quota.setContact(contact);
        quota.setAuthStatus(authStatus);
        long result = mapper.updateUserQuota(quota);
        Map<String, Object> data = Maps.newHashMap();
        data.put("num", result);
        if (result > 0) {
            data.put("status", quota.getAuthStatus());
            
//            String [] params = {realname ,contact};
//    		smsService.sendInterViewHint(params);
    		
            return JsonUtil.successResultJson(data);
        } else {
            return JsonUtil.toJson(ErrorCodes.FAIL, "认证失败", data);
        }
    }
    
    public JSONObject CheckIDCard(String name, String idcard) {
		String url = Constants.IDCARD_CHECK.replace("APPKEY", Constants.IDCARD_APPKEY).replace("NAME", name)
				.replace("IDCARD", idcard);
		String result = HttpUtil.get(url, false);
		return JSONObject.parseObject(result);
	}

    /**
     * 面签
     * 
     * @param uid
     * @param grade
     * @param classes
     * @param remark
     * @param auth_status
     * @return
     * @throws Exception
     */
    public String auditUserQuota(Long uid, Grade grade, String classes,
            String remark, int auth_status) throws Exception {
        AuthStatus authStatus = AuthStatus.fromId(auth_status);
        if (StringUtils.isBlank(classes)) {
            classes = "";
        } else if (StringUtils.isBlank(remark)) {
            return JsonUtil.toJson(1001, "备注不能为空", null);
        }
        UserQuota quota = queryUserQuota(uid);
        quota.setSchoolRemark(remark);
        quota.setGrade(grade);
        quota.setClasses(classes);
        quota.setAuthStatus(authStatus.getId());
        long result = mapper.updateUserQuota(quota);
        Map<String, Object> data = Maps.newHashMap();
        data.put("num", result);
        if (result > 0) {
            data.put("status", quota.getAuthStatus());
            return JsonUtil.successResultJson(data);
        } else {
            return JsonUtil.toJson(ErrorCodes.FAIL, "验签失败", data);
        }
    }

    public String updateUserInfo(Long uid, String realname, String idCard,
            String school, String contact, Grade grade, String classes,
            String remark) throws Exception {
        logger.info("user applyInterview |{}|{}|{}|{}|{}|{}|{}|{}", uid,
                realname, idCard, school, contact, grade, classes, remark);

        int code = ErrorCodes.SUCCESS;
        String msg = "面签信息修改成功";
        
        if (StringUtils.isNotBlank(realname)
                && !VerifyUtils.verifyRealName(realname)) {
            code = 1001;
            msg = "用户真实姓名格式错误";
        } else if (StringUtils.isBlank(idCard)) {
            code = 1001;
            msg = "身份证格式错误";
        } else if (StringUtils.isBlank(school)) {
            code = 1001;
            msg = "学校格式错误";
        } else if (StringUtils.isNotBlank(contact)
                && !VerifyUtils.verifyMobile(contact)) {
            code = 1001;
            msg = "联系方式格式错误";
        } else if (StringUtils.isBlank(classes)) {
            classes = "";
        } else if (StringUtils.isBlank(remark)) {
            remark = "";
        }
        if (code != ErrorCodes.SUCCESS) {
            return JsonUtil.toJson(code, msg, null);
        }
        UserQuota quota = queryUserQuota(uid);
        quota.setRealname(realname);
        quota.setIdCard(idCard);
        quota.setSchool(school);
        quota.setContact(contact);
        quota.setGrade(grade);
        quota.setClasses(classes);
        quota.setSchoolRemark(remark);
        quota.setAuthStatus(AuthStatus.TOINTERVIEW.getId());
        long result = mapper.updateUserQuota(quota);
        Map<String, Object> data = Maps.newHashMap();
        data.put("num", result);
        if (result > 0) {
            data.put("status", quota.getAuthStatus());
            return JsonUtil.successResultJson(data);
        } else {
            return JsonUtil.toJson(ErrorCodes.FAIL, "认证失败", data);
        }
    }

    public long updateUserWish(long uid, int wish) {
        return mapper.updateUserWish(uid, wish);
    }

    public long insertUserQuota(UserQuota quota) {
        return mapper.insertUserQuota(quota);
    }

    public UserQuota queryUserQuota(long userId) {
        return mapper.queryUserQuota(userId);
    }

	public String updateUserInfoTask(Long uid, String realname, String idCard, String school, String contact,
			Grade grade, String classes, String remark) {
		JSONObject data = new JSONObject();
		data.put("uid", uid);
		data.put("realname", realname);
		data.put("idcard", idCard);
		data.put("contact", contact);
		data.put("grade", grade.getId());
		data.put("classes", classes);
		data.put("remark", remark);
		
		long r = cache.lpush("tt", data.toJSONString());
		
//		IDCardCheckService
//		idCardCheckService.extur();
		return null;
	}

	public boolean updateIDPic(Long userId, String pic, int t) {
		
		return mapper.updateIdPic(userId, pic, t);
	}

	public String applyAdultInterView(Long uid, String realname, String idCard, int gender, String origin, String location , String mobile_type, String blackbox) throws Exception {
        logger.info("user applyInterview |{}|{}|{}|{}|{}", uid, realname,
                idCard, gender, origin, location);

        int code = ErrorCodes.SUCCESS;
        String msg = "身份验证成功";
        Gender g = Gender.fromValue(gender);
        if (StringUtils.isNotBlank(realname)
                && !VerifyUtils.verifyRealName(realname)) {
            code = 1001;
            msg = "用户真实姓名格式错误";
        } else if (StringUtils.isBlank(idCard)) {
            code = 1001;
            msg = "身份证格式错误";
        } else if (g == null) {
            code = 1001;
            msg = "用户性别错误";
        } else if (StringUtils.isBlank(origin)) {
            code = 1001;
            msg = "籍贯式格式错误";
        }else if (StringUtils.isBlank(location)) {
            code = 1001;
            msg = "现居地错误";
        }
        if (code != ErrorCodes.SUCCESS) {
            return JsonUtil.toJson(code, msg, null);
        }
        
        int authStatus = AuthStatus.UNREAL.getId();
        
        
        //校验身份证是否正确
        String vStr = IDCardUtil.IDCardValidate(idCard);
        if(!"".equals(vStr)){
        	return JsonUtil.toJson(1002, vStr, null);
        }
        JSONObject ret = CheckIDCard(realname, idCard);
        int retcode = ret.getInteger("code");
        if(retcode == 1){
        	authStatus = 0;
        }else if(retcode == 2){
        	return JsonUtil.toJson(1002, "身份证校验不一致", null);
        }else if(retcode == 3){
        	return JsonUtil.toJson(1003, "无此身份证号", null);
        }else if(retcode == 12){
	  		smsService.sendSysSMS("系统消息：身份验证接口，余额不足。");
        	return JsonUtil.toJson(1004, "余额不足", null);
        }else{
        	return JsonUtil.toJson(1005, "身份证校验错误", null);
        }
        
        UserQuota quota = queryUserQuota(uid);
        if(quota == null){
        	return JsonUtil.toJson(1006, "错误", null);
        }
        //实名认证通过后，同盾验证。如果不通过，则返回错误信息。错误信息是不通过的原因，通常为命中了规则。
        User user = userService.queryUser(uid);
        String ip_address = AppContext.getIp();
        String account_email = "";
        String account_phone = user.getMobile();
        String type = mobile_type;
        Map<String,Object> fraud = fraudApiInvoker.fraudCertify(realname, idCard, account_email, account_phone, ip_address, blackbox, type);
		if(fraud !=null && fraud.size()>0 && fraud.get("decision").toString().toLowerCase().equals("reject")){
			logger.info("tong dun {}",fraud);
        	return JsonUtil.toJson(ErrorCodes.FRAUD_ERROR, fraud.get("msg").toString(), null);
        }
        //同盾验证结束
        long result = mapper.updateUserQuotaForAdult(realname, idCard, g, origin, authStatus, location, uid);
        Map<String, Object> data = Maps.newHashMap();
        data.put("num", result);
        
        if (result > 0) {
            data.put("status", quota.getAuthStatus());

            return JsonUtil.successResultJson(data);
        } else {
            return JsonUtil.toJson(ErrorCodes.FAIL, "认证失败", data);
        }
	}


    private void sendNotificationSms(String realname, String mobile) throws Exception {
        //短信通知

        String [] params = {realname ,mobile};
        smsService.sendInterViewHint(params);
    }

	/**
	 * 保存白领信息
	 * @param uid  用户ID
	 * @param company  公司
	 * @param position 职位
	 * @param department 部门
	 * @param salary 年收入
	 * @param social_insurance 社保号
	 * @param work_years 工作年限
	 * @return
	 */
	public String saveAdultInfo(Long uid, String company, String position, String department, String salary,
			String social_insurance, String work_years) throws Exception {
		User user = userService.queryUser(uid);
		if(user == null || user.getUid() <1){
			return JsonUtil.toJson(1001, "用户不存在", null);
		}
		int result = mapper.updateAdultWorkInfo(uid, company, position, department, salary, social_insurance, work_years);

        if(result > 0) {
            UserQuota quota = queryUserQuota(user.getUid());
            //消息通知
            sendNotificationSms(quota.getRealname(), user.getMobile());
        }
        return JsonUtil.successResultJson(result);
		
	}

    /**
     * 保存学生信息
     * @param uid
     * @param student_id
     * @param school
     * @param school_location
     * @param grade
     * @param school_level
     * @param faculty
     * @param speciality
     * @param scholastic_years
     * @return
     */
	public String saveStudentInfo(Long uid, String student_id, String school, String school_location, long grade,
			String school_level, String faculty, String speciality, int scholastic_years) throws Exception {
		User user = userService.queryUser(uid);
		if(user == null || user.getUid() <1){
			return JsonUtil.toJson(1001, "用户不存在", null);
		}
		int result = mapper.updateStudentWorkInfo(uid, student_id, school, school_location, grade, school_level, faculty, speciality,
				scholastic_years);
		if(result>0){
            UserQuota quota = queryUserQuota(user.getUid());
            //消息通知
            sendNotificationSms(quota.getRealname(), user.getMobile());
        }
		return JsonUtil.successResultJson(result);
	}
	
	public int updateAuthStatusByUid(long uid ,int authStatus){
		return mapper.updateAuthStatusByUid(uid, authStatus);
	}
	
	
    

}
