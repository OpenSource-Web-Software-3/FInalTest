<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="license.*" %>
<%@ page import="licensescrap.*" %>
<% String absolutePath_license = request.getContextPath()+"/Web-source"; %>
<!DOCTYPE html>
<html>
    <head>
        <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
        <script type="text/javascript" src="<%=absolutePath_license %>/js/scrap.js" defer></script>
    
        <!-- css -->
        <link rel="stylesheet" href="<%= absolutePath_license %>/css/scrap-license-style.css" />
        <link rel="stylesheet" href="<%= absolutePath_license %>/css/aside-style.css" />
        <%@include file="../head-tags.jsp" %>
        <!-- 현재 사용자가 보고 있는 자격증 이름을 `licenseName`에 삽입하고 싶습니다! -->
        <title>스크랩한 시험일정 보기</title>
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
		LicensescrapDAO licensescrapDAO = new LicensescrapDAO();
		ArrayList<LicensescrapDTO> licenseScrapList = licensescrapDAO.getLicensescrapList(userID);
		
		LicenseDAO licenseDao = new LicenseDAO();
		ArrayList<LicenseDTO> licenseList = licenseDao.getLicenseList(licenseScrapList);
    %>
     
    <body>        
       <section class="set-margin scrap-license-list">
           <!-- 해당 자격증의 시험일정을 보여주는 부분 -->
           <div class="license-name">스크랩한 시험일정</div>
           <div class="descript">현재 로그인을 하고 계신 아이디로  스크랩 버튼을 누른 시험일정들을 확인하실 수 있습니다.
           </br>순서대로, 시험일정과 해당 시험일정의 자격증 이름이 표시됩니다. 다시 스크랩 버튼을 누르시면, 해당 리스트에서 제거할 수 있습니다.</div>
           <ul class="list">
              <!-- 임시적으로 9개로 제한 -> scroll로 변환 -->
              <% for(int i = 0; i < licenseList.size(); i++) { %>
               <li class="items">
                   <!-- 시험일정 정보  -->
                   <a href="" class="url">             
                       <div class="text-area">
                           <span class="date"><%=licenseList.get(i).getLicenseDate() %></span>
                           <span class="time"><%=licenseList.get(i).getLicenseName() %></span>
                       </div>
                   </a>
                   <!-- 스크랩 버튼 -->
                   <!-- 여기에서 스크랩 버튼을 누르면 스크랩한 일정에서 제외 -->
                  <button class="scrapBtn active" value="<%=licenseList.get(i).getLicenseID() %>" >
                       <i class="fas fa-star"></i>
                   </button>
               </li>
               <% } %>
           </ul>        
       </section>
       <!-- aside-->
       <%@include file= "../aside.jsp" %>
    </body>
</html>