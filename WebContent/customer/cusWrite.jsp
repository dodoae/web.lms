<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String memID=(String)session.getAttribute("MEMBERID");
	boolean login = memID == null ? false : true;
	if (login) {
		int num = 0, ref = 1, re_step = 0, re_level = 0;
		try {
			if (request.getParameter("num") != null) {
				num = Integer.parseInt(request.getParameter("num"));
				ref = Integer.parseInt(request.getParameter("ref"));
				re_step = Integer.parseInt(request.getParameter("re_step"));
				re_level = Integer.parseInt(request.getParameter("re_level"));
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
			<form method="post" name="writeform" action="<%=request.getContextPath() %>/BoardController?cmd=insertBoard"
				onsubmit="return writeSave()">
				<input type="hidden" name="num" value="<%=num%>"> 
				<input type="hidden" name="ref" value="<%=ref%>"> 
				<input type="hidden" name="re_step" value="<%=re_step%>"> 
				<input type="hidden" name="re_level" value="<%=re_level%>">

				<table width="650" border="0" cellspacing="0" cellpadding="0" align="center">
					<tr>
						<td align="right" colspan="2"><a href="cusList.jsp"> 거래처 목록</a></td>
					</tr>
					<tr>
						<td width="70" align="center">이 름</td>
						<td width="330" align="left">
						<input type="text" size="10" maxlength="10" name="writer"></td>
					</tr>
					<tr>
						<td width="70" align="center">제 목</td>
						<td width="330" align="left">
							<%
								if (request.getParameter("num") == null) {
							%> <input type="text"
							size="40" maxlength="50" name="subject" align="left">
						</td>
						<%
							} else {
						%>
						<input type="text" size="40" maxlength="50" name="subject"
							value="[답변]">
						</td>
						<%
							}
						%>
					</tr>
					<tr>
						<td width="70" align="center">Email</td>
						<td width="330" align="left"><input type="text" size="40" maxlength="30"
							name="email"></td>
					</tr>
					<tr>
						<td width="70" align="center">내 용</td>
						<td width="330" align="left"><textarea name="content" rows="13" cols="80"></textarea>
						</td>
					</tr>
					<tr>
						<td width="70" align="center">비밀번호</td>
						<td width="330" align="left"><input type="password" size="8"
							maxlength="12" name="passwd"></td>
					</tr>
					<tr>
						<td colspan=2 align="center"><input id="writing"
							type="submit" value="글쓰기"> <input type="reset"
							value="다시작성"> <input type="button" value="목록보기"
							OnClick="window.location.href='cusList.jsp'"></td>
					</tr>
				</table>
			</form>
		</center>
		</div>
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