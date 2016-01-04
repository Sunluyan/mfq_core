package com.mfq.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mfq.bean.user.User;
import com.mfq.bean.user.UserQuota;
import com.mfq.service.user.UserQuotaService;
import com.qiniu.util.Json;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.mfq.annotation.LoginRequired;
import com.mfq.bean.CodeMsg;
import com.mfq.bean.Hospital;
import com.mfq.bean.Product;
import com.mfq.bean.ProductDetail;
import com.mfq.bean.app.ProductInfoItem;
import com.mfq.constants.ErrorCodes;
import com.mfq.constants.ProductType;
import com.mfq.dataservice.context.UserIdHolder;
import com.mfq.service.ActivityService;
import com.mfq.service.HospitalService;
import com.mfq.service.ProductService;
import com.mfq.service.VcodeService;
import com.mfq.service.sms.SMSService;
import com.mfq.service.user.UserService;
import com.mfq.service.wechat.WeChatService;
import com.mfq.utils.DateUtil;
import com.mfq.utils.FQUtil;
import com.mfq.utils.JsonUtil;

/**
 * 活动
 */
@Controller
@RequestMapping("/activity")
public class ActivityController {

    private static final Logger logger = LoggerFactory
            .getLogger(ActivityController.class);
    
    private String startDate = "2015-11-11 18:00:00";
    private String endDate = "2015-11-12 23:59:59";
    
    @Resource
    ActivityService activityService;
    @Resource
    ProductService productService;
    @Resource
    HospitalService hospitalService;
    @Resource
    WeChatService weChatService;
    @Resource
    UserService userService;
    @Resource
    VcodeService vcodeService;
    @Resource
    SMSService smsService;
    @Resource
    UserQuotaService userQuotaService;

    /**
     * 首页HTML5页面
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/index/","/index"})
    public ModelAndView index(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
    	
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("title", "首页");

        return new ModelAndView("/activity/s/index", model);
    }
    
    /**
     * 秒杀活动规则页面
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/list/","/list"})
    public ModelAndView rule(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("title", "双十一");
        return new ModelAndView("/activity/s/rule", model);
    }
    
    
    @RequestMapping(value = {"/product/special/","/product/special"})
    public ModelAndView register(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        Map<String, Object> model = new HashMap<String, Object>();
        try {
            String pid = request.getParameter("pid");
            if (StringUtils.isBlank(pid)) { // 参数异常
                model.put("error", "参数异常");
                return new ModelAndView("/app/product/detail", model);
            }
            long id = Long.parseLong(pid);
            productService.addViewNum(id);  //添加浏览数
            if (id > 0) {
                model.put("error", "");
                Product product = productService.findById(id);
                model.put("title", product.getName()+"-美分期");
                model.put("name", product.getName());
                model.put("viewNum", String.valueOf(product.getViewNum()));
                model.put("endTime", DateUtil.formatCZYYYYMMDD(product.getDateEnd()));
                model.put("price", String.valueOf(product.getPrice()));
                model.put("purl", product.getImg());
                model.put("type", product.getType().getId());
                model.put("market_price", String.valueOf(product.getMarketPrice()));
                model.put("online_price", String.valueOf(product.getOnlinePay()));
                
                ProductDetail productDetail = productService.findProductDetailByPid(id);
                model.put("consume_step", productDetail.getConsumeStep());  //消费流程
                model.put("reserve", productDetail.getReserve());  //如何预约
                model.put("special_note", productDetail.getSpecialNote());  //特殊说明
                model.put("body", productDetail.getBody()); //简介
                
                Map<String,Object> fq = FQUtil.fenqiMaxCompute(product.getPrice());// 分期得计算规则
                model.put("p_price", String.valueOf(fq.get("p_price")));
                model.put("p_num", String.valueOf(fq.get("p_num")));
                
                Hospital hospital = hospitalService.findById(product.getHospitalId());
                model.put("hospital_name", hospital.getName());
                model.put("hospital_addr", hospital.getAddress());
                model.put("hospital_img", hospital.getImg());
                
            } else {
                model.put("error", "product_not_exist");
            }
        } catch (Exception e) {
            logger.error("Exception ProductInfo Process!", e);
            model.put("error", "系统异常");
        }
        logger.info("Product_Info_Ret is:{}", model);
        return new ModelAndView("/activity/s/special_detail", model);
    }
    
    
    @RequestMapping(value = {"/doSeckilling/","/doSeckilling"})
    @ResponseBody
    @LoginRequired
    public String doSeckilling(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
    	String code = StringUtils.stripToEmpty(request.getParameter("code"));
    	String id = StringUtils.stripToEmpty(request.getParameter("uid"));
    	String p_id = StringUtils.stripToEmpty(request.getParameter("id"));
		if ("".equals(code) || "".equals(id) || "".equals(p_id)) {
			return JsonUtil.toJson(ErrorCodes.CORE_PARAM_UNLAWFUL, "参数不合法", null);
		}
		long uid = UserIdHolder.getUserId();
		if(uid<1){
			return JsonUtil.toJson(ErrorCodes.CORE_PARAM_UNLAWFUL, "不合法请求", null);
		}
		long start = DateUtil.convertLong(startDate).getTime();
        long end = DateUtil.convertLong(endDate).getTime();
        long now = System.currentTimeMillis();
        if(start > now || end < now){
        	return JsonUtil.toJson(ErrorCodes.CORE_PARAM_UNLAWFUL, "不合法请求", null);
        }
    	long pid = Long.parseLong(p_id);
        String ret = activityService.seckingProduct(code, uid, pid); 
        logger.info("checkInvite code {} | user {} | ret {}", code, ret);
        return ret;
    }
    
    @RequestMapping(value = {"/items/" ,"/items"})
    public String item(Model model) throws Exception {
        List<ProductInfoItem> list = activityService.getItemsByType(ProductType.SECKILLING);
        model.addAttribute("products", list);
        long toTime = DateUtil.convertLong(startDate).getTime();
        model.addAttribute("time", toTime);
        return "/activity/s/items";
    }
    
    @RequestMapping(value = {"/download", "/download/"})
    public String down(Model model) throws Exception{
    	return "/activity/s/down";
    }
    
    @RequestMapping(value = {"/f_share/","/f_share"})
    public String fshare(Model model){
    	return "/activity/s/f_share";
    }
    
    @RequestMapping(value = {"/product/secking" , "/product/secking/"})
    public ModelAndView secking(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("title", "秒杀");

        return new ModelAndView("/activity/s/secking", model);
    }
    
    @RequestMapping(value = {"/special" , "/special/"})
    public ModelAndView special(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        Map<String, Object> model = new HashMap<String, Object>();
        
        model.put("title", "特惠产品");
        List<ProductInfoItem> list = activityService.getItemsByType(ProductType.SPECIAL);
        model.put("products", list);
        long toTime = DateUtil.convertLong("2015-11-7 10:00:00").getTime();
        model.put("time", toTime);
        int isstart = 1;
        Date startDate = DateUtil.convertYYYYMMDD("2015-11-09"); // 开始时间
        if(DateUtil.isBefore(new Date(), startDate)){
            isstart = 0;
        }
        model.put("isstart", isstart);
        return new ModelAndView("/activity/s/special", model);
    }
    
    @RequestMapping(value = {"/share" , "/share/"})
    public String share(Model model) throws Exception {
        
    	long toTime = DateUtil.convertLong(startDate).getTime();
        model.addAttribute("time", toTime);
        model.addAttribute("title", "美分期-双十一");
        
        return "/activity/s/share";
    }
    
    @RequestMapping(value = {"/register/","/register"}, method = RequestMethod.GET)
    public String register(HttpServletRequest request, Model model){
    	
        // 随机产生用户id(用于验证请求）
        String uid = UUID.randomUUID().toString();
        model.addAttribute("UID", uid);
        // 保存至Session
        request.getSession().setAttribute("uid", uid);
    	return "/wechat/register";
    }
    
    @RequestMapping(value = {"/register/","/register"}, method = RequestMethod.POST)
    public String doRegister(HttpServletRequest request, Model model){
    	
        String name = request.getParameter("name");
        String mobile = request.getParameter("phone");
        String vcode = request.getParameter("vcode");
        String sign = request.getParameter("uid");
        logger.info("REGISTER_PARAM:{}|{}|{}|{}", name, mobile, vcode, sign);

        if (StringUtils.isBlank(name) || StringUtils.isBlank(mobile)) {
            model.addAttribute("msg", "参数错误 ！！");
            return "/activity/s/reg_success";
        }

        // 验证请求
        if (!weChatService.checkRequestUser(request, sign)) {
            model.addAttribute("msg", "验证错误！！");
            return "/activity/s/reg_success";
        }

        // 验证 手机验证码
        CodeMsg cm = vcodeService.validate(mobile, vcode);
        if (cm.getCode() != 0) {
            // 验证不成功
            model.addAttribute("msg", "手机验证不正确！！");
            return "/activity/s/reg_success";
        }

        try {
            String result = weChatService.registerWeChatUser(name, mobile,
                    vcode);
            Map<String, Object> ResultMap = JsonUtil.getMapFromJsonStr(result);
            int code = Integer.parseInt(ResultMap.get("code").toString());
            if (code == 0) {
                logger.info("user {} login meifenqi fronted system.....", name);
                model.addAttribute("msg", " 注册成功！！");
                return "/activity/s/reg_success";
            } else {
                logger.error("Exception WeChatRegister Progress!",
                        (String) ResultMap.get("msg"));
                model.addAttribute("msg", " 注册失败！！");
                return "/activity/s/reg_success";
            }
        } catch (Exception e) {
            logger.error("Exception WeChatRegister Progress!", e);
            model.addAttribute("msg", "系统错误,请稍后再试");
            return "/activity/s/reg_success";
        }
 
    }
    
    @RequestMapping(value = {"/seckilling","/seckilling/"})
    public String seckiling(
    		@RequestParam(value = "id", defaultValue = "0") long id,
    		Model model
    		) throws Exception {
        Product product = productService.findById(id);
        Hospital hospital = hospitalService.findById(product.getHospitalId());
        ProductDetail pDetail = productService.findDetailById(id);
        
        
        String t = pDetail.getSpecialNote();
        if("0".equals(t)){
        	t="";
        }
        model.addAttribute("price", product.getPrice().toBigInteger());
        model.addAttribute("detail", t);
        model.addAttribute("product", product);
        model.addAttribute("hospital", hospital);
        
        long start = DateUtil.convertLong(startDate).getTime();
        long end = DateUtil.convertLong(endDate).getTime();
        long now = System.currentTimeMillis();
        boolean stra = false;
        if(start < now && end > now){
        	stra = true;
        }
        model.addAttribute("time", start);
        model.addAttribute("is_start", stra);
        return "/activity/s/seckiling_product";
    }

    @RequestMapping(value="/Christmas/{page}")
    public ModelAndView ChristmasPage(@PathVariable String page, HttpServletRequest request, HttpServletResponse response){
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
        return new ModelAndView("/activity/Christmas/"+page, model);
    }

    @RequestMapping("/Christmas/register/")
    @ResponseBody
    public String ChristmasRegister(HttpServletRequest request, HttpServletResponse response){
        String passwd = request.getParameter("passwd");
        String mobile = request.getParameter("phone");
        String vcode = request.getParameter("vcode");
        String ret = "";
        if (StringUtils.isBlank(passwd) || StringUtils.isBlank(mobile)) {
            ret = JsonUtil.toJson(ErrorCodes.CORE_ERROR,"密码不能为空!",null);
            return ret;
        }

        // 验证 手机验证码
        CodeMsg cm = vcodeService.validate(mobile, vcode);
        if (cm.getCode() != 0) {
            ret = JsonUtil.toJson(3124,"验证码不对哦!",null);
            return ret;
        }

        try {
            String result = weChatService.registerWeChatUser(passwd, mobile,
                    vcode);
            Map<String, Object> ResultMap = JsonUtil.getMapFromJsonStr(result);
            int code = Integer.parseInt(ResultMap.get("code").toString());

            if (code == 0) {
                logger.info("user {} login meifenqi fronted system.....");

                User user = userService.queryUserByMobile(mobile);

                if(user!=null){
                    UserQuota quota = userQuotaService.queryUserQuota(user.getUid());
                    System.out.println(quota.toString());
                    System.out.println(quota.getPresent().compareTo(BigDecimal.valueOf(500)));

                    if(quota.getPresent().compareTo(BigDecimal.valueOf(500))==0){
                        ret = JsonUtil.toJson(7788,"你已经领取过了哦~",null);
                        return ret;
                    }
                }
                userService.updateUserPresent(mobile);
                ret = JsonUtil.toJson(0,"注册成功",null);

            }else if(code == 1001){
                logger.error("Exception WeChatRegister Progress!",
                        (String) ResultMap.get("msg"));
                ret= JsonUtil.toJson(code,"密码格式错误",null);
            }else if(code == 1004){
                logger.error("Exception WeChatRegister Progress!",
                        (String) ResultMap.get("msg"));
                ret= JsonUtil.toJson(code,"手机号非法",null);
            }else if(code == 1104){
                logger.error("Exception WeChatRegister Progress!",
                        (String) ResultMap.get("msg"));
                User user = userService.queryUserByMobile(mobile);
                if(user!=null){
                    UserQuota quota = userQuotaService.queryUserQuota(user.getUid());
                    if(quota.getPresent().compareTo(BigDecimal.valueOf(500))==0){
                        ret = JsonUtil.toJson(7788,"你已经领取过了哦~",null);
                        return ret;
                    }
                }
                userService.updateUserPresent(mobile);
                ret= JsonUtil.toJson(code,"此手机号已注册",null);
            }
            else {
                logger.error("Exception WeChatRegister Progress!",
                        (String) ResultMap.get("msg"));
                ret= JsonUtil.toJson(code,"注册失败",null);
            }

            if(code == 0 || code == 1104){
                userService.updateUserPresent(mobile);
            }
        } catch (Exception e) {
            logger.error("Exception WeChatRegister Progress!", e);
            ret = JsonUtil.toJson(7788, "系统错误,请稍后再试", null);
        }
        return ret;
    }

}
