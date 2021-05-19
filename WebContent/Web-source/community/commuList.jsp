<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String  absolutePath_commu = request.getContextPath()+"/Web-source";%>
<!DOCTYPE html>
<html>
	<head>
		<%@include file="../head-tags.jsp" %>
        <!-- css -->
        <link rel="stylesheet" href="<%= absolutePath_commu %>/css/commu-list-style.css" />
        <link rel="stylesheet" href="<%= absolutePath_commu %>/css/aside-style.css" />
		<title>커뮤니티 | licenseName</title>
	</head>
	<body>
	   <!-- aside -->
	   <%@include file="../aside.jsp" %>
	   <!-- 게시글을 보여주는 영역 -->
	   <section class="set-margin commu-list">
	       <!-- 자격증 이름 -->
	       <div class="license-name">licenseName</div>
	       <!-- 정렬, 검색어, 글쓰기를 하나의 div로 묶음 -->
	       <div class="input-wrap">
	           <!-- 검색어, 정렬 기준 선택 -->
	           <ul class="text">
	               <li>
	                   <!-- 각 option에 대한 value는 상의할 필요가 있음 -->
	                   <select name="sortStandard" id="sort-standard">
	                       <option value="" class="default">정렬기준</option>
	                       <option value="title">제목</option>
                           <option value="latest">최신</option>
                           <option value="user">작성자</option>
                           <option value="scrapCount">인기순(스크랩)</option>	                       
	                   </select>
	               </li>
	               <li>
	                   <input type="text" id="search" placeholder="검색어 입력"/>
	               </li>
	           </ul>
	           <button class="writeBtn" onclick = "location.href = '<%= absolutePath_commu%>/community/write.jsp'">글쓰기</button>
	       </div>
	       <!-- 게시글 list -->
	       <ul class="list">
              <!-- 임시적으로 9개로 제한 (아마 9개로 갈 거 같습니다.) -->
              <% for(int i = 0; i < 9; i++) { %>
               <li class="items">
                   <!-- 시험일정 정보  -->
                   <a href="<%=absolutePath_commu %>/community/read.jsp" class="url">             
                       <div class="writing">
                           <span class="title">게시글 제목</span>
                           <span class="writer-info">사용자닉네임 | 작성일</span>
                       </div>
                   </a>
                   <div class="writing-info">
                       <div class="view">
	                       <i class="far fa-eye"></i>
	                       <span class="count">1,234</span>
                       </div>
                       <div class="comment">
	                       <i class="far fa-comment-dots"></i>
	                       <span class="count">10</span>
                       </div>
                   </div>
               </li>
               <% } %>
           </ul> 
	   </section>
	</body>
</html>