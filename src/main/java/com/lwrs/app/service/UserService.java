package com.lwrs.app.service;

import com.lwrs.app.db.entity.UserDB;
import com.lwrs.app.db.entity.WxOauthDB;
import com.lwrs.app.db.entity.WxUserDB;
import com.lwrs.app.db.mapper.FileLocationMapper;
import com.lwrs.app.db.mapper.UserMapper;
import com.lwrs.app.db.mapper.WxOauthMapper;
import com.lwrs.app.db.mapper.WxUserMapper;
import com.lwrs.app.domain.dto.BaseResp;
import com.lwrs.app.domain.dto.WxUserInfoResp;
import com.lwrs.app.domain.dto.WxOauthAccTokenResp;
import com.lwrs.app.enums.FileType;
import com.lwrs.app.enums.Gender;
import com.lwrs.app.enums.RespCode;
import com.lwrs.app.enums.WxScopeType;
import com.lwrs.app.utils.UserLoginContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Slf4j
@Service
public class UserService {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private UserMapper userMapper;
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private WxUserMapper wxUserMapper;
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private WxOauthMapper wxOauthMapper;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private FileLocationMapper fileLocationMapper;

    @Autowired
    private FileService fileService;

    @Autowired
    private WxService wxService;

    /**
     * 修改头像
     * @param file
     * @param userId
     * @return
     */
    public BaseResp uploadAvatar(MultipartFile file, Long userId){
        try {
            UserDB userDB = userMapper.selectById(userId);
            if(null == userDB){
                log.error(String.format("uploadAvatar, get empty DB by userId=%s", userId));
                return BaseResp.of(RespCode.NOT_EXIST);
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

            return BaseResp.of(RespCode.OK);
        }catch (Exception e){
            log.error("Exception in uploadAvatar", e);
        }
        return BaseResp.of(RespCode.EXCEPTION);
    }


    /**
     * 修改手机 别名
     * @param phone
     * @param alias
     * @return
     */
    public BaseResp modifyBasicInfo(String phone, String alias){
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
            return BaseResp.of(RespCode.OK);
        }
        log.error("modifyBasicInfo update failed, userId={}", userId);
        return BaseResp.of(RespCode.NOT_EXIST);
    }



    public Long wxLoginOrRegister(String code){
        WxOauthAccTokenResp oauthAccTokenResp = wxService.getOAuthAccessToken(code);
        if(null == oauthAccTokenResp){
            //todo
            return null;
        }

        String openId = oauthAccTokenResp.getOpenid();
        UserDB userDB = userMapper.selectByOpenId(openId);
        if(null != userDB){
            return userDB.getId();
        }
        //没有记录
        userDB = UserDB.builder().build();
        Long userId = userMapper.insert(userDB);

        long current = System.currentTimeMillis();
        Date accessExpire = new Date(current + oauthAccTokenResp.getExpires_in() * 1000);
        Date refreshExpire = new Date(current + 30 * 3600 * 1000);
        WxOauthDB wxOauthDB = WxOauthDB.builder()
            .userId(userId)
            .openId(openId)
            .scopeType(WxScopeType.BASE.getScope())
            .accessToken(oauthAccTokenResp.getAccess_token())
            .accessExpire(accessExpire)
            .refreshToken(oauthAccTokenResp.getRefresh_token())
            .refreshExpire(refreshExpire)
            .scope(oauthAccTokenResp.getScope())
            .build();
        wxOauthMapper.insert(wxOauthDB);


        return userId;

    }

    public void getWxUserInfo(Long userId, String openId, String accessToken){
        WxUserInfoResp wxUserInfoResp = wxService.getWxUserInfo(accessToken, openId);
        if(null == wxUserInfoResp) {
            //todo
            return;
        }

        WxUserDB wxUserDB = WxUserDB.builder()
            .userId(userId)
            .openId(wxUserInfoResp.getOpenid())
            .nickName(wxUserInfoResp.getNickname())
            .sex(wxUserInfoResp.getSex())
            .province(wxUserInfoResp.getProvince())
            .city(wxUserInfoResp.getCity())
            .country(wxUserInfoResp.getCountry())
            .headImgUrl(wxUserInfoResp.getHeadimgurl())
            .privilege(wxUserInfoResp.getPrivilege())
            .unionId(wxUserInfoResp.getUnionid())
            .build();
        wxUserMapper.insert(wxUserDB);

        //update userDB
        UserDB userDB = UserDB.builder()
            .id(userId)
            .alias(wxUserInfoResp.getNickname())
            .gender(Gender.getByCode(wxUserInfoResp.getSex()).getCode())
            .address(String.format("%s%s", wxUserInfoResp.getProvince(), wxUserInfoResp.getCity()))
            .useWxAvatar(1)
            .build();
        userMapper.updateById(userDB);
    }

}
