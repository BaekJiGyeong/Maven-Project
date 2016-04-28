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
	
});
</script>
</head>
<body>
<c:import url="/common/loginTop" />
<div class="wrapper">
	<div class="vNav" style="float:left;">
		<ul>
			<li class="RegEx">XPath Injection</li>
		</ul>
	</div>
	<div class="w-spacer">&nbsp;</div>
	<div class="content" style="float:left;">
		<div class="result">
<pre>
&lt;addressBook&gt;
	&lt;address&gt;
		&lt;name&gt;홍길동&lt;/name&gt;
		&lt;password&gt;1234&lt;/password&gt;
		&lt;email&gt;hong@korea.com&lt;/email&gt;
		&lt;ccard&gt;1234-5678-3322-9000&lt;/ccard&gt;
	&lt;/address&gt;
	&lt;address&gt;
		&lt;name&gt;이순신&lt;/name&gt;
		&lt;password&gt;9876&lt;/password&gt;
		&lt;email&gt;lee@korea.com&lt;/email&gt;
		&lt;ccard&gt;9999-8888-9000-1234&lt;/ccard&gt;
	&lt;/address&gt;
&lt;/addressBook&gt;
</pre>
			찾고자 하는 address의 email과 password를 입력<br/>
			name : ${name} <br/>
			password : ${password} <br/>
			email : ${email} <br/>
			ccard : ${ccard} <br/>
		</div>
		<form name="regExForm" id="regExForm1" method="post" action="<c:url value='/attack/xpath/test' />">
			Email : <input type="text" name="email" size="50" /><br/>
			Password : <input type="text" name="password" size="50" />
			<input type="submit" value="전송"/>
		</form>
	</div>
	<div class="clear"></div>
</div>
<c:import url="/common/bottom" />
</body>
</html>