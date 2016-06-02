<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<input type="text" name="pageNo"/>
	<c:forEach items="${memoListVO.memoList}" var="memo">
	<p>
		<a href="/MongoTest/detail/${memo._id}">${memo.memo}</a>
	</p>
	</c:forEach>
	<form id="pagingForm">
		${memoListVO.paging.getPagingList("pageNo", "[@]", "이전","다음","pagingForm")}
	</form>
</body>
</html>