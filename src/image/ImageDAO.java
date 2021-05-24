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
	
	//안 쓰일 수도 있음
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
	
	//파일 delete : QNA랑 겹치므로 DB는 같이 쓰되 폴더만 따로 쓰기
	// commuType = 1 commu파일 지우기 , commuType = 2 QNA파일 지우기 
	public int delete(int bbsID, int commuType) {   
		
		String SQL = "";
		
		if (commuType == 1) SQL = "DELETE FROM image WHERE bbsID = ? AND (bbsType='document' OR bbsType='file')";
		else if (commuType == 2) SQL = "DELETE FROM image WHERE bbsID = ? AND (bbsType='QNAdocument' OR bbsType='QNAfile')";
		else return -2; //type오류  
			
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
	
	//파일 delete : QNA랑 겹치므로 DB는 같이 쓰되 폴더만 따로 쓰기
	// commuType = 1 commu파일 지우기 , commuType = 2 QNA파일 지우기 
	public ArrayList<ImageDTO> getFiles(int bbsID, int commuType) {
		
		String SQL = "";
		if (commuType == 1) SQL = "SELECT * FROM image WHERE bbsID = ? AND (bbsType='document' OR bbsType='file')";
		else if (commuType == 2) SQL = "SELECT * FROM image WHERE bbsID = ? AND (bbsType='QNAdocument' OR bbsType='QNAfile')";
		
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
