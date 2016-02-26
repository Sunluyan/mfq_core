package com.mfq.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mfq.service.sms.SMSService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mfq.annotation.LoginRequired;
import com.mfq.constants.ErrorCodes;
import com.mfq.helper.MobileHelper;
import com.mfq.helper.SignHelper;
import com.mfq.service.user.UserQuotaService;
import com.mfq.utils.JsonUtil;

/**
 * 实名认证
 * 
 * @author hui
 *
 */
@Controller
@RequestMapping("/auth")
public class AuthenticationController {

    private static final Logger logger = LoggerFactory
            .getLogger(AuthenticationController.class);

    @Resource
    UserQuotaService userQuotaService;
    @Resource
    SMSService smsService;



    /**
     * 实名认证
     *
     * @throws Exception
     *             任何异常
     */
    @RequestMapping(value = { "/user/type",
            "/user/type/" }, method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    @LoginRequired
    public String userType(HttpServletRequest request,
                        HttpServletResponse response) {
        String ret = "";

        try {
            Map<String, Object> params = JsonUtil.readMapFromReq(request);
            if (!SignHelper.validateSign(params)) { // 签名验证失败
                ret = JsonUtil.toJson(ErrorCodes.SIGN_VALIDATE_ERROR, "签名验证失败",
                        null);
                logger.error("签名验证失败！ret={}", ret);
                return ret;
            }
            if (params.get("uid") == null || params.get("type") == null) {
                ret = JsonUtil.toJson(ErrorCodes.CORE_PARAM_UNLAWFUL, "参数不合法",
                        null);
                logger.error("参数不合法！ret={}", ret);
                return ret;
            }
            long uid = Long.parseLong(params.get("uid").toString());
            int type = Integer.parseInt(params.get("type").toString());
            ret = userQuotaService.updateUserType(uid, type);



        }catch (Exception e){
            logger.error("user type is {}",e);
        }
        return ret;




    }

    /**
     * 实名认证
     *
     * @throws Exception
     *             任何异常
     */
    @RequestMapping(value = { "/autonym",
            "/autonym/" }, method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    @LoginRequired
    public String apply(HttpServletRequest request,
            HttpServletResponse response) {
        String ret = "";
        try {
            Map<String, Object> params = JsonUtil.readMapFromReq(request);
            if (!SignHelper.validateSign(params)) { // 签名验证失败
                ret = JsonUtil.toJson(ErrorCodes.SIGN_VALIDATE_ERROR, "签名验证失败",
                        null);
                logger.error("签名验证失败！ret={}", ret);
                return ret;
            }
            if (params.get("uid") == null || params.get("realname") == null || params.get("idcard") == null) {
                ret = JsonUtil.toJson(ErrorCodes.CORE_PARAM_UNLAWFUL, "参数不合法",
                        null);
                logger.error("参数不合法！ret={}", ret);
                return ret;
            }
            Long uid = Long.parseLong(params.get("uid").toString());
            String realname = (String) params.get("realname");
            String idCard = (String) params.get("idcard");
            int gender = Integer.parseInt(params.get("gender").toString());
            String origin = params.get("origin").toString();
            String location = params.get("location").toString();

            int userType = 0;
            if (params.get("user_type")!=null) {
                userType = Integer.parseInt(params.get("user_type").toString());
            }
            
            Object blackboxObj = params.get("blackbox").toString();
            String blackbox = null;
            if(blackboxObj != null){
            	blackbox = blackboxObj.toString();      	
            }
            String mobileType = MobileHelper.getMobileType(request);
            ret = userQuotaService.applyAdultInterView(uid, userType,realname, idCard, gender, origin, location,mobileType,blackbox);

        } catch (Exception e) {
            logger.error("Exception InterView apply!", e);
            ret = JsonUtil.toJson(ErrorCodes.CORE_ERROR, "系统异常", null);
        }
        logger.info("audllt_EndRet={}", ret);
        return ret;
    }
//    4006008500
    
    @RequestMapping(value = { "/adult/info",
    "/adult/info/" }, method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	@LoginRequired
	public String adultInfo(HttpServletRequest request) {
        String ret = "";
        try {
            Map<String, Object> params = JsonUtil.readMapFromReq(request);
            if (!SignHelper.validateSign(params)) { // 签名验证失败
                ret = JsonUtil.toJson(ErrorCodes.SIGN_VALIDATE_ERROR, "签名验证失败",null);
                logger.error("签名验证失败！ret={}", ret);
                return ret;
            }
            if (params.get("uid") == null) {
                ret = JsonUtil.toJson(ErrorCodes.CORE_PARAM_UNLAWFUL, "参数不合法",
                        null);
                logger.error("参数不合法！ret={}", ret);
                return ret;
            }
            Long uid = Long.parseLong(params.get("uid").toString());
            String company = (String) params.get("company");
            String position = (String) params.get("position");  //职位
            String department = params.get("department").toString();
            String salary = params.get("salary").toString();
            String social_insurance = params.get("social_insurance").toString();  //社保账户
            String work_years = params.get("work_years").toString();  //工作年限

            logger.info(" adult inf is {}|{}|{}|{}|{}|{}|{}", uid ,company, position, department, salary, social_insurance, work_years);
            
            ret = userQuotaService.saveAdultInfo(uid, company, position, department, salary, social_insurance, work_years);

        } catch (Exception e) {
            logger.error("Exception InterView apply!", e);
            ret = JsonUtil.toJson(ErrorCodes.CORE_ERROR, "系统异常", null);
        }

        logger.info("AUDLT_SAVE_INFO Ret={}", ret);
        return ret;
    }
    
    
    @RequestMapping(value = { "/student/info",
    "/student/info/" }, method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	@LoginRequired
	public String studentInfo(HttpServletRequest request,
	    HttpServletResponse response) {
        String ret = "";
        try {
            Map<String, Object> params = JsonUtil.readMapFromReq(request);
            if (!SignHelper.validateSign(params)) { // 签名验证失败
                ret = JsonUtil.toJson(ErrorCodes.SIGN_VALIDATE_ERROR, "签名验证失败",null);
                logger.error("签名验证失败！ret={}", ret);
                return ret;
            }
            if (params.get("uid") == null) {
                ret = JsonUtil.toJson(ErrorCodes.CORE_PARAM_UNLAWFUL, "参数不合法",
                        null);
                logger.error("参数不合法！ret={}", ret);
                return ret;
            }
            Long uid = Long.parseLong(params.get("uid").toString());
            String student_id = (String) params.get("student_id");
            String school = (String) params.get("school");  //职位
            String school_location = params.get("school_location").toString();  //学校地址
            long grade = Long.parseLong(params.get("grade").toString());
            String school_level = params.get("school_level").toString();  //层次
            String faculty = params.get("faculty").toString();  //系名称
            String speciality = params.get("speciality").toString(); //专业
            int scholastic_years = Integer.parseInt(params.get("scholastic_years").toString());
            
            ret = userQuotaService.saveStudentInfo(uid, student_id, school, school_location, grade, school_level, faculty,
            		speciality, scholastic_years);

        } catch (Exception e) {
            logger.error("Exception InterView apply!", e);
            ret = JsonUtil.toJson(ErrorCodes.CORE_ERROR, "系统异常", null);
        }
        logger.info("AUDLT_SAVE_INFO Ret={}", ret);
        return ret;
    }



    @RequestMapping(value = { "/sms",
            "/sms/" }, method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    @LoginRequired
    public String sma(HttpServletRequest request) {
        String ret="";
        try {
            Map<String, Object> params = JsonUtil.readMapFromReq(request);
            if (!SignHelper.validateSign(params)) { // 签名验证失败
                ret = JsonUtil.toJson(ErrorCodes.SIGN_VALIDATE_ERROR, "签名验证失败",null);
                logger.error("签名验证失败！ret={}", ret);
                return ret;
            }
            String mobile=params.get("mobile").toString();
            String msg=params.get("msg").toString();
            smsService.sendSms(mobile,msg);
            return "success";

        }catch (Exception e){
            logger.error("sms  is error {}",e);
        }


        return "fail";
    }
	

}