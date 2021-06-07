<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="communication.*" %>
<%@ page import="commuscrap.*" %>
<% String  absolutePath_commu = request.getContextPath()+"/Web-source";%>
<!DOCTYPE html>
<html>
    <head>
        <!-- css -->
        <link rel="stylesheet" href="<%= absolutePath_commu %>/css/scrap-commu-style.css" />
        <link rel="stylesheet" href="<%= absolutePath_commu %>/css/aside-style.css" />
        <%@include file="../head-tags.jsp" %>
        <title>스크랩한 게시글 보기</title>
    </head>
     <%
		String userID = null;
		if(session.getAttribute("userID") != null){
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
		CommuscrapDAO commuscrapDAO = new CommuscrapDAO();
		ArrayList<CommuscrapDTO> commuScrapList = commuscrapDAO.getCommuscrapList(userID);
		
		CommunicationDAO communicationDao = new CommunicationDAO();
		ArrayList<CommunicationDTO> commuList = communicationDao.getCommunicationList(commuScrapList);
    %>
    
    <body>        
       <!-- 게시글을 보여주는 영역 -->
       <section class="set-margin scrap-commu-list">
           <!-- 자격증 이름 -->
           <div class="page-title-name">스크랩한 게시글</div>
           <div class="descript">현재 로그인을 하고 계신 아이디로  스크랩 버튼을 누른 게시글들을 확인하실 수 있습니다.
           </br>순서대로, 게시글 이름, 작성자, 커뮤니티 이름, 작성일자에 대한 정보를 보여줍니다.</div>
           <!-- 게시글 list -->
           <ul class="list">
              <!-- 임시적으로 10개 -->
              <% for(int i = 0; i < commuList.size(); i++) { %>
               <li class="items">
                   <!-- 클릭한 게시글을 commuRead로  -->
                   <a href="<%=absolutePath_commu %>/community/read.jsp?category=<%=commuList.get(i).getCategory()%>&writingID=<%=commuList.get(i).getWritingID() %>" class="url">             
                       <div class="writing">
                           <span class="title"><%=commuList.get(i).getTitle() %></span>
                           <span class="writer-info"><%=commuList.get(i).getNickName() %> | <%=commuList.get(i).getCategory() %></span>
                       </div>
                   </a>
                   <span class="date"><%=commuList.get(i).getCommuDate() %></span>                   
               </li>
               <% } %>
           </ul> 
       </section>
       <!-- aside -->
       <%@include file="../aside.jsp" %>
    </body>
</html>