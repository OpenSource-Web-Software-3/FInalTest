<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String  absolutePath_ques = request.getContextPath()+"/Web-source";%>
<!DOCTYPE html>
<html>
	<head>
		<%@include file="../head-tags.jsp" %>
        <!-- css -->
        <link rel="stylesheet" href="<%= absolutePath_ques %>/css/question-list-style.css" />
        <link rel="stylesheet" href="<%= absolutePath_ques %>/css/aside-style.css" />
		<title>문의사항</title>
	</head>
	<body>
	   <!-- aside -->
	   <%@include file="../aside.jsp" %>
	   <!-- 게시글을 보여주는 영역 -->
	   <section class="set-margin question">
	       <div class="title">문의사항</div>
	       <span class="comment">문의사항은 다른 사용자들과 독립적인 공간입니다.</br>
	       오직 사용자께서 작성하신 문의글만 보여지고, 다른 사용자가 작성한 문의는 보이지 않습니다.</br>
	       그리고 작성하신 문의는 관리자만 열람할 수 있고, 문의에 대한 답변은 댓글로 작성해드립니다😊</span>
	       <!-- 정렬, 검색어, 글쓰기를 하나의 div로 묶음 -->
	       <div class="input-wrap">
	           <!-- 검색어, 정렬 기준 선택 -->
	           <ul class="text">
	               <li>
	                   <!-- 로그인한 사용자가 작성한 글만 보이므로, 작성자 & 스크랩은 필요X -->
	                   <select name="sortStandard" id="sort-standard">
	                       <option value="" class="default">정렬기준</option>
	                       <option value="title">제목</option>
                           <option value="latest">최신</option>                       
	                   </select>
	               </li>
	               <li>
	                   <input type="text" id="search" placeholder="검색어 입력"/>
	               </li>
	           </ul>
	           <button class="writeBtn" onclick = "location.href = '<%= absolutePath_ques %>/questions/write.jsp'">글쓰기</button>
	       </div>
	       <!-- 문의사항 list -->
	       <ul class="list">
              <!-- scroll로 구현 (로그인한 사용자가 작성한 문의만 보여줌) -->
              <% for(int i = 0; i < 8; i++) { %>
               <li class="items">
                   <!-- 문의사항 작성글 정보  -->
                   <a href="<%= absolutePath_ques %>/questions/read.jsp" class="url">             
                       <div class="writing">
                           <span class="title">문의 제목</span>
                           <span class="writer-info">사용자 닉네임 | 2021.05.23</span>
                       </div>
                   </a>
                   <!-- comment 횟수 -->
                   <div class="writing-info">
                       <div class="comment">
                           <i class="far fa-comment-dots"></i>
                           <span class="count">나중에 commentDAO.getAllComment(ID)</span>
                       </div>
                   </div>
               </li>
               <% } %>
           </ul> 
	   </section>
	</body>
</html>