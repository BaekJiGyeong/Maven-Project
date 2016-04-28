<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(document).ready(function() {
		$(".bottom").css({
			"position" : "absolute",
			"top" : (($(window).height()- (14 + 30)) + $(window).scrollTop() ) + "px",
			"left" : "0px",
			"width" : "100%"
		});
		
		$(".bottom").click(function() {
			$(".bottom").fadeOut();
		});
	});
	
	$(window).scroll(function() {
		$(".bottom").css({
			"position" : "absolute",
			"top" : (($(window).height()- (14 + 30)) + $(window).scrollTop() ) + "px",
			"left" : "0px",
			"width" : "100%"
		});
		
	});
	
	$(window).resize(function() {
		$(".bottom").css({
			"position" : "absolute",
			"top" : (($(window).height()- (14 + 30)) + $(window).scrollTop() ) + "px",
			"left" : "0px",
			"width" : "100%"
		});
	});
</script>
<div class="wrapper bottom tip" title="F8 버튼으로 토글합니다." style="background-color:#555; padding: 15px 0px 15px 0px;">
	<div width="100%" style="padding-left:25px; color:#FFF;">
	Copyright ⓒ 2015 <a class="white" href="mailto:mc.jang@hucloud.co.kr">Min Chang Jang</a> in <a class="white" href="http://www.hucloud.co.kr">HuCloud</a> Corp.
	</div>
</div>
