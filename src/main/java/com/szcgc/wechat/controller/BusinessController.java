/**
 * Project:szcgcWechatServer
 * File:BusinessController.java
 * Date:2020年4月9日
 * Author:chenxinli
 * Description:
 */
package com.szcgc.wechat.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.szcgc.user.service.UserService;
import com.szcgc.wechat.smart.entity.Custom;
import com.szcgc.wechat.smart.entity.Projectapply;
import com.szcgc.wechat.smart.entity.Projectdetail;
import com.szcgc.wechat.smart.entity.Unsendmessage;
import com.szcgc.wechat.smart.service.BusinessService;
import com.szcgc.wechat.smart.service.CompanyService;
import com.szcgc.wechat.smart.service.MessageService;
import com.szcgc.wechat.smart.service.ProjectService;
import com.szcgc.wechat.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.*;

/**
 * @author chenxinli
 * @date 2020年4月9日
 * 
 */
@Controller
public class BusinessController {

    private static final Logger logger = LoggerFactory.getLogger(BusinessController.class);

    @Autowired
    private CompanyService companyService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private BusinessService businessService;

    @ResponseBody
    @PostMapping(value = "/transproject")
    public String transProject(String data){
        logger.info("wechat-project-trans-insert:项目数据接收开始");

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<Projectdetail> list = objectMapper.readValue(data, new TypeReference<List<Projectdetail>>() {});
            Iterable<Projectdetail> iterable = projectService.updateProjectDetailForOA(list);
            logger.info("wechat-project-trans-insert:"+iterable.spliterator().getExactSizeIfKnown()+"条数据");
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("接收项目数据出错",e.getMessage());
        }

        return null;
    }

    @PostMapping(value = "/transcustom")
    public String transCustom(String data){
        logger.info("wechat-custom-trans-insert:客户数据接收开始");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<Custom> list = objectMapper.readValue(data, new TypeReference<List<Custom>>() {});
            companyService.removeAllCustom();
            Iterable<Projectdetail> iterable = companyService.saveAllCustom(list);
            logger.info("wechat-custom-trans-insert:"+iterable.spliterator().getExactSizeIfKnown()+"条数据");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @PostMapping(value = "/transmsg")
    public String transMsg(String data){
        logger.info("wechat-msg-trans-insert:消息数据接收开始");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<Unsendmessage> beanList = objectMapper.readValue(data, new TypeReference<List<Unsendmessage>>() {});
            List<Unsendmessage> unsendList = new ArrayList<>();
            for(Unsendmessage msg : beanList){
                Projectdetail projectdetail = projectService.getProjectDetailByMainid(msg.getLinkitem());
                List<String> receiverlist = companyService.getCellPhonesByCustomcode(projectdetail.getCustomid());
                for(String receiver:receiverlist){

                    Unsendmessage newmsg = msg;
                    newmsg.setReceiver(userService.findByCellphone(receiver).getId());
                    unsendList.add(newmsg);
                }
            }
            Iterable<Unsendmessage> iterable = messageService.saveUnsendmessages(unsendList);
            logger.info("wechat-msg-trans-insert:插入"+unsendList.spliterator().getExactSizeIfKnown()+"条数据");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @PostMapping(value = "/transprincipal")
    public String transPrincipalInfo(String data){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<Custom> beanList = objectMapper.readValue(data, new TypeReference<List<Custom>>() {});
            //Todo: 写入数据库
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @PostMapping(value = "/transinterest")
    public String transInterestInfo(String data){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<Custom> beanList = objectMapper.readValue(data, new TypeReference<List<Custom>>() {});
            //Todo: 写入数据库
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @RequestMapping(value = "/business/index")
    public String businessIndex(){
        return "szcgc/recommend/index";
    }

    @RequestMapping(value = "/business/inquiry")
    public String businessInquiry(){
        return "szcgc/recommend/inquiry";
    }

    @RequestMapping(value = "/business/result")
    public String businessResult(){
        return "szcgc/recommend/result";
    }

    @GetMapping(value = "/business/apply")
    public String businessApply(){
        return "szcgc/recommend/apply";
    }

    @ResponseBody
    @PostMapping(value = "/business/apply")
    public String businessApplyPost(Projectapply projectapply) throws IOException {
        Projectapply result = businessService.addProjectApply(projectapply);

        Map<String, String> map = new HashMap<String, String>();
        if(result!=null&&result.getId()>0){
            map.put("code", "0");
            map.put("msg", "添加成功！");
        }else{
            map.put("code", "-1");
            map.put("msg", "添加失败！");
        }
        return JsonUtil.objToJsonMap(map);
    }

    @RequestMapping(value = "/business/success")
    public String businessSuccess(){
        return "szcgc/recommend/success";
    }
}
