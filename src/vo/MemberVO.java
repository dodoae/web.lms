package vo;

public class MemberVO {
	private String memID;
	private String name;
	private String pwd;
	private String phone;
	private boolean loginCheck;
	private boolean idCheck;	// id 중복체크. true일경우 사용 X
	private int result;
	
	public MemberVO() {}
	
	// 회원가입용
	public MemberVO(String memID, String name, String pwd, String phone) {
		this.memID = memID;
		this.name = name;
		this.pwd = pwd;
		this.phone = phone;
	}
	
	// 로그인용
	public MemberVO(String memID, String pwd, String name) {
		this.memID = memID;
		this.pwd = pwd;
		this.name = name;
	}
	
	// 회원 탈퇴용
	public MemberVO(String memID, String pwd) {
		this.memID = memID;
		this.pwd = pwd;
	}
	
	// ID 중복 확인용
	public MemberVO(String memID) {
		this.memID = memID;
	}

	public boolean isIdCheck() {
		return idCheck;
	}

	public void setIdCheck(boolean idCheck) {
		this.idCheck = idCheck;
	}

	public boolean isLoginCheck() {
		return loginCheck;
	}

	public void setLoginCheck(boolean loginCheck) {
		this.loginCheck = loginCheck;
	}

	public String getMemID() {
		return memID;
	}
	public String getName() {
		return name;
	}
	
	public String getPwd() {
		return pwd;
	}

	public String getPhone() {
		return phone;
	}


	public void setMemID(String memID) {
		this.memID = memID;
	}
	
	public int getResult() {
		return result;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setResult(int result) {
		this.result = result;
	}
}
