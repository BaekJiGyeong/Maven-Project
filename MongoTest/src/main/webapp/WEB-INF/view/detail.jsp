<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="/MongoTest/doUpdate">
		<textarea name="memo" style="width:300px; height:300px">${memo.memo}</textarea>
		<br/>
		<input type="submit" value="수정"/>
		<input type="hidden" name="id" value="${memo._id}"/>
		<input type="button" value="삭제"
			onclick="location.href='/MongoTest/doDelete/${memo._id}'"/>
	</form>
</body>
</html>