package DataObject;

import java.sql.Timestamp;

public class SurveyBoardDto {
	private int bNum;
	private String bName;
	private String bTitle;
	private String bContent;
	private int bHit;
	private Timestamp bRegDate;
	private String bFileOriName;
	private String bFileSaveName;
	private String bFileFullPath;
	
	public SurveyBoardDto() {};
	
	public SurveyBoardDto(int bNum, String bName, String bTitle, String bContent, int bHit, Timestamp bRegDate) {
		super();
		this.bNum = bNum;
		this.bName = bName;
		this.bTitle = bTitle;
		this.bContent = bContent;
		this.bHit = bHit;
		this.bRegDate = bRegDate;
	}
	
	public SurveyBoardDto(int bNum, String bName, String bTitle, String bContent, int bHit, Timestamp bRegDate,
			String bFileOriName, String bFileSaveName, String bfileFullPath) {
		this.bNum = bNum;
		this.bName = bName;
		this.bTitle = bTitle;
		this.bContent = bContent;
		this.bHit = bHit;
		this.bRegDate = bRegDate;
		this.bFileOriName = bFileOriName;
		this.bFileSaveName = bFileSaveName;
		this.bFileFullPath = bfileFullPath;
	}

	public SurveyBoardDto(int bNum, String bTitle) {
		this.bNum = bNum;
		this.bTitle = bTitle;
	}

	public int getbNum() {
		return bNum;
	}
	public void setbNum(int bNum) {
		this.bNum = bNum;
	}
	public String getbName() {
		return bName;
	}
	public void setbName(String bName) {
		this.bName = bName;
	}
	public String getbTitle() {
		return bTitle;
	}
	public void setbTitle(String bTitle) {
		this.bTitle = bTitle;
	}
	public String getbContent() {
		return bContent;
	}
	public void setbContent(String bContent) {
		this.bContent = bContent;
	}
	public int getbHit() {
		return bHit;
	}
	public void setbHit(int bHit) {
		this.bHit = bHit;
	}
	public Timestamp getbRegDate() {
		return bRegDate;
	}
	public void setbRegDate(Timestamp bRegDate) {
		this.bRegDate = bRegDate;
	}

	public String getbFileOriName() {
		return bFileOriName;
	}

	public void setbFileOriName(String bFileOriName) {
		this.bFileOriName = bFileOriName;
	}

	public String getbFileSaveName() {
		return bFileSaveName;
	}

	public void setbFileSaveName(String bFileSaveName) {
		this.bFileSaveName = bFileSaveName;
	}

	public String getbFileFullPath() {
		return bFileFullPath;
	}

	public void setbFileFullPath(String bfileFullPath) {
		bFileFullPath = bfileFullPath;
	}
	
	
}
