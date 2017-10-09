package com.lwrs.app.interceptor;

import com.lwrs.app.constant.Constants;
import com.lwrs.app.service.TempUserLinkService;
import com.lwrs.app.service.UserService;
import com.lwrs.app.utils.ServletHelper;
import com.lwrs.app.utils.SpringContextUtils;
import com.lwrs.app.utils.UserLoginContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 在使用微信登录之前 只用uuid标识用户, 使用TempLoginInterceptor 拦截请求 并静默注册
 */
@Slf4j
public class TempLoginInterceptor implements HandlerInterceptor {
    private UserService userService;

    private TempUserLinkService tempUserLinkService;

    public void initBean(){
        userService = SpringContextUtils.getBeanByType(UserService.class);
        tempUserLinkService = SpringContextUtils.getBeanByType(TempUserLinkService.class);
    }


    /**
     * 在使用微信登录之前 只用uuid标识用户
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(null == userService){
            initBean();
        }
        Long userId = tempUserLinkService.getUserIdByUUIdCookie();
        if(null != userId){
            UserLoginContext.setUserId(userId);
            return true;
        }
        //没有uuid
        userId = userService.addNewUser();
        log.info("auto gen userId={}", userId);
        String uuid = tempUserLinkService.addTempUserLink(userId);
        ServletHelper.addCookie(Constants.TEMP_USER_COOKIE_NAME, uuid);
        UserLoginContext.setUserId(userId);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}
