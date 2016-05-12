<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>졸업 구분 코드 설정 페이지</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script type="text/javascript">

	$(document).ready(function() {
		
		$("#mainBtn").click(function(){
			location.href="<c:url value="/"/>";
		});
		
		$("#cancleBtn").click(function (){
			$("#cdId").val("");
			$("#cdNm").val("");
			
		});
		
		//수정버튼 클릭시
		$(".highestEduModifyBtn").click(function (){
			var cdId =  $(this).parent().parent().children(":eq(0)");
			var cdNm = $(this).parent().parent().children(":eq(2)").children();
			
			if(cdNm.val()==""){
				alert("CODE NAME을 써주세요.");
				return;
			}
			
			if(cdNm.val().length > 10){
				alert("CODE NAME은 10글자 이하로 써주세요.");
				return;
			}
			
			if ( confirm("입력한 내용으로 수정하시겠습니까?") == true ) {
				
				$.post("<c:url value="/doHighestEduModify"/>", {
					"cdId" : cdId.val(),
					"cdNm" : cdNm.val()
				}, function(data) {
					
					location.href="<c:url value="/highestEduPage"/>";
					
				});
			}
			else {
				return;
			}
		});
		
		
		//삭제버튼 클릭시
		$(".highestEduDeleteBtn").click(function (){
			
			var root = $(this).parent().parent().children(":eq(0)");
			console.log(root.val());
			
			if ( confirm("삭제하시겠습니까?") == true ) {
				location.href = "/comm/doHighestEduDelete/"+ root.val();
			}
			else {
				return;
			} 
		});
		
		//추가버튼 클릭시
		$("#highestEduInsertBtn").click(function (){
			var cdId =  $("#cdId").val();
			var cdNm = $("#cdNm").val();
			
			if(cdId==""){
				alert("CODE ID를 써주세요.");
				return;
			}
			if(cdId.length > 4){
				alert("CODE ID는 4글자 이하로 써주세요.");
				return;
			}
			
			if(cdNm==""){
				alert("CODE NAME을 써주세요.");
				return;
			}
			
			if(cdNm.length > 10){
				alert("CODE NAME은 10글자 이하로 써주세요.");
				return;
			}
			
			if ( confirm("등록하시겠습니까?") == true ) {
				
				$.post("<c:url value="/doHighestEduInsert"/>", {
					"cdId" : cdId,
					"cdNm" : cdNm
				}, function(data) {

					location.href="<c:url value="/highestEduPage"/>";
					
				});
			}
			else {
				return;
			}
		});
		
	});
	
</script>

</head>
<body>

	<table>
		<th>Code Id</th>
		<th>Code Name</th>
		
		<c:forEach items="${highestEduTpList}" var="highestEdu">
			<tr>
				<input type="hidden" id="${highestEdu.cdId}" name="cdId" value="${highestEdu.cdId}"/>
				<td style="text-align:center">${highestEdu.cdId}</td>
				<td>
					<input style="text-align:center" type="text" id="${highestEdu.cdNm}" name="cdNm" value="${highestEdu.cdNm}"/>
				</td>
				
				<td><input type="button" class="highestEduModifyBtn" value="수정"/></td>
				<td><input type="button" class="highestEduDeleteBtn" value="삭제"/></td>
			</tr>
		</c:forEach>
	
		<tr>
			<td>
				<input type="text" id="cdId" name="newCdId" value=""/>
			</td>
			<td>
				<input type="text" id="cdNm" name="newCdNm" value=""/>
			</td>
			<td><input type="button" id="highestEduInsertBtn" value="추가" /></td>
			<td><input type="button" id="cancleBtn" value="취소" /></td>
		</tr>
		<br/>
	</table>
		
		<input type="button" id="mainBtn" value="처음으로"/>
	
	
	
</body>
</html>