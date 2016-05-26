<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
	table {
	     border-collapse: collapse;
	     text-align: center;
	     width: 800px;
	}
	
	 table, th, td {
	     border: 1px solid black;
	}
</style>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.min.js'/>"></script>
<script type="text/javascript">

</script>
<title>수강생 출결 이력</title>
</head>
<body>
	수강생 출결 이력<br/><br/>
	
	정상 출석 : ○ / 지각 : △ / 조퇴 : ● / 결석 : X<br/><br/>
	
	
	<table>
		<tr>
			<th>교육명</th>
			<th>교육 아이디</th>
			<th>수강생 이름</th>
			<th>수강생 아이디</th>
			<th>출근 시간</th>
			<th>퇴근시간</th>
			<th>출결 상태</th>
		</tr>
		
		<c:forEach items="${ attendanceList }" var="attend">
			<tr>
				<td>${ attend.educationTitle }</td>
				<td>${ attend.educationId }</td>
				<td>${ attend.memberName }</td>
				<td>${ attend.memberId }</td>
				<td>${ attend.attendTime }</td>
				<td>${ attend.leaveTime }</td>
				<td>${ attend.state }</td>
			</tr>
		</c:forEach>
	</table>
	
	<%
		int day = 0;
		int late = 0;
		int absence = 0;
		int earlyLeave = 0;
		int attend = 0;
	%>
	
	<c:forEach items="${ attendanceList }" var="attend">
		<%
			day++;
		%>
		<c:if test="${ attend.state eq '△' }">
			<%
				late++;
			%>
		</c:if>
		<c:if test="${ attend.state eq 'X' }">
			<%
				absence++;
			%>
		</c:if>
		<c:if test="${ attend.state eq '●' }">
			<%
				earlyLeave++;
			%>
		</c:if>
		<c:if test="${ attend.state eq '○' }">
			<%
				attend++;
			%>
		</c:if>
	</c:forEach>
	
	<br/>
	<table>
		<tr>
			<th>전체 일수</th>
			<th>정상 출석 일수</th>
			<th>지각/조퇴 일수</th>
			<th>결석 일수</th>
		</tr>
		<tr>
			<td>
				<% pageContext.setAttribute("day", day); %>
				${ day }
			</td>
			<td>
				<% pageContext.setAttribute("attend", attend); %>
				${ attend }
			</td>
			<td>
				<% 
					int lateAndEarlyLeave = late + earlyLeave;
					pageContext.setAttribute("lateAndEarlyLeave", lateAndEarlyLeave);
				%>
				${ lateAndEarlyLeave }
			</td>
			<td>
				<% pageContext.setAttribute("absence", absence); %>
				${ absence }
			</td>
		</tr>
	</table>
	
</body>
</html>