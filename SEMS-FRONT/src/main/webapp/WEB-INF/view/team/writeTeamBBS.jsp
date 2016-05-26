<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html >
<html>
<head>
<script type="text/javascript" src="/resources/js/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>팀 게시판 댓글</title>
</head>
<body>
	
	<form:form commandName="teamBBSVO" method="POST" action="/team/teamBBS/doWrite" enctype="multipart/form-data">
		<table>
			<tr>
				<td>	
					공지사항 : <input type="checkbox" id="isNotice" name="isNotice" >
				</td>
			</tr>
			<tr>
				<td>
					<input type="text" name="title" id="title" size="50" style="width:550px;" placeholder="제목을 입력하세요."  />
				</td>
			</tr>
			<tr>
				<td>
					<textarea name="descript" rows="20" id="descript" cols="49" style="width:550px;" placeholder="내용을 입력하세요." ></textarea>
				</td>
			</tr>
			<tr>
				<td>
					<input type="file" name="file" />
				</td>
			</tr>
			<tr>
				<td align="right">
					<input type="submit" value="저장" />
				</td>
			</tr>
		</table>
	</form:form>
	
	
</body>
</html>