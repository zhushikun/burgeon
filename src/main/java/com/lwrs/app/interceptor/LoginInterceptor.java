package com.lwrs.app.interceptor;

import com.lwrs.app.constant.Constants;
import com.lwrs.app.db.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    @Override public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean flag;
        User user=(User)request.getSession().getAttribute(Constants.SESSION_KEY);
        if(null==user){
            response.sendRedirect("login");
            flag = false;
        }else{
            flag = true;
        }
        return flag;
    }

    @Override public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
