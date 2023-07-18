package com.szcgc.wechat.util;

import java.io.IOException;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 把对象转换为json字符串的模式，以提供给微信公众号
 * 
 * @author yangyc
 *
 */
public class JsonUtil {

	/**
	 * @description:对象转换成为的json字符串仅包含子类的变量，不追溯父类
	 * @param obj
	 * @return
	 * @throws IOException
	 * @author yangyc
	 * @date Mar 26, 2020
	 * @version V1.0
	 */
	public static String objToJson(Object obj) throws IOException {
		JSONObject jsonObj = new JSONObject(obj);
		String str = jsonObj.toString();
		return str;
	}

	/**
	 * @description:对象转换成为的json字符串可以追溯到父类的变量
	 * @param obj
	 * @return
	 * @throws IOException
	 * @author yangyc
	 * @date Mar 26, 2020
	 * @version V1.0
	 */
	public static String objToJsonMap(Object obj) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		mapper.registerModule(new JavaTimeModule());
		String str = mapper.writeValueAsString(obj);
		return str;
	}

	/**
	 * @description:json转对象
	 * @param json
	 * @param clazz
	 * @return
	 * @throws IOException
	 */
	public static Object jsonToObj(String json,Class<?> clazz) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json,clazz);
	}

}
