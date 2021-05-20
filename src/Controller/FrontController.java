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

		if (com.contains("/index.do")) {
			viewPage = "index.jsp";
		} 
		else if (com.contains("/registerAction.do")) {
			viewPage = "../registerAction";
		} 
		else if (com.contains("/login.do")) {
			viewPage = "login.jsp";
		}
		else if (com.contains("/loginAction.do")) {
			viewPage = "../loginAction";
		} 
		else if (com.contains("/findInfoAction.do")) { // ID or 비밀번호 찾기 버튼 클릭 시
			viewPage = "../findInfoAction";
		}
		else if (com.contains("/licenseListAction.do")) {
			viewPage = "../licenseListAction";
		}
		else if (com.contains("/commuListAction.do")) {
			viewPage = "../commuListAction";
		}
		else if (com.contains("/licenseScrapAction.do")) {
			viewPage = "../licenseScrapAction";
		} 
		else if (com.contains("/commuScrapAction.do")) {
			viewPage = "../../commuScrapAction";
		} 
		else if (com.contains("/writeAction.do")) {
			viewPage = "../writeAction";
		} 
		else if (com.contains("/deleteAction.do")) {
			viewPage = "../deleteAction";
		} 
		else if (com.contains("/commentWriteAction.do")) {
			viewPage = "../commentWriteAction";
		} 
//		else if (com.contains("/purchaseAction.do")) { // 구매하기 버튼
//			viewPage = "../purchaseAction";
//		} else if (com.contains("/purchaseListAction.do")) { // 구매내역 목록 이동 시
//			viewPage = "../purchaseListAction";
//		} else if (com.contains("/shopCartAction.do")) { // 장바구니 목록 보여주기
//			viewPage = "../shopCartAction";
//		} else if (com.contains("/addToCartAction.do")) { // 장바구니 추가 버튼 클릭시
//			viewPage = "../addToCartAction";
//		} else if (com.contains("/insertItemAction.do")) { // 상품 추가 시
//			viewPage = "../insertItemAction";
//		} else if (com.contains("/getItemList_to_SubCateAction.do")) { 
//			viewPage = "../getItemList_to_SubCateAction";
//		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response); // RequestDispatcher 현재 req와 res객체를 공유한다.
	}
}
