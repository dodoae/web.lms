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
			<table class="form-group">
				<tr>
				<td><label>거래처 코드</label></td><td><input type="text" class="form-control"
				value="<%=vo.getCusCode()%>" readonly="readonly"></td>
				</tr>
				<tr>
				<td><label>거래처명</label></td><td><input type="text" class="form-control"
				maxlength="20" value="<%=vo.getCusName()%>"></td>
				</tr>
				<tr>
				<td><label>사업자 번호</label></td><td><input type="text" class="form-control"
				maxlength="30" value="<%=vo.getLicenseNum()%>"></td>
				</tr>
				<tr>
				<td><label>대표자</label></td><td><input type="text" class="form-control"
				maxlength="20" value="<%=vo.getCusRep()%>"></td>
				</tr>
				<tr>
				<td><label>전화번호</label></td><td><input type="text" class="form-control"
				maxlength="15" value="<%=vo.getCusNumber()%>"></td>
				</tr>
				<tr>
				<td><label>주소</label></td><td><input type="text" class="form-control" maxlength="50"
				maxlength="50" value="<%=vo.getCusAddress()%>"></td>
				</tr>
				<tr>
				<td><label>등록일</label></td><td><input type="text" class="form-control"
				value="<%= sdf.format(vo.getReg_date())%>" readonly="readonly"></td>
				</tr>
				
				<tr height="50">
					<td colspan="4" align="center">
					<input type="button" class="btn btn-success" value="거래처 수정"
						onclick="document.location.href='cusUpdateEx.jsp?num=<%=vo.getNum()%>&pageNum=<%=pageNum%>'">
						&nbsp;&nbsp;&nbsp;&nbsp; <input type="button" class="btn btn-info" value="거래처 목록"
						onclick="document.location.href='cusListEx.jsp?pageNum=<%=pageNum%>'">
						&nbsp;&nbsp;&nbsp;&nbsp; <input type="button" class="btn btn-primary" value="거래처 삭제"
						onclick="document.location.href='cusDeleteEx.jsp?num=<%=vo.getNum()%>&pageNum=<%=pageNum%>'">
						
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