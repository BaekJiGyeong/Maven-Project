<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/board/js/jquery-1.12.1.js"></script>
<script type="text/javascript" src="/board/js/sockjs-1.0.3.min.js"></script>
<script type="text/javascript">
	
	var sock = null;
	var message = {};
	
	$(document).ready(function() {
		sock = new SockJS("/board/echo-ws");
		sock.onopen = function() {
			
			message={};
			message.message = "반갑습니다~";
			message.type="all";
			message.to="all";
			
			
			sock.send(JSON.stringify(message));
		};
		sock.onmessage=function(ewt){
			//alert(ewt.data);
			$("#chatMessage").append(ewt.data+"<br/>");
			$("#chatMessage").scrollTop(99999999);
		};
		sock.onclose=function() {
			//sock.send("10.225.152.167 퇴장~~");
		}
		
		$("#sendMessage").click(function() {
			if( $("#message").val() != ""){
				
				message={};
				message.message = $("#message").val();
				message.type="all";
				message.to="all";
				
				var to =$("#to").val();
				if ( to != "" ) {
					message.type="one";
					message.to=to;
				}
				
				sock.send(JSON.stringify(message));
				$("#chatMessage").append("나 -> " + $("#message").val()+ "<br/>");
				$("#chatMessage").scrollTop(99999999);
				$("#message").val("");
			}
		});
	
	});
</script>
</head>
<body>

<input type="text" id="to"/>
<input type="text" id="message"/>
<input type="button" id="sendMessage" value="메시지 보내기"/>
<div id="chatMessage" style="overflow:auto; max-height: 500px;"></div>
</body>
</html>