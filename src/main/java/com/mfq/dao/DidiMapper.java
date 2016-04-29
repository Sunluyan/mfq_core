package com.mfq.dao;

import com.mfq.annotation.MFQDao;
import com.mfq.bean.Didi;
import com.mfq.bean.DidiExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@MFQDao
@Component
public interface DidiMapper {
    int countByExample(DidiExample example);

    int deleteByExample(DidiExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Didi record);

    int insertSelective(Didi record);

    List<Didi> selectByExample(DidiExample example);

    Didi selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Didi record, @Param("example") DidiExample example);

    int updateByExample(@Param("record") Didi record, @Param("example") DidiExample example);

    int updateByPrimaryKeySelective(Didi record);

    int updateByPrimaryKey(Didi record);
}