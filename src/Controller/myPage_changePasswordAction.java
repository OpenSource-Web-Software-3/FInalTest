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
 * Servlet implementation class myPage_checkPasswordAction
 */
@WebServlet("/myPage_changePasswordAction")
public class myPage_changePasswordAction extends HttpServlet {
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
		if (userID == null) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('로그인을 해주세요')");
			script.println("history.back()");
			script.println("</script>");
			return;
		}
		
		String newPassword1 = null;
		String newPassword2 = null;
		
		newPassword1 = request.getParameter("newPassword1");
		newPassword2 = request.getParameter("newPassword2");

		UserDAO userDao = new UserDAO();
		int result = userDao.changePassword(userID, newPassword1); 
		if (result == 1) session.invalidate(); //세션 할당 해제
		
		response.getWriter().write(Integer.valueOf(result).toString());
	}

}
