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

import communication.CommunicationDAO;
import communication.CommunicationDTO;

/**
 * Servlet implementation class licenseListAction
 */
@WebServlet("/commuListAction")
public class commuListAction extends HttpServlet {
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

		CommunicationDAO communicationDAO = new CommunicationDAO();

		ArrayList<CommunicationDTO> commuList = new ArrayList<CommunicationDTO>();

		// URL로 요청받은 licenseName에 해당하는 licenseList 가져오기 (실제 자격증 정보가 뿌려지기위해 데이터를 저장하는 부분)
		String category = null;
		if (request.getParameter("category") != null) {
			category = request.getParameter("category");
		}

		if (category == null || category.equals("")) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('잘못된 접근입니다')");
			script.println("history.back()");
			script.println("</script>");
			return;
		} else {
			commuList = communicationDAO.getCommunicationList(category);
		}
		
		request.setAttribute("commuList", commuList);
		request.setAttribute("category", category);

		dispatcher = context.getRequestDispatcher("/Web-source/community/commuList.jsp");
		dispatcher.forward(request, response);
	}

}