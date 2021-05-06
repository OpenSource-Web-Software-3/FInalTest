package crawler;

import java.util.Scanner;
import java.util.Vector;

public class ex {
	public static void main(String[] args) throws Exception {

//		Crawler TOEICCrawler = new Crawler("https://exam.toeic.co.kr/receipt/examSchList.php", ".link_list tbody tr");
//		TOEICCrawler.print();

		// scheType == 1 기술사
		// scheType == 2 기능장
		// scheType == 3 기사 산업기사
		// scheType == 4 기능사

		Crawler QnetCrawler = new Crawler("http://www.q-net.or.kr/crf021.do?id=crf02101s03&IMPL_YY=2021&SERIES_CD=03", ".webCont tbody tr");			
		QnetCrawler.print();

	}
}
