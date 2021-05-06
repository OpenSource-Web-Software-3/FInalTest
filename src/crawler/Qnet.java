package crawler;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Qnet {

	public static void main(String[] args) {

		// 정기 기사 &산업기사 시험일정 : http://www.q-net.or.kr/crf021.do?scheType=03
		// scheType == 1 기술사
		// scheType == 2 기능장
		// scheType == 3 기사 산업기사
		// scheType == 4 기능사

		// 정보처리기사 시험일정 (jmCd의 각 번호 필요)
		// https://www.q-net.or.kr/crf005.do?id=crf00505&jmCd=1320

		try {
			// 1. 수집 대상 URL
			String URL = "http://www.q-net.or.kr/crf021.do?id=crf02101s03&IMPL_YY=2021&SERIES_CD=03";

			// 2. Connection 생성
			Connection conn = Jsoup.connect(URL);

			// 3. HTML 파싱.

			Document doc = conn.get();

			Elements elements = doc.select(".webCont tbody tr");

			// 4. HTML 출력
			for (int i = 0; i < elements.size(); i++) {
				for (int j = 0; j < elements.get(i).children().size(); j++) {
					// DTO클래스를 만들어서 하나씩 집어넣고 날짜같은경우 format하기
					System.out.println(i + "번째 " + j + "열 : " + elements.get(i).child(j).text());
				}
				System.out.println(
						"-------------------------------------------------------------------------------------------------------------------");
			}

//			System.out.println(elements.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
