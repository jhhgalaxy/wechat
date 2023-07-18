package com.szcgc.wechat.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.szcgc.wechat.smart.entity.Projectdetail;
import com.szcgc.wechat.smart.service.ProjectService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author JINLINGXUAN
 * @create 2020-07-14
 */
@Controller
public class EsignProjectController {

  private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);
  @Autowired
  private ProjectService projectService;

  @RequestMapping(value = "/esign/{var}")
  public String viewProject(@PathVariable("var") String encryptData, Model model) {
    try {
      String key = "5c0af8e827c3e2e39df0b76cbddd23cdaec7175cf8b7f0ce";//AES密钥
      byte[] keyBytes = new byte[key.length() / 2];
      for (int i = 0; i < keyBytes.length; i++) {
        keyBytes[i] = (byte) Integer.parseInt(key.substring(i * 2, i * 2 + 2), 16);
      }

      byte[] data = new byte[encryptData.length() / 2];
      for (int i = 0; i < data.length; i++) {
        data[i] = (byte) Integer.parseInt(encryptData.substring(i * 2, i * 2 + 2), 16);
      }

      //AES解密
      SecretKey secretKey = new SecretKeySpec(keyBytes, "AES");
      Cipher cipher = Cipher.getInstance("AES");
      cipher.init(Cipher.DECRYPT_MODE, secretKey);
      byte[] plainBytes = cipher.doFinal(data);

      JSONObject json = JSONObject.parseObject(new String(plainBytes, "utf-8"));
      logger.info("解密后的信息为>>>" + new String(plainBytes));

      int mainid = 0;
      if (json.getString("id").length() > 0) {
        mainid = Integer.parseInt(json.getString("id"));
      }

      String type = json.getString("type");
      String endDate = json.getString("youxrq");
      String bank = json.getString("danbyxyh");
      String guaDate = json.getString("danbqx");
      String projectCode = json.getString("xiangmbm");

      logger.info("===电子签章二维码查询ID为【" + mainid + "】的项目信息===");

      Projectdetail projectDetailByMainid = null;
      if (mainid > 0) {
        projectDetailByMainid = projectService.getProjectDetailByMainid(mainid);
      } else {
        projectDetailByMainid = projectService.getProjectDetailByProjectCode(projectCode);
      }

      model.addAttribute("projectname", projectDetailByMainid.getProjectname());//客户名称
      model.addAttribute("projectamount", projectDetailByMainid.getProjectamount());//项目金额
      model.addAttribute("businessclass", projectDetailByMainid.getBusinessclass());//业务品种
      model.addAttribute("type", type);//电子签章类型（G/P）
      model.addAttribute("endDate", endDate);//有效日期
      model.addAttribute("bank", bank);//担保意向银行
      model.addAttribute("guaDate", guaDate);//担保期限
      if (type.equals("G") && json.getString("guaranteeid") != null && !(json.getString("guaranteeid").equals(""))) {
        int guaranteeid = Integer.parseInt(json.getString("guaranteeid"));
        logger.info("===项目【" + projectCode + "】的最新guaranteeid应为：" + projectDetailByMainid.getGuaranteeId() + "===");
        if (projectDetailByMainid.getGuaranteeId() != 0 && guaranteeid < projectDetailByMainid.getGuaranteeId()) {
          logger.info("===项目【" + projectCode + "】的此二维码信息中的guaranteeid为：" + guaranteeid + "，二维码已失效");
          model.addAttribute("endDate", "已失效");//有效日期
        }
      }

    } catch (Exception e) {
      logger.info("===电子签章二维码查询项目信息报错，接收到的密文为：" + encryptData + "===");
    }

    return "szcgc/center/project/esignProjectInfo";
  }

  @RequestMapping(value = "/esign/new/{var}")
  public String viewProjectData(@PathVariable("var") String encryptData, Model model) {
    try {
      String data= new String(Base64.getUrlDecoder().decode(encryptData), StandardCharsets.UTF_8);

      JSONObject json = JSONObject.parseObject(data);
      logger.info("解密后的信息为>>>" + data);

      int mainid = 0;
      if (json.getString("id").length() > 0) {
        mainid = Integer.parseInt(json.getString("id"));
      }

      String type = json.getString("t");
      String endDate = json.getString("y");
      String bank = json.getString("d");
      String guaDate = json.getString("q");
      String projectCode = json.getString("x");

      logger.info("===电子签章二维码查询ID为【" + mainid + "】的项目信息===");

      Projectdetail projectDetailByMainid = null;
      if (mainid > 0) {
        projectDetailByMainid = projectService.getProjectDetailByMainid(mainid);
      } else {
        projectDetailByMainid = projectService.getProjectDetailByProjectCode(projectCode);
      }

      model.addAttribute("projectname", projectDetailByMainid.getProjectname());//客户名称
      model.addAttribute("projectamount", projectDetailByMainid.getProjectamount());//项目金额
      model.addAttribute("businessclass", projectDetailByMainid.getBusinessclass());//业务品种
      model.addAttribute("type", type);//电子签章类型（G/P）
      model.addAttribute("endDate", endDate);//有效日期
      model.addAttribute("bank", bank);//担保意向银行
      model.addAttribute("guaDate", guaDate);//担保期限
      if (!StringUtils.isEmpty(json.getString("g")) && type.equals("G")) {
        int guaranteeid = Integer.parseInt(json.getString("g"));
        logger.info("===项目【" + projectCode + "】的最新guaranteeid应为：" + projectDetailByMainid.getGuaranteeId() + "===");
        if (projectDetailByMainid.getGuaranteeId() != 0 && guaranteeid < projectDetailByMainid.getGuaranteeId()) {
          logger.info("===项目【" + projectCode + "】的此二维码信息中的guaranteeid为：" + guaranteeid + "，二维码已失效");
          model.addAttribute("endDate", "已失效");//有效日期
        }
      }

    } catch (Exception e) {
      logger.info("===电子签章二维码查询项目信息报错，接收到的密文为：" + encryptData + "===");
    }

    return "szcgc/center/project/esignProjectInfo";
  }

  @RequestMapping(value = "/esign/new1/{var}")
  public String viewProjectData1(@PathVariable("var") String encryptData, Model model) {
    try {
      JSONObject json = JSONObject.parseObject(encryptData);
      logger.info("解密后的信息为>>>" + encryptData);

      int mainid = 0;
      if (json.getString("id").length() > 0) {
        mainid = Integer.parseInt(json.getString("id"));
      }

      String type = json.getString("t");
      String endDate = json.getString("y");
      String bank = json.getString("d");
      String guaDate = json.getString("q");
      String projectCode = json.getString("x");

      logger.info("===电子签章二维码查询ID为【" + mainid + "】的项目信息===");

      Projectdetail projectDetailByMainid = null;
      if (mainid > 0) {
        projectDetailByMainid = projectService.getProjectDetailByMainid(mainid);
      } else {
        projectDetailByMainid = projectService.getProjectDetailByProjectCode(projectCode);
      }

      model.addAttribute("projectname", projectDetailByMainid.getProjectname());//客户名称
      model.addAttribute("projectamount", projectDetailByMainid.getProjectamount());//项目金额
      model.addAttribute("businessclass", projectDetailByMainid.getBusinessclass());//业务品种
      model.addAttribute("type", type);//电子签章类型（G/P）
      model.addAttribute("endDate", endDate);//有效日期
      model.addAttribute("bank", bank);//担保意向银行
      model.addAttribute("guaDate", guaDate);//担保期限
      if (!StringUtils.isEmpty(json.getString("g")) && type.equals("G")) {
        int guaranteeid = Integer.parseInt(json.getString("g"));
        logger.info("===项目【" + projectCode + "】的最新guaranteeid应为：" + projectDetailByMainid.getGuaranteeId() + "===");
        if (projectDetailByMainid.getGuaranteeId() != 0 && guaranteeid < projectDetailByMainid.getGuaranteeId()) {
          logger.info("===项目【" + projectCode + "】的此二维码信息中的guaranteeid为：" + guaranteeid + "，二维码已失效");
          model.addAttribute("endDate", "已失效");//有效日期
        }
      }

    } catch (Exception e) {
      logger.info("===电子签章二维码查询项目信息报错，接收到的密文为：" + encryptData + "===");
    }

    return "szcgc/center/project/esignProjectInfo";
  }

}
