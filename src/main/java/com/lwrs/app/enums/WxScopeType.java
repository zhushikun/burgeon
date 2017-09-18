package com.lwrs.app.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum WxScopeType {
    BASE("snsapi_base"),
    USER_INFO("snsapi_userinfo"),
    ;

    @Getter
    private String scope;
}
