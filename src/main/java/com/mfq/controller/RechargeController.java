package com.mfq.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.mfq.annotation.LoginRequired;
import com.mfq.bean.PayRecord;
import com.mfq.constants.ErrorCodes;
import com.mfq.constants.OrderType;
import com.mfq.dataservice.context.UserIdHolder;
import com.mfq.helper.SignHelper;
import com.mfq.service.PayRecordService;
import com.mfq.utils.JsonUtil;

/**
 * 充值用的 充值与提现 (提现暂未实现）
 * 
 * @author xingyongshan
 *
 */
@Controller
@RequestMapping("/recharge")
public class RechargeController {

    private static final Logger logger = LoggerFactory
            .getLogger(RechargeController.class);

    @Resource
    PayRecordService payRecordService;

    /**
     * 充值－生成单号－仅用于充值
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = { "/deposit", "/deposit/" }, method = RequestMethod.POST)
    @ResponseBody
    @LoginRequired
    public String deposit(HttpServletRequest request,
            HttpServletResponse response) {
        String ret = "";
        try {
            Map<String, Object> params = JsonUtil.readMapFromReq(request);
            if (!SignHelper.validateSign(params)) { // 签名验证失败
                logger.warn("签名验证失败！params={}", params);
                return JsonUtil.toJson(ErrorCodes.SIGN_VALIDATE_ERROR, "签名验证失败",
                        null);
            }
            Long uid = Long.parseLong(params.get("uid").toString());
            if (UserIdHolder.getLongUid() != uid) {
                logger.warn("参数错误！UserIdHolder.getLongUid={}, Param uid={}",
                        UserIdHolder.getLongUid(), uid);
                return JsonUtil.toJson(ErrorCodes.CORE_PARAM_UNLAWFUL, "参数错误",
                        null);
            }
            String chargeNo = null;
            if(params.get("order_no") == null){
                chargeNo = payRecordService.makeChargeNo(uid);
            }else{ // 重复操作
                chargeNo = (String)params.get("order_no");
            }
            if (StringUtils.isNotBlank(chargeNo)) {
                Map<String, Object> data = Maps.newHashMap();
                data.put("chargeNo", chargeNo);
                data.put("activity", "");
                ret = JsonUtil.successResultJson(data);
            } else {
                ret = JsonUtil.toJson(ErrorCodes.CHARGE_DEPOSIT_SAVE2DB,
                        "充值记录构造失败", null);
            }
        } catch (Exception e) {
            logger.error("Exception OrderCreate Process!", e);
            ret = JsonUtil.toJson(ErrorCodes.CORE_ERROR, "系统异常", null);
        }
        logger.info("RECHARGE_DEPOSIT_RESULT:{}", ret);
        return ret;
    }

    /**
     * 充值－存钱－回调
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = { "/callback", "/callback/" }, method = RequestMethod.POST)
    @ResponseBody
    @LoginRequired
    public String callback(HttpServletRequest request,
            HttpServletResponse response) {
        // do nothing，不在这里实现任何功能！
        // 请参见paymentController中的callback
        return "";
    }

    /**
     * 提现－取钱
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = { "/draw", "/draw/" }, method = RequestMethod.POST)
    @ResponseBody
    @LoginRequired
    public String drawMoney(HttpServletRequest request,
            HttpServletResponse response) {
        return "";
    }

    /**
     * 充值记录
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = { "/history", "/history/" }, method = RequestMethod.POST)
    @ResponseBody
    @LoginRequired
    public String chargeHistory(HttpServletRequest request,
            HttpServletResponse response) {
        String ret = "";
        try {
            Map<String, Object> params = JsonUtil.readMapFromReq(request);
            if (!SignHelper.validateSign(params)) { // 签名验证失败
                logger.warn("签名验证失败！params={}", params);
                return JsonUtil.toJson(ErrorCodes.SIGN_VALIDATE_ERROR, "签名验证失败",
                        null);
            }
            Long uid = Long.parseLong(params.get("uid").toString());
            if (UserIdHolder.getLongUid() != uid) {
                logger.warn("参数错误！UserIdHolder.getLongUid={}, Param uid=",
                        UserIdHolder.getLongUid(), uid);
                return JsonUtil.toJson(ErrorCodes.CORE_PARAM_UNLAWFUL, "参数错误",
                        null);
            }
            OrderType orderType = OrderType.RECHARGE; //充值
            List<PayRecord> data = payRecordService.findUserHistory(orderType, uid);
            ret = JsonUtil.successResultJson(data);
        } catch (Exception e) {
            logger.error("Exception OrderCreate Process!", e);
            ret = JsonUtil.toJson(ErrorCodes.CORE_ERROR, "系统异常", null);
        }
        logger.info("RECHARGE_DEPOSIT_RESULT:{}", ret);
        return ret;
    }
}