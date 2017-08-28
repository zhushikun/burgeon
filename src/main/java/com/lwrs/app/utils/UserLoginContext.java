package com.lwrs.app.utils;

import com.lwrs.app.constant.Constants;
import com.lwrs.app.db.entity.UserDB;

import javax.servlet.http.HttpSession;

public class UserLoginContext {
    private static ThreadLocal<HttpSession> userSession= new ThreadLocal<>();


    public static void recordSession(HttpSession session){
        userSession.set(session);
    }

    //todo maskuid
    public static Integer getUserId(){
        HttpSession session = userSession.get();
        return (null == session) ? null : (Integer) session.getAttribute(Constants.USER_KEY);
    }
}
