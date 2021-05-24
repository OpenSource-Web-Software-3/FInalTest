<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="QnA.*"%>
<%@page import="image.*"%>
<%@page import="java.io.PrintWriter" %>
<% String  absolutePath = request.getContextPath()+"/Web-source";%>
<!DOCTYPE html>
<html>
	<head>
	   <%@include file="../head-tags.jsp" %>
        <!-- css -->
        <link rel="stylesheet" href="<%= absolutePath %>/css/ques-modify-style.css" />
        <link rel="stylesheet" href="<%= absolutePath%>/css/aside-style.css" />
		<title>글 수정하기</title>
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
				return;
			}
			int QID = 0;
			if(request.getParameter("QID")!=null) QID = Integer.parseInt(request.getParameter("QID"));
			
			//글 가져오기
			QnADAO qnaDao = new QnADAO();
			QnADTO qnaDto = qnaDao.getQnA(QID);
			if(qnaDto == null) {
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('잘못된 접근입니다.')");
				script.println("history.back()");
				script.println("</script>");
				return;
			}else if(!qnaDto.getID().equals(userID)){
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('수정 권한이 없습니다.')");
				script.println("history.back()");
				script.println("</script>");
				return;
			}
			
			//file 가져오기
			ImageDAO ImageDao = new ImageDAO();
			ImageDTO QNAfile = ImageDao.getFile(QID, "QNAfile"); 
			ImageDTO QNAdocument = ImageDao.getFile(QID, "QNAdocument"); 
			
		%>
	
       <%@include file="../aside.jsp" %>
       <!-- 문의사항을 작성하는 영역 -->
       <section class="set-margin ques-modify">
           <!-- 해당 페이지 title -->
           <div class="title">작성한 문의사항 수정</div>
           <!-- 실제로 글을 작성는 부분 -->
           <form class="writing-part" method="POST" action="<%= absolutePath %>/QNAmodifyAction.do?QID=<%=QID%>" enctype = "multipart/form-data">
               <!-- 제목 입력 : 수정하려는 문의사항의 제목을 불러와서 수정 -->
               <input type="text" id="title" name="title" placeholder="제목" value="<%=qnaDto.getTitle()%>"/>
               <!-- 내용 입력 : 수정하려는 문의사항의 내용을 불러와서 수정-->
               <textarea id="content" name="content" placeholder="내용을 입력하세요"><%=qnaDto.getContent() %>
               </textarea>
               <!-- 사진 첨부 : 수정하려는 게시글의 파일도 불러올 수 있을까요?-->
               <div class="file-area">
                   <label for="">사진 첨부하기</label>
                   <input type="file" id="file" name="QNAfile"/>
               </div>
               <!-- 교수님 피드백 수용으로 `문서 첨부`을 생성함 -->
               <div class="document-area">
                   <label for="">문서 첨부하기</label>
                   <input type="file" id="document" name="QNAdocument" />
               </div>
               <div class="btn-area">
                   <button class="sendBtn" type="submit">수정하기</button>
               </div>
           </form>
       </section>
	</body>
</html>