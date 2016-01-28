package com.mfq.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mfq.bean.Hospital;
import com.mfq.service.HospitalService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mfq.bean.HomeBanner;
import com.mfq.bean.ProductClassify;
import com.mfq.bean.app.ProductListItem2App;
import com.mfq.constants.ProductFlag;
import com.mfq.constants.QiniuBucketEnum;
import com.mfq.service.ClassifyService;
import com.mfq.service.HomeBannerService;
import com.mfq.service.ProductService;
import com.mfq.utils.DateUtil;
import com.mfq.utils.JsonUtil;

//首页
@Controller
public class IndexController {

	@Resource
	HomeBannerService bannerService;
	@Resource
	ProductService productService;
	@Resource
    ClassifyService classifyService;
	@Resource
	HospitalService hospitalService;
	
	private String startDate = "2015-12-10 10:00:00";
    private String endDate = "2015-12-12 23:59:59";
    
    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request, Model model) {
    	String city_id = StringUtils.stripToEmpty(request.getParameter("city_id"));
    	int cityId=0;
    	if(StringUtils.isNotBlank(city_id)){
    		cityId = Integer.parseInt(city_id);
    	}
    	List<ProductListItem2App> data = productService
                .findByFlag(cityId, ProductFlag.RECOMMEND, 0);
    	
    	List<ProductClassify> classifys = classifyService.findByRootId(0);

		List<Map<String,Object>> hospitals = hospitalService.findAll();

		model.addAttribute("hospitals", hospitals);
    	
//    	for(int i=0;i<10;i++){
//    		ProductClassify c=null;
//    		if(i < classifys.size()){
//    			c=classifys.get(i);
//    		}else{
//    			c = new ProductClassify();
//    		}
//
//    		model.addAttribute("classify_"+i, c);
//    	}
		model.addAttribute("classifys", classifys);
    	
    	model.addAttribute("products", data);
        return "/app/home/home";
    }
    
    @RequestMapping(value = {"/ad","/ad/"})
    @ResponseBody
    public String ad() {
    	String data = QiniuBucketEnum.IMG2.getDomain()+"/images/ad/ad.png?t=2";
        return JsonUtil.toJson(0, "", data);
    }
    
    @RequestMapping(value = {"/index/hd","/index/hd"})
    @ResponseBody
    public String hd(HttpServletRequest request,
            HttpServletResponse response) {
    	List<Object> data= Lists.newArrayList();
    	String t=System.currentTimeMillis()+"";
    	
    	List<HomeBanner> banners = bannerService.queryHomeBanners();
    	
    	for(HomeBanner b:banners){

    		Map<String,Object> hd = Maps.newHashMap();
        	hd.put("imgs",b.getImg()+"?_="+t);
        	hd.put("flag", b.getpType().getFlag());
        	hd.put("id",b.getpId());
        	hd.put("name",b.getName());
        	hd.put("url",b.getUrl());
        	data.add(hd);
    	}
    	String ret = JsonUtil.successResultJson(data);
        return ret;
    }


    //首页弹窗活动
    @RequestMapping(value = {"/index/duration","/index/duration/"})
    @ResponseBody
    public String duration(HttpServletRequest request,
            HttpServletResponse response) {
    	
    	Map<String,Object> m = Maps.newHashMap();
    	m.put("url", "https://www.zhihu.com/people/beABadGuy");
    	m.put("img", "https://pic3.zhimg.com/3d9200d4dba778731cbf1f05a42a9a86_l.jpg");
    	
    	long start = DateUtil.convertLong(startDate).getTime();
        long end = DateUtil.convertLong(endDate).getTime();
        
        long now = System.currentTimeMillis();
		if (start < now && end > now) {
			m.put("status", 1); // 0--活动结束 1--活动进行时
		} else {
			m.put("status", 0); // 0--活动结束 1--活动进行时
		}

    	String ret = JsonUtil.successResultJson(m);
    	return ret;
    }
    
    
	@RequestMapping("/protocol")
	public ModelAndView regProtocol(HttpServletRequest request, HttpServletResponse response){
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("title", "注册协议");
		
		return new ModelAndView("/help/protocol", model);
	}

	/**
	 *
	 * @return
     */
	@RequestMapping(value = {"/hospital/{hid}/","/hospital/{hid}"})
	public String hospitalSdy(@PathVariable("hid") int hid, Model model){
		String tpp = "error";
		if(hid == 0){

		}
		tpp = "hospital_"+hid;

		model.addAttribute("static_url",QiniuBucketEnum.STATIC.getDomain());
		return "/hospital/"+tpp;
	}


	
	
    
}