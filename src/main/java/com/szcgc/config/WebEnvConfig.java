package com.szcgc.config;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.servlet.ServletContext;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * @Title: WebEnvConfig.java
 * @Description: TODO
 * @author liaohong
 * @date May 16, 2019 11:59:40 AM
 * @version V1.0
 */


public class WebEnvConfig {

	public static ServletContext CONTEXT;


	/**
	 * APPATH:contextpath
	 */
	public static String CONTEXTPATH = "";

	/**
	 * REALPATH:realpath
	 */
	public static String REALPATH = "";

	public static final String MVC_PRODUCES_JSON = "application/json;charset=UTF-8;";

	public static final String VIEW_ERROR_406 = "error/e406";

	public static final String UTF8 = "UTF-8";

	public static final Properties APPPROPERTIES = new Properties();



	public static void initApp() {
		try {
			Resource res = new ClassPathResource("application.properties");
			APPPROPERTIES.load(new InputStreamReader(res.getInputStream(),"UTF-8"));
			// for (Map.Entry<Object,Object> entry : APPPROPERTIES.entrySet()) {
			// System.out.println(entry.getKey()+","+entry.getValue());
			// }
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
