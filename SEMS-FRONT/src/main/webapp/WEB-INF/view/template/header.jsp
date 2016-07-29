<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<c:set value="${sessionScope._MEMBER_}" var="memberInfo" />
<c:set value="${sessionScope._MEMBER_TYPE_}" var="memberType" />

<div class="wrapper row1">
  <header id="header" class="clear"> 
    <div id="logo" class="fl_left">
      <h1><a href="/main">Kt'ds SEMS</a></h1>
    </div>
    <div class="fl_right">
      <ul class="inline">
        <li><i class="fa fa-phone"></i> ${memberInfo.phoneNumber}</li>
        <li><i class="fa fa-envelope-o"></i> ${memberInfo.email}</li>
        <li><i class="fa fa-power-off"></i> <a href="/logout"> logout </a> </li>
        <!-- 관리자라면 관리하기 버튼 보여주기 -->
        <c:if test="${memberType eq 'ADM' }">
        	<li><i class="fa fa-cog"></i> <a href="#" onclick="window.open('/comm/main','Place Detail','toolbar=no, location=no, status=no, menubar=no, scrollbars=no, resizeable=no, width=930, height=820');"> 관리하기 </a> </li>
        </c:if>
      </ul>
    </div>
  </header>
</div>
<div class="wrapper row2">
  <nav id="mainav" class="clear"> 
    <ul class="clear">
      <li class="active"><a href="/main">Home</a></li>
      <li><a href="/educationList">Education</a></li>
      <li><a href="/myTeamList">My Team</a></li>
      <li><a class="drop" href="/member/myPage">My Page</a>
      	<ul>
          <li><a href="/myPage/myReportList">과제 이력</a></li>
          <li><a href="/myPage/myQNAList">문의 이력</a></li>
          <li><a href="/member/myPage/educationHistory">교육참가 이력</a></li>
          <li><a href="/member/myPage/attendHistory">출결 이력</a></li>
          <li><a href="/member/loginHistory">로그인 이력</a></li>
          <li><a href="/member/myPc">내 컴퓨터 등록</a></li>
          <li><a href="/member/myPage/course">강의 게시판</a></li>
        </ul>
      </li>
      <li><a class="drop" href="#">Profile</a>
        <ul>
          <li><a href="/myPage/checkPassword">Edit Profile</a></li>
          <li><a href="/member/myPage/resignMember">Delete Account</a></li>
        </ul>
      </li>
    </ul>
  </nav>
</div>
