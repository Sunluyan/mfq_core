package com.mfq.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.mfq.annotation.LoginRequired;
import com.mfq.bean.app.ProductListItem2App;
import com.mfq.constants.ErrorCodes;
import com.mfq.helper.SignHelper;
import com.mfq.service.FavoritesService;
import com.mfq.utils.JsonUtil;

@Controller
@RequestMapping("/favorites")
public class FavoritesController {

    private static final Logger logger = LoggerFactory.getLogger(FavoritesController.class);
    
    @Resource
    FavoritesService favoritesService;
    
    @RequestMapping(value = {"/list", "/list/"}, method = RequestMethod.POST)
    @ResponseBody
    @LoginRequired
    public String list(HttpServletRequest request, HttpServletResponse response) {
        String ret = "";
        try{
    		Map<String, Object> params = JsonUtil.readMapFromReq(request);
            if (!SignHelper.validateSign(params)) { // 签名验证失败
                return JsonUtil.toJson(ErrorCodes.SIGN_VALIDATE_ERROR, "签名验证失败",
                        null);
            }
            if (params.get("uid") == null) { // 参数异常
                return JsonUtil.toJson(ErrorCodes.CORE_PARAM_UNLAWFUL, "参数异常",
                        null);
            }
            long uid = Long.parseLong(params.get("uid").toString());
            List<ProductListItem2App> data=favoritesService.findByUid(uid);
            logger.info("Favorites_Info_Ret is:{}", ret);
            ret=JsonUtil.successResultJson(data);
        } catch (Exception e) {
            logger.error("Exception_FavoritesInfo_ Process!", e);
            ret = JsonUtil.toJson(ErrorCodes.CORE_ERROR, "系统异常", null);
        }
        return ret;
    }
    
    @RequestMapping(value = {"/delete", "/delete/"}, method = RequestMethod.POST)
    @ResponseBody
    @LoginRequired
    public String delete(HttpServletRequest request, HttpServletResponse response) {
        String ret = "";
        try{
    		Map<String, Object> params = JsonUtil.readMapFromReq(request);
            if (!SignHelper.validateSign(params)) { // 签名验证失败
                return JsonUtil.toJson(ErrorCodes.SIGN_VALIDATE_ERROR, "签名验证失败",
                        null);
            }
            if (params.get("uid") == null || params.get("pids") == null) { // 参数异常
                return JsonUtil.toJson(ErrorCodes.CORE_PARAM_UNLAWFUL, "参数异常",
                        null);
            }
            long uid = Long.parseLong(params.get("uid").toString());
            String[] pids = params.get("pids").toString().split(",");
            long result = favoritesService.deleteByUids(uid, pids);
            Map<String, Long> data = Maps.newHashMap();
            data.put("num", result);
            if(result > 0){
                ret=JsonUtil.successResultJson(data);
            }else{
            	ret=JsonUtil.toJson(ErrorCodes.FAIL, "更新失败", data);
            }
        } catch (Exception e) {
            logger.error("Exception FavoitesInfo Process!", e);
            ret = JsonUtil.toJson(ErrorCodes.CORE_ERROR, "系统异常", null);
        }
        logger.info("Favorites_Delete_Ret is:{}", ret);
        return ret;
    }
    
    
    @RequestMapping(value = {"/add", "/add/"}, method = RequestMethod.POST)
    @ResponseBody
    @LoginRequired
    public String insertFavorites(HttpServletRequest request, HttpServletResponse response) {
        String ret = "";
        try{
    		Map<String, Object> params = JsonUtil.readMapFromReq(request);
            if (!SignHelper.validateSign(params)) { // 签名验证失败
                return JsonUtil.toJson(ErrorCodes.SIGN_VALIDATE_ERROR, "签名验证失败",
                        null);
            }
            if (params.get("uid") == null ) { // 参数异常
                return JsonUtil.toJson(ErrorCodes.CORE_PARAM_UNLAWFUL, "参数异常",
                        null);
            }
            long uid = Long.parseLong(params.get("uid").toString());
            long pid = 0;
            if(params.get("pid") != null){
            	pid = Long.parseLong(params.get("pid").toString());
            }else if(params.get("pids") != null){
            	pid = Long.parseLong(params.get("pids").toString());
            }else {
            	return JsonUtil.toJson(ErrorCodes.CORE_PARAM_UNLAWFUL, "参数异常",
                        null);
            }
            long result=favoritesService.insertByUidAndPid(uid, pid);
            Map<String, Long> data = Maps.newHashMap();
            data.put("num", result);
            if(result > 0){
                ret=JsonUtil.successResultJson(data);
            }else{
            	ret=JsonUtil.toJson(ErrorCodes.FAIL, "添加失败", data);
            }
        } catch (Exception e) {
            logger.error("Exception FavoritesAdd Process!", e);
            ret = JsonUtil.toJson(ErrorCodes.CORE_ERROR, "系统异常", null);
        }
        logger.info("Favorites_Add_Ret is:{}", ret);
        return ret;
    }
}
