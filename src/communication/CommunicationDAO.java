package communication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import util.DBUser;

public class CommunicationDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public CommunicationDAO() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			DBUser DBuser = new DBUser();

			conn = DriverManager.getConnection(DBuser.dbURL, DBuser.dbID, DBuser.dbPassword);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getNext() {
		String SQL = "SELECT writingID FROM communication ORDER BY writingID DESC";

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
	
	public int getWritingID(int writingID) {
		String SQL = "SELECT writingID FROM communication WHERE writingID = ?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, writingID);
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
	
	//글의 개수를 세는 함수
	public int getCount() {
		String SQL = "SELECT COUNT(*) FROM communication";
		
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
	public int write(String category, String title, String userID, String nickname, String content) {
		String SQL = "INSERT INTO communication VALUES (?,?,?,?,?,now(),?,0,0,1)";

		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, getNext());
			pstmt.setString(2, category);
			pstmt.setString(3, title);
			pstmt.setString(4, userID);
			pstmt.setString(5, nickname);
			pstmt.setString(6, content);
			return pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // DB오류
	}
	
	//글 수정
	public int update(int writingID, String title, String content) {
		String SQL = "UPDATE communication SET title = ?, content = ? WHERE writingID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setInt(3, writingID);
			return pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // DB오류
	}

	//글 삭제
	public int delete(int writingID) {
		String SQL = "UPDATE communication SET available = 0 WHERE writingID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, writingID);
			return pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // DB오류
	}

	
	
	public CommunicationDTO getCommunication(int writingID) {
		String SQL = "SELECT * FROM communication WHERE writingID = ? AND available = 1";

		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, writingID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				CommunicationDTO communicationDTO = new CommunicationDTO();
				communicationDTO.setWritingID(rs.getInt(1));
				communicationDTO.setCategory(rs.getString(2));
				communicationDTO.setTitle(rs.getString(3));
				communicationDTO.setID(rs.getString(4));
				communicationDTO.setNickName(rs.getString(5));
				communicationDTO.setCommuDate(rs.getString(6));
				communicationDTO.setContent(rs.getString(7));
				communicationDTO.setScrapCount(rs.getInt(8));
				communicationDTO.setView(rs.getInt(9));
				return communicationDTO;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public CommunicationDTO getCommunication(int writingID, int limit) {
		String SQL = "SELECT * FROM communication WHERE writingID = ? AND available = 1 LIMIT ?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, writingID);
			pstmt.setInt(2, limit);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				CommunicationDTO communicationDTO = new CommunicationDTO();
				communicationDTO.setWritingID(rs.getInt(1));
				communicationDTO.setCategory(rs.getString(2));
				communicationDTO.setTitle(rs.getString(3));
				communicationDTO.setID(rs.getString(4));
				communicationDTO.setNickName(rs.getString(5));
				communicationDTO.setCommuDate(rs.getString(6));
				communicationDTO.setContent(rs.getString(7));
				communicationDTO.setScrapCount(rs.getInt(8));
				communicationDTO.setView(rs.getInt(9));
				return communicationDTO;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Communication 에 해당하는 모든 데이터 가져오기
	public ArrayList<CommunicationDTO> getCommunicationList(String category) {
		ArrayList<CommunicationDTO> list = new ArrayList<CommunicationDTO>();

		String SQL = "SELECT * FROM communication WHERE category = ? AND available = 1 ORDER BY writingID DESC";

		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, category);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				CommunicationDTO communicationDTO = new CommunicationDTO();
				communicationDTO.setWritingID(rs.getInt(1));
				communicationDTO.setCategory(rs.getString(2));
				communicationDTO.setTitle(rs.getString(3));
				communicationDTO.setID(rs.getString(4));
				communicationDTO.setNickName(rs.getString(5));
				communicationDTO.setCommuDate(rs.getString(6));
				communicationDTO.setContent(rs.getString(7));
				communicationDTO.setScrapCount(rs.getInt(8));
				communicationDTO.setView(rs.getInt(9));
				list.add(communicationDTO);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null; // 데이터가 없는 경우 or DB오류
	}
	
	// Communication의 userID에 해당하는 모든 데이터 가져오기 (*USE user-writing-list.jsp)
		public ArrayList<CommunicationDTO> getCommunicationList_userID(String userID) {
			ArrayList<CommunicationDTO> list = new ArrayList<CommunicationDTO>();
			
			String SQL = "SELECT * FROM communication WHERE ID = ? AND available = 1 ORDER BY writingID DESC";
			
			try {
				PreparedStatement pstmt = conn.prepareStatement(SQL);
				pstmt.setString(1, userID);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					CommunicationDTO communicationDTO = new CommunicationDTO();
					communicationDTO.setWritingID(rs.getInt(1));
					communicationDTO.setCategory(rs.getString(2));
					communicationDTO.setTitle(rs.getString(3));
					communicationDTO.setID(rs.getString(4));
					communicationDTO.setNickName(rs.getString(5));
					communicationDTO.setCommuDate(rs.getString(6));
					communicationDTO.setContent(rs.getString(7));
					communicationDTO.setScrapCount(rs.getInt(8));
					communicationDTO.setView(rs.getInt(9));
					list.add(communicationDTO);
				}
				return list;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null; // 데이터가 없는 경우 or DB오류
		}
		
	
	
	
	//조회 수 증가 
	public int increaseView(int writingID) {
		String SQL = "UPDATE communication SET view = view+1 WHERE writingID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, writingID);
			return pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // DB오류
	}
	
	//스크랩 수 증가 
	public int increaseScrap(int writingID) {
		String SQL = "UPDATE communication SET scrapCount = scrapCount+1 WHERE writingID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, writingID);
			return pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // DB오류
	}
	
	//스크랩 수 감소 
	public int decreaseScrap(int writingID) {
		String SQL = "UPDATE communication SET scrapCount = scrapCount-1 WHERE writingID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, writingID);
			return pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // DB오류
	}
	
	public int secessionUpdate(String userID) {
		String SQL = "UPDATE communication SET ID = '탈퇴한 회원입니다', nickName ='탈퇴한 회원입니다' WHERE ID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			return pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // DB오류
	}
	
	
}
