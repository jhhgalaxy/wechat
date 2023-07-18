package com.szcgc.wechat.controller;

import com.szcgc.wechat.util.SignUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.szcgc.config.WebEnvConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.szcgc.wechat.util.JsonUtil;
import com.szcgc.wechat.util.MessageUtil;

/**
 * 处理微信公众平台发过来的get、post请求；
 * 
 * @author yangyc
 *
 */
@Controller
@RequestMapping("cgcWX")
public class WechatController {
    public static final Logger logger = LoggerFactory.getLogger(WechatController.class);

    @ResponseBody
    @RequestMapping(value = "demo", method = RequestMethod.GET, produces = { WebEnvConfig.MVC_PRODUCES_JSON })
    public String demo(HttpServletRequest request) throws Exception {
        return "hello";
    }

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // signature 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
        String signature = request.getParameter("signature");
        // timestamp 时间戳
        String timestamp = request.getParameter("timestamp");
        // nonce 随机数
        String nonce = request.getParameter("nonce");
        // echostr 随机字符串
        String echostr = request.getParameter("echostr");

        // 检验signature进行校验,成功则返回echostr,接入成功;
        if (SignUtil.checkSignature(signature, timestamp, nonce)) {
            PrintWriter out = response.getWriter();
            out.print(echostr);
            out.flush();
            out.close();
        }
        return null;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("utf8");
        response.setCharacterEncoding("utf8");

        // 处理消息和事件推送,requestmap中为键值对
        Map<String, String> requestMap = MessageUtil.parseRequest(request.getInputStream());
        // 理论上能打印出收到的各类消息的参数，因为收到消息的层数和模板是一样的；且所有的参数都已经存到了requestMap中，这样之前的步骤才是正确的；
        logger.info("WechatController doPost 接收到的requestMap is: " + JsonUtil.objToJson(requestMap));

        // 回复数据包
        String resXml = MessageUtil.dealResponse(requestMap);
        logger.info("WechatController doPost 回复的XML is" + resXml);

        PrintWriter out = response.getWriter();
        out.print(resXml);
        out.flush();
        out.close();

        return null;
    }
}
