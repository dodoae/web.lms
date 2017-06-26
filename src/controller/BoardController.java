package controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BoardService;
import vo.BoardVO;

/**
 * Servlet implementation class BoardController
 */
@WebServlet("/BoardController")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService bs;

	public BoardController() {
		bs = new BoardService();
	}

	// Board Process
	protected void boardProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");	// 한글로 받은 값을 EUC-KR로 인코딩
		response.setContentType("text/html;charset=UTF-8"); //값을 보낼때 사용
		String cmd = (String)request.getParameter("cmd");
		System.out.println("BoardController :: cmd = " + cmd);

		if(cmd.equals("insertBoard")) {
			insertBoard(request, response);
		} else if(cmd.equals("getAllBoard")) {
			getAllBoard(request, response);
		} else if(cmd.equals("getBoard")) {
			getBoard(request, response);
		} else if(cmd.equals("updateGetBoard")) {
			updateGetBoard(request, response);
		} else if(cmd.equals("updateBoard")) {
			updateBoard(request, response);
		} else if(cmd.equals("deleteBoard")){
			deleteBoard(request, response);    		
		}
	}

	// delete Board
	private void deleteBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num = Integer.parseInt(request.getParameter("num"));
		String passwd = (String)request.getParameter("passwd");
		String pageNum = (String)request.getParameter("pageNum");	
		BoardVO vo = new BoardVO(num, passwd);

		try {
			bs.deleteBoard(vo);
			if (vo.getResult()==1){
				System.out.println("글삭제 성공");
			} else if(vo.getResult()==0){
				System.out.println("비밀번호 틀림");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/customer/cusList.jsp");
		request.setAttribute("pageNum", pageNum);
		dispatcher.forward(request, response);
	}

	// Update Button Finish
	private void updateBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num = Integer.parseInt(request.getParameter("num"));
		String writer = (String)request.getParameter("writer");
		String subject = (String)request.getParameter("subject");
		String email = (String)request.getParameter("email");
		String content = (String)request.getParameter("content");
		String passwd = (String)request.getParameter("passwd");
		Timestamp reg_date = new Timestamp(System.currentTimeMillis());
		String ip = request.getRemoteAddr();

		BoardVO vo = new BoardVO(num, writer, subject, email, content, passwd, reg_date, ip);	
		try {
			bs.updateBoard(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("customer/cusList.jsp");
	}

	// Update Button Click
	private void updateGetBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		String result = "1";
		BoardVO vo = new BoardVO(num, pageNum);
		try {
			vo = bs.updateGetBoard(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/customer/cusUpdate.jsp?pageNum="+pageNum+"&num="+num);
		request.setAttribute("vo", vo);    	
		request.setAttribute("result", result);
		dispatcher.forward(request, response); 	
	}

	// get Board page Article
	private void getBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");	
		String result = "1";
		BoardVO vo = new BoardVO(num);
		try {
			vo = bs.getBoard(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		RequestDispatcher dis = request.getRequestDispatcher("/customer/cusContent.jsp?pageNum="+pageNum+"&num="+num);
		request.setAttribute("vo", vo);
		request.setAttribute("result", result);
		request.setAttribute("num", num);
		request.setAttribute("pageNum", pageNum);
		dis.forward(request, response); 	
	}

	// Write Click
	private void insertBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num = Integer.parseInt(request.getParameter("num"));
		String writer = (String)request.getParameter("writer");
		String subject = (String)request.getParameter("subject");
		String email = (String)request.getParameter("email");
		String content = (String)request.getParameter("content");
		String passwd = (String)request.getParameter("passwd");
		Timestamp reg_date = new Timestamp(System.currentTimeMillis());
		String ip = request.getRemoteAddr();
		int ref = Integer.parseInt(request.getParameter("ref"));
		int re_step = Integer.parseInt(request.getParameter("re_step"));
		int re_level = Integer.parseInt(request.getParameter("re_level"));

		BoardVO vo = new BoardVO(num, writer, subject, email, content, passwd, reg_date, ip, ref, re_step, re_level);

		try {
			bs.insertBoard(vo);
		} catch(Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("customer/cusList.jsp");
	}

	// get All Board list
	private void getAllBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int count = 0;
		int startRow = Integer.parseInt(request.getParameter("startRow"));
		int endRow = Integer.parseInt(request.getParameter("endRow"));
		List articleList = null;
		try {
			count = bs.getArticleCount();
			articleList = bs.getArticles(startRow, endRow);
		} catch(Exception e) {
			e.printStackTrace();
		}
		RequestDispatcher dis = request.getRequestDispatcher("customer/cusList.jsp?articleList"+articleList);
		request.setAttribute("count", count);
		request.setAttribute("articleList", articleList);
		dis.forward(request, response);
	}



	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boardProcess(request, response);
		//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boardProcess(request, response);
	}

}
