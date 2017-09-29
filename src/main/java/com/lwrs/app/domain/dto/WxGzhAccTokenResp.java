package com.lwrs.app.domain.dto;

import lombok.Builder;

import java.util.Date;

@Builder
public class WxGzhAccTokenResp extends BaseResp {

    private String access_token;
    private Date expireTime;
}
