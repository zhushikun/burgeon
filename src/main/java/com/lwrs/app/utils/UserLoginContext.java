package com.lwrs.app.utils;

public class UserLoginContext {
    private static ThreadLocal<Long> userIdLocal= new ThreadLocal<>();


    public static void setUserId(Long userId){
        userIdLocal.set(userId);
    }

    public static Long getUserId(){
        return userIdLocal.get();
    }
}
