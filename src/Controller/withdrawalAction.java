package Controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import QnA.QnADAO;
import QnAcomment.QnacommentDAO;
import comment.CommentDAO;
import communication.CommunicationDAO;
import user.UserDAO;

/**
 * Servlet implementation class deleteAction
 */
@WebServlet("/withdrawalAction")
public class withdrawalAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(true);

		String userID = null;
		if (session.getAttribute("userID") != null) {
			userID = (String) session.getAttribute("userID");
		}
		if (userID == null) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('로그인을 하세요')");
			script.println("location.href = 'login.do'");
			script.println("</script>");

		}
		String password = null;

		if (request.getParameter("password") != null) {
			password = request.getParameter("password");
		}

		UserDAO userDao = new UserDAO();

		int result = userDao.login(userID, password); // 로그인 함수와 같은 기능을 함으로 password 체크를 login함수로 대체한다

		if (result == 1) { // 패스워드가 아이디와 일치
			int checked = userDao.secession(userID);
			if (checked == -1) {
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('DB오류입니다.')");
				script.println("history.back()");
				script.println("</script>");
			} else { // 탈퇴 성공
				session.invalidate(); //세션해제
				
				//탈퇴한 회원으로 이름 바꾸기
				CommunicationDAO communicationDao = new CommunicationDAO();
				CommentDAO commentDao = new CommentDAO();
				QnADAO qnaDao = new QnADAO();
				QnacommentDAO qnacommentDao = new QnacommentDAO();
				
				communicationDao.secessionUpdate(userID);
				commentDao.secessionUpdate(userID);
				qnaDao.secessionUpdate(userID);
				qnacommentDao.secessionUpdate(userID);
				
				
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('서비스를 사용해 주셔서 감사합니다')");
				script.println("location.href = 'index.do'");
				script.println("</script>");
			}

		} else if (result == 0) { // 패스워드 불일치
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('비밀번호가 다릅니다')");
			script.println("history.back()");
			script.println("</script>");
		} else { // DB오류
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('DB오류입니다.')");
			script.println("history.back()");
			script.println("</script>");
		}

	}

}
