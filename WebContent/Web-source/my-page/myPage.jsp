<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="user.*" %>
<%@ page import ="java.io.PrintWriter" %>
<% String  absolutePath = request.getContextPath()+"/Web-source";%>
    
<!DOCTYPE html>
<html>
	<head>
        <!-- css -->
        <link rel="stylesheet" href="<%= absolutePath %>/css/mypage-style.css" />
        <link rel="stylesheet" href="<%= absolutePath %>/css/aside-style.css" />
		<%@include file="../head-tags.jsp" %>
        <!-- js -->
        <script src="<%= absolutePath%>/js/popUp.js" defer></script>
		<title>마이 페이지</title>
	</head>
	<body>
		<%

			String userID = null;
			if(session.getAttribute("userID") != null){
				userID = (String) session.getAttribute("userID");
			}
			if (userID == null) {
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('로그인을 하세요')");
				script.println("history.back()");
				script.println("</script>");
				return;
			}
			
			UserDTO userDto = new UserDTO();
			UserDAO userDao = new UserDAO();
			userDto = userDao.getUser(userID);
		%>
	
	
	   
	   <!-- my page 영역 -->
	   <section class="set-margin my-page">
	        <!-- 페이지 타이틀 -->
            <div class="title">My page</div>
            <!-- 각 영역 구분 -->
            <ul class="domain">
                <!-- 사용자 정보 영역 -->
                <li class="user-info">
                    <!-- 사용자 그림 -->
                    <i class="fas fa-user-alt"></i>
                    <!-- 실제 정보 -->
                    <div class="info-wrap">
                        <span class="nickname"><%=userDto.getNickName() %></span>
                        <span class="other"><%=userDto.getName()%> | <%=userID %></span>
                    </div>
                </li>
                <!-- 개인정보 영역 -->
                <li class="personal-info">
                    <ul>
                        <li class="sub-title">개인정보</li>
                        <li class="password" onclick="CreateChangePop()">비밀번호 변경</li>
                        <li class="email" onclick="CreateChangePop()">이메일 변경</li>
                    </ul>
                </li>
                <!-- 스크랩 확인 영역 -->
                <li class="scrap-check">
                    <ul>
                        <li class="sub-title">스크랩 확인</li>
                        <li class="license"><a href="<%= absolutePath%>/my-page/scrapLicense.jsp">시험일정</a></li>
                        <li class="commu"><a href="<%= absolutePath%>/my-page/scrapCommu.jsp">커뮤니티</a></li>
                    </ul>
                </li>
                <!-- 작성한 게시글 영역 -->
                <li class="writing">
                    <ul>
                        <li class="sub-title">작성한 게시글</li>
                        <li class="commu"><a href="<%= absolutePath%>/my-page/user-writing-list.jsp">커뮤니티</a></li>
                    </ul>
                </li>
                <!-- 회원탈퇴 영역 -->
                <li class="withdrawal">
                    <div class="sub-title" onclick="CreatewithdrawalPop()">회원탈퇴</div>
                </li>
            </ul>
	   </section>
	   <!-- aside -->
	   <%@include file="../aside.jsp" %>
	   <%@include file="./popUp.jsp" %>
	</body>
</html>
<!-- 간단한 js라서 따로 파일 생성x -->
<script>
	const changeSection = document.querySelector('.change');
    const withdrawal = document.querySelector('.change .withdrawal'); 
    const identification = document.querySelector('.change div.identification');
    const passwordSection = document.querySelector('.change div.password');
    const emailSection = document.querySelector('.change div.email');
    
    /* 본인인증 & 변경 관련 팝업창 띄움 */	
	function CreateChangePop() {
	    changeSection.style.display = "flex";
	    identification.style.display = "flex";
	    withdrawal.style.display = "none";
	}
	/* 본인인증 & 변경 관련 팝업창 닫음 */
	function DeleteCheckPop() {
	    changeSection.style.display = "none";
	    passwordSection.style.display = "none";
	    emailSection.style.display = "none";
	    withdrawal.style.display = "none";
	}
	
	function CreatewithdrawalPop() {
		changeSection.style.display = "flex"; 
		identification.style.display = "none";
		withdrawal.style.display = "flex";
	}
</script>