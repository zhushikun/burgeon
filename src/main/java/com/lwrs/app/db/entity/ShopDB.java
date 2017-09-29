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
public class ShopDB extends BaseDto{

    private Long id;
    private String shopName;

    private Long groupId;
    private String address;
    private String phone;

    private Date createAt;
    private Date updateAt;
}
