package entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import vo.MemberVO;

public class MemberEntity {
	private Connection con;
	private MemberVO vo;
	private boolean check;
	private String memID;
	//	private DataSource ds;
	private Context ctx;
	private DataSource ds;
	
	public MemberEntity(MemberVO vo) {
		this.vo = vo;
		System.out.println("MemberEntity :: ");
		try {
//			Class.forName("com.mysql.jdbc.Driver");
			ctx = new InitialContext(); //JNDI Subsystem
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/mysql");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ȸ������
	public void insertMember() {
		// TODO Auto-generated method stub
		System.out.println("insertMemeber");
		System.out.println("�Է��� ID : " + vo.getMemID());
		try {
			PreparedStatement pstmt;
//			con = DriverManager.getConnection(url, user, pass);
			con = ds.getConnection();
			String query = "INSERT INTO tb_member(memID, name, pwd, phone, address) values(?,?,?,?,?)";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, vo.getMemID());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getPwd());
			pstmt.setString(4, vo.getPhone());
			pstmt.setString(5, vo.getAddress());

			idCheck(vo);
			System.out.println("insertMember : " + check);
			if(check == false){
				pstmt.executeUpdate();
				System.out.println("ȸ������ �Ϸ�");
				System.out.println(check);
			} else if(check == true) {
				System.out.println("�̹� �ִ� ID�Դϴ�.");
				System.out.println(check);
			}
			vo.setIdCheck(check);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// ID �ߺ� �˻�
	public void idCheck(MemberVO vo) {
		try {
			PreparedStatement pstmt;
			memID = vo.getMemID();
			con = ds.getConnection();
			String query = "SELECT * from tb_member where memID=?";
			ResultSet rs = null;
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, memID);
			rs = pstmt.executeQuery();
			try {
				if(rs.next()) {
					check = true;	// ID�� ������ true
					System.out.println("�� ID�� ������Դϴ�.");
					System.out.println(check);
				} else { 
					check = false;	// ID�� ������ false
					System.out.println("��밡���� ID�Դϴ�.");
					System.out.println(check);
				}
				vo.setIdCheck(check);
				System.out.println("selectMember : " + vo.isIdCheck() );
			} finally {
				if(rs != null) {
					rs.close();
				}
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		vo.setIdCheck(check);
	} 

	// �α���
	public void loginMember() {
		PreparedStatement pstmt;
		String memID="";
		String memPwd="";
		String memName="";
		try {
			con = ds.getConnection();
			String query = "select * from tb_member where memID=? and pwd=?";
			ResultSet rs = null;
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, vo.getMemID());
			pstmt.setString(2, vo.getPwd());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				memID = rs.getString("memID");
				memPwd = rs.getString("pwd");
				memName = rs.getString("name");				
			}
			if(vo.getMemID().equals(memID)&&vo.getPwd().equals(memPwd)) {
				vo.setLoginCheck(true);
				System.out.println(memName+"���� �α��� �ϼ̽��ϴ�.");
				vo.setName(memName);
			} else {
				vo.setLoginCheck(false);
				System.out.println("���̵�� ��й�ȣ�� �ٽ� Ȯ�����ּ���.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// ���� ��ȸ
	public void selectMember() {
		PreparedStatement pstmt;
		String memID = "";
		String memPwd = "";
		String name = "";
		String phone = "";
		String address = "";

		System.out.println("MemberEntity :: selectMember");

		try {
			System.out.println("try");
			con = ds.getConnection();
			String query = "select * from tb_member where memID=?";
			ResultSet rs = null;
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, vo.getMemID());
			rs = pstmt.executeQuery();

			while(rs.next()) {
				memID = rs.getString("memID");
				memPwd = rs.getString("pwd");
				name = rs.getString("name");
				phone = rs.getString("phone");
				address = rs.getString("address");
				System.out.println("�������� ����");
				System.out.println("memID : " + memID);
				System.out.println("memPwd : " + memPwd);
				System.out.println("name : " + name);
				System.out.println("phone : " + phone);
				System.out.println("address : " + address);
				vo.setResult(1);
			}
			if(vo.getResult()==1) {
				System.out.println("��ȸ ����");
				vo.setPwd(memPwd);
				vo.setName(name);
				vo.setPhone(phone);
				vo.setAddress(address);
			} else {
				System.out.println("��ȸ ����");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// ȸ������ ����
	public void updateMember() {
		System.out.println("updateMember");
		try {
			PreparedStatement pstmt;
			con = ds.getConnection();
			String query = "UPDATE tb_member set name=?, pwd=?, phone=?, address=? where memID=?";

			pstmt = con.prepareStatement(query);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPwd());
			pstmt.setString(3, vo.getPhone());
			pstmt.setString(4, vo.getAddress());
			pstmt.setString(5, vo.getMemID());

			pstmt.executeUpdate();

			vo.setResult(1);

			if(vo.getResult()==1) {
				System.out.println("���� ����");

			} else {
				System.out.println("��ȸ ����");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// ȸ�� Ż��
	public void deleteMember() {
		System.out.println("deleteMember");

		try {
			PreparedStatement pstmt;
			con = ds.getConnection();
			String query = "DELETE from tb_member where memID=? and pwd=?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, vo.getMemID());
			pstmt.setString(2, vo.getPwd());
			int i = pstmt.executeUpdate();
			if(i>0) {
				vo.setResult(1);
				System.out.println("delete :: ���� ����");
			} else {
				System.out.println("Ż�� ����. �� ����� ����");
			}	
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	// ��ü ȸ�� ��� ��ȸ
	public List selectAllMember(List<MemberVO> list) {
		PreparedStatement pstmt;
		System.out.println("MemberEntity :: selectAllMember");
		try {
			System.out.println("try");
			con = ds.getConnection();
			String query = "SELECT * from tb_member";
			ResultSet rs = null;
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				MemberVO vo = new MemberVO();
				vo.setMemID(rs.getString("memID"));
				vo.setName(rs.getString("name"));
				vo.setPhone(rs.getString("phone"));
				vo.setAddress(rs.getString("address"));
				list.add(vo);
				System.out.println(list.toString());
			}

		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
