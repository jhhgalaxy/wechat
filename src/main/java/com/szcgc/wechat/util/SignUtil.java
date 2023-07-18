package com.szcgc.wechat.util;

import com.szcgc.config.WebEnvConfig;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * 请求校验工具类
 * 
 * @author yangyc
 * @date 2020-3-19
 */
public class SignUtil {
    // 接口配置信息中的Token
    private static String token = WebEnvConfig.APPPROPERTIES.getProperty("CONFIGTOKEN");

    /**
     * 验证签名
     * 
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     */
    public static boolean checkSignature(String signature, String timestamp, String nonce) {
        String[] arr = new String[] { token, timestamp, nonce };
        // 排序
        Arrays.sort(arr);
        StringBuilder contentSB = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            contentSB.append(arr[i]);
        }

        MessageDigest md = null;
        String tmpStr = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
            // 将contenSBt中的字符串加密
            byte[] digest = md.digest(contentSB.toString().getBytes());
            tmpStr = byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        contentSB = null;
        // 将sha1加密后的字符串与signature进行对比
        return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;
    }

    /**
     * 将字节组转换为十六进制字符串
     * 
     * @param byteArray
     * @return
     */
    private static String byteToStr(byte[] byteArray) {
        String strdigest = "";
        for (int i = 0; i < byteArray.length; i++) {
            strdigest += byteToHexStr(byteArray[i]);
        }
        return strdigest;
    }

    /**
     * 将字节转换为十六进制字符串
     * 
     * @param mbyte
     * @return
     */
    private static String byteToHexStr(byte mbyte) {
        char[] digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        char[] charArr = new char[2];
        charArr[0] = digit[(mbyte >>> 4) & 0X0F];
        charArr[1] = digit[mbyte & 0X0F];
        String str = new String(charArr);
        return str;
    }
}
