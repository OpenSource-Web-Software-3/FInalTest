package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.UserDAO;


/**
 * Servlet implementation class loginAction
 */
@WebServlet("/loginAction")
public class loginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(true);

		String userID = null;
		if (session.getAttribute("userID") != null) {
			userID = (String) session.getAttribute("userID");
		}
		if (userID != null) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('이미 로그인 되어 있습니다.')");
			script.println("location.href = 'index.do'");
			script.println("</script>");
		} else if (request.getParameter("ID") == null || request.getParameter("ID").equals("")
				|| request.getParameter("password") == null || request.getParameter("password").equals("")) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('아이디와 비밀번호를 입력해주세요')");
			script.println("history.back()");
			script.println("</script>");
		} else {

			UserDAO userDao = new UserDAO();
			int result = userDao.login(request.getParameter("ID"), request.getParameter("password"));
			if (result == 1) {

				session.setAttribute("userID", request.getParameter("ID")); // allocate session
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("location.href = 'index.do'");
				script.println("</script>");
			} else if (result == 0) {
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('비밀번호가 틀립니다')");
				script.println("history.back()");
				script.println("</script>");
			} else if (result == -1) {
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('존재하지 않는 아이디 입니다')");
				script.println("history.back()");
				script.println("</script>");
			} else if (result == -2) {
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('데이터베이스 오류')");
				script.println("history.back()");
				script.println("</script>");
			}
		}
	}

}
