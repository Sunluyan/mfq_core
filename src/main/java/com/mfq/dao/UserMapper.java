package com.mfq.dao;

import org.apache.ibatis.annotations.Param;

import com.mfq.annotation.MFQDao;
import com.mfq.bean.user.Status;
import com.mfq.bean.user.User;
import org.springframework.stereotype.Component;

import java.util.List;

@MFQDao
@Component
public interface UserMapper {

    public long insertUser(User user);

    public User queryUser(@Param("uid") long uid);
    
    public User queryUserByEmail(@Param("email") String email);
    
    public User queryUserByMobile(@Param("mobile") String mobile);
    
    public boolean updateStatus(@Param("uid") long uid, @Param("status") Status status);
    
    public boolean updateSetSigns(@Param("uid") long uid, @Param("setSign") int setSign);
    
    public boolean updateUnsetSigns(@Param("uid") long uid, @Param("setSign") int setSign);
    
    public boolean updateSign(@Param("uid") long uid, @Param("sign") int sign);
    
    public boolean updateSimpleProfile(@Param("uid") long uid, @Param("nick") String nick, @Param("gender") int gender);

    public boolean updateEmail(@Param("uid") long uid, @Param("newEmail") String newEmail);

    public boolean updateMobile(@Param("uid") long uid, @Param("newMobile") String newMobile);
    

    public boolean updatePic(@Param("uid") long uid, @Param("pic") String pic);

    public int updatePresentByMobile(@Param("mobile")String  mobile);

    public List<User> queryAllUser();

    List<User> queryUsersByPage(@Param("start") int p,@Param("size") int size);
}
