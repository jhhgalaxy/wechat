package com.szcgc.trip.controller;

import com.alibaba.fastjson.JSON;
import com.szcgc.wechat.controller.ProjectController;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author JINLINGXUAN
 * @create 2021-08-25
 * 携程差旅-接收订单状态推送
 */

@Controller
@RequestMapping("/trip")
public class OderStatus {

    private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);

    @RequestMapping(value = "/status", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    @ResponseBody
    public String getOrderStatus(@RequestBody String json) {

        logger.info("====开始接收携程订单状态变更信息====");

        Map<String,String> resultMap=new HashMap<>();

        HttpEntity he;
        String result="";
        try {
            HttpResponse resp = doHttpPost("http://10.8.5.12:8081/cgc/trip/GetOrderStatus.jsp", json);
            if (resp.getStatusLine().getStatusCode() == 200) {
                he = resp.getEntity();
                result = EntityUtils.toString(he);
                logger.info("从OA接口获取的结果为：" + result);
            }
        } catch (Exception e) {
            result=e.getMessage();
            logger.info("调用OA接口报错：" + e.getMessage());
        }

        if("SUCCESS".equals(result)){
            resultMap.put("errno","0");
            resultMap.put("errmsg","");
        }else{
            resultMap.put("errno","1");
            resultMap.put("errmsg", result);
        }

        return JSON.toJSONString(resultMap);

    }

    public static HttpResponse doHttpPost(String url, String jsonParam) throws Exception {
        StringEntity entity = new StringEntity(jsonParam, "utf-8");//解决中文乱码问题
        entity.setContentEncoding("utf-8");
        entity.setContentType("application/json");
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(30000).setConnectionRequestTimeout(30000).setSocketTimeout(60000).build();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);
        httpPost.setEntity(entity);
        CloseableHttpClient client = HttpClients.createDefault();
        return client.execute(httpPost);
    }


}
