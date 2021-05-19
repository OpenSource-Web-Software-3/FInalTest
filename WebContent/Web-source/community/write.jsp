<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String absolutePath_write = request.getContextPath()+"/Web-source"; %>
<!DOCTYPE html>
<html>
	<head>
		<%@include file="../head-tags.jsp" %>
        <!-- css -->
        <link rel="stylesheet" href="<%= absolutePath_write %>/css/write-style.css" />
        <link rel="stylesheet" href="<%= absolutePath_write %>/css/aside-style.css" />
		<title>글 작성하기</title>
	</head>
	<body>
	   <!-- aside -->
	   <%@include file="../aside.jsp" %>
	   <!-- 게시글을 작성하는 영역 -->
	   <section class="set-margin write">
	       <!-- 해당 페이지 title -->
	       <div class="title">게시글 작성</div>
	       <!-- 실제로 글을 작성는 부분 -->
	       <form class="writing-part">
	           <!-- 제목 입력 -->
	           <input type="text" id="title" name="title" placeholder="제목"/>
	           <!-- 내용 입력 -->
	           <textarea name="" id="content" name="content" placeholder="내용을 입력하세요">
	           
	           </textarea>
		       <!-- 사진 첨부 -->
		       <div class="file-area">
		           <label for="">사진 첨부하기</label>
		           <!-- name에는 fileName를 적어야 하나요, 아님 fileRealName을 적어야 하나요 -->
		           <input type="file" id="file" name=""/>
		       </div>
		       <!-- 교수님 피드백 수용으로 `문서 첨부`을 생성함 -->
		       <div class="document-area">
	               <label for="">문서 첨부하기</label>
	               <input type="file" id="document" />
	           </div>
	           <div class="btn-area">
		           <button class="sendBtn">완료</button>
	           </div>
	       </form>
	   </section>
	</body>
</html>