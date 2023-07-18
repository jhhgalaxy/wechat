package com.szcgc.wechat.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.szcgc.wechat.entity.UserInfo;
import com.szcgc.wechat.util.NetWorkAuthorizedUtil;

@Controller
@RequestMapping("cgcFC")
public class FunctionController {
    public static final Logger Logger = LoggerFactory.getLogger(FunctionController.class);

    /**
     * @description:处理网页授权时产生的回调
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     * @author yangyc
     * @date Apr 1, 2020
     * @version V1.0
     */
    @RequestMapping(method = RequestMethod.GET)
    public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~code is: " + code);
        try {
            UserInfo userInfo = NetWorkAuthorizedUtil.getUserInfo(code);
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~userInfo headImage is: " + userInfo.getHeadImgUrl());
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        return null;
    }

}
