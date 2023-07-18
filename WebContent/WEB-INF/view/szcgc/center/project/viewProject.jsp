<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.Date"%>
<%@ page import="java.math.BigDecimal"%>


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
<div class="headbar"><span><img src="${pageContext.servletContext.contextPath}/resources/images/ico_top04.png" /></span>项目详情</div>
<div class="whole_frame">
	<div class="project_show_top">
	    <div class="project_show_label"><img src="${pageContext.servletContext.contextPath}/resources/images/${statusImg}" /></div><!--未放款状态时，图片改为ico_label02.png-->
	    <h1>${projectname} </h1>
		<div class="project_show_money">${loanamount}</div>
		<div class="project_show_txt">${businessclass}</div>
	</div>
	
	<div class="project_show_info">
	
	   <li>放款日期<span>${startdate}</span></li>
	   <li>到期日期<span>${enddate}</span></li>
	   <li>项目经理<span>${manager}</span></li>
	
	</div>
	
	<div class="project_show_info">
	   <li>下期应还本金<span>${yinghje_benjin}</span></li>
	   <li>本金应还时间<span>${yinghrq_benjin}</span></li>
	   <li>下期应还利息<span>${yinghje_lixi}</span></li>
	   <li>利息应还时间<span>${yinghrq_lixi}</span></li>
	</div>
</div>
<div class="foot_bg"><img src="${pageContext.servletContext.contextPath}/resources/images/foot_bg.png" /></div>
</body>
</html>