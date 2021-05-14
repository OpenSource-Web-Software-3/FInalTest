<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 나중에 상대경로로 인해 error가 발생하는 것을 방지하기 위해 -->
<%String absolutePath_reg = request.getContextPath()+"/Web-source";%>

<!DOCTYPE html>
<html>
	<head>
		<%@include file="../head-tags.jsp" %>
		<link rel="stylesheet" href="<%= absolutePath_reg%>/css/user-info-style.css" />
		<title>회원가입 | Register</title>
	</head>
	<body>
	   <section class="register">
	       <!-- 페이지 타이틀 -->
	       <a class="title" href="<%=absolutePath_reg%>/index.jsp">LINS</a>
	       <span class="sub-title">회원가입</span>
	       
	       <!-- 회원가입 시 필요한 정보를 입력하는 form -->
	       <form class="register-form" action="">
	           <!-- 첫 번째 영역 -->
	           <div class="first">
		           <input type="text" class="id" name="ID" placeholder="아이디"/>
	               <input type="password" class="password" name="password" placeholder="비밀번호"/>
	               <input type="text" class="nickname" name="nickname" placeholder="닉네임"/>
               </div>
               <!-- 두 번째 영역 -->
               <div class="second">
                    <input type="text" class="name" name="name" placeholder="실명"/>
                    <input type="text" class="phone-num" name="phoneNum" placeholder="휴대폰 번호"/>
                    <input type="text" class="email" name="email" placeholder="이메일"/>
               </div>
               <!-- 작성한 정보를 제출하는 btn -->
               <button class="sendBtn">가입하기</button>
	       </form>
	   </section>
	</body>
</html>