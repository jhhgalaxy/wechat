<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;" name="viewport" />
<meta name="description" content="深圳担保服务平台" />
<meta name="keywords" content="深圳担保服务平台" />
<title>深圳担保服务平台</title>
<%@ include file="../common/base.jsp" %>

<script type="text/javascript">
//提交投诉
function addComplaint() {
	if(checkMust()){
		return;
	}
	var layerIndex = loading("正在提交...");
	jQuery.ajax({
		type: 'POST',
		contentType:"application/x-www-form-urlencoded; charset=utf-8",
		data: $("#addComplaintForm").serialize(),
		url: "addSuggestion",
		dataType: "json",
		success: function (obj) {//提交成功
			endLoading(layerIndex);
			if(obj == null || typeof(obj) == 'undefined'){
				msg("操作失败");
				return;
			}
			if(obj.code=="0"){ //执行成功
				layer.open({
					content: "您的投诉建议已成功提交，感谢您对我们工作的支持！",
				  	btn: "关闭",
				  	shadeClose: false,
				  	yes: function(index){
				  		var loadingIndex = loading("正在跳转...");
				  		window.location.href = "listComplaint?userid=${sessionScope.user.id}";
				  		endLoading(loadingIndex);
				  		layer.close(index);
				  		$("#tousnr").val("");
				  	}
				});
			}else{
				var msgContent = objmsg;
				layer.open({
					content: msgContent,
				  	btn: "关闭",
				  	shadeClose: false,
				  	yes: function(index){
				  		layer.close(index);
				  	}
				});
			}
		},
		error: function (e) {//提交失败
			endLoading(layerIndex);
			msg("提交数据到服务器失败！");
		}
	});
}
//校验表单数据
function checkMust(){
	if($("#content").val()==""){
		msg("请填写投诉内容");
		return true;
	}
}
</script>
</head>
<body style="background:#ffa020">
<form action="" id="addComplaintForm">
<input type="hidden" name="complainant" value="${sessionScope.user.id}">
<input type="hidden" name="cellphone" value="${sessionScope.user.cellphone}">
<div class="proposal_bg">
	<img src="${pageContext.servletContext.contextPath}/resources/images/proposal_bg.jpg" />
	<div class="proposal_my">
		<a href="listComplaint?userid=${sessionScope.user.id}">我的投诉</a>
	</div>
</div>
<div class="proposal_text">
	<textarea id="content" maxlength="150" name="content" cols="" rows="" placeholder="请在此输入您要投诉的问题，以便我们尽快为您解决。"></textarea>
</div>
<div class="proposal_reminder">温馨提示：感谢您对我们工作的支持,我们将尽快处理您的意见与建议。</div>
<div class="button_submit">
	<a href="javascript:void(0)" onclick="addComplaint()">
		<img src="${pageContext.servletContext.contextPath}/resources/images/button_submit .png" border="0" />
	</a>
</div>
</form>
</body>
</html>