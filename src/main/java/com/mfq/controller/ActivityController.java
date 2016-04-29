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

import com.mfq.bean.*;
import com.mfq.bean.app.ActivityOffline;
import com.mfq.bean.app.ActivityOnline;
import com.mfq.bean.app.ActivityOnlineDetail;
import com.mfq.bean.user.User;
import com.mfq.bean.user.UserQuota;
import com.mfq.helper.SignHelper;
import com.mfq.service.activity.DidiService;
import com.mfq.service.user.UserQuotaService;
import com.mfq.utils.ListSortUtil;
import com.qiniu.util.Json;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.mfq.annotation.LoginRequired;
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
    @Resource
    DidiService didiService;

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

//    @RequestMapping(value="/newyear/{page}")
//    public ModelAndView ChristmasPage(@PathVariable String page, HttpServletRequest request, HttpServletResponse response){
//        Map<String, Object> model = new HashMap<String, Object>();
//        model.put("title", "会员注册");
//        try {
//            // 随机产生用户id(用于验证请求）
//            String uid = UUID.randomUUID().toString();
//            model.put("UID", uid);
//            // 保存至Session
//            request.getSession().setAttribute("uid", uid);
//        } catch (Exception e) {
//            logger.error("WeiXin_REGISTER_Exception", e);
//        }
//        return new ModelAndView("/activity/newyear/"+page, model);
//    }

//    @RequestMapping("/newyear/register/")
//    @ResponseBody
//    public String ChristmasRegister(HttpServletRequest request, HttpServletResponse response){
//        String passwd = request.getParameter("passwd");
//        String mobile = request.getParameter("phone");
//        String vcode = request.getParameter("vcode");
//        String ret = "";
//        if (StringUtils.isBlank(passwd) || StringUtils.isBlank(mobile)) {
//            ret = JsonUtil.toJson(ErrorCodes.CORE_ERROR,"密码不能为空!",null);
//            return ret;
//        }
//
//        // 验证 手机验证码
//        CodeMsg cm = vcodeService.validate(mobile, vcode);
//        if (cm.getCode() != 0) {
//            ret = JsonUtil.toJson(3124,"验证码不对哦!",null);
//            return ret;
//        }
//
//        try {
//            String result = weChatService.registerWeChatUser(passwd, mobile,
//                    vcode);
//            Map<String, Object> ResultMap = JsonUtil.getMapFromJsonStr(result);
//            int code = Integer.parseInt(ResultMap.get("code").toString());
//
//            if (code == 0) {
//                logger.info("user {} login meifenqi fronted system.....");
//
//                User user = userService.queryUserByMobile(mobile);
//
//                if(user!=null){
//                    UserQuota quota = userQuotaService.queryUserQuota(user.getUid());
//                    System.out.println(quota.toString());
//                    System.out.println(quota.getPresent().compareTo(BigDecimal.valueOf(500)));
//
//                    if(quota.getPresent().compareTo(BigDecimal.valueOf(500))==0){
//                        ret = JsonUtil.toJson(7788,"你已经领取过了哦~",null);
//                        return ret;
//                    }
//
//                    //校验优惠券
//                    if(userService.isHavePresentCoupon(user.getUid())){
//                        ret = JsonUtil.toJson(7788,"你已经领取过了哦~",null);
//                        return ret;
//                    }
//                }
//                userService.updateUserPresentCoupon(user.getUid());
//                //加一个成功分享
//                String activityName = request.getParameter("activityName");
//                if(StringUtils.isNotBlank(activityName)){
//                    activityService.addResultCount(activityName);
//                }
//                return JsonUtil.toJson(0,"注册成功",null);
//
//            }else if(code == 1001){
//                logger.error("Exception WeChatRegister Progress!",
//                        (String) ResultMap.get("msg"));
//                ret= JsonUtil.toJson(code,"密码格式错误",null);
//            }else if(code == 1004){
//                logger.error("Exception WeChatRegister Progress!",
//                        (String) ResultMap.get("msg"));
//                ret= JsonUtil.toJson(code,"手机号非法",null);
//            }else if(code == 1104){
//                logger.error("Exception WeChatRegister Progress!",
//                        (String) ResultMap.get("msg"));
//                User user = userService.queryUserByMobile(mobile);
//                if(user!=null){
//                    UserQuota quota = userQuotaService.queryUserQuota(user.getUid());
//                    if(quota.getPresent().compareTo(BigDecimal.valueOf(500))==0){
//                        ret = JsonUtil.toJson(7788,"你已经领取过了哦~",null);
//                        return ret;
//                    }
//                    //校验优惠券
//                    if(userService.isHavePresentCoupon(user.getUid())){
//                        ret = JsonUtil.toJson(7788,"你已经领取过了哦~",null);
//                        return ret;
//                    }
//                }
//                userService.updateUserPresentCoupon(user.getUid());
//                return JsonUtil.toJson(code,"此手机号已注册",null);
//            }
//            else {
//                logger.error("Exception WeChatRegister Progress!",
//                        (String) ResultMap.get("msg"));
//                ret= JsonUtil.toJson(code,"注册失败",null);
//            }
//
//            if(code == 0 || code == 1104){
//                User user = userService.queryUserByMobile(mobile);
//                userService.updateUserPresentCoupon(user.getUid());
//            }
//        } catch (Exception e) {
//            logger.error("Exception WeChatRegister Progress!", e);
//            ret = JsonUtil.toJson(7788, "系统错误,请稍后再试", null);
//        }
//        return ret;
//    }


//    /**
//     * 礼品券号码兑换优惠券
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping("/present/code")
//    @ResponseBody
//    public String presentCode(HttpServletRequest request, HttpServletResponse response){
//        String ret = "";
//        try {
//            Map<String,Object> params = JsonUtil.readMapFromReq(request);
//
//            if (!SignHelper.validateSign(params)) { // 签名验证失败
//                return JsonUtil.toJson(ErrorCodes.SIGN_VALIDATE_ERROR, "签名验证失败", null);
//            }
//
//            if(params.get("uid") == null || params.get("code") == null){
//                logger.error("系统非法请求！ATTENTION_UNLAWFULL_ACCESS");
//                return JsonUtil.toJson(ErrorCodes.CORE_PARAM_UNLAWFUL, "参数非法",
//                        null);
//            }
//
//            long uid = Long.parseLong(params.get("uid").toString());
//            String code = params.get("code").toString();
//            logger.info("uid:{},code:{}",uid,code);
//            ret = activityService.presentCode(uid,code);
//
//        } catch (Exception e) {
//            logger.error(e.getMessage());
//            e.printStackTrace();
//            ret = JsonUtil.toJson(9999,"系统错误",null);
//        }
//        return ret;
//    }

//    /**
//     * 姓名测试
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping("/testname")
//    public ModelAndView nameTest(HttpServletRequest request,HttpServletResponse response){
//        Map<String, Object> model = new HashMap<String, Object>();
//        activityService.addOpenCount("testname");
//        return new ModelAndView("/activity/testname/nametest", model);
//    }

    @RequestMapping("/coupon")
    public ModelAndView coupon(){
        Map<String, Object> model = new HashMap<String, Object>();
        activityService.addOpenCount("coupon");
        return new ModelAndView("/activity/coupon/index", model);
    }

//    @RequestMapping("/baoming")
//    public ModelAndView baoming(){
//        Map<String, Object> model = new HashMap<String, Object>();
//        activityService.addShareCount("baoming");
//        return new ModelAndView("/activity/baoming/index", model);
//    }



    /**
     * 添加活动的被分享次数
     * @param activityName
     * @param request
     * @param response
     */
    @RequestMapping(value = {"/addShareCount/{activityName}","/addShareCount/{activityName}"})
    public void addShareCount(@PathVariable String activityName, HttpServletRequest request,HttpServletResponse response){

        activityService.addShareCount(activityName);
    }


    /**
     * 添加活动的预期结果次数
     * @param activityName
     * @param request
     * @param response
     */
    @RequestMapping(value = {"/addResultCount/{activityName}","/addResultCount/{activityName}"})
    public void addResultCount(@PathVariable String activityName, HttpServletRequest request,HttpServletResponse response){
        activityService.addResultCount(activityName);
    }


    /**
     * 获取线上活动列表
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = {"/online","/online/"})
    public @ResponseBody String onlineList(HttpServletRequest request, HttpServletResponse response){
        try{
            Map<String,Object> params = JsonUtil.readMapFromReq(request);
            if (!SignHelper.validateSign(params)) { // 签名验证失败
                return JsonUtil.toJson(ErrorCodes.SIGN_VALIDATE_ERROR, "签名验证失败", null);
            }
            List<ActivityOnline> list = activityService.onlineList();
            ListSortUtil<ActivityOnline> listSortUtil = new ListSortUtil<>();
            listSortUtil.sort(list,"end","asc");
            logger.info(list.toString());
            return JsonUtil.successResultJson(list);
        }catch(Exception e){
            logger.error(e.toString());
            return JsonUtil.toJson(9999,"获取线上列表出错",null);
        }
    }

    @RequestMapping(value = {"/online/detail","/online/detail/"})
    public @ResponseBody String onlineDetail(HttpServletRequest request, HttpServletResponse response){
        try{
            Map<String,Object> params = JsonUtil.readMapFromReq(request);
            if (!SignHelper.validateSign(params)) {
                return JsonUtil.toJson(ErrorCodes.SIGN_VALIDATE_ERROR, "签名验证失败", null);
            }
            if(params.get("id") == null){
                return JsonUtil.toJson(ErrorCodes.CORE_PARAM_NULL, "参数错误", null);
            }
            Integer id = Integer.parseInt(params.get("id").toString());

            ActivityOnlineDetail onlineDetail = activityService.onlineDetail(id);

            String ret = JsonUtil.successResultJson(onlineDetail);
            logger.info(" online over {}",ret);
            return ret;
        }catch(Exception e){
            logger.error("online detail error {}",e);
            return JsonUtil.toJson(9999,"获取线上列表出错",null);
        }
    }

    /**
     * 获取线下活动列表
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = {"/offline","/offline/"})
    public @ResponseBody String offlineList(HttpServletRequest request, HttpServletResponse response){
        try{
            Map<String,Object> params = JsonUtil.readMapFromReq(request);
            if (!SignHelper.validateSign(params)) { // 签名验证失败
                return JsonUtil.toJson(ErrorCodes.SIGN_VALIDATE_ERROR, "签名验证失败", null);
            }
            List<ActivityOffline> list = activityService.offlineList();
            ListSortUtil<ActivityOffline> listSortUtil = new ListSortUtil<>();
            listSortUtil.sort(list,"end","asc");
            return JsonUtil.successResultJson(list);
        }catch(Exception e){
            logger.error(e.toString());
            return JsonUtil.toJson(9999,"获取线下列表出错",null);
        }
    }
    @RequestMapping(value = {"/topimg","/topimg/"})
    public @ResponseBody String topimg(HttpServletRequest request, HttpServletResponse response){
        try{
            String url = "http://7xlb4k.com2.z0.glb.qiniucdn.com/2016.03.25.topimg.jpg";
            return JsonUtil.successResultJson(url);
        }catch(Exception e){
            logger.error(e.toString());
            return JsonUtil.toJson(9999,"获取线下列表出错",null);
        }
    }

    @RequestMapping(value = {"/didi/{city}"})
    public String didiBeijing(@PathVariable("city")String city, HttpServletRequest request, HttpServletResponse response){
        try{
            if(city.equals("beijing")){

            }else if(city.equals("shanghai")){

            }else if(city.equals("guangzhou")){

            }
            return "activity/didi/index";
        }catch(Exception e){
            logger.error(e.toString());
            return JsonUtil.toJson(9999,"获取线下列表出错",null);
        }
    }

    @RequestMapping(value = {"/didi/mark"})
    public String addDidiMark(HttpServletRequest request,HttpServletResponse response){
        try{
            String mobile = request.getParameter("mobile");
            //pid 按照 ',' 连接 , 如 : 213,421,1,2,4
            String pids = request.getParameter("pids");
            didiService.insertMobile(mobile,0,pids);

        }catch(Exception e){
            logger.error(e.getMessage());
        }

        return "";
    }



//
//    @RequestMapping(value = {"/yanzhiai","/yanzhiai/"})
//    public ModelAndView yanzhiai(){
//        Map<String, Object> model = new HashMap<String, Object>();
//        activityService.addOpenCount("颜值癌");
//        return new ModelAndView("/activity/baoming/yanzhiai", model);
//    }

//    @RequestMapping(value = {"/yanzhiai/baoming","/yanzhiai/baoming/"})
//    public @ResponseBody String yanzhiaiBaoming(HttpServletRequest request,HttpServletResponse response){
//        activityService.addResultCount("颜值癌");
//        String name = request.getParameter("name");
//        String mobile = request.getParameter("mobile");
//        String wechat = request.getParameter("wechat");
//
//        Baoming baoming = new Baoming();
//        baoming.setName(name);
//        baoming.setMobile(mobile);
//        baoming.setWechat(wechat);
//        baoming.setActivityName("颜值癌");
//        try{
//            return activityService.saveBaoming(baoming);
//        }catch(Exception e){
//            logger.error(e.toString());
//            return JsonUtil.toJson(9999,"报名出错",null);
//        }
//
//    }









}
