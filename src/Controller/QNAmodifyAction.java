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
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import QnA.QnADAO;
import QnA.QnADTO;
import communication.CommunicationDAO;
import communication.CommunicationDTO;
import image.ImageDAO;
import image.ImageDTO;

/**
 * Servlet implementation class updateAction
 */
@WebServlet("/QNAmodifyAction")
public class QNAmodifyAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("UTF-8");

		ServletContext application = request.getServletContext();
		HttpSession session = request.getSession(true);

		String userID = null;
		if (session.getAttribute("userID") != null) {
			userID = (String) session.getAttribute("userID");
		}
		if (userID == null) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('로그인을 하세요')");
			script.println("location.href = 'index.do'");
			script.println("</script>");
			return;
		}

		String fileDirectory = application.getRealPath("/qnaFile/");
		int maxSize = 1024 * 1024 * 100; // 100MB
		String encoding = "UTF-8";
		MultipartRequest multipartRequest = new MultipartRequest(request, fileDirectory, maxSize, encoding,
				new DefaultFileRenamePolicy());

		String title = (String) multipartRequest.getParameter("title");
		String content = (String) multipartRequest.getParameter("content");

		int QID = 0;
//		System.out.println(multipartRequest.getParameter("writingID") +" ?");
		if (multipartRequest.getParameter("QID") != null) {
			QID = Integer.parseInt((String) multipartRequest.getParameter("QID"));
		}
		if (QID == 0) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('유효하지 않은 글입니다')");
			script.println("location.href = 'questionListAction.do'");
			script.println("</script>");
			deleteFileFunction(multipartRequest, fileDirectory); // 현재 파일 삭제
			return;
		}

		QnADAO qnaDao = new QnADAO();
		QnADTO qnaDto = qnaDao.getQnA(QID);
		
		if (!userID.equals(qnaDto.getID())) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('권한이 없습니다.')");
			script.println("location.href = 'index.do'");
			script.println("</script>");
			deleteFileFunction(multipartRequest, fileDirectory); // 현재 파일 삭제
			return;
		} else {
			if (title == null || content == null || title.equals("") || content.equals("")) {
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('입력되지 않는 정보가 있습니다.')");
				script.println("history.back()");
				script.println("</script>");
				deleteFileFunction(multipartRequest, fileDirectory); // 현재 파일 삭제
				return;
			} else {
				int result = qnaDao.update(QID, title, content);
				if (result == -1) {
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("alert('글수정에 실패했습니다.')");
					script.println("history.back()");
					script.println("</script>");
					deleteFileFunction(multipartRequest, fileDirectory); // 현재 파일 삭제
					return;
				} else {
					// 글쓰기 성공시 파일도 저장하기
					Enumeration fileNames = multipartRequest.getFileNames(); // type = file
					while (fileNames.hasMoreElements()) {

						String parameter = (String) fileNames.nextElement();
//						String [] arr = multipartRequest.getParameterValues(parameter);
						String fileName = multipartRequest.getOriginalFileName(parameter);
						String fileRealName = multipartRequest.getFilesystemName(parameter);
						if (fileName == null)
							continue; // 파일을 다른곳에 넣었다면

						// 시큐어 코딩 (기존 코드)
//						if (!fileName.endsWith(".PNG") && !fileName.endsWith(".jpg") && !fileName.endsWith(".png") && !fileName.endsWith(".JPEG") && !fileName.endsWith(".JPG") 	&& !fileName.endsWith(".jpeg")) {
						if (fileName.endsWith("??")) {

							File file = new File(fileDirectory + fileRealName);
							file.delete();
							PrintWriter script = response.getWriter();
							script.println("<script>");
							script.println("alert('파일을 업로드 하지 못했습니다.')");
							script.println("location.href = './questions/read.jsp?QID="+ QID + "'");
							script.println("</script>");
							deleteFileFunction(multipartRequest, fileDirectory); // 현재 파일 삭제

						} else {
							ImageDAO imageDao = new ImageDAO();
							ImageDTO imageDto = new ImageDTO();
							
							imageDto = imageDao.getFile(QID, parameter);
							
							if (imageDto != null) { // 파일을 업로드 하지 않고 글을 썼다가 파일을 추가로 업로드 하는경우는 기존 파일이 없기대문에 delete를 해주지
													// 않음
								deleteFileFunction(imageDto, fileDirectory, QID, parameter);
							}

							imageDao.upload(QID, parameter, fileName, fileRealName);
						}
					}

					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("alert('수정했습니다')");
					script.println("location.href = './questions/read.jsp?QID="+ QID + "'");
					script.println("</script>");

				}
			}
		}
	}

	// 기존파일 삭제 : file업로드까지 성공했을때 사용하는 함수
	public void deleteFileFunction(ImageDTO imageDto, String directory, int bbsID, String parameter) {
		File file = new File(directory + imageDto.getFileRealName()); // 실제 파일도 같이 삭제
		file.delete();
		new ImageDAO().delete(bbsID, parameter); // 기존에 있던 사진을 먼저 삭제

	}

	// 현재 날라온 파일 모두 삭제 : 예외처리부분에서 사용하는 함수
	public void deleteFileFunction(MultipartRequest multipartRequest, String directory) {
		Enumeration fileNames = multipartRequest.getFileNames();
		while (fileNames.hasMoreElements()) {
			String parameter = (String) fileNames.nextElement();
			String fileRealName = multipartRequest.getFilesystemName(parameter);
			File file = new File(directory + fileRealName); // 실제 파일도 같이 삭제
			file.delete();
		}
	}
}
