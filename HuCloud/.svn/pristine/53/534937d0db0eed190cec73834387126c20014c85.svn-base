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
});
	
</script>
</head>
<body>
<c:import url="/common/loginTop" />
<div class="wrapper">
	<div class="vNav" style="float:left;">
		<ul>
			<li class="sql">Check Password</li>
		</ul>
	</div>
	<div class="w-spacer">&nbsp;</div>
	<div class="content" style="float:left;">
		<div class="result">
			<b>일반적으로 권고되는 패스워드 정책</b>
			<ol>
				<li>대/소문자, 숫자, 특수문자를 혼용하여 8글자 이상 패스워드를 사용하도록 한다.</li>
				<li>동일문자를 연속 4회 이상 사용하지 못하도록 한다.</li>
				<li>패스워드 히스토리를 관리하여 2~3개 이상 동일 패스워드를 사용하지 못하도록 한다.</li>
				<li>패스워드 변경 주기를 설정한다.(패스워드 유효기간을 90일 이하로 설정)</li>
				<li>연속적인 숫자/문자의 조합 및 생일, 전화번호 등 추측하기 쉬운 패스워드 사용을 금지한다.</li>
				<li>사전에 나오는 쉬운 단어나 이름은 패스워드로 사용하지 못하도록 한다.</li>
				<li>사용자명이나 계정명, 사번과 같은 패스워드는 사용하지 못하도록 한다.</li>
				<li>기본 설정된 패스워드는 사용하지 못하도록 설정한다.</li>
				<li>초기 부여된 패스워드는 사용자 최초 접속시 변경하도록 설정한다.</li>
			</ol>
		</div>
		<div class="spacer"></div>
		<div class="result">
			결과<br/>
			${result }
		</div>
		<div class="spacer"></div>
		<form method="post" action="/HuCloud/attack/verifyPassword">
		<table>
			<tr>
				<td><input type="password" name="password" class="tip" data-tip="하나 이상의 영문자, 하나 이상의 숫자, 하나 이상의 특수문자로 이루어진 8글자 이상의 비밀번호"/></td>
				<td><input type="submit" value="Execute" /></td>
			</tr>
		</table>
		</form>
	</div>
	<div class="clear"></div>
</div>
<c:import url="/common/bottom" />
</body>
</html>