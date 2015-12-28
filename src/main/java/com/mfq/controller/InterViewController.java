package com.mfq.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mfq.annotation.LoginRequired;
import com.mfq.constants.ErrorCodes;
import com.mfq.constants.Grade;
import com.mfq.helper.MobileHelper;
import com.mfq.helper.SignHelper;
import com.mfq.service.user.UserQuotaService;
import com.mfq.utils.JsonUtil;

/**
 * 面签接口
 * 
 * @author hui
 *
 */
@Controller
@RequestMapping("/interview")
public class InterViewController {

    private static final Logger logger = LoggerFactory
            .getLogger(InterViewController.class);

    @Resource
    UserQuotaService userQuotaService;

    /**
     * 申请面签
     *
     * @throws Exception
     *             任何异常
     */
    @RequestMapping(value = { "/apply",
            "/apply/" }, method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    @LoginRequired
    public String apply(HttpServletRequest request,
            HttpServletResponse response) {
        String ret = "";
        try {
            Map<String, Object> params = JsonUtil.readMapFromReq(request);
            if (!SignHelper.validateSign(params)) { // 签名验证失败
                ret = JsonUtil.toJson(ErrorCodes.SIGN_VALIDATE_ERROR, "签名验证失败", null);
                logger.error("签名验证失败！ret={}", ret);
                return ret;
            }
            if (params.get("uid") == null) {
                ret = JsonUtil.toJson(ErrorCodes.CORE_PARAM_UNLAWFUL, "参数不合法",null);
                logger.error("参数不合法！ret={}", ret);
                return ret;
            }
            Long uid = Long.parseLong(params.get("uid").toString());
            String realname = (String) params.get("realname");
            String idCard = (String) params.get("idcard");
            int gender = Integer.parseInt(params.get("gender").toString());
            String origin = params.get("origin").toString();
            String location = params.get("location").toString();
            Object blackboxObj = params.get("blackbox").toString();
            String blackbox = null;
            if(blackboxObj != null){
            	blackbox = blackboxObj.toString();      	
            }
            String mobileType = MobileHelper.getMobileType(request);
            ret = userQuotaService.applyAdultInterView(uid, realname, idCard, gender, origin, location,mobileType,blackbox);
//            String school = (String) params.get("school");
//            String contact = (String) params.get("contact");
//            ret = userQuotaService.applyInterView(uid, realname, idCard, school,
//                    contact);
        } catch (Exception e) {
            logger.error("Exception InterView apply!", e);
            ret = JsonUtil.toJson(ErrorCodes.CORE_ERROR, "系统异常", null);
        }
        logger.info("InterViewApply_EndRet={}", ret);
        return ret;
    }

    /**
     * 申请面签
     *
     * @throws Exception
     *             任何异常
     */
    @RequestMapping(value = { "/audit",
            "/audit/" }, method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    @LoginRequired
    public String audit(HttpServletRequest request,
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
            if (params.get("uid") == null || params.get("status") == null) {
                ret = JsonUtil.toJson(ErrorCodes.CORE_PARAM_UNLAWFUL, "参数不合法",
                        null);
                logger.error("参数不合法！ret={}", ret);
                return ret;
            }
            Long uid = Long.parseLong(params.get("uid").toString());
            Integer auth_status = Integer
                    .parseInt(params.get("status").toString());
            String remark = (String) params.get("remark");

            Grade grade = null;
            if (params.get("grade") != null) {
                grade = Grade.fromId((Integer) params.get("grade"));
            }
            String classes = null;
            if (params.get("classes") != null) {
                classes = (String) params.get("classes");
            }
            ret = userQuotaService.auditUserQuota(uid, grade, classes, remark,
                    auth_status);
        } catch (Exception e) {
            logger.error("Exception InterView audit!", e);
            ret = JsonUtil.toJson(ErrorCodes.CORE_ERROR, "系统异常", null);
        }
        logger.info("InterViewAudit_EndRet={}", ret);
        return ret;
    }

    /**
     * 修改面签资料
     *
     * @throws Exception
     *             任何异常
     */
    @RequestMapping(value = { "/update",
            "/update/" }, method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    @LoginRequired
    public String update(HttpServletRequest request,
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
            if (params.get("uid") == null) {
                ret = JsonUtil.toJson(ErrorCodes.CORE_PARAM_UNLAWFUL, "参数不合法",
                        null);
                logger.error("参数不合法！ret={}", ret);
                return ret;
            }
            Long uid = Long.parseLong(params.get("uid").toString());
            String realname = (String) params.get("realname");
            String idCard = (String) params.get("idcard");
            String school = (String) params.get("school");
            String contact = (String) params.get("contact");
            Grade grade = Grade.fromId((Integer) params.get("grade"));
            String classes = (String) params.get("classes");
            String remark = (String) params.get("remark");
            
            ret = userQuotaService.updateUserInfoTask(uid, realname, idCard, school,
                    contact, grade, classes, remark);
            
//            ret = userQuotaService.updateUserInfo(uid, realname, idCard, school,
//                    contact, grade, classes, remark);
        } catch (Exception e) {
            logger.error("Exception InterView update!", e);
            ret = JsonUtil.toJson(ErrorCodes.CORE_ERROR, "系统异常", null);
        }
        logger.info("InterViewUpdate_EndRet={}", ret);
        return ret;
    }

}