package com.mfq.dao;

import java.math.BigDecimal;

import org.apache.ibatis.annotations.Param;

import com.alibaba.dubbo.config.support.Parameter;
import com.mfq.annotation.MFQDao;
import com.mfq.bean.user.Gender;
import com.mfq.bean.user.UserQuota;

@MFQDao
public interface UserQuotaMapper {

    public long insertUserQuota(UserQuota quota);

    public UserQuota queryUserQuota(@Param("uid") long uid);

    /**
     * 
     * @param uid 用户ID
     * @param num 要更新剩余余额是多少，变数部分，可为负数！
     * @param balance 旧的余额额度是多少
     * @return
     */
    public long updateUserBalance(@Param("uid") long uid, @Param("balance") BigDecimal balance);

    
    /**
     * 更新用户剩余赠送额度
     * @param uid
     * @param num
     * @param present
     * @return
     */
    public long updateUserPresent(@Param("uid") long uid,
            @Param("num") BigDecimal num, @Param("present") BigDecimal present);

    
    /**
     * 更新用户分期额度
     * @param quota
     * @return
     */
    public long updateUserQuota(UserQuota quota);

	public long updateUserWish(@Param("uid")long uid, @Param("wish")int wish);

	public boolean updateIdPic(@Param("uid")Long userId, @Param("pic") String pic,@Param("type") int i);

	public long updateUserQuotaForAdult(@Param("realname")String realname,@Param("idCard") String idCard,@Param("gender") Gender g,@Param("origin") String origin,@Param("status") int authStatus, @Param("schoolLocation")String schoolLocation, @Param("uid")Long userId);
	

	public int updateAdultWorkInfo(@Param("uid")Long uid, @Param("company")String company, @Param("position")String position, @Param("department")String department, @Param("salary")String salary,
			@Param("socialInsurance")String socialInsurance, @Param("workYears")String workYears);

	public int updateStudentWorkInfo(@Param("uid")Long uid, @Param("studentId")String student_id, @Param("school")String school, @Param("schoolLocation")String school_location, @Param("grade")long grade,
			@Param("schoolLevel")String school_level, @Param("faculty")String faculty, @Param("speciality")String speciality, @Param("scholasticYears")int scholastic_years);
	
	
	public int updateAuthStatusByUid(@Param("uid")long uid,@Param("authStatus")int authStatus);
	
	
}