package com.lwrs.app.service;

import com.lwrs.app.enums.FileType;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FileService {

    @Getter
    @Value("${PIC_DIR}")
    private String pictureDir;

    public String getAvatarPath(FileType fileType){
        return pictureDir + fileType.getSubPath();
    }

    public String getMaskImgName(Integer userId, String orgName){
        String uid = null == userId ? "" : String.valueOf(userId) + "_";
        return uid + System.nanoTime() + "." + orgName.replaceAll(".*\\.", "");
    }

}
