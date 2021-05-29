<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String url1 = request.getContextPath()+"/Web-source"; %>

<!DOCTYPE html>
<html>
	<head>
         <%@include file="./head-tags.jsp" %>
         <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
        <title>LINS에 오신 걸 환영합니다</title>
    </head>
    <body>
    	<%
    		String userID = null;
    		if(session.getAttribute("userID")!=null){
    			userID = (String) session.getAttribute("userID");
    		}
    	%>
        <!-- 4가지 서비스를 보여주는 부분 -->
        <section class="main">
            <!-- LINS 환영 멘트 -->
           <p class="comment">LINS에 오신 걸을 환영합니다😊</br>
           원하시는 메뉴를 클릭하시면 </br>해당 서비스를 이용하실 수 있습니다.</br></br>
           스케줄러는 로그인 후, 이용하실 수 있습니다.</p>
           <!-- 서비스 선택 부분 -->
           <ul class="service-list">
               <li class="menu first">
                   <a href="<%=url1%>/licenseListAction.do?licenseName=정보처리기사">
	                   <i class="far fa-file-alt"></i>
	                   <span>시험일정</span>
                   </a>
               </li>
               <li class="menu second">
                   <a href="<%=url1%>/commuListAction.do?category=정보처리기사">
	                   <i class="fas fa-users"></i>
	                   <span>커뮤니티</span>
                   </a>
               </li>
               <li class="menu third">
                   <a href="<%=url1%>/calendar/calendar.jsp">
	                   <i class="fas fa-calendar-alt"></i>
	                   <span>스케줄러</span>
                   </a>
               </li>
               <li class="menu fourth">
                   <a href="<%=url1%>/questionListAction.do">
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
           <%if(userID == null){ %>
           <!-- 아이디 / 비밀번호 입력 (로그인을 하지 않았을 때 보여짐) - display: none or block으로 조정 -->
           <form class="login-form" method="POST" action="loginAction.do" style="display:block">
               <input type="text" id="id" name="ID" placeholder="아이디"/>
               <input type="password" id="password" name="password" placeholder="비밀번호"/>
               <button type="submit" class="loginBtn">로그인</button>
               
               <div>
		           <!-- 회원가입 페이지로 이동 -->
		           <a href="<%=url1%>/user-info/register.jsp" class="register">회원가입</a>
		           <!-- 찾기 페이지로 이동 -->
		           <a href="<%=url1%>/user-info/find.jsp" class="find">ID/password 찾기</a>
               </div>
           </form>
           <%}else{ %>
           <!-- 로그아웃 (로그인을 한 후에 보여짐) - display: none or block으로 조정 -->
           <form class="logout-form" method="POST" action="logoutAction.do" style="display:block">
                <span class="comment"><%=userID %></span>
                <ul class="menu">
                    <li class="my-page"><a href="<%=url1%>/my-page/myPage.jsp">내 정보</a></li>
                    <li class="logout"><button type="submit">로그아웃</button></li>
                </ul>
           </form>
           <%} %>
           <button class="change-mode dark">Bright Mode로 전환</button>
        </aside>
	</body>
</html>

<script>
const modeChangeBtn = document.querySelector(".change-mode");

$(".change-mode").click(function() {
    if($(this).is(".dark")) {
    	$(this).removeClass('dark');
    	$(this).addClass('bright');
    	document.documentElement.style.setProperty('--current-special-font', 'var(--bright-special-font)');
        document.documentElement.style.setProperty('--current-background', 'var(--bright-background)');
        document.documentElement.style.setProperty('--current-aside', 'var(--bright-aside)');
        document.documentElement.style.setProperty('--current-basic-font', 'var(--bright-basic-font)');
        document.documentElement.style.setProperty('--current-btn', 'var(--pastel-yellow)');
        document.documentElement.style.setProperty(' --current-btn-hover', 'var(--pastel-red)');
        
        localStorage.setItem("theme","bright");
    }
    else if($(this).is(".bright")) {
    	$(this).removeClass('bright');
        $(this).addClass('dark');
        
        document.documentElement.style.setProperty('--current-special-font', 'var(--dark-special-font)');
        document.documentElement.style.setProperty('--current-background', 'var(--dark-background)');
        document.documentElement.style.setProperty('--current-aside', 'var(--dark-aside)');
        document.documentElement.style.setProperty('--current-basic-font', 'var(--dark-basic-font)');
        document.documentElement.style.setProperty('--current-btn', 'var(--pastel-skyblue)');
        document.documentElement.style.setProperty(' --current-btn-hover', 'var(--pastel-blue)');
        
        localStorage.setItem("theme","dark");
    }
});
</script>