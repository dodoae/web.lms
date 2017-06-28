package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import net.sf.json.JSONException;
import service.MemberService;
import vo.MemberVO;

/**
 * Servlet implementation class MemberController
 */
@WebServlet("/memberController")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private MemberService ms;
    /**
     * @see HttpServlet#HttpServlet()
     */
    
    public MemberController() {
        // TODO Auto-generated constructor stub
    	ms = new MemberService();
    }
    
    protected void memberProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String cmd=request.getParameter("cmd");
    	System.out.println("MemberController :: cmd=" + cmd);
    	if(cmd.equals("idCheck")) {
    		idCheck(request, response);
    	} else if(cmd.equals("logout")) {
    		logout(request, response);
    	} else if(cmd.equals("login")) {
    		login(request, response);
    	} else if(cmd.equals("join")) {
    		join(request, response);
    	} else if(cmd.equals("selectMember")) {
    		selectMember(request, response);
    	} else if(cmd.equals("updateMember")) {
    		updateMember(request, response);
    	} else if(cmd.equals("deleteMember")) {
    		deleteMember(request, response);
    	} else if(cmd.equals("selectAllMember")) {
    		selectAllMember(request, response);
    	}
    }

	// 회원가입시 ID 중복 체크
    protected void idCheck(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
    	String memID = request.getParameter("memID");
    	PrintWriter out = response.getWriter();
    	
    	System.out.println("idCheck :: "+memID);
    	MemberVO vo = new MemberVO(memID);
    	ms = new MemberService(vo);
    	try {
    		ms.idCheck(vo);
    		if(vo.isIdCheck() == true){
        		out.print("no");
        	}   		
    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
    		out.close();
    	}
//    	RequestDispatcher dis = request.getRequestDispatcher("signUp/checkID.jsp");
//    	request.setAttribute("result", vo);
//    	dis.forward(request, response);
    }
    
    // 로그인
    protected void login(HttpServletRequest request, HttpServletResponse response) throws IOException{
    	String memID = request.getParameter("memID");
    	String memPwd = request.getParameter("memPwd");
    	String memName = request.getParameter("name");
    	PrintWriter out = response.getWriter();
    	MemberVO vo = new MemberVO(memID, memPwd, memName);
    	ms = new MemberService(vo);
    	try{
    		ms.loginMember();
    		if(vo.isLoginCheck() == true) {
        		request.getSession().setAttribute("MEMBERID", memID);
        		System.out.println("cont :: "+memID);
        		request.getSession().setAttribute("memName", vo.getName());
        		out.print("yes");
        	} else {
        		out.print("no");
        	}
    	} catch(Exception e) {
    		e.printStackTrace();
    	} finally {
    		out.close();
    	}
    	
    } 
    
    // 로그아웃
    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
    	response.setCharacterEncoding("UTF-8");
    	PrintWriter out = response.getWriter();
//    	System.out.println("MemberContoller :: logout");
		request.getSession().invalidate();
		out.print("로그아웃 되었습니다.");
	}
    
    // 회원가입
    protected void join(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
    	String memID = request.getParameter("memID");
    	String memPwd = request.getParameter("memPwd");
    	String memName = request.getParameter("memName");
    	String memPhone = request.getParameter("memPhone");
    	PrintWriter out = response.getWriter();
    	JSONObject obj = new JSONObject();
    	System.out.println("join::");
    	MemberVO vo = new MemberVO(memID,memPwd,memName,memPhone);
    	ms = new MemberService(vo);
    	try {
    		ms.insertMember();
//    		obj.put("idCheck", vo.isIdCheck());
//    		out.print(obj);
    		
    		if(vo.isIdCheck()==true) {
        		System.out.println("join :: no");
        		out.print("no");
        	} else if(vo.isIdCheck()==false) {
        		System.out.println("join :: yes");
        		out.print("yes");
        	}
    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
    		out.close();
    	}
    System.out.println("Controller :: VO :: " + vo.isIdCheck());
    }
    
    // 정보조회
    private void selectMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setCharacterEncoding("utf-8");
    	String memID = request.getParameter("memID");
    	PrintWriter out = response.getWriter();
    	MemberVO vo = new MemberVO(memID);
    	ms = new MemberService(vo);
    	System.out.println("MemberController :: selectMember");
    	JSONObject obj = new JSONObject();
    	try {
    		System.out.println("try");
    		ms.selectMember();
    		obj.put("pwd", vo.getPwd());
    		obj.put("name", vo.getName());
    		obj.put("phone", vo.getPhone());
    		if(vo.getResult()==1) {
        		out.print(obj);
        	} else {
        		out.print("no");
        	}
    	} catch(Exception e) {
    		e.printStackTrace();
    	} finally {
    		out.close();
    	}
	}
    
    // 회원정보수정
	private void updateMember(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String memID = request.getParameter("memID");
    	String memPwd = request.getParameter("memPwd");
    	String memName = request.getParameter("memName");
    	String memPhone = request.getParameter("memPhone");
    	PrintWriter out = response.getWriter();
    	
    	MemberVO vo = new MemberVO(memID,memPwd,memName,memPhone);
    	ms = new MemberService(vo);
    	try {
    		ms.updateMember();
    		if(vo.getResult()==1) {
        		out.print("yes");
        	}	
    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
    		out.close();
    	}
	}
	
	// 회원탈퇴
	private void deleteMember(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String memID = request.getParameter("memID");
    	String memPwd = request.getParameter("memPwd");
    	PrintWriter out = response.getWriter();
    	
    	MemberVO vo = new MemberVO(memID,memPwd);
    	ms = new MemberService(vo);
    	try {
    		ms.deleteMember();
    		if(vo.getResult()==1) {
        		request.getSession().invalidate();
        		out.print("yes");
        	} 
    	} catch(Exception e) {
    		e.printStackTrace();
    	} finally {
    		out.close();
    	}
	}
	
	// 전체회원조회
	private void selectAllMember(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		response.setCharacterEncoding("utf-8");
		System.out.println("Controller :: selectAllMember");
		JSONObject obj = new JSONObject();
		List<MemberVO> list = new ArrayList(); // Entity에서 오는 list
		MemberVO vo = new MemberVO();
		PrintWriter out = response.getWriter();
		try {
			System.out.println("try");
			ms.selectAllMember(list);
			JSONArray jArray = new JSONArray();//배열이 필요할때
			for (int i = 0; i < list.size(); i++)//배열
			{
				JSONObject sObject = new JSONObject();//배열 내에 들어갈 json
				sObject.put("memID", list.get(i).getMemID());
				sObject.put("name", list.get(i).getName());
				sObject.put("phone", list.get(i).getPhone());
				jArray.add(sObject); // sObject에 넣은 값을 jArray에 배열로 담음
			}
			obj.put("memberlist", jArray);//배열을 넣음
			out.print(obj);
			System.out.println(obj);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//		memberProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		memberProcess(request, response);
	}
}