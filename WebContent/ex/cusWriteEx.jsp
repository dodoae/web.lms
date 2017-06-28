<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	//String memID=(String)session.getAttribute("MEMBERID");
	//boolean login = memID == null ? false : true;
	//if (login) {
		int num = 0;
		try {
			if (request.getParameter("num") != null) {
				num = Integer.parseInt(request.getParameter("num"));
			}
%>

<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Logistic Management System</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
</head>
<body>
	<jsp:include page="/common/category.jsp" flush="false" />
	<center>
		<!-- 거래처 등록 화면 -->
		<form method="post" name="writeform"
			action="<%=request.getContextPath() %>/CustomerController?cmd=insertCustomer">
			<input type="hidden" name="num" value="<%=num%>">
			<table class="form-group">
				<tr>
					<td><label>거래처 코드</label></td><td><input type="text" class="form-control" maxlength="10"
						name="cusCode"></td>
				</tr>
				<tr>
					<td><label>거래처명</label></td>
					<td>
						<%
								if (request.getParameter("num") == null) {
							%> <input type="text" class="form-control" maxlength="20"
						name="cusName" align="left">
					</td>
					<%
							} 
						%>
				</tr>
				<tr>
					<td><label>사업자 번호</label></td>
					<td><input type="text" class="form-control" maxlength="30"
						name="licenseNum"></td>
				</tr>
				<tr>
					<td><label>대표자</label></td>
					<td width="330" align="left"><input type="text"
						class="form-control" maxlength="20" name="cusRep"></td>
				</tr>
				<tr>
					<td><label>전화번호</label></td>
					<td><input type="text" class="form-control" maxlength="15"
						name="cusNumber"></td>
				</tr>
				<tr>
					<td><label>주소</label></td>
					<td><input type="text" class="form-control" maxlength="50"
						name="cusAddress"></td>
				</tr>
				<tr height="50">
					<td colspan="5" align="center"><input id="writing"
						type="submit" class="btn btn-success" value="거래처 등록"> <input
						type="reset" class="btn btn-default" value="다시작성"> <input
						type="button" class="btn btn-info" value="거래처 목록"
						OnClick="window.location.href='cusListEx.jsp'">
					</td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>

<%
	} catch (Exception e) {}

	//} else {
%>
<!-- 
<script>
		alert("로그인을 하세요.");
		document.location.href="../index.jsp";
	</script>
 -->
<% //} %>