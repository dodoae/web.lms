<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
  int num = Integer.parseInt(request.getParameter("num"));
  String pageNum = request.getParameter("pageNum");
  String memID = (String) session.getAttribute("MEMBERID");
	boolean login = memID == null ? false : true;
	if (login) {
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
<div class="container">
		<center>
			<b>글삭제</b> <br>
			<form method="POST" name="delForm"
				action="<%=request.getContextPath() %>/BoardController?cmd=deleteBoard"
				onsubmit="return deleteSave()">
				<table border="1" align="right" cellspacing="0" cellpadding="0"
					width="650">
					<tr height="30">
						<td align=center><b>비밀번호를 입력해 주세요.</b></td>
					</tr>
					<tr height="30">
						<td align=center>비밀번호 : <input type="password" name="passwd"
							size="8" maxlength="12"> <input type="hidden" name="num"
							value="<%=num%>"></td>
					</tr>
					<tr height="30">
						<td align=center><input type="submit"
							value="글삭제"> <input type="button" value="글목록"
							onclick="document.location.href='cusList.jsp?pageNum=<%=pageNum%>'">
						</td>
					</tr>
				</table>
			</form>
		</center>
	</div>
</body>
</html>
<%
	} else {
%>
<script>
		alert("로그인을 하세요.");
		document.location.href="../index.jsp";
	</script>
<% } %>