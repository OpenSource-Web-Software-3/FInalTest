package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import licensescrap.LicensescrapDAO;

@WebServlet("/scrapAction")
public class scrapAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession(true);

		Boolean active = Boolean.parseBoolean(request.getParameter("active"));
		int licenseID = Integer.parseInt(request.getParameter("licenseID"));

		String userID = null;
		if (session.getAttribute("userID") != null) {
			userID = (String) session.getAttribute("userID");
		}
		if (userID == null) {
			response.getWriter().write("-1"); //로그인안되어 있음
			return;
		} else {
			if (request.getParameter("active") == null || request.getParameter("active").equals("")
					|| request.getParameter("licenseID") == null || request.getParameter("licenseID").equals("")) {
				response.getWriter().write("0"); // 잘못된 접근 경로
			} else {
				LicensescrapDAO licenseScrapDAO = new LicensescrapDAO();
				int result;
				if (active) {
					result = licenseScrapDAO.addScrap(userID, licenseID);
				} else {
					result = licenseScrapDAO.deleteScrap(userID, licenseID);
				}
				response.getWriter().write(Integer.valueOf(result).toString());
			}
		}

	}

}
