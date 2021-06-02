<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String  absolutePath_commu = request.getContextPath()+"/Web-source";%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../head-tags.jsp" %>
        <!-- css -->
        <link rel="stylesheet" href="<%= absolutePath_commu %>/css/scrap-commu-style.css" />
        <link rel="stylesheet" href="<%= absolutePath_commu %>/css/aside-style.css" />
        <title>스크랩한 게시글 보기</title>
    </head>
    <body>        
       <!-- aside -->
       <%@include file="../aside.jsp" %>
       <!-- 게시글을 보여주는 영역 -->
       <section class="set-margin scrap-commu-list">
           <!-- 자격증 이름 -->
           <div class="page-title-name">스크랩한 게시글</div>
           <div class="descript">현재 로그인을 하고 계신 아이디로  스크랩 버튼을 누른 게시글들을 확인하실 수 있습니다.
           </br>순서대로, 게시글 이름, 작성자, 커뮤니티 이름, 작성일자에 대한 정보를 보여줍니다.</div>
           <!-- 게시글 list -->
           <ul class="list">
              <!-- 임시적으로 10개 -->
              <% for(int i = 0; i < 10; i++) { %>
               <li class="items">
                   <!-- 클릭한 게시글을 commuRead로  -->
                   <a href="" class="url">             
                       <div class="writing">
                           <span class="title">게시글 이름</span>
                           <span class="writer-info">작성자 닉네임 | 해당 게시글이 작성된 커뮤니티 이름</span>
                       </div>
                   </a>
                   <span class="date">해당 게시글의 작성일자</span>                   
               </li>
               <% } %>
           </ul> 
       </section>
    </body>
</html>