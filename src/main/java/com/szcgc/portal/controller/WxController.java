package com.szcgc.portal.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.szcgc.portal.entity.Session;
import com.szcgc.portal.vo.SessionVo;
import com.szcgc.wechat.util.HttpClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author JINLINGXUAN
 * @create 2022-08-04
 * 获取微信小程序用户授权信息
 */
@Controller
@RequestMapping("portal/wx")
public class WxController {

  private static final Logger logger = LoggerFactory.getLogger(WxController.class);

  private static final String APPID="wx60bc7d8aff3946ed";
  private static final String SECRET="7a1de6be97c90f3ec762782c1f7dadcc";

  @GetMapping(value = "login")
  @ResponseBody
  public SessionVo login(String code) throws ServletException, IOException {
    logger.info("接收到的用户code为："+code);

    String url = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";
    url=url.replace("APPID", APPID).replace("SECRET", SECRET).replace("JSCODE", code);
    logger.info("获取openid的地址为："+url);

    String result = HttpClientUtil.get(url);
    logger.info("接收到的微信返回信息为："+result);

    SessionVo vo=new SessionVo();
    vo.code=202;//未登录
    Session session=JSON.parseObject(result,Session.class);
    vo.session=session;
    if(session.errcode!=0){
      return vo;
    }
    String resultPortal=HttpClientUtil.post("http://ebridge.szcgc.com:4403/user/openid",result);
    logger.info("接收到统一门户的返回信息为："+resultPortal);
    JSONObject jsonPortal=JSONObject.parseObject(resultPortal);
    if(jsonPortal.getInteger("code")==200){
      vo.code=200;
    }
    return vo;
  }



}
