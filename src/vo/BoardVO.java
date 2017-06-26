package vo;

import java.sql.Timestamp;

public class BoardVO {
	private int num; 
    private String writer;
    private String subject;
    private String email;
    private String content;
    private String passwd;
    private Timestamp reg_date;
    private int readcount;
    private String ip;
    private int ref; // 답글쓸때 한덩이로 묶는역할
    private int re_step;	// 답글
    private int re_level;	// 답글 레벨..?ㅋ 이걸로 hot 이런거 결정됨
    private int result;
    

	public BoardVO() {}

	public BoardVO(int num, String writer, String subject, String email, String content, String passwd,
			Timestamp reg_date, String ip, int ref, int re_step, int re_level) {
		this.num = num;
    	this.writer = writer;
		this.subject = subject;
		this.email = email;
		this.content = content;
		this.passwd = passwd;
		this.reg_date = reg_date;
		this.ip = ip;
		this.ref = ref;
		this.re_step = re_step;
		this.re_level = re_level;
	}
	public BoardVO(int num) {
		this.num = num;
	}

	public BoardVO(int num, String pageNum) {
		this.num = num;
		this.passwd = pageNum;
	}

	public BoardVO(int num, String writer, String subject, String email, String content, String passwd,
			Timestamp reg_date, String ip) {
		this.num = num;
		this.writer = writer;
		this.subject = subject;
		this.email = email;
		this.content = content;
		this.passwd = passwd;
		this.reg_date = reg_date;
		this.ip = ip;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public int getRe_level() {
		return re_level;
	}
	public void setRe_level(int re_level) {
		this.re_level = re_level;
	}
	public int getRe_step() {
		return re_step;
	}
	public void setRe_step(int re_step) {
		this.re_step = re_step;
	}
	public int getReadcount() {
		return readcount;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public Timestamp getReg_date() {
		return reg_date;
	}
	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}

	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
}
