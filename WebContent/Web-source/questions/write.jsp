<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>
<% String absolutePath_write = request.getContextPath()+"/Web-source"; %>
<!DOCTYPE html>
<html>
	<head>
        <!-- css -->
        <link rel="stylesheet" href="<%= absolutePath_write %>/css/ques-write-style.css" />
        <link rel="stylesheet" href="<%= absolutePath_write %>/css/aside-style.css" />
		<%@include file="../head-tags.jsp" %>
		<title>문의사항 작성하기</title>
	</head>
	<body>
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
			}
			
		%>
	   <!-- 문의사항을 작성하는 영역 -->
	   <section class="set-margin ques-write">
	       <!-- 해당 페이지 title -->
	       <div class="title">문의사항 작성</div>
	       <!-- 실제로 글을 작성는 부분 -->
	       <form class="writing-part" method="POST" action="<%= absolutePath_write %>/QNAwriteAction.do" enctype = "multipart/form-data">
	           <!-- 제목 입력 -->
	           <input type="text" id="title" name="title" placeholder="제목"/>
	           <!-- 내용 입력 -->
	           <textarea id="content" name="content" placeholder="내용을 입력하세요">
	           </textarea>
		       <!-- 사진 첨부 -->
		       <div class="file-area">
		           <label for="">사진 첨부하기</label>
		           <input type="file" id="file" name="QNAfile" />
		       </div>
		       <!-- 교수님 피드백 수용으로 `문서 첨부`을 생성함 -->
		       <div class="document-area">
	               <label for="">문서 첨부하기</label>
	               <input type="file" id="document" name="QNAdocument" />
	           </div>
	           <div class="btn-area">
		           <button class="sendBtn" type="submit">완료</button>
	           </div>
	       </form>
	   </section>
	   <!-- aside -->
	   <%@include file="../aside.jsp" %>
	</body>
</html>