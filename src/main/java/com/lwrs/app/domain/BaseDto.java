package com.lwrs.app.domain;

import com.lwrs.app.utils.GsonHelper;

public class BaseDto {

    public String toString(){
           return GsonHelper.getGson().toJson(this);
    }
}
