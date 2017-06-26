<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="vo.BoardVO"%>
<%@ page import="entity.BoardEntity"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%
	String memID=(String)session.getAttribute("MEMBERID");
	boolean login = memID == null ? false : true;
	if (login) {
	   int num = Integer.parseInt(request.getParameter("num"));
	   String pageNum = request.getParameter("pageNum");
	   String result = (String)request.getAttribute("result");
	
	   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	  
		if(result!="1") {
%>
<jsp:forward page="/BoardController?cmd=getBoard">
	<jsp:param value="<%=num %>" name="num" />
	<jsp:param value="<%=pageNum %>" name="pageNum" />
</jsp:forward>
<%
		}
		try{
			BoardVO vo = (BoardVO)request.getAttribute("vo");
			int ref=vo.getRef();
			int re_step=vo.getRe_step();
			int re_level=vo.getRe_level();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Logistic Management System</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
</head>
<body>
	<jsp:include page="/common/category.jsp" flush="false" />
	<center>
		<b>글내용 보기</b> <br>
		<form>
			<table width="650" border="1" cellspacing="0" cellpadding="0"
				align="center">
				<tr height="30">
					<td align="center" width="125">글번호</td>
					<td align="center" width="125" align="center"><%=vo.getNum()%></td>
					<td align="center" width="125">조회수</td>
					<td align="center" width="125" align="center"><%=vo.getReadcount()%></td>
				</tr>
				<tr height="30">
					<td align="center" width="125">작성자</td>
					<td align="center" width="125" align="center"><%=vo.getWriter()%></td>
					<td align="center" width="125">작성일</td>
					<td align="center" width="125" align="center"><%= sdf.format(vo.getReg_date())%></td>
				</tr>
				<tr height="30">
					<td align="center" width="125">글제목</td>
					<td align="center" width="375" align="center" colspan="3"><%=vo.getSubject()%></td>
				</tr>
				<tr>
					<td align="center" width="125">글내용</td>
					<td align="center" width="375" colspan="3"><pre><%=vo.getContent()%></pre></td>
				</tr>
				<tr height="30">
					<td colspan="4" align="center"><input type="button"
						value="글수정"
						onclick="document.location.href='cusUpdate.jsp?num=<%=vo.getNum()%>&pageNum=<%=pageNum%>'">
						&nbsp;&nbsp;&nbsp;&nbsp; <input type="button" value="글삭제"
						onclick="document.location.href='cusDelete.jsp?num=<%=vo.getNum()%>&pageNum=<%=pageNum%>'">
						&nbsp;&nbsp;&nbsp;&nbsp; <input type="button" value="답글쓰기"
						onclick="document.location.href='cusWrite.jsp?num=<%=num%>&ref=<%=ref%>&re_step=<%=re_step%>&re_level=<%=re_level%>'">
						&nbsp;&nbsp;&nbsp;&nbsp; <input type="button" value="글목록"
						onclick="document.location.href='cusList.jsp?pageNum=<%=pageNum%>'">
					</td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>
<%
	} catch (Exception e) {}

	} else {
%>
<script>
		alert("로그인을 하세요.");
		document.location.href="../index.jsp";
	</script>
<% } %>