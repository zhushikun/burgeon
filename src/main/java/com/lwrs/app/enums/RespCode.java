package com.lwrs.app.enums;

import lombok.Getter;

public enum RespCode {
    OK("0000", "ok"),

    INVALID_PARAM("1001", "请求不合法"),
    EXCEPTION("1002", "处理异常"),
    NOT_EXIST("1003", "请求记录不存在"),

    ;

    RespCode(String code, String msg){
        this.code = code;
        this.msg = msg;
    }


    @Getter
    private String code;
    @Getter
    private String msg;
}
