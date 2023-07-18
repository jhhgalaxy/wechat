/**
 * Project:szcgcWechatServer
 * File:SuggestionController.java
 * Date:2020年4月9日
 * Author:chenxinli
 * Description:
 */
package com.szcgc.wechat.controller;

import com.szcgc.user.entity.User;
import com.szcgc.user.service.UserService;
import com.szcgc.wechat.smart.entity.Custom;
import com.szcgc.wechat.smart.entity.Suggestion;
import com.szcgc.wechat.smart.service.CompanyService;
import com.szcgc.wechat.smart.service.SuggestionService;
import com.szcgc.wechat.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenxinli
 * @date 2020年4月9日
 * 
 */
@Controller
public class SuggestionController {

    @Autowired
    private SuggestionService suggestionService;

    @Autowired
    private UserService userService;

    @Autowired
    private CompanyService companyService;

    /**
     * 添加投诉建议、入库、并同步给OA
     * @param complainant
     * @param cellphone
     * @param content
     * @return
     * @throws IOException
     */
    @ResponseBody
    @PostMapping(value = "/addSuggestion")
    public String addSuggestion(int complainant,String cellphone,String content) throws IOException {
        Map<String, String> map = new HashMap<String, String>();

        Suggestion suggestion = new Suggestion();
        suggestion.setComplainant(complainant);
        suggestion.setCellphone(cellphone);
        suggestion.setContent(content);

        List<Custom> customsByCellphone = companyService.getCustomsByCellphone(cellphone);
        if(customsByCellphone.size()>0){
            suggestion.setCustom_corp(customsByCellphone.get(0).getCustomName());
            suggestion.setCustom_type("合作企业");
        }else{
            suggestion.setCustom_type("普通用户");
        }
        Suggestion rst = suggestionService.saveSuggestion(suggestion);

        //传给OA
        if(rst!=null){
            map.put("code", "0");
            map.put("msg", "添加成功！");
        }else{
            map.put("code", "-1");
            map.put("msg", "添加失败,服务器繁忙，请稍后再试！");
        }
        return JsonUtil.objToJsonMap(map);
    }

    @GetMapping(value = "/addSuggestion")
    public String addSuggestion(){
        return "szcgc/complaint/addComplaint";
    }

    /**
     * 查看投诉建议信息
     * @param userid
     * @param model
     * @return
     */
    @RequestMapping(value = "/listComplaint")
    public String lisomplaint(int userid, HttpSession session, Model model){
        User user = (User)session.getAttribute("user");
        if(user!=null){
            //// TODO: 2020/4/22 按投诉人查询投诉列表
            model.addAttribute("list",suggestionService.getComplaintList(userid));

        }
        return "szcgc/complaint/listComplaint";
    }
}
