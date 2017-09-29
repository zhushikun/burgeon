package com.lwrs.app.domain;

import com.lwrs.app.utils.JacksonHelper;

public class BaseDto {

    public String toString(){
//           return GsonHelper.getGson().toJson(this);
        return JacksonHelper.obj2Str(this, this.getClass());
    }

}
