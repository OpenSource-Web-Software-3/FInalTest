<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="license.LicenseDAO"%>
<%@page import="java.util.ArrayList"%>
<% String absolutePath_aside = request.getContextPath()+"/Web-source"; %>

<%
	LicenseDAO licenseDao = new LicenseDAO();
	ArrayList<String> licencseNameList = licenseDao.getLicenseNameList();	
%>
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
	        <li class="items"><a href="#">licenseName1</a></li>
	        <li class="items"><a href="#">licenseName2</a></li>
	        <li class="items"><a href="#">licenseName3</a></li>
	    </ul>
	    <!-- 스케줄러 -->
	    <ul class="subtitle" class="scheduler">
	        <a href="<%=absolutePath_aside%>/scheduler.jsp">스케줄러</a>
	    </ul>
	    <!-- 문의사항 -->
	    <ul class="subtitle" class="questions">
	        <a href="#">문의사항</a>
	    </ul>
    </div>
    <!-- my page -->
    <div class="my-page">
        <!-- my page로 이동 -->
        <a href="<%=absolutePath_aside%>/my-page/myPage.jsp">
            <i class="far fa-user-circle"></i>
            My page
        </a>
    </div>
</aside>