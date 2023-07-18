<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;" name="viewport" />
<title>深圳担保服务平台</title>
<%@ include file="/mobile/plugin/westvalley_wev8/szcgc/common/base.jsp" %>

<!--phone750自适应 -->
<%@ include file="/mobile/plugin/westvalley_wev8/szcgc/common/jspage/jspage_phone750.jsp" %>

<!--下拉选择 -->
<!--
<link href="/mobile/plugin/westvalley_wev8/szcgc/common/css/mobileSelect.css" rel="stylesheet" type="text/css">
<script src="/mobile/plugin/westvalley_wev8/szcgc/common/js/mobileSelect.js" type="text/javascript"></script>
 -->
<%@ include file="/mobile/plugin/westvalley_wev8/szcgc/common/jspage/jspage_mobileSelect.jsp" %>
<!--弹出层-->
<!--
<link  href="/mobile/plugin/westvalley_wev8/szcgc/common/css/Popup_donate.css" rel="stylesheet">
<script src="/mobile/plugin/westvalley_wev8/szcgc/common/js/Popup.js"></script>
-->
<%@ include file="/mobile/plugin/westvalley_wev8/szcgc/common/jspage/jspage_popup.jsp" %>
<!--地区选择-->
<!--
<link href="/mobile/plugin/westvalley_wev8/szcgc/common/vendors/larea/LArea.css" rel="stylesheet">
<script src="/mobile/plugin/westvalley_wev8/szcgc/common/vendors/larea/LAreaData1.js"></script>
<script src="/mobile/plugin/westvalley_wev8/szcgc/common/vendors/larea/LAreaData2.js"></script>
<script src="/mobile/plugin/westvalley_wev8/szcgc/common/vendors/larea/LArea.js"></script>
-->
<%@ include file="/mobile/plugin/westvalley_wev8/szcgc/common/jspage/jspage_larea.jsp" %>
</head>
<body>
<div class="business_bg"><img src="/mobile/plugin/westvalley_wev8/szcgc/common/images/business_bg.png" /></div>
<form id="addBusinessForm">
<input type="hidden" name="shenqr" id="shenqr" value="<%=bindUser!=null?bindUser.getId():""%>">
<div class="business">
   <li class="b-line">
      <h3>客户名称</h3>
      <input type="text" id="kehmc" name="kehmc" placeholder="请输入客户名称" class="business_input"/>
   </li>
   <li class="b-line">
      <h3>联系人</h3>
      <input type="text" id="lianxr" name="lianxr" placeholder="请输入联系人" class="business_input"/>
   </li>
    <li class="b-line">
      <h3>手机号码</h3>
      <input type="tel" id="shoujhm" name="shoujhm" placeholder="请输入手机号码" class="business_input"/>
   </li>
   <li class="b-line">
      <h3>成立时间</h3>
	  <div id="establish_date" class="form-control" ><span>请选择成立时间</span></div>
      <div class="aui-cells-arrow"><i class="icon-arrow"></i></div>
      <input type="hidden" id="chenglsj" name="chenglsj">
   </li>
   <li class="b-line">
      <h3>员工人数</h3>
      <input type="number" id="yuangrs" name="yuangrs" placeholder="请输入员工人数" class="business_input"/>
   </li>
    <li class="b-line">
      <h3>所在地区</h3>
	  <input class="business_input" style="margin-right: 25px; " id="diqLabel" name="diqLabel" type="text" readonly="" placeholder="请选择所在地区"/>
      <input type="hidden" id="diqValue" name="diqValue">
      <input type="hidden" id="diq" name="diq">
      <div class="aui-cells-arrow"><i class="icon-arrow"></i></div>
   </li>
   <li class="b-line">
      <h3>总资产(万元)</h3>
      <input type="number" id="zongzc" name="zongzc" placeholder="请输入总资产" class="business_input"/>
   </li>
   <li class="b-line">
      <h3>净资产(万元)</h3>
      <input type="number" id="jingzc" name="jingzc" placeholder="请输入净资产" class="business_input"/>
   </li>
    <li class="b-line">
      <h3>年度收入(万元)</h3>
      <input type="number" id="niandsr" name="niandsr" placeholder="请输入年度收入" class="business_input"/>
   </li>
   <li class="b-line">
      <h3>年度利润(万元)</h3>
      <input type="number" id="niandlr" name="niandlr" placeholder="请输入年度利润" class="business_input"/>
   </li>
   <li class="b-line">
      <h3>资产负责率(%)</h3>
      <input type="number" id="zicfzl" name="zicfzl" placeholder="请输入资产负债率" class="business_input"/>
   </li>
   <li class="b-line">
      <h3>物业情况</h3>
      <div id="Property_situation" class="form-control" ><span>请选择物业情况</span></div>
      <div class="aui-cells-arrow"><i class="icon-arrow"></i></div>
      <input type="hidden" id="wuyqk" name="wuyqk">
   </li>
   <div class="reminder">
	温馨提示：您填写的信息仅用于贷款资质核查,不会用于任何其它用途。
   </div>
   <div class="button_submit" onclick="addBusiness()"><a href="###"><img src="/mobile/plugin/westvalley_wev8/szcgc/common/images/button_submit .png" border="0" /></a></div>
</div>

</form>

<!--手机验证弹出层-->
<div class="cd-Gift_collection" role="alert">
	<div class="cd-collection-container">
	    <div class="Subtask-cont"> 
		     
		<div class="register">
		    <div class="verification"><p>用户身份认证</p></div>
			<div class="res-item">   
				<input type="tel" id="checkPhone" name="checkPhone" placeholder="手机号" class="input-item mobile" disabled="disabled"  value="">
				<i class="res-icon am-icon-phone"></i>
			</div>
			<div class="res-item">  
				<input type="number" id="checkCode" name="checkCode" placeholder="验证码" class="input-item yanzheng">
				<i class="res-icon am-icon-mobile"></i>
				<button type="button" class="yanzhengma" onclick="sendSmsCode()">发送验证码</button>
			</div>
            <div class="separate_50"></div>
		   	<div class="button_res-item" id="res-btn" onclick="checkSmsCode()"><a href="###"><img src="/mobile/plugin/westvalley_wev8/szcgc/common/images/button_submit .png" border="0" /></a></div>
	   		<div class="res-item_Close"><img src="/mobile/plugin/westvalley_wev8/szcgc/common/images/ico_Close.png" class="cd-Gift_collection-quxiao"/></div>
		</div>
		   
		</div> 
	</div> 
</div>
<!--手机验证弹出层-->

<!--成立时间-->
<script type="text/javascript">
var weekdayArr=['1年以下','1-3年','3-5年','5-10年','10年以上'];
var mobileSelect1 = new MobileSelect({
    trigger: '#establish_date', 
    wheels: [{data: weekdayArr}],
});
</script>
<!--成立时间-->

<!--物业情况-->
<script type="text/javascript">
var weekdayArr=['有','无'];
var mobileSelect1 = new MobileSelect({
    trigger: '#Property_situation', 
    wheels: [{data: weekdayArr}],
});
</script>
<!--物业情况-->

<script>
var area1 = new LArea();
area1.init({
    'trigger': '#diqLabel', //触发选择控件的文本框，同时选择完毕后name属性输出到该位置
    'valueTo': '#diqValue', //选择完毕后id属性输出到该位置
    'keys': {
        id: 'id',
        name: 'name'
    }, //绑定数据源相关字段 id对应valueTo的value属性输出 name对应trigger的value属性输出
    'type': 1, //数据源类型
    'data': LAreaData //数据源
});
</script>
<!--地区选择-->

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
function addBusiness() {
	if(checkMust()){// 检查数据填写情况
		return;
	}else{// 数据校验通过
		// (1)赋值给手机验证弹出框手机号
		$("#checkPhone").val($("#shoujhm").val());
		$('.cd-Gift_collection').addClass('is-visible');// 显示弹出框
	}
}
//校验表单数据
function checkMust(){
	if($("#kehmc").val()==""){
		msg("请填写客户名称！");return true;
	}
	if($("#lianxr").val()==""){
		msg("请填写联系人！");return true;
	}
	if($("#shoujhm").val()==""){
		msg("请填写手机号码！");return true;
	}
	if($("#establish_date").text()==""||$("#establish_date").text()=="请选择成立时间"){
		msg("请填写成立时间！");return true;
	}
	if($("#yuangrs").val()==""){
		msg("请填写员工人数！");return true;
	}
	if($("#diqLabel").val()==""){
		msg("请填写所在地区！");return true;
	}
	if($("#zongzc").val()==""){
		msg("请填写总资产！");return true;
	}
	if($("#jingzc").val()==""){
		msg("请填写净资产！");return true;
	}
	if($("#niandsr").val()==""){
		msg("请填写年度收入！");return true;
	}
	if($("#niandlr").val()==""){
		msg("请填写年度利润！");return true;
	}
	if($("#zicfzl").val()==""){
		msg("请填写资产负责率！");return true;
	}
	if($("#Property_situation").text()==""||$("#Property_situation").text()=="请选择物业情况"){
		msg("请填写物业情况！");return true;
	}
}
// 发送验证码
function sendSmsCode(){
	var checkPhone = $("#checkPhone").val();
    if(!checkPhone){
        $('.mobile').focus();
        document.querySelector('.mobile').placeholder = '手机号获取错误';
        return
    }
	//用户id
	var userid="<%=userid%>";
	var layerIndex = loading("正在发送请求...");// 显示加载中
	jQuery.ajax({
		type: 'POST',
		contentType:"application/x-www-form-urlencoded; charset=utf-8",
		data: {phone:checkPhone,userid:userid},
		url: "/mobile/plugin/westvalley_wev8/szcgc/common/oper.jsp?cmd=sendSms",
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
}
// 校验验证码
function checkSmsCode(){
	var checkPhone = $("#checkPhone").val();
    var checkCode = $("#checkCode").val();
    if(!checkCode){
        $('.yanzheng').focus();
        document.querySelector('.yanzheng').placeholder = '请填写验证码';
        return
    }
  	//用户id
	var userid="<%=userid%>";
	var layerIndex = loading("正在校验...");// 显示加载中
	jQuery.ajax({
		type: 'POST',
		contentType:"application/x-www-form-urlencoded; charset=utf-8",
		data: {code:checkCode,phone:checkPhone,userid:userid,checkType:"checkCode"},
		url: "/mobile/plugin/westvalley_wev8/szcgc/common/oper.jsp?cmd=checkSmsCode",
		dataType: "json",
		success: function (obj) {//提交成功
			endLoading(layerIndex);
			if(obj == null || typeof(obj) == 'undefined'){
				msg("操作失败");
				return;
			}
			if(obj[0].code=="0"){ //执行成功
				submitData();
			}else{
				if(obj[0].msg!=""){
					msg(obj[0].msg);
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
function submitData(){
	$("#chenglsj").val(getChenglsj());// 成立时间
	$("#wuyqk").val(getWuyqk());// 物业情况
	$("#diq").val(getDiq());// 地区
	var layerIndex = loading("校验成功，正在提交...");
	jQuery.ajax({
		type: 'POST',
		contentType:"application/x-www-form-urlencoded; charset=utf-8",
		data: $("#addBusinessForm").serialize(),
		url: "/mobile/plugin/westvalley_wev8/szcgc/common/oper.jsp?cmd=addBusiness",
		dataType: "json",
		success: function (obj) {//提交成功
			endLoading(layerIndex);
			if(obj == null || typeof(obj) == 'undefined'){
				msg("操作失败");
				return;
			}
			if(obj[0].code=="0"){ //执行成功
				layer.open({
					content: "您的申请已成功提交，稍后将会有项目经理与您联系。",
				  	btn: "我知道了",
				  	shadeClose: false,
				  	yes: function(){
				  		loading("正在跳转...");
				  		window.location.href = "/mobile/plugin/westvalley_wev8/szcgc/center/index.jsp?userid=<%=userid%>";
				  	}
				});
			}else{
				if(obj[0].msg!=""){
					msg(obj[0].msg);
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
// 获取成立时间
function getChenglsj(){
	var label = $("#establish_date").text();
	if(label=="1年以下"){
		return 0;
	}else if(label=="1-3年"){
		return 1;
	}else if(label=="3-5年"){
		return 2;
	}else if(label=="5-10年"){
		return 3;
	}else if(label=="10年以上"){
		return 4;
	}
}
// 获取物业情况
function getWuyqk(){
	var label = $("#Property_situation").text();
	if(label=="有"){
		return 1;
	}else{
		return 0;
	}
}
// 获取地区
function getDiq(){
	var diqLabel = $("#diqLabel").val();
	var reg = new RegExp(",","g");//g,表示全部替换。
	var diq = diqLabel.replace(reg," ");
	return diq;
}
</script>
</body>
</html>
