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

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mfq.bean.Hospital;
import com.mfq.bean.area.City;
import com.mfq.constants.ErrorCodes;
import com.mfq.dao.area.CityMapper;
import com.mfq.service.HospitalService;
import com.mfq.utils.JsonUtil;

@Controller
public class AppController {

    private static final Logger logger = LoggerFactory
            .getLogger(AppController.class);
    
    @Resource
    HospitalService hopitalService;
    @Resource
    CityMapper cityMapper;

    @RequestMapping(value = "/app/download/", method = { RequestMethod.GET,
            RequestMethod.POST }, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String appdownload(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        try {
            Map<String, Object> params = JsonUtil.readMapFromReq(request);
            String appName = (String) params.get("app_name");

            Map<String, Object> map = Maps.newHashMap();
            if (StringUtils.equalsIgnoreCase(appName, "mfq")) { // 临时写为固定值
                Integer version = 5; // 从数据库根据iosUrlKey获取appName对应的版本号，注意是int类型
                String appAndroidUrl = "http://www.5imfq.com/download/meifenqi_v1.1_F201510311630m_0.apk"; // 从数据库根据iosUrlKey获取appName对应的
                String appIosUrl = "";
                map.put("version", version);
                map.put("appName", appName);
                map.put("appAndroidUrl", appAndroidUrl);
                map.put("appIosUrl", appIosUrl);
            }
            return JsonUtil.toJson(ErrorCodes.SUCCESS, "ok", map);
        } catch (Exception e) {
            logger.error("Exception Check Mobile Usage!", e);
            return JsonUtil.toJson(ErrorCodes.CORE_ERROR, "系统异常", null);
        }
    }
    
    @RequestMapping(value = "/support/city/", method = { RequestMethod.GET,
            RequestMethod.POST }, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getCity(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
    	
    	List<Hospital> list = hopitalService.queryHospitals();
    	List<City> clist = Lists.newArrayList();
    	
    	
    	for(Hospital h:list){
    		City city = new City();
    		city = cityMapper.findById(h.getCityId());
    		clist.add(city);
    	}
    	qcf(clist);
    	
    	return JsonUtil.successResultJson(clist);
    }
    
    
    @RequestMapping(value = {"/xmei/withyou/" , "/mei/withyou"}, method = { RequestMethod.GET})
    public String withyou(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
    	
    	return "activity/xm/withyou";
    }
    
    /**
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/mc/guide/" , "/mei/guide"}, method = { RequestMethod.GET})
    public String act() throws Exception {
    	return "activity/mxa/guide";
    }
    
    private List<City> qcf(List<City> list){
    	
    	for (int i = 0; i < list.size() - 1; i++) {                             //循环遍历集体中的元素
            for (int j = list.size() - 1; j > i; j--) {                         //这里非常巧妙，这里是倒序的是比较
                 if (list.get(j).getId()==list.get(i).getId()) {
                 list.remove(j);
                 }
           }
       }
    return list;
   }
}
