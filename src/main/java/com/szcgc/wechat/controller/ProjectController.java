/**
 * Project:szcgcWechatServer
 * File:ProjectController.java
 * Date:2020年4月9日
 * Author:chenxinli
 * Description:
 */
package com.szcgc.wechat.controller;

import com.szcgc.config.WebEnvConfig;
import com.szcgc.user.entity.User;
import com.szcgc.user.service.UserService;
import com.szcgc.wechat.smart.entity.Projectdetail;
import com.szcgc.wechat.smart.service.ProjectService;
import com.szcgc.wechat.util.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenxinli
 * @date 2020年4月9日
 * @description 项目数据全部由OA中获取而来
 */
@Controller
public class ProjectController {

    private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

//    /**
//     * 查看项目列表
//     * @param userid
//     * @param model
//     * @return
//     * @throws IOException
//     */
//    @RequestMapping(value = "/project")
//    public String project(int userid, Model model) throws IOException {
//        logger.info("wechat-project:userid=" + userid);
//        User user = userService.find(userid);
//        String url_project = WebEnvConfig.APPPROPERTIES.getProperty("PROJECT_INDEX");
//        String url = url_project + "?phone=" + user.getCellphone();
//
//        final String json = HttpClientUtil.get(url);
//        Map<String, Object> map = (HashMap<String, Object>) JsonUtil.jsonToObj(json, HashMap.class);
//
//        if ((int) map.get("code") == 1) {
//            model.addAttribute("unloanCount", map.get("countNo"));
//            model.addAttribute("loaningcount", map.get("countYes"));
//            model.addAttribute("loanedcount", map.get("countJb"));
//            model.addAttribute("unloanList", map.get("listProjectNo"));
//            model.addAttribute("loaningList", map.get("listProjectYes"));
//            model.addAttribute("loanedList", map.get("listProjectJb"));
//            model.addAttribute("hasYewpz", map.get("hasYewpz"));
//            model.addAttribute("hasPrice", map.get("hasPrice"));
//            model.addAttribute("searchDivCount", map.get("searchDivCount"));
//            model.addAttribute("searchDivWidth", map.get("searchDivWidth"));
//            model.addAttribute("projectPriceValueArr", map.get("projectPriceValueArr"));
//            model.addAttribute("projectPriceLabelArr", map.get("projectPriceLabelArr"));
//            model.addAttribute("businessClassList", map.get("listProjectType"));
//        } else {
//            return "redirect:szcgc/common/noData?emptyMsg=zwxm";
//        }
//
//
//        return "szcgc/center/project/project";
//    }
//
//    /**
//     * AJAX获取OA中项目列表数据
//     * @param userid
//     * @param status
//     * @param order
//     * @param businessclass
//     * @param priceType
//     * @param page
//     * @param model
//     * @return
//     * @throws IOException
//     */
//    @GetMapping(value = "/listproject")
//    public String listProject(int userid, int status,String order, String businessclass, String priceType, int page, Model model) throws IOException {
//        logger.info("wechat-project:userid=" + userid + " status=" + status);
//        User user = userService.find(userid);
//
//        String url_project = WebEnvConfig.APPPROPERTIES.getProperty("PROJECT_LIST");
//        StringBuilder sb = new StringBuilder(url_project);
//        sb.append("?phone=" + user.getCellphone());
//        sb.append("&status="+status);
//        sb.append("&order="+order);
//        sb.append("&type="+businessclass);
//        sb.append("&priceType="+priceType);
//        String url = sb.toString();
//
//        final String json = HttpClientUtil.get(url);
//        Map<String, Object> map = (HashMap<String, Object>) JsonUtil.jsonToObj(json, HashMap.class);
//        if ((int) map.get("code") == 1) {
//            model.addAttribute("hasYewpz", map.get("hasYewpz"));
//            model.addAttribute("hasPrice", map.get("hasPrice"));
//            model.addAttribute("searchDivCount", map.get("searchDivCount"));
//            model.addAttribute("searchDivWidth", map.get("searchDivWidth"));
//            model.addAttribute("projectPriceValueArr", map.get("projectPriceValueArr"));
//            model.addAttribute("projectPriceLabelArr", map.get("projectPriceLabelArr"));
//            model.addAttribute("businessClassList", map.get("listProjectType"));
//        } else {
//            return "redirect:szcgc/common/noData?emptyMsg=zwxm";
//        }
//
//        return "szcgc/center/project/listProject";
//    }
//
//    /**
//     * AJAX获取OA中项目详细信息
//     * @param userid
//     * @param mainid
//     * @param model
//     * @return
//     * @throws IOException
//     */
//    @RequestMapping(value = "/viewproject")
//    public String viewProject(int userid,int mainid,Model model) throws IOException {
//        logger.info("wechat-project:userid=" + userid + " mainid=" + mainid);
//
//        User user = userService.find(userid);
//
//        String url_project = WebEnvConfig.APPPROPERTIES.getProperty("PROJECT_VIEW");
//        StringBuilder sb = new StringBuilder(url_project);
//        sb.append("?mainid=" + mainid);
//
//        String url = sb.toString();
//
//        final String json = HttpClientUtil.get(url);
//        Map<String, Object> map = (HashMap<String, Object>) JsonUtil.jsonToObj(json, HashMap.class);
//        if ((int) map.get("code") == 1) {
//            model.addAttribute("xiangmmc", map.get("xiangmmc"));
//            model.addAttribute("fangkje", map.get("fangkje"));
//            model.addAttribute("yewpz", map.get("yewpz"));
//            model.addAttribute("fangkrq", map.get("fangkrq"));
//            model.addAttribute("daoqhkr", map.get("daoqhkr"));
//            model.addAttribute("xiangmjl", map.get("xiangmjl"));
//            model.addAttribute("yinghrq_benjin", map.get("yinghrq_benjin"));
//            model.addAttribute("yinghje_benjin", map.get("yinghje_benjin"));
//            model.addAttribute("yinghrq_lixi", map.get("yinghrq_lixi"));
//            model.addAttribute("yinghje_lixi", map.get("yinghje_lixi"));
//            model.addAttribute("statusImg", map.get("statusImg"));
//        } else {
//            return "redirect:szcgc/common/noData?emptyMsg=zwxm";
//        }
//
//        return "szcgc/center/project/viewProject";
//    }


    @RequestMapping(value = "/project")
    public String project(int userid, String businessclass, String amountrange, @RequestParam(defaultValue = "1")int page, Model model) {
        logger.info("wechat-project:userid=" + userid);
        boolean hasCompany = userService.hasCompany(userid);
        if (!hasCompany) {
            return "redirect:szcgc/common/noData?emptyMsg=zwxm";
        }
        User user = userService.find(userid);

        //切分金额
        double amount_min = 0f;
        double amount_max = Double.MAX_VALUE;
        if (amountrange!=null&&StringUtils.isNotBlank(amountrange)) {// 放款金额
            String[] priceArr = amountrange.split("-");// 格式0-5，5-10，10
            if (priceArr != null) {
                if (priceArr.length == 1) {// 只有一个表示大于
                    amount_min = Double.parseDouble(priceArr[0]);
                    amount_max = Double.MAX_VALUE;
                } else if (priceArr.length == 2) {// 两个表示区间，0-5表示5以下
                    amount_min = Double.parseDouble(priceArr[0]);
                    amount_max = Double.parseDouble(priceArr[1]);
                }
            }
        }
        if(businessclass==null||StringUtils.isNotBlank(businessclass)){
            businessclass = "";
        }
        Page<Projectdetail> unloanList = projectService.getUnLoan(user.getCellphone(), businessclass, amount_min, amount_max, page);
        Page<Projectdetail> loaningList = projectService.getLoaning(user.getCellphone(), businessclass, amount_min, amount_max, page);
        Page<Projectdetail> loanedList = projectService.getLoaned(user.getCellphone(), businessclass, amount_min, amount_max, page);
        int unloanCount = unloanList.getNumberOfElements();
        int loaningcount = loaningList.getNumberOfElements();
        int loanedcount = loanedList.getNumberOfElements();

        if (loaningcount == 0l && loanedcount == 0l) {
            return "redirect:szcgc/common/noData?emptyMsg=zwxm";
        }

        boolean hasYewpz = false;
        boolean hasPrice = false;
        int searchDivCount = 1;
        String searchDivWidth = "100%";

        List<String> businessClassList = projectService.listBusinessClass(user.getCellphone());
        if (businessClassList != null && businessClassList.size() > 0) {
            searchDivCount++;
            hasYewpz = true;
        }

        //获取金额筛选范围
        String[] projectPriceValueArr = null;// 金额范围值
        String[] projectPriceLabelArr = null;// 金额范围键
        String projectPriceValue = WebEnvConfig.APPPROPERTIES.getProperty("projectPriceValue");
        String projectPriceLabel = WebEnvConfig.APPPROPERTIES.getProperty("projectPriceLabel");

        if (StringUtils.isNotBlank(projectPriceValue) && StringUtils.isNotBlank(projectPriceLabel)) {
            projectPriceValueArr = projectPriceValue.split(",");
            projectPriceLabelArr = projectPriceLabel.split(",");
            if (projectPriceValueArr != null && projectPriceLabelArr != null && projectPriceValueArr.length > 0 && projectPriceValueArr.length == projectPriceLabelArr.length) {
                searchDivCount++;
                hasPrice = true;
            }
        }
        if (searchDivCount == 2) {
            searchDivWidth = "50%";
        } else if (searchDivCount == 3) {
            searchDivWidth = "33.3%";
        }
        model.addAttribute("unloanCount", unloanCount);
        model.addAttribute("loaningcount", loaningcount);
        model.addAttribute("loanedcount", loanedcount);
        model.addAttribute("unloanList", unloanList);
        model.addAttribute("loaningList", loaningList);
        model.addAttribute("loanedList", loanedList);
        model.addAttribute("hasYewpz", hasYewpz);
        model.addAttribute("hasPrice", hasPrice);
        model.addAttribute("searchDivCount", searchDivCount);
        model.addAttribute("searchDivWidth", searchDivWidth);
        model.addAttribute("projectPriceValueArr", projectPriceValueArr);
        model.addAttribute("projectPriceLabelArr", projectPriceLabelArr);
        model.addAttribute("businessClassList", businessClassList);

        return "szcgc/center/project/project";
    }

    @RequestMapping(value = "/listproject")
    public String listProject(int userid, @RequestParam(defaultValue = "1")int status, String businessclass, String amountRange, @RequestParam(defaultValue = "1")int page, Model model) {
        logger.info("wechat-project:userid=" + userid + " status=" + status);
        User user = userService.find(userid);

        boolean hasYewpz = false;
        boolean hasPrice = false;
        int searchDivCount = 1;
        String searchDivWidth = "100%";

        List<String> businessClassList = projectService.listBusinessClass(user.getCellphone());
        if (businessClassList != null && businessClassList.size() > 0) {
            searchDivCount++;
            hasYewpz = true;
        }

        //获取金额筛选范围
        String[] projectPriceValueArr = null;// 金额范围值
        String[] projectPriceLabelArr = null;// 金额范围键
        String projectPriceValue = WebEnvConfig.APPPROPERTIES.getProperty("projectPriceValue");
        String projectPriceLabel = WebEnvConfig.APPPROPERTIES.getProperty("projectPriceLabel");

        if (StringUtils.isNotBlank(projectPriceValue) && StringUtils.isNotBlank(projectPriceLabel)) {
            projectPriceValueArr = projectPriceValue.split(",");
            projectPriceLabelArr = projectPriceLabel.split(",");
            if (projectPriceValueArr.length > 0 && projectPriceValueArr.length == projectPriceLabelArr.length) {
                searchDivCount++;
                hasPrice = true;
            }
        }
        if (searchDivCount == 2) {
            searchDivWidth = "50%";
        } else if (searchDivCount == 3) {
            searchDivWidth = "33.3%";
        }

        model.addAttribute("hasYewpz", hasYewpz);
        model.addAttribute("hasPrice", hasPrice);
        model.addAttribute("searchDivCount", searchDivCount);
        model.addAttribute("searchDivWidth", searchDivWidth);
        model.addAttribute("projectPriceValueArr", projectPriceValueArr);
        model.addAttribute("projectPriceLabelArr", projectPriceLabelArr);
        model.addAttribute("businessClassList", businessClassList);

        return "szcgc/center/project/listProject";
    }

//    @ResponseBody
//    @PostMapping(value = "/listProject")
//    public String getProjectlist(int userid,int status,String businessclass,String amountRange,int curPage, Model model) throws IOException {
//        logger.info("wechat-project:userid="+userid);
//        boolean hasCompany = userService.hasCompany(userid);
//        if(!hasCompany){
//            return "redirect:szcgc/common/noData?emptyMsg=zwxm";
//        }
//        User user = userService.find(userid);
//
//        //切分金额
//        double amount_min = Double.NaN;
//        double amount_max = Double.MAX_VALUE;
//        if(StringUtils.isNotBlank(amountRange)){// 放款金额
//            String[] priceArr = amountRange.split("-");// 格式0-5，5-10，10
//            if(priceArr!=null){
//                if(priceArr.length==1){// 只有一个表示大于
//                    amount_min = Double.parseDouble(priceArr[0]);
//                    amount_max = Double.MAX_VALUE;
//                }else if(priceArr.length==2){// 两个表示区间，0-5表示5以下
//                    amount_min = Double.parseDouble(priceArr[0]);
//                    amount_max = Double.parseDouble(priceArr[1]);
//                }
//            }
//        }
//        Map<String,Object> map = new HashMap<>();
//        if(status==0){
//            Page<Projectdetail> unloanList = projectService.getUnLoan(user.getCellphone(),businessclass,amount_min,amount_max,curPage);
//            Long unloanCount = unloanList.getTotalElements();
//            map.put("count", unloanCount);
//            map.put("data", unloanList.getContent());
//        }else if(status==1){
//            Page<Projectdetail> loaningList = projectService.getLoaning(user.getCellphone(),businessclass,amount_min,amount_max,curPage);
//            Long loaningcount = loaningList.getTotalElements();
//            map.put("count", loaningcount);
//            map.put("data", loaningList.getContent());
//        }else if(status==2){
//            Page<Projectdetail> loanedList = projectService.getLoaned(user.getCellphone(),businessclass,amount_min,amount_max,curPage);
//            Long loanedcount = loanedList.getTotalElements();
//            map.put("count", loanedcount);
//            map.put("data", loanedList.getContent());
//        }
//
//        return JsonUtil.objToJsonMap(map);
//    }

    @ResponseBody
    @PostMapping(value = "/queryproject")
    public String queryProject(int userid, @RequestParam(defaultValue = "1")int status, @RequestParam(defaultValue = "")String businessclass, @RequestParam(defaultValue = "")String amountRange, @RequestParam(defaultValue = "1")int curPage, Model model) throws IOException {
        logger.info("wechat-project:userid=" + userid);
        boolean hasCompany = userService.hasCompany(userid);
        if (!hasCompany) {
            return "redirect:szcgc/common/noData?emptyMsg=zwxm";
        }
        User user = userService.find(userid);

        //切分金额
        double amount_min = 0f;
        double amount_max = Double.MAX_VALUE;
        if (StringUtils.isNotBlank(amountRange)) {// 放款金额
            String[] priceArr = amountRange.split("-");// 格式0-5，5-10，10
            if (priceArr != null) {
                if (priceArr.length == 1) {// 只有一个表示大于
                    amount_min = Double.parseDouble(priceArr[0]);
                    amount_max = Double.MAX_VALUE;
                } else if (priceArr.length == 2) {// 两个表示区间，0-5表示5以下
                    amount_min = Double.parseDouble(priceArr[0]);
                    amount_max = Double.parseDouble(priceArr[1]);
                }
            }
        }
        Map<String, Object> map = new HashMap<>();
        if (status == 0) {
            Page<Projectdetail> unloanList = projectService.getUnLoan(user.getCellphone(), businessclass, amount_min, amount_max, curPage);
            int unloanCount = (int)unloanList.getSize();
            map.put("count", unloanCount);
            map.put("data", unloanList.getContent());
        } else if (status == 1) {
            Page<Projectdetail> loaningList = projectService.getLoaning(user.getCellphone(), businessclass, amount_min, amount_max, curPage);
            int loaningcount = (int)loaningList.getSize();
            map.put("count", loaningcount);
            map.put("data", loaningList.getContent());
        } else if (status == 2) {
            Page<Projectdetail> loanedList = projectService.getLoaned(user.getCellphone(), businessclass, amount_min, amount_max, curPage);
            int loanedcount = (int)loanedList.getSize();
            map.put("count", loanedcount);
            map.put("data", loanedList.getContent());
        }

        return JsonUtil.objToJsonMap(map);
    }

    @RequestMapping(value = "/viewproject")
    public String viewProject(int mainid, int userid, Model model) {
        if (mainid <= 0) {
            return "szcgc/center/project/viewProject";
        }
        String statusImg = "ico_label01.png";// 状态默认已放款
        Projectdetail projectDetailByMainid = projectService.getProjectDetailByMainid(mainid);
        if (projectDetailByMainid != null) {
            // 未放款:放款日期为空或者放款金额小于等于0
            if (projectDetailByMainid.getStartdate() == null || projectDetailByMainid.getLoanamount().doubleValue() <= 0) {
                statusImg = "ico_label02.png";
            } else if (projectDetailByMainid.getReleasedate() != null) {// 已解保:解保日期不为空且小于等于当前日期
                LocalDate today = LocalDate.now();
                LocalDate compareDate = projectDetailByMainid.getReleasedate();
                if (today.isAfter(compareDate)) {
                    statusImg = "ico_label03.png";
                }
            }
        }
        model.addAttribute("projectname", projectDetailByMainid.getProjectname());
        model.addAttribute("loanamount", projectDetailByMainid.getLoanamount());
        model.addAttribute("businessclass", projectDetailByMainid.getBusinessclass());
        model.addAttribute("startdate", projectDetailByMainid.getStartdate());
        model.addAttribute("enddate", projectDetailByMainid.getEnddate());
        model.addAttribute("manager", projectDetailByMainid.getManager());
        model.addAttribute("statusImg", statusImg);


        return "szcgc/center/project/viewProject";
    }
}
