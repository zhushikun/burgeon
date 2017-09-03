package com.lwrs.app.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Gender {
    M("1"),
    F("2"),
    X("0");

    @Getter
    private String wxGender;

    public static Gender getByWxGender(String wxGender){
        for(Gender gender : Gender.values()){
            if(gender.getWxGender().equals(wxGender)){
                return gender;
            }
        }
        return X;
    }


}
