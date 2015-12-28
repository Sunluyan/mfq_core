package com.mfq.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

//帮助中心
@Controller
@RequestMapping("/help")
public class HelpController {

	private static Logger logger = LoggerFactory.getLogger(HelpController.class);
	
	@RequestMapping("/{path}")
	public ModelAndView helpPage(@PathVariable("path") String path, 
			HttpServletRequest request, HttpServletResponse response){
		
		logger.info("help page param path : {}", path);
		Map<String, Object> model = new HashMap<String, Object>();
		//生成菜单，将打开页面的class特殊着色，在jsp页面上做。
		
		//根据path 数据库取值，拿到id及body内容
				
		
		model.put("title", "");
		
		return new ModelAndView("/help/singlepage", model);
	}
	
	
}
