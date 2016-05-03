<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

		<c:forEach items="${articleListVO.articleList}" var="article">
			<tr>
				<td>${article.articleId}</td>
				<td>${article.articleNumber}</td>
				<td><a href="/board/detail/${article.articleId}">${article.subject}</a></td>
				<td>${article.writer}</td>
				<td>${article.description}</td>
				<td>${article.createdDate}</td>
				<td>${article.modifiedDate}</td>
			</tr>
		</c:forEach>
	</table>

	<form id="pagingForm">
		${articleListVO.paging.getPagingList("pageNo", "[@]", "이전","다음","pagingForm")}
	</form>
	<a href="/board/write">새글쓰기</a>

</body>
</html>