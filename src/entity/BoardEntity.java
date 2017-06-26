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

import vo.BoardVO;

public class BoardEntity {
	private Connection con;
	private Context ctx;
	private DataSource ds;
	private BoardVO vo;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public BoardEntity() {
		try {
			ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/mysql");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	// 글쓰기
	public void insertBoard(BoardVO vo) {
		int num=vo.getNum();
		int ref=vo.getRef();
		int re_step=vo.getRe_step();
		int re_level=vo.getRe_level();
		int number=0;
		String query="";

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement("select max(num) from board");
			rs = pstmt.executeQuery();

			if (rs.next()) {
				number=rs.getInt(1)+1;
			} else {
				number=1; 
			}
			if (num!=0) {  
				query="update board set re_step=re_step+1 where ref= ? and re_step> ?";
				pstmt = con.prepareStatement(query);
				pstmt.setInt(1, ref);
				pstmt.setInt(2, re_step);
				pstmt.executeUpdate();
				re_step=re_step+1;
				re_level=re_level+1;
			}else{
				ref=number;
				re_step=0;
				re_level=0;
			}	 	

			query = "insert into board(writer,email,subject,passwd,reg_date,readcount,";
			query += "ref,re_step,re_level,content,ip) values(?,?,?,?,?,?,?,?,?,?,?)";

			pstmt = con.prepareStatement(query);
			pstmt.setString(1, vo.getWriter());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getSubject());
			pstmt.setString(4, vo.getPasswd());
			pstmt.setTimestamp(5, vo.getReg_date());
			pstmt.setInt(6, vo.getReadcount());
			pstmt.setInt(7, ref);
			pstmt.setInt(8, re_step);
			pstmt.setInt(9, re_level);
			pstmt.setString(10, vo.getContent());
			pstmt.setString(11, vo.getIp());

			pstmt.executeUpdate();
			System.out.println("글 등록 성공");
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

	// 조회수 count
	public int getArticleCount() {
		int x=0;
		try {
			con = ds.getConnection();

			pstmt = con.prepareStatement("select count(*) from board");
			rs = pstmt.executeQuery();

			if (rs.next()) {
				x= rs.getInt(1);
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

	// list들을 불러옴
	public List getArticles(int start, int end) throws Exception {
		List articleList;
		articleList = new ArrayList(end);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(
					"select * from board order by ref desc, re_step asc limit ?,? ");
			pstmt.setInt(1, start-1);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();

			if (rs.next()) {	
				do{
					BoardVO vo= new BoardVO();
					vo.setNum(rs.getInt("num"));
					vo.setWriter(rs.getString("writer"));
					vo.setEmail(rs.getString("email"));
					vo.setSubject(rs.getString("subject"));
					vo.setPasswd(rs.getString("passwd"));
					vo.setReg_date(rs.getTimestamp("reg_date"));
					vo.setReadcount(rs.getInt("readcount"));
					vo.setRef(rs.getInt("ref"));
					vo.setRe_step(rs.getInt("re_step"));
					vo.setRe_level(rs.getInt("re_level"));
					vo.setContent(rs.getString("content"));
					vo.setIp(rs.getString("ip")); 
					articleList.add(vo);
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
		return articleList;
	}

	// list에서 글을 눌렀을 때 게시판 내용을 가져옴
	public BoardVO getBoard(BoardVO vo) throws Exception {
		int num = vo.getNum();
		ResultSet rs = null;
		try {
			con = ds.getConnection();

			pstmt = con.prepareStatement(
					"update board set readcount=readcount+1 where num = ?");
			pstmt.setInt(1, num);
			pstmt.executeUpdate();

			pstmt = con.prepareStatement(
					"select * from board where num = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				vo = new BoardVO();
				vo.setNum(rs.getInt("num"));
				vo.setWriter(rs.getString("writer"));
				vo.setEmail(rs.getString("email"));
				vo.setSubject(rs.getString("subject"));
				vo.setPasswd(rs.getString("passwd"));
				vo.setReg_date(rs.getTimestamp("reg_date"));
				vo.setReadcount(rs.getInt("readcount"));
				vo.setRef(rs.getInt("ref"));
				vo.setRe_step(rs.getInt("re_step"));
				vo.setRe_level(rs.getInt("re_level"));
				vo.setContent(rs.getString("content"));
				vo.setIp(rs.getString("ip"));     
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
	public BoardVO updateGetBoard(BoardVO vo) throws Exception {
		int num = vo.getNum();
		try {
			con = ds.getConnection();

			pstmt = con.prepareStatement(
					"select * from board where num = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				vo = new BoardVO();
				vo.setNum(rs.getInt("num"));
				vo.setWriter(rs.getString("writer"));
				vo.setEmail(rs.getString("email"));
				vo.setSubject(rs.getString("subject"));
				vo.setPasswd(rs.getString("passwd"));
				vo.setReg_date(rs.getTimestamp("reg_date"));
				vo.setReadcount(rs.getInt("readcount"));
				vo.setRef(rs.getInt("ref"));
				vo.setRe_step(rs.getInt("re_step"));
				vo.setRe_level(rs.getInt("re_level"));
				vo.setContent(rs.getString("content"));
				vo.setIp(rs.getString("ip"));   
				System.out.println("Entity :: updateGetBoard :: "+vo.getWriter());
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

	public int updateBoard(BoardVO vo) throws Exception {
		ResultSet rs = null;
		String dbpasswd="";
		String sql="";
		int x=-1;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(
					"select passwd from board where num = ?");
			pstmt.setInt(1, vo.getNum());
			rs = pstmt.executeQuery();
			System.out.println("updateBoard :: getNum : " + vo.getNum());

			if(rs.next()){
				dbpasswd= rs.getString("passwd"); 
				if(dbpasswd.equals(vo.getPasswd())){
					sql="update board set writer=?,email=?,subject=?,passwd=?";
					sql+=",content=? where num=?";
					pstmt = con.prepareStatement(sql);

					pstmt.setString(1, vo.getWriter());
					pstmt.setString(2, vo.getEmail());
					pstmt.setString(3, vo.getSubject());
					pstmt.setString(4, vo.getPasswd());
					pstmt.setString(5, vo.getContent());
					pstmt.setInt(6, vo.getNum());
					pstmt.executeUpdate();
					System.out.println("Entity :: updateBoard :: "+vo.getWriter());
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

	public void deleteBoard(BoardVO vo) {
		String dbpasswd="";
		vo.setResult(-1); 
		try {
			con = ds.getConnection();

			pstmt = con.prepareStatement(
					"select passwd from board where num = ?");
			pstmt.setInt(1, vo.getNum());
			rs = pstmt.executeQuery();

			if(rs.next()){
				dbpasswd= rs.getString("passwd"); 
				if(dbpasswd.equals(vo.getPasswd())){
					pstmt = con.prepareStatement(
							"delete from board where num=?");
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
