<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="vo.CustomerVO"%>
<%@ page import="java.util.List"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%!
	int pageSize = 10;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
%>
<%
	String memID = (String) session.getAttribute("MEMBERID");
	boolean login = memID == null ? false : true;
	if (login) {
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}
		System.out.println("cusListEx.jsp :: CusListEx :: pageNum :: " + pageNum);
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		int count = 0; // 거래처의 개수 count
		int number = 0;

		List customerList = null;

		customerList = (List) request.getAttribute("customerList");
		if (customerList == null) {
%>
<jsp:forward page="/CustomerController?cmd=getAllCustomer">
	<jsp:param value="<%=count %>" name="count"/>
	<jsp:param value="<%=startRow%>" name="startRow" />
	<jsp:param value="<%=endRow%>" name="endRow" />
</jsp:forward>
<%
	}
		count = (int)request.getAttribute("count");
		number = count - (currentPage - 1) * pageSize;
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Logistic Management System</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="../js/script.js"></script>
<link href="<%=request.getContextPath()%>/css/style.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<jsp:include page="/common/category.jsp" flush="false" />
	<form class="navbar-form navbar-left" role="search">
		<a href="cusWriteEx.jsp"><input type="button" class="btn btn-info"
			value="거래처 등록"></a> <input type="button" class="btn btn-info"
			value="거래처 수정" id="cusUpdate"> <a href="cusDeleteEx.jsp"><input
			type="button" class="btn btn-info" value="거래처 삭제" id="cusDelete"></a>
	</form>
	<form class="navbar-form navbar-right" role="search">
		<div class="form-group">
			<input type="text" class="form-control" id="" placeholder="거래처명을 입력하세요.">
		</div>
		<button type="button" class="btn btn-default">Submit</button>
	</form>
	<center>
		<b>등록된 거래처 : <%=count%></b>
		<%
			if (count == 0) {
		%>
				<table class="table table-striped" cellpadding="0" cellspacing="0">
					<tr>
						<td align="center">등록된 거래처가 없습니다.</td>
					</tr>
				</table>
		<%
			} else {
		%>
				<table class="table table-striped" cellpadding="0" cellspacing="0"
					align="center">
					<tr height="30">
						<td align="center" width="10"><input type="checkbox" name="cusAllCheck" id="cusAllCheck"></td>
						<td align="center" width="50">거래처 코드</td>
						<td align="center" width="50">거래처명</td>
						<td align="center" width="80">사업자 번호</td>
						<td align="center" width="50">대표자</td>
						<td align="center" width="50">전화번호</td>
						<td align="center" width="200">주소</td>
						<td align="center" width="100">등록일</td>
					</tr>
			<%
				for (int i = 0; i < customerList.size(); i++) {
					CustomerVO vo = (CustomerVO) customerList.get(i);
			%>
					<tr height="30">
						<td align="center" width="10"><input type="checkbox"
							id="cusCheck"></td>
						<td align="center" width="50"><a
							href="<%=request.getContextPath()%>/ex/cusContentEx.jsp?num=<%=vo.getNum()%>&pageNum=<%=currentPage%>"><%=vo.getCusCode()%></a></td>
						<td align="center" width="50"><%=vo.getCusName()%></td>
						<td align="center" width="80"><%=vo.getLicenseNum()%></td>
						<td align="center" width="50"><%=vo.getCusRep()%></td>
						<td align="center" width="50"><%=vo.getCusNumber()%></td>
						<td align="center" width="200"><%=vo.getCusAddress()%></td>
						<td align="center" width="100"><%=sdf.format(vo.getReg_date())%></td>
					</tr>
			<%
				}
			%>
		
		<%
			}
		%>
		</table>
		<ul class="pagination">
		<%
		
			if (count != 0) {
				int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
				int startPage = (int) (currentPage / 10) * 10 + 1;
				int pageBlock = 10;
				int endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
		%>
				<li><a href="/lms.webPrj/ex/cusListEx.jsp?pageNum=<%=startPage%>">&laquo;</a></li>
		<%
					for (int i = startPage; i <= endPage; i++) {
		%>
		
			<li><a href="/lms.webPrj/ex/cusListEx.jsp?pageNum=<%=i%>"><%=i%></a></li>
			<%
					}
			%>
			<li><a href="/lms.webPrj/ex/cusListEx.jsp?pageNum=<%=endPage%>">&raquo;</a></li>
			<%
			}
		%>
		</ul>
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