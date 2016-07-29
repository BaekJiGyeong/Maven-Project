<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="decorator"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<link rel="stylesheet" href="/resources/css/layout.css" />
<!-- head 부분을 가져가겠다. title은 다른부분에서 가져감 -->
<script type="text/javascript" src="/resources/js/jquery-2.1.1.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<decorator:head />
<title><decorator:title default="sems-front" /></title>
</head>
<body>

	<div id="template">
		<div id="templateHeader">
			<jsp:include page="header.jsp" />
		</div>
		<div id="templateBody" class="wrapper row3">
			<main class="container clear">
				<decorator:body />
			<div class="clear"></div>
			</main>
		</div>
		<div id="templateFooter">
			<jsp:include page="footer.jsp" />
		</div>
		<!-- 	<div style="clear:both;"></div>	 -->
	</div>
</body>
</html>