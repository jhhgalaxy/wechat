<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;" name="viewport"/>
    <meta name="description" content="深圳担保服务平台"/>
    <meta name="keywords" content="深圳担保服务平台"/>
    <title>深圳担保服务平台</title>

    <link href="${pageContext.servletContext.contextPath}/resources/css/css.css" rel="stylesheet">

    <style>
        .business_span{
            border: none;
            height: 50px;
            font-size: 14px;
            width: 100%;
            text-align: right;
            color: #333333;
        }

        .b-line span{

        }

        .e-table{
            border-radius: 10px;
        }

        .e-table tr{
            font-size: 14px;
            text-overflow: ellipsis;
            -webkit-box-orient: vertical;
            -webkit-line-clamp: 1;
            padding: 5px 0;
            text-align: left;
            border-bottom-width: 1px;
            border-bottom-style: solid;
            border-bottom-color: #E8E8E8;
            overflow: hidden;
            display: flex!important;
        }

        .e-item{
            font-weight: normal;
            font-size: 14px;
            color: #999999;
            overflow: hidden;
            width: 150px;
            float: left;
            line-height:50px;
        }

        .e-info{
            border: none;
            height: 50px;
            font-size: 14px;
            width: 100%;
            text-align: right;
            color: #333333;
            vertical-align: middle;
            word-break: break-word;
            line-height: 50px;
        }

        .e-bank{
            border: none;
            font-size: 14px;
            width: 100%;
            text-align: right;
            color: #333333;
            vertical-align: middle;
            word-break: break-word;
            padding: 12px;
            padding-right: 0;
        }

    </style>

</head>
<body>
<div class="business_bg"><img src="${pageContext.servletContext.contextPath}/resources/images/business_bg.png"/></div>
<div class="business">
    <table class="e-table">
        <tr>
            <td class="e-item">客户名称</td>
            <td class="e-info">${projectname}</td>
        </tr>
        <tr>
            <td class="e-item">业务品种</td>
            <td class="e-info">${businessclass}</td>
        </tr>
        <tr>
            <td class="e-item">项目金额</td>
            <td class="e-info">${projectamount}&nbsp;元</td>
        </tr>
        <c:if test="${type=='G'}">
            <tr>
                <td class="e-item">担保意向银行</td>
                <td class="e-bank">${bank}</td>
            </tr>
            <tr>
                <td class="e-item">担保期限</td>
                <td class="e-info">${guaDate}&nbsp;个月</td>
            </tr>
            <tr>
                <td class="e-item">有效日期</td>
                <td class="e-info">${endDate}</td>
            </tr>
        </c:if>
    </table>
    <div class="reminder">
        此信息由深圳担保集团服务平台提供
    </div>
</div>

<div class="foot_bg" style="bottom: 0px;position: fixed;padding:0;"><img src="${pageContext.servletContext.contextPath}/resources/images/foot_bg.png"/></div>
</body>
</html>
