package com.mfq.service;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mfq.bean.InviteRecord;
import com.mfq.bean.user.*;
import com.mfq.dao.InviteRecordMapper;
import com.mfq.service.user.UserExtendService;
import com.mfq.utils.RequestUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;
import com.mfq.bean.CodeMsg;
import com.mfq.bean.passport.Passport;
import com.mfq.constants.ErrorCodes;
import com.mfq.dataservice.context.AppContext;
import com.mfq.helper.MobileHelper;
import com.mfq.net.tongdun.FraudApiInvoker;
import com.mfq.service.passport.PassportService;
import com.mfq.service.sms.MailAndSmsService;
import com.mfq.service.user.UserLoginService;
import com.mfq.service.user.UserQuotaService;
import com.mfq.service.user.UserService;
import com.mfq.utils.CookieUtils;
import com.mfq.utils.JsonUtil;
import com.mfq.utils.VerifyUtils;

@Service
public class RegisterService {

    private static final Logger logger = LoggerFactory
            .getLogger(RegisterService.class);

    @Resource
    UserService userService;
    @Resource
    UserLoginService userLoginService;
    @Resource
    VcodeService vcodeService;
    @Resource
    UserExtendService userExtendService;
    @Resource
    PassportService passportService;
    @Resource
    MailAndSmsService mailAndSmsService;
    @Resource
    UserQuotaService userQuotaService;
    @Resource
    FraudApiInvoker fraudApiInvoker;
    @Resource
    InviteService inviteService;
    @Resource
    LoginService loginService;


    @Transactional
    public String reg(long uid, long end) {
        logger.info("start is {}, end is {}", uid, end);
//    	for(; uid<=end;uid++){
//    		User user = userService.queryUser(uid);
//    		if( user!= null){
//    		Passport passport = passportService.createPassport(user.getUid(),
//                    user.getMobile().substring(6, 10));
//    		UserQuota quota = userService.buildDefaultQuota(user.getUid(), "");
//    		
//    		
//	    		userLoginService.createUsersLogin(passport.getUid(), "127.0.0.1",
//	                    "", "");
//    		
//    		logger.info("userquota  is start ... {}", uid);
//    		if(userQuotaService.queryUserQuota(uid) == null);
//    			long id = userQuotaService.insertUserQuota(quota);
//    			if(id>0){
//    				logger.info(" success is {}", uid);
//    			}else{
//    				logger.info(" error is {}", uid);
//    			}
//    		}
//    	}

        return "success";
    }

    @Transactional
    public long reg(String email, String mobile, String nick, String password,
                      String vcode, String refer, String regip, String source,
                      String regTrack, int stu, String invite_code, String blackbox, String mobileType,
                      HttpServletRequest request, HttpServletResponse response) throws Exception {

        logger.info("REG|{}|{}|{}|{}|{}|{}|{}|{}|{}", email, mobile, nick,
                password, vcode, refer, regip, source, regTrack, stu);

        //////////////插入同盾验证
        String ip_address = AppContext.getIp();

        Map<String, Object> result = Maps.newHashMap();
        if (StringUtils.isNotEmpty(blackbox))
            result = fraudApiInvoker.fraudRegister(mobile, mobile, ip_address, blackbox, mobileType);
        //如果result不为空且裁决是拒绝时
        if (result != null && result.size() > 0 && result.get("decision").toString().toLowerCase().equals("reject")) {
            throw new Exception(result.get("msg").toString());
        }
        //////////////

        int code = ErrorCodes.SUCCESS;
        String msg = "注册成功";
        boolean active = false;

        UserExtend userExtend = null;

        if (StringUtils.isNotBlank(nick) && !VerifyUtils.verifyNick(nick)) {
            code = 1001;msg = "昵称格式错误";
        } else if (!VerifyUtils.verifyPassword(password)) {
            code = 1003;msg = "密码格式错误";
        } else if (StringUtils.isBlank(email) && StringUtils.isBlank(mobile)) {
            code = 1201;msg = "邮箱和手机号必须填充一项";
        } else if (StringUtils.isNotBlank(email) && !VerifyUtils.verifyEmail(email)) {
            code = 1002;msg = "邮箱格式错误";
        } else if (StringUtils.isNotBlank(email) && userService.queryUserByEmail(email).getUid() != 0) {
            code = 1102;msg = "此邮箱已注册";
        } else if (StringUtils.isNotBlank(mobile)
                && !VerifyUtils.verifyMobile(mobile)) {
            code = 1004;msg = "手机号格式错误";
        } else if (StringUtils.isNotBlank(mobile)
                && userService.queryUserByMobile(mobile).getUid() != 0) {
            code = 1104;msg = "此手机号已注册";
        } else if (StringUtils.isNotBlank(mobile)) {
            CodeMsg codeMsg = vcodeService.validate(mobile, vcode);
            if (codeMsg.getCode() == 0) {
                active = true;code = codeMsg.getCode();msg = codeMsg.getMsg();
            }
        } else if (invite_code.length() != 7) {
            userExtend = userExtendService.getUserExtendByInviteCode(invite_code);
            if (userExtend == null) {code = 1105;msg = "邀请码错误";}
        }

        if (code != 0) {
            logger.warn("reg fail! code={}, msg={}", code, msg);
            throw new Exception(msg);
        }


        Map<String, Object> data = Maps.newHashMap();
        long userId = userService.createUser(
                active ? Status.NORMAL : Status.INACTIVE, nick, null, email, mobile, stu, invite_code,
                new SignIndex[0]);


        //插入一条邀请记录 如果有邀请码的话
        if (StringUtils.isNotBlank(invite_code)) {
            InviteRecord inviteRecord = new InviteRecord();
            inviteRecord.setUid(userId);
            inviteRecord.setInvitedTime(new Date());
            inviteRecord.setInvitedUid(userId);
            inviteService.insertSelective(inviteRecord);
        }


        Passport passport = passportService.createPassport(userId,
                password);

        if (userId <= 0 || passport.getUid() == 0) {
            throw new Exception("创建用户失败");
        }

        long count = userLoginService.createUsersLogin(passport.getUid(), regip,
                source, regTrack);
        if (count != 1) {
            throw new Exception("登陆失败");
        }

        if (active) {
            userLoginService.updateUsersLoginActivedTime(passport.getUid());
            CookieUtils.setLoginCookie(request, response, passport,
                    true);
        }

        data.put("uid", userId);
        data.put("token", passport.getTicket());
        if (active) {
            data.put("status", Status.NORMAL.getValue());
        } else {
            data.put("status", Status.INACTIVE.getValue());
        }
        String refers = RequestUtils.getString(request, "refer", request.getHeader("Referer"));
        refers = StringUtils.isEmpty(refers) ? "http://www.5imfq.com/profile/" : refers;
        //登录
        String loginResult = loginService.login(request, response, mobile, password, refers, false,blackbox);
        Map<String, Object> loginResultMap = JsonUtil.getMapFromJsonStr(loginResult);
        code = Integer.parseInt(loginResultMap.get("code").toString());
        msg = loginResultMap.get("msg") == null ? null :loginResultMap.get("msg").toString();
        if (code == 0) {
            // 更新access token所对应的用户信息
            @SuppressWarnings("unchecked")
            Map<String, Object> datas = (Map<String, Object>)loginResultMap.get("data");
            if(datas != null && datas.get("uid") != null){
                Long uid = Long.parseLong(datas.get("uid").toString());
                loginService.updateIntallations(request, uid);
            }
        }else{
            throw new Exception(msg);
        }

        return userId;
    }
}