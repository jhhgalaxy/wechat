package com.szcgc.wechat.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @Title: SundryUtil.java
 * @Description: TODO
 * @author liaohong
 * @date May 6, 2019 10:49:04 AM
 * @version V1.0
 */
public class SundryUtil {

	/**
	 * 尝试转换成int
	 * 
	 * //代码return (int) o; 如果o是string，则会报异常
	 * 
	 * @param o
	 * @param value
	 * @return
	 */
	public static int tryGetInt(Object o, int value) {
		if (o == null)
			return value;
		if (o instanceof String)
			return tryGetInt(o.toString(), value);
		if (o instanceof Integer)
			return (int) o;
		return value;
	}

	/**
	 * 尝试转换成int
	 * 
	 * @param s
	 * @param value
	 * @return
	 */
	public static int tryGetInt(String s, int value) {
		if (s == null || s.length() == 0)
			return value;
		try {
			return Integer.parseInt(s);
		} catch (Exception e) {
			return value;
		}
	}

	public static String convert(Throwable t) {
		if (t == null)
			return null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			t.printStackTrace(new PrintStream(baos));
		} finally {
			try {
				baos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return baos.toString();
	}

	private static final boolean DEBUG_MODE = false;

	public static void log(String msg) {
		if (DEBUG_MODE) {
			System.out.println(msg);
		} else {
			Log log = LogFactory.getLog("oa-capital");
			log.info(msg);
		}
	}

	public static void log(String msg, Exception e) {
		if (DEBUG_MODE) {
			System.out.println(msg);
			e.printStackTrace();
		} else {
			Log log = LogFactory.getLog("oa-capital");
			log.error(msg, e);
		}
	}

	public static void log(Object... arguments) {
		StringBuilder sb = new StringBuilder();
		for (Object arg : arguments) {
			sb.append(arg);
			sb.append(",");
		}
		log(sb.toString());
	}

	public static void log(Exception e, Object... arguments) {
		StringBuilder sb = new StringBuilder();
		for (Object arg : arguments) {
			sb.append(arg);
			sb.append(",");
		}
		log(sb.toString(), e);
	}
}
