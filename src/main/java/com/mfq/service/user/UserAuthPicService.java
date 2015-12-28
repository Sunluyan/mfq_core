package com.mfq.service.user;

import com.google.common.collect.Maps;
import com.mfq.bean.app.UserProfile2App;
import com.mfq.bean.user.*;
import com.mfq.constants.AuthStatus;
import com.mfq.constants.Career;
import com.mfq.constants.ErrorCodes;
import com.mfq.constants.Grade;
import com.mfq.controller.PictureController;
import com.mfq.dao.UserMapper;
import com.mfq.dao.UsersAuthPicMapper;
import com.mfq.helper.UserHelper;
import com.mfq.utils.JsonUtil;
import com.mfq.utils.MD5Util;
import com.mfq.utils.VerifyUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class UserAuthPicService {

    @Resource
    UsersAuthPicMapper mapper;
    
    final UsersAuthPicExample example = new UsersAuthPicExample();
	private static final Logger logger = LoggerFactory
            .getLogger(UserAuthPicService.class);


    public boolean updatePicByType(Long userId, String key, PictureController.MIAN m) {
    	
    	example.or().andUidEqualTo(userId);
    	List<UsersAuthPic> list =mapper.selectByExample(example);
    	UsersAuthPic pic = null;
    	if(list.size() > 0){
    		pic = list.get(0);
    	}else{
    		pic = defaultUsersAuthPic(userId);
			int i = mapper.insert(pic);
			if (i < 1) {
				logger.warn("user pic is null usersId = {}", userId);
			}
    	}
    	
    	
    	if(m.getId() == PictureController.MIAN.SOCIAL_F.getId()){
    		pic.setSocialF(key);
    	}else if(m.getId() == PictureController.MIAN.SOCIAL_R.getId()){
    		pic.setSocialR(key);
    	}else if(m.getId() == PictureController.MIAN.WORK_PERMIT_F.getId()){
    		pic.setWorkPermitF(key);
    	}else if(m.getId()== PictureController.MIAN.WORK_PERMIT_R.getId()){
    		pic.setWorkPermitR(key);
    	}
        
    	mapper.updateByExampleSelective(pic, example);
        return false;
    }
    
    private UsersAuthPic defaultUsersAuthPic(Long uid){
    	UsersAuthPic pic = new UsersAuthPic();
    	pic.setUid(uid);
    	pic.setIdCard("");
    	pic.setIdCardF("");
    	pic.setIdCardR("");
    	pic.setSocial("");
    	pic.setSocialF("");
    	pic.setSocialF("");
    	pic.setSocialR("");
    	pic.setWorkPermit("");
    	pic.setWorkPermitF("");
    	pic.setWorkPermitR("");
    	return pic;
    	
    }
    
}
