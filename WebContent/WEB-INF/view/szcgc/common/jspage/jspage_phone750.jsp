<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<script type="text/javascript">
var fontSizeAuto = function(oriWidth){
	return function(){
		var viewportWidth = document.documentElement.clientWidth;
		if(viewportWidth > oriWidth){ viewportWidth = oriWidth; }
		if(viewportWidth < 320){ viewportWidth = 320; }
		document.documentElement.style.fontSize = viewportWidth/(oriWidth/100) +'px';	
	}
}
fontSizeAuto(750)();
window.onresize = fontSizeAuto(750);
</script>


