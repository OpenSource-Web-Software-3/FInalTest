<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String url1 = request.getContextPath()+"/Web-source"; %>

<!DOCTYPE html>
<html>
	<head>
        <meta charset="UTF-8">
        <title>LINS에 오신 걸 환영합니다</title>
        <!-- font awesome -->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.3/css/all.css" integrity="sha384-SZXxX4whJ79/gErwcOYf+zWLeJdY/qpuqC4cAa9rOGUstPomtqpuNWT9wdPEn2fk" crossorigin="anonymous">
        <link rel="stylesheet" href="<%=url1%>/css/main-style.css" />
    </head>
    <body>
        <!-- 4가지 서비스를 보여주는 부분 -->
        <section class="main">
            <!-- LINS 환영 멘트 -->
            <div class="web-title">LINS</div>
           <p class="comment">LINS에 오신 걸을 환영합니다😊</br>
           원하시는 메뉴를 클릭하시면 </br>해당 서비스를 이용하실 수 있습니다.</br></br>
           스케줄러는 로그인 후, 이용하실 수 있습니다.</p>
           <!-- 서비스 선택 부분 -->
           <ul class="service-list">
               <li class="menu first">
                   <a href="<%=url1%>/licenseList.jsp">
	                   <i class="far fa-file-alt"></i>
	                   <span>시험일정</span>
                   </a>
               </li>
               <li class="menu second">
                   <a href="<%=url1%>/community/commuList.jsp">
	                   <i class="fas fa-users"></i>
	                   <span>커뮤니티</span>
                   </a>
               </li>
               <li class="menu third">
                   <a href="<%=url1%>/scheduler.jsp">
	                   <i class="fas fa-calendar-alt"></i>
	                   <span>스케줄러</span>
                   </a>
               </li>
               <li class="menu fourth">
                   <a href="">
	                   <i class="fas fa-chalkboard"></i>
	                   <span>문의사항</span>
                   </a>
               </li>
           </ul>
        </section>
        <!-- 로그인 부분 -->
        <aside class="login">
            <!-- LINS -->
           <div class="title">LINS</div>
           <!-- 아이디 / 비밀번호 입력 -->
           <form class="login-form"action="">
               <input type="text" id="id" name="ID" placeholder="아이디"/>
               <input type="password" id="password" name="password" placeholder="비밀번호"/>
               <button class="loginBtn">로그인</button>
           </form>
           <!-- 회원가입 페이지로 이동 -->
           <a href="<%=url1%>/user-info/register.jsp" class="register">회원가입</a>
           <!-- 찾기 페이지로 이동 -->
           <a href="<%=url1%>/user-info/find.jsp" class="find">ID/password 찾기</a>
        </aside>
	</body>
</html>