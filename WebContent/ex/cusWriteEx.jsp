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

	<!-- 거래처 등록 화면 -->
	<div class="container">
		<form method="post" name="writeform"
			action="<%=request.getContextPath() %>/CustomerController?cmd=insertCustomer">
			<div class="form-group">
				<input type="hidden" name="num" value="<%=num%>"> <label><b>거래처
						코드</b></label> <input type="text" class="form-control" name="cusCode">
				<label><b>거래처</b></label>
				<%
								if (request.getParameter("num") == null) {
							%>
				<input type="text" class="form-control" maxlength="50"
					name="cusName">
				<%
							} 
						%>
				<label><b>대표자</b></label> <input type="text" class="form-control"
					maxlength="50" name="cusRep"> <label><b>사업자 번호</b></label>
				<input type="text" class="form-control" maxlength="30"
					name="licenseNum"> <label><b>전화번호</b></label> <input
					type="text" class="form-control" maxlength="15" name="cusNumber">
				<label><b>주소</b></label> <input type="text" class="form-control"
					maxlength="50" name="cusAddress">
			</div>
			<input id="writing" class="btn btn-default" type="submit" value="등록">
			<input type="reset" class="btn btn-default" value="취소"> <input
				type="button" class="btn btn-default" value="목록"
				OnClick="window.location.href='cusListEx.jsp'">
		</form>
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