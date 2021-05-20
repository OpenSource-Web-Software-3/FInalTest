/*AJAX scrap*/
var request = new XMLHttpRequest();
$(document).ready(function() {

	$(".scrapBtn").click(function() {
		
		var licenseID = $(this).val();
		var active;
		if($(this).is(".active")){
			$(this).removeClass('active');
			active = false;		
		}
		else{
			$(this).addClass('active');
			active = true;		
		}
		
		$.ajax({
			url: "licenseScrapAction.do",
			type: "POST",
			data: {
				licenseID : licenseID,
				active : active
			},
			success: function(result) {
				if(result == 1){
					// 아마 return 하고 그냥 지워도 될 듯
					if(active){
						//scrap 추가된 상태 (색 칠해주시면 될 것 같습니다!)
					}
					
					else{
						//scrap 삭제 상태 (색 빼주시면 될 것 같습니다!)
					}
				}
				else if (result == 0) {
					alert('잘못된 접근입니다.');
				}
				else if (result == -1){
					alert('로그인을 해주세요');
				}
				else{ // -1
					alert('DB오류');
				}
			}
		});
	});
	
	$(".scrap").click(function() {
		var writingID = $(this).val();
		var active;
		if($(this).is(".active")){
			$(this).removeClass('active');
			active = false;		
		}
		else{
			$(this).addClass('active');
			active = true;		
		}
		
		alert(active);
		$.ajax({
			url: "commuScrapAction.do",
			type: "POST",
			data: {
				writingID : writingID,
				active : active
			},
			success: function(result) {
				if(result == 1){
					// 아마 return 하고 그냥 지워도 될 듯
					if(active){
						//scrap 추가된 상태 (색 칠해주시면 될 것 같습니다!)
					}
					
					else{
						//scrap 삭제 상태 (색 빼주시면 될 것 같습니다!)
					}
				}
				else if (result == 0) {
					alert('잘못된 접근입니다.');
				}
				else if (result == -1){
					alert('로그인을 해주세요');
				}
				else{ // -1
					alert('DB오류');
				}
			}
		});
	});

});
