
package com.szcgc.wechat.servlet;

import com.szcgc.wechat.util.MessageUtil;
import com.szcgc.wechat.util.SignUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 核心请求处理类
 * 
 * @author yangyc
 * @date 2020-3-19
 */
//@WebServlet("/cgcWX")
public class WeChatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public WeChatServlet() {
		super();
	}

	/**
	 * 确认请求来自微信服务器（微信平台发过来get请求可以都在这里处理）
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// System.out.println("begin");
		// signature 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
		String signature = request.getParameter("signature");
		// timestamp 时间戳
		String timestamp = request.getParameter("timestamp");
		// nonce 随机数
		String nonce = request.getParameter("nonce");
		// echostr 随机字符串
		String echostr = request.getParameter("echostr");

		// 检验signature进行校验,成功则返回echostr,接入成功;
		if (SignUtil.checkSignature(signature, timestamp, nonce)) {
			PrintWriter out = response.getWriter();
			out.print(echostr);
			out.flush();
			out.close();
		}
		// System.out.println("get");
	}

	/**
	 * 接收消息和事件推送（微信平台发过来post请求可以都在这里处理）
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf8");
		response.setCharacterEncoding("utf8");
		// System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

		// 处理消息和事件推送,requestmap中为键值对
		Map<String, String> requestMap = MessageUtil.parseRequest(request.getInputStream());
		// 理论上能打印出收到的各类消息的参数，因为收到消息的层数和模板是一样的；且所有的参数都已经存到了requestMap中，这样之前的步骤才是正确的；
		// System.out.println(requestMap);
		// System.out.println("^_^ ^_^ ^_^ ^_^");

		// 回复数据包
		String resXml = MessageUtil.dealResponse(requestMap);
		// System.out.println(resXml);

		PrintWriter out = response.getWriter();
		out.print(resXml);
		out.flush();
		out.close();

	}

}
