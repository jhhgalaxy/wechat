<%--
  Author: JINLINGXUAN
  Date: 2021/6/7
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;" name="viewport"/>
    <meta name="description" content="深圳担保集团固定资产"/>
    <meta name="keywords" content="深圳担保集团固定资产"/>
    <title>深圳担保集团固定资产</title>

    <style>
        html,body{
            padding:0;
            margin:0;
        }
        .top{
            height:70px;
            width:100%;
            background: #cd0a10;
            text-align:center;
            position:relative
        }
        .title{
            text-align: center;
            width: 100%;
            font-size: 22px;
            font-weight: 700;
            line-height:22px;
            position:absolute;
            bottom:25px;
            padding:0px;
            margin:0px;
            word-wrap: break-word;
            white-space: pre-line;
            color:#fff;
        }
        .table{
            position: relative;
            padding: 15px;
            background-color: rgb(247, 248, 250);
            margin: 15px;
            border-radius: 15px;
        }
        .column{
            padding: 15px 5px 5px;
            border-bottom: 1px solid rgb(230, 230, 230);
        }
        .column-title{
            color: rgb(145, 158, 173);
            font-size: 14px;
            margin-bottom: 5px;
            line-height: 14px;
            min-height: 15px;
        }
        .column-text{
            margin: 8px 0;
            font-size: 18px;
        }
    </style>
</head>
<body>
    <div class="top">
        <p class="title">深圳担保集团固定资产</p>
    </div>
    <section class="table">
        <section class="column">
            <section class="column-title">
                资产编码
            </section>
            <p class="column-text">
                <strong>${assetCode}</strong>
            </p>
        </section>
        <section class="column">
            <section class="column-title">
                资产名称
            </section>
            <p class="column-text">
                <strong>${assetName}</strong>
            </p>
        </section>
        <section class="column">
            <section class="column-title">
                品牌
            </section>
            <p class="column-text">
                <strong>${brand}</strong>
            </p>
        </section>
        <section class="column">
            <section class="column-title">
                出厂日期
            </section>
            <p class="column-text">
                <strong>${productDate}</strong>
            </p>
        </section>
        <section class="column">
            <section class="column-title">
                购置日期
            </section>
            <p class="column-text">
                <strong>${buyDate}</strong>
            </p>
        </section>
        <section class="column">
            <section class="column-title">
                领用人
            </section>
            <p class="column-text">
                <strong>${user}</strong>
            </p>
        </section>
        <section class="column">
            <section class="column-title">
                领用日期
            </section>
            <p class="column-text">
                <strong>${useDate}</strong>
            </p>
        </section>
        <section class="column">
            <section class="column-title">
                存放地点
            </section>
            <p class="column-text">
                <strong>${location}</strong>
            </p>
        </section>
    </section>
</body>
</html>
