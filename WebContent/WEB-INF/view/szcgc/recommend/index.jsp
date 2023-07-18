<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;" name="viewport" />
<title>深圳担保服务平台</title>




<%@ include file="../common/base_recommend.jsp" %>

<!-- Slider组件 -->
		<!-- CSS -->
<link rel = "stylesheet"  type="text/css" href = "${pageContext.servletContext.contextPath}/resources/css/slider/default.css" />
<link rel = "stylesheet"  type="text/css" media = "screen" href = "${pageContext.servletContext.contextPath}/resources/css/slider/common.css" />
		<!-- JS -->
<script src = "${pageContext.servletContext.contextPath}/resources/js/slider/jquery-1.4.min.js"></script>
<script src = "${pageContext.servletContext.contextPath}/resources/js/slider/jquery.easing-1.3.js"></script>
		
		<%-- iosSlider plugin --%>
<script src = "${pageContext.servletContext.contextPath}/resources/js/slider/jquery.iosslider.min.js"></script>


<!--phone750-->
<%@ include file="../common/jspage/jspage_phone750.jsp" %>

<!--Ђ-ѡձ -->
<!--
<link href="/mobile/plugin/westvalley_wev8/szcgc/common/css/mobileSelect.css" rel="stylesheet" type="text/css">
<script src="/mobile/plugin/westvalley_wev8/szcgc/common/js/mobileSelect.js" type="text/javascript"></script>
 -->
<%@ include file="../common/jspage/jspage_mobileSelect.jsp" %>
<!--
<link  href="/mobile/plugin/westvalley_wev8/szcgc/common/css/Popup_donate.css" rel="stylesheet">
<script src="/mobile/plugin/westvalley_wev8/szcgc/common/js/Popup.js"></script>
-->

</head>
<body>
<div class="main_container">
<!-- banner -->

<div class = 'container'>
			
			<div class = 'iosSlider'>
			
				<div class = 'slider'>

					<div class = 'item'>
					<a href="https://mp.weixin.qq.com/s?__biz=MjM5MjMyNjcxNA==&mid=2651060095&idx=1&sn=afc827ba9b166d1811c4b57215b18f99&chksm=bd50a22a8a272b3cdcb756fc7181fdc9cb5a397ebc6a58e9346cec505ed4bfcf8a4aba4f73f0&token=754033993&lang=zh_CN#rd">
					<img src="${pageContext.servletContext.contextPath}/resources/images/recommend/banner_1.png" />
					</a>
						
					</div>
					<div class = 'item'>
						<img src="${pageContext.servletContext.contextPath}/resources/images/recommend/banner_2.png" />
					</div>
					

				</div>
				
				<div class = 'prevContainer'>
					<div class = 'prev' style="">
						<img src="${pageContext.servletContext.contextPath}/resources/images/recommend/arror_left.png" />
						</div>
				</div>
				
				<div class = 'nextContainer'>
					<div class = 'next'>
						<img src="${pageContext.servletContext.contextPath}/resources/images/recommend/arror_right.png" />
						</div>
				</div>
				
				<div class = 'selectorsBlock'>
				
					<div class = 'selectors'>
						<div class = 'item first selected'></div>
						<div class = 'item'></div>
					</div>
					
				</div>

			</div>
						
		</div>




<!--4宫格-->
	<div class="personal_column">
		<li class="Underline">
			<a href=""> 
				<img src="${pageContext.servletContext.contextPath}/resources/images/recommend/ico_column01.png" />
			</a>
		</li>
		<div class="personal_column_line">
			<img src="${pageContext.servletContext.contextPath}/resources/images/recommend/ico_line.png" />
		</div>
		
		
		<li class="Underline">
			<a href="">
				<img src="${pageContext.servletContext.contextPath}/resources/images/recommend/ico_column02.png" />
			</a>
		</li>
		<li>
			<a href="">
				<img src="${pageContext.servletContext.contextPath}/resources/images/recommend/ico_column03.png" />
			</a>
		</li>
		<div class="personal_column_line">
			<img src="${pageContext.servletContext.contextPath}/resources/images/recommend/ico_line.png" />
		</div>
		<li>
			<a href="">
				<img src="${pageContext.servletContext.contextPath}/resources/images/recommend/ico_column04.png" />
			</a>
		</li>
	</div>

<!-- 提交按钮 -->
<div class="button_submit" onclick=""><a href="/business/inquiry"><img src="${pageContext.servletContext.contextPath}/resources/images/recommend/button_submit1.png" border="0" /></a></div>

<!-- ؊׊ -->
<div class="award">
	<p>资本市场信用评级AAA最高级</p>
	<p>国家级中小企业公共服务示范平台</p>
	<p>深圳市中小企业公共服务示范平台</p>
	</div>
	
</div>
<!-- ҳβ -->
<%@ include file="../common/foot.jsp" %>

</body>

		<script type="text/javascript">
			$(document).ready(function() {
				
				$('.iosSlider').iosSlider({
					snapToChildren: true,
					desktopClickDrag: true,
					keyboardControls: true,
					navNextSelector: $('.next'),
					navPrevSelector: $('.prev'),
					navSlideSelector: $('.selectors .item'),
					autoSlide: true,
					onSlideChange: slideChange
				});
				
			}); 
			
			function slideChange(args) {
				
				$('.selectors .item').removeClass('selected');
				$('.selectors .item:eq(' + (args.currentSlideNumber - 1) + ')').addClass('selected');
				
			}
		</script>


</html>


