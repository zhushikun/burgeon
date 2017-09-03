package com.lwrs.app.service;

import com.lwrs.app.domain.bo.AppToken;
import com.lwrs.app.domain.dto.WxAccTokenResp;
import com.lwrs.app.enums.RespCode;
import com.lwrs.app.service.remote.RequestHandler;
import com.lwrs.app.utils.GsonHelper;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Map;

@Slf4j
@Service
public class WxService {

//    private static String ACCESS_TOKEN_NAME = "access_token";

    @Setter
    private static AppToken appTokenCache;

    private static final String APP_TOKEN_REFRESH = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
    @Value("${WX_SECRET}")
    private String secret;
    @Value("${WX_APP_ID}")
    private String appId;


    @Autowired
    private RequestHandler requestHandler;

    /**
     * 公众号 交互access_token
     * @return
     */
    public WxAccTokenResp getAccessToken(){
        if(null == appTokenCache && refreshAppToken()){
            return null;
        }
        WxAccTokenResp tokenResp = WxAccTokenResp.builder()
            .access_token(appTokenCache.getAccess_token())
            .expireTime(appTokenCache.getExpireTime())
            .build();
        tokenResp.setRespCode(RespCode.OK);
        return tokenResp;
    }

    /**
     * 公众号 交互access_token
     * @return
     */
    public boolean refreshAppToken(){
        String respStr = requestHandler.get(String.format(APP_TOKEN_REFRESH, appId, secret));
        log.info("APP_TOKEN_REFRESH, resp={}", respStr);
        AppToken appToken = GsonHelper.getGson().fromJson(respStr, AppToken.class);
        if(StringUtils.isNotEmpty(appToken.getAccess_token())){
            Calendar now = Calendar.getInstance();
            now.add(Calendar.SECOND, appToken.getExpires_in() - 10 * 60);
            appToken.setExpireTime(now.getTime());
            appTokenCache = appToken;
            return true;
        }
        return false;
    }
}
