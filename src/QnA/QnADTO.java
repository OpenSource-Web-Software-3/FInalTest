package QnA;

public class QnADTO {
	private int QID;
	private String ID;
	private String nickName;
	private String title;
	private String content;
	private String QDate;
	private String answer;

	public int getQID() {
		return QID;
	}

	public void setQID(int qID) {
		QID = qID;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getQDate() {
		return QDate;
	}

	public void setQDate(String qDate) {
		QDate = qDate;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

}
