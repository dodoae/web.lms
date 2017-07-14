package vo;

import java.sql.Timestamp;

public class CustomerVO {
	private int num; 
    private String cusCode; // 거래처 코드
    private String cusRep;	// 대표자 명
    private String licenseNum; // 사업자 번호
    private String cusName;	// 거래처 이름
    private String cusNumber;	// 거래처 전화번호
    private String cusAddress;	// 거래처 주소
    private Timestamp reg_date;	// 입력한 시간
    private Timestamp update_reg_date;	// 입력한 시간
    private int result;
    
    public CustomerVO(){}
    
	public CustomerVO(int num, String cusCode) {
		this.num = num;
		this.cusCode = cusCode;
	}
	
	// write
	public CustomerVO(int num, String cusCode, String cusName, String licenseNum, String cusRep, String cusNumber, String cusAddress, Timestamp reg_date) {
		this.num=num;
		this.cusCode = cusCode;
		this.cusName = cusName;
		this.licenseNum = licenseNum;
		this.cusRep = cusRep;
		this.cusNumber = cusNumber;
		this.cusAddress = cusAddress;
		this.reg_date = reg_date;
	}
	public CustomerVO(int num) {
		this.num = num;
	}
	
	// update
	public CustomerVO(int num, String cusCode, String cusName, String licenseNum, String cusRep, String cusNumber,
			String cusAddress) {
		this.num=num;
		this.cusCode = cusCode;
		this.cusName = cusName;
		this.licenseNum = licenseNum;
		this.cusRep = cusRep;
		this.cusNumber = cusNumber;
		this.cusAddress = cusAddress;
	}

	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getCusCode() {
		return cusCode;
	}
	public void setCusCode(String cusCode) {
		this.cusCode = cusCode;
	}
	
	public String getCusRep() {
		return cusRep;
	}
	public void setCusRep(String cusRep) {
		this.cusRep = cusRep;
	}
	public String getLicenseNum() {
		return licenseNum;
	}
	public void setLicenseNum(String licenseNum) {
		this.licenseNum = licenseNum;
	}

	public String getCusName() {
		return cusName;
	}
	public void setCusName(String cusName) {
		this.cusName = cusName;
	}
	
	public String getCusNumber() {
		return cusNumber;
	}
	public void setCusNumber(String cusNumber) {
		this.cusNumber = cusNumber;
	}	
	public String getCusAddress() {
		return cusAddress;
	}
	public void setCusAddress(String cusAddress) {
		this.cusAddress = cusAddress;
	}
	public Timestamp getReg_date() {
		return reg_date;
	}
	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}

	public Timestamp getUpdate_reg_date() {
		return update_reg_date;
	}

	public void setUpdate_reg_date(Timestamp update_reg_date) {
		this.update_reg_date = update_reg_date;
	}   
	
}