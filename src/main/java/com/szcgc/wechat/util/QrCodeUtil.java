package com.szcgc.wechat.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.szcgc.wechat.entity.QrCodeTicket;
import com.szcgc.wechat.entity.SceneData;

/**
 * 微信带参数二维码的生成
 * 
 * @author yangyc
 *
 */
public class QrCodeUtil {
    public static final Logger logger = LoggerFactory.getLogger(QrCodeUtil.class);
    private static final String Get_Ticket_Url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN";

    /**
     * @description:获取带参数二维码的ticket
     * @param expireSeconds 过期时间，1296000为15天
     * @param sceneStr      场景值，每个二维码的场景值不一样,长度1-64
     * @return
     * @author yangyc
     * @date Mar 30, 2020
     * @version V1.0
     * @throws IOException
     * @throws JSONException
     */
    public static String getQrCodeTicket(String expireSeconds, String sceneStr) throws IOException, JSONException {
        String accessToken = TokenUtil.getAccessToken();
        // 看看accessToken的取值
        logger.info("getQrCodeTicket中取到的accessToken is: " + accessToken);
        String ticketUrl = Get_Ticket_Url.replace("TOKEN", accessToken);

        SceneData scene = new SceneData(sceneStr);
        Map<String, SceneData> actionInfo = new HashMap<String, SceneData>();
        actionInfo.put("scene", scene);

        QrCodeTicket ticket = new QrCodeTicket(expireSeconds, actionInfo);
        String ticketData = JsonUtil.objToJsonMap(ticket);
        String result = HttpClientUtil.post(ticketUrl, ticketData);
        // 看看post后，微信那边返回的result
        logger.info("getQrCodeTicket中post完ticketData后返回的result is: " + result);

        JSONObject resObj = new JSONObject(result);
        String codeTicket = resObj.getString("ticket");
        // 打印取到的ticket值
        logger.info("getQrCodeTicket中取到的codeTicket is: " + codeTicket);

        return codeTicket;
    }

//	public static String showQrCode(String expireSeconds, String sceneStr) throws IOException, JSONException {
//		String ticketStr = getQrCodeTicket(expireSeconds, sceneStr);
//		String ticket = HttpClientUtil.urlEncode(ticketStr);
//		System.out.println("-----------------------------------------encodeTicket: " + ticket);
//
//		String qrUrl = Change_QrCode_Url.replace("TICKET", ticket);
//		String res = HttpClientUtil.get(qrUrl);
//		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
//		System.out.println(res);
//		return null;
//	}

}
