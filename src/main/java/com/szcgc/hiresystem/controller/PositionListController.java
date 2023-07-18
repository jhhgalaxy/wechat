package com.szcgc.hiresystem.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.szcgc.hiresystem.entity.PositionList;
import com.szcgc.hiresystem.service.PositionListService;
import com.szcgc.wechat.controller.ProjectController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author JINLINGXUAN
 * @create 2020-09-27
 * 招聘系统-操作招聘岗位信息
 */

@Controller
@RequestMapping("/positionList")
public class PositionListController {

  private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);
  @Resource
  private PositionListService positionListService;

  /**
   * 查询某一具体招聘岗位
   */
  @RequestMapping(value = "/position/{var}", produces = "application/json;charset=utf-8")
  @ResponseBody
  public String getPosition(@PathVariable("var") int id) {
    return JSON.toJSONString(positionListService.findPositionById(id));
  }

  /**
   * 查询招聘岗位列表
   */
  @RequestMapping(value = "/simple/{var}", produces = "application/json;charset=utf-8")
  @ResponseBody
  public String getSimplePositionList(@PathVariable("var") String type) {
    return JSON.toJSONString(positionListService.getSimplePositionList(type));
  }

  /**
   * 更新招聘岗位信息
   */
  @RequestMapping(value = "/update", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
  @ResponseBody
  public void updatePosition(@RequestBody String json) {
    //删除岗位信息表中的所有数据
    positionListService.deleteAll();
    //处理json数据
    JSONArray jsonArray = JSONArray.parseArray(json);
    for (int i = 0; i < jsonArray.size(); i++) {
      JSONObject jsonObject = jsonArray.getJSONObject(i);
      PositionList positionList = new PositionList();
      positionList.setPositionid(Integer.parseInt(jsonObject.getString("positionid")));//OA中的岗位ID
      positionList.setName(jsonObject.getString("name"));
      positionList.setDuty(jsonObject.getString("duty"));
      positionList.setRequest(jsonObject.getString("request"));
      positionList.setNum(jsonObject.getString("num"));
      positionList.setType(jsonObject.getString("type"));
      positionList.setWorkplace(jsonObject.getString("workplace"));
      positionList.setEducation(jsonObject.getString("education"));
      positionList.setValid(Integer.parseInt(jsonObject.getString("valid")));
      PositionList result=positionListService.update(positionList);
      if(result!=null && result.getId()>0){
        logger.info("成功更新【"+result.getName()+"】岗位信息");
      }
    }
  }

}
