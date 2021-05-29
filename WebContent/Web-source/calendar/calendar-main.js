(function () {
  calendarMaker($("#calendarForm"), new Date());
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
      });
      //다음날 클릭
      $(".custom_calendar_table").on("click", ".next", function () {
          nowDate = new Date(nowDate.getFullYear(), nowDate.getMonth() + 1, nowDate.getDate());
          calendarMaker($(target), nowDate);
      });
      //일자 선택 클릭
      $(".custom_calendar_table").on("click", "td", function () {
          $(".custom_calendar_table .select_day").removeClass("select_day");
          $(this).removeClass("select_day").addClass("select_day");
      });
  }
}