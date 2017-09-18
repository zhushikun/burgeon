package com.lwrs.app.db.entity;

import com.lwrs.app.domain.BaseDto;
import com.lwrs.app.enums.WxScopeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxOauthDB extends BaseDto{
    private Long id;
    private Long userId;
    private String openId;
    /**
     * @see WxScopeType#scope
     */
    private String scopeType;
    private String accessToken;
    private Date accessExpire;
    /**
     * refresh_token有效期为30天，当refresh_token失效之后，需要用户重新授权
     */
    private String refreshToken;
    private Date refreshExpire;
    /**
     * 用户授权的作用域，使用逗号分隔
     */
    private String scope;

    private Date createAt;
    private Date updateAt;
}
