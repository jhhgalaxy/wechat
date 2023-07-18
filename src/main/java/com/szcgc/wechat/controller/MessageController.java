/**
 * Project:szcgcWechatServer
 * File:MessageController.java
 * Date:2020年4月9日
 * Author:chenxinli
 * Description:
 */
package com.szcgc.wechat.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.szcgc.user.entity.User;
import com.szcgc.user.service.UserService;
import com.szcgc.wechat.smart.entity.Sentmessage;
import com.szcgc.wechat.smart.entity.Suggestion;
import com.szcgc.wechat.smart.entity.Unsendmessage;
import com.szcgc.wechat.smart.service.MessageService;
import com.szcgc.wechat.smart.service.SuggestionService;
import com.szcgc.wechat.sms.SMSManages;
import com.szcgc.wechat.util.CacheUtil;
import com.szcgc.wechat.util.CommonUtil;
import com.szcgc.wechat.util.JsonUtil;
import com.szcgc.wechat.util.SundryUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenxinli
 * @since 2020年4月9日
 */
@Controller
@RequestMapping(value = "message")
public class MessageController {

    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    UserService userService;

    @Autowired
    MessageService messageService;

    @Autowired
    SuggestionService suggestionService;

    private static final int MSGLIST_SIZE = 10;

    /**
     * 发送手机验证码
     *
     * @param phone
     * @param userid
     * @return
     * @throws IOException
     */
    @ResponseBody
    @PostMapping(value = "/sendSMS")
    public String sendSMS(String phone, int userid) throws IOException {
        Map<String, String> map = new HashMap<String, String>();
        if (CommonUtil.isMobile(phone) && userid > 0) {
            int code = CommonUtil.randToInt(100000, 999999);
            String msg = "您的手机绑定验证码为: " + code + ",请尽快完成验证。";
            SundryUtil.log(msg);

            SMSManages smsManages = new SMSManages();
            String re = smsManages.sendSMS(phone, msg, "1");

            if (StringUtils.equals("100", re)) {
                // 保存在缓存里,以用户id为key值，手机号-验证码-时间为value
                CacheUtil.putObject("phone_code_check_" + userid, phone + "_" + code + "_" + CommonUtil.format(LocalDateTime.now()));

                map.put("code", "0");
                map.put("msg", "验证码发送成功！");
            } else {
                map.put("code", "-1");
                map.put("msg", "短信验证码发送失败，请稍后再试。");
                SundryUtil.log("Wechat sms send fail:" + re);
            }
        } else {
            map.put("code", "-1");
            map.put("msg", "短信验证码发送失败，请确认您填写了正确的手机号码。");
        }

        return JsonUtil.objToJsonMap(map);

    }

    /**
     * 发送手机验证码(以手机号码为Key)
     *
     * @param phone
     * @return
     * @throws IOException
     */
    @ResponseBody
    @PostMapping(value = "/sendSMSByPhone")
    public String sendSMSByPhone(String phone) throws IOException {
        Map<String, String> map = new HashMap<String, String>();
        if (CommonUtil.isMobile(phone)) {
            int code = CommonUtil.randToInt(100000, 999999);
            String msg = "您的手机绑定验证码为: " + code + ",请尽快完成验证。";
            SundryUtil.log(msg);

            SMSManages smsManages = new SMSManages();
            String re = smsManages.sendSMS(phone, msg, "1");

            if (StringUtils.equals("100", re)) {
                // 保存在缓存里,以用户id为key值，手机号-验证码-时间为value
                CacheUtil.putObject("phone_code_check_" + phone, phone + "_" + code + "_" + CommonUtil.format(LocalDateTime.now()));

                map.put("code", "0");
                map.put("msg", "验证码发送成功！");
            } else {
                map.put("code", "-1");
                map.put("msg", "短信验证码发送失败，请稍后再试。");
                SundryUtil.log("Wechat sms send fail:" + re);
            }
        } else {
            map.put("code", "-1");
            map.put("msg", "短信验证码发送失败，请确认您填写了正确的手机号码。");
        }

        return JsonUtil.objToJsonMap(map);

    }

    /**
     * 验证手机验证码(以手机号码为Key)
     *
     * @param code
     * @param phone
     * @param checkType
     * @return
     * @throws IOException
     */
    @ResponseBody
    @PostMapping(value = "/checkSmsCodeByPhone")
    public String checkSmsCode(String code, String phone, String checkType) throws IOException {
        // 返回信息
        Map<String, String> map = new HashMap<String, String>();


        SundryUtil.log("code" + code);
        SundryUtil.log("phone:" + phone);
        SundryUtil.log("checkType:" + checkType);


        map.put("code", "-1");
        map.put("msg", "服务器繁忙，请稍后发起验证！");

        if (StringUtils.isNotBlank(phone)) {
            String value = CacheUtil.getObject("phone_code_check_" + phone);


            SundryUtil.log("phone_code_check_:" + value);
            if (StringUtils.isNotBlank(value)) {
                String[] valueArr = value.split("_");
                if (valueArr != null && valueArr.length == 3) {
                    String cachePhone = valueArr[0];
                    String cacheCode = valueArr[1];
                    String cacheDate = valueArr[2];
                    SundryUtil.log("cachePhone:" + cachePhone);
                    SundryUtil.log("cacheCode:" + cacheCode);
                    SundryUtil.log("cacheDate:" + cacheDate);
                    // 判断发送时间加上2分钟是否大于当前时间
                    LocalDateTime nowDate = LocalDateTime.now();
                    LocalDateTime afterDate = CommonUtil.getLocalDateTime(cacheDate).plusMinutes(2);
                    if (afterDate.isAfter(nowDate)) {
                        if (StringUtils.equals(phone, cachePhone) && StringUtils.equals(code, cacheCode)) {

                            map.put("code", "0");
                            map.put("msg", "校验成功！");

                        } else {
                            map.put("code", "-1");
                            map.put("msg", "验证码错误！");
                        }
                    } else {
                        map.put("code", "-1");
                        map.put("msg", "验证码已过期！");
                    }
                } else {
                    map.put("code", "-1");
                    map.put("msg", "验证码获取错误！");
                }
            } else {
                map.put("code", "-1");
                map.put("msg", "验证码已被清空，请稍后在发起验证！");
            }
        }
        return JsonUtil.objToJsonMap(map);


    }


    /**
     * 验证手机验证码
     *
     * @param userid
     * @param code
     * @param phone
     * @param checkType
     * @return
     * @throws IOException
     */
    @ResponseBody
    @PostMapping(value = "/checkSmsCode")
    public String checkSmsCode(String userid, String code, String phone, String checkType) throws IOException {
        // 返回信息
        Map<String, String> map = new HashMap<String, String>();


        SundryUtil.log("userid:" + userid);
        SundryUtil.log("code" + code);
        SundryUtil.log("phone:" + phone);
        SundryUtil.log("checkType:" + checkType);


        map.put("code", "-1");
        map.put("msg", "服务器繁忙，请稍后发起验证！");

        if (StringUtils.isNotBlank(userid)) {
            String value = CacheUtil.getObject("phone_code_check_" + userid);


            SundryUtil.log("phone_code_check_:" + value);
            if (StringUtils.isNotBlank(value)) {
                String[] valueArr = value.split("_");
                if (valueArr != null && valueArr.length == 3) {
                    String cachePhone = valueArr[0];
                    String cacheCode = valueArr[1];
                    String cacheDate = valueArr[2];
                    SundryUtil.log("cachePhone:" + cachePhone);
                    SundryUtil.log("cacheCode:" + cacheCode);
                    SundryUtil.log("cacheDate:" + cacheDate);
                    // 判断发送时间加上2分钟是否大于当前时间
                    LocalDateTime nowDate = LocalDateTime.now();
                    LocalDateTime afterDate = CommonUtil.getLocalDateTime(cacheDate).plusMinutes(2);
                    if (afterDate.isAfter(nowDate)) {
                        if (StringUtils.equals(phone, cachePhone) && StringUtils.equals(code, cacheCode)) {
                            User user = userService.find(Integer.parseInt(userid));
                            User userByphone = userService.findByCellphone(phone);
                            if (StringUtils.equals("bindUser", checkType)) {// 如果是绑定人员，则加入数据库
                                // 判断该手机是否已绑定人员
                                if (userByphone != null && user.getId() == userByphone.getId()) {// 如果已经绑定
                                    map.put("code", "-1");
                                    map.put("msg", "抱歉，该手机号已经绑定微信号！");
                                } else {
                                    int result = userService.binding(user, phone);
                                    if (result > 0) {
                                        map.put("code", "0");
                                        map.put("msg", "校验成功！");
                                    } else {
                                        map.put("code", "-1");
                                        map.put("msg", "插入数据库数据失败，请联系管理员！");
                                    }
                                }
                            } else if (StringUtils.equals("unbindUser", checkType)) {// 如果是用户解绑
                                int result = userService.unbinding(user);
                                if (result > 0) {
                                    map.put("code", "0");
                                    map.put("msg", "解绑成功！");
                                } else {
                                    map.put("code", "-1");
                                    map.put("msg", "更新数据库数据失败，请联系管理员！");
                                }
                            } else {// 如果只是验证手机验证码，直接通过
                                map.put("code", "0");
                                map.put("msg", "校验成功！");
                            }
                        } else {
                            map.put("code", "-1");
                            map.put("msg", "验证码错误！");
                        }
                    } else {
                        map.put("code", "-1");
                        map.put("msg", "验证码已过期！");
                    }
                } else {
                    map.put("code", "-1");
                    map.put("msg", "验证码获取错误！");
                }
            } else {
                map.put("code", "-1");
                map.put("msg", "验证码已被清空，请稍后在发起验证！");
            }
        }
        return JsonUtil.objToJsonMap(map);


    }

    /**
     * 消息列表
     *
     * @param userid
     * @param page
     * @return
     * @throws IOException
     */
    @ResponseBody
    @PostMapping(value = "/listMsg")
    public String listMsg(int userid, int page) throws IOException {
        Map<String, Object> map = messageService.listMsg(userid, page, MSGLIST_SIZE);
        return JsonUtil.objToJsonMap(map);
    }

    @GetMapping(value = "/listMsg")
    public String msg() throws IOException {
        return "szcgc/center/msg/listMsg";
    }

    /**
     * 查看消息内容
     *
     * @param userid
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/viewMsg")
    public String viewMsg(int userid, int id, Model model) {
        Sentmessage sentmessage = messageService.getSentmessageByID(id);
        model.addAttribute("msg", sentmessage);
        return "szcgc/center/msg/viewMsg";
    }

    /**
     * 接收OA推送来的待发消息，并存储（未实现加密及访问IP校验）
     *
     * @param param
     * @return
     * @throws IOException
     */
    @ResponseBody
    @PostMapping(value = "/transMsg")
    public String transMsg(String param) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> readValue = mapper.readValue(param, Map.class);
        List list = (List) readValue.get("data");
        List<Unsendmessage> msgList = new ArrayList<>();
        SundryUtil.log("接收到OA来的消息:" + msgList.toString());
        for (Object item : list) {
            Map<String, String> map = (Map<String, String>) item;
            String phone = map.get("phone");
            User user = userService.findByCellphone(phone);
            if (user != null) {
                Unsendmessage unsendmessage = new Unsendmessage();
                unsendmessage.setReceiver(user.getId());
                unsendmessage.setMsgtitle(map.get("title"));
                unsendmessage.setMsgcontent(map.get("content"));
                unsendmessage.setMsgtype(Integer.valueOf(map.get("type")));
                unsendmessage.setLinkitem(Integer.valueOf(map.get("id")));

                if (!StringUtils.isEmpty(map.get("reply")) && "3".equals(map.get("type"))) {
                    Suggestion suggestion = suggestionService.find(Integer.valueOf(map.get("id")));
                    suggestion.setReply(map.get("reply"));
                    suggestionService.saveSuggestion(suggestion);
                } else {
                    unsendmessage.setProject(map.get("projectname"));
                    unsendmessage.setManager(map.get("manager"));
                    unsendmessage.setManagerphone(map.get("managerphone"));
                }
                msgList.add(unsendmessage);
            }


        }
        messageService.saveUnsendmessages(msgList);
        System.out.println(list);
        return "success";

    }
}
