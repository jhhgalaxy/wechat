package com.szcgc.wechat.util;

import com.szcgc.config.WebEnvConfig;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.szcgc.wechat.entity.UserInfo;

import java.io.UnsupportedEncodingException;

/**
 * 网页授权
 * 
 * @author yangyc
 *
 */
public class NetWorkAuthorizedUtil {
    public static final Logger logger = LoggerFactory.getLogger(NetWorkAuthorizedUtil.class);
    private final static String AUTHORIZE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE#wechat_redirect";
    // private final static String APPID = "wx81c5bea2c3a412a3";
    // private final static String APPSECRET = "a7b41475946c12838d8655d266130389";
    // private final static String REDIRECT_URI =
    // "http://113.108.70.118/szcgcWechatServer/cgcFC";
    private final static String scope = "snsapi_userinfo";
    //private final static String scope = "snsapi_base";//仅获取openid，无需用户授权
    private final static String AUTHORIZED_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
    private final static String WEB_USERINFO_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
    public static String authorize_url = AUTHORIZE_URL.replace("APPID", WebEnvConfig.APPPROPERTIES.getProperty("APPID"))
            .replace("REDIRECT_URI", WebEnvConfig.APPPROPERTIES.getProperty("REDIRECT_URI")).replace("SCOPE", scope);

    /**
     * @description:通过用户同意后接收到的code，取出用户信息（不论用户是否已经关注）
     * @param code
     * @return
     * @author yangyc
     * @date Apr 1, 2020
     * @version V1.0
     * @throws JSONException
     */
    public static UserInfo getUserInfo(String code) throws JSONException, UnsupportedEncodingException {
        String authorizedTokenUrl = AUTHORIZED_TOKEN_URL
                .replace("APPID", WebEnvConfig.APPPROPERTIES.getProperty("APPID"))
                .replace("SECRET", WebEnvConfig.APPPROPERTIES.getProperty("APPSECRET")).replace("CODE", code);
        String authorizedTokenStr = HttpClientUtil.get(authorizedTokenUrl);
        // 是否取到了token，在log中打印一下
        logger.info("getUserInfo 网页授权中取到的authorizedTokenStr is: " + authorizedTokenStr);

        JSONObject authorizedTokenObj = new JSONObject(authorizedTokenStr);
        if ((authorizedTokenObj != null) && (!authorizedTokenObj.has("errcode"))) {
            String webAccessToken = authorizedTokenObj.getString("access_token");
            String openId = authorizedTokenObj.getString("openid");
            String scope = authorizedTokenObj.getString("scope");
            // 获取用户信息
            if (scope.equals("snsapi_userinfo")) {
                String webUserInfoUrl = WEB_USERINFO_URL.replace("ACCESS_TOKEN", webAccessToken).replace("OPENID",
                        openId);
                String webUserInfoStr = HttpClientUtil.get(webUserInfoUrl);
                // 用token去获取用户信息，是否取到了，在log中打印一下
                logger.info("getUserInfo 用token换取用户信息webUserInfoStr is: " + webUserInfoStr);

                JSONObject webUserInfoObj = new JSONObject(webUserInfoStr);
                if ((webUserInfoObj != null) && (!webUserInfoObj.has("errcode"))) {
                    UserInfo userInfo = new UserInfo();
                    userInfo.setOpenId(webUserInfoObj.getString("openid"));
                    userInfo.setNickName(webUserInfoObj.getString("nickname"));
                    userInfo.setSex(webUserInfoObj.getInt("sex"));
                    userInfo.setCity(webUserInfoObj.getString("city"));
                    userInfo.setProvince(webUserInfoObj.getString("province"));
                    userInfo.setCountry(webUserInfoObj.getString("country"));
                    userInfo.setHeadImgUrl(webUserInfoObj.getString("headimgurl"));
                    return userInfo;
                }
            }
        }
        return null;
    }
}
