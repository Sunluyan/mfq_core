package com.mfq.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.mfq.annotation.LoginRequired;
import com.mfq.bean.user.User;
import com.mfq.constants.ErrorCodes;
import com.mfq.constants.QiniuBucketEnum;
import com.mfq.dataservice.context.UserIdHolder;
import com.mfq.filemanipulate.image.service.QiniuManipulater;
import com.mfq.service.user.UserAuthPicService;
import com.mfq.service.user.UserQuotaService;
import com.mfq.service.user.UserService;
import com.mfq.utils.JsonUtil;
import com.mfq.utils.RequestUtils;

@Controller
@RequestMapping("/picture")
public class PictureController {

    private static final Logger logger = LoggerFactory
            .getLogger(PictureController.class);

    @Resource
    UserService userService;
    @Resource
    UserQuotaService userQuotaService;
    @Resource
    UserAuthPicService usersAuthPicService;
    
   public enum MIAN{
    	FRONT(1,"id_card_f","身份证正面"),
    	REVERSE(2,"id_card_r","身份证反面"),
        SOCIAL_F(3,"social_f","社保正"),
        SOCIAL_R(4,"social_r","社保反"),
        WORK_PERMIT_F(5,"work_permit_f","工作证正"),
        WORK_PERMIT_R(6,"work_permit_r","工作证反");
	   
	   int id;
    	String flag;
    	String desc;
    	MIAN(int id, String flag, String desc){
    		this.flag = flag;
    		this.desc = desc;
    		this.id = id;
    	}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getFlag() {
			return flag;
		}
		public void setFlag(String flag) {
			this.flag = flag;
		}
		public String getDesc() {
			return desc;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}
    	
    	
    }
    
    /**
     * 生成上传凭证
     * 
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/token/", method = RequestMethod.GET)
    @ResponseBody
    @LoginRequired
    public String uploadToken(HttpServletRequest request) throws Exception {
        if (!UserIdHolder.isLogin()) {
            logger.error("USER_IS_NOT_LOGIN");
            return JsonUtil.toJson(ErrorCodes.CORE_NEED_LOGIN, "需要登录", null);
        }
        Map<String, String> data = Maps.newHashMap();
        try {
        	String type = StringUtils.stripToEmpty(request.getParameter("t"));
			if ("".equals(type)){
				type = StringUtils.stripToEmpty(request.getParameter("f"));
			}
        	int t = 0;
        	if(StringUtils.isNotBlank(type))
        		t = Integer.parseInt(type);
        	if(1==t||t ==2){

        		MIAN mians = MIAN.FRONT;
        		if(t == 2){
        			mians = MIAN.REVERSE;
        		}
        		data = QiniuManipulater.getAuthUpToken(UserIdHolder.getUserId(), mians.flag);
        		
        	}else if(t==3||t==4||t==5||t==6){
                data = QiniuManipulater.getAuthUpToken(UserIdHolder.getUserId(),type);
            }else{
        		data = QiniuManipulater.getAvatarUpToken(UserIdHolder.getUserId());
        	}
        } catch (Exception e) {
            logger.error("GENERATE_AVATAR_TOKEN_ERROR", e);
        }
        String ret = JsonUtil.successResultJson(data);
        logger.info("End..UploadToken2App is:{}", ret);
        return ret;
    }

    @RequestMapping(value = "/callback/{bucket}/{userId}", method = RequestMethod.POST)
    @ResponseBody
    public String callback(@PathVariable("userId") Long userId,
            @PathVariable("bucket") String bucket, HttpServletRequest request)
                    throws Exception {
    	logger.info("qiniu  callback {}|{}|{}", bucket, userId, RequestUtils.getString(request, "key", null));
        Map<String, Object> ret = Maps.newHashMap();
        boolean flag = false;
        String key = null;
        try {
            if (userId != null && userId > 0) {
            	key = RequestUtils.getString(request, "key", null);
            	if(QiniuBucketEnum.AUTH.getBucket().equals(bucket)){
            		if (StringUtils.isNotBlank(key)&&key.contains(MIAN.FRONT.flag)) {
            			flag = userQuotaService.updateIDPic(userId, key, 1);
            		}else if(StringUtils.isNotBlank(key)&&key.contains(MIAN.REVERSE.flag)){
            			flag = userQuotaService.updateIDPic(userId, key, 2);
            		}else{
                        flag = usersAuthPicService.updatePicByType(userId, key, MIAN.SOCIAL_F);
                    }
            	}else{
	            	// 处理用户头像的逻辑
	                
	                if (StringUtils.isNotBlank(key)) {
	                    User user = userService.queryUser(userId);
	                    if(user != null && StringUtils.isNotBlank(user.getPic())){
	                        //QiniuManipulater.deleteByKey(QiniuBucketEnum.AVATAR, user.getPic());
	                    }
	                    flag = userService.updatePic(userId, key);
	                }
            	}
            } else {
                // 回调返回－产品上传图片回调！
            }
        } catch (Exception e) {
            logger.error("QINIU_CALLBACK_ERROR", e);
        }
        ret.put("success", flag);
        ret.put("key", key);
        logger.info("Server2client_result:{}", ret);
        return JsonUtil.writeToJson(ret);
    }
}