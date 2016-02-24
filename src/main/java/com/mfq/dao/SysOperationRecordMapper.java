package com.mfq.dao;

import com.mfq.bean.SysOperationRecord;
import com.mfq.bean.SysOperationRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysOperationRecordMapper {
    int countByExample(SysOperationRecordExample example);

    int deleteByExample(SysOperationRecordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysOperationRecord record);

    int insertSelective(SysOperationRecord record);

    List<SysOperationRecord> selectByExample(SysOperationRecordExample example);

    SysOperationRecord selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysOperationRecord record, @Param("example") SysOperationRecordExample example);

    int updateByExample(@Param("record") SysOperationRecord record, @Param("example") SysOperationRecordExample example);

    int updateByPrimaryKeySelective(SysOperationRecord record);

    int updateByPrimaryKey(SysOperationRecord record);
}