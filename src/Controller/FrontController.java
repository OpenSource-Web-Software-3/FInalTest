package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.do")
public class FrontController extends HttpServlet {
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

		String viewPage = null;
		String URI = request.getRequestURI(); // /MidtermTest/view.do
		String contextPath = request.getContextPath(); // /MidtermTest
		String com = URI.substring(contextPath.length()); // /view.do

		System.out.println(URI);
		System.out.println(contextPath);

		if (com.contains("/index.do")) { //index 페이지 이동 
			viewPage = "index.jsp";
		} 
		else if (com.contains("/registerAction.do")) { //회원가입 기능
			viewPage = "../registerAction";
		} 
		else if (com.contains("/login.do")) { //로그인 페이지 이동 
			viewPage = "login.jsp";
		}
		else if (com.contains("/loginAction.do")) { // 로그인 기능 
			viewPage = "../loginAction";
		} 
		else if (com.contains("/logoutAction.do")) { // 로그아웃 기능 
			viewPage = "../logoutAction";
		} 
		else if (com.contains("/findInfoAction.do")) { // ID or 비밀번호 찾기 버튼 클릭 시
			viewPage = "../findInfoAction";
		}
		else if (com.contains("/licenseListAction.do")) { //자격증 리스트 보여주기
			viewPage = "../licenseListAction";
		}
		else if (com.contains("/commuListAction.do")) { //커뮤니티 글 보여주기
			viewPage = "../commuListAction";
		}
		else if (com.contains("/licenseScrapAction.do")) { //자격증 스크랩 AJAX
			viewPage = "licenseScrapAction";
		} 
		else if (com.contains("/commuScrapAction.do")) { //커뮤니티 스크랩 AJAX
			viewPage = "../../commuScrapAction";
		} 
		else if (com.contains("/writeAction.do")) { // 커뮤 글 쓰기
			viewPage = "../writeAction";
		} 
		else if (com.contains("/modifyAction.do")) { // 커뮤 글 수정하기 
			viewPage = "../modifyAction";
		} 
		else if (com.contains("/deleteAction.do")) { // 커뮤 글 삭제
			viewPage = "../deleteAction";
		} 
		else if (com.contains("/commentWriteAction.do")) { // 커뮤 댓글 달기 
			viewPage = "../commentWriteAction";
		} 
		else if (com.contains("/Community_groupingConditionAction.do")) { // 커뮤니티 분류기능 AJAX
			viewPage = "./Community_groupingConditionAction";
		} 
		//QNA
		else if (com.contains("/questionListAction.do")) { //QNA 글 보여주기
			viewPage = "../questionListAction";
		}
		else if (com.contains("/QNAwriteAction.do")) { // QNA 글 쓰기
			viewPage = "../QNAwriteAction";
		} 
		else if (com.contains("/QNAmodifyAction.do")) { // QNA 글 수정하기 
			viewPage = "../QNAmodifyAction";
		} 
		else if (com.contains("/QNAdeleteAction.do")) { // QNA 글 삭제
			viewPage = "../QNAdeleteAction";
		} 
		else if (com.contains("/QNAcommentWriteAction.do")) { // QNA 댓글 달기 
			viewPage = "../QNAcommentWriteAction";
		} 
		else if (com.contains("/withdrawalAction.do")) { // 탈퇴
			viewPage = "../withdrawalAction";
		}
		else if (com.contains("/myPage_checkPasswordAction.do")) { //마이페이지 비밀번호 인증
			viewPage = "../../myPage_checkPasswordAction";
		}
		else if (com.contains("/myPage_changePasswordAction.do")) { //마이페이지 비밀번호 변경
			viewPage = "../../myPage_changePasswordAction";
		}
		else if (com.contains("/myPage_changeEmailAction.do")) { //마이페이지 이메일 변경
			viewPage = "../../myPage_changeEmailAction";
		}
		else if (com.contains("/Calendar_scrapLicenseListAction.do")) { //마이페이지 이메일 변경
			viewPage = "../../Calendar_scrapLicenseListAction";
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response); // RequestDispatcher 현재 req와 res객체를 공유한다.
	}
}
