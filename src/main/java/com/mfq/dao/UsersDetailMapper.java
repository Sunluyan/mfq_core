package com.mfq.dao;

import com.mfq.annotation.MFQDao;
import com.mfq.bean.UsersDetail;
import com.mfq.bean.UsersDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@MFQDao
@Component
public interface UsersDetailMapper {
    int countByExample(UsersDetailExample example);

    int deleteByExample(UsersDetailExample example);

    int deleteByPrimaryKey(Long uid);

    int insert(UsersDetail record);

    int insertSelective(UsersDetail record);

    List<UsersDetail> selectByExample(UsersDetailExample example);

    UsersDetail selectByPrimaryKey(Long uid);

    int updateByExampleSelective(@Param("record") UsersDetail record, @Param("example") UsersDetailExample example);

    int updateByExample(@Param("record") UsersDetail record, @Param("example") UsersDetailExample example);

    int updateByPrimaryKeySelective(UsersDetail record);

    int updateByPrimaryKey(UsersDetail record);
}