package license;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

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
	
	public int getLicenseID(int licenseID) {
		String SQL = "SELECT licenseID FROM license WHERE licenseID = ?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, licenseID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
			
			return 0; //없는 경우
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // DB오류
		
	}
	
	public int getCount() {
		String SQL = "SELECT COUNT(*) FROM license";
		
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
				licenseDto.setLicenseType(rs.getString(3));
				licenseDto.setLicenseDate(rs.getString(4));
				licenseDto.setLicenseTime(rs.getString(5));
				licenseDto.setLicenseURL(rs.getString(6));
				licenseDto.setApplyPeriod(rs.getString(7));
				return licenseDto;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public LicenseDTO getLicense(int licenseID, int limit) {
		String SQL = "SELECT * FROM license WHERE licenseID = ? limit ?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, licenseID);
			pstmt.setInt(2, limit);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				LicenseDTO licenseDto = new LicenseDTO();
				licenseDto.setLicenseID(rs.getInt(1));
				licenseDto.setLicenseName(rs.getString(2));
				licenseDto.setLicenseType(rs.getString(3));
				licenseDto.setLicenseDate(rs.getString(4));
				licenseDto.setLicenseTime(rs.getString(5));
				licenseDto.setLicenseURL(rs.getString(6));
				licenseDto.setApplyPeriod(rs.getString(7));
				return licenseDto;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// licenseName 에 해당하는 모든 데이러 가져오기
	public ArrayList<LicenseDTO> getLicenseList(String licenseName) {
		ArrayList<LicenseDTO> list = new ArrayList<LicenseDTO>();

		String SQL = "SELECT * FROM license WHERE licenseName = ? ORDER BY licenseID ASC";

		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, licenseName);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				LicenseDTO licenseDto = new LicenseDTO();
				licenseDto.setLicenseID(rs.getInt(1));
				licenseDto.setLicenseName(rs.getString(2));
				licenseDto.setLicenseType(rs.getString(3));
				licenseDto.setLicenseDate(rs.getString(4));
				licenseDto.setLicenseTime(rs.getString(5));
				licenseDto.setLicenseURL(rs.getString(6));
				licenseDto.setApplyPeriod(rs.getString(7));
				list.add(licenseDto);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null; // 데이터가 없는 경우 or DB오류
	}
	
	// DB에 저장되어 있는 모든 자격증 이름을 그룹화하여 가져오기
	public ArrayList<String> getLicenseNameList() {
		ArrayList<String> list = new ArrayList<>();
		
		String SQL = "SELECT DISTINCT licenseName FROM license";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(rs.getString(1));
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null; // 데이터가 없는 경우 or DB오류
	}

	

	//크롤링해온 데이터를 DB에 insert(최초1회)
	public int insertCrawlingData(ArrayList<LicenseDTO> licenseList) {
		String SQL = "INSERT INTO license VALUES (?,?,?,?,?,?,?)";

		try {

			for (LicenseDTO license : licenseList) {
				PreparedStatement pstmt = conn.prepareStatement(SQL);
				pstmt.setInt(1, getNext());
				pstmt.setString(2, license.getLicenseName());
				pstmt.setString(3, license.getLicenseType());
				pstmt.setString(4, license.getLicenseDate().replaceAll(" ", ""));
				pstmt.setString(5, license.getLicenseTime());
				pstmt.setString(6, license.getLicenseURL());
				pstmt.setString(7, license.getApplyPeriod().replaceAll(" ", ""));
				pstmt.executeUpdate();
			}

			return 1;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // DB오류

	}
	
	//크롤링해온 데이터를 DB에 update -- 아직 다 구현안함
	public int updateCrawlingData(ArrayList<LicenseDTO> licenseList) {
		String SQL = "UPDATE license SET licenseName = ?, licenseType = ?, licenseDate = ?, licenseTime = ?, licenseURL = ?, applyPeriod = ? WHERE licenseID = ?";
		
		try {
			for (LicenseDTO license : licenseList) {
				PreparedStatement pstmt = conn.prepareStatement(SQL);
				pstmt.setString(1, license.getLicenseName());
				pstmt.setString(2, license.getLicenseType());
				pstmt.setString(3, license.getLicenseDate());
				pstmt.setString(4, license.getLicenseTime());
				pstmt.setString(5, license.getLicenseURL());
				pstmt.setString(6, license.getApplyPeriod());
				if (getLicenseID(license.getLicenseID()) == 0) {
					
				}
				pstmt.setInt(7, getLicenseID(license.getLicenseID()));
				pstmt.executeUpdate();
			}
			
			return 1;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // 
		
	}

}
