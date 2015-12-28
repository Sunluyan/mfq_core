package com.mfq.controller;

import java.util.List;
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
import com.mfq.bean.app.PolicyListForApp;
import com.mfq.constants.ErrorCodes;
import com.mfq.helper.SignHelper;
import com.mfq.service.PolicyService;
import com.mfq.utils.JsonUtil;

@Controller
@RequestMapping("/policy")
public class PolicyController {

    private static final Logger logger = LoggerFactory.getLogger(PolicyController.class);
    
    @Resource
    PolicyService policyService;
    
    @RequestMapping(value = {"/list", "/list/"}, method = RequestMethod.POST)
    @ResponseBody
    @LoginRequired
    public String list(HttpServletRequest request, HttpServletResponse response) {
        String ret = "";
        try{
    		Map<String, Object> params = JsonUtil.readMapFromReq(request);
            if (!SignHelper.validateSign(params)) { // 签名验证失败
                return JsonUtil.toJson(ErrorCodes.SIGN_VALIDATE_ERROR, "签名验证失败",
                        null);
            }
            
            if (params.get("uid") == null) { // 参数异常
                return JsonUtil.toJson(ErrorCodes.CORE_PARAM_UNLAWFUL, "参数异常",
                        null);
            }
            
            long uid = Long.parseLong(params.get("uid").toString());

            List<PolicyListForApp> data=policyService.findByUid(uid);
            logger.info("POLICY LIST_Ret is:{}", ret);
            ret=JsonUtil.successResultJson(data);
        } catch (Exception e) {
            logger.error("Exception_FavoritesInfo_ Process!", e);
            ret = JsonUtil.toJson(ErrorCodes.CORE_ERROR, "系统异常", null);
        }
        logger.info("POLICY_LIST_RET = {}", ret);
        return ret;
    }
    
    
    
    @RequestMapping(value = {"/detail", "/detail/"}, method = RequestMethod.POST)
    @ResponseBody
    @LoginRequired
    public String detail(HttpServletRequest request, HttpServletResponse response) {
        String ret = "";
        try{
    		Map<String, Object> params = JsonUtil.readMapFromReq(request);
            if (!SignHelper.validateSign(params)) { // 签名验证失败
                return JsonUtil.toJson(ErrorCodes.SIGN_VALIDATE_ERROR, "签名验证失败",
                        null);
            }
            
            if (params.get("uid") == null) { // 参数异常
                return JsonUtil.toJson(ErrorCodes.CORE_PARAM_UNLAWFUL, "参数异常",
                        null);
            }
            
            long uid = Long.parseLong(params.get("uid").toString());
            String orderNo = params.get("order_no").toString();
            
            PolicyListForApp data=policyService.findByOrder(uid, orderNo);
            logger.info("POLICY LIST_Ret is:{}", ret);
            ret=JsonUtil.successResultJson(data);
        } catch (Exception e) {
            logger.error("Exception_FavoritesInfo_ Process!", e);
            ret = JsonUtil.toJson(ErrorCodes.CORE_ERROR, "系统异常", null);
        }
        logger.info("POLICY_DETAIL_RET = {}", ret);
        return ret;
    }
    
    /**
     * 诉讼申请
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = {"/lawsuit/apply", "/lawsuit/apply/"}, method = RequestMethod.POST)
    @ResponseBody
    @LoginRequired
    public String applyLawsuit(HttpServletRequest request, HttpServletResponse response) {
        String ret = "";
        try{
    		Map<String, Object> params = JsonUtil.readMapFromReq(request);
            if (!SignHelper.validateSign(params)) { // 签名验证失败
                return JsonUtil.toJson(ErrorCodes.SIGN_VALIDATE_ERROR, "签名验证失败",
                        null);
            }
            if (params.get("uid") == null || params.get("order_no") == null) { // 参数异常
                return JsonUtil.toJson(ErrorCodes.CORE_PARAM_UNLAWFUL, "参数异常",
                        null);
            }
            
            long uid = Long.parseLong(params.get("uid").toString());
            String order_no = params.get("order_no").toString();
            String mobile = params.get("mobile").toString();
            ret = policyService.complaintPolicy(order_no);
            
            
        } catch (Exception e) {
            logger.error("Exception FavoitesInfo Process!", e);
            ret = JsonUtil.toJson(ErrorCodes.CORE_ERROR, "系统异常", null);
        }
        logger.info("POLICY_LAWSUIT_Ret is:{}", ret);
        return ret;
    }


    /**
     * 投保回调
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = {"/callback", "/callback/"}, method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    @LoginRequired
    public String insureCallback(HttpServletRequest request, HttpServletResponse response) {
        String ret ="";
        logger.info("悟空保 回调。。。。");
        try {
            ret=policyService.updatePolicyStatus(request);
        }catch (Exception e){
            logger.error("");
        }

        return ret;
    }

    
    /**
     * 用户须知
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = {"/mxa/contract", "/mxa/contract/"}, method = {RequestMethod.POST,RequestMethod.GET})
    public String contract(HttpServletRequest request, HttpServletResponse response) {
    	return "app/mxa/contract";
    }
    
    /**
     * 用户须知
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = {"/mxa/guide", "/mxa/guide/"}, method = {RequestMethod.POST,RequestMethod.GET})
    public String education(HttpServletRequest request, HttpServletResponse response) {
    	return "app/mxa/guide";
    }
    
}
