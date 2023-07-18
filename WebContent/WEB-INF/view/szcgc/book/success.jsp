<%@ page language="java" contentType="text/html; charset=UTF-8" %>
        <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
        <html xmlns="http://www.w3.org/1999/xhtml">
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
            <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;" name="viewport" />
            <title>深圳担保服务平台</title>



            <%@ include file="../common/base_recommend.jsp" %>



            <!--phone750-->
            <%@ include file="../common/jspage/jspage_phone750.jsp" %>

            <!--选择框-->
            <!--
            <link href="/mobile/plugin/westvalley_wev8/szcgc/common/css/mobileSelect.css" rel="stylesheet" type="text/css">
            <script src="/mobile/plugin/westvalley_wev8/szcgc/common/js/mobileSelect.js" type="text/javascript"></script>
             -->
            <%@ include file="../common/jspage/jspage_mobileSelect.jsp" %>
            <!--  -->
            <!--
            <link  href="/mobile/plugin/westvalley_wev8/szcgc/common/css/Popup_donate.css" rel="stylesheet">
            <script src="/mobile/plugin/westvalley_wev8/szcgc/common/js/Popup.js"></script>
            -->

        </head>
<body>
<div class="main_container">


    <!--成功-->
    <div class="success">
        <img  src="${pageContext.servletContext.contextPath}/resources/images/recommend/success.png" />
    </div>
    <div>
        <b class="success_text">提交成功！</b>
    </div>
    <div class="success">
        <img style="max-width: calc(60%)" src="${pageContext.servletContext.contextPath}/resources/images/book_qr.jpg" />
        <p style="font-size:20px"><b>请长按页面识别二维码付款，<span style="color: #ff0000">付款时务必备注姓名+数量</span>。</b></p></br>
    </div>

    <div class="reminder">
        <p style="font-size:20px"><b>付款后我们会尽快给您寄出书籍。</b></p></br>
        </br>
        <p>注明：本书籍仅供同行交流之所用，不提供发票和收据，请谅解。</p></br>








    </div>






</div>


</body>


</html>


