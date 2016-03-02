package com.mfq.dao;

import com.mfq.annotation.MFQDao;
import com.mfq.bean.ProFqRecord;
import com.mfq.bean.ProFqRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@MFQDao
@Component
public interface ProFqRecordMapper {
    int countByExample(ProFqRecordExample example);

    int deleteByExample(ProFqRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProFqRecord record);

    int insertSelective(ProFqRecord record);

    List<ProFqRecord> selectByExample(ProFqRecordExample example);

    ProFqRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProFqRecord record, @Param("example") ProFqRecordExample example);

    int updateByExample(@Param("record") ProFqRecord record, @Param("example") ProFqRecordExample example);

    int updateByPrimaryKeySelective(ProFqRecord record);

    int updateByPrimaryKey(ProFqRecord record);
}