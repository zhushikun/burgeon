package com.lwrs.app.enums;

import lombok.Getter;

public enum RespCode {
    OK("0000", "ok"),

    INVALID_PARAM("1001", "invalid param"),
    EXCEPTION("1002", "exception happen"),
    DB_NOT_EXIST("1003", "not exist"),

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
