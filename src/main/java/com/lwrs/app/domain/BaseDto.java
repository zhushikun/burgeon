package com.lwrs.app.domain;

import com.google.gson.GsonBuilder;

public class BaseDto {

    public String toString(){
           return new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").setPrettyPrinting().create().toJson(this);
    }
}
