package commuscrap;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import licensescrap.LicensescrapDTO;
import util.DBUser;

public class CommuscrapDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public CommuscrapDAO() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			DBUser DBuser = new DBUser();

			conn = DriverManager.getConnection(DBuser.dbURL, DBuser.dbID, DBuser.dbPassword);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 해당 아이디의 스크랩을 모두 가져오기
	public ArrayList<CommuscrapDTO> getCommuscrapList(String ID) {
		String SQL = "SELECT * FROM commuscrap WHERE ID = ?";
		ArrayList<CommuscrapDTO> list = new ArrayList<CommuscrapDTO>();

		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				CommuscrapDTO commuScrap = new CommuscrapDTO();
				commuScrap.setID(rs.getString(1));
				commuScrap.setWritingID(rs.getInt(2));
				list.add(commuScrap);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 해당하는 1개의 스크랩만 가져오기
	public CommuscrapDTO getCommuscrap(String ID, int writingID) {
		String SQL = "SELECT * FROM commuscrap WHERE ID = ? AND writingID = ?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, ID);
			pstmt.setInt(2, writingID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				CommuscrapDTO commuScrap = new CommuscrapDTO();
				commuScrap.setID(rs.getString(1));
				commuScrap.setWritingID(rs.getInt(2));
				return commuScrap; // 없는 경우
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null; // DB오류

	}

	// 해당 아이디의 스크랩 개수를 모두 가져오기
	public int getCount(String ID) {
		String SQL = "SELECT COUNT(*) FROM commuscrap WHERE ID = ?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, ID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // DB오류
	}
	
	// 해당 글의 스크랩 개수를 모두 가져오기
	public int getCount(int writingID) {
		String SQL = "SELECT COUNT(*) FROM commuscrap WHERE writingID = ?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, writingID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // DB오류
	}

	// 스크랩 추가
	public int addScrap(String ID, int writingID) {
		String SQL = "INSERT INTO commuscrap VALUES (?,?)";

		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, ID);
			pstmt.setInt(2, writingID);
			pstmt.executeUpdate();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // DB오류
	}
	
	// 스크랩 삭제
	public int deleteScrap(String ID, int writingID) {
		String SQL = "DELETE FROM commuscrap WHERE ID = ? AND writingID = ?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, ID);
			pstmt.setInt(2, writingID);
			pstmt.executeUpdate();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // DB오류
	}

}
