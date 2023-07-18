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
    <%@ include file="../../common/base.jsp" %>


    <!--phone750自适应 -->
    <%@ include file="../../common/jspage/jspage_phone750.jsp" %>
    <!--弹出层-->
    <!--
    <link  href="/mobile/plugin/westvalley_wev8/szcgc/common/css/Popup_donate.css" rel="stylesheet">
    <script src="/mobile/plugin/westvalley_wev8/szcgc/common/js/Popup.js"></script>
    -->
    <%@ include file="../../common/jspage/jspage_popup.jsp" %>
    <!--上拉加载，下拉刷新-CSS、JS-->
    <!--
    <link href="/mobile/plugin/westvalley_wev8/szcgc/common/vendors/mescroll-master/mescroll.min.css" type="text/css" media="all" rel="stylesheet" >
    <script src="/mobile/plugin/westvalley_wev8/szcgc/common/vendors/mescroll-master/mescroll.min.js"></script>
    -->
    <%@ include file="../../common/jspage/jspage_mescroll.jsp" %>
    <style>
        .mescroll {
            position: fixed;
            top: 74px;
            bottom: 0;
            height: auto; /*如设置bottom:50px,则需height:auto才能生效*/
        }

        .whole_frame_inner {
            min-height: calc(100% - 169px);
        }

        .no_data_inner {
            padding-top: 200px;
            text-align: center;
        }

        .no_data_inner img {
            width: 110px;
            margin-bottom: 22px
        }

        .no_data_inner p {
            font-size: 16px;
            color: #CCC;
            letter-spacing: 4.5px;
            padding-left: 7.5px
        }
    </style>
    <script type="text/javascript">
        //项目列表分页加载
        var mescroll;
        var isEmpty = true;
        $(function () {
            /*创建MeScroll对象*/
            mescroll = new MeScroll("mescroll", {
                down: {
                    auto: false, //是否在初始化完毕之后自动执行下拉回调callback; 默认true
                    callback: downCallback //下拉刷新的回调
                },
                up: {
                    auto: true,//初始化完毕,是否自动触发上拉加载的回调
                    isBoth: true, //上拉加载时,如果滑动到列表顶部是否可以同时触发下拉刷新;默认false,两者不可同时触发; 这里为了演示改为true,不必等列表加载完毕才可下拉;
                    isBounce: false, //此处禁止ios回弹,解析(务必认真阅读,特别是最后一点): http://www.mescroll.com/qa.html#q10
                    callback: upCallback, //上拉加载的回调
                    noMoreSize: 5,//如果列表已无数据,可设置列表的总数量要大于5才显示无更多数据;
                    toTop: { //配置回到顶部按钮
                        src: "${pageContext.servletContext.contextPath}/resources/vendors/mescroll-master/mescroll-totop.png", //默认滚动到1000px显示,可配置offset修改
                        //offset : 1000
                    },
                    empty: {
                        //列表第一页无任何数据时,显示的空提示布局; 需配置warpId才显示
                        warpId: "projectList", //父布局的id (1.3.5版本支持传入dom元素)
                        icon: "${pageContext.servletContext.contextPath}/resources/vendors/mescroll-master/mescroll-empty.png", //图标,默认null,支持网络图
                        tip: "暂无相关数据~" //提示
                    },
                }
            });

            /*下拉刷新的回调 */
            function downCallback() {
                $("#searchForm").submit();
            }

            /*上拉加载的回调 page = {num:1, size:10}; num:当前页 从1开始, size:每页数据条数 */
            function upCallback(page) {
                getData(page);
            }
        });

        //获取消息列表数据
        function getData(page) {
            var id = ${sessionScope.user.id};
            var status = $("#status").val();
            var order = $("#order").val();
            var type = $("#businessclass").val();
            var amountRange = $("#amountRange").val();
            var data = {curPage:page.num,userid:id,status:status,businessclass:type,amountRange:amountRange};
            var url = "queryproject";
            jQuery.ajax({
                type: 'POST',
                contentType: "application/x-www-form-urlencoded; charset=utf-8",
                data: {userid:id,status:status,businessclass:type,amountRange:amountRange,curPage:page.num},
                url: url,
                dataType: "json",
                success: function (result) {//获取成功
                    parseData(result);
                },
                error: function (e) {//获取失败
                    msg("请求服务器数据失败！");
                }
            });
        }

        //渲染数据
        function parseData(result) {
            var obj = result.data;
            var totalCount = result.count;
            mescroll.endBySize(obj.length, totalCount);
            if (obj != null && obj.length > 0) {
                isEmpty = false;
                var imgIco = "ico_label01";// 默认已放款图标
                if ($("#status").val() == "0") {
                    imgIco = "ico_label02";// 未放款图标
                } else if ($("#status").val() == "2") {
                    imgIco = "ico_label03";// 已解保图标
                }
                for (var i = 0; i < obj.length; i++) {
                    if (obj[i] == null) {
                        continue;
                    }
                    var url = "viewproject?userid=${sessionScope.user.id}&mainid=" + obj[i].mainid;
                    var result = ""
                        + "<div class=\"project_list\">"
                        + "	<a href=\"" + url + "\">"
                        + "		<div class=\"project_list_label\"><img src=\"${pageContext.servletContext.contextPath}/resources/images/" + imgIco + ".png\" /></div>"
                        + "		<h1>" + obj[i].projectname + " </h1>"
                        + "		<div class=\"project_list_money\">" + obj[i].amount + "元</div>"
                        + "		<div class=\"project_list_txt\">"
                        + "			<p><span>业务品种：</span>" + obj[i].business + "</p>"
                        + "			<p></p>"
                        + "			<p><span>放款日期：</span>" + obj[i].startDate + "</p>"
                        + "			<p><span>到期日期：</span>" + obj[i].endDate + "</p>"
                        + "		</div>"
                        + "	</a>"
                        + "</div>";
                    $("#projectList").append(result);//添加数据
                }
            }
            if (isEmpty) {
                $(".whole_frame_inner").show();
                $(".foot_bg").show();
                $("#mescroll").hide();
            } else {
                $(".whole_frame_inner").hide();
                $(".foot_bg").hide();
                $("#mescroll").show();
            }
        }
    </script>
</head>
<body>
<div class="headbar"><span><img src="${pageContext.servletContext.contextPath}/resources/images/ico_top04.png"/></span>项目列表
</div>
<form id="searchForm" action="/listproject">
    <input type="hidden" id="userid" name="userid" value="${sessionScope.user.id}">
    <input type="hidden" id="status" name="status" value="${requestScope.get("status")}">
    <input type="hidden" id="order" name="order"
           value="<%=StringUtils.isNotBlank(request.getParameter("order"))?request.getParameter("order"):""%>">
    <input type="hidden" id="businessclass" name="businessclass"
           value="<%=StringUtils.isNotBlank(request.getParameter("businessclass"))?request.getParameter("businessclass"):""%>">
    <input type="hidden" id="amountRange" name="amountRange"
           value="<%=StringUtils.isNotBlank(request.getParameter("amountRange"))?request.getParameter("amountRange"):""%>">
</form>

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

<!--项目列表-->
<div id="mescroll" class="mescroll">
    <div id="projectList" class="project_list_frame"></div>
</div>
<!--项目列表-->
<div class="whole_frame_inner" style="display: none;">
    <div class="no_data_inner"><img src="${pageContext.servletContext.contextPath}/resources/images/no_data.png"/>
        <p>暂无项目</p></div>
</div>
<div class="foot_bg" style="display: none;"><img
        src="${pageContext.servletContext.contextPath}/resources/images/foot_bg.png"/>
</div>

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
                <li dataType="order" dataValue=""
                    dataParent="cd-Home_sort" <%=StringUtils.isBlank(request.getParameter("order")) ? "class='active'" : ""%>>
                    <a
                            href="javascript:void(0)"><span>综合排序</span><span class="ico_selected"></span></a></li>
            </div>

        </div>
    </div>
</div>
<!--综合排序弹出层-->
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
                    <div class="device_choice Home_money_data" style="width:  ${searchDivWidth}"><span
                            class="screen_width">项目金额</span><i class="fa fa-chevron-down"></i></div>
                </c:if>
            </div>
            <div class="Intelligence_sort">
                <li dataType="businessclass" dataValue=""
                    dataParent="cd-Home_business" <%=StringUtils.isBlank(request.getParameter("businessclass")) ? "class='active'" : ""%>><a
                        href="javascript:void(0)"><span>不限</span><span class="ico_selected"></span></a></li>
                <c:forEach var="item" items="${businessClassList}">
                    <li dataType="businessclass" dataValue="${item}"
                        dataParent="cd-Home_business"
                    <c:if test="${param.businessclass eq item}">
                        "class='active'"
                    </c:if> ><a
                            href="javascript:void(0)"><span>${item}</span><span class="ico_selected"></span></a></li>
                </c:forEach>
            </div>

        </div>
    </div>
</div>
<!--业务品种弹出层-->
<!--项目金额弹出层-->
<div class="cd-Home_money home_eject" role="alert">
    <div class="cd-Upload-container" style="height:6.95rem">
        <div class="Subtask-cont">
            <!--头部-->
            <div class="device_top">
                <div class="device_choice Home_sort_data" style="width: ${searchDivWidth}"><span class="screen_width">综合排序</span><i
                        class="fa fa-chevron-down"></i></div>
<c:if test="${hasYewpz}">
                <div class="device_choice Home_business_data" style="width: ${searchDivWidth}"><span
                        class="screen_width">业务品种</span><i class="fa fa-chevron-down"></i></div>
</c:if>
                <div class="device_choice open Home_money_data" style="width: ${searchDivWidth}"><span
                        class="screen_width">项目金额</span><i class="fa fa-chevron-down"></i></div>
            </div>
            <div class="Intelligence_sort">
                <li dataType="amountRange" dataValue=""
                    dataParent="cd-Home_money" <%=StringUtils.isBlank(request.getParameter("amountRange")) ? "class='active'" : ""%>><a
                        href="javascript:void(0)"><span>不限</span><span class="ico_selected"></span></a></li>
                <%  String[] projectPriceValueArr = (String[])request.getAttribute("projectPriceValueArr");
                    String[] projectPriceLabelArr = (String[])request.getAttribute("projectPriceLabelArr");
                    for (int i = 0; i < projectPriceValueArr.length; i++) { %>
                <li dataType="amountRange" dataValue="<%=projectPriceValueArr[i]%>"
                    dataParent="cd-Home_money" <%=StringUtils.equals(request.getParameter("amountRange"), projectPriceValueArr[i]) ? "class='active'" : ""%>>
                    <a href="javascript:void(0)"><span><%=projectPriceLabelArr[i]%></span><span
                            class="ico_selected"></span></a></li>
                <%} %>
            </div>

        </div>
    </div>
</div>
<!--项目金额弹出层-->
</body>
<script>
    $(function () {
        $(".Intelligence_sort li a").click(function () {
            var addressLi = $(this).parent("li");
            var liDataType = addressLi.attr("dataType");
            var liDataValue = addressLi.attr("dataValue");
            var liDataParent = addressLi.attr("dataParent");
            $("#" + liDataType).val(liDataValue);

            var addressState = addressLi.hasClass("active");
            if (addressState == true) {
                addressLi.removeClass("active");
            } else {
                addressLi.addClass("active").siblings("li").removeClass("active");
            }
            $("." + liDataParent).click();
            $("#searchForm").submit();
        });
    });
</script>
</html>
