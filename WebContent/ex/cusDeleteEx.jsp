<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
  int num = Integer.parseInt(request.getParameter("num"));
  String pageNum = request.getParameter("pageNum");
  String memID = (String) session.getAttribute("MEMBERID");
	/* boolean login = memID == null ? false : true;
	if (login) { */
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Logistic Management System</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
</head>
<body>
	<jsp:include page="/common/category.jsp" flush="false" />
<div class="container">
		<center>
			<form method="POST" name="delForm"
				action="<%=request.getContextPath() %>/BoardController?cmd=deleteBoard"
				onsubmit="return deleteSave()">
		<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="login-panel panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">관리자 비밀번호를 입력해주세요.</h3>
					</div>
					<div class="panel-body">
						<form method="POST" role="form">
							<fieldset>
								<div class="form-group">
									<input class="form-control" placeholder="id" id="adID" name="adID"
										type="text" value="<%= (String)session.getAttribute("MEMBERID") %>" readonly>
								</div>
								<div class="form-group">
									<input class="form-control" placeholder="Password"
										name="adPass" id="adPass" type="password">
								</div>
								<input type="button" id="adminLogin" class="btn btn-lg btn btn-primary" value="삭제">
								<input type="button" id="adminLogin" class="btn btn-lg btn btn-success" value="취소" onclick="document.location.href='cusListEx.jsp?pageNum=<%=pageNum%>'">
							</fieldset>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
		</center>
	</div>
</body>
</html>
<%
	//} else {
%>
<!-- 
<script>
		alert("로그인을 하세요.");
		document.location.href="../index.jsp";
	</script>
-->
<% // } %>