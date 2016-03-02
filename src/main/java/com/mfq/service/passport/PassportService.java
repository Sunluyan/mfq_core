package com.mfq.service.passport;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mfq.bean.passport.Passport;
import com.mfq.bean.user.Status;
import com.mfq.dao.PassportMapper;
import com.mfq.utils.CommonUtil;
import com.mfq.utils.DateUtil;
import com.mfq.utils.MD5Util;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PassportService {

	private static final Logger logger = LoggerFactory.getLogger(PassportService.class);
	
    private final static long TICKET_PERSISTEN_TIME = TimeUnit.DAYS
            .toMillis(14); // 记住密码的有效期14天
    private final static long TICKET_TEMP_TIME = TimeUnit.DAYS.toMillis(7); // 临时登录的有效期6小时
    private final static long TICKET_DELAY_TIME = TICKET_TEMP_TIME / 2; // 每个三个小时刷新一次时间

    @Resource
    PassportMapper mapper;
    
    /**
     * 新的登录方式
     * 返回的passport中uid大于0成功，等于0验证失败，小于0用户不存在
     * @param password2-md5( md5(plainPassword+salt)+salt2 )
     * @return 登录信息，登录成功后salt2/password2 会更新
     */
    public Passport login(long uid, String password2, boolean autoLogin) {
        Passport passport = queryPassport(uid);
        if (passport.getUid() == 0) {
            passport.setUid(-1); // 用户不存在
            return passport;
        }
        if (MD5Util.md5Digest(passport.getPassword() + passport.getSalt2())
                .equals(password2)) {
            // 验证通过, 登录成功，更新salt2、password2
            _resetSalt2(passport);
        } else {
            passport.setUid(0);
            passport.setPassword("");
            passport.setPassword2("");
            passport.setTicket("");
        }
        return defaultFlushTicketTime(passport, true, autoLogin, false);
    }

    public Passport validateTicket(long uid, String ticket) {
        Passport passport = mapper.queryValidPassportByTicket(uid, ticket, Status.DELETED.getValue());
        logger.info("validate from db passport={}", passport);
        if (passport == null) {
            passport = new Passport();
            passport.setUid(0);
        } else {
            // 验证票据成功，此时要刷新票据有效期为新value
            defaultFlushTicketTime(passport, false, false, false);
        }
        return passport;
    }

    /**
     * 创建一个passport，此passport带有ticket以及salt等信息
     */
    @Transactional
    public Passport createPassport(long uid, String plainPassword) throws Exception{
        String salt = _uuid();
        String salt2 = _uuid();
        Passport passport = new Passport();
        passport.setUid(uid);
        passport.setTicket(CommonUtil.createTicket());
        passport.setCreatedAt(new Date());
        passport.setExpiredAt(DateUtil.addDay(new Date(), 7));
        passport.setPassword(MD5Util.md5Digest(plainPassword + salt));
        passport.setSalt(salt);
        passport.setSalt2(salt2);
        passport.setPassword2(
                MD5Util.md5Digest(passport.getPassword() + salt2));
        long count = mapper.insertPassport(passport);
        if(count != 1){
            throw new Exception("创建passport失败");
        }
        return passport;
    }

    public Passport queryPassport(long uid) {
        Passport passport = mapper.queryPassport(uid);
        if (passport != null && (StringUtils.isEmpty(passport.getSalt2())
                || StringUtils.isEmpty(passport.getPassword2()))) {
            _resetSalt2(passport);
        }
        return passport == null ? new Passport() : passport;
    }

    public boolean flushTicketTime(long uid, long expiredTime) {
        return mapper.updateExpired(uid, expiredTime);
    }

    public boolean destroyPassport(long uid) {
        return mapper.updateExpired(uid, System.currentTimeMillis());
    }

    public Passport oauthLogin(long uid, boolean autoLogin) {
        Passport passport = queryPassport(uid);
        return defaultFlushTicketTime(passport, true, autoLogin, false);
    }

    /**
     * 通过提供旧密码，修改密码的渠道
     * 
     * @param uid
     * @return
     */
    public boolean updatePassword(long uid, String oldPlainPassword,
            String newPlainPassword) {
        Passport passport = validatePassword(uid, oldPlainPassword);
        return updatePassword(passport, newPlainPassword);
    }

    /**
     * 直接更新密码，无视旧密码
     * 
     * @param passport
     *            数据库passport
     * @param newPassword
     *            新密码
     * @return 是否更新成功
     */
    public boolean updatePassword(Passport passport, String newPassword) {
        boolean ret = false;
        if (passport.getUid() > 0) {
            String password = MD5Util
                    .md5Digest(newPassword + passport.getSalt());
            String salt2 = _uuid();
            String password2 = MD5Util.md5Digest(password + salt2);
            ret = mapper.updateSalt2AndPP2(passport.getUid(),
                    salt2, password,
                    password2);
            if (ret) {
                defaultFlushTicketTime(passport, false, false, true);
            }
        }
        return ret;
    }

    public boolean updatePasswordForAdmin(long uid, String newPassword) {
        Passport passport = queryPassport(uid);
        return updatePassword(passport, newPassword);
    }

    public Passport validatePassword(long uid, String plainPassword) {
        Passport passport = queryPassport(uid);
        // 验证用户一代密码
        if (passport.getUid() > 0
                && !MD5Util.md5Digest(plainPassword + passport.getSalt())
                        .equals(passport.getPassword())) {
            passport = new Passport();
        }
        return passport;
    }

    private boolean _resetSalt2(Passport passport) {
        // 重置新的salt2 & password2
        String newSalt2 = _uuid();
        String newPassword2 = MD5Util
                .md5Digest(passport.getPassword() + newSalt2);
        passport.setSalt2(newSalt2);
        passport.setPassword2(newPassword2);
        return mapper.updateSalt2AndPassword2(passport.getUid(),
                passport.getSalt2(), passport.getPassword2());
    }

    private String _uuid() {
        return UUID.randomUUID().toString();
    }

    /**
     * 按照默认的规则刷新ticket时间
     * <ul>
     * <li>登录操作：
     * <ol>
     * <li>如果ticket已经过期，则ticket值变化（新的ticket值）</li>
     * <li>记住密码，有效期从登录起14天后</li>
     * <li>非记住密码，有效期从登录起6小时</li>
     * <li>第三方登录，有效期从登录起6小时</li>
     * </ol>
     * </li>
     * <li>刷新操作：过期时间小于3小时，延长至6小时，否则什么不操作</li>
     * <li>修改密码：ticket值变化，有效期不变</li>
     * </ul>
     * 
     * @param passport
     *            当前票据
     * @param isLogin
     *            是否登录操作
     * @param autoLogin
     *            是否自动登录 (当前仅当登录操作并且是自动登录)
     * @param resetPassword
     *            是否修改密码,如果是修改密码，则用户强制退出登录
     * @return 刷新ticket逻辑参考 {@link #login(long, String, boolean)}
     */
    private Passport defaultFlushTicketTime(Passport passport, boolean isLogin,
            boolean autoLogin, boolean resetPassword) {
        if (passport.getUid() <= 0)
            return passport;// 密码验证不通过
        long uid = passport.getUid();
        boolean newTicket = resetPassword;// 是否需要新的ticket
        long extendTime = 0L;// 延长ticket的时间
        if (!newTicket) {
            if (isLogin) {
                extendTime = autoLogin ? TICKET_PERSISTEN_TIME
                        : TICKET_TEMP_TIME;
                newTicket = (passport.getExpiredAt().getTime() - System
                        .currentTimeMillis()) < 0;
            } else {
                extendTime = (passport.getExpiredAt().getTime()
                        - System.currentTimeMillis()) < TICKET_DELAY_TIME
                                ? TICKET_TEMP_TIME : 0L;
            }
        }
        if (newTicket || extendTime > 0) {
            if (newTicket) {
                passport.setTicket(CommonUtil.createTicket());
                passport.setCreatedAt(new Date());
            }
            if (extendTime > 0) {
                passport.setExpiredAt(new Date(System.currentTimeMillis() + extendTime));
            }
            mapper.updateDefaultTicket(uid, passport.getTicket(),
                    passport.getCreatedAt(), passport.getExpiredAt());
        }
        return passport;
    }
}
