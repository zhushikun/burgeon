package com.lwrs.app.service.impl;

import com.lwrs.app.db.entity.FileLocationDB;
import com.lwrs.app.db.entity.UserDB;
import com.lwrs.app.db.mapper.FileLocationMapper;
import com.lwrs.app.db.mapper.UserMapper;
import com.lwrs.app.domain.dto.RespBaseDto;
import com.lwrs.app.enums.FileType;
import com.lwrs.app.enums.RespCode;
import com.lwrs.app.service.FileService;
import com.lwrs.app.utils.UserLoginContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;

@Slf4j
@Service
public class UserServiceImpl {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private FileLocationMapper fileLocationMapper;

    @Autowired
    private FileService fileService;

    public RespBaseDto uploadAvatar(MultipartFile file, Integer userId){
        try {
            UserDB userDB = userMapper.selectById(userId);
            if(null == userDB){
                log.error(String.format("uploadAvatar, get empty DB by userId=%s", userId));
                return RespBaseDto.of(RespCode.DB_NOT_EXIST);
            }

            String maskName = fileService.getMaskImgName(userId, file.getOriginalFilename());
            String pathStr = fileService.getAvatarPath(FileType.AVATAR) + "/" + maskName;
            byte[] bytes = file.getBytes();
            FileOutputStream fileOutputStream = new FileOutputStream(pathStr);
            fileOutputStream.write(bytes);
//            Path path = Paths.get(pathStr);
//            Files.write(path, bytes);
            log.info(String.format("uploadAvatar file save ok, path=%s", pathStr));

            FileLocationDB fileLocationDB = FileLocationDB.builder().subPath(FileType.AVATAR.getSubPath()).build();
            System.out.println(fileLocationDB.toString());
            Integer avatarId = 111; //todo fileLocationMapper。insert
            Validate.isTrue(avatarId > 0,
                String.format("uploadAvatar insert fileLocationDB failed, userId=%s, path=%s", userId, pathStr));
//
            UserDB userDBUpdate = UserDB.builder().id(userId).avatarId(avatarId).build();
            int updateNum = userMapper.updateById(userDBUpdate);
            Validate.isTrue(updateNum > 0,
                String.format("uploadAvatar update userDB failed, userId=%s, avatarId=%s", userId, avatarId));

            return RespBaseDto.of(RespCode.OK);
        }catch (Exception e){
            log.error("Exception in uploadAvatar", e);
        }
        return RespBaseDto.of(RespCode.EXCEPTION);
    }


    public RespBaseDto modifyBasicInfo(String phone, String alias){
        Integer userId = UserLoginContext.getUserId();
        log.info("modifyBasicInfo userId={}, phone={}, alias={}",
            userId, phone, alias);
        UserDB userDBUpdate = UserDB.builder()
            .id(userId)
            .phone(phone)
            .alias(alias)
            .build();
        int updateNum = userMapper.updateById(userDBUpdate);
        if(updateNum > 0){
            return RespBaseDto.of(RespCode.OK);
        }
        log.error("modifyBasicInfo update failed, userId={}", userId);
        return RespBaseDto.of(RespCode.DB_NOT_EXIST);
    }
}
