<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String absolutePath_aside = request.getContextPath()+"/Web-source"; %>
<aside class="left-aside">
    
    <!-- 시험일정, 커뮤니티, 스케줄러, 문의사항을 하나의 tag로 묶음 -->
    <div class="menu">
	    <!-- aside title -->
	    <div class="project-title">
	        <a href="<%=absolutePath_aside%>/index.jsp">LINS</a>
	    </div>
        <!-- 시험일정 -->
	    <ul class="license">
	        <li class="subtitle">시험일정</li>
	        <!-- 저번 쇼핑몰처럼 license-list.jsp를 skin으로 사용
	             (방법을 몰라요..ㅎㅎ) -->
	        <li class="items"><a href="#">licenseName1</a></li>
	        <li class="items"><a href="#">licenseName2</a></li>
	        <li class="items"><a href="#">licenseName3</a></li>
	    </ul>
	    <!-- 커뮤니티 -->
	    <ul class="community">
	        <li class="subtitle">커뮤니티</li>
	        <li class="items"><a href="#">licenseName1</a></li>
	        <li class="items"><a href="#">licenseName2</a></li>
	        <li class="items"><a href="#">licenseName3</a></li>
	    </ul>
	    <!-- 스케줄러 -->
	    <ul class="subtitle" class="scheduler">
	        <a href="#">스케줄러</a>
	    </ul>
	    <!-- 문의사항 -->
	    <ul class="subtitle" class="questions">
	        <a href="#">문의사항</a>
	    </ul>
    </div>
    <!-- my page -->
    <div class="my-page">
        <!-- my page로 이동 -->
        <a href="">
            <i class="far fa-user-circle"></i>
            My page
        </a>
    </div>
</aside>