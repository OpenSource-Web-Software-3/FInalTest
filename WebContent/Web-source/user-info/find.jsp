<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%String absolutePath_find = request.getContextPath()+"/Web-source";%>
<!DOCTYPE html>
<html>
	<head>
		<%@include file="../head-tags.jsp" %>
		<link rel="stylesheet" href="<%= absolutePath_find%>/css/user-info-style.css" />
		<title>ID / 비밀번호 찾기 </title>
	</head>
	<body>
	   <section class="find">
	       <!-- 페이지 title -->
	       <div class="title">LINS</div>
	       <!-- ID 찾기 영역 -->
	       <div class="id">
	           <span class="title">ID 찾기</span>
	           <!-- ID를 찾을 때, 필요한 정보 양식 -->
	           <form action="">
	               <input type="text" class="name" name="name" placeholder="이름"/>
	               <input type="text" class="email" name="email" placeholder="이메일"/>
	               <button class="sendBtn">완료</button>
	           </form>
	       </div>
	       <!-- 비밀번호 찾기 영역 -->
	       <div class="password">
	           <span class="title">비밀번호 찾기</span>
	           <!-- 비밀번호를 찾을 때, 필요한 정보 양식 -->
	           <form action="">
	               <input type="text" class="id" name="ID" placeholder="아이디"/>
	               <input type="text" class="email" name="email" placeholder="이메일"/>
	               <button class="sendBtn">완료</button>
	           </form>
	       </div>
	       <a href="<%= absolutePath_find%>/index.jsp" class="login-page">로그인</a>
	   </section>
	</body>
</html>