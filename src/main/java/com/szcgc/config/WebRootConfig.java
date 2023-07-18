package com.szcgc.config;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import com.szcgc.wechat.MsgScheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;

/**
 * @Title: WebRootConfig.java
 * @Description: TODO
 * @author liaohong
 * @date May 14, 2019 11:23:33 AM
 * @version V1.0
 */
@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = { "com.szcgc.*" }, excludeFilters = {
        // @ComponentScan.Filter(value = Controller.class, type =
        // FilterType.ANNOTATION),
        //@ComponentScan.Filter(value = EnableWebMvc.class, type = FilterType.ANNOTATION),
        @ComponentScan.Filter(pattern = { "com.szcgc.wechat.controller.*" }, type = FilterType.REGEX) })
public class WebRootConfig {
    private static final Logger logger = LoggerFactory.getLogger(WebRootConfig.class);

    @Autowired
    private ServletContext servletContext;

    @Autowired
    private MsgScheduler msgScheduler;

    @PostConstruct
    public void initApp() {
        WebEnvConfig.initApp();
        WebEnvConfig.CONTEXT = servletContext;
        WebEnvConfig.CONTEXTPATH = servletContext.getContextPath();
        WebEnvConfig.REALPATH = servletContext.getRealPath("/");
        System.out.println(WebEnvConfig.CONTEXTPATH);
        System.out.println(WebEnvConfig.REALPATH);
        System.out.println("wechat init finish " + System.currentTimeMillis());
        System.out.println("测试中文乱码1111 " + System.currentTimeMillis());
        msgScheduler.sendMsg();
        logger.info("wechat init finish " + System.currentTimeMillis());
    }

}
