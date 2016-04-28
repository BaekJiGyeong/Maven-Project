<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
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
	
	$("#replyWriteBtn").click(function() {
		if($("#conent").val() == "") {
			alert("댓글을 입력하세요!");
			return;
		}
		
		$("#replyForm").submit();
	});
	
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
	<div class="content" style="float:left; width:700px;">
		<b style="font-size: 30px;">${article.subject}</b><br/>
		<hr size="1"/>
		<b style="font-size: 20px; color:#999;"> : ${article.userName} :: H : ${article.hit} / R : ${article.recommend}</b><br/>
		<b style="font-size: 15px; color:#999;">C : ${article.createdDate} / M : ${article.modifiedDate}</b>
		<hr size="1"/>
		<c:if test="${not empty article.fileName}">
			<a href="<c:url value='/resources/uploadFiles/${article.fileName}'/>">
				<img src="<c:url value='/resources/img/data.png'/>" width="12" height="12"/> ${article.fileName}
			</a>
		</c:if>
		<p style="font-size: 12px; padding-left: 10px;">${article.content}</p>
		<hr size="1"/>
		<p style="text-align:right;">
			<a href="<c:url value='/board/list'/>">목록</a> 
			<c:if test="${article.userId eq sessionScope._MEMBER_.id}">
			/ <a href="<c:url value='/board/modify/${article.id}'/>">수정</a> 
			</c:if>
			/ <a href="<c:url value='/board/recommend/${article.id}'/>">추천</a> 
			/ <a href="<c:url value='/board/write'/>">글쓰기</a>
			<c:if test="${article.userId eq sessionScope._MEMBER_.id}"> 
			/ <a href="<c:url value='/board/delete/${article.id}'/>">삭제</a>
			</c:if>
		</p>
		
		<table class="dataGrid" style="width:100%;">
			<tr>
				<th>댓글</th>
			</tr>
			<c:if test="${ empty replyList}">
			<tr>
				<td>
					등록된 댓글이 없습니다.
				</td>
			</tr>
			</c:if>
			<c:forEach items="${replyList}" var="replyList">
			<tr>
				<td>
					<div style="width:100%; text-align:left;">
						<b style="font-size:12pt;">${replyList.userName}</b> <a href="<c:url value='/reply/recommend/${article.id}/${replyList.id}'/>">추천</a> : ${replyList.recommend} (${replyList.createdDate})
					</div>
					<div style="width:98%; text-align:left; padding-left:10px;">
						${replyList.content}
					</div>
				</td>
			</tr>
			</c:forEach>
		</table>
		<form id="replyForm" name="replyForm" style="text-align:right;" method="post" action="<c:url value='/reply/write' />">
			<input type="hidden" name="boardId" value="${article.id}" />
			<textarea name="content" id="conent" rows="10" placeholder="댓글을 입력하세요." style="width:100%"></textarea>
			<input type="button" id="replyWriteBtn" value="댓글 등록" />
		</form>
	</div>
	<div class="clear"></div>
</div>
<c:import url="/common/bottom" />
</body>
</html>