<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/board/js/jquery-1.12.1.js" ></script>
<script type="text/javascript" src="/board/js/sockjs-1.0.3.min.js" ></script>
<script type="text/javascript">
	
	var chatSock = null;
	
	
	var message = {};
	
	$(document).ready(function() {
		
		chatSock = new SockJS("/board/echo-paint");	
		
		var ctx = null;
		var isEditable = false;
		
		
		var isDown = false;
		var prevX = 0;
		var prevY = 0;
		
		// 마우스를 눌렀을 때
		$("#paint").mousedown(function(e) {
			
			if ( !isEditable) {
				return;
			}
			
			var c = document.getElementById("paint");
			ctx = c.getContext("2d");
			
			ctx.strokeStyle=$("#color").val();
			ctx.beginPath();
			isDown = true;
			prevX = e.pageX;
			prevY = e.pageY;
			console.log(e.pageX);
		});
		
		var point = {};
		
		// 마우스를 움직일 때
		$("#paint").mousemove(function(e) {
			if ( isDown ) {
				point.prevX = prevX;
				point.prevY = prevY;
				point.nowX = e.pageX; 
				point.nowY = e.pageY; 
				point.color = $("#color").val();
				
				chatSock.send(JSON.stringify(point));
				
				ctx.moveTo(prevX, prevY);
				ctx.lineTo(e.pageX,e.pageY);
				ctx.stroke();
				
				prevX = e.pageX;
				prevY = e.pageY;
			}
		});
		
		// 마우스를 뗄 때
		$("#paint").mouseup(function(e) {
			
			if ( !isEditable) {
				return;
			}
			
			isDown = false;
			ctx.closePath();
			console.log(e.pageX);
		});
		
		$("#fill").click(function(){
			
			if ( !isEditable) {
				return;
			}
			
			var c = document.getElementById("paint");
			ctx = c.getContext("2d");
			
			ctx.beginPath();
			ctx.rect(0, 0, 500, 500);
			ctx.fillStyle=$("#color").val();
			ctx.fill();
			ctx.closePath();
			
			var fill = {};
			fill.node = "fill";
			fill.color = $("#color").val();
			
			chatSock.send(JSON.stringify(fill));
			
		})
		
		
		chatSock.onmessage = function(ewt) {
			
			if (evt.data="!@#OK" || evt.data == "!@#NO"){
				if(evt.data=="!@#OK"){
					isEditable=true;
				}
				else {
					isEditable=false;
				}
			}
			
			var drawData = JSON.parse(ewt.data);
			
			var c = document.getElementById("paint");
			var otherCtx = c.getContext("2d");
			
			if ( drawData.node != undefined && drawData.node != "") {
				otherCtx.rect(0, 0, 500, 500);
				otherCtx.fillStyle = drawData.color;
				otherCtx.fill();
				otherCtx.closePath();
				
				return;
			}
			
			otherCtx.strokeStyle= drawData.color;
			otherCtx.beginPath();
			otherCtx.moveTo(drawData.prevX, drawData.prevY);
			otherCtx.lineTo(drawData.nowX, drawData.nowY);
			otherCtx.stroke();
			otherCtx.closePath();
			
		};
		
	});

</script>
</head>
<body style="margin: 0px; padding: 0px;">
	<div style="width:500px; float:left;">
		<canvas id="paint" width="500" height="500" style="border: 1px solid #333;"></canvas>
		<br/>
		<input type="color" id="color" value="#000000"/>
		<input type="button" id="fill" value="채우기"/>
	</div>
	<div style="width:400px; float:left;">
		<c:import url="/chat"/>
	</div>
</body>
</html>











