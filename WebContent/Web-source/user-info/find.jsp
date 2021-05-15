<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>
    <%String absolutePath_find = request.getContextPath()+"/Web-source";%>
<!DOCTYPE html>
<html>
	<head>
		<%@include file="../head-tags.jsp" %>
		<link rel="stylesheet" href="<%= absolutePath_find%>/css/user-info-style.css" />
		<title>ID / 비밀번호 찾기 </title>
	</head>
	<body>
		<%
		    String userID = null;
		    if(session.getAttribute("userID")!=null){
		        userID = (String) session.getAttribute("userID");
		    }
		    
		    if(userID != null){
		        PrintWriter script = response.getWriter();
		        script.println("<script>");
		        script.println("alert('현재 로그인 되어 있습니다.')");
		        script.println("history.back()");
		        script.println("</script>");
		    }
		%>
	
	   <section class="find">
	       <!-- 페이지 title -->
	       <div class="title">LINS</div>
	       <!-- ID 찾기 영역 -->
	       <div class="id">
	           <span class="title">ID 찾기</span>
	           <!-- ID를 찾을 때, 필요한 정보 양식 -->
	           <form action="<%=absolutePath_find %>/findInfoAction.do" method="POST">
	               <input type="text" class="name" name="name" placeholder="이름"/>
	               <input type="text" class="email" name="email" placeholder="이메일"/>
	               <input type="hidden" name="formType" value="ID"/>
	               <button class="sendBtn" type="submit">완료</button>
	           </form>
	       </div>
	       <!-- 비밀번호 찾기 영역 -->
	       <div class="password">
	           <span class="title">비밀번호 찾기</span>
	           <!-- 비밀번호를 찾을 때, 필요한 정보 양식 -->
	           <form action="<%=absolutePath_find %>/findInfoAction.do" method="POST">
	               <input type="text" class="id" name="ID" placeholder="아이디"/>
	               <input type="text" class="email" name="email" placeholder="이메일"/>
	               <input type="hidden" name="formType" value="PW"/>
	               <button class="sendBtn" type="submit">완료</button>
	           </form>
	       </div>
	       <a href="<%= absolutePath_find%>/index.jsp" class="login-page">로그인</a>
	   </section>
	</body>
</html>