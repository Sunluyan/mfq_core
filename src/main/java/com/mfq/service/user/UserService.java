package com.mfq.service.user;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import com.mfq.bean.InviteRecord;
import com.mfq.bean.InviteRecordExample;
import com.mfq.bean.UsersDetail;
import com.mfq.bean.app.UsersDetail2App;
import com.mfq.bean.coupon.Coupon;
import com.mfq.bean.coupon.CouponBatchInfo;
import com.mfq.dao.InviteRecordMapper;
import com.mfq.dao.UsersDetailMapper;
import com.mfq.service.CouponService;
import com.mfq.utils.UserUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.messaging.simp.user.UserSessionRegistry;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;
import com.mfq.bean.app.UserProfile2App;
import com.mfq.bean.user.Gender;
import com.mfq.bean.user.SignIndex;
import com.mfq.bean.user.Status;
import com.mfq.bean.user.User;
import com.mfq.bean.user.UserExtend;
import com.mfq.bean.user.UserQuota;
import com.mfq.constants.AuthStatus;
import com.mfq.constants.Career;
import com.mfq.constants.ErrorCodes;
import com.mfq.constants.Grade;
import com.mfq.dao.UserMapper;
import com.mfq.helper.UserHelper;
import com.mfq.utils.JsonUtil;
import com.mfq.utils.MD5Util;
import com.mfq.utils.VerifyUtils;

@Service
public class UserService {

    @Resource
    UserLoginService userLoginService;
    @Resource
    UserQuotaService userQuotaService;
    @Resource
    UserExtendService userExtendService;
    @Resource
    UserMapper mapper;
    @Resource
    InviteRecordMapper inviteRecordMapper;
    @Resource
    CouponService couponService;
    @Resource
    UsersDetailMapper usersDetailMapper;


    private final long PERSENTCOUPON = 7;
	private static final Logger logger = LoggerFactory
            .getLogger(UserService.class);

    /**
     * 事务管理，用于关联用户创建及初始阶段额度授予
     * @param status
     * @param nick
     * @param email
     * @param mobile
     * @param indexs
     * @return
     */
    @Transactional
    public long createUser(Status status, String nick,String realName, String email,
            String mobile, int job, String invite_code, SignIndex... indexs) {
        if (StringUtils.isBlank(email) && StringUtils.isBlank(mobile)) {
            throw new RuntimeException("email and mobile both are empty");
        }
        long userId = 0;
        User user = new User();
        user.setUid(userId);
        user.setStatus(status);
        user.setNick(nick);
        user.setEmail(email);
        user.setMobile(mobile);
        user.setLocation("");
        user.setLocationId(0);
        Career career = Career.fromId(job);
        user.setCareer(career == null? Career.ELSE.getId() : career.getId());
        // set default value for balnk attribute
        UserHelper.formatDefaultUser(user);
        if (indexs == null) {
            indexs = new SignIndex[0];
        }
        UserHelper.setSign(user, indexs);
        long num = mapper.insertUser(user);
        if(num > 0){
            UserQuota quota = buildDefaultQuota(user.getUid(), realName);
            userQuotaService.insertUserQuota(quota);
            logger.info("insert extend {}|{}", user.getUid(),invite_code);
            UserExtend extend = new UserExtend(user.getUid(), "");
            extend.setInviteCode(UserUtils.makeInviteCode(user.getUid()));
            userExtendService.insertUserExtend(extend);
            userId = user.getUid();

        }
        return userId;
    }

    public UserProfile2App queryUserProfile2App(long uid){
        User user = queryUser(uid);
        if(user != null && user.getUid() > 0){
            UserQuota quota = userQuotaService.queryUserQuota(user.getUid());
            if(quota != null){
                UserProfile2App result = new UserProfile2App(user, quota);
                UsersDetail usersDetail = usersDetailMapper.selectByPrimaryKey(user.getUid());

                result.setDesc(usersDetail!=null?usersDetail.getDescription():"");
                result.setPercent(detail(user.getUid()).getpercent());

                return result;
            }
        }

        return new UserProfile2App();
    }

    
    public User queryUser(long uid) {
        User user = mapper.queryUser(uid);
        if(user == null){
            user = new User();
        }
        return user;
    }

    public User queryUserByEmail(String email) {
        User user = mapper.queryUserByEmail(email);
        if(user == null){
            user = new User();
        }
        return user;
    }

    public User queryUserByMobile(String mobile) {
        User user = mapper.queryUserByMobile(mobile);
        if(user == null){
            user = new User();
        }
        return user;
    }

    public boolean updateStatus(long uid, Status status) {
        return mapper.updateStatus(uid, status);
    }

    public boolean updateSigns(long uid, boolean set, SignIndex... indexs) {
        int setSign = 0;
        for (SignIndex index : indexs) {
            setSign |= 1 << index.getValue();
        }
        if(set){
            return mapper.updateSetSigns(uid, setSign);
        }else{
            return mapper.updateUnsetSigns(uid, setSign);
        }
    }

    public boolean updateMailStatus(long uid, boolean actived) {
        User user = queryUser(uid);
        if (UserHelper.isSetSign(user, SignIndex.MailStatus) && actived) {
            return true;
        }
        if (actived) {
            UserHelper.setSign(user, SignIndex.MailStatus);
        } else {
            UserHelper.unsetSign(user, SignIndex.MailStatus);
        }
        int sign = user.getSign();
        boolean re = mapper.updateSign(uid, sign);
        if (re && actived && user.getStatus() == Status.INACTIVE) {
            userLoginService.updateUsersLoginActivedTime(uid);
            return updateStatus(uid, Status.NORMAL);
        }
        return true;
    }

    public boolean updateSimpleProfile(long uid, String nick, int gender) {
        return mapper.updateSimpleProfile(uid, nick, gender);
    }

    public boolean updateEmail(long uid, String newEmail) {
        return mapper.updateEmail(uid, newEmail);
    }

    public boolean updateMobile(long uid, String newMobile) {
        return mapper.updateMobile(uid, newMobile);
    }
    
    public boolean updatePic(long uid, String pic) {
        return mapper.updatePic(uid, pic);
    }

    public boolean updateUsersLogin(long uid, String loginIp) {
        // 登陆失败成功的逻辑，现在只有成功。
        return userLoginService.updateUsersLogin(uid, loginIp);
    }

    public long autoRegister(String mobile, String email) {
        // 手机号必填，邮箱可以不填
        // 自动注册用户，email昵称从email中自动抽取，不存在自动生成
        if (StringUtils.isNotEmpty(email)) {
            if (!VerifyUtils.verifyEmail(email)) {
                return -1;
            }
        }
        if (mobile.length() < 8) {
            return -2;
        }
        if (queryUserByEmail(email).getUid() > 0) {
            return -3; // email重复
        }
        if (queryUserByMobile(mobile).getUid() > 0) {
            return -4; // 手机重复
        }
        String nick = UserHelper.getAutoNick(email);

        if (email.length() == 0) {
            email = MD5Util.md5Digest(UUID.randomUUID().toString());
        }
        if (mobile.length() == 0) {
            mobile = MD5Util.md5Digest(UUID.randomUUID().toString());
        }
        return createUser(Status.NORMAL, nick, null, email, mobile, Career.ELSE.getId(), "",
                SignIndex.AutoRegister, SignIndex.FirstPassword,
                SignIndex.NewMobile);
    }
    
    public UserQuota buildDefaultQuota(long userId, String realName){
        UserQuota quota = new UserQuota();
        quota.setUid(userId);
        quota.setBalance(new BigDecimal(0));
        if(StringUtils.isBlank(realName)){
        	quota.setRealname("");
        }else{
        	quota.setRealname(realName);
        }
        quota.setSchool("");
        quota.setIdCard("");
        quota.setContact("");
        quota.setGrade(Grade.fromId(0));
        quota.setClasses("");
        quota.setSchoolLocationId(0);
        quota.setSchoolLocation("");
        quota.setHomesite("");
        quota.setSchoolRemark("");
        quota.setIdCardReverse("");
        quota.setIdCardFront("");
        quota.setWishPlastic(0);
        quota.setScholasticYears(0);
        quota.setPosition("");   //职位
        quota.setDepartment(""); //部门
        quota.setPresent(new BigDecimal(0));
        quota.setQuotaAll(new BigDecimal(0));
        quota.setQuotaLeft(new BigDecimal(0));
        quota.setStartschoolAt(new Date());
        quota.setAuthStatus(AuthStatus.UNREAL.getId());
        quota.setCreatedAt(new Date());
        quota.setUpdatedAt(new Date());
        return quota;
    }
   
    
	public String modifyUser(Long uid, String nick, Gender gender) {
		
		logger.info("set user|{}|{}|{}", uid, nick, gender);
		
		int code = ErrorCodes.SUCCESS;
        String msg = "修改用户成功";
        
		if (StringUtils.isBlank(nick) || !VerifyUtils.verifyNick(nick)) {
            code = 1001;
            msg = "昵称格式错误";
            return JsonUtil.toJson(code, msg, null);
        }
		boolean result=mapper.updateSimpleProfile(uid, nick, gender.getValue());
	    
		Map<String, Object> data = Maps.newHashMap();
		if(result){
			code = 0;
			data.put("uid", uid);
		}else{
			code = ErrorCodes.CORE_ERROR;
			msg = "修改用户错误";
			data.put("uid", uid);
		}
		return JsonUtil.toJson(code, msg, data);
	}
    
    
    public int updateUserPresent(String mobile){
       return mapper.updatePresentByMobile(mobile);
    }

    @Transactional
    public String getInviteCode(long uid) throws Exception{
        UserExtend userExtend = userExtendService.getUserExtendByUid(uid);
        if(userExtend.getInviteCode()!=null && !userExtend.getInviteCode().equals("")){
            return userExtend.getInviteCode();
        }else{

            String inviteCode = UserUtils.makeInviteCode(uid);
            long count = userExtendService.updateUserInviteCode(uid,inviteCode);
            if(count != 1){
                throw  new Exception("更新邀请码出错");
            }
            return inviteCode;
        }
    }

    public boolean isHavePresentCoupon(long uid) {
        CouponBatchInfo info = couponService.findBatchInfoById(PERSENTCOUPON);
        if(info==null){
            // TODO: 16/1/16
        }
        User user = queryUser(uid);
        if(user == null){
            // TODO: 16/1/16
        }
        List<Coupon> list = couponService.findCouponsByBatchAndUid(uid, PERSENTCOUPON);
        if(list.size() > 0){
            return true;
        }
        return  false;
    }

    public long updateUserPresentCoupon(long uid) {
        CouponBatchInfo info = couponService.findBatchInfoById(PERSENTCOUPON);
        if(info==null){
            // TODO: 16/1/16
        }
        couponService.makeAndSaveCouponByBatch(uid,info.getId());
        return 1;
    }

    public List<User> queryAllUser(){
        return mapper.queryAllUser();
    }


    public List<User> queryUsersByPage(int p, int size){
        return mapper.queryUsersByPage(p,size);
    }


    @Transactional(readOnly = true)
    public void findByU(long uid){
        mapper.queryUser(uid);
    }

    @Transactional(readOnly = false)
    public void updateMo(long uid){
        mapper.updateStatus(uid,Status.DELETED);
    }

    public UsersDetail2App detail(long uid) {
        User user = queryUser(uid);
        UsersDetail detail = usersDetailMapper.selectByPrimaryKey(uid);
        if(detail == null){
            detail = new UsersDetail();
            detail.setUid(uid);
        }
        UsersDetail2App result = new UsersDetail2App(user,detail);


        return result;
    }


    @Transactional
    public void updateDetail(long uid, String desc, String interest, String nick, String sex, Integer blood, Integer constellation, String age, String job, String school, String area,String img) throws Exception{
        //Long uid, String img, String nick, String sex, String blood,
        // String constellation, String age, String job, String school, String area, String description) {
        User user = new User();
        user.setUid(uid);
        user.setImg(img);
        user.setNick(nick);

        user.setGender(sex!=null?sex.equals("男")?Gender.Male:sex.equals("女")?Gender.Female:null:null);

        UsersDetail detail = new UsersDetail();
        detail.setUid(uid);
        detail.setBloodType(blood);
        detail.setConstellation(constellation);
        detail.setAge(age);
        detail.setJob(job);
        detail.setSchool(school);
        detail.setArea(area);
        detail.setDescription(desc);
        detail.setInteresting(interest);

        logger.info(user.toString());
        int count = 0;
        if(StringUtils.isNotBlank(user.getNick()) || user.getGender() != null || StringUtils.isNotBlank(user.getImg())) {
            count += mapper.updateNickAndGenderByPrimaryKey(user);
        }
        if(usersDetailMapper.selectByPrimaryKey(uid) == null){
            count += usersDetailMapper.insertSelective(detail);
        }else{
            count += usersDetailMapper.updateByPrimaryKeySelective(detail);
        }




    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring/spring.xml");
        UserService service = ac.getBean(UserService.class);
        service.queryUserProfile2App(2874);
    }

}
