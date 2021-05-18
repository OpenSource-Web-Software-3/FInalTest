<%@page import="license.LicenseDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<% String absolutePath_license = request.getContextPath()+"/Web-source"; %>
<!DOCTYPE html>
<html>
	<head>
		<%@include file="./head-tags.jsp" %>
		<!-- css -->
		<link rel="stylesheet" href="<%= absolutePath_license %>/css/license-list-style.css" />
		<link rel="stylesheet" href="<%= absolutePath_license %>/css/aside-style.css" />
		<!-- 현재 사용자가 보고 있는 자격증 이름을 `licenseName`에 삽입하고 싶습니다! -->
		<title>시험일정 | licenseName</title>
	</head>
	<body>
		<%
			String licenseName = null;
			String licencseName = (String) request.getAttribute("licenseName");
			ArrayList<LicenseDTO> licenseList = (ArrayList<LicenseDTO>) request.getAttribute("licenseList");
		%>
		
	   <!-- aside-->
	   <%@include file= "./aside.jsp" %>
	   <section class="license-list">
		   <!-- 해당 자격증의 시험일정을 보여주는 부분 -->
		   <div class="license-name"><%=licencseName %></div>
		   <ul class="list">
		      <!-- 임시적으로 9개로 제한 (아마 9개로 갈 거 같습니다.) -->
		      <% for(int i = 0; i < licenseList.size(); i++) { %>
	           <li class="items">
	               <!-- 시험일정 정보  -->
		           <a href="" class="url">	           
		               <div class="text-area">
		                   <span class="date"><%=licenseList.get(i).getLicenseDate() %></span>
		                   <span class="time"><%=licenseList.get(i).getLicenseTime() %></span>
		               </div>
		           </a>
		           <!-- 스크랩 버튼 -->
	               <button class="scrapBtn">
	                   <i class="fas fa-star"></i>
	               </button>
	           </li>
	           <% } %>
	       </ul>   		
	   </section>
	</body>
</html>