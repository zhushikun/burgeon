package com.lwrs.app.db.entity;

import com.lwrs.app.domain.BaseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter@Setter
public class User extends BaseDto{
    private Long id;
    private String alias;
    private String pwd;
    private String name;
    private String gender;
    private String phone;
    private String birthday;
    private String address;
    private Date createAt;
    private Date updateAt;
}


 
 
 
 
 
 
 
 
 
 
