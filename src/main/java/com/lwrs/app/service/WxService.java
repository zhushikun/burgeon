package com.lwrs.app.service;

import com.lwrs.app.constant.WxConstant;
import com.lwrs.app.domain.bo.AppToken;
import com.lwrs.app.domain.dto.resp.WxGzhAccTokenResp;
import com.lwrs.app.domain.dto.resp.WxOauthAccTokenResp;
import com.lwrs.app.domain.dto.resp.WxUserInfoResp;
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

@Slf4j
@Service
public class WxService {


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
     * 根据code 获取 oauth的 access_token
     * @param code
     * @return
     */
    public WxOauthAccTokenResp getOAuthAccessToken(String code){
        String url = String.format(WxConstant.OAUTH_ACCESS_TOKEN_URL, code);
        String strResp = requestHandler.get(url);
        WxOauthAccTokenResp resp = GsonHelper.getGson().fromJson(strResp, WxOauthAccTokenResp.class);
        if(StringUtils.isEmpty(resp.getAccess_token())){
            log.error("request OAUTH_ACCESS_TOKEN_URL failed, code={}, resp={}", code, strResp);
            return null;
        }
        return resp;
    }

    /**
     * 由于access_token拥有较短的有效期，当access_token超时后，可以使用refresh_token进行刷新
     * refresh_token有效期为30天
     * @param refreshToken
     * @return
     */
    public WxOauthAccTokenResp refreshOAuthAccessToken(String refreshToken){
        String url = String.format(WxConstant.OAUTH_REFRESH_URL, refreshToken);
        String strResp = requestHandler.get(url);
        WxOauthAccTokenResp resp = GsonHelper.getGson().fromJson(strResp, WxOauthAccTokenResp.class);
        if(StringUtils.isEmpty(resp.getAccess_token())){
            log.error("request OAUTH_REFRESH_URL failed, refreshToken={}, resp={}", refreshToken, strResp);
            return null;
        }
        return resp;
    }


    public WxUserInfoResp getWxUserInfo(String accessToken, String openId){
        String url = String.format(WxConstant.USER_INFO_URL, accessToken, openId);
        String strResp = requestHandler.get(url);
        WxUserInfoResp resp = GsonHelper.getGson().fromJson(strResp, WxUserInfoResp.class);
        if(StringUtils.isEmpty(resp.getOpenid())){
            log.error("request OAUTH_REFRESH_URL failed, accessToken={} openId={}, resp={}", accessToken, openId, strResp);
            return null;
        }
        return resp;
    }

    /**
     * 公众号 交互access_token
     * @return
     */
    public WxGzhAccTokenResp getGzhAccessToken(){
        if(null == appTokenCache && refreshGzhAppToken()){
            return null;
        }
        WxGzhAccTokenResp tokenResp = WxGzhAccTokenResp.builder()
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
    public boolean refreshGzhAppToken(){
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
