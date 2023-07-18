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
	 <a href="/business/inquiry">
	 	<img src="${pageContext.servletContext.contextPath}/resources/images/recommend/back.png"  />
	 	</a>
	</div>


<!--标题-->
<div>
	<img src="${pageContext.servletContext.contextPath}/resources/images/recommend/recommend.png"  />
	</div>

<!--表格1 -->
<div class="product_table">
	<table class="recommend">
		<tr>
			<th>
				产品名称
				</th>
				<th>
				最高额度
				</th>
				<th>
				担保费
				</th>
			</tr>
			<tr id="knowledge" class="invisible">
				<td>知识产权质押</td>
				<td>2000万</td>
				<td>最低1%/年</td>
				</tr>
			<tr id="pub" class="invisible">
				<td>普惠贷</td>
				<td>500万</td>
				<td>最低1%/年</td>
				</tr>
				<tr id="invest" class="invisible">
				<td>投贷易</td>
				<td>1500万</td>
				<td>最低1%/年</td>
				</tr>
				<tr id="first" class="invisible">
				<td>首贷易</td>
				<td>1000万</td>
				<td>最低1%/年</td>
				</tr>
				<tr id="default" class="invisible">
				<td>额度授信</td>
				<td>100-2000万</td>
				<td>最低1%/年</td>
				</tr>
		
		</table>
	</div>



<div>
	<img src="${pageContext.servletContext.contextPath}/resources/images/recommend/fund.png"  />
	</div>

<!--表格2 -->
<div>
		<table class="recommend fund">
		<tr id="header">
			<th>
				所在区域
				</th>
			<th>
				利息补贴
				</th>
				<th>
				知识产权质押
				</th>
				<th>
				保费补贴
				</th>
			</tr>
			
			
			<tr id="fund1" class="invisible">
				<td>深圳市福田区</td>
				<td><img class="tick" src="${pageContext.servletContext.contextPath}/resources/images/recommend/tick.png"  /></td>
				<td><img class="tick" src="${pageContext.servletContext.contextPath}/resources/images/recommend/tick.png"  /></td>
				<td><img class="tick" src="${pageContext.servletContext.contextPath}/resources/images/recommend/tick.png"  /></td>
			</tr>
			<tr id="desc1" class="invisible"><td colspan="4">知识产权质押贷款（补贴利息、担保费、评审费的50%）；“科技成长贷” 、“科技孵化贷”（补贴利息的40%和担保费的100%）；最高120万。</td></tr>
			
			<tr id="fund2" class="invisible">
				<td>深圳市罗湖区</td>
				<td><img class="tick" src="${pageContext.servletContext.contextPath}/resources/images/recommend/tick.png"  /></td>
				<td></td>
				<td><img class="tick" src="${pageContext.servletContext.contextPath}/resources/images/recommend/tick.png"  /></td>
			</tr>
			<tr id="desc2" class="invisible"><td colspan="4">补贴利息的70%；最高300万元，同一企业连续贷款两年及以上的累计最高500万元。</td></tr>
			
			<tr id="fund3" class="invisible">
				<td>深圳市南山区</td>
				<td><img class="tick" src="${pageContext.servletContext.contextPath}/resources/images/recommend/tick.png"  /></td>
				<td><img class="tick" src="${pageContext.servletContext.contextPath}/resources/images/recommend/tick.png"  /></td>
				<td><img class="tick" src="${pageContext.servletContext.contextPath}/resources/images/recommend/tick.png"  /></td>
			</tr>
			<tr id="desc3" class="invisible"><td colspan="4">所有科技金融产品（知识产权质押贷款、成长贷、孵化贷）补贴利息的70%；最高100万。</td></tr>
			
			
			<tr id="fund4" class="invisible">
				<td>深圳市盐田区</td>
				<td><img class="tick" src="${pageContext.servletContext.contextPath}/resources/images/recommend/tick.png"  /></td>
				<td></td>
				<td><img class="tick" src="${pageContext.servletContext.contextPath}/resources/images/recommend/tick.png"  /></td>
			</tr>
			<tr id="desc4"class="invisible"><td colspan="4">补贴利息的50%和担保费的50%；最高100万元。</td></tr>
			
			<tr id="fund5" class="invisible">
				<td>深圳市宝安区</td>
				<td></td>
				<td></td>
				<td><img class="tick" src="${pageContext.servletContext.contextPath}/resources/images/recommend/tick.png"  /></td>
			</tr>
			<tr id="desc5" class="invisible"><td colspan="4">补贴利息基准上浮部分的50%，最高100万；担保费50%，最高100万。</td></tr>
			
			<tr id="fund6" class="invisible">
				<td>深圳市龙岗区</td>
				<td><img class="tick" src="${pageContext.servletContext.contextPath}/resources/images/recommend/tick.png"  /></td>
				<td><img class="tick" src="${pageContext.servletContext.contextPath}/resources/images/recommend/tick.png"  /></td>
				<td><img class="tick" src="${pageContext.servletContext.contextPath}/resources/images/recommend/tick.png"  /></td>
			</tr>
			<tr id="desc6" class="invisible"><td colspan="4">助飞贷（补贴利息的50%；不超过贷款本金的1.5%的担保费）；创业贷（补贴利息的70%；不超过贷款本金的1.5%的担保费）；最高100万。</td></tr>
			
			<tr id="fund7" class="invisible">
				<td>深圳市龙华区</td>
				<td><img class="tick" src="${pageContext.servletContext.contextPath}/resources/images/recommend/tick.png"  /></td>
				<td></td>
				<td><img class="tick" src="${pageContext.servletContext.contextPath}/resources/images/recommend/tick.png"  /></td>
			</tr>
			<tr id="desc7" class="invisible"><td colspan="4">孵化贷（1年最高500万额度，补贴基准上浮30%的50%利息，1年不超过100万；补贴担保费50%，不超过100万）；成长贷（1年最高1000万额度，补贴基准上浮30%的50%利息，1年不超过100万；补贴担保费50%，不超过100万）</td></tr>
			
			<tr id="fund8" class="invisible">
				<td>深圳市坪山区</td>
				<td><img class="tick" src="${pageContext.servletContext.contextPath}/resources/images/recommend/tick.png"  /></td>
				<td></td>
				<td><img class="tick" src="${pageContext.servletContext.contextPath}/resources/images/recommend/tick.png"  /></td>
			</tr>
			<tr id="desc8" class="invisible"><td colspan="4">补贴利息的50%和担保费的50%；最高100万元，可连续支持3年。</td></tr>
			
			<tr id="fund9" class="invisible">
				<td>深圳市光明区</td>
				<td><img class="tick" src="${pageContext.servletContext.contextPath}/resources/images/recommend/tick.png"  /></td>
				<td><img class="tick" src="${pageContext.servletContext.contextPath}/resources/images/recommend/tick.png"  /></td>
				<td><img class="tick" src="${pageContext.servletContext.contextPath}/resources/images/recommend/tick.png"  /></td>
			</tr>
			<tr id="desc9" class="invisible"><td colspan="4">补贴利息的50%；一般企业最高50万，高新企业最高100万。</td></tr>
		
		</table>
	
	</div>

<!-- -->
<div class="article"><a href="https://mp.weixin.qq.com/mp/homepage?__biz=MjM5MjMyNjcxNA==&hid=3&sn=dd0e74ca007fafc5f475de3fac90c5fc" style="text-decoration:underline;margin:0 0 20px 0;">点击此处查看：疫情期间深圳市政策补贴</a></div>

<!--申报按钮 -->
   

<form id="hiddenForm" action="/business/apply" method="get">
	<input type="hidden" id="suoshy" name="suoshy" value="<%=CommonUtil.trim(request.getParameter("suoshy"))%>">
	<input type="hidden" id="yuangrs" name="yuangrs" value="<%=CommonUtil.trim(request.getParameter("yuangrs"))%>">
	<input type="hidden" id="shangnsr" name="shangnsr" value="<%=CommonUtil.trim(request.getParameter("shangnsr"))%>">
	<input type="hidden" id="zhiscq" name="zhiscq" value="<%=CommonUtil.trim(request.getParameter("zhiscq"))%>">
	<input type="hidden" id="jigtz" name="jigtz" value="<%=CommonUtil.trim(request.getParameter("jigtz"))%>">
	<input type="hidden" id="jiekjl" name="jiekjl" value="<%=CommonUtil.trim(request.getParameter("jiekjl"))%>">
	<input type="hidden" id="suozqy" name="suozqy" value="<%=CommonUtil.trim(request.getParameter("suozqy"))%>">
	<div class="button_submit" onclick="submit()"><a href="#" ><img src="${pageContext.servletContext.contextPath}/resources/images/recommend/button_submit3.png" border="0" /></a></div>
</form>

</div>
<!--foot-->
<%@ include file="../common/foot.jsp" %>

</body>

<script type="text/javascript">
	function submit(){
		if(checkData()){
			return;
		}
		
		$("#hiddenForm").submit();
			
}

function checkData(){
		if($("#suoshy").val()==""){
		msg("数据出错，请重新填写企业信息。");
		setTimeout('jumpurl()',3000);
		return true;
	}
}
function jumpurl(){
	location='/business/inquiry';
}
	</script>


<!--判断政策 -->
<script type="text/javascript">
	var suoshy = $("#suoshy").val();
	var yuangrs = $("#yuangrs").val();
	var yingysr = $("#yingysr").val();
	var zhiscq = $("#zhiscq").val();
	var jigtz = $("#jigtz").val();
	var jiekjl = $("#jiekjl").val();
	var suozqy = $("#suozqy").val();
	var flag = 0;
	var ispub = 0;
	if(zhiscq=="1"){
		$("#knowledge").removeClass("invisible")
		flag = 1;
	}
	if(jigtz=="1"){
		$("#invest").removeClass("invisible")
		flag = 1;
	}
	if(jiekjl=="0"){
		$("#first").removeClass("invisible")
		flag = 1;
	}
	
	console.log("所属行业:"+suoshy);
	console.log("所在地区:"+suozqy);
	
	switch(suoshy) {
     case '0':
     if(yingysr<500){
     	ispub=1;
     }
     break;
        
     case '1':
     if(yingysr<2000||yuangrs<300){
     	ispub=1;
     }
     break;
        
     case '2':
     if(yingysr<6000){
     	ispub=1;
     }
     break;
        
     case '3':
     if(yingysr<5000||yuangrs<20){
     	ispub=1;
     }   
     break;
        
     case '4':
     if(yingysr<500||yuangrs<50){
     	ispub=1;
     }   
     break;
        
     case '5':
     if(yingysr<3000||yuangrs<300){
     	ispub=1;
     }   
     break;
        
     case '6':
     if(yingysr<1000||yuangrs<100){
     	ispub=1;
     }   
     break;
        
     case '7':
     if(yingysr<2000||yuangrs<300){
     	ispub=1;
     }   
     break;
        
     case '8':
     if(yingysr<2000||yuangrs<100){
     	ispub=1;
     }   
     break;
        
     case '9':
     if(yingysr<2000||yuangrs<100){
     	ispub=1;
     }   
     break;
        
     case '10':
     if(yingysr<1000||yuangrs<100){
     	ispub=1;
     }   
     break;
        
     case '11':
     if(yingysr<1000||yuangrs<100){
     	ispub=1;
     }   
     break;
        
     case '12':
     if(yingysr<1000){
     	ispub=1;
     }   
     break;
        
     case '13':
     if(yingysr<1000||yuangrs<300){
     	ispub=1;
     }   
     break;
        
     case '14':
     if(yuangrs<100){
     	ispub=1;
     }   
     break;
        
     case '15':
     if(yuangrs<100){
     	ispub=1;
     }   
     break;
     
     default:
     ispub=0;
     break;
} 
if(ispub==1){
	$("#pub").removeClass("invisible")
	flag = 1;
	}
if(flag==0){
	$("#default").removeClass("invisible")
}

var areaID = parseInt(suozqy)+1;
var id1 = "#fund"+areaID;
var id2 = "#desc"+areaID;
$(id1).removeClass("invisible");
$(id2).removeClass("invisible");
	
	</script>
</html>


