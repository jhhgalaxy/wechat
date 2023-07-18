<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="org.apache.commons.lang3.StringUtils"%>


<%@ include file="jspage/jspage_base.jsp" %>
<script type="text/javascript">
// 限定只能在微信端打开
// var ua = navigator.userAgent.toLowerCase();
// if(!(/micromessenger/.test(ua))){
// 	window.location.href = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=888"
// }
// 信息提示框
function msg(msg, fn){
	layer.open({
	  	content: msg
	  	, style: "width:70%;background-color:#fff; border:none;border-radius:10px;"
	  	, time: 3
	  	, end: function(){
	  		if(typeof(fn) != "undefined"){
	  			layer.open({
			    	type: 2
			    	,content: '加载中'
			    });
			    fn();
	  		}
	  	}
	});
}
// 显示加载中
function loading(msg) {
	if (typeof (msg) == "undefined") {
		msg = "加载中";
	}
	return layer.open({
		type : 2,
		content : msg,
		shadeClose : false
	});
}
// 关闭指定加载框
function endLoading(index) {
	layer.close(index);
}
// 关闭所有加载框
function endLoadingAll() {
	layer.closeAll();
}
// 动态设置可见区域高度最小值
function getWinHeight(){
	var winHeight;
	if (window.innerHeight){// 获取窗口高度
		winHeight = window.innerHeight;
	}else if ((document.body) && (document.body.clientHeight)){
		winHeight = document.body.clientHeight;
	}
	// 通过深入 Document 内部对 body 进行检测，获取窗口大小
	if (document.documentElement && document.documentElement.clientHeight){
		winHeight = document.documentElement.clientHeight;
	}
	return parseInt(winHeight);
}
</script>