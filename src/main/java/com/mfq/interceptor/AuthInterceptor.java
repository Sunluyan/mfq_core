package com.mfq.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mfq.annotation.LoginRequired;
import com.mfq.constants.Constants;
import com.mfq.constants.ErrorCodes;
import com.mfq.dataservice.context.UserIdHolder;
import com.mfq.service.user.UserService;
import com.mfq.utils.JsonUtil;
import com.mfq.utils.RequestUtils;

/**
 * Spring 认证拦截器 
 * Created by xingyongshan on 15/8/4.
 */
@Component
public class AuthInterceptor extends HandlerInterceptorAdapter
        implements Ordered {
    
    private static final Logger logger = LoggerFactory
            .getLogger(AuthInterceptor.class);

    @Resource
    UserService userService;
    
    public AuthInterceptor() {
    }
    
    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
        // 〇 非.5imfq.com域名 直接无视，系统错误。
        // 〇 如果是白名单地址（某些controller无需登录），直接通过，返回true
        // 〇 如果未登录且页面需要登录信息，则返回false
        // 〇 返回true
        String serverName = request.getServerName();
        logger.info("requestServerName:{}", serverName);
        String domainSuffix = Constants.COOKIE_DOMAIN;
//        if (!serverName.endsWith(domainSuffix)) {
//            response.setContentType("text/plain; charset=UTF-8");
//            response.getWriter().append("域名错误(必须*" + domainSuffix + ")");
//            return false;
//        }
        if (!checkLoginRequired(request, response, handler)) {
            return false;
        }
        return true;
    }
    
    
    @Override
    public void postHandle(
			HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {
    	System.out.println("ret ...");
	}
    
    
    


    /**
     * 检查登录需求
     *
     * @param request
     * @param response
     * @param handler
     * @return true表示OK，false表示登录失败，已经跳转到登录页面或者ajax结果
     * @throws Exception
     */
    private boolean checkLoginRequired(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof org.springframework.web.servlet.resource.ResourceHttpRequestHandler) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        LoginRequired loginRequired = handlerMethod
                .getMethodAnnotation(LoginRequired.class);
        if (loginRequired == null) {
            loginRequired = handlerMethod.getBeanType()
                    .getAnnotation(LoginRequired.class);
        }
        if (loginRequired != null && !UserIdHolder.isLogin()) {
            // 需要登录 但 未登录!!!!!
            RequestUtils.writeResponse(request, response,
                    JsonUtil.toJson(ErrorCodes.CORE_NEED_LOGIN, "需要登录", null));
            return false;
        }
        return true;
    }
    
    
    
    
    

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
