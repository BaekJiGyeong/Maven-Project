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
게시판 목록 입니다...<br/>
${title}<br/>
${number}<br/>
${author}

<!--  &nbsp;는 공백 한칸  -->
<c:forEach items="${allEmployees}" var="employee">
	${employee.employeeId}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	${employee.firstName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	${employee.lastName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	${employee.email}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	${employee.hireDate}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	${employee.salary}<br/>
</c:forEach>
</body>
</html>