package image;

public class ImageDTO {
	private int bbsID;
	private String bbsType;
	private String fileName;
	private String fileRealName;

	public int getBbsID() {
		return bbsID;
	}

	public void setBbsID(int bbsID) {
		this.bbsID = bbsID;
	}

	public String getBbsType() {
		return bbsType;
	}

	public void setBbsType(String bbsType) {
		this.bbsType = bbsType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileRealName() {
		return fileRealName;
	}

	public void setFileRealName(String fileRealName) {
		this.fileRealName = fileRealName;
	}

}
