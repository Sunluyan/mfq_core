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

import com.google.common.collect.Maps;
import com.mfq.annotation.LoginRequired;
import com.mfq.constants.ErrorCodes;
import com.mfq.helper.SignHelper;
import com.mfq.service.FeedbackService;
import com.mfq.utils.JsonUtil;

@Controller
@RequestMapping("/feedback")
public class FeedbackController {

    private static final Logger logger = LoggerFactory
            .getLogger(FeedbackController.class);

    @Resource
    FeedbackService feedbackService;

    @RequestMapping(value = { "/send", "/send/" }, method = RequestMethod.POST,
    		produces = "application/json;charset=utf-8")
    @ResponseBody
    @LoginRequired
    public String submitFeedback(HttpServletRequest request,
            HttpServletResponse response) {
        String ret = "";
        try {
        	Map<String, Object> params = JsonUtil.readMapFromReq(request);
            if (!SignHelper.validateSign(params)) { // 签名验证失败
                return JsonUtil.toJson(ErrorCodes.SIGN_VALIDATE_ERROR, "签名验证失败",
                        null);
            }
            if (params.get("uid") == null || params.get("content") == null) { // 参数异常
                return JsonUtil.toJson(ErrorCodes.CORE_PARAM_UNLAWFUL, "参数异常",
                        null);
            }
            long uid = Long.parseLong(params.get("uid").toString());
            String content = (String)params.get("content");
            long result=feedbackService.submitFeedback(uid, content);
            Map<String, Long> data = Maps.newHashMap();
            data.put("num", result);
            if(result > 0){
                ret=JsonUtil.successResultJson(data);
            }else{
            	ret=JsonUtil.toJson(ErrorCodes.FAIL, "添加失败", data);
            }
        } catch (Exception e) {
            logger.error("Exception Feedback_subFeedback Process!", e);
            ret = JsonUtil.toJson(ErrorCodes.CORE_ERROR, "系统异常", null);
        }
        logger.info("Feedback_submitFeedback_Ret is:{}", ret);
        return ret;
    }

}
