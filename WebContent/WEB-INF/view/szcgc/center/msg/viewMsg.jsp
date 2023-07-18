<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;" name="viewport" />
<meta name="description" content="深圳担保服务平台" />
<meta name="keywords" content="深圳担保服务平台" />
<title>深圳担保服务平台</title>
<%@ include file="../../common/base.jsp" %>

</head>
<body>
<div class="whole_frame">
	<div class="notice_show" style="padding-bottom: 40px;">
	   <h1>${msg.msgtitle}</h1>
	   <p>${msg.typename}<span>${msg.createtime.toLocalDate().toString()}</span></p>
	   <div class="notice_project">
	      <a href="viewProject?userid=${sessionScope.user.id}&mainid=${msg.linkitem}">
		     <div class="notice_project_title">关联项目：${msg.project}</div>
		     <span><img src="${pageContext.servletContext.contextPath}/resources/images/ico_arrow.png" /></span>
		  </a>
	   </div>
	   <div class="notice_show_content">
	   		${msg.msgcontent}
	   </div>
	</div>
</div>
<div class="foot_bg"><img src="${pageContext.servletContext.contextPath}/resources/images/foot_bg.png" /></div>
</body>
</html>