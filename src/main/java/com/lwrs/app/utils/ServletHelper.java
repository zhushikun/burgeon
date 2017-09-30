package com.lwrs.app.utils;

import com.lwrs.app.constant.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 需要请求上下文
 */
public class ServletHelper {

    public static HttpServletResponse getResponse(){
        ServletRequestAttributes servletAttrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return servletAttrs.getResponse();
    }

    public static HttpServletRequest getRequest(){
        ServletRequestAttributes servletAttrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return servletAttrs.getRequest();
    }

    public static void sendRedirect(String location) throws IOException {
        getResponse().sendRedirect(location);
    }


    /**
     * add cookie in path="/" age=1 year
     * @param name
     * @param value
     */
    public static void addCookie(String name, String value){
        addCookie(name, value, Constants.USER_COOKIE_AGE);
    }

    /**
     *
     * @param name
     * @param value
     * @param age seconds
     */
    public static void addCookie(String name, String value, int age){
        addCookie(getResponse(), name, value, age);
    }

    private static void addCookie(HttpServletResponse response, String name, String value, int age){
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(age);

        response.addCookie(cookie);
    }

    public static String getFromCookie(String key){
        return getFromCookie(getRequest(), key);
    }
    public static String getFromCookie(HttpServletRequest request, String key){
        Cookie[] cookies = request.getCookies();
        if(null == key || null == cookies){
            return null;
        }
        for(Cookie cookie : cookies){
            if(null == cookie || !key.equals(cookie.getName())){
                continue;
            }
            return cookie.getValue();
        }
        return null;
    }
}
