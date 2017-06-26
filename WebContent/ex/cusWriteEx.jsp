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
<jsp:include page="/common/category.jsp" flush="false"/>
	
	<!-- 거래처 등록 화면 -->
	<div class="container">
		<center>
			<form method="post" name="writeform" action="<%=request.getContextPath() %>/CustomerController?cmd=insertCustomer">
				<input type="hidden" name="num" value="<%=num%>"> 
				<table width="800" border="0" cellspacing="0" cellpadding="0" align="center">
					<tr>
						<td align="right" colspan="2"><a href="cusListEx.jsp"> 거래처 목록</a></td>
					</tr>
					<tr>
						<td width="70" align="center">거래처 코드</td>
						<td width="330" align="left">
						<input type="text" size="10" maxlength="10" name="cusCode"></td>
					</tr>
					<tr>
						<td width="70" align="center">거래처</td>
						<td width="330" align="left">
							<%
								if (request.getParameter("num") == null) {
							%> <input type="text"
							size="40" maxlength="50" name="cusName" align="left">
						</td>
						<%
							} 
						%>
						
					</tr>
					<tr>
						<td width="70" align="center">사업자 번호</td>
						<td width="330" align="left"><input type="text" size="40" maxlength="30"
							name="licenseNum"></td>
					</tr>
					<tr>
						<td width="70" align="center">대표자</td>
						<td width="330" align="left"><input type="text"
							size="40" maxlength="50" name="cusRep" align="left">
						</td>
					</tr>
					<tr>
						<td width="70" align="center">전화번호</td>
						<td width="330" align="left"><input type="text" size="40"
							maxlength="15" name="cusNumber"></td>
					</tr>
					<tr>
						<td width="70" align="center">주소</td>
						<td width="330" align="left"><input type="text" size="40"
							maxlength="50" name="cusAddress"></td>
					</tr>
					<tr>
						<td colspan=2 align="center"><input id="writing"
							type="submit" value="거래처 등록"> <input type="reset"
							value="다시작성"> <input type="button" value="거래처 보기"
							OnClick="window.location.href='cusListEx.jsp'"></td>
					</tr>
				</table>
			</form>
		</center>
		</div>
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