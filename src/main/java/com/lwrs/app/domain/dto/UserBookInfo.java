package com.lwrs.app.domain.dto;

import com.lwrs.app.domain.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class UserBookInfo extends BaseDto{

    private String shopName;

    private String userName;
    private String gender;
    private String phone;
    private String birthDate;

    private String bookType;
    private String bookDate;
}
