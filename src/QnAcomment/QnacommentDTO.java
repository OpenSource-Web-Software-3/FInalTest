package QnAcomment;

public class QnacommentDTO {
	private int QID;
	private int QcommentID;
	private String ID;
	private String content;
	private String qCommentDate;

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public int getQID() {
		return QID;
	}

	public void setQID(int qID) {
		QID = qID;
	}

	public int getQcommentID() {
		return QcommentID;
	}

	public void setQcommentID(int qcommentID) {
		QcommentID = qcommentID;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getqCommentDate() {
		return qCommentDate;
	}

	public void setqCommentDate(String qCommentDate) {
		this.qCommentDate = qCommentDate;
	}

}
