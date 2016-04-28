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
			<li class="Encoding">안전하지 않은 예외처리</li>
		</ul>
	</div>
	<div class="w-spacer">&nbsp;</div>
	<div class="content" style="float:left;">
		<div class="result">
			<b>오류 메시지를 통한 정보 노출</b><br/>
			예외 처리시 과도하게 많은 시스템 정보를 출력하도록 코드가 작성된 경우.<br/>
			오류 발생시 시스템의 상태에 대한 많은 정보는 공격자에게 쉬운 공격 경로를 알려주는 역할을 할 수 있으므로 프로그램 작성시 주의해야 한다.<br/>
			입력 예> 숫자를 넣으면 정수로 변환한다.
		</div>
		<form name="cookieForm" id="cookieForm1" method="post" action="<c:url value='/attack/exception/printexception' />">
			<input type="text" name="text" size="50" />
			<input type="submit" value="전송"/>
		</form>
		<br/><br/>
		<div class="result">
			<b>오류상황 대응 부재</b><br/>
			발생한 예외에 대해 적절한 처리 없이 다음 로직들이 처리되는 경우 충분하지 않은 데이터로 프로그램이 실행될 수 있다.<br/>
			입력값 : ${input2}<br/>
			결과 : ${result2} / ${result2_1}
		</div>
		<form name="cookieForm2" id="cookieForm2" method="post" action="<c:url value='/attack/exception/passexception' />">
			<input type="text" name="text" size="50" />
			<input type="submit" value="전송"/>
		</form>
	</div>
	<div class="clear"></div>
</div>
<c:import url="/common/bottom" />
</body>
</html>