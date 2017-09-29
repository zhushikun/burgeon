package com.lwrs.app.exception;

import lombok.NoArgsConstructor;

/**
 * Created by drjr on 17-7-18.
 */
@NoArgsConstructor
public class BurgeonException extends RuntimeException {
    public BurgeonException(String msg){
        super(msg);
    }

    public BurgeonException(String msg, Throwable e){
        super(msg, e);
    }

    public String getDisplayMsg() {
        return getMessage();
    }
}
