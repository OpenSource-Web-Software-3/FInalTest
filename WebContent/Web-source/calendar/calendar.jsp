<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String  absolutePath = request.getContextPath()+"/Web-source";%>
<!DOCTYPE html>
<html>
	<head>
	    <%@include file="../head-tags.jsp" %>
		<link rel="stylesheet" href="<%= absolutePath%>/css/aside-style.css" />
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<link rel="stylesheet" href="<%= absolutePath%>/calendar/calendar-style.css" />
		<script src="<%= absolutePath%>/calendar/calendar-main.js" defer></script>
		<title>스케줄러</title>
	</head>
	<body>
	   <%@include file="../aside.jsp" %>
	   <section class="calendar set-margin">
	       <!-- 실제 달력 구현은 calendar-main.js 부분에 위치함 -->
		   <div id="calendarForm"></div>
	   </section>
	</body>
</html>