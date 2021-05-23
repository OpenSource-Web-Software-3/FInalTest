package QnA;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import util.DBUser;

public class QnADAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public QnADAO() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			DBUser DBuser = new DBUser();

			conn = DriverManager.getConnection(DBuser.dbURL, DBuser.dbID, DBuser.dbPassword);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getNext() {
		String SQL = "SELECT QID FROM qna ORDER BY QID DESC";

		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) + 1;
			}
			return 1; // 첫번째 게시물인 경우
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // DB오류

	}
	
	//글의 개수를 세는 함수
	public int getCount() {
		String SQL = "SELECT COUNT(*) FROM qna";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // DB오류
	}



	//글 쓰기
	public int write(String ID, String nickName, String title, String content, String answer) {
		String SQL = "INSERT INTO qna VALUES (?,?,?,?,?,now(),?,1)";

		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, getNext());
			pstmt.setString(2, ID);
			pstmt.setString(3, nickName);
			pstmt.setString(4, title);
			pstmt.setString(5, content);
			pstmt.setString(6, answer);
			return pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // DB오류
	}
	
	//글 수정
	public int update(int QID, String title, String content) {
		String SQL = "UPDATE qna SET title = ?, content = ? WHERE QID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setInt(3, QID);
			return pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // DB오류
	}

	//글 삭제
	public int delete(int QID) {
		String SQL = "UPDATE qna SET available = 0 WHERE QID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, QID);
			return pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // DB오류
	}

	public QnADTO getQnA(int QID) {
		String SQL = "SELECT * FROM qna WHERE QID = ? AND available = 1";

		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, QID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				QnADTO qnADTO = new QnADTO();
				qnADTO.setQID(rs.getInt(1));
				qnADTO.setID(rs.getString(2));
				qnADTO.setNickName(rs.getString(3));
				qnADTO.setTitle(rs.getString(4));
				qnADTO.setContent(rs.getString(5));
				qnADTO.setQDate(rs.getString(6));
				qnADTO.setAnswer(rs.getString(7));
				qnADTO.setAvailable(rs.getInt(8));
				return qnADTO;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public QnADTO getQnA(int QID, int limit) {
		String SQL = "SELECT * FROM qna WHERE QID = ? AND available = 1 LIMIT ?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, QID);
			pstmt.setInt(2, limit);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				QnADTO qnADTO = new QnADTO();
				qnADTO.setQID(rs.getInt(1));
				qnADTO.setID(rs.getString(2));
				qnADTO.setNickName(rs.getString(3));
				qnADTO.setTitle(rs.getString(4));
				qnADTO.setContent(rs.getString(5));
				qnADTO.setQDate(rs.getString(6));
				qnADTO.setAnswer(rs.getString(7));
				qnADTO.setAvailable(rs.getInt(8));
				return qnADTO;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Communication 에 해당하는 모든 데이터 가져오기
	public ArrayList<QnADTO> getCommunicationList(String userID) {
		ArrayList<QnADTO> list = new ArrayList<QnADTO>();

		String SQL = "SELECT * FROM qna WHERE ID = ? AND available = 1 ORDER BY QID DESC";

		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				QnADTO qnADTO = new QnADTO();
				qnADTO.setQID(rs.getInt(1));
				qnADTO.setID(rs.getString(2));
				qnADTO.setNickName(rs.getString(3));
				qnADTO.setTitle(rs.getString(4));
				qnADTO.setContent(rs.getString(5));
				qnADTO.setQDate(rs.getString(6));
				qnADTO.setAnswer(rs.getString(7));
				qnADTO.setAvailable(rs.getInt(8));
				list.add(qnADTO);
				
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null; // 데이터가 없는 경우 or DB오류
	}
	
}
