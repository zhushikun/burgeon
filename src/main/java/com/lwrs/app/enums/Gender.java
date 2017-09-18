package com.lwrs.app.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Gender {
    MALE("1"),
    FEMALE("2"),
    UNKNOWN("0");

    @Getter
    private String code;

    public static Gender getByWxGender(String wxGender){
        for(Gender gender : Gender.values()){
            if(gender.getCode().equals(wxGender)){
                return gender;
            }
        }
        return UNKNOWN;
    }


}
