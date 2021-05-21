package licensescrap;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import license.LicenseDTO;
import util.DBUser;

public class LicensescrapDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public LicensescrapDAO() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			DBUser DBuser = new DBUser();

			conn = DriverManager.getConnection(DBuser.dbURL, DBuser.dbID, DBuser.dbPassword);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 해당 아이디의 스크랩을 모두 가져오기
	public ArrayList<LicensescrapDTO> getLicensescrapList(String ID) {
		String SQL = "SELECT * FROM licensescrap WHERE ID = ?";
		ArrayList<LicensescrapDTO> list = new ArrayList<LicensescrapDTO>();

		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				LicensescrapDTO licScrap = new LicensescrapDTO();
				licScrap.setID(rs.getString(1));
				licScrap.setLicenseID(rs.getInt(2));
				list.add(licScrap);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 해당하는 1개의 스크랩만 가져오기
	public LicensescrapDTO getLicensescrap(String ID, int licenseID) {
		String SQL = "SELECT * FROM licensescrap WHERE ID = ? AND licenseID = ?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, ID);
			pstmt.setInt(2, licenseID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				LicensescrapDTO lic = new LicensescrapDTO();
				lic.setID(rs.getString(1));
				lic.setLicenseID(rs.getInt(2));
				return lic; // 없는 경우
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null; // DB오류

	}

	// 해당 아이디의 스크랩 개수를 모두 가져오기
	public int getCount(String ID) {
		String SQL = "SELECT COUNT(*) FROM licensescrap WHERE ID = ?";

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

	// 스크랩 추가
	public int addScrap(String ID, int licenseID) {
		String SQL = "INSERT INTO licensescrap VALUES (?,?)";

		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, ID);
			pstmt.setInt(2, licenseID);
			pstmt.executeUpdate();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // DB오류
	}
	
	// 스크랩 삭제
	public int deleteScrap(String ID, int licenseID) {
		String SQL = "DELETE FROM licensescrap WHERE ID = ? AND licenseID = ?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, ID);
			pstmt.setInt(2, licenseID);
			pstmt.executeUpdate();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // DB오류
	}

	//스크랩했는지 체크
	public boolean checkLicenseScrap(String ID, int licenseID) {
		if (ID == null) return false; //로그인 되어 있지 않다면
		
		String SQL = "SELECT * FROM licensescrap WHERE ID = ? AND licenseID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, ID);
			pstmt.setInt(2, licenseID);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					return true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false; 
		}

}
