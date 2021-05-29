package crawler;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.sun.scenario.effect.LinearConvolveCoreEffect;

import license.LicenseDTO;

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

	// 크롤링한 데이터를 list에 맞게 저장
	public ArrayList<LicenseDTO> getQnetList() throws IOException {
		ArrayList<LicenseDTO> list = new ArrayList<LicenseDTO>();

		// 2. Connection 생성
		Connection conn = Jsoup.connect(this.URL);
		// 3. HTML 파싱.
		Document doc = conn.get(); // conn.post();
		Elements elements = doc.select(this.selectHTML);

		for (int i = 0; i < elements.size(); i++) {
			LicenseDTO license1 = new LicenseDTO(); // qnet의 필기시험을 담을 항목
			LicenseDTO license2 = new LicenseDTO(); // qnet의 실기시험을 담을 항목

			license1.setLicenseName("정보처리기사");
			license1.setLicenseType(elements.get(i).child(0).text() + " 필기");
			license1.setLicenseDate(elements.get(i).child(2).text().trim());
			license1.setLicenseTime("no notice"); // 시간 안나와 있음 -> 문자로
			license1.setApplyPeriod(elements.get(i).child(1).text().trim()); // 시간 안나와 있음 -> 문자로
			license1.setLicenseURL(this.URL);

			license2.setLicenseName("정보처리기사");
			license2.setLicenseType(elements.get(i).child(0).text() + " 실기");
			license2.setLicenseDate(elements.get(i).child(5).text().trim());
			license2.setLicenseTime("no notice"); // 시간 안나와 있음 -> 문자로
			license2.setApplyPeriod(elements.get(i).child(4).text().trim()); // 시간 안나와 있음 -> 문자로
			license2.setLicenseURL(this.URL);

			list.add(license1);
			list.add(license2);

		}

		return list;
	}

	// 크롤링한 데이터를 list에 맞게 저장
	public ArrayList<LicenseDTO> getQnetList(String SERIES_CD) throws IOException {
		ArrayList<LicenseDTO> list = new ArrayList<LicenseDTO>();

		// 2. Connection 생성
		Connection conn = Jsoup.connect(this.URL);
		// 3. HTML 파싱.
		Document doc = conn.get(); // conn.post();
		Elements elements = doc.select(this.selectHTML);

		// SERIES_CD == 1 기술사
		// SERIES_CD == 2 기능장
		// SERIES_CD == 3 기사 산업기사
		// SERIES_CD == 4 기능사

		for (int i = 0; i < elements.size(); i++) {
			LicenseDTO license1 = new LicenseDTO(); // qnet의 필기시험을 담을 항목
			LicenseDTO license2 = new LicenseDTO(); // qnet의 실기시험을 담을 항목

			if (SERIES_CD.equals("01")) {
				license1.setLicenseName("기술사");
				license2.setLicenseName("기술사");
			} else if (SERIES_CD.equals("02")) {
				license1.setLicenseName("기능장");
				license2.setLicenseName("기능장");
			} else if (SERIES_CD.equals("03")) {
				license1.setLicenseName("기사/산업기사");
				license2.setLicenseName("기사/산업기사");
			} else if (SERIES_CD.equals("04")) {
				license1.setLicenseName("기능사");
				license2.setLicenseName("기능사");
			} else {
				System.out.println("Error");
			}

			license1.setLicenseType(elements.get(i).child(0).text() + " 필기");
			license1.setLicenseDate(elements.get(i).child(2).text().trim());
			license1.setLicenseTime("no notice"); // 시간 안나와 있음 -> 문자로
			license1.setLicenseURL(this.URL);

			license2.setLicenseType(elements.get(i).child(0).text() + " 실기");
			license2.setLicenseDate(elements.get(i).child(6).text().trim());
			license2.setLicenseTime("no notice"); // 시간 안나와 있음 -> 문자로
			license2.setLicenseURL(this.URL);

			list.add(license1);
			list.add(license2);

		}

		return list;
	}

	// 크롤링한 데이터를 list에 맞게 저장
	public ArrayList<LicenseDTO> getTOEICList() throws IOException {
		ArrayList<LicenseDTO> list = new ArrayList<LicenseDTO>();

		// 2. Connection 생성
		Connection conn = Jsoup.connect(this.URL);
		// 3. HTML 파싱.
		Document doc = conn.get(); // conn.post();
		Elements elements = doc.select(this.selectHTML);

		for (int i = 0; i < elements.size(); i++) {
			LicenseDTO license = new LicenseDTO(); // qnet의 필기시험을 담을 항목

			license.setLicenseName("TOEIC");
			license.setLicenseType(elements.get(i).child(0).text()); // n회차 저장
			license.setLicenseDate(elements.get(i).child(1).text().substring(0, 10));
			license.setLicenseTime(elements.get(i).child(1).text().substring(18, 23)); // 시간 안나와 있음 -> 일단 해당 날짜로 대체
			license.setApplyPeriod(elements.get(i).child(3).text().substring(7, 17)+" ~ "+elements.get(i).child(3).text().substring(29, 40));
			license.setLicenseURL(this.URL);
			list.add(license);
		}

		return list;
	}

	// 크롤링한 데이터를 list에 맞게 저장
	public ArrayList<LicenseDTO> getTOPCITList() throws IOException {
		ArrayList<LicenseDTO> list = new ArrayList<LicenseDTO>();

		// 2. Connection 생성
		Connection conn = Jsoup.connect(this.URL);
		// 3. HTML 파싱.
		Document doc = conn.get(); // conn.post();
		Elements elements = doc.select(this.selectHTML);

		LicenseDTO license = new LicenseDTO(); // qnet의 필기시험을 담을 항목

		license.setLicenseName("TOPCIT");
		license.setLicenseType("1회차"); // n회차 저장
		// 이거 원서접수기간
		// elements.get(0).child(1).text().substring(0,
		// 12).trim()+"-"+elements.get(0).child(1).text().substring(26,38).trim()
		license.setLicenseDate(elements.get(2).child(1).text().substring(0, 12));
		license.setLicenseTime(elements.get(2).child(1).text().substring(0, 12)); // 시간 안나와 있음 -> 일단 해당 날짜로 대체
		license.setApplyPeriod(elements.get(0).child(1).text().substring(0, 12).trim()+" ~ "+elements.get(0).child(1).text().substring(26,38).trim());
		license.setLicenseURL(this.URL);
		list.add(license);

		return list;
	}

	// test를 위한 print
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
