package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.UserDAO;
import user.UserDTO;

/**
 * Servlet implementation class resisterAction
 */
@WebServlet("/registerAction")
public class registerAction extends HttpServlet {
	private String[] strings;
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
		}
		/*
		 * else if (request.getParameter("agreeCheck") == null ||
		 * request.getParameter("agreeCheck").equals("")) { PrintWriter script =
		 * response.getWriter(); script.println("<script>");
		 * script.println("alert('게인정보동의란을 다시 체크해주세요')");
		 * script.println("location.href = 'resister1.do'");
		 * script.println("</script>"); }
		 */
		else {
			UserDTO user = new UserDTO();
			user.setID(request.getParameter("ID"));
			user.setPassword(request.getParameter("password"));
			user.setNickName(request.getParameter("nickname"));
			user.setName(request.getParameter("name"));
			user.setPhoneNum(request.getParameter("phoneNum"));
			user.setEmail(request.getParameter("email"));

			if (user.getID() == null || user.getID().equals("") || user.getPassword() == null
					|| user.getPassword().equals("") || user.getNickName() == null || user.getNickName().equals("")
					|| request.getParameter("nickname") == null || request.getParameter("nickname").equals("")
					|| request.getParameter("name") == null || request.getParameter("name").equals("")
					|| request.getParameter("email") == null || request.getParameter("email").equals("")
					|| request.getParameter("phoneNum") == null || request.getParameter("phoneNum").equals("")) {
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('입력되지 않는 정보가 있습니다.')");
				script.println("history.back()");
				script.println("</script>");
				return;
			}
			// 비밀번호가 일치하는지 확인
//			else if (!request.getParameter("password1").equals(request.getParameter("password2"))) {
//				PrintWriter script = response.getWriter();
//				script.println("<script>");
//				script.println("alert('비밀번호가 일치하지않습니다')");
//				script.println("history.back()");
//				script.println("</script>");
//				return;
//			}
			else {
				UserDAO userDao = new UserDAO();

				int checkNickName = userDao.checkNickName(user.getNickName());
				if (checkNickName == -1) {
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("alert('중복되는 닉네임입니다.')");
					script.println("history.back()");
					script.println("</script>");
					return;
				}
				else if (checkNickName == -2) {
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("alert('DB오류')");
					script.println("history.back()");
					script.println("</script>");
					return;
				}
				else {
					int result = userDao.join(user);
					if (result == -1) {
						PrintWriter script = response.getWriter();
						script.println("<script>");
						script.println("alert('이미 존재하는 아이디 입니다.')");
						script.println("history.back()");
						script.println("</script>");
					} else { // 회원가입 성공
						PrintWriter script = response.getWriter();
						script.println("<script>");
						script.println("alert('회원가입하였습니다')");
						script.println("location.href = 'index.do'");
						script.println("</script>");
					}
				}
			}
		}
	}
}