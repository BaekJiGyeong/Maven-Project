<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
$(document).ready(function() {
	
	$("div.login").hide();
	
	var isShow = false;
	
	$("span.login").click(function() {
		if(isShow) {
			$("div.login").slideUp("fast");
			isShow = false;
		}
		else {
			$("div.login").slideDown("fast");
			$("#id").focus();
			isShow = true;
		}
		
	});
	
	fullBlock().hide();
	
	$(".login .logout").click(function() {
		
		fullBlock().show();
		
		if(confirm("로그아웃 하시겠습니까?")) {
			$.post("/HuCloud/member/logout", {}, function(data) {
				if(data == "OK") {
					location.href="/HuCloud";
				}
				if(data != "OK") {
					alert("로그아웃에 실패했습니다. 강사에게 문의하세요.");
					fullBlock().hide();
				}
			});
		}
		else {
			fullBlock().hide();
		}
		
	});
	
	$(".login .dropTable").click(function() {
		
		fullBlock().show();
		
		if(confirm("모든 테이블을 삭제합니다. 계속 진행하겠습니까?")) {
			$.post("/HuCloud/table/deleteAllTable", {}, function(data) {
				if(data == "OK") {
					alert("모든 테이블을 삭제했습니다. 이 창을 닫습니다.");
					self.close();
				}
				if(data != "OK") {
					alert("테이블 삭제에 실패했습니다. 강사에게 문의하세요.");
					fullBlock().hide();
				}
			});
		}
		else {
			fullBlock().hide();
		}
	});
	
});

function fullBlock() {
	var width = $(window).width();
	var height = $(window).height();
	
	$(".blockDiv").css({
		"width" : width + "px",
		"height": height + "px",
		"opacity" : "0.5"
	});
	
	return $(".blockDiv");
}

</script>
<div class="blockDiv"></div>
<div class="login">
	<div class="wrapper" style="margin-top: 6px;">
		<span class="button loginButton logout">Logout</span>
		<span class="button registButton dropTable">Drop All Tables</span>
	</div>
</div>
<div class="wrapper">
	<div style="vertical-align: top;">
		<img src="/HuCloud/resources/img/hucloud-logo_60.png" style="float:left;" />
		<img src="/HuCloud/resources/img/security.PNG" width="130" style="float:left;padding-top:5px;"/>
		<span class="login link" style="float:right;padding-top: 5px; padding-right: 5px;">(<b>${sessionScope._MEMBER_.userName}</b>) Logout</span>
		<div style="clear:both;"></div>
	</div>
	<div class="spacer"></div>
	<c:import url="/common/menu" />
</div>
<div class="tooltip"></div>