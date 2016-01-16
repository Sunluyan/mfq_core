package com.mfq.dao;

import com.mfq.annotation.MFQDao;
import com.mfq.bean.InviteRecord;
import com.mfq.bean.InviteRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@MFQDao
public interface InviteRecordMapper {
    int countByExample(InviteRecordExample example);

    int deleteByExample(InviteRecordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(InviteRecord record);

    int insertSelective(InviteRecord record);

    List<InviteRecord> selectByExample(InviteRecordExample example);

    InviteRecord selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") InviteRecord record, @Param("example") InviteRecordExample example);

    int updateByExample(@Param("record") InviteRecord record, @Param("example") InviteRecordExample example);

    int updateByPrimaryKeySelective(InviteRecord record);

    int updateByPrimaryKey(InviteRecord record);
}