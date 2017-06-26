<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="vo.CustomerVO"%>
<%
	/*String memID=(String)session.getAttribute("MEMBERID");
	boolean login = memID == null ? false : true;
	if (login) {*/
%>
<%
		  int num = Integer.parseInt(request.getParameter("num"));
		  String pageNum = request.getParameter("pageNum");
		  if(request.getAttribute("result") == null) {
%>
			<jsp:forward page="/CustomerController?cmd=updateGetCustomer">
				<jsp:param value="<%=pageNum%>" name="pageNum" />
				<jsp:param value="<%=num%>" name="num" />
			</jsp:forward>
<%  
  			}
		  try {
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
			<b>거래처 수정</b> <br>
			<form method="post" name="writeform"
				action="<%=request.getContextPath() %>/CustomerController?cmd=updateCustomer">
				<table width="650" border="1" cellspacing="0" cellpadding="0"
					align="right">
					<tr>
						<td width="70" align="center">거래처 코드</td>
						<td align="left" width="330">
							<input type="text" size="10" maxlength="10" name="cusCode" value="<%=vo.getCusCode()%>">
							<input type="hidden" name="cusCode" value="<%=vo.getNum()%>"></td>
					</tr>
					<tr>
						<td width="70" align="center">거래처</td>
						<td align="left" width="330"><input type="text" size="40"
							maxlength="50" name="cusName" value="<%=vo.getCusName()%>"></td>
					</tr>
					<tr>
						<td width="70" align="center">사업자 번호</td>
						<td align="left" width="330"><input type="text" size="40"
							maxlength="50" name="licenseNum" value="<%=vo.getLicenseNum()%>"></td>
					</tr>
					<tr>
						<td width="70" align="center">대표자</td>
						<td align="left" width="330"><input type="text" size="40"
							maxlength="50" name="cusRep" value="<%=vo.getCusRep()%>"></td>
					</tr>
					<tr>
						<td width="70" align="center">전화번호</td>
						<td align="left" width="330"><input type="text" size="40"
							maxlength="50" name="cusNumber" value="<%=vo.getCusNumber()%>"></td>
					</tr>

					<tr>
						<td width="70" align="center">주소</td>
						<td align="left" width="330"><input type="text" size="40"
							maxlength="50" name="cusAddress" value="<%=vo.getCusAddress()%>"></td>
					</tr>
					<tr>
						<td colspan=2 align="center"><input type="submit" value="글수정">
							<input type="reset" value="다시작성"> <input type="button"
							value="목록보기"
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
	//} else {
%>
<!-- 
<script>
	alert("로그인을 하세요.");
		document.location.href="../index.jsp";
	</script>
 -->
<% // } %>