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
import javax.servlet.http.HttpSession;

import QnA.QnADAO;
import QnA.QnADTO;
import communication.CommunicationDAO;
import communication.CommunicationDTO;

/**
 * Servlet implementation class licenseListAction
 */
@WebServlet("/questionListAction")
public class questionListAction extends HttpServlet {
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
		
		HttpSession session = request.getSession(true);

		String userID = null;
		if (session.getAttribute("userID") != null) {
			userID = (String) session.getAttribute("userID");
		}
		if (userID == null) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('로그인을 해주세요')");
			script.println("history.back()");
			script.println("</script>");
			return;
		}
		
		QnADAO qnaDao = new QnADAO();
		
		ArrayList<QnADTO> qnaList = new ArrayList<QnADTO>();
		if (userID.equals("LINS")) { //관리자로 로그인 했을 시 모든 QnA 글을 보여주기
			qnaList = qnaDao.getCommunicationList_ADMIN();
		}
		else {
			qnaList = qnaDao.getCommunicationList(userID);
		}
		
		request.setAttribute("qnaList", qnaList);

		dispatcher = context.getRequestDispatcher("/Web-source/questions/questionList.jsp");
		dispatcher.forward(request, response);
	}

}