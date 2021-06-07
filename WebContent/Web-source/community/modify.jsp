<%@page import="communication.CommunicationDTO"%>
<%@page import="communication.CommunicationDAO"%>
<%@page import="image.ImageDAO"%>
<%@page import="image.ImageDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.io.PrintWriter" %>
<% String  absolutePath = request.getContextPath()+"/Web-source";%>
<!DOCTYPE html>
<html>
	<head>
        <!-- css -->
        <link rel="stylesheet" href="<%= absolutePath %>/css/modify-style.css" />
        <link rel="stylesheet" href="<%= absolutePath%>/css/aside-style.css" />
	    <%@include file="../head-tags.jsp" %>
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
			int writingID = 0;
			if(request.getParameter("writingID")!=null) writingID = Integer.parseInt(request.getParameter("writingID"));
			
			//글 가져오기
			CommunicationDAO communicationDAO = new CommunicationDAO();
			CommunicationDTO commu = communicationDAO.getCommunication(writingID);
			if(commu == null) {
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('잘못된 접근입니다.')");
				script.println("history.back()");
				script.println("</script>");
				return;
			}else if(!commu.getID().equals(userID)){
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('수정 권한이 없습니다.')");
				script.println("history.back()");
				script.println("</script>");
				return;
			}
			
			//file 가져오기
			ImageDAO ImageDao = new ImageDAO();
			ImageDTO file = ImageDao.getFile(writingID, "file"); 
			ImageDTO document = ImageDao.getFile(writingID, "document"); 
			
		%>
       <!-- 게시글을 작성하는 영역 -->
       <section class="set-margin modify">
           <!-- 해당 페이지 title -->
           <div class="title">게시글 수정</div>
           <!-- 서브 카테고리 선택 : 글을 작성했을 때, 설정했던 서브 카테고리를 보여줄 수 있도록 해주세요!-->
           <select name="sub-category" id="sub-category">
               <option value="" class="default">주제</option>
               <option value="free">자유</option>
               <option value="review">시험 후기</option>
               <option value="study">공부</option>
           </select>
           <!-- 실제로 글을 작성는 부분 -->
           <form class="writing-part" method="POST" action="<%= absolutePath %>/modifyAction.do?writingID=<%=writingID %>" enctype = "multipart/form-data">
               <input type="hidden" name="category" value="<%=commu.getCategory()%>"/>
               <!-- 제목 입력 : 수정하려는 글의 제목을 불러와서 수정 -->
               <input type="text" id="title" name="title" placeholder="제목" value="<%=commu.getTitle()%>"/>
               <!-- 내용 입력 : 수정하려는 글의 내용을 불러와서 수정-->
               <textarea id="content" name="content" placeholder="내용을 입력하세요"><%=commu.getContent() %>
               </textarea>
               <!-- 사진 첨부 : 수정하려는 게시글의 파일도 불러올 수 있을까요?-->
               <div class="file-area">
                   <label for="">사진 첨부하기</label>
                   <input type="file" id="file" name="file" />
               </div>
               <!-- 교수님 피드백 수용으로 `문서 첨부`을 생성함 -->
               <div class="document-area">
                   <label for="">문서 첨부하기</label>
                   <input type="file" id="document" name="document" />
               </div>
               <div class="btn-area">
                   <button class="sendBtn" type="submit">수정하기</button>
               </div>
           </form>
       </section>
       <%@include file="../aside.jsp" %>
	</body>
</html>