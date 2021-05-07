package comment;

import java.util.ArrayList;
import java.util.List;

public class CommentDTO {
	private int writingID;
	private int commentID;
	private int parentCommentID;
	private String ID;
	private String nickName;
	private String content;
	private String commentDate;
	
	public ArrayList <CommentDTO> commentChildList = new ArrayList<CommentDTO>();
	
	public int getWritingID() {
		return writingID;
	}

	public void setWritingID(int writingID) {
		this.writingID = writingID;
	}

	public int getCommentID() {
		return commentID;
	}

	public void setCommentID(int commentID) {
		this.commentID = commentID;
	}

	public int getParentCommentID() {
		return parentCommentID;
	}

	public void setParentCommentID(int parentCommentID) {
		this.parentCommentID = parentCommentID;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(String commentDate) {
		this.commentDate = commentDate;
	}

}
