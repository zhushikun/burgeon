package com.lwrs.app.db.entity;

import com.lwrs.app.domain.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShopOwnerDB extends BaseDto{
    private Long id;

    private Long userId;
    //后台用户名
    private String alias;
    //后台MD5密码
    private String code;
    private String shopIds;
    private String phone;
    private Date createAt;
    private Date updateAt;
}
