package com.szcgc.wechat.servlet;

import com.szcgc.wechat.util.MenuUtil;

import java.io.IOException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Application Lifecycle Listener implementation class WechatListener
 *
 */
//@WebListener
public class WechatListener implements ServletContextListener {

	/**
	 * Default constructor.
	 */
	public WechatListener() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try {
			MenuUtil.setMenu();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
