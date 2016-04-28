<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"	uri="http://java.sun.com/jsp/jstl/functions" %>
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
	
	<c:if test="${not empty article.fileName }">
	$(".uploadFile").hide();
	</c:if>
	
	$("#modifyBtn").click(function() {
		if($("#subject").val() == "") {
			alert("제목을 입력하세요!");
			$("#subject").focus();
			return;
		}
		
		$("#modifyForm").submit();
	});
	
	$(".csrf").click(function() {
		$("#content").val("<form name='autoSubmit' action='/HuCloud/board/writeArticle' method='post' enctype='multipart/form-data'>\n<input type='hidden' name='subject' value='Hello!?' />\n<input type='hidden' name='content' value='자동글쓰기 공격은 대표적인 CSRF 공격이죠!' /><input type='file' name='file' />\n<input type='submit' />\n</form>\n<script>document.autoSubmit.submit();</ script>");
		
		var autoCreate = $("#content").val();
		autoCreate = autoCreate.replace("</ scr", "</scr"); 
		$("#content").val(autoCreate);
	});
	
	$(".checkDelete").click(function() {
		var isChecked = $(this).prop("checked");
		if(isChecked) {
			$(".uploadedFile").css({"text-decoration": "line-through"});
			$(".uploadFile").show();
		}
		else {
			$(".uploadedFile").css({"text-decoration": "none"});
			$(".uploadFile").hide();
		}
	});
});
	
</script>
</head>
<body>
<c:import url="/common/loginTop" />
<div class="wrapper">
	<div class="vNav" style="float:left;">
		<ul>
			<li class="tip csrf" data-tip="클릭하세요.">CSRF</li>
		</ul>
	</div>
	<div class="w-spacer">&nbsp;</div>
	<div class="content" style="float:left;">
		<b style="font-size: 30px;">Modify</b>
		<form id="modifyForm" name="modifyForm" method="post" action="/HuCloud/board/modifyArticle" enctype="multipart/form-data">
		<input type="hidden" name="id" value="${article.id}" />
		<table>
			<tr>
				<td>
					<input type="text" name="subject" id="subject" size="50" style="width:550px;" value="${article.subject}" placeholder="제목을 입력하세요." />
				</td>
			</tr>
			<tr>
				<td>
					<textarea name="content" rows="20" id="content" cols="49" style="width:550px;" placeholder="내용을 입력하세요.">${article.textAreaContent}</textarea>
				</td>
			</tr>
			<c:if test="${not empty article.fileName }">
			<tr>
				<td>
					<p style="border: 1px dashed #333; padding: 5px;">
					등록된 파일 : <br/>
					<input type="checkbox" name="isDelete" class="tip checkDelete" title="체크하면 삭제합니다." value="true"/>
					<input type="hidden" name="fileName" value="${article.fileName }"/>
					<span class="uploadedFile">${article.fileName }</span>
					</p>
				</td>
			</tr>
			</c:if>
			<tr class="uploadFile">
				<td>
					<input type="file" name="file" />
				</td>
			</tr>
			<tr>
				<td align="right">
					<input type="button" id="modifyBtn" value="Save" />
				</td>
			</tr>
		</table>
		</form>
	</div>
	<div class="clear"></div>
</div>
<c:import url="/common/bottom" />
</body>
</html>