package crawler;
import java.util.Iterator;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TOEFL {

	public static void main(String[] args) {

		try {
			// 1. 수집 대상 URL
			String URL = "https://www.google.com/search?q=%ED%86%A0%ED%94%8C+%EC%8B%9C%ED%97%98%EC%9D%BC%EC%A0%95+2021&sxsrf=ALeKk00nfbelte1TA0zhwo_ysrJHMqt4cA%3A1620289114019&ei=WqaTYI1boZDj4Q_f157oBw&oq=%ED%86%A0%ED%94%8C+%EC%8B%9C%ED%97%98%EC%9D%BC%EC%A0%95&gs_lcp=Cgdnd3Mtd2l6EAMYATIHCCMQsAMQJzIHCCMQsAMQJzIFCAAQsAMyBwgAELADEB4yBwgAELADEB4yCQgAELADEAgQHlAAWABglRpoAnAAeACAAakBiAGpAZIBAzAuMZgBAKoBB2d3cy13aXrIAQbAAQE&sclient=gws-wiz";
			
			// 2. Connection 생성
			Connection conn = Jsoup.connect(URL).ignoreContentType(true);

			// 3. HTML 파싱.
			Document doc = conn.get(); // conn.post();

//			Elements elements = doc.select(".link_list tbody tr");

			// 4. HTML 출력
//			for (int i = 0; i < elements.size(); i++) {
//				for (int j = 0; j < elements.get(i).children().size(); j++) {
//					//DTO클래스를 만들어서 하나씩 집어넣고 날짜같은경우 format하기
//					System.out.println(i + "번째 " + j + "열 : " + elements.get(i).child(j).text());
//				}
//				System.out.println("-------------------------------------------------------------------------------------------------------------------");
//			}

			System.out.println(doc.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
