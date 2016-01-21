package com.mfq.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mfq.cache.UserOperationUtil;
import com.mfq.dataservice.context.UserIdHolder;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Maps;
import com.mfq.bean.Hospital;
import com.mfq.bean.Product;
import com.mfq.bean.ProductDetail;
import com.mfq.bean.ProductImg;
import com.mfq.bean.app.ProductListItem2App;
import com.mfq.constants.ErrorCodes;
import com.mfq.constants.ProductFlag;
import com.mfq.constants.ProductType;
import com.mfq.helper.SignHelper;
import com.mfq.service.HospitalService;
import com.mfq.service.ProductService;
import com.mfq.utils.DateUtil;
import com.mfq.utils.FQUtil;
import com.mfq.utils.JsonUtil;

@Controller
@RequestMapping("/product")
public class ProductController {

    private static final Logger logger = LoggerFactory
            .getLogger(ProductController.class);

    @Resource
    ProductService productService;
    @Resource
    HospitalService hospitalService;

    /**
     * 获取分类的产品列表
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = { "/classproduct",
            "/classproduct/" }, method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String classProduct(HttpServletRequest request,
            HttpServletResponse response) {
        String ret = "";
        try {
            Map<String, Object> params = JsonUtil.readMapFromReq(request);
            if (!SignHelper.validateSign(params)) { // 签名验证失败
                return JsonUtil.toJson(ErrorCodes.SIGN_VALIDATE_ERROR, "签名验证失败",
                        null);
            }
            int city = (Integer) params.get("city") == null ? 0
                    : (Integer) params.get("city");
            int category = (Integer) params.get("category") == null ? 0
                    : (Integer) params.get("category");
            int sort = (Integer) params.get("sort") == null ? 0
                    : (Integer) params.get("sort");
            int type = 0;
            if(params.get("type") != null){
            	type = (Integer) params.get("type");
            }
            List<ProductListItem2App> data = productService.findByClass(city,
                    category, sort, type);
            ret = JsonUtil.successResultJson(data);
            logger.info("Product_Info_Ret is:{}", ret);
        } catch (Exception e) {
            logger.error("Exception ProductInfo Process!", e);
            ret = JsonUtil.toJson(ErrorCodes.CORE_ERROR, "系统异常", null);
        }
        return ret;
    }

    /**
     * 搜索产品
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = {"/search","search/"},method = {RequestMethod.GET,RequestMethod.POST},produces = "application/json;charset=utf-8")
    public @ResponseBody String searchProduct(HttpServletRequest request,HttpServletResponse response){
        String ret = "";
        try{
            Map<String, Object> params = JsonUtil.readMapFromReq(request);
            if (!SignHelper.validateSign(params)) { // 签名验证失败
                ret = JsonUtil.toJson(ErrorCodes.SIGN_VALIDATE_ERROR, "签名验证失败",
                        null);
                logger.error("签名验证失败！ret={}", ret);
                return ret;
            }

            if(params.get("keyword") == null){
                ret = JsonUtil.toJson(ErrorCodes.CORE_PARAM_UNLAWFUL, "参数不合法", null);
                logger.error("参数不合法！ret={}", ret);
                return ret;
            }
            String keyword = params.get("keyword").toString();

            long page = 1;
            if(params.get("page") != null){
                page = Long.parseLong(params.get("page").toString());
            }

            List<ProductListItem2App> list = productService.findProductByKeyword(keyword,page);
            ret = JsonUtil.successResultJson(list);
            //添加一条用户行为记录
            UserOperationUtil.setKeyword(UserIdHolder.getLongUid(),keyword);
        }catch(Exception e){
            logger.error("Exception ProductInfo Process!", e);
            ret = JsonUtil.toJson(ErrorCodes.CORE_ERROR, "系统异常", null);
        }
        return ret;
    }

    

    /**
     * 获取推荐列表
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = { "/product_type",
            "/product_type/" }, method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String productType(HttpServletRequest request,
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
             if(params.get("uid") == null){
                 ret = JsonUtil.toJson(ErrorCodes.CORE_PARAM_UNLAWFUL, "参数不合法", null);
                 logger.error("参数不合法！ret={}", ret);
                 return ret;
             }
             Long uid = Long.parseLong(params.get("uid").toString());
             if(uid!=201){
            	 ret = JsonUtil.toJson(ErrorCodes.CORE_PARAM_UNLAWFUL, "登录 。。。", null);
            	 return ret;
             }
             int t = Integer.parseInt(params.get("type").toString());
             ProductType type = ProductType.SECKILLING;
             if(t == 0){
            	 type = ProductType.NORMAL;
             }else if(t == 2){
            	 type = ProductType.SECKILLING;
             }
            List<Product> data = productService.queryProductsByType(type);
            ret = JsonUtil.successResultJson(data);
        } catch (Exception e) {
            logger.error("Exception ProductInfo Process!", e);
            ret = JsonUtil.toJson(ErrorCodes.CORE_ERROR, "系统异常", null);
        }
        logger.info("Product_Info_Ret is:{}", ret);
        return ret;
    }
    
    /**
     * 获取推荐列表
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = { "/remcommend",
            "/recommend/" }, method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String remcommend(HttpServletRequest request,
            HttpServletResponse response) {
        String ret = "";
        try {
        	String cityId = request.getParameter("city_id");
        	if(cityId == null){
        		cityId =  "0";
        	}
        	int city = Integer.parseInt(cityId);
        	int type = 0;
        	if(request.getParameter("type") != null){
        		type = Integer.parseInt(request.getParameter("type"));
        	}
            List<ProductListItem2App> data = productService
                    .findByFlag(city, ProductFlag.RECOMMEND, type);
            ret = JsonUtil.successResultJson(data);
        } catch (Exception e) {
            logger.error("Exception ProductInfo Process!", e);
            ret = JsonUtil.toJson(ErrorCodes.CORE_ERROR, "系统异常", null);
        }
        logger.info("Product_Info_Ret is:{}", ret);
        return ret;
    }

    java.text.DecimalFormat myformat=new java.text.DecimalFormat("000");
    
    
    @RequestMapping(value = { "/app/detail",
            "/app/detail/" }, method = RequestMethod.GET)
    public ModelAndView appItemInfo(HttpServletRequest request,
            HttpServletResponse response) {
        Map<String, Object> model = Maps.newHashMap();
        String ret = "";
        try {
        	
            String pid = request.getParameter("pid");
            if (StringUtils.isBlank(pid)) { // 参数异常
                model.put("error", "参数异常");
                return new ModelAndView("/app/product/detail", model);
            }
            long id = Long.parseLong(pid);
            UserOperationUtil.setProduct(UserIdHolder.getLongUid(),(int)id);  //添加浏览数
            ProductDetail productDetail = null;
            
            

            if (id > 0) {
                model.put("error", "");
                Product product = productService.findById(id);

                //产品特殊处理
                if(id == 129 ){
                    return new ModelAndView("/app/product/t/1980", model);
                }else if(id == 130){
                    return new ModelAndView("/app/product/t/3980", model);
                }else if(id == 131){
                    return new ModelAndView("/app/product/t/6980", model);
                }

                model.put("title", product.getName()+"-美分期");
                model.put("name", product.getName());
                model.put("viewNum", String.valueOf(product.getViewNum()));
                model.put("endTime", DateUtil.formatCZYYYYMMDD(product.getDateEnd()));
                model.put("price", product.getPrice().toString());
                model.put("purl", product.getImg());
                model.put("type", product.getType().getId());
                model.put("market_price", myformat.format(product.getMarketPrice()));
                
                productDetail = productService.findProductDetailByPid(id);
                if(productDetail != null){
                	model.put("consume_step", productDetail.getConsumeStep());  //消费流程
                    model.put("reserve", productDetail.getReserve());  //如何预约
                    model.put("special_note", productDetail.getSpecialNote());  //特殊说明
                    model.put("body", productDetail.getBody()); //简介
                }
                
                
                Map<String,Object> fq = FQUtil.fenqiMaxCompute(product.getMarketPrice());// 分期得计算规则
                model.put("p_price", String.valueOf(fq.get("p_price")));
                model.put("p_num", String.valueOf(fq.get("p_num")));
                
                Hospital hospital = hospitalService.findById(product.getHospitalId());
                model.put("hid", String.valueOf(hospital.getId()));
                model.put("hospital_name", hospital.getName());
                model.put("hospital_addr", hospital.getAddress());
                model.put("hospital_img", hospital.getImg());
                
                ret = "/app/product/detail";
                
                if(id > 120){
                	model.put("cureMeans", productDetail.getCureMeans());
                	model.put("p", productDetail);
                	
                	Map<Integer, BigDecimal> fqs = FQUtil.fenqiCompute(product.getMarketPrice());// 分期得计算规则
                    
                	Map<Integer, BigDecimal> f = Maps.newHashMap();
                	if(fqs.containsKey(6)){
                		model.put("fq_6", fqs.get(6));
                	}else{
                		model.put("fq_6", "");
                	}
                	
                	if(fqs.containsKey(12)){
                		model.put("fq_12", fqs.get(12));
                	}else{
                		model.put("fq_12", "");
                	}
                	
                	if(fqs.containsKey(3)){
                		model.put("fq_3", fqs.get(3));
                	}else{
                		model.put("fq_3", "");
                	}
                	BigDecimal t = product.getMarketPrice().subtract(product.getPrice());
                	model.put("bt", myformat.format(t));
                	List<ProductImg> imgs = productService.findProductImg(id);
                	model.put("imgs", imgs);
                	ret = "/app/product/detailn";
                }else {
                    model.put("error", "product_not_exist");
                }
                
                
                String s = StringUtils.stripToEmpty(request.getParameter("s"));
            	if(!"".equals(s)){
            		model.put("s", true);
            	}else{
            		model.put("s", false);
            	}
            }

        } catch (Exception e) {
            logger.error("Exception ProductInfo Process!", e);
            model.put("error", "系统异常");
        }
        logger.info("Product_Info_Ret is:{}", model);
        return new ModelAndView(ret, model);
    }
}
