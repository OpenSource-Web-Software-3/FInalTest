<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="QnAcomment.QnacommentDTO"%>
<%@page import="QnAcomment.QnacommentDAO"%>
<%@page import="QnA.QnADAO"%>
<%@page import="QnA.QnADTO"%>
<%@ page import="java.io.PrintWriter" %>
<!DOCTYPE html>
<% String absolutePath_read = request.getContextPath()+"/Web-source";%>
<html>
	<head>
		<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
		<script type="text/javascript" src="<%=absolutePath_read %>/js/scrap.js" defer></script>
	
		<%@include file="../head-tags.jsp" %>
        <!-- css -->
        <link rel="stylesheet" href="<%= absolutePath_read %>/css/ques-read-style.css" />
        <link rel="stylesheet" href="<%= absolutePath_read %>/css/aside-style.css" />
		<title>문의사항 읽기</title>
	</head>
	<body>
		<%
			String userID = null;
 			if (session.getAttribute("userID") != null) {
				userID = (String) session.getAttribute("userID");
 			}
		
			int QID = 0;
 			if(request.getParameter("QID")!=null){
 				QID = Integer.parseInt(request.getParameter("QID"));
			}
		
 			QnADAO qnADao = new QnADAO();
 			QnADTO qnaDto = qnADao.getQnA(QID);
			
 			String category = null;
			if(request.getParameter("category") != null) category = request.getParameter("category"); 
			
			
			if(QID <= 0 || userID == null){
				PrintWriter script = response.getWriter();
 				script.println("<script>");
				script.println("alert('잘못된 접근입니다')");
 				script.println("history.back()");
 				script.println("</script>");
 			}
			
				
 			/* 댓글 */
			QnacommentDAO commentDao = new QnacommentDAO();
 			ArrayList<QnacommentDTO> commentList = new ArrayList<QnacommentDTO>();
			
			commentList = commentDao.getCommentList(QID);
		%>
		
	   <!-- aside -->
	   <%@include file="../aside.jsp" %>
	   <!-- 문의사항 내용 영역 -->
	   <section class="set-margin question-read">
	       <div class="test">
		       <!-- 문의사항 상단  -->
	           <div class="title-part">
	               <!-- 문의사항 작성 정보 -->
	               <div class="writer-info">
	                   <!-- 작성자 닉네임 -->
	                   <span class="nickname"><%=qnaDto.getNickName() %></span>
	                   <!-- 작성일자 -->
	                   <span class="date"><%=qnaDto.getQDate()%></span>
	               </div>
	               <!-- 게시글 제목 -->
	               <div class="title"><%=qnaDto.getTitle() %></div>
	               <div class="wrap">
	                   <!-- counting 정보 -->
	                   <ul>
	                       <!-- 댓글 수  -->
	                       <li class="comment">
	                           <i class="far fa-comment-dots"></i>
	                           <span class="count"><%=commentDao.getAllComment(QID) %></span>
	                       </li>
	                   </ul>
	                   <!-- 해당 게시글을 쓴 사용자에게만 보여짐 -->
	                   <div class="btn-wrap">
	                       <button class="modifyBtn" onclick="location.href='<%=absolutePath_read %>/questions/modify.jsp?QID=<%=QID%>'">수정</button>
	                       <button class="deleteBtn" onclick="location.href='<%=absolutePath_read %>/QNAdeleteAction.do?QID=<%=QID%>'">삭제</button>
	                   </div>
	               </div>
	           </div>
	           <!-- 게시글 내용 -->
	           <div class="content">
	           	  <%= qnaDto.getContent().replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>") %>
	           </div>
	           
	           
	           <!-- 작성된 댓글이 보여지는 부분 -->
	           <div class="comment-area">
	               <!-- comment sample -->
	               <%
	               for(int i = 0; i < commentList.size(); i++) { %>
	               <div class="comment">
	                   <div class="user-info">
	                   <!--  admin / 관리자 -->
	                       <span class="nickname"><%=commentList.get(i).getID() %></span>
	                       <span class="date"><%=commentList.get(i).getqCommentDate() %></span>
	                   </div>
	                   <pre class="content"><%=commentList.get(i).getContent() %></pre>
	               </div>
	               <% } %>
	           </div>
           </div>
           <!-- 댓글 작성 -->
           <form class="type-comment" method="POST" action="" >
               <!-- 나중에 수정할 부분이 있음 / 입력한 값이 없으면 알아서 경고 띄움(추가적인 조건 삽입 필요 X) -->
               <textarea type="text" id="content" name="content" placeholder="댓글을 작성해주세요." required="required">
               </textarea>
               <button class="submit">
                   <i class="far fa-paper-plane"></i>
               </button>
           </form>
	   </section>
	</body>
</html>