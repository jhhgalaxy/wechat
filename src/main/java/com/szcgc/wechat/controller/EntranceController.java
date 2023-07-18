/**
 * Project:szcgcWechatServer
 * File:EntranceController.java
 * Date:2020年4月9日
 * Author:chenxinli
 * Description:
 */
package com.szcgc.wechat.controller;

import com.szcgc.user.entity.User;
import com.szcgc.user.service.UserService;
import com.szcgc.wechat.entity.UserInfo;
import com.szcgc.wechat.sms.SMSManages;
import com.szcgc.wechat.util.NetWorkAuthorizedUtil;
import com.szcgc.wechat.util.SignUtil;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;

/**
 * @author chenxinli
 * @date 2020年4月9日
 * 
 */
@Controller
public class EntranceController {

    @Autowired
    UserService userService;

    /**
     * 测试主页
     * @return
     */
    @GetMapping(value = "/home")
    public String test(){
        SMSManages sm = new SMSManages();
        sm.getInfo();
        return "home";
    }

    @GetMapping(value = "/test")
    public String test2(){
        return "szcgc/center/test";
    }

    /**
     * 用户中心主页
     * @param session
     * @param model
     * @return
     */
    @GetMapping(value = "index")
    public String index(HttpSession session,Model model){
        User user = (User)session.getAttribute("user");
        boolean hasbinding = userService.hasbinding(user.getId());
        boolean hascompany = userService.hasCompany(user.getId());
        model.addAttribute("hasbinding",hasbinding);
        model.addAttribute("hascompany",hascompany);
        model.addAttribute("userid",user.getId());

        return "szcgc/center/index";
    }

    /**
     * 将用户重定向到微信获取授权入口
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    @GetMapping(value = "/startauth")
    public String sendAuthReq(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //request.setAttribute("code","888");
        System.out.println("发起网页授权："+NetWorkAuthorizedUtil.authorize_url);
        //request.getRequestDispatcher(NetWorkAuthorizedUtil.authorize_url).forward(request, response);
        //request.getRequestDispatcher(WebEnvConfig.APPPROPERTIES.getProperty("REDIRECT_URI")).forward(request, response);

        //return "redirect:auth?code=888&returnURI="+request.getAttribute("returnURI");
        return "redirect:"+NetWorkAuthorizedUtil.authorize_url;
    }

    @RequestMapping(value = "/checkToken",method = RequestMethod.GET)
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

    /**
     * 接收微信授权CODE，获取微信授权；新建/保存用户；转到授权前访问页面
     * @param code
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/auth")
    public String auth(String code,HttpServletRequest request, HttpServletResponse response) throws JSONException, UnsupportedEncodingException {
        String uri = request.getRequestURI();
        System.out.println("访问URI为："+uri);
        System.out.println("code为："+code);

        //获取微信授权
        UserInfo userInfo = NetWorkAuthorizedUtil.getUserInfo(code);
//        UserInfo userInfo = new UserInfo();
//        userInfo.setOpenId("123456");
//        userInfo.setNickName("点点");
        //初次获取授权将新建用户
        User user = userService.findByOpenid(userInfo.getOpenId());
        if(user==null){
            user = new User();
            user.setFollowtime(LocalDateTime.now());
            user.setName(userInfo.getNickName());
            user.setOpenid(userInfo.getOpenId());
            userService.saveUser(user);
        }

        //保存user到SESSION
        HttpSession session = request.getSession();
        session.setAttribute("user",user);

        session.setAttribute("userinfo",userInfo);
        //返回上次访问页面
        String returnURI = (String)session.getAttribute("returnUri");
        System.out.println("返回地址uri："+returnURI);
        return "redirect:"+returnURI;
    }

    @RequestMapping(value = "szcgc/common/noData")
    public String noData(String emptyMsg,Model model){
        String tipContent ="暂无数据";
        String tipTitle = "暂无数据";
        if(StringUtils.equals("zwxm", emptyMsg)){
            tipContent = "暂无项目";
            tipTitle = "项目列表";
        }
        model.addAttribute("tipContent",tipContent);
        model.addAttribute("tipTitle",tipTitle);
        return "szcgc/common/noData";
    }
}
