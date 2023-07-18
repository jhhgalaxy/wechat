package com.szcgc.config;

import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @Title: WebAppInitializer.java
 * @Description: TODO
 * @author liaohong
 * @date May 14, 2019 11:18:14 AM
 * @version V1.0
 */
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// spring 注入bean;
		return new Class<?>[] { WebRootConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// spring mvc 配置
		return new Class<?>[] { WebMvcConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		// 配置DispatcherServlet映射到 '/' 什么情况进入DispatherServlet
		return new String[] { "/" };
	}

	// Instead we used SecurityWebApplicationInitializer
	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		return new Filter[] { characterEncodingFilter };
	}
}
