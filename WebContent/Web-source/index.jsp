<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String url1 = request.getContextPath()+"/Web-source"; %>

<!DOCTYPE html>
<html>
	<head>
         <%@include file="./head-tags.jsp" %>
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
                   <a href="<%=url1%>/licenseListAction.do?licenseName=기술사">
	                   <i class="far fa-file-alt"></i>
	                   <span>시험일정</span>
                   </a>
               </li>
               <li class="menu second">
                   <a href="<%=url1%>/commuListAction.do?category=기술사">
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
                   <a href="<%=url1%>/questions/questionList.jsp">
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
               
	           <!-- 회원가입 페이지로 이동 -->
	           <a href="<%=url1%>/user-info/register.jsp" class="register">회원가입</a>
	           <!-- 찾기 페이지로 이동 -->
	           <a href="<%=url1%>/user-info/find.jsp" class="find">ID/password 찾기</a>
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
        </aside>
	</body>
</html>