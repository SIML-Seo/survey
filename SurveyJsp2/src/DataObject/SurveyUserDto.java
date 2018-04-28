package DataObject;

public class SurveyUserDto {
	private String id;
	private String password;
	private String email;
	private int master;
	private int point;
	
	public SurveyUserDto() {}
	public SurveyUserDto(String id) {
		this.id = id;
	}
	public SurveyUserDto(int master) {
		this.master = master;
	}
	public SurveyUserDto(String id, String password, String email, int master, int point) {
		this.id = id;
		this.password = password;
		this.email = email;
		this.master = master;
		this.point = point;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getMaster() {
		return master;
	}
	public void setMaster(int master) {
		this.master = master;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
}
