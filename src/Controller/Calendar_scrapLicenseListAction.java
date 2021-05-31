package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import license.LicenseDAO;
import license.LicenseDTO;
import licensescrap.LicensescrapDAO;
import licensescrap.LicensescrapDTO;

/**
 * Servlet implementation class Calendar_scrapLicenseListAction
 */
@WebServlet("/Calendar_scrapLicenseListAction")
public class Calendar_scrapLicenseListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession(true);

		String year = null;
		String month = null;

		if (request.getParameter("year") != null) {
			year = request.getParameter("year");
		}
		if (request.getParameter("month") != null) {
			month = request.getParameter("month");
		}

		String userID = null;
		if (session.getAttribute("userID") != null) {
			userID = (String) session.getAttribute("userID");
		}

		if (userID == null) {
			response.getWriter().write("-1"); // 로그인안되어 있음
			return;
		} else {

			if (year == null || year.equals("") || month == null || month.equals("")) {
				response.getWriter().write("0"); // 잘못된 접근 경로
			} else {
				// scrap가져오기
				LicensescrapDAO licenseScrapDAO = new LicensescrapDAO();
				ArrayList<LicensescrapDTO> licenseScrapList = new ArrayList<LicensescrapDTO>();
				licenseScrapList = licenseScrapDAO.getLicensescrapList(userID);

				// scrap에 해당하는 licenseDTO 가져오기
				LicenseDAO licenseDAO = new LicenseDAO();
				ArrayList<LicenseDTO> licenseList = new ArrayList<LicenseDTO>();

				for (int i = 0; i < licenseScrapList.size(); i++) {
					LicenseDTO license = new LicenseDTO();
					license = licenseDAO.getLicense(licenseScrapList.get(i).getLicenseID());
					licenseList.add(license);
				}

				int result;
				response.getWriter().write(getJSON(licenseList));
			}
		}

	}

	public String getJSON(ArrayList<LicenseDTO> licenseList) {

		StringBuffer result = new StringBuffer("");
		result.append("{\"result\":[");
		for (int i = 0; i < licenseList.size(); i++) {

			result.append("[{\"value\": \"" + licenseList.get(i).getLicenseID() + "\"},");
			result.append("{\"value\": \"" + licenseList.get(i).getLicenseName() + "\"},");
			result.append("{\"value\": \"" + licenseList.get(i).getLicenseType() + "\"},");
			result.append("{\"value\": \"" + licenseList.get(i).getLicenseDate() + "\"},");
			result.append("{\"value\": \"" + licenseList.get(i).getLicenseTime() + "\"},");
			result.append("{\"value\": \"" + licenseList.get(i).getLicenseURL() + "\"},");
			result.append("{\"value\": \"" + licenseList.get(i).getApplyPeriod() + "\"}]");
			if (i != licenseList.size() - 1) {
				result.append(",");
			}
		}
		result.append("]}");
		// 이렇게 가져와야 오류가 안난다

		return result.toString();

	}

}
