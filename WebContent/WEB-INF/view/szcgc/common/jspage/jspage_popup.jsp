<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<!--弹出层-->
<link  href="${pageContext.servletContext.contextPath}/resources/css/Popup_donate.css" rel="stylesheet">
<script type="text/javascript">
jQuery(document).ready(function($){$('.Home_sort_data').on('click',function(event){event.preventDefault();$('.cd-Home_business').removeClass('is-visible');$('.cd-Home_money').removeClass('is-visible');if($('.cd-Home_sort').attr("class").indexOf("is-visible")>-1){$('.cd-Home_sort').removeClass('is-visible');}else{$('.cd-Home_sort').addClass('is-visible');}});$('.cd-Home_sort').on('click',function(event){if($(event.target).is('.cd-Home_sort')){event.preventDefault();$(this).removeClass('is-visible');}});$('.Home_business_data').on('click',function(event){event.preventDefault();$('.cd-Home_sort').removeClass('is-visible');$('.cd-Home_money').removeClass('is-visible');if($('.cd-Home_business').attr("class").indexOf("is-visible")>-1){$('.cd-Home_business').removeClass('is-visible');}else{$('.cd-Home_business').addClass('is-visible');}});$('.cd-Home_business').on('click',function(event){if($(event.target).is('.cd-Home_business')){event.preventDefault();$(this).removeClass('is-visible');}});$('.Home_money_data').on('click',function(event){event.preventDefault();$('.cd-Home_sort').removeClass('is-visible');$('.cd-Home_business').removeClass('is-visible');if($('.cd-Home_money').attr("class").indexOf("is-visible")>-1){$('.cd-Home_money').removeClass('is-visible');}else{$('.cd-Home_money').addClass('is-visible');}});$('.cd-Home_money').on('click',function(event){if($(event.target).is('.cd-Home_money')){event.preventDefault();$(this).removeClass('is-visible');}});$('.Gift_collection_data').on('click',function(event){event.preventDefault();$('.cd-Gift_collection').addClass('is-visible');});$('.cd-Gift_collection').on('click',function(event){if($(event.target).is('.cd-Gift_collection-quxiao')){event.preventDefault();$(this).removeClass('is-visible');}});});
</script>

