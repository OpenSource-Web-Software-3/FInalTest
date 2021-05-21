package comment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import util.DBUser;

public class CommentDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public CommentDAO() {

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

	public int getNext(int writingID) {
		String SQL = "SELECT commentID FROM comment WHERE writingID = ? ORDER BY commentID DESC";

		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, writingID);
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

	public int commentWrite(int writingID, int parentCommentID, String ID, String nickName, String content) {
		String SQL = "INSERT INTO comment VALUES (?,?,?,?,?,?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, writingID);
			pstmt.setInt(2, getNext(writingID));
			pstmt.setInt(3, parentCommentID);
			pstmt.setString(4, ID);
			pstmt.setString(5, nickName);
			pstmt.setString(6, content);
			pstmt.setString(7, getDate());
			return pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // DB오류

	}

	public ArrayList<CommentDTO> getCommentList(int writingID) {
		String SQL = "SELECT * FROM comment WHERE writingID = ? ORDER BY commentID ASC";
		ArrayList<CommentDTO> list = new ArrayList<CommentDTO>();
		
		//root 댓글 저장 = 게시글 전체를 의미 (list의 index를 맞추고 확실한 tree 구조를 만들기 위해서)
		CommentDTO comment_rootBbs = new CommentDTO();
		comment_rootBbs.setCommentID(0);
		comment_rootBbs.setParentCommentID(-1);
		list.add(comment_rootBbs);
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, writingID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				CommentDTO comment = new CommentDTO();
				comment.setWritingID(rs.getInt(1));
				comment.setCommentID(rs.getInt(2));
				comment.setParentCommentID(rs.getInt(3));
				comment.setID(rs.getString(4));
				comment.setNickName(rs.getString(5));
				comment.setContent(rs.getString(6));
				comment.setCommentDate(rs.getString(7));
				list.add(comment);

			}
			if (list.size() > 1) {
				//정렬
				Sort_C sort = new Sort_C();
				ArrayList<CommentDTO> sorted_list = sort.sortedCommentList(list);
				return sorted_list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}
}
