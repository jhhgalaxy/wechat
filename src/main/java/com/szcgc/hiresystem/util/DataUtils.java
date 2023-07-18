package com.szcgc.hiresystem.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author JINLINGXUAN
 * @create 2020-10-27
 */
public class DataUtils {

  /**
   * 获取当前日期：yyyy-MM-dd
   */
  public static String getNowDate() {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    return sdf.format(new Date());
  }

  /**
   * 获取当前时间：HH:mm:ss
   */
  public static String getNowTime() {
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    return sdf.format(new Date());
  }

  /**
   * 判断字符串是否为空
   */
  public static boolean isEmpty(String s) {
    return s == null || s.length() == 0 || s.trim().length() == 0;
  }

  /**
   * 返回数组对应index
   */
  public static String getArrayIndex(String[] array, String key) {
    for (int i = 0; i < array.length; i++) {
      if (array[i].equals(key)) {
        return i+"";
      }
    }
    return "-1";
  }

}
