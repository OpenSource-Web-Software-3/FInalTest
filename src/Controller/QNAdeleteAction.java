package Controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import QnA.QnADAO;
import QnA.QnADTO;
import image.ImageDAO;
import image.ImageDTO;

/**
 * Servlet implementation class deleteAction
 */
@WebServlet("/QNAdeleteAction")
public class QNAdeleteAction extends HttpServlet {
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
		String userID = null;
		if (session.getAttribute("userID") != null) {
			userID = (String) session.getAttribute("userID");
		}
		if (userID == null) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('로그인을 하세요')");
			script.println("history.back()");
			script.println("</script>");
			return;
		}

		int QID = 0;
		if (request.getParameter("QID") != null) {
			QID = Integer.parseInt(request.getParameter("QID"));
		}
		
		if (QID <= 0) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('유효하지 않은 글입니다')");
			script.println("history.back()");
			script.println("</script>");
			return;
		}
		// 파일관련 변수
		ServletContext application = request.getServletContext();
		String directory = application.getRealPath("/qnaFile/");
		ImageDAO imageDao = new ImageDAO();
		ArrayList<ImageDTO> imageList = new ArrayList<>();
		ArrayList<ImageDTO> documentList = new ArrayList<>();
		imageList = imageDao.getFiles(QID,2);
//		documentList = 여기 코딩해야함
		
		QnADAO qnaDao = new QnADAO();
		QnADTO qnaDto = qnaDao.getQnA(QID);
		if (!userID.equals(qnaDto.getID())) // 동일한 아이디가 아니라면
		{
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('권한이 없습니다.')");
			script.println("history.back()");
			script.println("</script>");
			return;
		} else {
			int result = qnaDao.delete(QID);

			if (result == -1) {
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('글삭제에 실패했습니다.')");
				script.println("history.back()");
				script.println("</script>");
				return;
			} else {
				PrintWriter script = response.getWriter();
				deleteFileFunction(imageList, directory, QID); //기존 파일 삭제
				script.println("<script>");
				script.println("alert('삭제했습니다.')");
				script.println("location.href = 'questionListAction.do'");
				script.println("</script>");

			}
		}

	}

	// 기존파일 삭제
	public void deleteFileFunction(ArrayList<ImageDTO> fileList, String directory, int QID) {
		for (int i = 0; i < fileList.size(); i++) {
			File prevfile = new File(directory + fileList.get(i).getFileRealName()); // 실제 파일도 같이 삭제
			prevfile.delete();
		}
		new ImageDAO().delete(QID,2); // 기존에 있던 사진을 먼저 삭제 한다

	}

	// 현재 날라온 파일 삭제
	public void deleteFileFunction(MultipartRequest multipartRequest, String directory) {
		Enumeration fileNames = multipartRequest.getFileNames();
		while (fileNames.hasMoreElements()) {
			String parameter = (String) fileNames.nextElement();
			String fileRealName = multipartRequest.getFilesystemName(parameter);
			File prevfile = new File(directory + fileRealName); // 실제 파일도 같이 삭제
			prevfile.delete();
		}
	}

}
