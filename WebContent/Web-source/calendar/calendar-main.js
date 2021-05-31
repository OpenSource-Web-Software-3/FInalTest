(function () {
  calendarMaker($("#calendarForm"), new Date());
})();

// 달력을 생성하는 function
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
  for (let i = 1; i <= thisLastDay.getDate(); i++) {
      if (cnt % 7 == 0) { tag += "<tr class='cal_date'>"; }

      tag += `<td class="year${year} month${month} date${i}">` + i + "</td>";
      cnt++;
      if (cnt % 7 == 0) {
          tag += "</tr>";
      }
  }
 
  $(target).find("#custom_set_date").append(tag);
  calMoveEvtFn();

  function assembly(year, month) {
      var calendar_html_code =
          "<table class='custom_calendar_table' onchange='test()'>" +
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
		          "<td colspan='5'><p><span class>" + year + "</span>년 <span>" + month + "</span>월</p></td>" +
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
          nowDate = new Date(nowDate.getFullYear(), nowDate.getMonth()-1, nowDate.getDate());
          calendarMaker($(target), nowDate);
      });
      //다음날 클릭
      $(".custom_calendar_table").on("click", ".next", function () {
          nowDate = new Date(nowDate.getFullYear(), nowDate.getMonth()+1, nowDate.getDate());
          calendarMaker($(target), nowDate);
      });
      //일자 선택 클릭
      /*$(".custom_calendar_table").on("click", "td", function () {
          $(".custom_calendar_table .select_day").removeClass("select_day");
          $(this).removeClass("select_day").addClass("select_day");
      });*/
  }
  /* 일정관리와 관련된 부분 */
//각 숫자는 시험일정이 있는 날짜를 의미 (test 요소)
  const selectDate = document.querySelector(`.year2021.month${month}.date1`);
// 가져온 스크랩 일정중에 해당날짜에 시험이 치뤄지는 자격증 이름을 저장
  let licenseName = "TOEIC";
  
// 해당 일정이 단일 기간인 경우 -> class name에 single-date add
  selectDate.classList.add("single-date");
  
// 해당 일정의 td에 
  selectDate.innerHTML += `<div>${licenseName}</div>`;
}

