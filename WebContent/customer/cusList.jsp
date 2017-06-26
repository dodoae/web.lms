<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="vo.BoardVO"%>
<%@ page import="java.util.List"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%!int pageSize = 10;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");%>
<%
	String memID = (String) session.getAttribute("MEMBERID");
	boolean login = memID == null ? false : true;
	if (login) {
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}

		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		int count = 0;
		int number = 0;

		List articleList = null;

		articleList = (List) request.getAttribute("articleList");
		if (articleList == null) {
%>
<jsp:forward page="/BoardController?cmd=getAllBoard">
	<jsp:param value="<%=count%>" name="count" />
	<jsp:param value="<%=startRow%>" name="startRow" />
	<jsp:param value="<%=endRow%>" name="endRow" />
</jsp:forward>
<%
	}
		count = (int) request.getAttribute("count");
		number = count - (currentPage - 1) * pageSize;
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
		<b>등록된 거래처(전체 거래처:<%=count%>)
		</b>
		<table width="700">
			<tr>
				<td align="right"><a
					href="<%=request.getContextPath()%>/customer/cusWrite.jsp">거래처 등록</a></td>
			</tr>
		</table>

		<%
			if (count == 0) {
		%>
		<table class="table table-striped" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td align="center">등록된 거래처가 없습니다.</td>
		</table>
		<%
			} else {
		%>
		<table class="table table-striped" cellpadding="0" cellspacing="0"
			align="center">
			<tr height="30">
				<td align="center" width="50">번 호</td>
				<td align="center" width="250">제 목</td>
				<td align="center" width="100">작성자</td>
				<td align="center" width="150">작성일</td>
				<td align="center" width="50">조 회</td>
				<td align="center" width="100">IP</td>
			</tr>
			<%
				for (int i = 0; i < articleList.size(); i++) {
							BoardVO vo = (BoardVO) articleList.get(i);
			%>
			<tr height="30">
				<td align="center" width="50"><%=number--%></td>
				<td width="250">
				<a href="<%=request.getContextPath()%>/customer/cusContent.jsp?num=<%=vo.getNum()%>&pageNum=<%=currentPage%>"><%=vo.getSubject()%></a>
				</td>
				<td align="center" width="100"><a
					href="mailto:<%=vo.getEmail()%>"><%=vo.getWriter()%></a></td>
				<td align="center" width="150"><%=sdf.format(vo.getReg_date())%></td>
				<td align="center" width="50"><%=vo.getReadcount()%></td>
				<td align="center" width="100"><%=vo.getIp()%></td>
			</tr>
			<%
				}
			%>
		</table>
		<%
			}
		%>

		<%
			if (count > 0) {
					int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);

					int startPage = (int) (currentPage / 10) * 10 + 1;
					int pageBlock = 10;
					int endPage = startPage + pageBlock - 1;
					if (endPage > pageCount)
						endPage = pageCount;

					if (startPage > 10) {
		%>
		<a
			href="/lms.webPrj/customer/cusList.jsp?pageNum=<%=startPage - 10%>">[이전]</a>
		<%
			}
					for (int i = startPage; i <= endPage; i++) {
		%>
		<a href="/lms.webPrj/customer/cusList.jsp?pageNum=<%=i%>">[<%=i%>]
		</a>
		<%
			}
					if (endPage < pageCount) {
		%>
		<a
			href="/lms.webPrj/customer/cusList.jsp?pageNum=<%=startPage + 10%>">[다음]</a>
		<%
			}
				}
		%>
	</center>
	</div>
	</div>
	</div>
</body>
</html>
<%
	} else {
%>
<script>
	alert("로그인을 하세요.");
	document.location.href = "../index.jsp";
</script>
<% } %>