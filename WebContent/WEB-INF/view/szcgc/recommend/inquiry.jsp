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
	 <a href="/business/index">
	 	<img src="${pageContext.servletContext.contextPath}/resources/images/recommend/back.png"  />
	 	</a>
	</div>


<!--标题-->
<div>
	<img src="${pageContext.servletContext.contextPath}/resources/images/recommend/inquiry.png"  />
	</div>

<!--表单-->
<div>
	<form id="addBusinessForm" action="/business/result" method="post" >
<div class="business">
   <li class="b-line">
      <h3>所属行业</h3>
	  <div id="industry" class="form-control" ><span>农、林、牧、渔业</span></div>
      <input type="hidden" id="suoshy" name="suoshy">
   </li>
    <li class="b-line">
      <h3>员工人数</h3>
      <input type="number" id="yuangrs" name="yuangrs" placeholder="请输入员工人数" class="business_input"/>
   </li>
   <li class="b-line">
      <h3>上一年报税营业收入（万元）</h3>
      <input type="number" id="shangnsr" name="shangnsr" placeholder="请输入营业收入" class="business_input"/>
   </li>
    <li class="b-line">
      <h3>是否有知识产权</h3>
      <p class="radio">
	  <label>否</label><input type="radio" name="zhiscq" value="0" class="form_radio" checked="checked"/>
	  <label>是</label><input type="radio" name="zhiscq" value="1" class="form_radio"/>
	  </p>
   </li>
       <li class="b-line">
      <h3>是否获得知名机构投资</h3>
      <p class="radio">
	  <label>否<input type="radio" name="jigtz" value="0" class="form_radio" checked="checked"/></label>
	<label>是<input type="radio" name="jigtz" value="1" class="form_radio"/></label>
	</p>
	  
   </li>
       <li class="b-line">
      <h3>是否有借款记录</h3>
      <p class="radio">
	  <label>否<input type="radio" name="jiekjl" value="0" class="form_radio" checked="checked" /></label>
	  <label>是<input type="radio" name="jiekjl" value="1" class="form_radio"/></label>
	</p>
   </li>
      <li class="b-line">
      <h3>所在区域</h3>
	  <div id="area" class="form-control" ><span>福田区</span></div>
      <input type="hidden" id="suozqy" name="suozqy">
   </li>


   <div class="button_submit" onclick="submit3()"><a href="#"><img src="${pageContext.servletContext.contextPath}/resources/images/recommend/button_submit2.png" border="0" /></a></div>
</div>

</form>
	</div>

</div>
<!--foot-->
<%@ include file="../common/foot.jsp" %>

</body>

<script type="text/javascript">
function submit3(){
		if(checkData()){
			return;
		}
		
		$("#suoshy").val(getIndustry());// 所属行业
		$("#suozqy").val(getArea());// 所属区域
		$("#addBusinessForm").submit();
			
}


function getIndustry(){
	var label = $("#industry").text();
	if(label=="农、林、牧、渔业"){
		return 0;
	}else if(label=="工业"){
		return 1;
	}else if(label=="建筑业"){
		return 2;
	}else if(label=="批发业"){
		return 3;
	}else if(label=="零售业"){
		return 4;
	}else if(label=="交通运输业"){
		return 5;
	}else if(label=="仓储业"){
		return 6;
	}else if(label=="邮政业"){
		return 7;
	}else if(label=="住宿业"){
		return 8;
	}else if(label=="餐饮业"){
		return 9;
	}else if(label=="信息传输业"){
		return 10;
	}else if(label=="软件和信息技术服务业"){
		return 11;
	}else if(label=="房地产开发经营"){
		return 12;
	}else if(label=="物业管理"){
		return 13;
	}else if(label=="租赁和商务服务业"){
		return 14;
	}else if(label=="其他"){
		return 15;
	}
}

function getArea(){
	var label = $("#area").text();
	if(label=="福田区"){
		return 0;
	}else if(label=="罗湖区"){
		return 1;
	}else if(label=="南山区"){
		return 2;
	}else if(label=="盐田区"){
		return 3;
	}else if(label=="宝安区"){
		return 4;
	}else if(label=="龙岗区"){
		return 5;
	}else if(label=="龙华区"){
		return 6;
	}else if(label=="坪山区"){
		return 7;
	}else if(label=="光明区"){
		return 8;
	}
}

function checkData(){
	if($("#industry").text()==""||$("#industry").text()=="请选择所属行业"){
		  msg("请选择所属行业！");return true;
			}
			
			if($("#yuangrs").val()==""){
			msg("请填写员工人数！");return true;
			}
			if($("#yingysr").val()==""){
			msg("请填写营业收入！");return true;
			}
			if($('input:radio[name="zhiscq"]:checked').val()==null){
			msg("请填写知识产权情况！");return true;
			}
			if($('input:radio[name="jigtz"]:checked').val()==null){
			msg("请填写机构投资情况！");return true;
			}
			if($('input:radio[name="jiekjl"]:checked').val()==null){
			msg("请填写借款情况！");return true;
			}
			if($("#area").text()==""||$("#area").text()=="请选择所在地区"){
		  msg("请选择所在地区！");return true;
			}
}
	
	
</script>


<!--所属行业-->
<script type="text/javascript">
var industryArr=['农、林、牧、渔业','工业','建筑业','批发业','零售业','交通运输业','仓储业','邮政业','住宿业','餐饮业','信息传输业','软件和信息技术服务业','房地产开发经营','物业管理','租赁和商务服务业','其他'];
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


