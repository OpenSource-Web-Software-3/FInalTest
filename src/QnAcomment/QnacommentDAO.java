package QnAcomment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import util.DBUser;

public class QnacommentDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public QnacommentDAO() {

			try {
				Class.forName("com.mysql.jdbc.Driver");
				DBUser DBuser = new DBUser();

				conn = DriverManager.getConnection(DBuser.dbURL, DBuser.dbID, DBuser.dbPassword);

			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	public String getDate() {
		String SQL = "SELECT NOW()";

		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";

	}

	public int getNext(int QID) {
		String SQL = "SELECT QcommentID FROM qnacomment WHERE QID = ? ORDER BY commentID DESC";

		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, QID);
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

	public int commentWrite(int QID, String ID, String content) {
		String SQL = "INSERT INTO qnacomment VALUES (?,?,?,?,now())";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, QID);
			pstmt.setInt(2, getNext(QID));
			pstmt.setString(3, ID);
			pstmt.setString(4, content);
			return pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // DB오류

	}
	
	public int getAllComment(int QID) {
		String SQL = "SELECT COUNT(*) FROM qnacomment WHERE QID = ?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, QID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; //DB오류
	}

	public ArrayList<QnacommentDTO> getCommentList(int QID) {
		String SQL = "SELECT * FROM qnacomment WHERE QID = ? ORDER BY QcommentID ASC";
		ArrayList<QnacommentDTO> list = new ArrayList<QnacommentDTO>();
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, QID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				QnacommentDTO qnacommentDTO = new QnacommentDTO();
				qnacommentDTO.setQID(rs.getInt(1));
				qnacommentDTO.setQcommentID(rs.getInt(2));
				qnacommentDTO.setID(rs.getString(3));
				qnacommentDTO.setContent(rs.getString(4));
				qnacommentDTO.setqCommentDate(rs.getString(5));
				list.add(qnacommentDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}
}
