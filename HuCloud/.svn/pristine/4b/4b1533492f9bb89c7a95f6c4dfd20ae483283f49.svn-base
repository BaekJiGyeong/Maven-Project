<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${initParam.TITLE}</title>
<link rel="stylesheet" href="/HuCloud/resources/css/menu.css" />
<link rel="stylesheet" href="/HuCloud/resources/css/common.css" />
<link rel="stylesheet" href="/HuCloud/resources/css/tableLayout.css" />
<script type="text/javascript" src="/HuCloud/resources/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="/HuCloud/resources/js/menu.js"></script>
<script type="text/javascript" src="/HuCloud/resources/js/tip.js"></script>
<script type="text/javascript" src="/HuCloud/resources/js/tableLayout.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	
});
</script>
</head>
<body>
<c:import url="/common/loginTop" />
<div class="wrapper">
	<div class="vNav" style="float:left;">
		<ul>
			<li class="tip" data-tip="글쓰기 페이지로 이동하세요!">CSRF</li>
		</ul>
	</div>
	<div class="w-spacer">&nbsp;</div>
	<div class="content" style="float:left;">
		<table class="dataGrid">
			<tr>
				<th width="50">No</th>
				<th width="*">제목</th>
				<th width="50">글쓴이</th>
				<th width="50">조회</th>
				<th width="50">추천</th>
				<th width="125">등록일<br/>수정일</th>
			</tr>
			<c:if test="${ empty list}">
				<tr>
					<td colspan="6">등록된 게시글이 없습니다.</td>
				</tr>
			</c:if>
			<c:forEach items="${list}" var="list">
			<tr>
				<td>${list.id}</td>
				<td style="text-align:left;">
					<a href="read/${list.id}">
						${list.subject}
					</a>
				</td>
				<td>${list.userName}</td>
				<td>${list.hit}</td>
				<td>${list.recommend}</td>
				<td>${list.createdDate}<br/>${list.modifiedDate}</td>
			</tr>
			</c:forEach>
		</table>
		<div style="float:left;padding:10px; width:50px; text-align:center;">
			<a href="/HuCloud/board/write">Write</a>
		</div>
		<div style="float:left;padding:10px; width:607px; text-align:center;">${paging}</div>
	</div>
	<div class="clear"></div>
</div>
<c:import url="/common/bottom" />
</body>
</html>