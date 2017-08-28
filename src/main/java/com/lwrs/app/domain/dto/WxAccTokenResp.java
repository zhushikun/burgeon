package com.lwrs.app.domain.dto;

import lombok.Builder;

import java.util.Date;

@Builder
public class WxAccTokenResp extends RespBaseDto {

    private String access_token;
    private Date expireTime;
}
