<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${initParam.TITLE}</title>
<link rel="stylesheet" href="/HuCloud/resources/css/menu.css" />
<link rel="stylesheet" href="/HuCloud/resources/css/common.css" />
<script type="text/javascript" src="/HuCloud/resources/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="/HuCloud/resources/js/menu.js"></script>
<script type="text/javascript" src="/HuCloud/resources/js/tip.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("#normal").click(function() {
		var form = document.getElementById("cookieForm2");
		form.action = "http://localhost:8080/HuCloud/attack/cookie/method1";
	});
	
	$("#ssl").click(function() {
		var form = document.getElementById("cookieForm2");
		form.action = "https://localhost:8443/HuCloud/attack/cookie/method2";
	});
	
	$("#normal").click();
});
	
</script>
</head>
<body>
<c:import url="/common/loginTop" />
<div class="wrapper">
	<div class="vNav" style="float:left;">
		<ul>
			<li class="Cookie">Cookie</li>
		</ul>
	</div>
	<div class="w-spacer">&nbsp;</div>
	<div class="content" style="float:left;">
		<div class="result">
			<b>Method 1. 쿠키 테스트를 위한 입력값을 넣고 서버로 전송</b><br/>
			예 > Greeting!<br/>
			Paros로 request와 response를 캡쳐해 전달되는 쿠키값을 확인.
		</div>
		<form name="cookieForm" id="cookieForm1" method="post" action="<c:url value='/attack/cookie/method1' />">
			<input type="text" name="text" />
			<input type="submit" value="전송" />
		</form>
		<br/><br/>
		<div class="result">
			<b>Method 2. 쿠키 테스트를 위한 입력값을 넣고 보안 쿠키 라디오 버튼을 선택한 뒤 서버로 전송</b><br/>
			예 > Greeting!<br/>
			HTTP와 HTTPS로 연결 요청시 전달되는 쿠키 확인<br/>
			HTTPS 설정<br/>
			keytool -genkey -alias tomcat -keypass changeit -storepass changeit -keyalg RSA -keystore %USERPROFILE%\.keystore -dname "CN=localhost, OU=OrgUnit, O=MyCompany, C=KR"
		</div>
		<form name="cookieForm" id="cookieForm2" method="post" action="<c:url value='/attack/cookie/method1' />">
			<input type="radio" name="ssl" id="normal" value="normal" checked="checked"/> 일반 쿠키
			<input type="radio" name="ssl" id="ssl" value="ssl" /> 보안쿠키 <br/>
			<input type="text" name="text" />
			<input type="submit" value="전송" />
		</form>
	</div>
	<div class="clear"></div>
</div>
<c:import url="/common/bottom" />
</body>
</html>