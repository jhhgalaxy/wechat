<%@ page import="com.szcgc.wechat.util.CommonUtil" %>
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
	 <a href="#" onclick="javascript: window.history.go(-1);return false;"><!--此处需改为带参数或JS返回 -->
	 	<img src="${pageContext.servletContext.contextPath}/resources/images/recommend/back.png"  />
	 	</a>
	</div>


<!--标题-->
<div>
	<img src="${pageContext.servletContext.contextPath}/resources/images/recommend/apply.png"  />
	</div>


    <!--表单-->
<div>
	<form id="addBusinessForm">
<div class="business">
   <input type="hidden" id="industry" name="industry" value="<%=CommonUtil.trim(request.getParameter("suoshy"))%>">
	 <input type="hidden" id="empolyee_num" name="empolyee_num" value="<%=CommonUtil.trim(request.getParameter("yuangrs"))%>">
 	<input type="hidden" id="income" name="income" value="<%=CommonUtil.trim(request.getParameter("shangnsr"))%>">
 	<input type="hidden" id="knowledge" name="knowledge" value="<%=CommonUtil.trim(request.getParameter("zhiscq"))%>">
 	<input type="hidden" id="invest" name="invest" value="<%=CommonUtil.trim(request.getParameter("jigtz"))%>">
 	<input type="hidden" id="loanrecord" name="loanrecord" value="<%=CommonUtil.trim(request.getParameter("jiekjl"))%>">
 	<input type="hidden" id="area" name="area" value="<%=CommonUtil.trim(request.getParameter("suozqy"))%>">


    <li class="b-line">
      <h3>企业全称</h3>
      <input type="text" id="corpname" name="corpname" placeholder="请输入企业全称" class="business_input"/>
   </li>
   <li class="b-line">
      <h3>联系人</h3>
      <input type="text" id="contactor" name="contactor" placeholder="请输入联系人" class="business_input"/>
   </li>
      <li class="b-line">
      <h3>手机号码</h3>
      <input type="tel" id="cellphone" name="cellphone" placeholder="请输入手机号码" class="business_input mobile"/>
   </li>
      <li class="b-line">
      <h3>验证码</h3>
      <input type="number" id="yanzm" name="yanzm" placeholder="请输入验证码" class="business_input yanzheng"/>
      <button type="button" class="yanzhengma" onclick="sendSmsCode()">发送验证码</button>
   </li>


   <div class="reminder">
	*您的信息仅用于企业资质审核,我们将严格保密。
	<br>
	如企业符合条件，项目经理将主动与您联系。
   </div>
   <div class="button_submit" onclick="submit4()"><a href="#"><img src="${pageContext.servletContext.contextPath}/resources/images/recommend/button_submit4.png" border="0" /></a></div>
</div>

</form>
	</div>





</div>
<!--foot-->
<%@ include file="../common/foot_qr.jsp" %>

</body>


<script type="text/javascript">
var times = 120;
function roof(){
	$(".yanzhengma").prop('disabled',true);// 验证码按钮不能再点击
	if(times == 0){
        $('.yanzhengma').text('发送验证码('+times+'s)');
        $('.yanzhengma').prop('disabled',false);
        $('.yanzhengma').text('发送验证码');
        times = 120;
        return
    }
    $('.yanzhengma').text('发送验证码('+times+'s)');
    times--;
    setTimeout(roof,1000);
}
//提交申报
function submit4() {
	if(checkMust()){// 检查数据填写情况
		return;
	}else{// 数据校验通过
		checkSmsCode();
	}
}

function back(){
	window.history.go(-1);
}
//校验表单数据
function checkMust(){
	if($("#qiyqc").val()==""){
		msg("请填写客户名称！");return true;
	}
	if($("#lianxr").val()==""){
		msg("请填写联系人！");return true;
	}
	if($("#shoujhm").val()==""){
		msg("请填写手机号码！");return true;
	}
	if($("#yanzm").val()==""){
		msg("请填写验证码！");return true;
	}
	
	
	if($("#suoshy").val()==""){
		msg("您未参与过额度查询，请先查询额度与补贴,页面将在3秒后跳转");
		setTimeout('jumpurl()',3000);
		return true;
	}
	
}
//TODO: 修改URL
function jumpurl(){
	location='/business/inquiry';
}


// 发送验证码
function sendSmsCode(){
	var checkPhone = $("#cellphone").val();
    if(!checkPhone){
        $('.mobile').focus();
        document.querySelector('.mobile').placeholder = '手机号获取错误';
        return
    }
	//用户id
    var userid="${sessionScope.user.id}";
	var layerIndex = loading("正在发送请求...");// 显示加载中
	jQuery.ajax({
		type: 'POST',
		contentType:"application/x-www-form-urlencoded; charset=utf-8",
		data: {phone:checkPhone,userid:userid},
		url: "../message/sendSMS",
		dataType: "json",
		success: function (obj) {//提交成功
			endLoading(layerIndex);
			if(obj == null || typeof(obj) == 'undefined'){
				msg("操作失败");
				return;
			}
			if(obj.code=="0"){ //执行成功
				msg("验证码发送成功,2分钟内有效");
				//短信发送的倒计时（剩余时间）
				roof();
			}else{
				if(obj.msg!=""){
					msg(obj.msg);
				}else{
					msg("验证码发送失败");
				}
			}
		},
		error: function (e) {//提交失败
			endLoading(layerIndex);
			msg("操作异常！");
		}
	});
}
// 校验验证码
function checkSmsCode(){
	var checkPhone = $("#cellphone").val();
    var checkCode = $("#yanzm").val();
    if(!checkCode){
        $('.yanzheng').focus();
        document.querySelector('.yanzheng').placeholder = '请填写验证码';
        return
    }
  	//用户id
    var userid="${sessionScope.user.id}";
	var layerIndex = loading("正在校验...");// 显示加载中
	jQuery.ajax({
		type: 'POST',
		contentType:"application/x-www-form-urlencoded; charset=utf-8",
		data: {code:checkCode,phone:checkPhone,checkType:"checkCode",userid:userid},
		url: "../message/checkSmsCode",
		dataType: "json",
		success: function (obj) {//提交成功
			endLoading(layerIndex);
			if(obj == null || typeof(obj) == 'undefined'){
				msg("操作失败");
				return;
			}
			if(obj.code=="0"){ //执行成功
				
				submitData();
			}else{
				if(obj.msg!=""){
					msg(obj.msg);
				}else{
					msg("验证码错误！");
				}
			}
		},
		error: function (e) {//提交失败
			endLoading(layerIndex);
			msg("操作异常！");
		}
	});
}
// 提交
//TODO: 修改URL
function submitData(){
	var layerIndex = loading("校验成功，正在提交...");
	jQuery.ajax({
		type: 'POST',
		contentType:"application/x-www-form-urlencoded; charset=utf-8",
		data: $("#addBusinessForm").serialize(),
		url: "/business/apply",
		dataType: "json",
		success: function (obj) {//提交成功
			endLoading(layerIndex);
			if(obj == null || typeof(obj) == 'undefined'){
				msg("操作失败");
				return;
			}
			if(obj.code=="0"){ //执行成功
				window.location.href = "/business/success";
			}else{
				if(obj.msg!=""){
					msg(obj.msg);
				}else{
					msg("提交失败");
				}
			}
		},
		error: function (e) {//提交失败
			endLoading(layerIndex);
			msg("操作异常！");
		}
	});
}

</script>



</html>


