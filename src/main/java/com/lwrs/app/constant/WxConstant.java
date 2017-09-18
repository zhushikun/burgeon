package com.lwrs.app.constant;

public class WxConstant {
    public static final String WX_GET_CODE_CALLBACK =
        "http://localhost:8080/wx/oauth";

    public static final String WX_APP_ID = "wxceac24c676993f3e";
    public static final String WX_SECRET = "1b73554bd466efe7035af7a5f4e057cd";

    /**
     * scope:
     * snsapi_base （不弹出授权页面，直接跳转，只能获取用户openid），
     * snsapi_userinfo （弹出授权页面，可通过openid拿到昵称、性别、所在地。并且，即使在未关注的情况下，只要用户授权，也能获取其信息）
     */
    public static final String SNS_API_BASE = "snsapi_base";
    public static final String SNS_API_USER_INFO = "snsapi_userinfo";

    /**
     * redirect_uri 为接受
     * state 为用户想访问的资源 （urlEncode）
     */
    public static final String OAUTH_BEGIN_PROMPT_URL =
        "https://open.weixin.qq.com/connect/oauth2/authorize?"
            + "appid=" + WX_APP_ID
            + "&redirect_uri=%s"
            + "&response_type=code"
            + "&scope=" + SNS_API_USER_INFO
            + "&state=%s#wechat_redirect";

    public static final String OAUTH_BEGIN_SLIENCE_URL =
        "https://open.weixin.qq.com/connect/oauth2/authorize?"
            + "appid=" + WX_APP_ID
            + "&redirect_uri=%s"
            + "&response_type=code"
            + "&scope=" + SNS_API_BASE
            + "&state=%s#wechat_redirect";


    public static final String OAUTH_ACCESS_TOKEN_URL =
        "https://api.weixin.qq.com/sns/oauth2/access_token?"
            + "appid=" + WX_APP_ID
            + "&secret=" + WX_SECRET
            + "&code=%s"
            + "&grant_type=authorization_code";


    public static final String OAUTH_REFRESH_URL =
        "https://api.weixin.qq.com/sns/oauth2/refresh_token?"
            + "appid=" + WX_APP_ID
            + "&grant_type=refresh_token"
            + "&refresh_token=%s";

    public static final String USER_INFO_URL =
        "https://api.weixin.qq.com/sns/userinfo?"
            + "access_token=%s"
            + "&openid=%s"
            + "&lang=zh_CN";
}
