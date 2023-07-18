package com.szcgc.hiresystem.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.szcgc.hiresystem.entity.*;
import com.szcgc.hiresystem.service.ResumeDetailService;
import com.szcgc.hiresystem.service.ResumeService;
import com.szcgc.hiresystem.util.DataUtils;
import com.szcgc.wechat.controller.ProjectController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author JINLINGXUAN
 * @create 2020-10-27
 * 招聘系统-操作简历相关信息
 */
@Controller
@RequestMapping("/resumeinfo")
public class ResumeController {

  private static final String[] DRIVER = {"是", "否"};
  private static final String[] FERTILITY = {"未育", "一孩", "二孩", "三孩"};
  private static final String[] HEALTH = {"健康", "一般", "较差"};
  private static final String[] MARRIAGE = {"未婚", "已婚", "离异"};
  private static final String[] POLITICAL = {"中共党员", "预备党员", "共青团员", "群众", "民主党派"};
  private static final String[] SEX = {"男", "女"};
  private static final String[] WORKEXPERIENCE = {"是", "否"};
  private static final String[] WORKPLACEARRAY = {"", "深圳", "杭州", "成都", "武汉", "昆明", "南京", "汕头"};
  private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);

  @Resource
  private ResumeDetailService resumeDetailService;

  @Resource
  private ResumeService resumeService;

  /**
   * 删除指定明细数据
   */
  @ResponseBody
  @RequestMapping(value = "/deleteDetail", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
  public void deleteDetail(HttpServletRequest request) {
    String detaileNo = request.getParameter("detaileNo");
    int id = Integer.parseInt(request.getParameter("id"));

    if (detaileNo.equals("1")) {
      resumeDetailService.deleteEducationInfo(id);
    }
    if (detaileNo.equals("2")) {
      resumeDetailService.deletePracticeInfo(id);
    }
    if (detaileNo.equals("3")) {
      resumeDetailService.deleteWorkInfo(id);
    }
    if (detaileNo.equals("4")) {
      resumeDetailService.deleteFamilyInfo(id);
    }
  }

  /**
   * 获取简历明细信息
   */
  @ResponseBody
  @RequestMapping(value = "/getDetail", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
  public String getDetailInfo(HttpServletRequest request) throws IOException {
    String openid = request.getParameter("openid");
    String detaileNo = request.getParameter("detaileNo");
    String id = request.getParameter("id");
    logger.info("====用户openid为：" + openid + "，需查询的简历明细序号为:" + detaileNo + "获取到的为：" + id + "====");

    if (detaileNo.equals("1")) {
      //获取教育信息
      if (!DataUtils.isEmpty(id)) {
        return JSON.toJSONString(resumeDetailService.findEducationInfoById(Integer.parseInt(id)));
      }
      return JSON.toJSONString(resumeDetailService.findEducationInfoByOpenid(openid));
    }
    if (detaileNo.equals("2")) {
      if (!DataUtils.isEmpty(id)) {
        return JSON.toJSONString(resumeDetailService.findPracticeInfoById(Integer.parseInt(id)));
      }
      return JSON.toJSONString(resumeDetailService.findPracticeInfoByOpenid(openid));
    }
    if (detaileNo.equals("3")) {
      if (!DataUtils.isEmpty(id)) {
        return JSON.toJSONString(resumeDetailService.findWorkInfoInfoById(Integer.parseInt(id)));
      }
      return JSON.toJSONString(resumeDetailService.findWorkInfoByOpenid(openid));
    }
    if (detaileNo.equals("4")) {
      if (!DataUtils.isEmpty(id)) {
        return JSON.toJSONString(resumeDetailService.findFamilyInfoById(Integer.parseInt(id)));
      }
      return JSON.toJSONString(resumeDetailService.findFamilyInfoByOpenid(openid));
    }

    return "";
  }

  /**
   * 查询简历主表信息
   */
  @ResponseBody
  @RequestMapping(value = "/info", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
  public String getResumeInfo(HttpServletRequest request) {
    String openid = request.getParameter("openid");
    String type = request.getParameter("type");
    logger.info("====开始查询openid【" + openid + "】的简历信息====");

    if (DataUtils.isEmpty(openid)) {
      //若openid为空
      return "";
    }

    int resumeId = 0;//简历ID
    Map<String, String> returnMap = new HashMap<>();
    String workplace = "";//工作地点
    ResumeInfo resumeInfo = new ResumeInfo();

    resumeId = resumeService.findResumeIdByOpenid(openid);
    if (resumeId > 0) {
      logger.info("====用户的简历ID为：" + resumeId + "====");
      //根据resumeId查询简历信息
      resumeInfo = resumeService.findResumeInfoById(resumeId);
    } else {
      return "";
    }

    if ("show".equals(type)) {
      //若为展示简历信息，则只需查询部分必要信息
      returnMap.put("id", resumeInfo.getId() + "");
      returnMap.put("name", resumeInfo.getName());
      returnMap.put("sex", DataUtils.getArrayIndex(SEX, resumeInfo.getSex()) + "");
      returnMap.put("birthday", resumeInfo.getBirthday());
      returnMap.put("idCardNo", resumeInfo.getIdCardNo());
      returnMap.put("tel", resumeInfo.getTel());
      returnMap.put("region", resumeInfo.getRegion());
      returnMap.put("updateTime", resumeInfo.getUpdateDate() + " " + resumeInfo.getUpdateTime());
      returnMap.put("photo", resumeInfo.getPhoto());
      String[] index = resumeInfo.getWorkplace().split(",");
      for (int i = 0; i < index.length; i++) {
        workplace += WORKPLACEARRAY[Integer.parseInt(index[i])] + "、";
      }
      if (workplace.length() > 0) {
        returnMap.put("workplace", workplace.substring(0, workplace.length() - 1));
      } else {
        returnMap.put("workplace", "");
      }

      return JSON.toJSONString(returnMap);
    }

    if ("edit".equals(type)) {
      //若为编辑简历信息，则展示所有的简历数据
      String json = JSON.toJSONString(resumeInfo);
      JSONObject jsonObject = JSON.parseObject(json);
      //修改部分json字段的值
      jsonObject.put("id", resumeInfo.getId() + "");
      jsonObject.put("sex", DataUtils.getArrayIndex(SEX, resumeInfo.getSex()) + "");
      if (!DataUtils.isEmpty(resumeInfo.getPolitical())) {
        jsonObject.put("political", DataUtils.getArrayIndex(POLITICAL, resumeInfo.getPolitical()));
      }
      if (!DataUtils.isEmpty(resumeInfo.getMarriage())) {
        jsonObject.put("marriage", DataUtils.getArrayIndex(MARRIAGE, resumeInfo.getMarriage()));
      }
      if (!DataUtils.isEmpty(resumeInfo.getFertility())) {
        jsonObject.put("fertility", DataUtils.getArrayIndex(FERTILITY, resumeInfo.getFertility()));
      }
      if (!DataUtils.isEmpty(resumeInfo.getHealth())) {
        jsonObject.put("health", DataUtils.getArrayIndex(HEALTH, resumeInfo.getHealth()));
      }
      if (!DataUtils.isEmpty(resumeInfo.getWorkExperience())) {
        jsonObject.put("workexperience", DataUtils.getArrayIndex(WORKEXPERIENCE, resumeInfo.getWorkExperience()));
      }
      if (!DataUtils.isEmpty(resumeInfo.getDriver())) {
        jsonObject.put("driver", DataUtils.getArrayIndex(DRIVER, resumeInfo.getDriver()));
      }
      String[] arrayTemp = resumeInfo.getWorkplace().split(",");
      List<String> workplaceList = new ArrayList<>();
      for (String s : arrayTemp) {
        workplaceList.add((Integer.parseInt(s) - 1) + "");
      }
      jsonObject.put("workplace", workplaceList);
      if (DataUtils.isEmpty(resumeInfo.getWorkDate())) {
        jsonObject.put("workdate", "2018-01-01");
      }
      if (DataUtils.isEmpty(resumeInfo.getRegion())) {
        jsonObject.put("region", "-");
      }
      if (DataUtils.isEmpty(resumeInfo.getSource())) {
        jsonObject.put("source", "-");
      }
      if (DataUtils.isEmpty(resumeInfo.getResidence())) {
        jsonObject.put("residence", "-");
      }

      return JSON.toJSONString(jsonObject);
    }

    return "";
  }

  /**
   * 获取所有简历信息
   */
  @ResponseBody
  @RequestMapping(value = "/query", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
  public String getResumeInfo() {
    logger.info("====开始获取所有简历信息====");
    List<ResumeInfo> iterator = resumeService.findAllResumeInfo();

    //将简历信息封装成json字符串
    return JSON.toJSONString(iterator, SerializerFeature.WriteNullStringAsEmpty);
  }

  /**
   * 获取985、211院校信息
   */
  @ResponseBody
  @RequestMapping(value = "/getSchoolInfo", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
  public String getSchoolInfo(HttpServletRequest request) {
    return JSON.toJSONString(resumeDetailService.findSchoolInfos());
  }

  /**
   * 查询是否有简历明细信息
   */
  @ResponseBody
  @RequestMapping(value = "/hasDetail", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
  public String hasResumeDetailInfo(HttpServletRequest request) throws IOException {
    String openid = request.getParameter("openid");
    String full = request.getParameter("full");
    logger.info("====查询用户openid为：" + openid + "是否有明细表信息，且显示全表为：" + full + "====");

    Map<String, String> returnMap = new HashMap<>();
    returnMap.put("status", "success");
    returnMap.put("errormsg", "");


    //教育信息、家庭信息必填
    if (!resumeDetailService.hasEducationInfo(openid)) {
      returnMap.put("status", "fail");
      returnMap.put("errormsg", "请填写教育背景");
      return JSON.toJSONString(returnMap);
    }

    //本科学历信息必填
    if (!resumeDetailService.hasEducationInfoByDegree(openid, "本科")) {
      returnMap.put("status", "fail");
      returnMap.put("errormsg", "请填写本科学历信息");
      return JSON.toJSONString(returnMap);
    }

    //若是完整简历，则需检验所有的明细
    if (full.equals("true")) {
      if (!resumeDetailService.hasFamilyInfo(openid)) {
        returnMap.put("status", "fail");
        returnMap.put("errormsg", "请填写家庭成员信息");
        return JSON.toJSONString(returnMap);
      }

      if (!resumeDetailService.hasEducationInfoByDegree(openid, "高中")) {
        returnMap.put("status", "fail");
        returnMap.put("errormsg", "请填写高中学历信息");
        return JSON.toJSONString(returnMap);
      }

    }

    return JSON.toJSONString(returnMap);
  }

  /**
   * 上传简历明细信息
   */
  @ResponseBody
  @RequestMapping(value = "/postDetail", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
  public void postResumeDetailInfo(HttpServletRequest request) throws IOException {

    String openid = request.getParameter("openid");
    String detaileNo = request.getParameter("detaileNo");
    String id = request.getParameter("id");

    BufferedReader reader = request.getReader();
    char[] buf = new char[512];
    int len = 0;
    StringBuffer contentBuffer = new StringBuffer();
    while ((len = reader.read(buf)) != -1) {
      contentBuffer.append(buf, 0, len);
    }
    String content = contentBuffer.toString();
    if (content == null) {
      content = "";
    }

    logger.info("===接收到的微信端发送数据为：" + content);

    //解析json对象
    JSONObject jsonObject = JSONObject.parseObject(content);
    Map<String, Object> resumeDetailMap = (Map<String, Object>) jsonObject;

    int updateId = 0;//更新简历信息后的数据ID

    if (!DataUtils.isEmpty(openid)) {
      if (detaileNo.equals("1")) {
        //更新教育经历信息
        EducationInfo educationInfo = new EducationInfo();
        if (!DataUtils.isEmpty(id)) {
          educationInfo.setId(Integer.parseInt(id));
        }
        educationInfo.setOpenid(openid);
        educationInfo.setStartDate((String) resumeDetailMap.get("startDate"));
        educationInfo.setEndDate((String) resumeDetailMap.get("endDate"));
        educationInfo.setSchool((String) resumeDetailMap.get("school"));
        educationInfo.setDegree((String) resumeDetailMap.get("degree"));
        educationInfo.setProfessional((String) resumeDetailMap.get("professional"));
        updateId = resumeDetailService.updateEducationInfo(educationInfo).getId();
      }
      if (detaileNo.equals("2")) {
        //更新实习经历信息
        PracticeInfo practiceInfo = new PracticeInfo();
        if (!DataUtils.isEmpty(id)) {
          practiceInfo.setId(Integer.parseInt(id));
        }
        practiceInfo.setOpenid(openid);
        practiceInfo.setStartDate((String) resumeDetailMap.get("startDate"));
        practiceInfo.setEndDate((String) resumeDetailMap.get("endDate"));
        practiceInfo.setCompany((String) resumeDetailMap.get("company"));
        practiceInfo.setJob((String) resumeDetailMap.get("job"));
        practiceInfo.setContact((String) resumeDetailMap.get("contact"));
        practiceInfo.setTel((String) resumeDetailMap.get("tel"));
        updateId = resumeDetailService.updatePracticeInfo(practiceInfo).getId();
      }
      if (detaileNo.equals("3")) {
        //更新工作经历信息
        WorkInfo workInfo = new WorkInfo();
        if (!DataUtils.isEmpty(id)) {
          workInfo.setId(Integer.parseInt(id));
        }
        workInfo.setOpenid(openid);
        workInfo.setStartDate((String) resumeDetailMap.get("startDate"));
        workInfo.setEndDate((String) resumeDetailMap.get("endDate"));
        workInfo.setCompany((String) resumeDetailMap.get("company"));
        workInfo.setJob((String) resumeDetailMap.get("job"));
        workInfo.setContact((String) resumeDetailMap.get("contact"));
        workInfo.setTel((String) resumeDetailMap.get("tel"));
        updateId = resumeDetailService.updateWorkInfo(workInfo).getId();
      }
      if (detaileNo.equals("4")) {
        //更新家庭成员信息
        FamilyInfo familyInfo = new FamilyInfo();
        if (!DataUtils.isEmpty(id)) {
          familyInfo.setId(Integer.parseInt(id));
        }
        familyInfo.setOpenid(openid);
        familyInfo.setName((String) resumeDetailMap.get("name"));
        familyInfo.setBirth((String) resumeDetailMap.get("birth"));
        familyInfo.setRelationship((String) resumeDetailMap.get("relationship"));
        familyInfo.setWork((String) resumeDetailMap.get("work"));
        updateId = resumeDetailService.updateFamilyInfo(familyInfo).getId();
      }

      if (updateId > 0) {
        logger.info("====更新openid【" + openid + "】，明细" + detaileNo + "，ID为:" + id + "的简历信息成功====");
      }
    }
  }

  /**
   * 上传简历主表信息
   */
  @ResponseBody
  @RequestMapping(value = "/post", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
  public String postResumeInfo(HttpServletRequest request) throws IOException {
    logger.info("====开始接收简历信息====");

    BufferedReader reader = request.getReader();
    char[] buf = new char[512];
    int len = 0;
    StringBuffer contentBuffer = new StringBuffer();
    while ((len = reader.read(buf)) != -1) {
      contentBuffer.append(buf, 0, len);
    }
    String content = contentBuffer.toString();
    if (content == null) {
      content = "";
    }
    logger.info("====接收到的微信端发送数据为：" + content);

    //解析json对象
    JSONObject jsonObject = JSONObject.parseObject(content);
    Map<String, Object> map = (Map<String, Object>) jsonObject;
    String openid = (String) map.get("openid");
    logger.info("====该用户的openid为：" + openid);
    JSONArray workplace = new JSONArray();//工作地点
    String workplaceStr = "";

    //存储简历信息
    if (openid != null || !openid.equals("")) {
      //封装简历对象
      ResumeInfo resumeInfo = new ResumeInfo();
      int resumeId = resumeService.findResumeIdByOpenid(openid);
      if (resumeId > 0) {
        //若已有该简历，则更新
        resumeInfo.setId(resumeId);
        logger.info("====【" + openid + "】已有简历，更新此简历信息====");
      }
      resumeInfo.setAddress((String) map.get("address"));
      resumeInfo.setEmail((String) map.get("email"));
      resumeInfo.setName((String) map.get("name"));
      resumeInfo.setOpenid((String) map.get("openid"));
      if (!DataUtils.isEmpty((String) map.get("sex"))) {
        resumeInfo.setSex(SEX[Integer.parseInt((String) map.get("sex"))]);
      }
      resumeInfo.setBirthday((String) map.get("birthday"));
      resumeInfo.setComputer((String) map.get("computer"));
      if (!DataUtils.isEmpty((String) map.get("driver"))) {
        resumeInfo.setDriver(DRIVER[Integer.parseInt((String) map.get("driver"))]);
      }
      resumeInfo.setDriverAge((String) map.get("driverAge"));
      resumeInfo.setEmergency((String) map.get("emergency"));
      resumeInfo.setEmergencyTel((String) map.get("emergencyTel"));
      resumeInfo.setEnglish((String) map.get("english"));
      if (!DataUtils.isEmpty((String) map.get("fertility"))) {
        resumeInfo.setFertility(FERTILITY[Integer.parseInt((String) map.get("fertility"))]);
      }
      if (!DataUtils.isEmpty((String) map.get("health"))) {
        resumeInfo.setHealth(HEALTH[Integer.parseInt((String) map.get("health"))]);
      }
      resumeInfo.setHeight((String) map.get("height"));
      resumeInfo.setHobbies((String) map.get("hobbies"));
      resumeInfo.setIdCardNo((String) map.get("idCardNo"));
      if (!DataUtils.isEmpty((String) map.get("health"))) {
        resumeInfo.setMarriage(MARRIAGE[Integer.parseInt((String) map.get("marriage"))]);
      }
      if (!DataUtils.isEmpty((String) map.get("political"))) {
        resumeInfo.setPolitical(POLITICAL[Integer.parseInt((String) map.get("political"))]);
      }
      resumeInfo.setNation((String) map.get("nation"));
      resumeInfo.setNotes((String) map.get("notes"));
      resumeInfo.setOthers((String) map.get("others"));
      resumeInfo.setPay((String) map.get("pay"));
      resumeInfo.setQq((String) map.get("qq"));
      resumeInfo.setPhoto((String) map.get("photo"));
      resumeInfo.setQualifications((String) map.get("qualifications"));
      resumeInfo.setRegion((String) map.get("region"));
      resumeInfo.setRelationship((String) map.get("relationship"));
      resumeInfo.setResidence((String) map.get("residence"));
      resumeInfo.setSource((String) map.get("source"));
      resumeInfo.setTel((String) map.get("tel"));
      resumeInfo.setUpdateDate(DataUtils.getNowDate());
      resumeInfo.setUpdateTime(DataUtils.getNowTime());
      resumeInfo.setWorkDate((String) map.get("workDate"));

      if (!DataUtils.isEmpty((String) map.get("workExperience"))) {
        if (map.get("workExperience").equals("0")) {
          resumeInfo.setWorkExperience("是");
        } else {
          resumeInfo.setWorkExperience("否");
        }
      }

      //处理工作地点字段
      workplace = (JSONArray) map.get("workplace");
      workplaceStr = "";
      for (int i = 0; i < workplace.size(); i++) {
        workplaceStr += Integer.parseInt(workplace.getString(i)) + 1 + ",";
      }
      resumeInfo.setWorkplace(workplaceStr.substring(0, workplaceStr.length() - 1));

      //保存简历
      ResumeInfo result = resumeService.saveResumeInfo(resumeInfo);
      if (result.getId() > 0) {
        logger.info("====保存/更新【" + openid + "】的简历成功====");
      }
    }

    JSONObject returnJson = new JSONObject();
    returnJson.put("openid", openid);
    return returnJson.toJSONString();
  }

}
