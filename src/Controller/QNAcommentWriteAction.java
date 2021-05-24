package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import QnAcomment.QnacommentDAO;
import comment.CommentDAO;
import user.UserDAO;

@WebServlet("/QNAcommentWriteAction")
public class QNAcommentWriteAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession(true);
		
		int QID = Integer.parseInt(request.getParameter("QID"));
		String content = request.getParameter("content"); // 댓글 내용
		
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

		} else {
			if (content == null || content.equals("") || request.getParameter("QID") == null || request.getParameter("QID").equals("")) { // 댓글 내용이 없다면
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('댓글내용을 입력해주세요')");
				script.println("history.back()");
				script.println("</script>");
				return;
			} else { // 댓글 입력
				QnacommentDAO qnacommentDAO = new QnacommentDAO();
				
				UserDAO userDao = new UserDAO();
				//나중에 댓글에 답글을 달 수 있는 버튼이 생기면 그떄 수정하기 parentID 를 수정하기
				int result = qnacommentDAO.commentWrite(QID, userID, content);

				if (result == -1) {
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("alert('댓글달기에 실패했습니다.')");
					script.println("history.back()");
					script.println("</script>");
				} else {
					PrintWriter script = response.getWriter();
					script.println("<script>");
//					script.println("location.href = document.referrer"); // 뒤로가기 후 새로고침
					script.println("location.href = './questions/read.jsp?QID="+QID+"'");
					script.println("</script>");

				}
			}
		}

	}
}
