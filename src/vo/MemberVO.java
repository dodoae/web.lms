package vo;

public class MemberVO {
	private String memID;
	private String name;
	private String pwd;
	private String phone;
	private boolean loginCheck;
	private boolean idCheck;	// id �ߺ�üũ. true�ϰ�� ��� X
	private int result;
	
	public MemberVO() {}
	
	// ȸ�����Կ�
	public MemberVO(String memID, String name, String pwd, String phone) {
		this.memID = memID;
		this.name = name;
		this.pwd = pwd;
		this.phone = phone;
	}
	
	// �α��ο�
	public MemberVO(String memID, String pwd, String name) {
		this.memID = memID;
		this.pwd = pwd;
		this.name = name;
	}
	
	// ȸ�� Ż���
	public MemberVO(String memID, String pwd) {
		this.memID = memID;
		this.pwd = pwd;
	}
	
	// ID �ߺ� Ȯ�ο�
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
