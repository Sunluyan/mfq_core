package com.mfq.dao;

import com.mfq.annotation.MFQDao;
import com.mfq.bean.Baoming;
import com.mfq.bean.BaomingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@MFQDao
public interface BaomingMapper {
    int countByExample(BaomingExample example);

    int deleteByExample(BaomingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Baoming record);

    int insertSelective(Baoming record);

    List<Baoming> selectByExample(BaomingExample example);

    Baoming selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Baoming record, @Param("example") BaomingExample example);

    int updateByExample(@Param("record") Baoming record, @Param("example") BaomingExample example);

    int updateByPrimaryKeySelective(Baoming record);

    int updateByPrimaryKey(Baoming record);

    int selectByBaoming(Baoming baoming);
}