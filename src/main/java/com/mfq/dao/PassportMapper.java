package com.mfq.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.mfq.annotation.MFQDao;
import com.mfq.bean.passport.Passport;
import org.springframework.stereotype.Component;

@MFQDao
@Component
public interface PassportMapper {

    public long insertPassport(Passport passport);

    public Passport queryPassport(@Param("uid") long uid);

    public Passport queryValidPassportByTicket(@Param("uid") long uid,
            @Param("ticket") String ticket, @Param("status") int status);

    public boolean updateSalt2AndPassword2(@Param("uid") long uid,
            @Param("salt2") String salt2, @Param("password2") String password2);

    public boolean updateSalt2AndPP2(@Param("uid") long uid,
            @Param("salt2") String salt2, @Param("password") String password,
            @Param("password2") String password2);

    public boolean updateExpired(@Param("uid") long uid,
            @Param("expiredAt") long expiredAt);

    public boolean updateDefaultTicket(@Param("uid") long uid,
            @Param("ticket") String ticket, @Param("createdAt") Date createdAt,
            @Param("expiredAt") Date expiredAt);

}