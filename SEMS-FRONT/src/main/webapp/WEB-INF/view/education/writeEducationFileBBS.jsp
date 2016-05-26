<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
.inputButton {
	border:none;
	border-radius:5px;
	padding:6px 12px;
	font-weight:bold;
	text-transform:uppercase;
	color:#FFFFFF;
	background-color:#E05149;
	cursor: pointer;
}
</style>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.min.js'/>"></script>
<script type="text/javascript">
	$(document).ready(function () {
		$("#writeButton").click (function () {
			$("#writeForm").attr("action", "<c:url value='/education/doWriteFileBBSAction' />" );
			$("#writeForm").submit();
		});
		
		$("#file").change(function () {
			$("#fileName").html("");
			var files = $("#file").val().split(', ');
			for(var i = 0; i < files.length; i++) {
				$("#fileName").append("<div class='deleteFile'>"+files[i]+"</div>");
			}
		});
	});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SEMS</title>
</head>
<body>
	<form:form id="writeForm" commandName="educationFileBBSVO" method="POST" enctype="multipart/form-data">
		<input type="hidden" name="educationId" value="${educationId}"/>
		<input name="title" type="text" placeholder="제목을 입력하세요."/>
		<br/>
		<br/>
		<textarea name="contents" placeholder="내용을 입력하세요."></textarea>
		<br/>
		<br/>
		<input id="file" type="file" name="file" multiple="multiple"/>
		<br/>
		<br/>
		<span id="fileName"></span>
	</form:form>
	<br/><br/>
	<input id="writeButton" class="inputButton" type="button" value="등록"/>
</body>
</html>