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
			<li class="openRedirect">Open Redirect</li>
		</ul>
	</div>
	<div class="w-spacer">&nbsp;</div>
	<div class="content" style="float:left;">
		<form name="redirectURLForm" id="redirectURLForm" method="post" action="<c:url value='/attack/openredirect/url' />">
			<select	id="redirectURL" name="redirectURL" class="tip" data-tip="아래 선택된 URL 들은 모두 주소값을 Value로 가진다. paros, WireShark 등을 통해 전달되는 파라미터가 변경될 수 있다.">
				<option value="http://www.naver.com">Naver</option>
				<option value="http://www.daum.net">DAUM</option>
				<option value="http://www.google.com">Google</option>
			</select>
			<input type="submit" value="Redirect" />
		</form>
	</div>
	<div class="clear"></div>
</div>
<c:import url="/common/bottom" />
</body>
</html>