<%@page import="commuscrap.CommuscrapDAO"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="communication.CommunicationDTO"%>
<%@page import="communication.CommunicationDAO"%>
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
        <link rel="stylesheet" href="<%= absolutePath_read %>/css/read-style.css" />
        <link rel="stylesheet" href="<%= absolutePath_read %>/css/aside-style.css" />
		<title>게시글 제목</title>
	</head>
	<body>
		<%
			String userID = null;
			if (session.getAttribute("userID") != null) {
				userID = (String) session.getAttribute("userID");
			}
		
			int writingID = 0;
			if(request.getParameter("writingID")!=null){
				writingID = Integer.parseInt(request.getParameter("writingID"));
			}
		
			CommunicationDAO communicationDAO = new CommunicationDAO();
			CommunicationDTO commu = communicationDAO.getCommunication(writingID);
			
			String category = null;
			if(request.getParameter("category") != null) category = request.getParameter("category"); 
			
			if(writingID <= 0 || category == null || category.equals("")){
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('잘못된 접근입니다')");
				script.println("history.back()");
				script.println("</script>");
			}
			else{
				communicationDAO.increaseView(writingID); //조회수 증가
			}
			
			//로그인한 아이디 스크랩 체크 
			CommuscrapDAO commuscrapDao = new CommuscrapDAO();
			
			boolean checkScrap;
			if(userID != null)
				checkScrap = commuscrapDao.checkCommuScrap(userID, writingID);
			else  
				checkScrap = false; //로그인 되어 있지 않다면 false
				
			/* 댓글 */
			CommentDAO commentDao = new CommentDAO();
			ArrayList<CommentDTO> commentList = new ArrayList<CommentDTO>();
			
			commentList = commentDao.getCommentList(writingID);
		%>
		<script type="text/javascript">
		$(document).ready(function() {
			var checkScrap = '<%=checkScrap%>';
			if(checkScrap === 'true'){
				$(".scrap").addClass('active');
			}else{
				$(".scrap").removeClass('active');
			}
		});
		</script>
		
	   <!-- aside -->
	   <%@include file="../aside.jsp" %>
	   <!-- 게시글 내용 영역 -->
	   <section class="set-margin read">
	       <div class="test">
		       <!-- 게시글 상단  -->
	           <div class="title-part">
	               <!-- 게시글 작성 정보 -->
	               <div class="writer-info">
	                   <!-- 작성자 닉네임 -->
	                   <span class="nickname"><%=commu.getNickName() %></span>
	                   <!-- 작성일자 -->
	                   <span class="date"><%=commu.getCommuDate() %></span>
	               </div>
	               <!-- 게시글 제목 -->
	               <div class="title"><%=commu.getTitle() %></div>
	               <div class="wrap">
	                   <!-- counting 정보 -->
	                   <ul>
	                       <!-- 조회수 -->
	                       <li class="view">
	                           <i class="far fa-eye"></i>
	                           <span class="count"> <%=commu.getView()+1 %> </span>
	                       </li>
	                       <!-- 댓글 수  -->
	                       <li class="comment">
	                           <i class="far fa-comment-dots"></i>
	                           <span class="count"><%=commentDao.getAllComment(writingID) %></span>
	                       </li>
	                       <!-- 스크랩한 사용자 수 -->
	                       <li class="scrap" value=<%=commu.getWritingID() %>>
	                           <button>
	                               <i class="fas fa-star"></i> 
	                           </button>
	                           <span class="count" id="scrapCount"><%=commu.getScrapCount() %></span>
	                       </li>
	                   </ul>
	                   <!-- 해당 게시글을 쓴 사용자에게만 보여짐 -->
	                   <div class="btn-wrap">
	                       <button class="modifyBtn" onclick="location.href='<%=absolutePath_read %>/community/modify.jsp?writingID=<%=writingID%>'">수정</button>
	                       <button class="deleteBtn" onclick="location.href='<%=absolutePath_read %>/deleteAction.do?category=<%=category %>&writingID=<%=commu.getWritingID()%>'">삭제</button>
	                   </div>
	               </div>
	           </div>
	           <!-- 게시글 내용 -->
	           <div class="content">
	               <div class="user-content">
		               <!-- 사용자가 작성한 내용 -->
		              <%=commu.getContent().replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>") %>
	               </div>
	              <!-- 사용자가 업로드한 files -->
	              <div class="download-area">
		               <ul class="images">
	                       <!-- 사용자가 업로드한 이미지의 개수만큼 보이도록 설정 -->
	                        <%for(int i = 0; i < 13; i++) { %>
	                            <li class="user-img">
	                                <a href="<%= absolutePath_read%>/20210524_171000.png" download>
	                                    <img src="<%= absolutePath_read%>/20210524_171000.png" style="width: 100%; height: 100%;"/>
	                                </a>
	                            </li>
	                        <% }  %>                       
	                  </ul>
	                  <ul class="documents">
	                       <!-- 사용자가 업로드한 문서 개수만큼 보이도록 설정 -->
	                       <%for(int i = 0; i < 3; i++) { %>
	                            <li class="user-doc">
	                                <a href="#doc-path 작성" download>
	                                   <span>`문서이름.확장자 작성(전체 path를 보여주는 것은 불필요하다고 생각)`</span> 
	                                   <i class="fas fa-download"></i>
	                                 </a>
	                            </li>
	                        <% }  %>    
	                  </ul>
	              </div>
	           </div>
	           
	           
	           <!-- 작성된 댓글이 보여지는 부분 -->
	           <div class="comment-area">
	               <!-- comment sample -->
	               <%
	               for(int i = 1; i < commentList.size(); i++) { %>
	               <div class="comment">
	                   <div class="user-info">
	                       <span class="nickname"><%=commentList.get(i).getNickName() %></span>
	                       <span class="date"><%=commentList.get(i).getCommentDate() %></span>
	                   </div>
	                   <pre class="content"><%=commentList.get(i).getContent()%></pre>
	               </div>
	               <% } %>
	           </div>
           </div>
           <!-- 댓글 작성 -->
           <form class="type-comment" method="POST" action="<%=absolutePath_read %>/commentWriteAction.do?category=<%=category%>&writingID=<%=writingID %>" >
               <!-- 나중에 수정할 부분이 있음 / 입력한 값이 없으면 알아서 경고 띄움(추가적인 조건 삽입 필요 X) -->
               <textarea type="text" id="comment" name="content" placeholder="댓글을 작성해주세요." required="required">
               </textarea>
               <button class="submit">
                   <i class="far fa-paper-plane"></i>
               </button>
           </form>
	   </section>
	</body>
</html>