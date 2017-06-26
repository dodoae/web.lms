<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="vo.CustomerVO"%>
<%@ page import="entity.BoardEntity"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%
	/* String memID=(String)session.getAttribute("MEMBERID");
	boolean login = memID == null ? false : true;
	if (login) { */
	   int num = Integer.parseInt(request.getParameter("num"));
	   String pageNum = request.getParameter("pageNum");
	   String result = (String)request.getAttribute("result");
	
	   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	  
		if(result!="1") {
%>
<jsp:forward page="/CustomerController?cmd=getCustomer">
	<jsp:param value="<%=num %>" name="num" />
	<jsp:param value="<%=pageNum %>" name="pageNum" />
</jsp:forward>
<%
		}
		try{
			CustomerVO vo = (CustomerVO)request.getAttribute("vo");
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
		<form>
			<table width="650" border="1" cellspacing="0" cellpadding="0"
				align="center">
				<tr height="30">
					<td align="center" width="125">거래처 코드</td>
					<td align="center" width="125" align="center"><%=vo.getCusCode()%></td>
					<td align="center" width="125">거래처</td>
					<td align="center" width="125" align="center"><%=vo.getCusName()%></td>
				</tr>
				<tr height="30">
					<td align="center" width="125">대표자</td>
					<td align="center" width="375" align="center"><%=vo.getCusRep()%></td>
					<td align="center" width="125">사업자 번호</td>
					<td align="center" width="375" align="center"><%=vo.getLicenseNum()%></td>
				</tr>
				<tr height="30">
					<td align="center" width="125">전화번호</td>
					<td align="center" width="375" colspan="3"><%=vo.getCusNumber()%></td>
				</tr>
				<tr>
					<td align="center" width="125">주소</td>
					<td align="center" width="375" colspan="3"><%=vo.getCusAddress()%></td>
				</tr>
				<td align="center" width="125">등록일</td>
					<td align="center" width="125" align="center" colspan="3"><%= sdf.format(vo.getReg_date())%></td>
				<tr height="30">
					<td colspan="4" align="center"><input type="button"
						value="거래처 수정"
						onclick="document.location.href='cusUpdateEx.jsp?num=<%=vo.getNum()%>&pageNum=<%=pageNum%>'">
						&nbsp;&nbsp;&nbsp;&nbsp; <input type="button" value="거래처 삭제"
						onclick="document.location.href='cusDeleteEx.jsp?num=<%=vo.getNum()%>&pageNum=<%=pageNum%>'">
						&nbsp;&nbsp;&nbsp;&nbsp; <input type="button" value="거래처 목록"
						onclick="document.location.href='cusListEx.jsp?pageNum=<%=pageNum%>'">
					</td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>
<%
	} catch (Exception e) {}

	// } else {
%>
<!-- 
<script>
		alert("로그인을 하세요.");
		document.location.href="../index.jsp";
	</script>
 -->
<% // } %>