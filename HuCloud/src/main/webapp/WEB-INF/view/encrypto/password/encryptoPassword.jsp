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
	$(".checkSalt").click(function() {
		$.post('<c:url value="/encrypto/password/checksalt"/>', {}, function(data) {
			if(data == "true") {
				alert("Salt 컬럼이 존재합니다!");
			}
			else {
				if(confirm("Salt 컬럼이 존재하지 않습니다.\n새로 생성하시겠습니까?")) {
					$.post('<c:url value="/encrypto/password/makesalt"/>', {}, function(data1) {
						if(data1 = "true") {
							alert("컬럼 생성 및 패스워드 변경을 완료했습니다.");
						}
						else {
							alert("에러가 발생했습니다. 강사에게 문의하세요!");
						}
					});
				}
			}
			
		});
	});
});
</script>
</head>
<body>
<c:import url="/common/loginTop" />
<div class="wrapper">
	<div class="vNav" style="float:left;">
		<ul>
			<li class="tip checkSalt">Check Salt</li>
		</ul>
	</div>
	<div class="w-spacer">&nbsp;</div>
	<div class="content" style="float:left;">
		<div class="result">
			<pre>사용자의 비밀번호는 모두 강력한 암호화 기법을 사용해 암호화 해야한다.
일반적인 방법으로 단방향 암호화 기법인 MD5를 사용하는데, 이 알고리즘은 출현한지 오래되어 이미 해킹이 가능한 수준이 되어
더 이상 안전한 방법이 아니다.

일반적으로 AES / SHA / DES를 많이 사용하는데 
DES 알고리즘은 AES 알고리즘이 출현한 이후 더이상 사용하지 않는다.

그런데, 위 AES / SHA / DES 는 메시지 인증 및 무결성 체크를 위한 알고리즘이지, 패스워드 암호화를 위한 알고리즘이 아니다.
따라서, 이 알고리즘들을 패스워드 암호화에 사용한다면 "복호화"가 가능하다는 취약점이 있다.

원래 위 3개 알고리즘은 "단방향"의 복호화가 불가능한 암호화 알고리즘이지만, 이미 많은 해커들이 "복호화"를 시도했고,
그 시도는 성공을 이룬지 오래되었기 때문에 암호화에 사용해서는 안된다.

암호화에 사용되는 기법은 http://helloworld.naver.com/helloworld/textyle/318732 에서 소개하고 있는
PBKDF2 / bcrypt / scrypt 중 하나를 사용한다.

본 예제는 bscrypt를 사용하고 있다.

좌측 Check Salt 를 클릭하면 Salt를 생성하고
기존의 패스워드에 Salt를 추가해 암호화(Digest) 한다.

EncryptoPasswordController.makeSalt
- Salt 컬럼 추가
- Salt 생성
- Salt 값을 기존 Password에 추가하여 Digest

Salt 값이 추가되면
Login의 로직도 함께 변경되어야 한다.</pre>
		</div>
	</div>
	<div class="clear"></div>
</div>
<c:import url="/common/bottom" />
</body>
</html>
