package com.mfq.dao;

import com.mfq.annotation.MFQDao;
import com.mfq.bean.OperationRecord;
import com.mfq.bean.OperationRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@MFQDao
@Component
public interface OperationRecordMapper {
    int countByExample(OperationRecordExample example);

    int deleteByExample(OperationRecordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OperationRecord record);

    int insertSelective(OperationRecord record);

    List<OperationRecord> selectByExample(OperationRecordExample example);

    OperationRecord selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OperationRecord record, @Param("example") OperationRecordExample example);

    int updateByExample(@Param("record") OperationRecord record, @Param("example") OperationRecordExample example);

    int updateByPrimaryKeySelective(OperationRecord record);

    int updateByPrimaryKey(OperationRecord record);
}