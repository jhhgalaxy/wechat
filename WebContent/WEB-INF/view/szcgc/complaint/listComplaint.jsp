<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>

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
<div class="headbar"><span><img src="${pageContext.servletContext.contextPath}/resources/images/ico_top02.png"/></span>我的投诉
</div>
<div class="whole_frame">
    <c:if test="${list.size()!=0}">
        <div class="proposal_list_com">
            <c:forEach var="item" items="${list}">
                <div class="proposal_list">
                    <div class="proposal_list_img"><img
                            src="${pageContext.servletContext.contextPath}/resources/images/ico_proposal01.png"/>
					</div>
                    <div class="proposal_list_txt">
                        <p class="proposal_list_time">${item.getCreatetime().toLocalDate().toString()}
                        </p>
                    </div>
                    <c:if test="${not empty item.getReply()}"></c:if>
                    <div class="proposal_list_reply">
                        <div class="proposal_triangle">
                            <img src="${pageContext.servletContext.contextPath}/resources/images/ico_proposal02.png"/>
                        </div>
                        <p>客服回复：${item.getReply()}</p>
                    </div>

                </div>
            </c:forEach>
        </div>
    </c:if>
	<c:if test="${list.size()==0}">
    <div class="no_data" style="padding-top: 200px;margin: 0px auto 0 auto;"><img
            src="${pageContext.servletContext.contextPath}/resources/images/no_data.png"/>
        <p>暂无投诉</p></div>
	</c:if>
</div>

<div class="foot_bg"><img src="${pageContext.servletContext.contextPath}/resources/images/foot_bg.png"/></div>
</body>
</html>