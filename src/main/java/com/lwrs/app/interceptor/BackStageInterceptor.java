package com.lwrs.app.interceptor;

import com.lwrs.app.constant.Constants;
import com.lwrs.app.utils.ServletHelper;
import com.lwrs.app.utils.UserIdMask;
import com.lwrs.app.utils.UserLoginContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BackStageInterceptor implements HandlerInterceptor {


    public Long getShopOwnerFromCookie(HttpServletRequest request){
        String strValue = ServletHelper.getFromCookie(request, Constants.SHOP_OWNER_COOKIE_NAME);
        if(StringUtils.isEmpty(strValue)){
            return null;
        }
        return UserIdMask.unmask(strValue);
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Long shopOwnerId = getShopOwnerFromCookie(request);
        if(null != shopOwnerId){
            UserLoginContext.setShopOwner(shopOwnerId);
            return true;
        }

        response.sendRedirect("/backstage/home");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
