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
			<li class="RegEx">입력값 검증 1</li>
			<li class="RegEx">입력값 검증 2</li>
			<li class="RegEx">입력값 검증 3</li>
		</ul>
	</div>
	<div class="w-spacer">&nbsp;</div>
	<div class="content" style="float:left;">
		<div class="result">
			<b>입력값 검증 1</b><br/>
			입력값의 첫 번째 문자와 마지막 문자가 숫자이며, 그 사이는 아무 문자나 허용되는 필터를 작성하여 프로그램에 적용한다.
			<ul>
				<li>유효한 입력값 예 : 1hello kim9, 123456789</li>
				<li>유효하지 않은 입력값 예 : hello kim, 테스트데이터</li>
			</ul>
			<b>입력값 : ${ input1 }</b> <br/>
			<b>결과 : ${ result1 }</b>
		</div>
		<form name="regExForm" id="regExForm1" method="post" action="<c:url value='/attack/regex/method1' />">
			<input type="text" name="text" size="50" />
			<input type="submit" value="전송"/>
		</form>
		<br/><br/>
		<div class="result">
			<b>입력값 검증 2</b><br/>
			입력값이 kim:010-1111-2222:kim과 같이 첫 번째 세 번째 값이 같고, 두 번째 값은 숫자와 -로만 구성된 경우 "유효한 입력값" 으로 출력한다.<br/>
			다음과 같은 입력값들은 "유효하지 않은 입력값"으로 처리되도록 한다.
			<ul>
				<li>kim:kim:kim</li>
				<li>kim:010-2222-3333:park</li>
				<li>kim:!!234%%:kim</li>
			</ul>
			<b>입력값 : ${ input2 }</b> <br/>
			<b>결과 : ${ result2 }</b>
		</div>
		<form name="regExForm2" id="regExForm2" method="post" action="<c:url value='/attack/regex/method2' />">
			<input type="text" name="text" size="50" />
			<input type="submit" value="전송"/>
		</form>
		<br/><br/>
		<div class="result">
			<b>입력값 검증 3</b><br/>
			입력값이 &lt;script&gt; ... &lt;/script&gt; 패턴이면 &lt;를 &amp;lt;로, &gt;를 &amp;gt;로 변환하여 출력한다.<br/>
			<ul>
				<li>입력값 : &lt;script&gt;alert("xss");&lt;/script&gt;</li>
				<li>출력값 : &amp;lt;script&amp;gt;alert("xss");&amp;lt;/script&amp;gt;</li>
			</ul>
			<b>입력값 : <c:out value="${ input3 }"/></b> <br/>
			<b>결과 : ${ result3 }</b>
		</div>
		<form name="regExForm3" id="regExForm3" method="post" action="<c:url value='/attack/regex/method3' />">
			<input type="text" name="text" size="50" />
			<input type="submit" value="전송" />
		</form>
	</div>
	<div class="clear"></div>
</div>
<c:import url="/common/bottom" />
</body>
</html>