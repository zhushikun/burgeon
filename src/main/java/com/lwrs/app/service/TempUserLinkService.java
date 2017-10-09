package com.lwrs.app.service;

import com.lwrs.app.constant.Constants;
import com.lwrs.app.db.entity.TempUserLinkDB;
import com.lwrs.app.db.mapper.TempUserLinkMapper;
import com.lwrs.app.utils.ServletHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TempUserLinkService {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private TempUserLinkMapper tempUserLinkMapper;

    public String addTempUserLink(Long userId){
        String uuid = UUID.randomUUID().toString();
        TempUserLinkDB tempUserLinkDB = TempUserLinkDB.builder()
            .userId(userId)
            .tempUid(uuid)
            .build();
        tempUserLinkMapper.insert(tempUserLinkDB);
        return uuid;
    }

    public Long getUserIdByUUIdCookie(){
        String strValue = ServletHelper.getFromCookie(Constants.TEMP_USER_COOKIE_NAME);
        return getUserIdByTempUid(strValue);
    }

    public Long getUserIdByTempUid(String tempUid){
        if(StringUtils.isEmpty(tempUid)){
            return null;
        }
        TempUserLinkDB userLinkDB = tempUserLinkMapper.selectByTempUid(tempUid);
        if(null == userLinkDB){
            return null;
        }
        return userLinkDB.getUserId();
    }
}
