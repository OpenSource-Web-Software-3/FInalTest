package crawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class TOPCIT {

	public static void main(String[] args) throws IOException {
		// 크롤링할 TOPCIT 시험일정 URL
		String urlTOPCIT = "https://sw7up.cbnu.ac.kr/community/notice/604afaa0f7b9274da64e86db";
		// TOPCIT의 시험일을 담을 List
		List<String> licenseDateTOPCIT = new ArrayList<String>();
		// TOPCIT의 접수기간을 담을 List
		List<String> applyPeriodTOPCIT = new ArrayList<String>();

		// TOPCIT 시험일정 페이지를 Connection 객체를 통해 접속
		Document document = Jsoup.connect(urlTOPCIT).get();
		// 해당 Selector에 해당하는 HTML tag 가져오기
		Elements elements = document.select(".ck-rounded-corners .table table tbody tr");

//		System.out.println(document);

		// TOPCIT의 시험일과 접수기간을 각각의 list에 저장
		for (int i = 0; i < elements.size(); i++) {
			
			for (int j = 0; j < elements.get(i).children().size(); j++) {
				System.out.println(i+"번째" + j + "열 : " + elements.get(i).child(j).text());
//				licenseDateTOPCIT.add(elements.get(i).child(j).text());
			}
			
		}
		
			//2021. 04. 12.(월), 09:00 ~ 2021. 04. 23.(금), 18:00 ※ 개별 가입 후 단체접수 진행 예정
//			System.out.println(elements.get(0).child(1).text().substring(26,38).trim());
			System.out.println(elements.get(0).child(1).text().substring(0, 12).trim()+"-"+elements.get(0).child(1).text().substring(26,38).trim());
			System.out.println(elements.get(2).child(1).text().substring(0, 12));
//		// TestCode
		for (int i = 0; i < licenseDateTOPCIT.size(); i++) {
//			System.out.println(licenseDateTOPCIT.get(i));
//			System.out.println(applyPeriodTOPCIT.get(i));
			System.out.println(
					"-------------------------------------------------------------------------------------------------------------------");
		}

	}

}
