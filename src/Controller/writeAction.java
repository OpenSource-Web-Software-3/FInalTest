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

import communication.CommunicationDAO;
import image.ImageDAO;
import image.ImageDTO;
import user.UserDAO;

/**
 * Servlet implementation class writeAction
 */
@WebServlet("/writeAction")
public class writeAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("UTF-8");

		ServletContext application = request.getServletContext();
		HttpSession session = request.getSession(true);

		String fileDirectory = application.getRealPath("/file/"); // 일반 파일

		int maxSize = 1024 * 1024 * 100; // 100MB
		String encoding = "UTF-8";
		MultipartRequest multipartRequest = new MultipartRequest(request, fileDirectory, maxSize, encoding,
				new DefaultFileRenamePolicy());
		String title = (String) multipartRequest.getParameter("title");
		String content = (String) multipartRequest.getParameter("content");
		String category = (String) multipartRequest.getParameter("category");
		
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
		} else if (category == null || category.equals("")) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('잘못된 접근입니다.')");
			script.println("history.back()");
			script.println("</script>");
			return;
		} else {
			if (title == null || content == null || title.equals("") || content.equals("")) {
				System.out.println(title);
				System.out.println(content);
				
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('입력되지 않는 정보가 있습니다.')");
				script.println("history.back()");
				script.println("</script>");
				deleteFileFunction(multipartRequest, fileDirectory); // 현재 파일 삭제
			} else {
				CommunicationDAO communicationDAO = new CommunicationDAO();
				UserDAO userDao = new UserDAO();

				int result = communicationDAO.write(category, title, userID, userDao.getUser(userID).getNickName(), content);

				if (result == -1) {
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("alert('글쓰기에 실패했습니다.(DB오류)')");
					script.println("history.back()");
					script.println("</script>");
					deleteFileFunction(multipartRequest, fileDirectory);
				} else {
					// 글쓰기 성공시 파일도 저장하기
					Enumeration fileNames = multipartRequest.getFileNames(); // type = file
					while (fileNames.hasMoreElements()) {
						String parameter = (String) fileNames.nextElement();
						String fileName = multipartRequest.getOriginalFileName(parameter);
						String fileRealName = multipartRequest.getFilesystemName(parameter);

						// 단일 파일시
						// String fileName = multipartRequest.getOriginalFileName("file"); //request요청
						// 객체 file (from form)
						// String fileRealName = multipartRequest.getFilesystemName("file"); //실제로 서버에
						// 업로드가 된 파일 시스템 name 을 가져온다

						if (fileName == null)
							continue; // 파일을 다른곳에 넣었다면

						// 시큐어 코딩
						if (!fileName.endsWith(".PNG") && !fileName.endsWith(".jpg") && !fileName.endsWith(".png")
								&& !fileName.endsWith(".JPEG") && !fileName.endsWith(".JPG")
								&& !fileName.endsWith(".jpeg")) {
							File file = new File(fileDirectory + fileRealName);
							file.delete();
							PrintWriter script = response.getWriter();
							script.println("<script>");
							script.println("alert('파일을 업로드 하지 못했습니다.')");
							script.println("location.href = 'commuListAction.do?category="+category+"'");
							script.println("</script>");

						} else {
							new ImageDAO().upload(communicationDAO.getNext() - 1, fileName, fileRealName);
							// out.write("파일명 : " + fileName + "<br>");
							// out.write("실제파일명 : " + fileRealName + "<br>");
						}
					}

					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("location.href = 'commuListAction.do?category="+category+"'");
					script.println("</script>");

				}
			}
		}

	}

	// 기존파일 삭제
	public void deleteFileFunction(ArrayList<ImageDTO> fileList, String directory, int bbsID) {
		for (int i = 0; i < fileList.size(); i++) {
			File prevfile = new File(directory + fileList.get(i).getFileRealName()); // 실제 파일도 같이 삭제
			prevfile.delete();
		}
		new ImageDAO().delete(bbsID); // 기존에 있던 사진을 먼저 삭제 한다

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