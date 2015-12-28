package com.mfq.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mfq.service.HospitalService;

/**
 */
@Controller
@RequestMapping("/hospital")
public class HospitalController {

    private static final Logger logger = LoggerFactory
            .getLogger(HospitalController.class);
    
    private String startDate = "2015-11-11 18:00:00";
    private String endDate = "2015-11-12 23:59:59";
    @Resource
    HospitalService hospitalService;
    
    
    /**
     * 跳转到hospital/index.jsp页面
     * @return
     * @author liuzhiguo1
     */
    @RequestMapping(value = {"/list/","/list"})
    public String hospitallist(){
    	return "/hospital/index";
    }
    
    /**
     * 获取hospital/index.jsp页面的数据
     * @author liuzhiguo1
     * @return
     */
    @RequestMapping(value = {"/list/data/","/list/data"})
    public @ResponseBody List<Map<String,Object>> hospitalData(){
    	List<Map<String,Object>> list = hospitalService.findAll();
    	return list;
    }
    
    
//    @RequestMapping(value = {"/seckilling","/seckilling/"})
//    public String seckiling(
//    		@RequestParam(value = "id", defaultValue = "0") long id,
//    		Model model
//    		) throws Exception {
//        Product product = productService.findById(id);
//        Hospital hospital = hospitalService.findById(product.getHospitalId());
//        ProductDetail pDetail = productService.findDetailById(id);
//        
//        
//        String t = pDetail.getSpecialNote();
//        if("0".equals(t)){
//        	t="";
//        }
//        model.addAttribute("price", product.getPrice().toBigInteger());
//        model.addAttribute("detail", t);
//        model.addAttribute("product", product);
//        model.addAttribute("hospital", hospital);
//        
//        long start = DateUtil.convertLong(startDate).getTime();
//        long end = DateUtil.convertLong(endDate).getTime();
//        long now = System.currentTimeMillis();
//        boolean stra = false;
//        if(start < now && end > now){
//        	stra = true;
//        }
//        model.addAttribute("time", start);
//        model.addAttribute("is_start", stra);
//        return "/activity/s/seckiling_product";
//    }

}
