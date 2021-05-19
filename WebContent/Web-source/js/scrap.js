/*AJAX jquery version*/
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
			url: "addScrapAction.do",
			type: "POST",
			data: {
				licenseID : licenseID,
				active : active
			},
			success: function(data) {


				if (data == "") {
					$('#category-item-list').empty();
					$('#shopCart-item').empty();
					$('#purchase-item-list').empty();

					return; //상품이 한개도 없는경우
				}

				var parsed = JSON.parse(data);
				var result = parsed.result;
				var flag = parsed.flag;
				var date = parsed.date;

				if (flag == "category-skin") {
					$('#category-item-list').empty();
					for (var i = 0; i < result.length; i++) {
						getItemList_to_SubCate1(result[i][0].value, result[i][1].value, result[i][2].value, result[i][3].value, result[i][4].value, result[i][5].value, result[i][6].value, result[i][7].value);
					}
				}
				else if (flag == "Shop Cart") {
					$('#shopCart-item').empty();
					for (var i = 0; i < result.length; i++) {
						getItemList_to_SubCate2(result[i][0].value, result[i][1].value, result[i][2].value, result[i][3].value, result[i][4].value, result[i][5].value, result[i][6].value, result[i][7].value, result[i][8].value, result[i][9].value, result[i][10].value);
					}
				}
				else if (flag == "Purchase List") {
					$('#purchase-item-list').empty();
					getItemList_to_SubCate3(result, date);
				}
				else alert('Ajax Error!');
			}
		});
	});

});
