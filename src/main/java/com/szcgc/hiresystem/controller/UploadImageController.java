package com.szcgc.hiresystem.controller;

import com.alibaba.fastjson.JSON;
import com.szcgc.wechat.controller.ProjectController;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author JINLINGXUAN
 * @create 2020-10-06
 * 招聘系统-上传简历信息中的图片
 */
@Controller
public class UploadImageController {

  private static final String FILEPATH = "D:/szcgcWechatServer/szcgcWechatServer_war/";
  private static final String IMAGEURL = "https://es.szcgc.com/";
  private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);

  @ResponseBody
  @RequestMapping(value = "/uploadimage", method = RequestMethod.POST)
  public String upload(HttpServletRequest request) {

    logger.info("====接收简历中上传的图片文件=====");

    Map<String, String> map = new HashMap<>();
    map.put("url", "");

    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    String nowDate = sdf.format(new Date());

    String path = FILEPATH + "resources/images/hiresystem/" + nowDate + "/";
    File dir = new File(path);
    if (!dir.exists()) {
      logger.info("文件存储路径不存在，创建此路径：" + path);
      boolean isCreated = dir.mkdirs();
      if (!isCreated) {
        logger.info("创建文件路径失败：" + path);
      }
    }
    logger.info("存储图片的路径为：" + path);

    //获得磁盘文件条目工厂
    DiskFileItemFactory factory = new DiskFileItemFactory();

    //如果没以下两行设置的话,上传大的文件会占用很多内存，
    //设置暂时存放的存储室,这个存储室可以和最终存储文件的目录不同
    /*
     *原理: 它是先存到暂时存储室，然后再真正写到对应目录的硬盘上，
     * 按理来说当上传一个文件时，其实是上传了两份，第一个是以 .tem 格式的
     * 然后再将其真正写到对应目录的硬盘上
     */
    factory.setRepository(dir);
    //设置缓存的大小，当上传文件的容量超过该缓存时，直接放到暂时存储室
    factory.setSizeThreshold(1024 * 1024);
    //高水平的API文件上传处理
    ServletFileUpload upload = new ServletFileUpload(factory);
    try {
      List<FileItem> list = upload.parseRequest(request);
      FileItem picture = null;
      for (FileItem item : list) {
        //获取表单的属性名字
        String name = item.getFieldName();
        //如果获取的表单信息是普通的 文本 信息
        if (item.isFormField()) {
          //获取用户具体输入的字符串
          String value = item.getString();
          request.setAttribute(name, value);
          logger.info("name=" + name + ",value=" + value);
        } else {
          picture = item;
        }
      }

      //自定义上传图片的名字为userId.jpg
      int rs = (int) ((Math.random() * 9 + 1) * Math.pow(10, 5 - 1));//5位随机数
      String fileName = request.getAttribute("openid") + String.valueOf(rs) + ".jpg";
      String destPath = path + fileName;
      logger.info("最终图片存储的路径为：" + destPath);

      //真正写到磁盘上
      File file = new File(destPath);
      OutputStream outFile = new FileOutputStream(file);
      InputStream in = picture.getInputStream();
      int length = 0;
      byte[] buf = new byte[1024];
      // in.read(buf) 每次读到的数据存放在buf 数组中
      while ((length = in.read(buf)) != -1) {
        //在buf数组中取出数据写到（输出流）磁盘上
        outFile.write(buf, 0, length);
      }
      in.close();
      outFile.close();

      map.put("url", destPath.replace(FILEPATH, IMAGEURL));

    } catch (Exception e) {
      e.printStackTrace();
    }

    logger.info("招聘系统上传图片返回的结果为：" + JSON.toJSONString(map));
    return JSON.toJSONString(map);
  }

}
