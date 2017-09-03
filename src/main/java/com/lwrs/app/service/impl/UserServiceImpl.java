package com.lwrs.app.service.impl;

import com.lwrs.app.db.entity.FileLocationDB;
import com.lwrs.app.db.entity.UserDB;
import com.lwrs.app.db.entity.WxUserDB;
import com.lwrs.app.db.mapper.FileLocationMapper;
import com.lwrs.app.db.mapper.UserMapper;
import com.lwrs.app.db.mapper.WxUserMapper;
import com.lwrs.app.domain.dto.RespBaseDto;
import com.lwrs.app.domain.dto.UserInfoReq;
import com.lwrs.app.enums.FileType;
import com.lwrs.app.enums.Gender;
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

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private UserMapper userMapper;
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private WxUserMapper wxUserMapper;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private FileLocationMapper fileLocationMapper;

    @Autowired
    private FileService fileService;

    /**
     * 修改头像
     * @param file
     * @param userId
     * @return
     */
    public RespBaseDto uploadAvatar(MultipartFile file, Long userId){
        try {
            UserDB userDB = userMapper.selectById(userId);
            if(null == userDB){
                log.error(String.format("uploadAvatar, get empty DB by userId=%s", userId));
                return RespBaseDto.of(RespCode.DB_NOT_EXIST);
            }

            Long avatarId = fileService.uploadFile(file, userId, FileType.AVATAR);
            Validate.isTrue(null != avatarId,
                "uploadAvatar insert fileLocationDB failed, userId={}", userId);

            UserDB userDBUpdate = UserDB.builder()
                .id(userId)
                .avatarId(avatarId)
                .useWxAvatar(0)
                .build();
            int updateNum = userMapper.updateById(userDBUpdate);
            Validate.isTrue(updateNum > 0,
                String.format("uploadAvatar update userDB failed, userId=%s, avatarId=%s", userId, avatarId));

            return RespBaseDto.of(RespCode.OK);
        }catch (Exception e){
            log.error("Exception in uploadAvatar", e);
        }
        return RespBaseDto.of(RespCode.EXCEPTION);
    }


    /**
     * 修改手机 别名
     * @param phone
     * @param alias
     * @return
     */
    public RespBaseDto modifyBasicInfo(String phone, String alias){
        Long userId = UserLoginContext.getUserId();
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

    /**
     * 注册
     * @param userInfoReq
     * @return
     */
    public boolean register(UserInfoReq userInfoReq){
        //validate req
        UserDB userDB = UserDB.builder()
            .alias(userInfoReq.getNickname())
            .gender(Gender.getByWxGender(userInfoReq.getSex()).name())
            .address(String.format("%s%s", userInfoReq.getProvince(), userInfoReq.getCity()))
            .useWxAvatar(1)
            .build();
        Long userId = userMapper.insert(userDB);
        Validate.notNull(userId, "insert userDB failed, DB={}", userDB);


        WxUserDB wxUserDB = WxUserDB.builder()
            .userId(userId)
            .openId(userInfoReq.getOpenid())
            .nickName(userInfoReq.getNickname())
            .sex(userInfoReq.getSex())
            .province(userInfoReq.getProvince())
            .city(userInfoReq.getCity())
            .country(userInfoReq.getCountry())
            .headImgUrl(userInfoReq.getHeadimgurl())
            .privilege(userInfoReq.getPrivilege())
            .unionId(userInfoReq.getUnionid())
            .build();
        Long dbId = wxUserMapper.insert(wxUserDB);
        Validate.notNull(dbId, "insert wxUserDB failed, DB={}", wxUserDB);

        return true;
    }
}
