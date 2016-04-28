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
			<li class="Encoding">URI 인코딩</li>
			<li class="Encoding">BASE64 인코딩</li>
			<li class="Encoding">URL 인코딩, HTLM 인코딩</li>
		</ul>
	</div>
	<div class="w-spacer">&nbsp;</div>
	<div class="content" style="float:left;">
		<div class="result">
			<b>Method 1. URI 인코딩</b><br/>
			웹브라우저에서 Data를 Encoding해 서버로 전달하고, 서버에서 Decoding<br/>
			예> hello&test=10 <br/>
			결과는 STS 혹은 Eclipse의 Console에서 확인
		</div>
		<form name="cookieForm" id="cookieForm1" method="post" action="<c:url value='/attack/encoding/method1' />">
			<input type="text" name="text" size="50" />
			<input type="submit" value="전송" onclick="this.form.text.value=encodeURIComponent(this.form.text.value)"/>
		</form>
		<br/><br/>
		<div class="result">
			<b>Method 2. BASE64 인코딩</b><br/>
			웹브라우저에서 Data를 Encoding해 서버로 전달하고, 서버에서 Decoding<br/>
			입력값 : ${inputValue}<br/>
			결과 : ${result}
		</div>
		<form name="cookieForm2" id="cookieForm2" method="post" action="<c:url value='/attack/encoding/method2' />">
			<input type="text" name="text" size="50" />
			<input type="submit" value="전송" onclick="this.form.text.value=Base64.encode(this.form.text.value)"/>
		</form>
		<br/><br/>
		<div class="result">
			<b>Method 3. URL Encoding, HTML Encoding해 출력</b><br/>
			웹브라우저에서 입력한 값을 URL Encoding, HTML Encoding 해 출력<br/>
			예> &lt;script&gt;alert("test");&lt;/script&gt; <br/>
			입력값 : <c:out value="${inputValue2}" /><br/>
			URL Encoder : ${result2_1}<br/>
			HTML Encoder : ${result2_2}
		</div>
		<form name="cookieForm3" id="cookieForm3" method="post" action="<c:url value='/attack/encoding/method3' />">
			<input type="text" name="text" size="50" />
			<input type="submit" value="전송" />
		</form>
	</div>
	<div class="clear"></div>
</div>
<c:import url="/common/bottom" />
</body>
</html>