/*AJAX Communication sort*/
var request = new XMLHttpRequest();
$(document).ready(function() {
	
	$("#search").on("input", function() {
		var bbsTitle = $("#search").val();
		var category = $(".license-name").text();
			$.ajax({
			url: getContextPath()+"/searchTitleAction.do",
			type: "POST",
			data: {
				bbsTitle: bbsTitle,
				category : category
			},
			success: function(data) {
				var parsed = JSON.parse(data); 
				var result = parsed.result;
				
				$('.list').empty(); //모든 엘리먼트 지우기
				for (var i = 0; i < result.length; i++) {
					printLicenseList(result[i][0].value, result[i][1].value, result[i][2].value, result[i][3].value, result[i][4].value, result[i][5].value, result[i][6].value, result[i][7].value, result[i][8].value, result[i][9].value, result[i][10].value);
				}
			}
			
		});
	});
});

function printLicenseList(writingID, category, sub_category, title, ID, nickname, commuDate, scrapCount, view, available, commentCount){ //ajax를 사용해 받아온json 데이터들을 테이블에 뿌리는 역할
		$('.list').append(
					'<li class="items">'
						+'<a href="'+getContextPath()+'/Web-source/community/read.jsp?category='+category+'&writingID='+writingID+'" class="url">'
						+ ' <div class="writing">'
						+ '<span class="title">'+title+'</span>' 
						+ ' <span class="writer-info">'+nickname+' | '+commuDate+'</span>'
						+ '</div></a>'
						+ ' <div class="writing-info">'
						+ '<div class="view">'
						+ ' <i class="far fa-eye"></i>'
						+ '<span class="count">'+view+'</span>'
						+ '</div>'
						+ '<div class="comment">'
						+ '<i class="far fa-comment-dots"></i>'
						+ '<span class="count">'+commentCount+'</span>'
						+ '</div>'
						+ '</div></li>');	
	}



/*AJAX Communication sub_category*/
$(document).ready(function() {
	$(".scrap").click(function() {
		var writingID = $(this).val();
		var active;
		if ($(this).is(".active")) {
			$(this).removeClass('active');
			active = false;
		}
		else {
			$(this).addClass('active');
			active = true;
		}

		$.ajax({
			url: "commuScrapAction.do",
			type: "POST",
			data: {
				writingID: writingID,
				active: active
			},
			success: function(result) {
				if (result == 1) {
					if (active) {
						$("#scrapCount").text(parseInt($("#scrapCount").text())+1);
					}
					else {
						$("#scrapCount").text(parseInt($("#scrapCount").text())-1);
					}
				}
				else if (result == 0) {
					alert('잘못된 접근입니다.');
				}
				else if (result == -1) {
					alert('로그인을 해주세요');
					location.href="../index.do";
				}
				else { // -1
					alert('DB오류');
				}
			}
		});
	});
});


/*AJAX Communication search*/
$(document).ready(function() {
	$(".scrap").click(function() {
		var writingID = $(this).val();
		var active;
		if ($(this).is(".active")) {
			$(this).removeClass('active');
			active = false;
		}
		else {
			$(this).addClass('active');
			active = true;
		}

		$.ajax({
			url: "commuScrapAction.do",
			type: "POST",
			data: {
				writingID: writingID,
				active: active
			},
			success: function(result) {
				if (result == 1) {
					if (active) {
						$("#scrapCount").text(parseInt($("#scrapCount").text())+1);
					}
					else {
						$("#scrapCount").text(parseInt($("#scrapCount").text())-1);
					}
				}
				else if (result == 0) {
					alert('잘못된 접근입니다.');
				}
				else if (result == -1) {
					alert('로그인을 해주세요');
					location.href="../index.do";
				}
				else { // -1
					alert('DB오류');
				}
			}
		});
	});
});


function getContextPath(){
    var hostIndex = location.href.indexOf( location.host ) + location.host.length;
    var contextPath = location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );
    return contextPath;
}