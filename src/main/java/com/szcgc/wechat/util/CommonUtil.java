package com.szcgc.wechat.util;

import org.springframework.cglib.core.Local;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * @author chenxinli
 * @since 2020.4.15
 * 常用工具类
 */
public class CommonUtil {

    private static final String REGEX_MOBILE = "((\\+86|0086)?\\s*)((134[0-8]\\d{7})|(((13([0-3]|[5-9]))|(14[5-9])|15([0-3]|[5-9])|(16(2|[5-7]))|17([0-3]|[5-8])|18[0-9]|19(1|[8-9]))\\d{8})|(14(0|1|4)0\\d{7})|(1740([0-5]|[6-9]|[10-12])\\d{7}))";

    /**
     * 生成范围内的随机数
     * @param startNum 随机数开始范围
     * @param endNum 随机数结束范围
     * @return
     */
    public static int randToInt(int startNum, int endNum){
        Random rand = new Random();
        return rand.nextInt(endNum - startNum + 1) + startNum;
    }

    /**
     * 字符串预处理
     * <p>
     * 1、空对象转成空字符串
     *
     * @param str 要转换的字符串
     * @return
     */
    public static String trim(String str) {
        if (str == null) {
            return "";
        } else {
            return str.trim();
        }
    }

    /**
     * 判断是否是手机号
     *
     * @param tel 手机号
     * @return boolean true:是 false:否
     */
    public static boolean isMobile(String tel) {
        if (StringUtils.isEmpty(tel)) {
            return false;
        }
        return Pattern.matches(REGEX_MOBILE, tel);
    }

    /**
     * 格式化日期（默认按yyyy-MM-dd HH:mm:ss格式解析）
     * @param curDate 要格式化的日期对象
     * @return
     */
    public static String format(LocalDateTime curDate){
        return format(curDate, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 格式化日期
     * @param curDate 要格式化的日期对象
     * @param fmt 要格式化的日期格式
     * @re  */
    public static String format(LocalDateTime curDate, String fmt){
        if(curDate == null) return "";
        try{
            return curDate.format(DateTimeFormatter.ofPattern(fmt));
        } catch(Exception e){
            return "";
        }
    }

    /**
     * 根据String输出LocalDateTime
     * @param date 日期String
     * @param fmt 日期格式
     * @return
     */
    public static LocalDateTime getLocalDateTime(String date,String fmt){
        if(StringUtils.isEmpty(date)){
            return null;
        }
        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern(fmt));
    }

    /**
     * 根据String输出LocalDateTime,默认为yyyy-MM-dd HH:mm:ss格式
     * @param date 日期String
     * @return
     */
    public static LocalDateTime getLocalDateTime(String date){
        if(StringUtils.isEmpty(date)){
            return null;
        }
        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * 格式化数字
     * @param num
     * @param fmt
     * @return
     */
    public static String format(BigDecimal num, String fmt){
        DecimalFormat nf = new DecimalFormat(fmt);
        try{
            return nf.format( num.doubleValue() );
        } catch(Exception e){
            return "0";
        }
    }

    /**
     * 格式化日期
     *
     * @param curDate 要格式化的日期对象
     * @param fmt     要格式化的日期格式
     * @return
     */
    public static String format(Date curDate, String fmt) {
        if (curDate == null)
            return "";
        try {
            SimpleDateFormat sdf1 = new SimpleDateFormat(fmt);
            return sdf1.format(curDate.getTime());
        } catch (Exception e) {
            return "";
        }
    }
}
