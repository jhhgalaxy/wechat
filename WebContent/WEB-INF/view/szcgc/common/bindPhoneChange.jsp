<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;" name="viewport" />
<title>用户绑定</title>
<%@ include file="base.jsp" %>
</head>

<body>

<div class="change">
	<div class="change_img"><img src="${pageContext.servletContext.contextPath}/resources/images/ico_change.png" /></div>
	<div class="change_phone">您绑定的手机号：${cellphone}</div>
	<div class="change_phone" style="font-size: 14px;color: #999;margin-top: 30px;padding: 0 20px;">您已绑定手机号，如想更换或者取消绑定，请点击下面的更换手机号按钮</div>
	<div class="change_button"style="margin: 1rem auto 0 auto;"><a href="/user/unbind?userid=${userid}">更换手机号</a></div>
</div>

</body>
</html>
