package com.mfq.controller.wechat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.http.ParseException;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Maps;
import com.mfq.bean.CodeMsg;
import com.mfq.bean.Vcode;
import com.mfq.bean.user.User;
import com.mfq.constants.Constants;
import com.mfq.constants.ErrorCodes;
import com.mfq.helper.SignHelper;
import com.mfq.service.VcodeService;
import com.mfq.service.sms.SMSService;
import com.mfq.service.user.UserService;
import com.mfq.service.wechat.WeChatService;
import com.mfq.utils.DateUtil;
import com.mfq.utils.JsonUtil;
import com.mfq.utils.MessageDigestUtils;
import com.mfq.utils.MessageUtil;
import com.mfq.utils.RequestUtils;
import com.mfq.utils.VerifyUtils;

@Controller
@RequestMapping("/wechat")
public class WeChatController {

    private static final Logger logger = LoggerFactory
            .getLogger(WeChatController.class);

    @Resource
    WeChatService weChatService;

    @Resource
    UserService userService;

    @Resource
    VcodeService vcodeService;

    @Resource
    SMSService smsService;

    /**
     * 微信验证接口
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = { "/" },method = { RequestMethod.GET})
    @ResponseBody
    public String weixin(HttpServletRequest request,
            HttpServletResponse response) {

        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");

        try {
            String[] paras = new String[] { Constants.WECHAT_TOKEN, timestamp,
                    nonce };
            Arrays.sort(paras);
            StringBuilder s = new StringBuilder();
            s.append(paras[0]);
            s.append(paras[1]);
            s.append(paras[2]);

            String tmpStr = MessageDigestUtils.sha1(s.toString());

            logger.info("tmpStr   " + tmpStr);
            logger.info("signature   " + signature);
            if (tmpStr.equals(signature)) {
                return echostr;
            } else {
                return null;
            }
        } catch (Exception e) {
            logger.error("微信验证错误!", e);
            return null;
        }
    }
    
    
    /**
     * 消息处理
     *
     * @param request
     *            请求
     * @param response
     *            响应
     * @throws Exception
     *             任何异常
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public String doMessage(HttpServletRequest request,
            HttpServletResponse response) {
    	PrintWriter out = null;
		try {
			
			request.setCharacterEncoding("UTF-8");
	    	response.setCharacterEncoding("UTF-8");
	    	out = response.getWriter();
			
			Map<String, String> map = MessageUtil.xmlToMap(request);
			String fromUserName = map.get("FromUserName");
			String toUserName = map.get("ToUserName");
			String msgType = map.get("MsgType");
			String content = map.get("Content");
			logger.info("微信消息请求 content = {}| fromUserName = {}| toUserName = {}| msgType = {}",content, fromUserName, toUserName, msgType );
			String message = "success";
			if(MessageUtil.MESSAGE_IMAGE.equals(msgType)){
				long result = weChatService.saveWeChatMsg(map);
				if(result > 0){
					message = MessageUtil.initText(toUserName, fromUserName, "消息已提交。。 ");
				}else{
					message = MessageUtil.initText(toUserName, fromUserName, "暂时无法提供服务，请稍后再试。");
				}
			}else if(MessageUtil.MESSAGE_TEXT.equals(msgType)){
				weChatService.saveWeChatMsg(map);
				if(content.startsWith("产品")){
					message = MessageUtil.initText(toUserName, fromUserName, "欢迎关注美分期产品。");
				}
			}else{
				//待处理。。。
				message = MessageUtil.initText(toUserName, fromUserName, "欢迎关注美分期。。。 ");
			}
			
			logger.info("wechat ret message {}",message);
			out.print(message);
		} catch (DocumentException e) {
			logger.info("wechat ret message DocumentException {}",e);
		}catch (Exception e) {
			logger.info("wechat ret message Exception {}",e);
		}finally{
			out.close();
		}
    	return null;
    }
    
    /**
     * 发送消息
     *
     * @param request
     *            请求
     * @param response
     *            响应
     * @throws Exception
     *             任何异常
     */
    @RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
    @ResponseBody
    public String sendMessage(HttpServletRequest request,
            HttpServletResponse response) {
        String ret = "";
        try{
            Map<String, Object> params = JsonUtil.readMapFromReq(request);
            if (!SignHelper.validateSign(params)) { // 签名验证失败
                ret = JsonUtil.toJson(ErrorCodes.SIGN_VALIDATE_ERROR, "签名验证失败",
                        null);
                logger.error("签名验证失败！ret={}", ret);
                return ret;
            }
            if(params.get("msgType") == null && params.get("msg") == null && params.get("user") == null){
                ret = JsonUtil.toJson(ErrorCodes.CORE_PARAM_UNLAWFUL, "参数不合法", null);
                logger.error("参数不合法！ret={}", ret);
                return ret;
            }
            String msgType = params.get("msgType").toString();
            String msg = params.get("msg").toString();
            String user = params.get("user").toString();
            ret = weChatService.sendAsyncMessage(user, msgType, msg);
        }catch(Exception e){
        	logger.info("WeChat SendMsg Exception: {}",e);
        }
    	return ret;
    }
    
    /**
     * 查询用户
     *
     * @param request
     *            请求
     * @param response
     *            响应
     * @throws Exception
     *             任何异常
     */
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @ResponseBody
    public String findUser(HttpServletRequest request,
            HttpServletResponse response) {
    	String ret = "";
    	try{
	    	Map<String, Object> params = JsonUtil.readMapFromReq(request);
	        if (!SignHelper.validateSign(params)) { // 签名验证失败
	            ret = JsonUtil.toJson(ErrorCodes.SIGN_VALIDATE_ERROR, "签名验证失败",
	                    null);
	            logger.error("签名验证失败！ret={}", ret);
	            return ret;
	        }
	        if(params.get("openid") == null){
	            ret = JsonUtil.toJson(ErrorCodes.CORE_PARAM_UNLAWFUL, "参数不合法", null);
	            logger.error("参数不合法！ret={}", ret);
	            return ret;
	        }
	    	String openId = params.get("openid").toString();
	    	ret = weChatService.queryUser(openId);
    	}catch(ParseException e){
    		
    	}catch(IOException e){
    		
    	}catch(Exception e){
    		
    	}
    	return null;
    }
    
    /**
     * 获取媒体
     *
     * @param request
     *            请求
     * @param response
     *            响应
     * @throws IOException 
     * @throws Exception
     *             任何异常
     */
    @RequestMapping(value = "/image", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String findMedia(HttpServletRequest request,
            HttpServletResponse response) throws IOException {
    	String ret = "";
    	InputStream in = null;
    	try{
    		Map<String, Object> params = JsonUtil.readMapFromReq(request);
	        if (!SignHelper.validateSign(params)) { // 签名验证失败
	            ret = JsonUtil.toJson(ErrorCodes.SIGN_VALIDATE_ERROR, "签名验证失败",
	                    null);
	            logger.error("签名验证失败！ret={}", ret);
	            return ret;
	        }
    		if(params.get("mediaid") == null){
	            ret = JsonUtil.toJson(ErrorCodes.CORE_PARAM_UNLAWFUL, "参数不合法", null);
	            logger.error("参数不合法！ret={}", ret);
	            return ret;
	        }

	        String media = params.get("mediaid").toString();
	        
	        in = weChatService.queryMedia(media);
	        OutputStream out = response.getOutputStream(); 
	        
	        byte buffer[] = new byte[1024];  
            int len = 0;  
            while((len=in.read(buffer))>0){  
                out.write(buffer,0,len);  
            }
	        
    	}catch(Exception e){
    		e.printStackTrace();
    	}finally{  
            if(in!=null){  
                in.close();     //关闭流  
            }  
        }  
    	
    	return ret;
    }

    /**
     * 注册（微信） 跳转
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = { "/register" }, method = { RequestMethod.GET })
    public ModelAndView register(HttpServletRequest request,
            HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("title", "会员注册");
        try {
            // 随机产生用户id(用于验证请求）
            String uid = UUID.randomUUID().toString();
            model.put("UID", uid);
            // 保存至Session
            request.getSession().setAttribute("uid", uid);
        } catch (Exception e) {
            logger.error("WeiXin_REGISTER_Exception", e);
        }
        return new ModelAndView("/wechat/register", model);
    }

    /**
     * 微信页面会员注册提交处理
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = { "/register" }, method = { RequestMethod.POST })
    public ModelAndView toRegister(HttpServletRequest request,
            HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();

        String passwd = request.getParameter("passwd");
        String mobile = request.getParameter("phone");
        String vcode = request.getParameter("vcode");
        String sign = request.getParameter("uid");
        
        logger.info("REGISTER_PARAM:{}|{}|{}|{}", passwd, mobile, vcode, sign);

        if (StringUtils.isBlank(passwd) || StringUtils.isBlank(mobile)) {
            model.put("msg", "参数错误 ！！");
            return new ModelAndView("/activity/s/reg_success", model);
        }

        // 验证请求
        if (!weChatService.checkRequestUser(request, sign)) {
            model.put("msg", "验证错误！！");
            return new ModelAndView("/activity/s/reg_success", model);
        }

        // 验证 手机验证码
        CodeMsg cm = vcodeService.validate(mobile, vcode);
        if (cm.getCode() != 0) {
            // 验证不成功
            model.put("msg", "手机验证不正确！！");
            return new ModelAndView("/activity/s/reg_success", model);
        }

        try {
            String result = weChatService.registerWeChatUser(passwd, mobile,
                    vcode);
            Map<String, Object> ResultMap = JsonUtil.getMapFromJsonStr(result);
            int code = Integer.parseInt(ResultMap.get("code").toString());
            model.put("code", code);
            if (code == 0) {
                logger.info("user {} login meifenqi fronted system.....", passwd);
                model.put("msg", "注册成功");
                return new ModelAndView("/activity/s/reg_success", model);
            } else if(code == 1001){
            	logger.error("Exception WeChatRegister Progress!",
                        (String) ResultMap.get("msg"));
                model.put("msg", " 密码格式错误");
                return new ModelAndView("/activity/s/reg_success", model);
            }else if(code == 1004){
            	logger.error("Exception WeChatRegister Progress!",
                        (String) ResultMap.get("msg"));
                model.put("msg", " 手机号非法");
                return new ModelAndView("/activity/s/reg_success", model);
            }else if(code == 1104){
            	logger.error("Exception WeChatRegister Progress!",
                        (String) ResultMap.get("msg"));
                model.put("msg", "此手机号已注册");
                
                return new ModelAndView("/activity/s/reg_success", model);
            }
            else {
                logger.error("Exception WeChatRegister Progress!",
                        (String) ResultMap.get("msg"));
                model.put("msg", " 注册失败");
                return new ModelAndView("/activity/s/reg_success", model);
            }
        } catch (Exception e) {
            logger.error("Exception WeChatRegister Progress!", e);
            model.put("msg", "系统错误,请稍后再试");
            return new ModelAndView("/activity/s/reg_success", model);
        }
        
    }


    /**
     * 微信注册手机号
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/mobile/check/", method = { RequestMethod.GET,
            RequestMethod.POST })
    @ResponseBody
    public String checkMobile(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        try {
            String mobile = request.getParameter("phone");
            String sign = request.getParameter("u");

            if (StringUtils.isBlank(sign)
                    && !VerifyUtils.verifyMobile(mobile)) {
                return JsonUtil.toJson(ErrorCodes.CORE_PARAM_NULL, "参数错误",
                        null);
            }
            if (!weChatService.checkRequestUser(request, sign)) { // 验证失败
                return JsonUtil.toJson(ErrorCodes.USER_WRONG_PASS, "验证失败",
                        null);
            } else if (!VerifyUtils.verifyMobile(mobile)) {
                return JsonUtil.toJson(ErrorCodes.USER_WRONG_PASS, "手机号格式不正确",
                        null);
            } else {
                User user = userService.queryUserByMobile(mobile);
                if (user.getUid() > 0) {
                    return JsonUtil.toJson(ErrorCodes.USER_MOBILE_USED, "该用户已注册",
                            null);
                } else {
                    return JsonUtil.successResultJson();
                }
            }
        } catch (Exception e) {
            logger.error("Exception Check Mobile Usage!", e);
            return JsonUtil.toJson(ErrorCodes.CORE_PARAM_NULL, "请稍后再试", null);
        }
    }

    /**
     * 发送短信确认码(微信页面 )
     *
     * @param request
     *            请求
     * @param response
     *            响应
     * @throws Exception
     *             任何异常
     */
    @RequestMapping(value = "/mcode/", method = { RequestMethod.GET,
            RequestMethod.POST })
    @ResponseBody
    public String wxSend(HttpServletRequest request,
            HttpServletResponse response) {
        String ret = "";
        try {
            String mobile = request.getParameter("mobile");
            String sign = request.getParameter("u");
            String ip = RequestUtils.getString(request, "ip",
                    request.getRemoteAddr());

            if (!weChatService.checkRequestUser(request, sign)) { // 验证失败
                ret = JsonUtil.toJson(ErrorCodes.SIGN_VALIDATE_ERROR, "用户验证失败",
                        null);
                logger.warn("SEND_VCODE_RESULT:{}", ret);
                return ret;
            }
            if(userService.queryUserByMobile(mobile).getUid() > 0){ // 若用户已注册
                ret = JsonUtil.toJson(ErrorCodes.USER_MOBILE_USED, "用户已注册",
                        null);
                logger.warn("SEND_VCODE_RESULT:{}", ret);
                return ret;
            }
            Vcode vcode = vcodeService.queryVcode(mobile);
            if (vcode.getId() > 0 && DateUtil.getDayBetweenD(new Date(),
                    vcode.getResendAt()) > 0) {
                ret = JsonUtil.toJson(ErrorCodes.USER_VCODE_TOOFAST, "请求太频繁",
                        null);
                logger.warn("SEND_VCODE_RESULT:{}", ret);
                return ret;
            }
            Date expireAt = new Date(System.currentTimeMillis()
                    + Constants.MOBILE_EXPIRE_DURATION);
            Date resendAt = new Date(
                    System.currentTimeMillis() + Constants.Msm_SEND_DURATION);
            vcode = vcodeService.applyVcode(mobile, expireAt, resendAt);

            String msg = vcode.getVcode() + ",30";
            logger.info("msg_full_log: {}|{}|{}|{}", ip,
                    request.getHeader("Referer"), mobile, msg);
            if (vcode.getVcode() == null) {
                return JsonUtil.toJson(ErrorCodes.USER_VCODE_TOOFAST, "请稍后再试",
                        null);
            }
            smsService.sendCodeSms(mobile, msg);
            Map<String, Object> data = Maps.newHashMap();
            long reSendSecond = (vcode.getResendAt().getTime()
                    - System.currentTimeMillis()) / 1000;
            data.put("reSendSecond", reSendSecond); // 兼容web的响应字段名
            data.put("seqId", vcode.getSeqId());
            ret = JsonUtil.successResultJson(data);
        } catch (Exception e) {
            logger.error("Exception Send Vcode!", e);
            ret = JsonUtil.toJson(ErrorCodes.CORE_ERROR, "请稍后再试", null);
            logger.warn("SEND_VCODE_RESULT:{}", ret);
        }
        return ret;
    }
    
    
    @RequestMapping(value = { "/reg_ret" }, method = { RequestMethod.GET })
    public ModelAndView registerRet(HttpServletRequest request,
            HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();
        String code = request.getParameter("code");
        String msg = request.getParameter("msg");
        model.put("code", code);
        model.put("msg", msg);
        return new ModelAndView("/activity/s/reg_success", model);
    }

    /**
     * 验证短信验证码(微信页面 )
     *
     * @param request
     *            请求
     * @param response
     *            响应
     * @throws Exception
     *             任何异常
     */
    @RequestMapping(value = "/check/", method = { RequestMethod.GET,
            RequestMethod.POST })
    @ResponseBody
    public String checkMobileCode(HttpServletRequest request,
            HttpServletResponse response) {

        try {
            String mobile = request.getParameter("mobile");
            String vcode = request.getParameter("vcode");
            String sign = request.getParameter("u");

            if (StringUtils.isBlank(mobile) || StringUtils.isBlank(vcode)
                    || StringUtils.isBlank(sign)) {
                return JsonUtil.toJson(ErrorCodes.CORE_ERROR, "参数错误 ", null);
            }
            if (!weChatService.checkRequestUser(request, sign)) {
                return JsonUtil.toJson(ErrorCodes.CORE_ERROR, "验证错误 ", null);
            }
            CodeMsg cm = vcodeService.validate(mobile, vcode);
            if (cm.getCode() == 0) {
                // 验证成功
                return JsonUtil.toJson(0, "验证成功", null);
            }
            return JsonUtil.toJson(cm.getCode(), cm.getMsg(), null);
        } catch (Exception e) {
            logger.error("Exception WeChatSend Vcode!", e);
            return JsonUtil.toJson(ErrorCodes.CORE_ERROR, "系统异常", null);
        }
    }
    
    
    /**
     * 扫描二维码跳转
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = { "/qr_coupons","/qr_coupons/"}, method = { RequestMethod.GET })
    public ModelAndView ScanQRcode(HttpServletRequest request,
            HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("title", "二维码");
        try {
            // 随机产生用户id(用于验证请求）
            String token = UUID.randomUUID().toString();
            model.put("token", token);
            // 保存至Session
            request.getSession().setAttribute("uid", token);

        } catch (Exception e) {
            logger.error("ScanQRCode_Exception", e);
            model.put("msg", "系统错误，请稍后重试！！");
            return new ModelAndView("/wechat/message", model);
        }
        return new ModelAndView("/wechat/QRCode", model);
    }
    
    /**
     * 扫描二维码处理
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = { "/qr_coupons","/qr_coupons/"}, method = { RequestMethod.POST })
    public ModelAndView doScanQRcode(HttpServletRequest request,
            HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();

        String name = request.getParameter("name");
        String mobile = request.getParameter("phone");
        String vcode = request.getParameter("vcode");
        String sign = request.getParameter("uid");
        String type = (String)request.getSession().getAttribute("wc");
        String wishs = request.getParameter("check");  //是否意愿微整形
        int wish=-1;
        if("on".equals(wishs)){
        	wish = 1;
        }
        BigDecimal amount = (BigDecimal)request.getSession().getAttribute("amount");
        logger.info("REGISTER_PARAM:{}|{}|{}|{}", name, mobile, vcode, sign);

        if (StringUtils.isBlank(mobile)) {
            model.put("msg", "参数错误 ！！");
            return new ModelAndView("/wechat/message", model);
        }

        // 验证请求
        if (!weChatService.checkRequestUser(request, sign)) {
            model.put("msg", "验证错误！！");
            return new ModelAndView("/wechat/message", model);
        }

        // 验证 手机验证码
        CodeMsg cm = vcodeService.validate(mobile, vcode);
        if (cm.getCode() != 0) {
            // 验证不成功
            model.put("msg", "手机验证不正确！！");
            return new ModelAndView("/wechat/message", model);
        }
        
        try {
            String result = weChatService.regAndGiveMoney(name, mobile, vcode, type, amount, wish);
            Map<String, Object> ResultMap = JsonUtil.getMapFromJsonStr(result);
            int code = Integer.parseInt(ResultMap.get("code").toString());
            if (code == 0) {
                logger.info("user {} login meifenqi fronted system.....", name);
                model.put("msg", "恭喜您，获取美分期平台500元抵用劵！！");
                return new ModelAndView("/wechat/message", model);
            } else {
                logger.error("Exception WeChatRegister Progress!",
                        (String) ResultMap.get("msg"));
                model.put("msg", "失败！！");
                return new ModelAndView("/wechat/message", model);
            }
        } catch (Exception e) {
            logger.error("Exception WeChatRegister Progress!", e);
            model.put("msg", "系统错误,请稍后再试");
            return new ModelAndView("/wechat/message", model);
        }
    }
    
    /**
     * 发送短信确认码(微信页面 )
     *
     * @param request
     *            请求
     * @param response
     *            响应
     * @throws Exception
     *             任何异常
     */
    @RequestMapping(value = "/sendcode/", method = { RequestMethod.GET,
            RequestMethod.POST })
    @ResponseBody
    public String SendMobileCode(HttpServletRequest request,
            HttpServletResponse response) {
        String ret = "";
        try {
            String mobile = request.getParameter("mobile");
            String sign = request.getParameter("u");
            String ip = RequestUtils.getString(request, "ip",
                    request.getRemoteAddr());
            String notNeedUid = request.getParameter("qewrasdfzxcv");

            if (notNeedUid == null && !weChatService.checkRequestUser(request, sign)) { // 验证失败
                ret = JsonUtil.toJson(ErrorCodes.SIGN_VALIDATE_ERROR, "用户验证失败",
                        null);
                logger.warn("SEND_VCODE_RESULT:{}", ret);
                return ret;
            }
            if(mobile == null || mobile.length()<11 ){
                ret = JsonUtil.toJson(ErrorCodes.SIGN_VALIDATE_ERROR, "手机号不正确",
                        null);
                logger.warn("SEND_VCODE_RESULT:{}", ret);
                return ret;
            }
            Vcode vcode = vcodeService.queryVcode(mobile);
            if (vcode.getId() > 0 && DateUtil.getDayBetweenD(new Date(),
                    vcode.getResendAt()) > 0) {
                ret = JsonUtil.toJson(ErrorCodes.USER_VCODE_TOOFAST, "请求太频繁",
                        null);
                logger.warn("SEND_VCODE_RESULT:{}", ret);
                return ret;
            }
            Date expireAt = new Date(System.currentTimeMillis()
                    + Constants.MOBILE_EXPIRE_DURATION);
            Date resendAt = new Date(
                    System.currentTimeMillis() + Constants.Msm_SEND_DURATION);
            vcode = vcodeService.applyVcode(mobile, expireAt, resendAt);

            String msg = vcode.getVcode() + ",30";
            System.out.println(msg);
            logger.info("msg_full_log: {}|{}|{}|{}", ip,
                    request.getHeader("Referer"), mobile, msg);
            if (vcode.getVcode() == null) {
                return JsonUtil.toJson(ErrorCodes.USER_VCODE_TOOFAST, "请稍后再试",
                        null);
            }
            smsService.sendCodeSms(mobile, msg);
            Map<String, Object> data = Maps.newHashMap();
            long reSendSecond = (vcode.getResendAt().getTime()
                    - System.currentTimeMillis()) / 1000;
            data.put("reSendSecond", reSendSecond); // 兼容web的响应字段名
            data.put("seqId", vcode.getSeqId());
            return JsonUtil.successResultJson(data);
        } catch (Exception e) {
            logger.error("Exception Send Vcode!", e);
            ret = JsonUtil.toJson(ErrorCodes.CORE_ERROR, "请稍后再试", null);
            logger.warn("SEND_VCODE_RESULT:{}", ret);
            return ret;
        }
    }
    
    
    @RequestMapping(value = {"/js/token/","/js/token"}, method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String JsApiToken(HttpServletRequest request) {
        String ret = "";
        try {
        	String url = StringUtils.stripToEmpty(request.getParameter("url"));
            ret = weChatService.generateJsApiToken(url);
            logger.info("ret:{}",ret);
        } catch (Exception e) {
            logger.info("gen js api token {}", e);
        }

        return ret;
    }

    
}
