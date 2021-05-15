package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;

//import com.sun.jndi.cosnaming.IiopUrl.Address;

import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.UserDAO;
import user.UserDTO;
import util.Gmail;
import util.SHA256;

/**
 * Servlet implementation class emailSendAction
 */
@WebServlet("/findInfoAction")
public class findInfoAction extends HttpServlet {
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
			script.println("alert('이미 로그인되어 있습니다.')");
			script.println("location.href = 'index.do'");
			script.println("</script>");
			return;
		} else if (request.getParameter("formType") == null || request.getParameter("formType").equals("")) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('잘못된 접근입니다.')");
			script.println("location.href = 'index.do'");
			script.println("</script>");
			return;
		} else if (request.getParameter("email") == null || request.getParameter("email").equals("")) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('이메일을 입력해주세요')");
			script.println("history.back()");
			script.println("</script>");
			return;
		} 
		else {
			String info1 = ""; // name or ID
			if (request.getParameter("formType").equals("ID")) info1 = request.getParameter("name");
			else if (request.getParameter("formType").equals("PW")) info1 = request.getParameter("ID");
			
			if (info1.equals("") || info1 == null) {
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('입력되지 않은 정보가 있습니다.')");
				script.println("history.back()");
				script.println("</script>");
				return;
			}
		
			UserDAO userDao = new UserDAO();
			UserDTO user = userDao.findInfo(request.getParameter("email"));
			
			if (user == null) {
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('일치하는 이메일정보가 없습니다.')");
				script.println("history.back()");
				script.println("</script>");
				return;
			} 
			else {// 이메일 정보 가져옴
				
				if (request.getParameter("formType").equals("ID")) {
					if (!info1.equals(user.getName())) {
						PrintWriter script = response.getWriter();
						script.println("<script>");
						script.println("alert('정보가 일치하지 않습니다')");
						script.println("history.back()");
						script.println("</script>");
						return;
					}
				}
				else if (request.getParameter("formType").equals("PW")) {
					if (!info1.equals(user.getID())) {
						PrintWriter script = response.getWriter();
						script.println("<script>");
						script.println("alert('정보가 일치하지 않습니다')");
						script.println("history.back()");
						script.println("</script>");
						return;
					}
				}
				
				int result = sendEmail(user, request.getParameter("formType"));
				if (result == 1) { // 이메일 보내기 성공
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("alert('이메일을 전송했습니다. 메일 확인 후 로그인해주세요')");
					script.println("location.href = 'index.do'");
					script.println("</script>");
					return;
				} else if (result == -1) {
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("alert('오류가 발생했습니다.')");
					script.println("history.back();");
					script.println("</script>");
					return;
				} else if (result == -2) {
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("alert('이메일 타입이 일치하지 않음')");
					script.println("history.back();");
					script.println("</script>");
					return;

				}
			}
		}
	}

	public int sendEmail(UserDTO user, String formType) {
		UserDAO userDao = new UserDAO();
		// 인증 이메일 정보
		String from = new Gmail().emailID;
		String to = user.getEmail();
		String subject = "<" + user.getName() + ">님. LINS사이트에서 보낸 본인확인 메일입니다.";
		String content = user.getName() + "님의 " + formType + "는 ";

		// findID or findPassword
		if (formType.equals("ID")) {
			content += user.getID();
		} else if (formType.equals("PW")) {
			content += user.getPassword();
		} else {
			System.out.println("비정상접근 : 타입불일치");
			return -2;
		}

		content += " 입니다.";

		// SMTP에 접속하기 위한 정보
		Properties p = new Properties();
		p.put("mail.smtp.user", from);
		p.put("mail.smtp.host", "smtp.googlemail.com");
		p.put("mail.smtp.port", "465");
		p.put("mail.smtp.starttls.enable", "true");
		p.put("mail.smtp.auth", "true");
		p.put("mail.smtp.debug", "true");
		p.put("mail.smtp.socketFactory.port", "465");
		p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		p.put("mail.smtp.socketFactory.fallback", "false");

		// 실제 메일을 전송하는 부분
		try {
			Authenticator auth = new Gmail();
			Session ses = Session.getInstance(p, auth);
			ses.setDebug(true);
			MimeMessage msg = new MimeMessage(ses);
			msg.setSubject(subject);
			InternetAddress fromAddr = new InternetAddress(from);
			msg.setFrom(fromAddr);
			InternetAddress toAddr = new InternetAddress(to);
			msg.addRecipient(Message.RecipientType.TO, toAddr);
			msg.setContent(content, "text/html;charset=UTF-8");
			Transport.send(msg); // 메일전송

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return 1;
	}

}
