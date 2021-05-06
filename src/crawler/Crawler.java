package crawler;
import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Crawler {
	private String URL;
	private String selectHTML;

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public String getSelectHTML() {
		return selectHTML;
	}

	public void setSelectHTML(String selectHTML) {
		this.selectHTML = selectHTML;
	}

	public Crawler(String uRL, String selectHTML) {
		this.URL = uRL;
		this.selectHTML = selectHTML;
	}

	public void print() throws IOException {
		// 2. Connection 생성
		Connection conn = Jsoup.connect(this.URL);

		// 3. HTML 파싱.
		Document doc = conn.get(); // conn.post();

		Elements elements = doc.select(this.selectHTML);

		// 4. HTML 출력
		for (int i = 0; i < elements.size(); i++) {
			for (int j = 0; j < elements.get(i).children().size(); j++) {
				// DTO클래스를 만들어서 하나씩 집어넣고 날짜같은경우 format하기
				System.out.println(i + "번째 " + j + "열 : " + elements.get(i).child(j).text());
			}
			System.out.println(
					"-------------------------------------------------------------------------------------------------------------------");
		}
	}
}
