/*AJAX myPage*/
let identiSection = document.querySelector('.change .identification');
var passwordView = document.querySelector('.change .password');
var emailView = document.querySelector('.change .email');

let mypagePassword = document.querySelector('.personal-info .password');
let mypageEmail = document.querySelector('.personal-info .email');

var request = new XMLHttpRequest();

//비밀번호 인증 
$(document).ready(function() {
	$(".personal-info .password").click(function() {
		$(".change .check").click(function() {

			var password = $("#currnet-password").val();

			$.ajax({
				url: "myPage_checkPasswordAction.do",
				type: "POST",
				data: {
					password: password,
				},
				success: function(result) {
					if (result == 1) { // 비밀번호 일치
						identiSection.style.display = "none";
						passwordView.style.display = "flex";
						emailView.style.display = "none";
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
});

$(document).ready(function(){
	$(".personal-info .email").click(function() {
		$(".change .check").click(function() {

			var password = $("#currnet-password").val();

			$.ajax({
				url: "myPage_checkPasswordAction.do",
				type: "POST",
				data: {
					password: password,
				},
				success: function(result) {
					if (result == 1) { // 비밀번호 일치
						identiSection.style.display = "none";
						emailView.style.display = "flex";
						passwordView.style.display = "none";
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
					passwordView.style.display = "none";
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
					emailView.style.display = "none";
				}
				else if (result == -1) {
					alert("DB오류");
				}
			}
		});
	});
});