<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String absolutePath_license = request.getContextPath()+"/Web-source"; %>
<!DOCTYPE html>
<html>
    <head>
        <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
        <script type="text/javascript" src="./js/scrap.js" defer></script>
    
        <%@include file="../head-tags.jsp" %>
        <!-- css -->
        <link rel="stylesheet" href="<%= absolutePath_license %>/css/scrap-license-style.css" />
        <link rel="stylesheet" href="<%= absolutePath_license %>/css/aside-style.css" />
        <!-- 현재 사용자가 보고 있는 자격증 이름을 `licenseName`에 삽입하고 싶습니다! -->
        <title>스크랩한 시험일정 보기</title>
    </head>
    <body>        
       <!-- aside-->
       <%@include file= "../aside.jsp" %>
       <section class="set-margin scrap-license-list">
           <!-- 해당 자격증의 시험일정을 보여주는 부분 -->
           <div class="license-name">스크랩한 시험일정</div>
           <div class="descript">현재 로그인을 하고 계신 아이디로  스크랩 버튼을 누른 시험일정들을 확인하실 수 있습니다.
           </br>순서대로, 시험일정과 해당 시험일정의 자격증 이름이 표시됩니다. 다시 스크랩 버튼을 누르시면, 해당 리스트에서 제거할 수 있습니다.</div>
           <ul class="list">
              <!-- 임시적으로 9개로 제한 -> scroll로 변환 -->
              <% for(int i = 0; i < 10; i++) { %>
               <li class="items">
                   <!-- 시험일정 정보  -->
                   <a href="" class="url">             
                       <div class="text-area">
                           <span class="date">스크랩한 시험일정의 licenseDate(applyDate 필요X)</span>
                           <span class="time">licenseName</span>
                       </div>
                   </a>
                   <!-- 스크랩 버튼 -->
                   <!-- 여기에서 스크랩 버튼을 누르면 스크랩한 일정에서 제외 -->
                  <button class="scrapBtn active" value="" >
                       <i class="fas fa-star"></i>
                   </button>
               </li>
               <% } %>
           </ul>        
       </section>
    </body>
</html>