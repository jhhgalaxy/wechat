package com.szcgc.wechat.util;

import com.szcgc.config.WebEnvConfig;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.szcgc.wechat.entity.AccessToken;

/**
 * 向微信公众号给出的地址请求token，并进行处理
 * 
 * @author yangyc
 * @date 2020-3-23
 */
public class TokenUtil {

    private static final Logger logger = LoggerFactory.getLogger(TokenUtil.class);

    private static final String Get_Token_Url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    // private static final String APPID = "wx81c5bea2c3a412a3";
    // private static final String APPSECRET = "a7b41475946c12838d8655d266130389";

    private static AccessToken accessToken;

    /**
     * 向指定的url发请求，得到含有token和过期时间的字符串,
     * 
     * @param
     * @return
     */
    private static void getToken() {
        String url = Get_Token_Url.replace("APPID", WebEnvConfig.APPPROPERTIES.getProperty("APPID"))
                .replace("APPSECRET", WebEnvConfig.APPPROPERTIES.getProperty("APPSECRET"));

        String tokenJsonStr = HttpClientUtil.get(url);
        logger.info(tokenJsonStr); // System.out.println(tokenJsonStr);

        // JSONObject jsonObj = JSONObject.fromObject(tokenStr);
        try {
            JSONObject tokenJsonObj = new JSONObject(tokenJsonStr);
            String token = tokenJsonObj.getString("access_token");
            String expiresIn = tokenJsonObj.getString("expires_in");
            // 创建并存储AccessToken对象
            accessToken = new AccessToken(token, expiresIn);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * @description:供外部调用,获取当前有效的access_token；
     * @return
     * @author yangyc
     * @date Mar 25, 2020
     * @version V1.0
     */
    public static String getAccessToken() {
        if (accessToken == null || accessToken.isExpired()) {
            getToken();
        }
        return accessToken.getAccessToken();
    }

}
