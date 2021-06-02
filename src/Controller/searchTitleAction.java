package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comment.CommentDAO;
import communication.CommunicationDAO;
import communication.CommunicationDTO;

@WebServlet("/searchTitleAction")
public class searchTitleAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 검색 게시판

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String bbsTitle = request.getParameter("bbsTitle");
		String category = request.getParameter("category");
		String sort = request.getParameter("sort");
		String sub_category = request.getParameter("sub_category");

		response.getWriter().write(getJSON(bbsTitle, category,sub_category,sort));
		
	}

	public String getJSON(String bbsTitle, String category, String sub_category, String sort) {

		StringBuffer result = new StringBuffer("");
		result.append("{\"result\":[");
		CommunicationDAO communicationDAO = new CommunicationDAO();
		ArrayList<CommunicationDTO> commuList = new ArrayList<CommunicationDTO>();
		System.out.println("bbsTitle " + bbsTitle);
		System.out.println("category " + category);
		System.out.println("sort " + sort);
		System.out.println("sub_category " + sub_category);
		if (bbsTitle.equals("") && (sub_category == null || sub_category.equals("")) && (sort == null || sort.equals(""))) {
			commuList = communicationDAO.getCommunicationList(category); // 전체 목록 보여주기
		} else {
			commuList = communicationDAO.search(bbsTitle, category, sub_category, sort); //검색관련 리스트 보여주기
		}

		CommentDAO commentDao = new CommentDAO();

		for (int i = 0; i < commuList.size(); i++) {
			result.append("[{\"value\": \"" + commuList.get(i).getWritingID() + "\"},");
			result.append("{\"value\": \"" + commuList.get(i).getCategory() + "\"},");
			result.append("{\"value\": \"" + commuList.get(i).getSub_category() + "\"},");
			result.append("{\"value\": \"" + commuList.get(i).getTitle() + "\"},");
			result.append("{\"value\": \"" + commuList.get(i).getID() + "\"},");
			result.append("{\"value\": \"" + commuList.get(i).getNickName() + "\"},");// 9
			result.append("{\"value\": \"" + commuList.get(i).getCommuDate() + "\"},");// 9
			result.append("{\"value\": \"" + commuList.get(i).getScrapCount() + "\"},");// 9
			result.append("{\"value\": \"" + commuList.get(i).getView() + "\"},");// 9
			result.append("{\"value\": \"" + commuList.get(i).getAvailable() + "\"},");// 9
			result.append("{\"value\": \"" + commentDao.getAllComment(commuList.get(i).getWritingID()) + "\"}]");
			if (i != commuList.size() - 1) {
				result.append(",");
			}
		}
		result.append("]}");

		return result.toString();

	}

}
