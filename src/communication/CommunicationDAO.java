package communication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import commuscrap.CommuscrapDTO;
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

			return 0; // 없는 경우
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // DB오류

	}

	// 글의 개수를 세는 함수
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

	// 글 쓰기
	public int write(String category, String sub_category, String title, String userID, String nickname,
			String content) {
		String SQL = "INSERT INTO communication VALUES (?,?,?,?,?,?,now(),?,0,0,1)";

		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, getNext());
			pstmt.setString(2, category);
			pstmt.setString(3, sub_category);
			pstmt.setString(4, title);
			pstmt.setString(5, userID);
			pstmt.setString(6, nickname);
			pstmt.setString(7, content);
			return pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // DB오류
	}

	// 글 수정
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

	// 글 삭제
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
				communicationDTO.setSub_category(rs.getString(3));
				communicationDTO.setTitle(rs.getString(4));
				communicationDTO.setID(rs.getString(5));
				communicationDTO.setNickName(rs.getString(6));
				communicationDTO.setCommuDate(rs.getString(7));
				communicationDTO.setContent(rs.getString(8));
				communicationDTO.setScrapCount(rs.getInt(9));
				communicationDTO.setView(rs.getInt(10));
				communicationDTO.setAvailable(rs.getInt(11));
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
				communicationDTO.setSub_category(rs.getString(3));
				communicationDTO.setTitle(rs.getString(4));
				communicationDTO.setID(rs.getString(5));
				communicationDTO.setNickName(rs.getString(6));
				communicationDTO.setCommuDate(rs.getString(7));
				communicationDTO.setContent(rs.getString(8));
				communicationDTO.setScrapCount(rs.getInt(9));
				communicationDTO.setView(rs.getInt(10));
				communicationDTO.setAvailable(rs.getInt(11));
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
				communicationDTO.setSub_category(rs.getString(3));
				communicationDTO.setTitle(rs.getString(4));
				communicationDTO.setID(rs.getString(5));
				communicationDTO.setNickName(rs.getString(6));
				communicationDTO.setCommuDate(rs.getString(7));
				communicationDTO.setContent(rs.getString(8));
				communicationDTO.setScrapCount(rs.getInt(9));
				communicationDTO.setView(rs.getInt(10));
				communicationDTO.setAvailable(rs.getInt(11));
				list.add(communicationDTO);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null; // 데이터가 없는 경우 or DB오류
	}

	// scrap한 commu글에 해당하는 모든 데이터 가져오기 (USE scrapCommu.jsp)
	public ArrayList<CommunicationDTO> getCommunicationList(ArrayList<CommuscrapDTO> commuList) {
		ArrayList<CommunicationDTO> list = new ArrayList<CommunicationDTO>();
		for (int i = 0; i < commuList.size(); i++) {
			CommunicationDTO commu = new CommunicationDTO();
			commu = getCommunication(commuList.get(i).getWritingID());
			list.add(commu);
		}
		return list;
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
				communicationDTO.setSub_category(rs.getString(3));
				communicationDTO.setTitle(rs.getString(4));
				communicationDTO.setID(rs.getString(5));
				communicationDTO.setNickName(rs.getString(6));
				communicationDTO.setCommuDate(rs.getString(7));
				communicationDTO.setContent(rs.getString(8));
				communicationDTO.setScrapCount(rs.getInt(9));
				communicationDTO.setView(rs.getInt(10));
				communicationDTO.setAvailable(rs.getInt(11));
				list.add(communicationDTO);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null; // 데이터가 없는 경우 or DB오류
	}

	// 조회 수 증가
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

	// 스크랩 수 증가
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

	// 스크랩 수 감소
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

	// search AJAX
	public ArrayList<CommunicationDTO> search(String bbsTitle, String category, String sub_category, String sort) {
		String SQL = "SELECT * FROM communication WHERE category = \""+category+"\"";
		
		if (!bbsTitle.equals("")) SQL += " AND title LIKE \"%"+bbsTitle+"%\"";
		if (sub_category != null && !sub_category.equals("")) SQL += " AND sub_category = \""+sub_category+"\"";
		if (sort != null && !sort.equals("")) SQL += " ORDER BY "+sort+"";
		
		//System.out.println(SQL);
		
//		String SQL = "SELECT * FROM communication WHERE title LIKE ? AND category = ?";
		ArrayList<CommunicationDTO> list = new ArrayList<CommunicationDTO>();

		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				CommunicationDTO communicationDTO = new CommunicationDTO();
				communicationDTO.setWritingID(rs.getInt(1));
				communicationDTO.setCategory(rs.getString(2));
				communicationDTO.setSub_category(rs.getString(3));
				communicationDTO.setTitle(rs.getString(4));
				communicationDTO.setID(rs.getString(5));
				communicationDTO.setNickName(rs.getString(6));
				communicationDTO.setCommuDate(rs.getString(7));
				communicationDTO.setContent(rs.getString(8));
				communicationDTO.setScrapCount(rs.getInt(9));
				communicationDTO.setView(rs.getInt(10));
				communicationDTO.setAvailable(rs.getInt(11));
				list.add(communicationDTO);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

}
