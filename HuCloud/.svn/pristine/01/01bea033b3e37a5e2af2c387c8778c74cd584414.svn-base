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

	$("#executeBtn").click(function() {
		
		$.post("/HuCloud/attack/xss/attack3", $("#formDOM").serialize(), function(data) {
			var jsonObj = $.parseJSON($.trim(data));
			document.write(jsonObj.result);
		});
	});
	
});
	
</script>
</head>
<body>
<c:import url="/common/loginTop" />
<div class="wrapper">
	<div class="vNav" style="float:left;">
		<ul>
			<li class="xss">XSS</li>
		</ul>
	</div>
	<div class="w-spacer">&nbsp;</div>
	<div class="content" style="float:left;">
		<div class="sqlInjection">
			<div class="result" id="result">
				결과<br/>
				${result}
			</div>
			<div class="spacer"></div>
			<b>1. Reflective XSS</b>
			<div class="result">
				공격자가 악성 스크립트가 포함된 URL을 클라이언트에게 노출시켜 클릭하도록 유도하여,<br/>쿠키 정보를 탈취하거나 피싱 사이트, 불법 광고 사이트로 이동하게 한다.
			</div>
			<form method="post" action="/HuCloud/attack/xss/attack1">
				<table>
					<tr>
						<td><input type="text" value="${requestedString1}" class="tip" data-tip-str="<script>alert('xss');</script> 또는 <script>alert(document.cookie);</script>" name="script" size="40" /></td>
						<td><input type="submit" value="Execute" /></td>
					</tr>
				</table>
			</form>
			<div class="spacer"></div>
			<b>2. Stored XSS</b>
			<div class="result">
				악성 스크립트를 DB에 저장하여 해당 DB 정보를 이용하는 애플리케이션을 통해,<br/>시스템을 사용하는 모든 사용자들이 해당 스크립트를 실행하게 함으로써<br/>사용자의 쿠키 정보를 탈취하거나 피싱사이트, 불법 광고 사이트로 이동하게 한다.
			</div>
			<form method="post" action="/HuCloud/attack/xss/attack2">
				<table>
					<tr>
						<td><input type="text" value="${requestedString2}" class="tip" data-tip="1 또는 2" name="script" size="40" /></td>
						<td><input type="submit" value="Execute" /></td>
					</tr>
				</table>
			</form>
			<div class="spacer"></div>
			<b>3. DOM XSS</b>
			<div class="result">
				DOM(Document Object Model)은 HTML, XML을 다루기 위한 프로그래밍 API로,<br/>AJAX 프로그램에서 사용되는 자바스크립트를 이용하여
				브라우저에게 수신된 데이터를 다시 잘라서<br/>Document에 Write 하는 작업을 수행하는 경우 XSS 공격이 가능하게 한다.
			</div>
			<form id="formDOM" method="post">
				<table>
					<tr>
						<td><input type="text" value="${requestedString3}" class="tip" data-tip-str="<script>alert('xss');</script> 또는 <script>alert(document.cookie);</script>" name="script" size="40" /></td>
						<td><input type="button" id="executeBtn" value="Execute" /></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div class="clear"></div>
</div>
<c:import url="/common/bottom" />
</body>
</html>