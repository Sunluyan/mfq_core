package com.mfq.dao;

import com.mfq.annotation.MFQDao;
import com.mfq.bean.InterviewInfo;
import com.mfq.bean.InterviewInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@MFQDao
public interface InterviewInfoMapper {
    int countByExample(InterviewInfoExample example);

    int deleteByExample(InterviewInfoExample example);

    int deleteByPrimaryKey(Long uid);

    int insert(InterviewInfo record);

    int insertSelective(InterviewInfo record);

    List<InterviewInfo> selectByExample(InterviewInfoExample example);

    InterviewInfo selectByPrimaryKey(Long uid);

    int updateByExampleSelective(@Param("record") InterviewInfo record, @Param("example") InterviewInfoExample example);

    int updateByExample(@Param("record") InterviewInfo record, @Param("example") InterviewInfoExample example);

    int updateByPrimaryKeySelective(InterviewInfo record);

    int updateByPrimaryKey(InterviewInfo record);
}