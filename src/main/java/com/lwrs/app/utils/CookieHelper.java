package com.lwrs.app.utils;

import com.lwrs.app.constant.Constants;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class CookieHelper {

    /**
     * add cookie in path="/" age=1 year
     * @param response
     * @param name
     * @param value
     */
    public static void addCookie(HttpServletResponse response, String name, String value){
        addCookie(response, name, value, Constants.USER_COOKIE_AGE);
    }

    public static void addCookie(HttpServletResponse response, String name, String value, int age){
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(age);

        response.addCookie(cookie);
    }
}
