<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="com.westvalley.tool.szcgc.util.LogUtil"%>
<%@ page import="com.westvalley.tool.szcgc.util.SzcgcUtil"%>
<%@ page import="com.westvalley.tool.szcgc.web.UserPage"%>
<%@ page import="com.westvalley.tool.szcgc.web.CenterPage"%>
<%@ page import="com.westvalley.tool.szcgc.web.BusinessPage"%>
<%@ page import="com.westvalley.tool.szcgc.web.ComplaintPage"%>
<%@ page import="com.westvalley.tool.szcgc.web.ProjectPage"%>
<%@ page import="com.westvalley.tool.szcgc.web.MsgPage"%>
<%
	response.setCharacterEncoding("UTF-8");
	String cmd = SzcgcUtil.trim(request.getParameter("cmd"));
	if("sendSms".equals(cmd)){// 发送手机短信获取手机绑定验证码
		UserPage userPage = new UserPage();
		userPage.sendSms(request,response);
	} else if("checkSmsCode".equals(cmd)){// 验证手机绑定的验证码
		UserPage userPage = new UserPage();
		userPage.checkSmsCode(request,response);
	} else if("addBusiness".equals(cmd)){// 添加业务申报
		BusinessPage service = new BusinessPage();
		service.addBusiness(request,response);
	} else if("listBusiness".equals(cmd)){// 查询申报列表
		BusinessPage service = new BusinessPage();
		service.listBusiness(request,response);
	} else if("addComplaint".equals(cmd)){// 添加投诉建议
		ComplaintPage service = new ComplaintPage();
		service.addComplaint(request,response);
	} else if("listProject".equals(cmd)){// 查询项目列表
		ProjectPage service = new ProjectPage();
		service.listProject(request,response);
	} else if("listMsg".equals(cmd)){// 查询消息列表
		MsgPage service = new MsgPage();
		service.listMsg(request,response);
	}
%>