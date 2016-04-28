<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${initParam.TITLE}</title>
<link rel="stylesheet" href="/HuCloud/resources/css/menu.css" />
<link rel="stylesheet" href="/HuCloud/resources/css/common.css" />
<script type="text/javascript" src="/HuCloud/resources/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="/HuCloud/resources/js/menu.js"></script>
<script type="text/javascript" src="/HuCloud/resources/js/tip.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$(".commandInjection").hide();
	
	$(".sql").click(function() {
		$(".sqlInjection").show();
		$(".commandInjection").hide();
	});
	
	$(".command").click(function() {
		$(".sqlInjection").hide();
		$(".commandInjection").show();
	});
});
	
</script>
</head>
<body>
<c:import url="/common/loginTop" />
<div class="wrapper">
	<div class="vNav" style="float:left;">
		<ul>
			<li class="sql">SQL Injection</li>
			<li class="command">Command Injection</li>
		</ul>
	</div>
	<div class="w-spacer">&nbsp;</div>
	<div class="content" style="float:left;">
		<div class="sqlInjection">
			<div class="spacer"></div>
			<div class="result">
				인증을 우회하기 위한 SQL Injection<br/>
				<ol>
					<li>회원 가입 화면에서 'admin', 'test', 'manager'와 같은 ID를 사용해 이미 등록된 ID를 찾는다.</li>
					<li>ID 입력 란에 [찾은 아이디' --]를 입력한다.</li>
					<li>Password 입력 란에 아무 값이나 입력한다.</li>
					<li>Execute 버튼을 클릭한다.</li>
					<li>[' or 1=1 --] 를 입력하고 Execute 버튼을 클릭한다.</li>
				</ol>
				<b>결과</b><br/>
				<c:if test="${not empty loginVO1}">
					<b>실행 쿼리문</b> : <br/>SELECT USER_ID, USER_NAME, USER_PASSWORD<br/>FROM USERS<br/>WHERE USER_ID = '<b>${loginVO1.id}</b>'<br/>AND USER_PASSWORD = '<b>${loginVO1.password}</b>';<br/>
					<br/>${result }
				</c:if>
			</div>
			<b>1. Oracle Injection (로그인 인증 우회)</b>
			<form method="post" action="/HuCloud/attack/injection/attack1">
				<table>
					<tr>
						<td>ID : <input type="text" class="tip" data-tip="SQL Injection 가능<br/>admin'-- 혹은 ' or 1=1" name="id" size="10" value="${loginVO1.id}" /></td>
						<td>Password : <input type="password" name="password" size="10" value="${loginVO1.password}" /></td>
						<td><input type="submit" value="Execute" /></td>
					</tr>
				</table>
			</form>
			<br/>
			<div class="spacer"></div>
			<div class="result">
				사용자 정보 보기 화면을 이용한 SQL Injection<br/>
				ID 항목에 본인의 ID를 적으면 정보가 나타난다. <br/><br/>
				테이블 목록 출력 : <br/>
				&nbsp;&nbsp;&nbsp;<b>admin' union all select table_name, '1', '2' from all_tables where table_name != 'a</b><br/>
				테이블 컬럼 목록 출력 :<br/>
				&nbsp;&nbsp;&nbsp;<b>ADMIN' UNION ALL SELECT COLUMN_NAME, DATA_TYPE, '1' FROM COLS WHERE TABLE_NAME = 'USERS'--<br/>
				<br/>결과<br/>
				<c:if test="${not empty memberInfo2}">
					실행 쿼리 : <br/>
					SELECT USER_ID, USER_NAME, USER_PASSWORD 
					<br/>FROM USERS 
					<br/>WHERE USER_ID = '<b>${userId}</b>' <br/><br/>
					<c:forEach items="${memberInfo2}" var="memberInfo">
						ID : ${memberInfo.id} / 이름 : ${memberInfo.userName} / 비밀번호 : ${memberInfo.password}<br/>
					</c:forEach>
				</c:if>
			</div>
			<b>2. Oracle Injection (사용자 정보 조회)</b>
			<form method="post" action="/HuCloud/attack/injection/attack2">
				<table>
					<tr>
						<td>ID : <input type="text" 
										class="tip" data-tip="SQL Injection 가능 : ' 만 작성하고 Execute 했을 때 에러 발생하면 Injection 가능</b>" 
										name="id" size="90" value="${loginVO2}" /></td>
						<td><input type="submit" value="Execute" /></td>
					</tr>
				</table>
			</form>
			<div class="spacer"></div>
			<div class="result">
				사용자 정보 보기 화면을 이용한 SQL Injection<br/>
				ID 항목에 본인의 ID를 적으면 정보가 나타난다. <br/><br/>
				<b>admin' union all select table_name from all_tables where table_name != 'a'--</b><br/> UNION ALL 아래의 컬럼을 에러가 안날 때까지 하나씩 늘린다.
				<br/><br/>결과<br/>
				<c:if test="${not empty memberInfo3}">
					실행 쿼리 : <br/>
					SELECT USER_ID, USER_NAME, USER_PASSWORD 
					<br/>FROM USERS 
					<br/>WHERE USER_ID = '<b>${userId}</b>' <br/><br/>
					<c:forEach items="${memberInfo3}" var="memberInfo">
						ID : ${memberInfo.id} / 이름 : ${memberInfo.userName} / 비밀번호 : ${memberInfo.password}<br/>
					</c:forEach>
				</c:if>
			</div>
			<b>3. Oracle Injection (사용자 정보 조회를 이용한 추출 컬럼 개수 확인)</b>
			<form method="post" action="/HuCloud/attack/injection/attack3">
				<table>
					<tr>
						<td>ID : <input type="text" class="tip" 
										data-tip="SQL Injection 가능 : ' 만 작성하고 Execute 했을 때 에러 발생하면 Injection 가능" 
										name="id" size="90" value="${loginVO2}" /></td>
						<td><input type="submit" value="Execute" /></td>
					</tr>
				</table>
			</form>
		</div>
		
		
		<div class="commandInjection">
			<div class="result">
				결과<br/>
				${result }
			</div>
			<div class="spacer"></div>
			<b>1. Command Injection</b>
			<form method="post" action="/HuCloud/attack/injection/commandAttack">
				<table>
					<tr>
						<td>
							실행할 프로그램을 선택하세요 : <select name="programs">
								<option value="1">계산기</option>
								<option value="2">메모장</option>
							</select>
						</td>
						<td><input type="submit" value="Execute" /></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div class="clear"></div>
</div>
<c:import url="/common/bottom" />
</body>
</html>