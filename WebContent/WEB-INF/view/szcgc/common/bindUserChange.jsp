<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;" name="viewport" />
<meta name="description" content="用户手机解绑" />
<meta name="keywords" content="用户手机解绑" />
<title>用户手机解绑</title>
<%@ include file="base.jsp" %>
<!--发送验证码JS-->
<script>
// 倒计时
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
$(function(){
	//发送验证码
	$('.yanzhengma').on('click',function(){
	  	//手机号码校验
		var mobile = $('.mobile').val();
	    if(!mobile){
	        $('.mobile').focus();
	        document.querySelector('.mobile').placeholder = '请填写手机号码';
	        return
	    }
		//用户id
		var userid="${userid}";
		var layerIndex = loading("正在发送请求...");// 显示加载中
		jQuery.ajax({
			type: 'POST',
			contentType:"application/x-www-form-urlencoded; charset=utf-8",
			data: {phone:mobile,userid:userid},
			url: "message/sendSMS",
			dataType: "json",
			success: function (obj) {//提交成功
				endLoading(layerIndex);
				if(obj == null || typeof(obj) == 'undefined'){
					msg("操作失败");
					return;
				}
				if(obj[0].code=="0"){ //执行成功
					msg("验证码发送成功,2分钟内有效");
					//短信发送的倒计时（剩余时间）
					roof();
				}else{
					if(obj[0].msg!=""){
						msg(obj[0].msg);
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
	});
	// 提交绑定
	$('#res-btn').on('click',function(){
	    var mobile = $('.mobile').val();
	    var yanzheng = $('.yanzheng').val();
	    if(!mobile){
	        $('.mobile').focus();
	        document.querySelector('.mobile').placeholder = '请填写手机号码';
	        return
	    }
	    if(!yanzheng){
	        $('.yanzheng').focus();
	        document.querySelector('.yanzheng').placeholder = '请填写验证码';
	        return
	    }
	    $(this).prop('disabled',true);
	  	//用户id
		var userid="${userid}";
		var layerIndex = loading("正在校验...");// 显示加载中
		jQuery.ajax({
			type: 'POST',
			contentType:"application/x-www-form-urlencoded; charset=utf-8",
			data: {code:yanzheng,phone:mobile,userid:userid,checkType:"unbindUser"},
			url: "message/checkSmsCode",
			dataType: "json",
			success: function (obj) {//提交成功
				endLoading(layerIndex);
				if(obj == null || typeof(obj) == 'undefined'){
					msg("操作失败");
					return;
				}
				if(obj[0].code=="0"){ //执行成功
					layer.open({
				    	content: "解绑成功"
				    	,btn: ["重新绑定", "暂不绑定"]
				    	,yes: function(index){
				    		loading("正在跳转...");
				    		window.location.href = "user/bind?userid=${userid}";
				      		layer.close(index);
				    	},
						no : function(index) {
							loading("正在跳转...");
							window.location.href = "index?userid=${userid}";
							layer.close(index);
						}
				  	});
				}else{
					if(obj[0].msg!=""){
						msg(obj[0].msg);
					}else{
						msg("验证码错误");
					}
				}
			},
			error: function (e) {//提交失败
				endLoading(layerIndex);
				msg("操作异常！");
			}
		});
	})
})
</script>
<!--发送验证码JS-->
</head>
<body style=" background:#FFFFFF">
<div class="register">
    <div class="binding"><img src="${pageContext.servletContext.contextPath}/resources/images/ico_verification.png" /><p>用户身份解绑</p></div>
	<div class="binding-item">   
		<input type="tel" name="phone" id="phone" disabled="disabled" placeholder="手机号" class="input-item mobile" value="${sessionScope.user.cellphone}">
		<i class="res-icon am-icon-phone"></i>
	</div>
	<div class="binding-item">  
		<input type="number" name="identCode" id="loginCode" placeholder="验证码" class="input-item yanzheng">
		<i class="res-icon am-icon-mobile"></i>
		<button type="button" name="getYzm" id="getYzm" class="yanzhengma">发送验证码</button>
	</div>
	<div class="separate_50"></div>
   	<div class="button_submit" id="res-btn" ><a href="###"><img src="${pageContext.servletContext.contextPath}/resources/images/button_submit .png" border="0" /></a></div>
</div>
</body>
</html>
