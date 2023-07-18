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
<!--返回按钮-->
<div class="back">
	 <a href="/mobile/plugin/westvalley_wev8/szcgc/recommend/index.jsp">
	 	<img class="back_index" src="${pageContext.servletContext.contextPath}/resources/images/recommend/back2.png"  />
	 	</a>
	</div>




<!--成功-->
<div class="success">
<img  src="${pageContext.servletContext.contextPath}/resources/images/recommend/success.png" />
	</div>
	<div>
<b class="success_text">提交成功！</b>
	</div>
   <div class="reminder">
	*您的信息仅用于企业资质审核,我们将严格保密。
	<br>
	如企业符合条件，项目经理将主动与您联系。
   </div>

   
   
   


</div>
<!--foot-->
<%@ include file="../common/foot_qr.jsp" %>

</body>

<!--所属行业-->
<script type="text/javascript">
var industryArr=['1年以下','1-3年','3-5年','5-10年','10年以上'];
var mobileSelect1 = new MobileSelect({
    trigger: '#industry', 
    wheels: [{data: industryArr}],
});
</script>
<!--所属行业-->


<!--所在区域-->
<script type="text/javascript">
var areaArr=['福田区','罗湖区','南山区','盐田区','宝安区','龙岗区','龙华区','坪山区','光明区'];
var mobileSelect1 = new MobileSelect({
    trigger: '#area', 
    wheels: [{data: areaArr}],
});
</script>
<!--所在区域-->
</html>


