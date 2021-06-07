<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String url1 = request.getContextPath()+"/Web-source"; %>

<div class="login-pop">
	<section>
		<form class="login-form" method="POST" action="loginAction.do">
		   <span class="login-pop-title">LINS</span>
		   <input type="text" id="id" name="ID" placeholder="아이디"/>
		   <input type="password" id="password" name="password" placeholder="비밀번호"/>
		   <button type="submit" class="loginBtn" onclick="window.location.reload()">로그인</button>
		   
		   <div>
		       <!-- 회원가입 페이지로 이동 -->
		       <a href="<%=url1%>/user-info/register.jsp" class="register">회원가입</a>
		       <!-- 찾기 페이지로 이동 -->
		       <a href="<%=url1%>/user-info/find.jsp" class="find">ID/password 찾기</a>
		    </div>
		</form>
	</section>
</div>

<script>

</script>
