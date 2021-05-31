<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>
<% String  absolutePath = request.getContextPath()+"/Web-source";%>
<!DOCTYPE html>
<html>
	<head>
	<%
		String userID = null;
		if (session.getAttribute("userID") != null) {
			userID = (String) session.getAttribute("userID");
		}
		if (userID == null) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('로그인을 하세요')");
			script.println("history.back()");
			script.println("</script>");
			return;
		}
	
	%>
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
		   <div id="calendarForm"></div>
	   </section>
	</body>
</html>