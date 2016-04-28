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
<script type="text/javascript" src="/HuCloud/resources/js/webtoolkit.base64.js"></script>
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
			<li class="Encoding">널포인터 역참조</li>
		</ul>
	</div>
	<div class="w-spacer">&nbsp;</div>
	<div class="content" style="float:left;">
		<div class="result">
			<b>널포인터 역참조</b><br/>
			웹 페이지에서 전달되는 파라미터가 누락되어 서버로 전달될 경우 NullPointerException이 발생한다.<br/>
			Paros를 이용해 파라미터를 삭제한 뒤 요청해, 에러가 발생하는지 확인한다.<br/>
			결과 : ${result}
		</div>
		<form name="cookieForm" id="cookieForm1" method="post" action="<c:url value='/attack/nullpointer/test' />">
			<input type="text" name="text" size="50" />
			<input type="submit" value="전송"/>
		</form>
	</div>
	<div class="clear"></div>
</div>
<c:import url="/common/bottom" />
</body>
</html>