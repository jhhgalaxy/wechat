<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="org.apache.commons.lang3.StringUtils"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;" name="viewport" />
<meta name="description" content="深圳担保服务平台" />
<meta name="keywords" content="深圳担保服务平台" />
<title>深圳担保服务平台</title>
<%@ include file="jspage/jspage_base.jsp" %>
</head>
<body>
<div class="headbar"><span><img src="${pageContext.servletContext.contextPath}/resources/images/ico_top04.png" /></span>${tipTitle}</div>
<div class="whole_frame">
	<div class="no_data" style="padding-top: 200px;margin: 0px auto 0 auto;"><img src="${pageContext.servletContext.contextPath}/resources/images/no_data.png" /><p>${tipContent}</p></div>
</div>
<div class="foot_bg"><img src="${pageContext.servletContext.contextPath}/resources/images/foot_bg.png" /></div>
</body>
</html>