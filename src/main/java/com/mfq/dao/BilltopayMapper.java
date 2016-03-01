package com.mfq.dao;

import com.mfq.annotation.MFQDao;
import com.mfq.bean.Billtopay;
import com.mfq.bean.BilltopayExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@MFQDao
@Component
public interface BilltopayMapper {
    int countByExample(BilltopayExample example);

    int deleteByExample(BilltopayExample example);

    int insert(Billtopay record);

    int insertSelective(Billtopay record);

    List<Billtopay> selectByExampleWithBLOBs(BilltopayExample example);

    List<Billtopay> selectByExample(BilltopayExample example);

    int updateByExampleSelective(@Param("record") Billtopay record, @Param("example") BilltopayExample example);

    int updateByExampleWithBLOBs(@Param("record") Billtopay record, @Param("example") BilltopayExample example);

    int updateByExample(@Param("record") Billtopay record, @Param("example") BilltopayExample example);
}