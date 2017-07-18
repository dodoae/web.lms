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

import service.CustomerService;
import vo.CustomerVO;

/**
 * Servlet implementation class CustomerController
 */
@WebServlet("/CustomerController")
public class CustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CustomerService cs;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CustomerController() {
		cs = new CustomerService();
	}

	// Customer Process
	protected void customerProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");	// 한글로 받은 값을 EUC-KR로 인코딩
		response.setContentType("text/html;charset=UTF-8"); //값을 보낼때 사용
		String cmd = (String)request.getParameter("cmd");
		System.out.println("CustomerController :: cmd = " + cmd);

		if(cmd.equals("insertCustomer")) {
			insertCustomer(request, response);
		} else if(cmd.equals("getAllCustomer")) {
			getAllCustomer(request, response);
		} else if(cmd.equals("getCustomer")) {
			getCustomer(request, response);
		} /* else if(cmd.equals("updateGetCustomer")) {
			updateGetCustomer(request, response);
		} */else if(cmd.equals("updateCustomer")) {
			updateCustomer(request, response);
		} else if(cmd.equals("deleteCustomer")){
			deleteCustomer(request, response);    		
		}
	}

	// delete Board
	private void deleteCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num = Integer.parseInt(request.getParameter("num"));
		String cusCode = (String)request.getParameter("cusCode");
		String pageNum = (String)request.getParameter("pageNum");	
		CustomerVO vo = new CustomerVO(num, cusCode);
		try {
			cs.deleteCustomer(vo);
			if (vo.getResult()==1){
				System.out.println("거래처 삭제 성공");
			} else {
				System.out.println("거래처 삭제 실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
//		RequestDispatcher dispatcher = request.getRequestDispatcher("/ex/cusListEx.jsp");
		request.setAttribute("pageNum", pageNum);
//		dispatcher.forward(request, response);
	}

	// Update Button Finish
	private void updateCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num = Integer.parseInt(request.getParameter("num"));
		String cusCode = (String)request.getParameter("cusCode");
		String cusName = (String)request.getParameter("cusName");
		String licenseNum = (String)request.getParameter("licenseNum");
		String cusRep = (String)request.getParameter("cusRep");
		String cusNumber = (String)request.getParameter("cusNumber");
		String cusAddress = (String)request.getParameter("cusAddress");
		//Timestamp reg_date = new Timestamp(System.currentTimeMillis());
		System.out.println("Controller:updateCustomer");
		CustomerVO vo = new CustomerVO(num, cusCode, cusName, licenseNum, cusRep, cusNumber, cusAddress);	
		try {
			cs.updateCustomer(vo);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
/*
	// Update Button Click
	private void updateGetCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		String result = "1";
		CustomerVO vo = new CustomerVO(num, pageNum);
		try {
			vo = cs.updateGetCustomer(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/ex/cusUpdateEx.jsp?pageNum="+pageNum+"&num="+num);
		request.setAttribute("vo", vo);    	
		request.setAttribute("result", result);
		dispatcher.forward(request, response); 	
	}
*/
	// 선택한 거래처 정보를 가져옴.
	private void getCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");	
		String result = "1";
		CustomerVO vo = new CustomerVO(num);
		try {
			vo = cs.getCustomer(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		RequestDispatcher dis = request.getRequestDispatcher("/ex/cusContentEx.jsp?pageNum="+pageNum+"&num="+num);
		request.setAttribute("vo", vo);
		request.setAttribute("result", result);
		request.setAttribute("num", num);
		request.setAttribute("pageNum", pageNum);
		dis.forward(request, response); 	
	}

	// 거래처 등록
	private void insertCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		int num = Integer.parseInt(request.getParameter("num"));
		String cusCode = (String)request.getParameter("cusCode");
		String cusName = (String)request.getParameter("cusName");
		String licenseNum = (String)request.getParameter("licenseNum");
		String cusRep = (String)request.getParameter("cusRep");
		String cusNumber = (String)request.getParameter("cusNumber");
		String cusAddress = (String)request.getParameter("cusAddress");
		Timestamp reg_date = new Timestamp(System.currentTimeMillis());

		CustomerVO vo = new CustomerVO(num,cusCode, cusName, licenseNum, cusRep, cusNumber, cusAddress, reg_date);

		try {
			cs.insertCustomer(vo);
		} catch(Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("ex/cusListEx.jsp");
	}

	// get All Customer list
	private void getAllCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int count = 0;
		int startRow = Integer.parseInt(request.getParameter("startRow"));
		int endRow = Integer.parseInt(request.getParameter("endRow"));
		System.out.println("Controller :: getAllCustomer :: ");
		List customerList = null;
		System.out.println("CustomerController :: getAllCustomer ");
		try {
			count = cs.getCustomerCount();
			customerList = cs.getArticles(startRow, endRow);
		} catch(Exception e) {
			e.printStackTrace();
		}
		RequestDispatcher dis = request.getRequestDispatcher("ex/cusListEx.jsp?customerList"+customerList);
		request.setAttribute("count", count);
		request.setAttribute("customerList", customerList);
		dis.forward(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		customerProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		customerProcess(request, response);
	}

}
