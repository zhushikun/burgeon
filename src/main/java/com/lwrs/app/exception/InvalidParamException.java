package com.lwrs.app.exception;

import lombok.NoArgsConstructor;

/**
 * Created by drjr on 17-7-18.
 */
@NoArgsConstructor
public class InvalidParamException extends BurgeonException {
    public InvalidParamException(String msg){
        super(msg);
    }

    public InvalidParamException(String msg, Throwable e){
        super(msg, e);
    }

    public String getDisplayMsg() {
        return getMessage();
    }
}
