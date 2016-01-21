package com.mfq.dao;

import com.mfq.annotation.MFQDao;
import com.mfq.bean.Presental;
import com.mfq.bean.PresentalExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@MFQDao
@Component
public interface PresentalMapper {
    int countByExample(PresentalExample example);

    int deleteByExample(PresentalExample example);

    int deleteByPrimaryKey(String code);

    int insert(Presental record);

    int insertSelective(Presental record);

    List<Presental> selectByExample(PresentalExample example);

    Presental selectByPrimaryKey(String code);

    int updateByExampleSelective(@Param("record") Presental record, @Param("example") PresentalExample example);

    int updateByExample(@Param("record") Presental record, @Param("example") PresentalExample example);

    int updateByPrimaryKeySelective(Presental record);

    int updateByPrimaryKey(Presental record);
}