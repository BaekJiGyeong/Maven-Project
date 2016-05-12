<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script type="text/javascript">

	$(document).ready(function () {
		
		$(".modifyEduCostBtn").click(function () {
			
			var root = $(this).parent().parent().children(":eq(1)").children();
			
			if (root.val() == "") {
				alert("수정할 이름을 입력하세요.");
				location.href = "<c:url value="/education/cost"/>";
				return;
			}
			
			if ( root.val().length > 5) {
				alert("입력 범위를 초과했습니다. 5자 이하로 입력하세요.");
				return;
			}
			
			
			if ( confirm("입력한 내용으로 수정하시겠습니까?") == true ) {
				
				$.post(
						"<c:url value="/education/modifyEduCost"/>"
						, {
							"cdNm" : root.val()
							, "cdId" : $(this).attr("id")
						}
						, function(data) {
							if(data == "OK") {
								alert("수정되었습니다.");
							}
							else if(data == "FAIL_V"){
								alert("중복된 데이터로 수정할 수 없습니다.");
							}
							else if(data == "FAIL_M"){
								alert("수정 중 오류 발생했습니다. 잠시 후 다시 시도해 주세요.");
							}
							location.href = "<c:url value="/education/cost"/>";
						}
				)
			}
		});
		
		$(".deleteEduCostBtn").click( function () {
			var root = $(this).parent().parent().children(":eq(0)").children();
			var cdId = root.val();
			
			if ( confirm("해당 교육 비용 코드를 삭제 하시겠습니까?") == true ) {
				location.href = "<c:url value="/education/deleteEduCost/"/>"+cdId;
			}
		});
		
		$("#insertEduBtn").click( function () {
			
			if ($("#newCode").val().length != 4) {
				alert("4자리 코드를 입력하세요.");
				return;
			}
			
			if ( $("#newCost").val() == "" || $("#newCost").val().length >5) {
				alert("1자 이상 5자 이하로 입력하세요.");
				return;
			}
			
			if ( confirm("해당 교육 비용 코드를 추가 하시겠습니까?") == true ) {
				$.post(
						"<c:url value="/education/insertEduCost"/>"
						, {
							"cdNm" : $("#newCost").val()
							, "cdId" : $("#newCode").val()
						}
						, function(data) {
							if(data == "OK") {
								alert("등록되었습니다.");
							}
							else if(data == "FAIL_V"){
								alert("중복된 데이터를 넣을 수 없습니다.");
							}
							else if(data == "FAIL_I"){
								alert("등록 중 오류 발생했습니다. 잠시 후 다시 시도해 주세요.");
							}
							location.href = "<c:url value="/education/cost"/>";
						}
				)
			}
		
		});
		
	});
</script>

</head>
<body>

<div>
	<table>
		<c:forEach items="${costList}" var="cost">
			<tr>
				<td><input type="hidden" id="cdNm${cost.cdId}" name="cdNm" value="${cost.cdId}" />${cost.cdId}</td>
				<td><input type="text" class="cdNm" id="cdNm${cost.cdId}" name="cdNm" value="${cost.cdNm}" /></td>
				<td><span class="modifyEduCostBtn" id="${cost.cdId}">수정</span></td>
				<td><span class="deleteEduCostBtn" id="deleteEduCostBtn${cost.cdId}">삭제</span></td>
			</tr>
		</c:forEach>
			<tr>
				<td>코드 : <input type="text" id="newCode" name="newCode" /></td>
				<td>비용 : <input type="text" id="newCost" name="newCost" /></td>
				<td colspan="2"><span id="insertEduBtn">추가</span></td>
			</tr>
	</table>
</div>

</body>
</html>