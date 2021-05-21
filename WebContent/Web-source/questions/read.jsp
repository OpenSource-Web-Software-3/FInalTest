<%@page import="comment.CommentDTO"%>
<%@page import="comment.CommentDAO"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
// 			String userID = null;
// 			if (session.getAttribute("userID") != null) {
// 				userID = (String) session.getAttribute("userID");
// 			}
		
// 			int writingID = 0;
// 			if(request.getParameter("writingID")!=null){
// 				writingID = Integer.parseInt(request.getParameter("writingID"));
// 			}
		
// 			CommunicationDAO communicationDAO = new CommunicationDAO();
// 			CommunicationDTO commu = communicationDAO.getCommunication(writingID);
			
// 			String category = null;
// 			if(request.getParameter("category") != null) category = request.getParameter("category"); 
			
			
// 			if(writingID <= 0 || category == null || category.equals("")){
// 				PrintWriter script = response.getWriter();
// 				script.println("<script>");
// 				script.println("alert('잘못된 접근입니다')");
// 				script.println("history.back()");
// 				script.println("</script>");
// 			}
// 			else{
// 				communicationDAO.increaseView(writingID); //조회수 증가
// 			}
			
			
// 			//로그인한 아이디 스크랩 체크 
// 			CommuscrapDAO commuscrapDao = new CommuscrapDAO();
			
// 			boolean checkScrap;
// 			if(userID != null)
// 				checkScrap = commuscrapDao.checkCommuScrap(userID, writingID);
// 			else  
// 				checkScrap = false; //로그인 되어 있지 않다면 false
				
				
// 			/* 댓글 */
// 			CommentDAO commentDao = new CommentDAO();
// 			ArrayList<CommentDTO> commentList = new ArrayList<CommentDTO>();
			
// 			commentList = commentDao.getCommentList(writingID);
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
	                   <span class="nickname">userName</span>
	                   <!-- 작성일자 -->
	                   <span class="date">2021.05.23</span>
	               </div>
	               <!-- 게시글 제목 -->
	               <div class="title">클릭한 문의사항 제목</div>
	               <div class="wrap">
	                   <!-- counting 정보 -->
	                   <ul>
	                       <!-- 댓글 수  -->
	                       <li class="comment">
	                           <i class="far fa-comment-dots"></i>
	                           <span class="count">commentCount</span>
	                       </li>
	                   </ul>
	                   <!-- 해당 게시글을 쓴 사용자에게만 보여짐 -->
	                   <div class="btn-wrap">
	                       <button class="modifyBtn" onclick="location.href='<%=absolutePath_read %>/questions/modify.jsp'">수정</button>
	                       <button class="deleteBtn" onclick="location.href=''">삭제</button>
	                   </div>
	               </div>
	           </div>
	           <!-- 게시글 내용 -->
	           <div class="content">
	              클릭한 게시글의 문의내용
	           </div>
	           
	           
	           <!-- 작성된 댓글이 보여지는 부분 -->
	           <div class="comment-area">
	               <!-- comment sample -->
	               <%
	               for(int i = 1; i < 6; i++) { %>
	               <div class="comment">
	                   <div class="user-info">
	                       <span class="nickname">userNickname / 관리자</span>
	                       <span class="date">2021.05.26</span>
	                   </div>
	                   <pre class="content">문의사항에 대한 답변 / 사용자의 댓글</pre>
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