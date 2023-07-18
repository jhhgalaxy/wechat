<%@ page import="com.szcgc.wechat.util.CommonUtil" %>
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
    <%@ include file="../../common/base.jsp" %>

    <!--phone750自适应 -->
    <%@ include file="../../common/jspage/jspage_phone750.jsp" %>
    <!--弹出层-->
    <!--
    <link  href="/mobile/plugin/westvalley_wev8/szcgc/common/css/Popup_donate.css" rel="stylesheet">
    <script src="/mobile/plugin/westvalley_wev8/szcgc/common/js/Popup.js"></script>
    -->
    <%@ include file="../../common/jspage/jspage_popup.jsp" %>

</head>
<body>
<div class="headbar"><span><img src="${pageContext.servletContext.contextPath}/resources/images/ico_top04.png"/></span>项目列表
</div>

<div class="whole_frame">
    <!-- 筛选 -->
    <div class="screen_frame">
        <div class="device_top">
            <div class="device_choice Home_sort_data" style="width: ${searchDivWidth}"><span
                    class="screen_width">综合排序</span><i class="fa fa-chevron-down"></i></div>
            <c:if test="${hasYewpz}">
                <div class="device_choice Home_business_data" style="width: ${searchDivWidth}"><span
                        class="screen_width">业务品种</span><i class="fa fa-chevron-down"></i></div>
            </c:if>

            <c:if test="${hasPrice}">
                <div class="device_choice Home_money_data" style="width: ${searchDivWidth}"><span
                        class="screen_width">项目金额</span><i class="fa fa-chevron-down"></i></div>
            </c:if>
        </div>
    </div>
    <!-- 筛选 -->

    <div style="height:74px"></div>

    <c:if test="${loaningcount!=0}">
        <!--已放款-->
        <div class="project_frame">
            <div class="project_frame_head">
                <div class="project_frame_title"><img
                        src="${pageContext.servletContext.contextPath}/resources/images/ico_title.png"/>已放款
                </div>
                <c:if test="${loaningcount>3}">
                    <div class="project_frame_more"><a
                            href="listproject?userid=${sessionScope.user.id}&status=1">更多(${loaningcount})</a>
                    </div>
                </c:if>
            </div>

            <c:if test="${loaningList!=null&&loaningList.size!=0}">
                <c:forEach var="item" items="${loaningList.getContent()}">
                    <div class="project_list">
                        <a href="viewproject?userid=${sessionScope.user.id}&mainid=${item.getMainid()}">
                            <div class="project_list_label"><img
                                    src="${pageContext.servletContext.contextPath}/resources/images/ico_label01.png"/>
                            </div>
                            <h1>${item.getProjectname()}
                            </h1>
                            <div class="project_list_money">${CommonUtil.format(item.getLoanamount(), "###,###.##")}
                            </div>
                            <div class="project_list_txt">
                                <p><span>业务品种：</span>${item.getBusinessclass()}
                                </p>
                                <p></p>
                                <p><span>放款日期：</span>${item.getStartdate().toString()}
                                </p>
                                <p><span>到期日期：</span>${item.getEnddate().toString()}
                                </p>
                            </div>
                        </a>
                    </div>
                </c:forEach>
            </c:if>


        </div>
        <!--已放款-->
    </c:if>

    <c:if test="${unloanCount!=0}">
        <!--未放款-->
        <div class="project_frame">
            <div class="project_frame_head">
                <div class="project_frame_title"><img
                        src="${pageContext.servletContext.contextPath}/resources/images/ico_title.png"/>未放款
                </div>
                <c:if test="${unloanCount>3}">
                    <div class="project_frame_more"><a href="listproject.jsp?userid=${sessionScope.user.id}&status=0">更多(${unloanCount})</a>
                    </div>
                </c:if>
            </div>

            <c:if test="${unloanList!=null&&unloanList.size!=0}">
                <c:forEach var="item" items="${unloanList.getContent()}">
                    <div class="project_list">
                        <a href="viewproject.jsp?userid=${sessionScope.user.id}&mainid=${item.getMainid()}">
                            <div class="project_list_label"><img
                                    src="${pageContext.servletContext.contextPath}/resources/images/ico_label02.png"/>
                            </div>
                            <h1>${item.getProjectname()}
                            </h1>
                            <div class="project_list_money">${CommonUtil.format(item.getProjectName(), "###,###.##")}
                            </div>
                            <div class="project_list_txt">
                                <p><span>业务品种：</span>${item.getBusinessclass()}
                                </p>
                                <p></p>
                                <p><span>放款日期：</span>${item.getStartdate().toString()}
                                </p>
                                <p><span>到期日期：</span>${item.getEnddate().toString()}
                                </p>
                            </div>
                        </a>
                    </div>
                </c:forEach>
            </c:if>
        </div>
        <!--未放款-->
    </c:if>

    <c:if test="${loanedcount!=0}">
        <!--已解保-->
        <div class="project_frame">
            <div class="project_frame_head">
                <div class="project_frame_title"><img
                        src="${pageContext.servletContext.contextPath}/resources/images/ico_title.png"/>已解保
                </div>
                <c:if test="${loanedcount>3}">
                    <div class="project_frame_more"><a href="listproject.jsp?userid=${sessionScope.user.id}&status=2">更多(${loanedcount})</a>
                    </div>
                </c:if>
            </div>

            <c:if test="${loanedList!=null&&loanedList.size!=0}">
                <c:forEach var="item" items="${loanedList.getContent()}">
                    <div class="project_list">
                        <a href="viewproject.jsp?userid=${sessionScope.user.id}&mainid=${item.getMainid()}">
                            <div class="project_list_label"><img
                                    src="${pageContext.servletContext.contextPath}/resources/images/ico_label03.png"/>
                            </div>
                            <h1>${item.getProjectname()}
                            </h1>
                            <div class="project_list_money">${CommonUtil.format(item.getProjectName(), "###,###.##")}
                            </div>
                            <div class="project_list_txt">
                                <p><span>业务品种：</span>${item.getBusinessclass()}
                                </p>
                                <p></p>
                                <p><span>放款日期：</span>${item.getStartdate().toString()}
                                </p>
                                <p><span>到期日期：</span>${item.getEnddate().toString()}
                                </p>
                            </div>
                        </a>
                    </div>
                </c:forEach>
            </c:if>
        </div>
        <!--已解保-->
    </c:if>
</div>
<div class="foot_bg"><img src="${pageContext.servletContext.contextPath}/resources/images/foot_bg.png"/></div>
<!--综合排序弹出层-->
<div class="cd-Home_sort home_cont" role="alert">
    <div class="cd-Upload-container" style="height:6.95rem">
        <div class="Subtask-cont">

            <!--头部-->
            <div class="device_top">
                <div class="device_choice open Home_sort_data" style="width: ${searchDivWidth}"><span
                        class="screen_width">综合排序</span><i class="fa fa-chevron-down"></i></div>
                <c:if test="${hasYewpz}">
                    <div class="device_choice Home_business_data" style="width: ${searchDivWidth}"><span
                            class="screen_width">业务品种</span><i class="fa fa-chevron-down"></i></div>
                </c:if>
                <c:if test="${hasPrice}">
                    <div class="device_choice Home_money_data" style="width: ${searchDivWidth}"><span
                            class="screen_width">项目金额</span><i class="fa fa-chevron-down"></i></div>
                </c:if>
            </div>
            <!--头部-->
            <div class="Intelligence_sort">
                <li dataType="order" dataValue="" class="active"><a href="javascript:void(0)"><span>综合排序</span><span
                        class="ico_selected"></span></a></li>
                <li dataType="order" dataValue="fangkrq desc"><a href="javascript:void(0)"><span>放款日期最近</span><span
                        class="ico_selected"></span></a></li>
                <li dataType="order" dataValue="daoqhkr desc"><a href="javascript:void(0)"><span>到期日期最近</span><span
                        class="ico_selected"></span></a></li>
                <li dataType="order" dataValue="fangkje desc"><a href="javascript:void(0)"><span>放款最高</span><span
                        class="ico_selected"></span></a></li>
                <li dataType="order" dataValue="fangkje"><a href="javascript:void(0)"><span>放款最低</span><span
                        class="ico_selected"></span></a></li>
            </div>

        </div>
    </div>
</div>
<!--综合排序弹出层-->

<c:if test="${hasYewpz}">
    <!--业务品种弹出层-->
    <div class="cd-Home_business home_eject" role="alert">
        <div class="cd-Upload-container" style="height:6.95rem">
            <div class="Subtask-cont">

                <!--头部-->
                <div class="device_top">
                    <div class="device_choice Home_sort_data" style="width: ${searchDivWidth}"><span class="screen_width">综合排序</span><i
                            class="fa fa-chevron-down"></i></div>
                    <div class="device_choice open Home_business_data" style="width: ${searchDivWidth}"><span
                            class="screen_width">业务品种</span><i class="fa fa-chevron-down"></i></div>
                    <c:if test="${hasPrice}">
                        <div class="device_choice Home_money_data" style="width: ${searchDivWidth}"><span
                                class="screen_width">项目金额</span><i class="fa fa-chevron-down"></i></div>
                    </c:if>
                </div>
                <div class="Intelligence_sort">
                    <li dataType="businessclass" dataValue="" class="active"><a href="javascript:void(0)"><span>不限</span><span
                            class="ico_selected"></span></a></li>
					<c:forEach var="item" items="${businessClassList}">
                    <li dataType="businessclass" dataValue="${item}"><a href="javascript:void(0)"><span>${item}</span><span
                            class="ico_selected"></span></a></li>
					</c:forEach>
                </div>

            </div>
        </div>
    </div>
    <!--业务品种弹出层-->
</c:if>

<c:if test="${hasPrice}">
<!--项目金额弹出层-->
<div class="cd-Home_money home_eject" role="alert">
    <div class="cd-Upload-container" style="height:6.95rem">
        <div class="Subtask-cont">

            <!--头部-->
            <div class="device_top">
                <div class="device_choice Home_sort_data" style="width:${searchDivWidth}"><span class="screen_width">综合排序</span><i
                        class="fa fa-chevron-down"></i></div>
	<c:if test="${hasYewpz}">
                <div class="device_choice Home_business_data" style="width:${searchDivWidth}"><span
                        class="screen_width">业务品种</span><i class="fa fa-chevron-down"></i></div>
	</c:if>
                <div class="device_choice open Home_money_data" style="width:${searchDivWidth}"><span
                        class="screen_width">项目金额</span><i class="fa fa-chevron-down"></i></div>
            </div>
            <div class="Intelligence_sort">
                <li dataType="priceType" dataValue="" class="active"><a href="javascript:void(0)"><span>不限</span><span
                        class="ico_selected"></span></a></li>
                <%		String[] projectPriceValueArr = (String[])request.getAttribute("projectPriceValueArr");
						String[] projectPriceLabelArr = (String[])request.getAttribute("projectPriceLabelArr");
						for (int i = 0; i < projectPriceValueArr.length; i++) { %>
                <li dataType="priceType" dataValue="<%=projectPriceValueArr[i]%>"><a
                        href="javascript:void(0)"><span><%=projectPriceLabelArr[i]%></span><span
                        class="ico_selected"></span></a></li>
                <%} %>
            </div>
        </div>
    </div>
</div>
<!--项目金额弹出层-->
</c:if>

</body>
<script>
    $(function () {
        $(".Intelligence_sort li a").click(function () {
            var addressLi = $(this).parent("li");
            var liDataType = addressLi.attr("dataType");
            var liDataValue = addressLi.attr("dataValue");
            if (liDataValue != "") {
                window.location.href = "listproject?userid=${sessionScope.user.id}&" + liDataType + "=" + liDataValue;
            }
        });
    });
</script>
</html>
