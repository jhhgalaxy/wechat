package com.szcgc.wechat.util;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.szcgc.wechat.entity.TemplateMessage;
import com.szcgc.wechat.entity.TemplateMsgData;

public class TemplateMsgUtil {
    private final static Logger logger = LoggerFactory.getLogger(TemplateMsgUtil.class);
    private final static String Set_Url = "https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=ACCESS_TOKEN";
    private final static String Get_Url = "https://api.weixin.qq.com/cgi-bin/template/get_industry?access_token=ACCESS_TOKEN";
    private final static String Send_Url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";

    /**
     * 设置所属行业
     * 
     * @throws IOException
     */
    public static void industrySet() throws IOException {
        String accessTocken = TokenUtil.getAccessToken();
        String url = Set_Url.replace("ACCESS_TOKEN", accessTocken);
        String industryData = "{\"industry_id1\":\"34\",\"industry_id2\":\"41\"}";
        String res = HttpClientUtil.post(url, industryData);
        logger.info("industrySet   res is : " + res);
    }

    /**
     * 获取所属行业
     */
    public static void industryGet() {
        String accessTocken = TokenUtil.getAccessToken();
        String url = Get_Url.replace("ACCESS_TOKEN", accessTocken);
        String res = HttpClientUtil.get(url);
        logger.info("industryGet   res is : " + res);
    }

    /**
     * 发送模板消息
     * 
     * @throws IOException
     */
    public static void sendTemplateMsg(String toUser, String name, String moneyNum, String url) throws IOException {
        String accessTocken = TokenUtil.getAccessToken();
        String sendUrl = Send_Url.replace("ACCESS_TOKEN", accessTocken);
        String templateData = creatTemplateMsg(toUser, name, moneyNum, url);
        String res = HttpClientUtil.post(sendUrl, templateData);
        logger.info("sendTemplateMsg   res is : " + res);
    }

    /**
     * 创建模板消息
     * 
     * @param toUser   收消息的人的openID
     * @param name     客户姓名
     * @param moneyNum 应缴纳金额
     * @param url      跳转链接
     * @throws IOException
     */
    public static String creatTemplateMsg(String toUser, String name, String moneyNum, String url) throws IOException {

        Map<String, TemplateMsgData> data = new HashMap<String, TemplateMsgData>();
        TemplateMsgData first = new TemplateMsgData(name + "，您好，请按期缴纳本期担保费用", "#173177");
        data.put("first", first);
        TemplateMsgData keyword1 = new TemplateMsgData("客户：" + name, "#173177");
        data.put("keyword1", keyword1);
        TemplateMsgData keyword2 = new TemplateMsgData("类型：担保费用", "#173177");
        data.put("keyword2", keyword2);
        TemplateMsgData keyword3 = new TemplateMsgData("当期应缴：" + moneyNum, "#173177");
        data.put("keyword3", keyword3);
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        TemplateMsgData keyword4 = new TemplateMsgData(format.format(date), "#173177");
        data.put("keyword4", keyword4);
        TemplateMsgData remark = new TemplateMsgData("详情请点击查看", "#173177");
        data.put("remark", remark);
        // 模板ID，需要在配置表中读取
        String template_id = "LuPa1yTCjoEo_Occ-lA_y4ZyQtwbqfzi5NnOQ2aBHnk";
        TemplateMessage tm = new TemplateMessage(toUser, template_id, url, data);
        String tmData = JsonUtil.objToJsonMap(tm);
        System.out.println(tmData);

        return tmData;
    }
}
