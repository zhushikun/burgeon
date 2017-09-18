package com.lwrs.app.domain.dto;

import com.lwrs.app.domain.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class WxOauthAccTokenResp extends BaseDto{


    /**
     * 失败时候返回
     */
    private String errcode;
    private String errmsg;

    /**
     * 成功时候返回
     */
    private String access_token;
    /**
     * access_token接口调用凭证超时时间，单位（秒）
     */
    private int expires_in;
    /**
     * refresh_token有效期为30天，当refresh_token失效之后，需要用户重新授权
     */
    private String refresh_token;
    private String openid;
    /**
     * 用户授权的作用域，使用逗号分隔
     */
    private String scope;
}
