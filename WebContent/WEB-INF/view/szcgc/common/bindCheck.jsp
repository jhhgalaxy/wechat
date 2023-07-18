<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/mobile/plugin/westvalley_wev8/szcgc/common/base.jsp" %>
<script type="text/javascript">
if(!isBindUser){
	msg("您还未绑定手机号，请先绑定",function(){
		var forwardUrl = window.location.href;
		window.location.href = "/mobile/plugin/westvalley_wev8/szcgc/common/bindUser.jsp?forwardUrl="+forwardUrl;
	})
}
</script>

