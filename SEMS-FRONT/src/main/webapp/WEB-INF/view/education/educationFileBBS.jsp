<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<link href="<c:url value='/resources/css/eduDetail.css'/>" rel="stylesheet"/>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.min.js'/>"></script> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>강의 자료 게시판</h2>
	<hr/>
	<form name="searchForm" id="searchForm" >
	<table>
		<tr>
			<th>
				강사명
			</th>
			<th style="width: 200px;">
				제목
			</th>
			<th style="width: 200px;">
				내용
			</th>
			<th>
				작성일
			</th>
			<th>
				수정일
			</th>
			<th>
				조회수
			</th>
		</tr>
		<c:forEach items="${educationFileBBSList.educationFileBBSVOs}" var="educationFileBBS">
		<tr>
			<td>
				
				${educationFileBBS.memberId}
			</td>
			<td>
				<a href="<c:url value='/education/fileBBS/detail/${educationFileBBS.articleId}'/>">
					${educationFileBBS.title}
				</a>
			</td>
			<td>
				${educationFileBBS.contents}
			</td>
			<td>
				${educationFileBBS.createDate}
			</td>
			<td>
				${educationFileBBS.modifyDate}
			</td>
			<td>
				${educationFileBBS.hits}
			</td>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="6" align="center">
				${educationFileBBSList.paging.getPagingList("pageNo", "[@]", "이전", "다음", "searchForm")}
			</td>
		</tr>
		<tr>
			<td colspan="6" align="right">
				<select name="searchType">
					<option value="title">제목</option>
					<option value="contents">내용</option>
					<option value="createDate">작성일</option>
					<option value="modifyDate">수정일</option>
				</select>
				<input type="text" id="searchKeyword" name="searchKeyword" />
			</td>
		</tr>
	</table>
	</form>
	<hr/>
	<c:if test="${sessionScope._MEMBER_.id eq teacherId}">
		<form action="<c:url value='/education/writeFileBBS' />" method="post">
			<input type="hidden" name="educationId" value="${educationId}"/>
			<input type="submit" value="글쓰기"/>
		</form>
	</c:if>
</body>
</html>