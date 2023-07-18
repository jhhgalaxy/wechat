<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;" name="viewport"/>
    <meta name="description" content="深圳担保服务平台"/>
    <meta name="keywords" content="深圳担保服务平台"/>
    <title>深圳担保服务平台</title>
    <%@ include file="../common/base.jsp" %>
</head>
<body>
<div class="headbar"><span><img src="${pageContext.servletContext.contextPath}/resources/images/ico_top01.png"/></span>用户中心
</div>

<div class="whole_personal">
    <!-- 人员信息 -->
    <div class="personal">
        <div class="personal_portrait">
            <img src="${pageContext.servletContext.contextPath}/resources/images/touxiang.jpg"/>
        </div>
        <div class="personal_info">
            <c:choose>
                <c:when test="${hasbinding==true}">
                    <p onclick="bindPhoneChange()">
                        <span>${sessionScope.userinfo.nickName}</span>${sessionScope.user.cellphone}
                    </p>
                </c:when>

                <c:when test="${hasbinding==false}">
                    <p onclick="bindUser()"><span>${sessionScope.userinfo.nickName}</span>(未绑定手机号,点击绑定)</p>
                </c:when>
            </c:choose>
            <c:choose>
                <c:when test="${hascompany==true}">
                    <p>用户类型：合作企业</p>
                </c:when>
                <c:when test="${hascompany==false}">
                    <p>用户类型：普通用户</p>
                </c:when>
            </c:choose>


        </div>
    </div>
    <!-- 人员信息 -->

    <!-- 模块列表 -->
    <div class="personal_column">
        <li class="Underline">
            <a href="/project?userid=${userid}">
                <img src="${pageContext.servletContext.contextPath}/resources/images/ico_column01.png"/>项目列表
            </a>
        </li>
        <div class="personal_column_line">
            <img src="${pageContext.servletContext.contextPath}/resources/images/ico_line.png"/>
        </div>
        <li class="Underline">
            <a href="/business/index">
                <img src="${pageContext.servletContext.contextPath}/resources/images/ico_column02.png"/>业务申报
            </a>
        </li>
        <li>
            <a href="/message/listMsg">
                <img src="${pageContext.servletContext.contextPath}/resources/images/ico_column03.png"/>消息通知
            </a>
        </li>
        <div class="personal_column_line">
            <img src="${pageContext.servletContext.contextPath}/resources/images/ico_line.png"/>
        </div>
        <li>
            <a href="/addSuggestion">
                <img src="${pageContext.servletContext.contextPath}/resources/images/ico_column04.png"/>投诉建议
            </a>
        </li>
    </div>
    <div class="personal_foot"></div>
</div>
<div class="foot_bg"><img src="${pageContext.servletContext.contextPath}/resources/images/foot_bg.png"/></div>
</body>
<script type="text/javascript">
    function bindUser() {
        window.location.href = "user/bind?userid=${userid}";
    }

    function bindPhoneChange() {
        window.location.href = "user/bindstatus?userid=${userid}";
    }
</script>
</html>