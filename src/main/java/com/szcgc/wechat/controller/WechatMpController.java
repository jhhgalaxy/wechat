package com.szcgc.wechat.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author JINLINGXUAN
 * @create 2020-08-26
 * 获取微信小程序用户授权信息
 */
@Controller
@RequestMapping("mpGetOpenid")
public class WechatMpController {

  private static final Logger logger = LoggerFactory.getLogger(WechatMpController.class);

  @RequestMapping(method = RequestMethod.GET)
  public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String code = request.getParameter("code");
    logger.info("接收到的用户code为："+code);

    final String APPID = "wxb8915526a5770bf7";
    final String SECRET = "8d29e840a635483b32ed5aaed389f425";

    String url = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";
    url=url.replace("APPID", APPID).replace("SECRET", SECRET).replace("JSCODE", code);
    logger.info("获取openid的地址为："+url);
    URL urlObj = new URL(url);
    // 打开连接
    URLConnection connection = urlObj.openConnection();
    InputStream is = connection.getInputStream();
    byte[] b = new byte[1024];
    int len;
    StringBuilder sb = new StringBuilder();
    while ((len = is.read(b)) != -1) {
      sb.append(new String(b, 0, len));
    }

    Map<String, String> returnStr = new HashMap<String, String>();

    String result = sb.toString();
    logger.info("接收到的返回信息为："+result);
    JSONObject jsonObject = JSON.parseObject(result);
    String openid = jsonObject.getString("openid");

    if (openid!=null && openid.length()>0) {
      returnStr.put("status", "success");
      returnStr.put("openid", openid);
    } else {
      returnStr.put("status", "fail");
      returnStr.put("openid", "");
    }

    logger.info("接口返回信息为："+JSON.toJSONString(returnStr));

    PrintWriter out = response.getWriter();
    out.print(JSON.toJSONString(returnStr));
    out.flush();
    out.close();

    return null;
  }

  @RequestMapping(method = RequestMethod.POST)
  public String doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    return null;
  }

}
