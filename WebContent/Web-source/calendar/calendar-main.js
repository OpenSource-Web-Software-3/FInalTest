(function () {
  calendarMaker($("#calendarForm"), new Date());
  getScrapLicenseList(new Date().getFullYear(), new Date().getMonth()+1)
})();

var nowDate = new Date();
function calendarMaker(target, date) {
  if (date == null || date == undefined) {
      date = new Date();
  }
  nowDate = date;
  if ($(target).length > 0) {
      var year = nowDate.getFullYear();
      var month = nowDate.getMonth() + 1;
      $(target).empty().append(assembly(year, month));
  } else {
      console.error("custom_calendar Target is empty!!!");
      return;
  }

  var thisMonth = new Date(nowDate.getFullYear(), nowDate.getMonth(), 1);
  var thisLastDay = new Date(nowDate.getFullYear(), nowDate.getMonth() + 1, 0);


  var tag = "<tr class='cal_date'>";
  var cnt = 0;
  //빈 공백 만들어주기
  for (i = 0; i < thisMonth.getDay(); i++) {
      tag += "<td></td>";
      cnt++;
  }

  //날짜 채우기
  for (i = 1; i <= thisLastDay.getDate(); i++) {
      if (cnt % 7 == 0) { tag += "<tr class='cal_date'>"; }

      tag += "<td>" + i + "</td>";
      cnt++;
      if (cnt % 7 == 0) {
          tag += "</tr>";
      }
  }
 
  $(target).find("#custom_set_date").append(tag);
  calMoveEvtFn();

  function assembly(year, month) {
      var calendar_html_code =
          "<table class='custom_calendar_table'>" +
	          "<colgroup>" +
		          "<col style='width:81px'/>" +
		          "<col style='width:81px'/>" +
		          "<col style='width:81px'/>" +
		          "<col style='width:81px'/>" +
		          "<col style='width:81px'/>" +
		          "<col style='width:81px'/>" +
		          "<col style='width:81px'/>" +
	          "</colgroup>" +
	          "<thead class='date'>" +
		          "<td><button type='button' class='prev'><</button></td>" +
		          "<td colspan='5'><p><span>" + year + "</span>년 <span>" + month + "</span>월</p></td>" +
		          "<td><button type='button' class='next'>></button></td>" +
	          "</thead>" +
	          "<tbody id='custom_set_date'>" +
		          "<tr  class='cal_week'>" +
		          	"<td>일</td>" +
		          	"<td>월</td>" +
		          	"<td>화</td>" +
		          	"<td>수</td>" +
		          	"<td>목</td>" +
		          	"<td>금</td>" +
		          	"<td>토</td>" +
		          "</tr>"
	          "</tbody>" +
          "</table>";
      return calendar_html_code;
  }

  function calMoveEvtFn() {
      //전달 클릭
      $(".custom_calendar_table").on("click", ".prev", function () {
          nowDate = new Date(nowDate.getFullYear(), nowDate.getMonth() - 1, nowDate.getDate());
          calendarMaker($(target), nowDate);
			getScrapLicenseList(nowDate.getFullYear(), nowDate.getMonth()+1); //AJAX
      });
      //다음날 클릭
      $(".custom_calendar_table").on("click", ".next", function () {
          nowDate = new Date(nowDate.getFullYear(), nowDate.getMonth() + 1, nowDate.getDate());
          calendarMaker($(target), nowDate);
			getScrapLicenseList(nowDate.getFullYear(), nowDate.getMonth()+1); //AJAX
      });
      //일자 선택 클릭
      $(".custom_calendar_table").on("click", "td", function () {
          $(".custom_calendar_table .select_day").removeClass("select_day");
          $(this).removeClass("select_day").addClass("select_day");
      });
  }

//해당 년,월에 해당하는 license일정 가져오기
function getScrapLicenseList(thisYear, thisMonth){
	$.ajax({
		url: "Calendar_scrapLicenseListAction.do",
		type: "POST",
		data: {
			thisYear: thisYear,
			thisMonth: thisMonth
		},
		success: function(data) {
			if(data == -1){
				alert('로그인을 해주세요');
				location.href="../index.do";
			}
			else{
				//result[i][1].value
				var parsed = JSON.parse(data); 
				var result = parsed.result;
				
				for (var i = 0; i < result.length; i++) {
					var licenseDate = result[i][3].value; //시험일정 (xxxx.xx.xx~xxxx.xx.xx) 
					var applyPeriod = result[i][6].value; //원서접수기간 (xxxx.xx.xx~xxxx.xx.xx)
					// ~기준으로 자르기 
					//각각 date객체
					var start_LicenseDate = parseDate(licenseDate.substr(0,10)); //시험기간시작 
					var end_LicenseDate = parseDate(licenseDate.substr(11,21)); //시험기간 끝
					var start_applyPeriod = parseDate(applyPeriod.substr(0,10)); //원서접수기간 시작
					var end_applyPeriod = parseDate(applyPeriod.substr(11,21)); //원서접수기간 끝
					
					//확인용 	(지우셔도 됩니다)
					//alert(start_LicenseDate); //<--이런식으로 쓰시면 될거 같습니다
					//alert(end_LicenseDate);
					//alert(start_applyPeriod);
					//alert(end_applyPeriod);
					
					//현재 캘린더의 년,월과 가져온 데이터의 날짜가 같은지 판별
					if(start_LicenseDate != null && start_LicenseDate.getMonth() == thisMonth && start_LicenseDate.getFullYear() == thisYear){
						alert("~~");
					}
					if(end_LicenseDate != null && end_LicenseDate.getMonth() == thisMonth  && start_LicenseDate.getFullYear() == thisYear){
						alert("~~!");
					}
					if(start_applyPeriod != null && start_applyPeriod.getMonth() == thisMonth  && start_LicenseDate.getFullYear() == thisYear){
						alert("~~!!");
					}
					if(end_applyPeriod != null && end_applyPeriod.getMonth() == thisMonth  && start_LicenseDate.getFullYear() == thisYear){
						alert("~~!!!");
					}
					
				}
			}
			
		}
		
	});
}

function parseDate(str) {
    var y = str.substr(0, 4);
    var m = str.substr(5, 2);
    var d = str.substr(8, 2);
    return new Date(y,m,d);
}

	
	
	
}