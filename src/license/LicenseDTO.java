package license;

public class LicenseDTO {
	private int licenseID;
	private String licenseName;
	private String licenseDate;
	private String licenseTime;
	private String licenseURL;

	public int getLicenseID() {
		return licenseID;
	}

	public void setLicenseID(int licenseID) {
		this.licenseID = licenseID;
	}

	public String getLicenseName() {
		return licenseName;
	}

	public void setLicenseName(String licenseName) {
		this.licenseName = licenseName;
	}

	public String getLicenseDate() {
		return licenseDate;
	}

	public void setLicenseDate(String licenseDate) {
		this.licenseDate = licenseDate;
	}

	public String getLicenseTime() {
		return licenseTime;
	}

	public void setLicenseTime(String licenseTime) {
		this.licenseTime = licenseTime;
	}

	public String getLicenseURL() {
		return licenseURL;
	}

	public void setLicenseURL(String licenseURL) {
		this.licenseURL = licenseURL;
	}

}
