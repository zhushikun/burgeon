package com.lwrs.app.interceptor;

import com.lwrs.app.constant.Constants;
import com.lwrs.app.constant.WxConstant;
import com.lwrs.app.utils.UserIdMask;
import com.lwrs.app.utils.UserLoginContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

public class LoginInterceptor implements HandlerInterceptor {

    private Long getUserId(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies){
            if(!Constants.USER_COOKIE_NAME.equals(cookie.getName())){
                return null;
            }
            String strValue = cookie.getValue();
            if(StringUtils.isEmpty(strValue)){
                return null;
            }
            return UserIdMask.unmask(strValue);
        }
        return null;
    }

    //todo 区分微信的 静默 和 提醒
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Long userId = getUserId(request);
        if(null != userId){
            UserLoginContext.setUserId(userId);
            return true;
        }

        String callBack = URLEncoder.encode(WxConstant.WX_GET_CODE_CALLBACK, "utf-8");
        StringBuffer urlBuffer = request.getRequestURL();
        if(null != request.getQueryString()){
            urlBuffer.append("?").append(request.getQueryString());
        }
        String origUrl = URLEncoder.encode(urlBuffer.toString(), "utf-8");
        String redirectUrl = String.format(WxConstant.OAUTH_BEGIN_SLIENCE_URL, callBack, origUrl);
        response.sendRedirect(redirectUrl);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
