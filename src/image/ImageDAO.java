package image;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import util.DBUser;

public class ImageDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public ImageDAO() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			DBUser DBuser = new DBUser();

			conn = DriverManager.getConnection(DBuser.dbURL, DBuser.dbID, DBuser.dbPassword);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//파일 업로드
	public int upload(int bbsID, String fileType, String fileName, String fileRealName) { 
		String SQL = "INSERT INTO image VALUES(?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, bbsID);
			pstmt.setString(2, fileType);
			pstmt.setString(3, fileName);
			pstmt.setString(4, fileRealName);
			return pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return -1;
	}
	
	
	//파일 업로드
	public int update(int bbsID, String fileType, String fileName, String fileRealName) { 
		String SQL = "UPDATE image SET fileName = ?, fileRealName = ? WHERE bbsID = ? AND bbsType = ?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, fileName);
			pstmt.setString(2, fileRealName);
			pstmt.setInt(3, bbsID);
			pstmt.setString(4, fileType);
			return pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return -1;
	}
	
	//파일 delete
	public int delete(int bbsID) { 
		String SQL = "DELETE FROM image WHERE bbsID = ?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, bbsID);
			return pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return -1;
	}
	
	//파일 delete
	public int delete(int bbsID, String bbsType) { 
		String SQL = "DELETE FROM image WHERE bbsID = ? AND bbsType = ?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, bbsID);
			pstmt.setString(2, bbsType);
			return pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return -1;
	}

	public ImageDTO getFile(int bbsID, String bbsType) {
		String SQL = "SELECT * FROM image WHERE bbsID = ? AND bbsType = ?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, bbsID);
			pstmt.setString(2, bbsType);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				ImageDTO imageDto = new ImageDTO();
				imageDto.setBbsID(rs.getInt(1));
				imageDto.setBbsType(rs.getString(2));
				imageDto.setFileName(rs.getString(3));
				imageDto.setFileRealName(rs.getString(4));
				return imageDto;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	
	public ArrayList<ImageDTO> getFiles(int bbsID) {
		String SQL = "SELECT * FROM image WHERE bbsID = ?";
		ArrayList<ImageDTO> list = new ArrayList<ImageDTO>();
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, bbsID);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ImageDTO imageDto = new ImageDTO();
				imageDto.setBbsID(rs.getInt(1));
				imageDto.setBbsType(rs.getString(2));
				imageDto.setFileName(rs.getString(3));
				imageDto.setFileRealName(rs.getString(4));
				list.add(imageDto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
		
	}
	
	
}
