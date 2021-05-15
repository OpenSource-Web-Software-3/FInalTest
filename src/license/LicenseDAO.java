package license;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import util.DBUser;

public class LicenseDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public LicenseDAO() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			DBUser DBuser = new DBUser();

			conn = DriverManager.getConnection(DBuser.dbURL, DBuser.dbID, DBuser.dbPassword);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public LicenseDTO getLicense(int licenseID) {
		String SQL = "SELECT * FROM license WHERE licenseID = ?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, licenseID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				LicenseDTO licenseDto = new LicenseDTO();
				licenseDto.setLicenseID(rs.getInt(1));
				licenseDto.setLicenseName(rs.getString(2));
				licenseDto.setLicenseDate(rs.getString(3));
				licenseDto.setLicenseTime(rs.getString(4));
				licenseDto.setLicenseURL(rs.getString(5));
				licenseDto.setScrapCount(rs.getInt(6));
				return licenseDto;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public int getNext() {
		String SQL = "SELECT licenseID FROM license ORDER BY licenseID DESC";

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

}
