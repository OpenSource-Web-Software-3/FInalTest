<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="license.LicenseDAO"%>
<%@page import="java.util.ArrayList"%>
<% String absolutePath_aside = request.getContextPath()+"/Web-source"; %>

<%
	LicenseDAO licenseDao1 = new LicenseDAO();
	ArrayList<String> licencseNameList = licenseDao1.getLicenseNameList();	
%>

<%
    String userID1 = null;
    if(session.getAttribute("userID")!=null){
    	userID1 = (String) session.getAttribute("userID");
    }
%>
<div class="dark-background"></div>
<aside class="left-aside">
    
    <!-- 시험일정, 커뮤니티, 스케줄러, 문의사항을 하나의 tag로 묶음 -->
    <div class="menu">
	    <!-- aside title -->
	    <div class="project-title">
	        <a href="<%=absolutePath_aside%>/index.jsp">LINS</a>
	    </div>
        <!-- 시험일정 -->
	    <ul class="license">
	        <li class="subtitle">시험일정</li>
	        <%
	        	for(int i = 0; i < licencseNameList.size(); i++){
	        %>
		        <li class="items"><a href="<%=absolutePath_aside%>/licenseListAction.do?licenseName=<%=licencseNameList.get(i) %>"><%=licencseNameList.get(i) %></a></li>
			<%
	        	}
	        %>
	    </ul>
	    <!-- 커뮤니티 -->
	    <ul class="community">
	        <li class="subtitle">커뮤니티</li>
	        <%
	        	for(int i = 0; i < licencseNameList.size(); i++){
	        %>
		        <li class="items"><a href="<%=absolutePath_aside%>/commuListAction.do?category=<%=licencseNameList.get(i) %>"><%=licencseNameList.get(i) %></a></li>
			<%
	        	}
	        %>
	    </ul>
	    <!-- 스케줄러 -->
	    <ul class="subtitle" class="scheduler">
	        <a href="<%=absolutePath_aside%>/calendar/calendar.jsp">스케줄러</a>
	    </ul>
	    <!-- 문의사항 -->
	    <ul class="subtitle" class="questions">
	        <a href="<%=absolutePath_aside%>/questionListAction.do">문의사항</a>
	    </ul>
    </div>
    <!-- my page -->
    <div class="my-page">
        <!-- my page로 이동 -->
        <%if(userID1 == null){ %>
            <a href="#" class="login-btn" onclick="createLoginPop()">
                <i class="fas fa-sign-in-alt"></i>
                                         로그인
            </a>
            <%@ include file="./user-info/login-pop.jsp" %>
        <%}else{ %>
       <a href="<%=absolutePath_aside%>/my-page/myPage.jsp">
            <i class="far fa-user-circle"></i>
            My page
        </a>
        <%} %>
    </div>
    
</aside>

<div class="aside-hamburger-bar">
    <i class="fas fa-bars"></i>
        메뉴 보기        
</div>
<script>
function createLoginPop() {
	const loginPop = document.querySelector('.my-page .login-pop');
	loginPop.style.display = "flex";
}

$(".aside-hamburger-bar").click(function() {
    if($(".left-aside").css("display") == "none") {
           $(".left-aside").css("display", "flex");
           $(".dark-background").css("display", "flex");  
    }
    else if ($(".left-aside").css("display") == "flex") {
        $(".left-aside").css("display", "none");
        $(".dark-background").css("display", "none");  
    }
});
</script>