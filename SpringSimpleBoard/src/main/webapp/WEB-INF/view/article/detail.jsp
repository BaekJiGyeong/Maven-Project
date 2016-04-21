<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="2">
	<tr>
		<th>articleId</th>
		<th>articleNumber</th>
		<th>subject</th>
		<th>writer</th>
		<th>description</th>
		<th>createdDate</th>
		<th>modifiedDate</th>
	</tr>	
	<tr>
	<td>${article.articleId}</td>
	<td>${article.articleNumber}</td>
	<td>${article.subject}</td>
	<td>${article.description}</td>
	<td>${article.writer}</td>
	<td>${article.createdDate}</td>
	<td>${article.modifiedDate}</td>
	</tr>
</table>

<a href="/board/delete/${article.articleId}" >삭제</a>
<a href="/board/modify/${article.articleId}" >수정</a>
<a href="/board/list" >목록</a>

</body>
</html>