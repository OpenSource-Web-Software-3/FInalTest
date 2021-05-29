package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crawler.Crawler;
import license.LicenseDAO;
import license.LicenseDTO;

/**
 * Servlet implementation class licenseListAction
 */
@WebServlet("/licenseListAction")
public class licenseListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);

	}

	public void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		ServletContext context = this.getServletContext();
		RequestDispatcher dispatcher = null;

		LicenseDAO licenseDao = new LicenseDAO();

		// *** 하루 한번 update ***
		// ==========================================================================================================================================================================
		
		
		// -------------<크롤러 객체 생성 : qnet은 if문으로 각각 받아오기 기술사 기사 기능사  기사/산업기사>-------------
		if (licenseDao.getCount() == 0) {
			
			
			/*******************************************************************************************************/
			/**********************************기존 소스 (기사/산업기사 기술사 기능사 기능사)********************************************************/
			/*******************************************************************************************************/
//			String QnetURL = "http://www.q-net.or.kr/crf021.do?id=crf02101s03&IMPL_YY=2021&SERIES_CD=";
//			String QnetSelect = ".webCont tbody tr";
			// -------------<QnetURL 마지막 URL에 들어가는 SERIES_CD의 값을 저장해서 URL뒤에 붙혀 저장하기>-------------
//			String SERIES_CD1 = "01"; // 기술사
//			String SERIES_CD2 = "02"; // 기능장
//			String SERIES_CD3 = "03"; // 기사/산업기사
//			String SERIES_CD4 = "04"; // 기능사
			
			// -------------<QnetURL 마지막 URL에 들어가는 SERIES_CD의 값을 저장해서 URL뒤에 붙혀 저장하기>-------------
//			Qnet.setURL(QnetURL + SERIES_CD1);
//			ArrayList<LicenseDTO> list1 = Qnet.getQnetList("01"); // 기술사
//			Qnet.setURL(QnetURL + SERIES_CD2);
//			ArrayList<LicenseDTO> list2 = Qnet.getQnetList("02"); // 기능장
//			Qnet.setURL(QnetURL + SERIES_CD3);
//			ArrayList<LicenseDTO> list3 = Qnet.getQnetList("03"); // 기사/산업기사
//			Qnet.setURL(QnetURL + SERIES_CD4);
//			ArrayList<LicenseDTO> list4 = Qnet.getQnetList("04"); // 기능사
			/*******************************************************************************************************/
			/*******************************************************************************************************/
			/*******************************************************************************************************/

			/*
			 * 
			 * 
			 * 
			 * 
			 * 
			 */
			
			/****************정보처리기사 ver****************/
			String QnetURL = "https://www.q-net.or.kr/crf005.do?id=crf00503s02&gSite=Q&gId=&jmCd=1320&jmInfoDivCcd=B0&jmNm=%C1%A4%BA%B8%C3%B3%B8%AE%B1%E2%BB%E7";
			String QnetSelect = ".tdCenter tbody tr";
			/*********************************************/
			
			String TOEICURL = "https://exam.toeic.co.kr/receipt/examSchList.php";
			String TOEICSelect = ".link_list tbody tr";

			String TOPCITURL = "https://sw7up.cbnu.ac.kr/community/notice/604afaa0f7b9274da64e86db";
			String TOPCITSelect = ".ck-rounded-corners .table table tbody tr";
			
			Crawler Qnet = new Crawler(QnetURL, QnetSelect);
			Crawler TOEIC = new Crawler(TOEICURL, TOEICSelect);
			Crawler TOPCIT = new Crawler(TOPCITURL, TOPCITSelect);
			// -------------<QnetURL 마지막 URL에 들어가는 SERIES_CD의 값을 저장해서 URL뒤에 붙혀 저장하기>-------------
			
			ArrayList<LicenseDTO> list1 = Qnet.getQnetList(); // 정보처리기사
			ArrayList<LicenseDTO> list2 = TOEIC.getTOEICList(); // TOEIC
			ArrayList<LicenseDTO> list3 = TOPCIT.getTOPCITList(); // TOPCIT
			
			// -------------<크롤링한 데이터를 정리해놓은 list들을 DB에 insert>-------------
			int[] results = new int[3];

			results[0] = licenseDao.insertCrawlingData(list1);
			results[1] = licenseDao.insertCrawlingData(list2);
			results[2] = licenseDao.insertCrawlingData(list3);

			// -----------<DB 오류 체크>------------
			for (int i = 0; i < results.length; i++) {
				if (results[i] == -1) {
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("alert('" + i + "번째 list에서 DB오류입니다.')");
					script.println("location.href = 'index.do'");
					script.println("</script>");
				}
			}
		}

		// ==========================================================================================================================================================================

		ArrayList<String> licenseNameList = new ArrayList<String>();
		ArrayList<LicenseDTO> licenseList = new ArrayList<LicenseDTO>();

		// licenseNameList 가져오기
		licenseNameList = licenseDao.getLicenseNameList();

		if (licenseNameList.size() == 0)
			return; // 자격증이 DB에 저장되어 있지 않은 경우

		// URL로 요청받은 licenseName에 해당하는 licenseList 가져오기 (실제 자격증 정보가 뿌려지기위해 데이터를 저장하는 부분)
		String licenseName = null;
		if (request.getParameter("licenseName") != null) {
			licenseName = request.getParameter("licenseName");
		}

		if (licenseName == null || licenseName.equals("")) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('잘못된 접근입니다')");
			script.println("history.back()");
			script.println("</script>");
			return;
		} else {
			licenseList = licenseDao.getLicenseList(licenseName);

		}

		request.setAttribute("licenseName", licenseName);
		request.setAttribute("licenseNameList", licenseNameList);
		request.setAttribute("licenseList", licenseList);

		dispatcher = context.getRequestDispatcher("/Web-source/licenseList.jsp");
		dispatcher.forward(request, response);
	}

}