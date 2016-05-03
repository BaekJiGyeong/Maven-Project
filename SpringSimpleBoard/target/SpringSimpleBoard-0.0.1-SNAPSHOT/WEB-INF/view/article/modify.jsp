<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<form:form commandName="articleVO" method="post" action="/board/doModifyAction">

	<input type="hidden" id="articleId" name="articleId" value="${article.articleId}"/>
	<input type="hidden" id="articleNumber" name="articleNumber" value="${article.articleNumber}"/>
	<input type="hidden" id="createdDate" name="createdDate" value="${article.createdDate}"/>
	<input type="text" id="subject" name="subject" placeholder="제목을 입력하세요." value="${article.subject}"/><br/>
	<form:errors path="subject"/><br/>
	
	<input type="text" id="writer" name="writer" placeholder="글쓴이를 입력하세요." value="${article.writer}"/><br/>
	<form:errors path="writer"/><br/>
	
	<textarea id="description" name="description" placeholder="내용을 입력하세요." >${article.description}</textarea><br/>
	<input type="submit" value="수정"/>
</form:form>

</body>
</html>