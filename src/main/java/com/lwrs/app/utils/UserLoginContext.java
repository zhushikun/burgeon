package com.lwrs.app.utils;

public class UserLoginContext {
    private static ThreadLocal<Long> userIdLocal= new ThreadLocal<>();
    private static ThreadLocal<Long> shopOwner= new ThreadLocal<>();


    public static void setUserId(Long userId){
        userIdLocal.set(userId);
    }
    public static Long getUserId(){
        return userIdLocal.get();
    }

    public static void setShopOwner(Long userId){
        shopOwner.set(userId);
    }

    public static Long getShopOwner(){
        return shopOwner.get();
    }
}
