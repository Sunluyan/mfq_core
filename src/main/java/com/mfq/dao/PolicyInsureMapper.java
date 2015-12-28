package com.mfq.dao;

import com.mfq.annotation.MFQDao;
import com.mfq.bean.PolicyInsure;
import com.mfq.bean.PolicyInsureExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@MFQDao
public interface PolicyInsureMapper {
    int countByExample(PolicyInsureExample example);

    int deleteByExample(PolicyInsureExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PolicyInsure record);

    int insertSelective(PolicyInsure record);

    List<PolicyInsure> selectByExample(PolicyInsureExample example);

    PolicyInsure selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PolicyInsure record, @Param("example") PolicyInsureExample example);

    int updateByExample(@Param("record") PolicyInsure record, @Param("example") PolicyInsureExample example);

    int updateByPrimaryKeySelective(PolicyInsure record);

    int updateByPrimaryKey(PolicyInsure record);
}