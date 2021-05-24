/*AJAX myPage*/

var passwordView = document.querySelector('.password');
var emailView = document.querySelector('.email');

var request = new XMLHttpRequest();

//비밀번호 인증 
$(document).ready(function() {

	$(".check").click(function() {

		var password = $("#currnet-password").val();

		$.ajax({
			url: "myPage_checkPasswordAction.do",
			type: "POST",
			data: {
				password: password,
			},
			success: function(result) {
				if (result == 1) { // 비밀번호 일치
					passwordView.style.display = "flex"; // 여기에 <비밀번호 변경 팝업> or <이메일 변경 팝업> 보이게 하면 될 것같습니다.
				}
				else if (result == 0) { // 비밀번호 불일치
					alert('비밀번호가 일치하지 않습니다.');
				}
				else if (result == -1) {
					alert('존재하는 아이디가 없습니다.');
				}
				else { // -2
					alert('DB오류');
				}
			}
		});
	});
});


//비밀번호 변경
$(document).ready(function() {
	$("#sendBtn_password").click(function() {
		var newPassword1 = $(".new1-pass").val();
		var newPassword2 = $(".new2-pass").val();

		if (newPassword1 != newPassword2) {
			alert("비밀번호가 일치하지 않습니다.");
			return;
		}
		
		$.ajax({
			url: "myPage_changePasswordAction.do",
			type: "POST",
			data: {
				newPassword1: newPassword1,
				newPassword2: newPassword2
			},
			success: function(result) {
				if (result == 1) {
					alert('비밃번호가 변경 되었습니다. 다시 로그인 해주세요');
					location.href = "/index.do";
				}
				else if (result == -1) {
					alert("DB오류");
				}
			}
		});
	});
});

//이메일 변경
$(document).ready(function() {
	$("#sendBtn_email").click(function() {
		var newEmail = $(".new-email").val();

		$.ajax({
			url: "myPage_changeEmailAction.do",
			type: "POST",
			data: {
				newEmail: newEmail,
			},
			success: function(result) {
				if (result == 1) {
					alert('이메일이 변경되었습니다'); 
					//여기에 이메일 변경 팝업 닫아주시면 될 것같습니다.
				}
				else if (result == -1) {
					alert("DB오류");
				}
			}
		});
	});
});