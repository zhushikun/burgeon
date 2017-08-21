package com.lwrs.app.enums;

import lombok.Getter;

public enum FileType {
    AVATAR("/avatar"),
    ;

    @Getter
    private String subPath;

    FileType(String subPath){
        this.subPath = subPath;
    }
}
