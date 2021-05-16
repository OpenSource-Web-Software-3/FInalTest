package crawler;
import java.util.Iterator;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TOEIC {

	public static void main(String[] args) {
		// 토익 : https://exam.toeic.co.kr/receipt/examSchList.php
		try {
			// 1. 수집 대상 URL
			String URL = "https://exam.toeic.co.kr/receipt/examSchList.php";

			// 2. Connection 생성
			Connection conn = Jsoup.connect(URL);

			// 3. HTML 파싱.
			
			Document doc = conn.get(); // conn.post();

			Elements elements = doc.select(".link_list tbody tr");

			// 4. HTML 출력
			for (int i = 0; i < elements.size(); i++) {
				for (int j = 0; j < elements.get(i).children().size(); j++) {
					//DTO클래스를 만들어서 하나씩 집어넣고 날짜같은경우 format하기
					System.out.println(i + "번째 " + j + "열 : " + elements.get(i).child(j).text());
				}
				System.out.println("-------------------------------------------------------------------------------------------------------------------");
			}

//			System.out.println(elements.toString());
			System.out.println("===========================================");
			System.out.println("===========================================");
			System.out.println("===========================================");
			System.out.println(elements.get(0).child(1).text().substring(0, 10));
			System.out.println(elements.get(0).child(1).text().substring(18, 23));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
	}
}
