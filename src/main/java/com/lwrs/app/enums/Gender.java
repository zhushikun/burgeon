package com.lwrs.app.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Gender {
    M("1"),
    F("2"),
    X("0");

    @Getter
    private String code;

    public static Gender getByCode(String wxGender){
        for(Gender gender : Gender.values()){
            if(gender.getCode().equals(wxGender)){
                return gender;
            }
        }
        return X;
    }

    public static Gender getByName(String name){
        for(Gender gender : Gender.values()){
            if(gender.name().equals(name)){
                return gender;
            }
        }
        return null;
    }


}
