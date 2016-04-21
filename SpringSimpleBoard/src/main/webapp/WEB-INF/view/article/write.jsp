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

<form:form commandName="articleVO"
		method="post" action="/board/doWriteAction">
	<input type="text" id="subject" name="subject" placeholder="제목을 입력하세요." value="${articleVO.subject}"/><br/>
	<form:errors path="subject"/><br/>
	
	<input type="text" id="writer" name="writer" placeholder="글쓴이를 입력하세요." value="${articleVO.writer}"/><br/>
	<form:errors path="writer"/><br/>
	
	<textarea id="description" name="description" placeholder="내용을 입력하세요." >${articleVO.description}</textarea><br/>
	<input type="submit" value="글쓰기"/>
</form:form>

</body>
</html>