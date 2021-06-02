/*AJAX Communication sort*/
var request = new XMLHttpRequest();
$(document).ready(function() {
	
	$("#search").on("input", function() {
		var category = $(".license-name").text();
		
		//조건 3개 합치기 		
		var bbsTitle = $("#search").val();
		var sort = $("#sort-standard").val();
		var sub_category = $("#sub-category").val();
		
		//alert($("#search").val());
		//alert($("#sort-standard").val());
		//alert($("#sub-category").val());
		if(sort != null && sort != ""){
			if(sort == "title") sort = "title";
			else if(sort == "latest") sort = "writingID DESC"; 
			else if(sort == "user") sort = "nickName"; 
			else if(sort == "scrapCount") sort = "scrapCount DESC"; 
		}
		
		
		$.ajax({
			url: getContextPath()+"/Community_groupingConditionAction.do",
			type: "POST",
			data: {
				bbsTitle: bbsTitle,
				category : category,
				sort : sort,
				sub_category : sub_category
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




/*AJAX Communication sort*/
$(document).ready(function() {
	$("#sort-standard").change(function() {
		var category = $(".license-name").text();
		
		//조건 3개 합치기 		
		var bbsTitle = $("#search").val();
		var sort = $("#sort-standard").val();
		var sub_category = $("#sub-category").val();
		
		//alert($("#search").val());
		//alert($("#sort-standard").val());
		//alert($("#sub-category").val());
		if(sort != null && sort != ""){
			if(sort == "title") sort = "title";
			else if(sort == "latest") sort = "writingID DESC"; 
			else if(sort == "user") sort = "nickName"; 
			else if(sort == "scrapCount") sort = "scrapCount DESC"; 
		}
		
		
		$.ajax({
			url: getContextPath()+"/Community_groupingConditionAction.do",
			type: "POST",
			data: {
				bbsTitle: bbsTitle,
				category : category,
				sort : sort,
				sub_category : sub_category
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








/*AJAX Communication sub_category*/
$(document).ready(function() {
	$("#sub-category").change(function() {
	var category = $(".license-name").text();
		
		//조건 3개 합치기 		
		var bbsTitle = $("#search").val();
		var sort = $("#sort-standard").val();
		var sub_category = $("#sub-category").val();
		
		//alert($("#search").val());
		//alert($("#sort-standard").val());
		//alert($("#sub-category").val());
		if(sort != null && sort != ""){
			if(sort == "title") sort = "title";
			else if(sort == "latest") sort = "writingID DESC"; 
			else if(sort == "user") sort = "nickName"; 
			else if(sort == "scrapCount") sort = "scrapCount DESC"; 
		}
		
		
		$.ajax({
			url: getContextPath()+"/Community_groupingConditionAction.do",
			type: "POST",
			data: {
				bbsTitle: bbsTitle,
				category : category,
				sort : sort,
				sub_category : sub_category
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

function getContextPath(){
    var hostIndex = location.href.indexOf( location.host ) + location.host.length;
    var contextPath = location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );
    return contextPath;
}