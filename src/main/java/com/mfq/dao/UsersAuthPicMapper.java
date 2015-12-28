package com.mfq.dao;

import com.mfq.annotation.MFQDao;
import com.mfq.bean.user.UsersAuthPic;
import com.mfq.bean.user.UsersAuthPicExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@MFQDao
public interface UsersAuthPicMapper {
    int countByExample(UsersAuthPicExample example);

    int deleteByExample(UsersAuthPicExample example);

    int insert(UsersAuthPic record);

    int insertSelective(UsersAuthPic record);

    List<UsersAuthPic> selectByExample(UsersAuthPicExample example);

    int updateByExampleSelective(@Param("record") UsersAuthPic record, @Param("example") UsersAuthPicExample example);

    int updateByExample(@Param("record") UsersAuthPic record, @Param("example") UsersAuthPicExample example);
}