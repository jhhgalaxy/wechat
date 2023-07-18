package com.szcgc.config;

import com.szcgc.wechat.Interceptor.AuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @Title: WebMvcConfig.java
 * @Description: TODO
 * @author liaohong
 * @date May 14, 2019 11:23:40 AM
 * @version V1.0
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.szcgc.wechat.*","com.szcgc.hiresystem.*","com.szcgc.trip.*","com.szcgc.assets.*","com.szcgc.portal.*" }, useDefaultFilters = false, includeFilters = @Filter({
		Controller.class, ControllerAdvice.class }))
public class WebMvcConfig implements WebMvcConfigurer {
	// class WebMvcConfig extends WebMvcConfigurationSupport
	// class WebMvcConfig extends WebMvcConfigurerAdapter

	@Autowired
	AuthInterceptor authInterceptor;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authInterceptor).addPathPatterns("/**").excludePathPatterns("/resources/**");
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// 访问根目录，对应的返回页面
		registry.addViewController("/").setViewName("home");
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Bean
	public ViewResolver viewResolver() {
		// public InternalResourceViewResolver jspViewResolver() {
		InternalResourceViewResolver bean = new InternalResourceViewResolver();
		bean.setPrefix("/WEB-INF/view/");
		bean.setSuffix(".jsp");
		bean.setRedirectHttp10Compatible(false);// https的redirect不会到http
		return bean;
	}

//	@Bean(name = "messageSource")
//	public ReloadableResourceBundleMessageSource getMessageSource() {
//		ReloadableResourceBundleMessageSource resource = new ReloadableResourceBundleMessageSource();
//		resource.setBasename("classpath:messages");
//		resource.setDefaultEncoding("UTF-8");
//		return resource;
//	}

//	@Override
//	public void addFormatters(FormatterRegistry registry) {
//		registry.addConverter(new StringToLocalDateConverter());
//		registry.addConverter(new StringToLocalDateTimeConverter());
//		registry.addConverterFactory(new StringToEnumConverterFactory());
//	}

//	@Override
//	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
//		argumentResolvers.add(new CgcIdArgumentResolver());
//	}
//	
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(new CgcAuthorizationInterceptor());
//	}

//	@Bean(name = "multipartResolver")
//	public CommonsMultipartResolver getMultipartResolver() {
//		return new CommonsMultipartResolver();
//	}

}
