<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="vo.CustomerVO"%>
<%@ page import="entity.BoardEntity"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%
	/* String memID=(String)session.getAttribute("MEMBERID");
	boolean login = memID == null ? false : true;
	if (login) { */

	int num = Integer.parseInt(request.getParameter("num"));
	String pageNum = request.getParameter("pageNum");
	String result = (String) request.getAttribute("result");

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	if (result != "1") {
%>
<jsp:forward page="/CustomerController?cmd=getCustomer">
	<jsp:param value="<%=num%>" name="num" />
	<jsp:param value="<%=pageNum%>" name="pageNum" />
</jsp:forward>
<%
	}
	try {
		CustomerVO vo = (CustomerVO) request.getAttribute("vo");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Logistic Management System</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="../js/script.js"></script>
</head>
<body>
	<jsp:include page="/common/category.jsp" flush="false" />
	<form>
		<div class="form-group">
			<input type="hidden" id="num" value="<%=num%>"> <input
				type="hidden" id="pageNum" value="<%=pageNum%>"> <label><b>거래처
					코드</b></label> <input type="text" class="form-control" id="cusCode"
				value="<%=vo.getCusCode()%>" readonly="readonly"> <label><b>거래처</b></label>
			<input type="text" class="form-control" id="cusName"
				value="<%=vo.getCusName()%>"> <label><b>대표자</b></label> <input
				type="text" class="form-control" id="cusRep"
				value="<%=vo.getCusRep()%>"> <label><b>사업자 번호</b></label> <input
				type="text" class="form-control" id="licenseNum"
				value="<%=vo.getLicenseNum()%>"> <label><b>전화번호</b></label>
			<input type="text" class="form-control" id="cusNumber"
				value="<%=vo.getCusNumber()%>"> <label><b>주소</b></label> <input
				type="text" class="form-control" id="cusAddress"
				value="<%=vo.getCusAddress()%>"> <label><b>등록일</b></label> <input
				type="text" class="form-control" id="reg_date" readonly="readonly"
				value="<%=sdf.format(vo.getReg_date())%>">
		</div>
		<input type="submit" id="updateContent" class="btn btn-default"
			value="수정"> <input type="button" id="deleteCus"
			class="btn btn-default" value="삭제"> <input type="button"
			class="btn btn-default" value="목록"
			onclick="document.location.href='cusListEx.jsp?pageNum=<%=pageNum%>'">
	</form>
</body>
</html>
<%
	} catch (Exception e) {
	}

	// } else {
%>
<!-- 
<script>
		alert("로그인을 하세요.");
		document.location.href="../index.jsp";
	</script>
 -->
<%
	// }
%>