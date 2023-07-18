<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;" name="viewport" />
<meta name="description" content="深圳担保服务平台" />
<meta name="keywords" content="深圳担保服务平台" />
<title>深圳担保服务平台</title>
<%@ include file="../../common/base.jsp" %>
<!--上拉加载，下拉刷新-CSS、JS-->
<!--
<link href="/mobile/plugin/westvalley_wev8/szcgc/common/vendors/mescroll-master/mescroll.min.css" type="text/css" media="all" rel="stylesheet" >
<script src="/mobile/plugin/westvalley_wev8/szcgc/common/vendors/mescroll-master/mescroll.min.js"></script>	
-->
<%@ include file="../../common/jspage/jspage_mescroll.jsp" %>
<style>
.mescroll{
	position: fixed;
	top: 32px;
	bottom: 0;
	height: auto; /*如设置bottom:50px,则需height:auto才能生效*/
}
.whole_frame_inner {
    min-height: calc(100% - 95px);
}
.no_data_inner {
    padding-top:200px;
    text-align: center;
}
.no_data_inner img{ width:110px; margin-bottom:22px}
.no_data_inner p{ font-size:16px; color:#CCC; letter-spacing:4.5px; padding-left:7.5px}
</style>
<script type="text/javascript">
var mescroll;
var isEmpty = true;
//消息列表分页加载
$(function(){
	/*创建MeScroll对象*/
	mescroll = new MeScroll("mescroll", {
		 down: {
			clearEmptyId:"noticeList",//上面的id
			auto: false, //是否在初始化完毕之后自动执行下拉回调callback; 默认true
			callback: downCallback //下拉刷新的回调
		 },
		 up: {
			clearEmptyId:"noticeList",//上面的id
			auto:true,//初始化完毕,是否自动触发上拉加载的回调
			isBoth: true, //上拉加载时,如果滑动到列表顶部是否可以同时触发下拉刷新;默认false,两者不可同时触发; 这里为了演示改为true,不必等列表加载完毕才可下拉;
			isBounce: false, //此处禁止ios回弹,解析(务必认真阅读,特别是最后一点): http://www.mescroll.com/qa.html#q10
			callback: upCallback, //上拉加载的回调
			noMoreSize: 5,//如果列表已无数据,可设置列表的总数量要大于5才显示无更多数据;
		    toTop:{ //配置回到顶部按钮
		    	src : "${pageContext.servletContext.contextPath}/resources/vendors/mescroll-master/mescroll-totop.png", //默认滚动到1000px显示,可配置offset修改
		    	//offset : 1000
		    },
		    empty: {
				//列表第一页无任何数据时,显示的空提示布局; 需配置warpId才显示
				warpId:	"noticeList", //父布局的id (1.3.5版本支持传入dom元素)
				icon: "${pageContext.servletContext.contextPath}/resources/vendors/mescroll-master/mescroll-empty.png", //图标,默认null,支持网络图
				tip: "暂无相关数据~" //提示
			},
		}
	});
	/*下拉刷新的回调 */
	function downCallback(){
		location.reload();
	}
	/*上拉加载的回调 page = {num:1, size:10}; num:当前页 从1开始, size:每页数据条数 */
	function upCallback(page){
		getData(page);
	}
});
//获取消息列表数据
function getData(page){
	var data = "page=" + page.num + "&userid="+${sessionScope.user.id};
	var url = "listMsg";
	jQuery.ajax({
		type: 'POST',
		contentType:"application/x-www-form-urlencoded; charset=utf-8",
		data: data,
		url: url,
		dataType: "json",
		success: function (result) {//获取成功
			parseData(result);
		},
		error: function (e) {//获取失败
			msg("请求服务器数据失败！");
		}
	});
}
//渲染数据
function parseData(result){
	var isEnd = true;
	var obj = result.data;
	var totalCount = result.count;
	mescroll.endBySize(obj.length, totalCount);
	if(obj != null&&obj.length>0){
		isEmpty = false;
		isEnd = false;
		for(var i=0; i<obj.length; i++){
			if(obj[i]==null){
				continue;
			}
			var url = "viewMsg?userid="+'${sessionScope.user.id}'+"&id="+obj[i].id;
			if(obj[i].msgtype==3){// 如果是投诉反馈
				url = "/mobile/plugin/westvalley_wev8/szcgc/complaint/complaint.jsp?fromType=msg";
			}
			var result = ""
				  +"<li>"
			      +"	<h1><a href=\""+url+"\">"+obj[i].msgtitle+"</a></h1>"
				  +"	<p>"+obj[i].typename+"<span>"+obj[i].createtime+"</span></p>"
			   	  +"</li>";
			$("#noticeList").append(result);//添加数据
		}
	}
	if(isEmpty){
		$(".whole_frame_inner").show();
		$(".foot_bg").show();
		$("#mescroll").hide();
	}else{
		$(".whole_frame_inner").hide();
		$(".foot_bg").hide();
		$("#mescroll").show();
	}
}
</script>
</head>
<body>
<div class="headbar"><span><img src="${pageContext.servletContext.contextPath}/resources/images/ico_top03.png" /></span>消息通知</div>
<div id="mescroll" class="mescroll">
	<div id="noticeList" class="notice_list" style="padding-top: 0px;"></div>
</div>
<!--项目列表-->
<div class="whole_frame_inner" style="display: none;">
	<div class="no_data_inner"><img src="${pageContext.servletContext.contextPath}/resources/images/no_data.png" /><p>暂无消息</p></div>
</div>
<div class="foot_bg" style="display: none;"><img src="${pageContext.servletContext.contextPath}/resources/images/foot_bg.png" /></div>
</body>
</html>