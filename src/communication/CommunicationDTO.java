package communication;

public class CommunicationDTO {
	private int writingID;
	private String category;
	private String title;
	private String ID;
	private String nickName;
	private String commuDate;
	private String content;
	private int scrapCount;
	private int view;

	public int getView() {
		return view;
	}

	public void setView(int view) {
		this.view = view;
	}

	public int getWritingID() {
		return writingID;
	}

	public void setWritingID(int writingID) {
		this.writingID = writingID;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getCommuDate() {
		return commuDate;
	}

	public void setCommuDate(String commuDate) {
		this.commuDate = commuDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getScrapCount() {
		return scrapCount;
	}

	public void setScrapCount(int scrapCount) {
		this.scrapCount = scrapCount;
	}

}
