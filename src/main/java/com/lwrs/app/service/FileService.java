package com.lwrs.app.service;

import com.lwrs.app.db.entity.FileLocationDB;
import com.lwrs.app.db.mapper.FileLocationMapper;
import com.lwrs.app.enums.FileType;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;

@Slf4j
@Service
public class FileService {

    @Getter
    @Value("${PIC_DIR}")
    private String pictureDir;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private FileLocationMapper fileLocationMapper;


    public String getMaskImgName(Long userId, String origName){
        String uid = null == userId ? "" : String.valueOf(userId) + "_";
        return uid + System.nanoTime() + "." + origName.replaceAll(".*\\.", "");
    }

    /**
     *
     * @param file
     * @param userId
     * @param fileType
     * @return fileLocationID; null if failed
     */
    public Long uploadFile(MultipartFile file, Long userId, FileType fileType){
        try {
            String maskName = getMaskImgName(userId, file.getOriginalFilename());
            String subPath =  fileType.getSubPath() + "/" + maskName;

            String pathStr = pictureDir + subPath;
            byte[] bytes = file.getBytes();
            FileOutputStream fileOutputStream = new FileOutputStream(pathStr);
            fileOutputStream.write(bytes);
            log.info("uploadFile file save ok, path={}", pathStr);

            FileLocationDB fileLocationDB = FileLocationDB.builder()
                .userId(userId)
                .location(subPath)
                .type(fileType.name())
                .build();
            fileLocationMapper.insert(fileLocationDB);
            log.info("add fileLocation ok, id={}", fileLocationDB.getId());
            return fileLocationDB.getId();
        }catch (Exception e){
            log.error("Exception in uploadFile, userId={}", userId);
        }
        return null;
    }

}
