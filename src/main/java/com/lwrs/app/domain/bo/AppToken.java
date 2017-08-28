package com.lwrs.app.domain.bo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter@Setter
public class AppToken {

    private String access_token;
    private int expires_in;
    private Date expireTime;
}
