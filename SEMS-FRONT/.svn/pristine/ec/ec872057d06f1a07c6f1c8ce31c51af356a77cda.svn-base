<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>강의 게시판</title>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.min.js"/>"></script>
<script type="text/javascript">
	$(document).ready(function (){
		
		$(".educationId").click(function () {
			var root = $(this).parent().parent().children(":eq(7)").children();
			
			var educationId = root.val();
			location.href = "<c:url value="/eduBoard/" />"+educationId;
		});
		
	});
	</script>

</head>
<body>


<div>
	<span>현재 수강 목록</span>
		<table>
			<tr>
				<th>educationId</th>
				<th>educationTitle</th>
				<th>memberId</th>
				<th>status</th>
				<th>startDate</th>
				<th>endDate</th>
		
			</tr>
			<c:forEach items="${educationListVO}" var="educationVO">
				<tr>
					<td>${ educationVO.educationId }</td>
					<td><span class="educationId" >${ educationVO.educationTitle }</span></td>
					<td>${ educationVO.memberId }</td>
					<td>${ educationVO.status }</td>
					<td>${ educationVO.startDate }</td>
					<td>${ educationVO.endDate }</td>
					<td><a href="<c:url value='/resignCourse/${educationVO.educationId}'/>" target="_blank" 
					onclick="window.open(this.href, 'popupName', 'width=800, height=500, left=50, top=50, statusbar=0, scrollbars=1'); return false;" 
					onkeypress="this.onclick(); return false;" >DROP</a></td>
					<td><input type="hidden" id="${ educationVO.educationId }" value="${ educationVO.educationId }" /></td>
				</tr>
			</c:forEach>
		</table>
</div>
<br/>
<span>-------------------------------------------------------------------------------------------</span>
<br/>
<br/>
<div>
	<span>이전 수강 목록</span>
		<table>
			<tr>
				<th>educationId</th>
				<th>educationTitle</th>
				<th>memberId</th>
				<th>status</th>
				<th>startDate</th>
				<th>endDate</th>
		
			</tr>
			<c:forEach items="${ preEducationListVO.educationList }" var="educationVO">
				<tr>
					<td>${ educationVO.educationId }</td>
					<td><span class="educationId" >${ educationVO.educationTitle }</span></td>
					<td>${ educationVO.memberId }</td>
					<td>${ educationVO.status }</td>
					<td>${ educationVO.startDate }</td>
					<td>${ educationVO.endDate }</td>
					<td></td>
					<td><input type="hidden" id="${ educationVO.educationId }" value="${ educationVO.educationId }" /></td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="10">
					<form id="pagingForm">
						${ preEducationListVO.paging.getPagingList("pageNo", "[@]", "이전", "다음", "pagingForm") }
					</form>
				</td>
			</tr>
		</table>
</div>
</body>
</html>