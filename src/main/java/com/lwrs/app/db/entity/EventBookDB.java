package com.lwrs.app.db.entity;

import com.lwrs.app.domain.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * 预约
 */
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventBookDB extends BaseDto {
    private Long id;
    private Long shopId;

    private Long userId;
    private String userName;
    private String gender;
    private String phone;
    private Date birthDate;

    private String bookType;
    private Date bookDate;
    private Date createAt;
    private Date updateAt;
}
