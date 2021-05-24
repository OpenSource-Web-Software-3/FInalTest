<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String absolutePath1 = request.getContextPath()+"/Web-source";%>
<!-- 팝업창 형식으로 띄울거에요! (새 페이지X) -->
<div class="change">
    <!-- 인증 영역 : 사용자가 올바른 비밀번호를 입력했다면, 해당 영역을 style="display: none"으로 변경 + 
                      변경해야할 영역을 display: block으로 변경
          * required="required"를 통해 해당 input을 입력받지 않으면 alert하도록 설정함-->
    <div class="identification">
        <i class="fas fa-arrow-left" onclick="DeleteCheckPop()"></i>
        <span class="title">본인확인</span>
        <span class="comment">본인확인을 위해 비밀번호를 입력해주세요.</span>
        <form action="" method="POST">
            <input type="password" name="password" placeholder="현재 비밀번호 입력" required="required"/>
            <button class="check" type="submit">확인</button>
        </form>
    </div>
    <!-- 비밀번호 변경 영역 -->    
    <div class="password">
        <i class="fas fa-arrow-left" onclick="DeleteCheckPop()"></i>
        <div class="title">비밀번호 변경</div>
        <form class="change-form" method="POST" action="">
            <input type="password" class="new1-pass" placeholder="새로운 비밀번호" name="password" required="required"/>
            <input type="password" class="new2-pass" placeholder="새로운 비밀번호 확인" name="password" required="required"/>
            <button class="sendBtn" type="submit">변경</button>
        </form>
    </div>
    <!-- 이메일 변경 영역 -->
    <div class="email">
        <i class="fas fa-arrow-left" onclick="DeleteCheckPop()"></i>
        <div class="title">이메일 변경</div>
        <form class="change-form" method="POST" action="">
            <input type="text" class="new-email" placeholder="변경할 이메일" name="email" required="required"/>
            <button class="sendBtn" type="submit">변경</button>
        </form>
    </div>
    <!-- 회원탈퇴 영역 -->
    <div class="withdrawal">
        <i class="fas fa-arrow-left" onclick="DeleteCheckPop()"></i>
        <div class="title">회원탈퇴</div>
        <form class="delete-form" method="POST" action="<%=absolutePath1%>/withdrawalAction.do">
            <input type="password" class="delete-password" placeholder="현재 비밀번호" name="password" required="required"/>
            <span class="alert">※ 탈퇴 후 개인 정보, 시간표 등의 데이터가 삭제되며, 복구할 수 없습니다.</br>
※ 작성한 게시물은 삭제되지 않고, (탈퇴한 회원)으로 닉네임이 표시됩니다.
            </span>
            <button class="sendBtn" type="submit">탈퇴</button>
        </form>
    </div>
</div>