package com.mfq.dao;

import com.mfq.annotation.MFQDao;
import com.mfq.bean.PortalTongdun;
import com.mfq.bean.PortalTongdunExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@MFQDao
@Component
public interface PortalTongdunMapper {
    int countByExample(PortalTongdunExample example);

    int deleteByExample(PortalTongdunExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PortalTongdun record);

    int insertSelective(PortalTongdun record);

    List<PortalTongdun> selectByExample(PortalTongdunExample example);

    PortalTongdun selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PortalTongdun record, @Param("example") PortalTongdunExample example);

    int updateByExample(@Param("record") PortalTongdun record, @Param("example") PortalTongdunExample example);

    int updateByPrimaryKeySelective(PortalTongdun record);

    int updateByPrimaryKey(PortalTongdun record);
}