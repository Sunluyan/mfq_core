package com.mfq.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mfq.cache.UserOperationUtil;
import com.mfq.dataservice.context.UserIdHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.google.common.collect.Maps;
import com.mfq.bean.ProductClassify;
import com.mfq.constants.ErrorCodes;
import com.mfq.service.ClassifyService;
import com.mfq.utils.JsonUtil;

@Controller
@RequestMapping("/class")
public class ClassifyController {

    private static final Logger logger = LoggerFactory
            .getLogger(ClassifyController.class);
    
    @Resource
    ClassifyService classifyService;

    @RequestMapping(value = {"/info", "/info/"}, method = RequestMethod.GET)
    @ResponseBody
    public String info(HttpServletRequest request, HttpServletResponse response) {
        String ret = "";
        try{
            Integer rootId = 0;
            String idParam = (String) request.getParameter("id");
            if (!StringUtils.isBlank(idParam)) {
                rootId = Integer.parseInt(idParam);
            }
            Map<Integer, List<ProductClassify>> lm = Maps.newHashMap();
            Map<Integer, ProductClassify> sm = Maps.newHashMap();
            classifyService.buildClassifyInfo(rootId, lm, sm);
            if(lm != null && sm != null && lm.size() > 0 && sm.size() > 0){
                Map<String, Object> map = Maps.newHashMap();
                map.put("lm", lm);
                map.put("sm", sm);
                ret = JsonUtil.successResultJson(map);
            }
            //加入一条用户的行为记录
            UserOperationUtil.setType(UserIdHolder.getLongUid(),(int)rootId);  //添加浏览数

        } catch (Exception e) {
            logger.error("Exception ProductInfo Process!", e);
            ret = JsonUtil.toJson(ErrorCodes.CORE_ERROR, "系统异常", null);
        }
        return ret;
    }
    
    @RequestMapping(value = {"/item", "/item/"}, method = RequestMethod.GET)
    @ResponseBody
    public String item(HttpServletRequest request, HttpServletResponse response) {
    	String ret = "";
    	try{
    		String id = request.getParameter("id");
    		int rootId = 0;
    		if(id != null){
    			rootId = Integer.parseInt(id);
    		}
    		List<ProductClassify> data = classifyService.findByRootId(rootId);
    		ret = JsonUtil.successResultJson(data);
    	}catch(Exception e){
    		logger.error("Exception ProductItem Process!", e);
            ret = JsonUtil.toJson(ErrorCodes.CORE_ERROR, "系统异常", null);
    	}
    	
    	return ret;
    }
}