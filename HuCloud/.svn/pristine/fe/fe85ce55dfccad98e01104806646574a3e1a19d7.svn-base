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
	
	$(".login .loginButton").click(function() {
		if( $("#id").val() == "" ) {
			alert("ID를 입력하세요!");
			$("#id").focus();
			return;
		}
		if( $("#password").val() == "" ) {
			alert("Password를 입력하세요!");
			$("#password").focus();
			return;
		}
		
		
		$.post("/HuCloud/member/login", $("#loginForm").serialize(), function(data) {
			if(data == "OK") {
				alert("로그인이 완료되었습니다. 페이지를 새로고침합니다.");
				location.href="/HuCloud/member";
			}
			if(data != "OK") {
				alert("로그인이 실패했습니다. 아이디 혹은 비밀번호를 확인해 주세요.");
				$("#id").focus();
			}
		});
		
	});
	
	$(".register").hide();
	fullBlock().hide();
	$(".login .registButton").click(function() {
		
		$("span.login").click();
		
		var left = $(window).width() / 2;
		var top = $(window).height() / 2;
		
		var regWidth = 170 / 2;
		var regHeight = 170 / 2;
		
		fullBlock().show();
		
		$(".register").css({
			"top" : (top - regHeight) + "px",
			"left" : (left - regWidth) + "px"
		});
		
		$(".register").fadeIn("fast");
		$("#userId").focus();
	});
	
	$(".register .loginButton").click(function() {
		fullBlock().hide();
		$(".register").fadeOut("fast");
	});
	
	$(".register .registButton").click(function() {
		
		if( $("#userId").val() == "" ) {
			alert("ID를 입력하세요!");
			$("#userId").focus();
			return;
		}
		if( $("#userPassword").val() == "" ) {
			alert("Password를 입력하세요!");
			$("#userPassword").focus();
			return;
		}
		if( $("#userPasswordConfirm").val() == "" ) {
			alert("Password를 입력하세요!");
			$("#userPasswordConfirm").focus();
			return;
		}
		if( $("#userName").val() == "" ) {
			alert("Name을 입력하세요!");
			$("#userName").focus();
			return;
		}
		
		if( $("#userPassword").val() != $("#userPasswordConfirm").val() ) {
			alert("Password 가 일치하지 않습니다!");
			return;
		}
		
		$.post("/HuCloud/member/registry", $("#registerForm").serialize(), function(data) {
			if(data == "OK") {
				alert("회원가입이 완료되었습니다!");
				$("span.login").click();
				$("#id").val($("#userId").val());
				$("#password").focus();
			}
			else {
				alert($("#userId").val()+ "은(는) 이미 존재하는 아이디 입니다.");
			}
		});
		
		$(".register .loginButton").click();		
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
	<div class="wrapper">
		<form id="loginForm" name="loginForm">
			<input type="text" name="id" id="id" class="tip" placeholder="ID" />
			<input type="password" name="password" id="password" placeholder="Password"/>
			<span class="button loginButton">Login</span>
			<span class="button registButton">Sign Up</span>
		</form>
	</div>
</div>
<div class="register">
	<form id="registerForm" name="registerForm">
		<input type="text" name="userId" id="userId" placeholder="ID"/><br/>
		<div class="spacer"></div>
		<input type="password" name="userPassword" id="userPassword" placeholder="Password"/><br/>
		<div class="spacer"></div>
		<input type="password" name="userPasswordConfirm" id="userPasswordConfirm" placeholder="Password Confirm"/><br/>
		<div class="spacer"></div>
		<input type="text" name="userName" id="userName" placeholder="name"/><br/>
		<div class="spacer"></div>
		<span class="button registButton">Sign Up</span>
		<span class="button loginButton">Cancel</span>
	</form>
</div>
<div class="wrapper">
	<div style="vertical-align: top;">
		<img src="/HuCloud/resources/img/hucloud-logo_60.png" style="float:left;" />
		<img src="/HuCloud/resources/img/security.PNG" width="130" style="float:left;padding-top:5px;"/>
		<span class="login link" style="float:right;padding-top: 5px; padding-right: 5px;">Login</span>
		<div style="clear:both;"></div>
	</div>
	<div class="spacer"></div>
	<c:import url="/common/menu" />
</div>
<div class="tooltip"></div>