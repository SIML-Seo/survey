package DataObject;

import java.sql.Date;

public class SurveyTitleDto {
	private int tNum;
	private Date tRegDate;
	private String tTitle;
	private String tFinish;
	
	public SurveyTitleDto(int tNum, Date tRegDate, String tTitle, String tFinish) {
		this.tNum = tNum;
		this.tRegDate = tRegDate;
		this.tTitle = tTitle;
		this.tFinish = tFinish;
	}
	public int gettNum() {
		return tNum;
	}
	public void settNum(int tNum) {
		this.tNum = tNum;
	}
	public Date gettRegDate() {
		return tRegDate;
	}
	public void settRegDate(Date tRegDate) {
		this.tRegDate = tRegDate;
	}
	public String gettTitle() {
		return tTitle;
	}
	public void settTitle(String tTitle) {
		this.tTitle = tTitle;
	}
	public String gettFinish() {
		return tFinish;
	}
	public void settFinish(String tFinish) {
		this.tFinish = tFinish;
	}
	
}
