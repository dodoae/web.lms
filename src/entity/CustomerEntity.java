package entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import vo.CustomerVO;

public class CustomerEntity {
	private Connection con;
	private Context ctx;
	private DataSource ds;
	private CustomerVO vo;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public CustomerEntity() {
		try {
			ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/mysql");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	// 글쓰기
	public void insertCustomer(CustomerVO vo) {
		int num=vo.getNum();
		int number=0;
		String query="";

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement("select max(num) from customer_ex");
			rs = pstmt.executeQuery();

			if (rs.next()) {
				number=rs.getInt(1)+1;
			} else {
				number=1; 
			} 

			query = "insert into customer_ex(cusCode,cusName,licenseNum,cusRep,cusNumber,cusAddress,reg_date) values(?,?,?,?,?,?,?)";

			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, vo.getCusCode());
			pstmt.setString(2, vo.getCusName());
			pstmt.setString(3, vo.getLicenseNum());
			pstmt.setString(4, vo.getCusRep());
			pstmt.setString(5, vo.getCusNumber());
			pstmt.setString(6, vo.getCusAddress());
			pstmt.setTimestamp(7, vo.getReg_date());
			pstmt.executeUpdate();
			System.out.println("거래처 등록 성공");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try { 
					rs.close(); 
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) { 
				try { 
					pstmt.close(); 
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try { 
					con.close(); 
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}


	// list들을 불러옴
	public List getArticles(int start, int end) throws Exception {
		List customerList;
		customerList = new ArrayList(end);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(
					"select * from customer_ex order by num desc limit ?,? ");
			pstmt.setInt(1, start-1);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();

			if (rs.next()) {

				do{
					CustomerVO vo= new CustomerVO();
					vo.setNum(rs.getInt("num"));
					vo.setCusCode(rs.getString("cusCode"));
					vo.setCusName(rs.getString("cusName"));
					vo.setLicenseNum(rs.getString("licenseNum"));
					vo.setCusRep(rs.getString("cusRep"));
					vo.setCusNumber(rs.getString("cusNumber"));
					vo.setCusAddress(rs.getString("cusAddress"));
					vo.setReg_date(rs.getTimestamp("reg_date"));
					customerList.add(vo);
				}while(rs.next());
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) {
				try { 
					rs.close(); 
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) { 
				try { 
					pstmt.close(); 
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try { 
					con.close(); 
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return customerList;
	}

	// list에서 글을 눌렀을 때 게시판 내용을 가져옴
	public CustomerVO getCustomer(CustomerVO vo) throws Exception {
		int num = vo.getNum();
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			
			/*
			pstmt = con.prepareStatement(
					"update customer_ex set readcount=readcount+1 where num = ?");
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			*/
			
			pstmt = con.prepareStatement(
					"select * from customer_ex where num = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				vo = new CustomerVO();
				vo.setNum(rs.getInt("num"));
				vo.setCusCode(rs.getString("cusCode"));
				vo.setCusName(rs.getString("cusName"));
				vo.setLicenseNum(rs.getString("licenseNum"));
				vo.setCusRep(rs.getString("cusRep"));
				vo.setCusAddress(rs.getString("cusAddress"));
				vo.setCusNumber(rs.getString("cusNumber"));
				vo.setReg_date(rs.getTimestamp("reg_date"));
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) {
				try { 
					rs.close(); 
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) { 
				try { 
					pstmt.close(); 
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try { 
					con.close(); 
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return vo;
	}

	// 
	public CustomerVO updateGetCustomer(CustomerVO vo) throws Exception {
		int num = vo.getNum();
		try {
			con = ds.getConnection();

			pstmt = con.prepareStatement(
					"select * from customer_ex where num = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				vo = new CustomerVO();
				vo.setNum(rs.getInt("num"));
				vo.setCusCode(rs.getString("cusCode"));
				vo.setCusName(rs.getString("cusName"));
				vo.setLicenseNum(rs.getString("licenseNum"));
				vo.setCusRep(rs.getString("cusRep"));
				vo.setCusNumber(rs.getString("cusNumber"));
				vo.setCusAddress(rs.getString("cusAddress"));
				vo.setReg_date(rs.getTimestamp("reg_date"));
				System.out.println("Entity :: updateGetCustomer :: ");
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) {
				try { 
					rs.close(); 
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) { 
				try { 
					pstmt.close(); 
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try { 
					con.close(); 
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return vo;
	}

	public int updateCustomer(CustomerVO vo) throws Exception {
		ResultSet rs = null;
		String dbpasswd="";
		String sql="";
		int x=-1;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(
					"select passwd from customer_ex where num = ?");
			pstmt.setInt(1, vo.getNum());
			rs = pstmt.executeQuery();
			System.out.println("updateCustomer :: getNum : " + vo.getNum());

			if(rs.next()){
				dbpasswd= rs.getString("passwd"); 
				if(dbpasswd.equals(vo.getPasswd())){
					sql="update customer_ex set writer=?,email=?,subject=?,passwd=?";
					sql+=",content=? where num=?";
					pstmt = con.prepareStatement(sql);

					pstmt.setString(1, vo.getWriter());
					pstmt.setString(2, vo.getEmail());
					pstmt.setString(3, vo.getSubject());
					pstmt.setString(4, vo.getPasswd());
					pstmt.setString(5, vo.getContent());
					pstmt.setInt(6, vo.getNum());
					pstmt.executeUpdate();
					System.out.println("Entity :: updateCustomer :: "+vo.getWriter());
					x= 1;	// 수정되면 1
				}else{
					System.out.println("비밀번호가 틀렸습니다.");
					x= 0;	// 아니면 0
				}
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) {
				try { 
					rs.close(); 
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) { 
				try { 
					pstmt.close(); 
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try { 
					con.close(); 
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return x;
	}

	public void deleteCustomer(CustomerVO vo) {
		String dbpasswd="";
		vo.setResult(-1); 
		try {
			con = ds.getConnection();

			pstmt = con.prepareStatement(
					"select passwd from customer_ex where num = ?");
			pstmt.setInt(1, vo.getNum());
			rs = pstmt.executeQuery();

			if(rs.next()){
				dbpasswd= rs.getString("passwd"); 
				if(dbpasswd.equals(vo.getPasswd())){
					pstmt = con.prepareStatement(
							"delete from customer_ex where num=?");
					pstmt.setInt(1, vo.getNum());
					pstmt.executeUpdate();
					vo.setResult(1); //글삭제 성공
				}else
					vo.setResult(0); //비밀번호 틀림
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) {
				try { 
					rs.close(); 
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) { 
				try { 
					pstmt.close(); 
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try { 
					con.close(); 
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
