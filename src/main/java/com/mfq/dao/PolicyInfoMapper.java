package com.mfq.dao;

import com.mfq.annotation.MFQDao;
import com.mfq.bean.PolicyInfo;
import com.mfq.bean.PolicyInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@MFQDao
@Component
public interface PolicyInfoMapper {
    int countByExample(PolicyInfoExample example);

    int deleteByExample(PolicyInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PolicyInfo record);

    int insertSelective(PolicyInfo record);

    List<PolicyInfo> selectByExample(PolicyInfoExample example);

    PolicyInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PolicyInfo record, @Param("example") PolicyInfoExample example);

    int updateByExample(@Param("record") PolicyInfo record, @Param("example") PolicyInfoExample example);

    int updateByPrimaryKeySelective(PolicyInfo record);

    int updateByPrimaryKey(PolicyInfo record);
}