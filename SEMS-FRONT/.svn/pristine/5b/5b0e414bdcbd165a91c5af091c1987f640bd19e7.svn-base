<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
.inputButton {
	border:none;
	border-radius:5px;
	padding:6px 12px;
	font-weight:bold;
	text-transform:uppercase;
	color:#FFFFFF;
	background-color:#E05149;
	cursor: pointer;
}
</style>
<link href="<c:url value='/resources/css/eduDetail.css'/>" rel="stylesheet"/>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.min.js'/>"></script> 
<script type="text/javascript">
	window.onload = function() {
		var backButton = document.getElementById("backButton");
		backButton.onclick = function() {
			location.href= "/education/fileBBS/${educationFileBBS.educationId}";
		};
	};
	
	$(document).ready (function () {
		$("#writeReplyButton").click(function () {
			$("#writeReplyForm").attr("action", "<c:url value='/education/fileBBS/doWriteReply' />");
			$("#writeReplyForm").submit();
		});
	});
	
	function download (id) {
		$.post("<c:url value="/checkClassAttend/" />" + id, { }, function(data) {
			if (!data) {
				alert("인터넷 연결이 끊겼습니다.");
			}
			else if (data == "NO") {
				alert("수강 기록이 없는 교육의 자료는 다운 받을수 없습니다.");
			}
			else if (data == "OK") {
				location.href = "<c:url value="/downloadEducationFile/" />" + id;
			}
		});
	};
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>강의 자료</h2>
	<hr/>
	<table>
		<tr>
			<th>
				교육 번호
			</th>
			<td>
				${educationFileBBS.educationId}
			</td>
		</tr>
		<tr>
			<th>
				강의 자료 번호
			</th>
			<td>
				${educationFileBBS.articleId}
			</td>
		</tr>
		<tr>
			<th>
				강사 아이디
			</th>
			<td>
				${educationFileBBS.memberId}
			</td>
		</tr>
		<tr>
			<th>
				제목
			</th>
			<td>
				${educationFileBBS.title}
			</td>
		</tr>
		<tr>
			<th>
				내용
			</th>
			<td>
				${educationFileBBS.contents}
			</td>
		</tr>
		<tr>
			<th>
				작성일
			</th>
			<td>
				${educationFileBBS.createDate}
			</td>
		</tr>
		<tr>
			<th>
				조회수
			</th>
			<td>
				${educationFileBBS.hits}
			</td>
		</tr>
	</table>
	<c:forEach items="${fileList}" var="file">
		<a href="javascript:download(${file.fileId});" >
			${file.fileName} <br/>
		</a>
	</c:forEach>
	<input id="backButton" class="inputButton" type="button" value="목록으로"/>
	<hr/>
	<h3>질문등록</h3>
	
	<c:if test="${ eduReplyListVO.qnaList.size() gt 0 }">
	<div id="tableTwo">
		<c:forEach items="${eduReplyListVO.qnaList}" var="qna">
			<div style="padding-left:${qna.depth * 20}px">
				<div>
					<div>
						<span>작성자 : </span>
						<span>${ qna.mbrId }</span>
						<input type="hidden" value="${qna.replyId }"/>
						<br/>
					</div>
					<div>
						<span>날짜 : </span>${ qna.createdDate }
					</div>
				</div>
				<div>
					<div>
						<span>내용 : </span>${ qna.description }
						
						<c:if test="${ qna.depth eq 1 and memberType eq 'ADM'}">
							<div style="text-align:right; cursor:pointer" class="reReplyBtn">&nbsp; + </div>
						</c:if>
					</div>
				</div>
			
				<div class="registNewReReply">
					<div>
						<form id="registReReplyForm">
							<div>답변내용 :</div> 
							<textarea class="reReplyContent" name="reReplyContent" cols="28" rows="5" placeholder="답글을 입력하세요."></textarea>
							<div style="text-align:right; cursor:pointer" class="registReReplyBtn">답글 등록</div>
						</form>
					</div>
				</div>
				<c:if test="${ qna.depth eq 2 }">
					<div>
						<div>
							<span class="likeBtn" style="cursor:pointer">추천 : </span>${ qna.likeCnt } &nbsp;&nbsp;
							<span class="dislikeBtn" style="cursor:pointer">반대 : </span>${ qna.dislikeCnt }
						</div>
					</div>
				</c:if>
			</div>
			<hr/>
		</c:forEach>
	</div>

	<div style="text-algin:center">
		<table>
			<tr>
				<td colspan="6" style="text-align:center">
					<form id="pagingForm">
						<c:if test="${ eduReplyListVO ne null }">
							${eduReplyListVO.paging.getPagingList("pageNo", "[@]", "이전", "다음", "pagingForm")}
						</c:if>
					</form>
				</td>
			</tr>
		</table>
	</div>
	</c:if>
	
	<form:form id="writeReplyForm" commandName="bbsReplyVO" method="post">
		<input type="hidden" name="articleId" value="${educationFileBBS.articleId}">
		<textarea id="description" name="description" cols="51" rows="5" placeholder="내용을 입력하세요.">${qnaVO.description}</textarea>
		<br />
		<br />
		<input id="writeReplyButton" type="button" class="inputButton" value="질문하기" />
	</form:form>
	
	
	
</body>
</html>