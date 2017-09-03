package com.lwrs.app.db.entity;

import com.lwrs.app.domain.BaseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter@Setter
@Builder
@NoArgsConstructor
public class UserDB extends BaseDto{
    private Long id;
    private String alias;
    private String pwd;
    private String name;
    /**
     *M是男性，F是女性，X是未知
     */
    private String gender;
    private String phone;
    /**
     * yyyy-mm-dd
     */
    private String birthday;
    private String address;
    private Long avatarId;
    /**
     * 1 使用微信头像, 0 不用
     */
    private int useWxAvatar;
    private Date createAt;
    private Date updateAt;


}


 
 
 
 
 
 
 
 
 
 
