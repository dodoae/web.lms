<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="vo.BoardVO"%>
<%
	String memID=(String)session.getAttribute("MEMBERID");
	boolean login = memID == null ? false : true;
	if (login) {
%>
<%
		  int num = Integer.parseInt(request.getParameter("num"));
		  String pageNum = request.getParameter("pageNum");
		  if(request.getAttribute("result") == null) {
%>
			<jsp:forward page="/BoardController?cmd=updateGetBoard">
				<jsp:param value="<%=pageNum%>" name="pageNum" />
				<jsp:param value="<%=num%>" name="num" />
			</jsp:forward>
<%  
  			}
		  try {
		      BoardVO vo = (BoardVO)request.getAttribute("vo"); 
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
			<b>글수정</b> <br>
			<form method="post" name="writeform"
				action="<%=request.getContextPath() %>/BoardController?cmd=updateBoard"
				onsubmit="return writeSave()">
				
				<table width="650" border="1" cellspacing="0" cellpadding="0"
					align="right">
					<tr>
						<td width="70" align="center">이 름</td>
						<td align="left" width="330">
							<input type="text" size="10" maxlength="10" name="writer" value="<%=vo.getWriter()%>">
							<input type="hidden" name="num" value="<%=vo.getNum()%>"></td>
					</tr>
					<tr>
						<td width="70" align="center">제 목</td>
						<td align="left" width="330"><input type="text" size="40"
							maxlength="50" name="subject" value="<%=vo.getSubject()%>"></td>
					</tr>
					<tr>
						<td width="70" align="center">Email</td>
						<td align="left" width="330"><input type="text" size="40"
							maxlength="30" name="email" value="<%=vo.getEmail()%>"></td>
					</tr>
					<tr>
						<td width="70" align="center">내 용</td>
						<td align="left" width="330"><textarea name="content"
								rows="13" cols="80"><%=vo.getContent()%></textarea></td>
					</tr>
					<tr>
						<td width="70" align="center">비밀번호</td>
						<td align="left" width="330"><input type="password" size="8"
							maxlength="12" name="passwd"></td>
					</tr>
					<tr>
						<td colspan=2 align="center"><input type="submit" value="글수정">
							<input type="reset" value="다시작성"> <input type="button"
							value="목록보기"
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
}
<% } %>