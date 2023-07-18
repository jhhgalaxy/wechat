package com.szcgc.hiresystem.controller;

import com.alibaba.fastjson.JSON;
import com.szcgc.hiresystem.entity.DeliveryRecords;
import com.szcgc.hiresystem.entity.PositionList;
import com.szcgc.hiresystem.entity.ResumeInfo;
import com.szcgc.hiresystem.service.DeliveryRecordsService;
import com.szcgc.hiresystem.service.PositionListService;
import com.szcgc.hiresystem.service.ResumeService;
import com.szcgc.hiresystem.util.DataUtils;
import com.szcgc.wechat.controller.ProjectController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author JINLINGXUAN
 * @create 2020-11-06
 * 招聘系统-投递岗位
 */
@Controller
@RequestMapping("/delivery")
public class DeliveryRecordsController {

  private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);
  @Resource
  DeliveryRecordsService deliveryRecordsService;
  @Resource
  PositionListService positionListService;
  @Resource
  ResumeService resumeService;

  /**
   * 查询用户是否投递简历，并判断简历信息是否完整
   */
  @RequestMapping(value = "/update", produces = "application/json;charset=utf-8")
  @ResponseBody
  public String deliveryResume(HttpServletRequest request) {
    String openid = request.getParameter("openid");
    int positionid = Integer.parseInt(request.getParameter("positionid"));
    logger.info("开始查询openid【" + openid + "】是否投递了岗位ID【" + positionid + "】");

    Map<String, String> returnMap = new HashMap<>();
    returnMap.put("id", "");
    returnMap.put("deliveryDate", "");
    returnMap.put("deliveryTime", "");
    returnMap.put("deliveryState", "");
    returnMap.put("full", "true");

    String nowDate = DataUtils.getNowDate();
    String nowTime = DataUtils.getNowTime();

    //若用户暂无简历
    int resumeid = resumeService.findResumeIdByOpenid(openid);
    if (resumeid <= 0) {
      return JSON.toJSONString(returnMap);
    }else{
      returnMap.put("id", resumeid+"");
    }

    String positionType = "";//投递岗位的类型
    String positionName = "";//投递岗位的名称
    String positions = "";//简历中已投递的岗位信息
    ResumeInfo resumeInfo = resumeService.findResumeInfoByOpenid(openid);
    DeliveryRecords deliveryRecords = deliveryRecordsService.getDeliveryInfo(openid, positionid);
    if (deliveryRecords != null) {
      //若有岗位投递信息
      returnMap.put("deliveryDate", deliveryRecords.getDeliveryDate());
      returnMap.put("deliveryTime", deliveryRecords.getDeliveryTime());
      return JSON.toJSONString(returnMap);
    } else {
      //若无简历投递信息，则先判断投递的岗位是校招还是社招
      PositionList positionList = positionListService.findPositionById(positionid);
      if (positionList != null) {
        positionType = positionList.getType();
        positionName = positionList.getName();
        if (positionType.equals("社招")) {
          //若为社招，则要判断简历信息是否完整
          if (DataUtils.isEmpty(resumeInfo.getNation())) {
            returnMap.put("full", "false");
            return JSON.toJSONString(returnMap);
          }
        }
      }

      //以上校验都通过后，插入一条投递信息并返回投递成功
      DeliveryRecords deliveryRecords1 = new DeliveryRecords();
      deliveryRecords1.setOpenid(openid);
      deliveryRecords1.setPositionId(positionid);
      deliveryRecords1.setDeliveryDate(nowDate);
      deliveryRecords1.setDeliveryTime(nowTime);
      deliveryRecordsService.saveDeliveryInfo(deliveryRecords1);
      returnMap.put("deliveryState", "success");

      //并更新简历中的岗位投递信息
      positions = resumeInfo.getPositions();
      if (!DataUtils.isEmpty(positions)) {
        positions += "，" + positionName + "(" + positionType + ")";
      } else {
        positions = positionName + "(" + positionType + ")";
      }
      resumeInfo.setPositions(positions);
      resumeService.saveResumeInfo(resumeInfo);

      return JSON.toJSONString(returnMap);
    }
  }

}
